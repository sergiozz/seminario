package ubicando

import java.time.LocalDate
import java.time.LocalDateTime

class Examen {

    LocalDateTime fechaExamen
    Integer duracion
    Integer cantidadAlumnos
    String codMateria
    Curso curso 
    Aula aula

    static hasOne = [ aula: Aula]
    
    static constraints = {
          aula nullable: true
    }

/*     void asignarAula(Aula aula) {
        this.aulas.add(aula)
    } */
}
