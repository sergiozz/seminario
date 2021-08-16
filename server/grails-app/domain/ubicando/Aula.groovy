package ubicando

import java.time.LocalDateTime

class Aula {

    String numero
    Integer piso
    Integer capacidad
    ArrayList<Curso> cursos = new ArrayList<Curso>()
    ArrayList<Examen> examenes = new ArrayList<Examen>()
    boolean  pendienteDesinfeccion = false

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



    boolean disponibleParaExamen(Integer cantidadAlumnos, LocalDateTime horaInicio, LocalDateTime horaFin) {
        if (cantidadAlumnos <= capacidad && !pendienteDesinfeccion){
            return this.horarioDisponible(horaInicio, horaFin)
        }
    }

    void reservarParaExamen(Examen examen) {
        if (this.disponibleParaExamen(examen.cantidadAlumnos, examen.horaInicio, examen.horaFin)){
            this.examenes.add(examen)
            examen.asignarAula(this)
        }
    }

    boolean horarioDisponible(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        //TODO
        return true
    }
}
