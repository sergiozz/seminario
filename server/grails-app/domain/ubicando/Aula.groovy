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
        cursos nullable: true
        examenes nullable: true
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

    boolean chequeoPorExamen(LocalDate dia, Horario horario) {
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

    boolean horarioDisponible(LocalDate dia, Horario horario) {
        Curso curso_mock = new Curso ()
        curso_mock.horarios.add(horario)
        if (this.puedeAgregarCurso(curso_mock)){
            return this.chequeoPorExamen(dia, horario)
        }
        return false
    }

    boolean puedeAgregarExamen(LocalDateTime fechaExamenSolicitud, def duracionExamen) {
        // busca examenes q contengan a fechaExamenSolicitud
        def resultado = examenes.findAll { (it.fechaExamen.plusHours(it.duracion)).isAfter(fechaExamenSolicitud) ||
        (fechaExamenSolicitud.plusHours(duracionExamen)).isAfter(it.fechaExamen)  } 

        return resultado.isEmpty()
    }
}
