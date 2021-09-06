package ubicando

class Puntaje {

    Integer idAlumno
    Integer calificacion
    String comentario
    Integer idCurso

    static constraints = {
        calificacion blank: false, min: 0, max: 10
        comentario blank: false, nullable: false
    }    
}
