package ubicando

import java.time.LocalDate
import java.time.LocalDateTime

class Examen {

    LocalDate diaExamen
    Horario horario
    Integer cantidadAlumnos
    String codMateria
    ArrayList<Curso> cursos = new ArrayList<Curso>()
    ArrayList<Aula> aulas = new ArrayList<Aula>()

    static constraints = {
    }


    void asignarAula(Aula aula) {
        this.aulas.add(aula)
    }
}
