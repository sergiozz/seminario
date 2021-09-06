package ubicando

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class DocenteSpec extends Specification implements DomainUnitTest<Docente> {

    def setup() {
    }

    def cleanup() {
    }

    void "docente puede cambiar el aula de su curso si el aula esta disponible"() {
        given: "docente con un curso y un aula con disponibilidad"
        Docente docente = new Docente()
        def horariosCurso = new ArrayList<Horario>()
        def horario1 = new Horario(dia: "Lunes", horaDesde: 10, horaHasta: 14)
        def horario2 = new Horario(dia: "Miercoles", horaDesde: 10, horaHasta: 14)
        horariosCurso.add(horario1)
        horariosCurso.add(horario2)
        Curso curso = new Curso(horarios: horariosCurso)
        Aula aula1 = new Aula(numero: 100, piso:1)
        docente.getTitularDeCursos().add(curso)
        aula1.agregarCurso(curso)

        Aula aula2 = new Aula(numero: 200, piso:2)

        when: "intenta cambiar el curso de aula"
        docente.cambiarAulaDeCurso(curso, aula2)

        then: "el curso 2 se asigna al aula 2 y se libera del aula 1"
        aula1.getCursos().size() == 0
        !aula1.getCursos().contains(curso)
        aula2.getCursos().size() == 1
        aula2.getCursos().contains(curso)
    }

    void "docente no puede cambiar el aula de su curso si el aula no esta disponible"() {
        given: "docente con un curso y un aula sin disponibilidad"
        Docente docente = new Docente()
        def horariosCurso = new ArrayList<Horario>()
        def horario1 = new Horario(dia: "Lunes", horaDesde: 10, horaHasta: 14)
        def horario2 = new Horario(dia: "Miercoles", horaDesde: 10, horaHasta: 14)
        horariosCurso.add(horario1)
        horariosCurso.add(horario2)
        Curso curso = new Curso(horarios: horariosCurso)
        Aula aula1 = new Aula(numero: 100, piso:1)
        docente.getTitularDeCursos().add(curso)
        aula1.agregarCurso(curso)

        Curso curso2 = new Curso(horarios: horariosCurso)
        Aula aula2 = new Aula(numero: 200, piso:2)
        aula2.agregarCurso(curso2)

        when: "intenta cambiar el curso de aula"
        docente.cambiarAulaDeCurso(curso, aula2)

        then: "el curso 2 se mantiene en el aula 1 y no se asigna al aula 2"
        aula1.getCursos().size() == 1
        aula1.getCursos().contains(curso)
        aula2.getCursos().size() == 1
        !aula2.getCursos().contains(curso)
        aula2.getCursos().contains(curso2)
    }
}
