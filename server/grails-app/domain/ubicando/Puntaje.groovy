package ubicando

class Puntaje {

    Alumno alumno
    Integer calificacion
    String comentario
    Curso curso

    static constraints = {
        calificacion blank: false, min: 0, max: 10
        comentario blank: false, nullable: false
    }

    
}
