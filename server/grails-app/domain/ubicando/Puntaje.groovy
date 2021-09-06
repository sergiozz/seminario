package ubicando

class Puntaje {

    Long idAlumno
    Integer calificacion
    String comentario
    Long idCurso

    static constraints = {
        calificacion blank: false, min: 0, max: 10
        comentario blank: false, nullable: false
    }    
}
