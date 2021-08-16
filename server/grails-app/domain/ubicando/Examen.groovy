package ubicando

import java.time.LocalDateTime

class Examen {

    LocalDateTime horaInicio
    LocalDateTime horaFin
    Integer cantidadAlumnos
    String codMateria
    ArrayList<Curso> cursos = new ArrayList<Curso>()
    ArrayList<Aula> aulas = new ArrayList<Aula>()

    static constraints = {
        horaInicio nullable: false, blank: false
        horaFin nullable: false, blank: false
    }


    void asignarAula(Aula aula) {
        this.aulas.add(aula)
    }
}
