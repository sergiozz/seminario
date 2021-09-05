package ubicando

import java.time.LocalDate
import java.time.LocalDateTime

class Aula {

    String numero
    Integer piso
    Integer capacidad
    List cursos = []
    List examenes = []
    static hasMany = [cursos: Curso, examenes: Examen]
    boolean pendienteDesinfeccion = false

    static constraints = {
    }

    boolean puedeAgregarCurso(Curso curso){
        boolean superpuesto = false
        int i = 0
        while (!superpuesto && i < cursos.size()){
            superpuesto = curso.superpuesto(cursos.get(i))
            i ++
        }
       return !superpuesto
    }

    void agregarCurso(Curso curso){
        if(puedeAgregarCurso(curso)){
            cursos.add(curso)
            curso.setAulaActual(this)
        }
    }

    boolean disponibleParaExamen(Integer cantidadAlumnos, LocalDate dia, Horario horario) {
        if (cantidadAlumnos <= capacidad && !pendienteDesinfeccion){
            return this.horarioDisponible(dia, horario)
        }
        return false
    }

    void reservarParaExamen(Examen examen) {
        if (this.disponibleParaExamen(examen.cantidadAlumnos, examen.diaExamen, examen.horario)){
            this.examenes.add(examen)
            examen.asignarAula(this)
        }
    }

    boolean horarioDisponible(LocalDate dia, Horario horario) {
        Curso curso_mock = new Curso ()
        curso_mock.horarios.add(horario)
        //TODO debug
        if (this.puedeAgregarCurso(curso_mock)){
            println("pase por aqui")
            return this.puedeAgregarExamen(dia, horario)
        }
        return false
    }

    boolean puedeAgregarExamen(LocalDate dia, Horario horario) {
        boolean superpuesto = false
        int i = 0
        while (!superpuesto && i < examenes.size()){
            if (dia == this.examenes.get(i).diaExamen){
                superpuesto = horario.superposicion(this.examenes.get(i).horario)
            }
            i ++
        }
        return !superpuesto
    }
}
