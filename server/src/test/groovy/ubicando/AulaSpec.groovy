package ubicando

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AulaSpec extends Specification implements DomainUnitTest<Aula> {

    def setup() {

    }

    def cleanup() {
    }

    void "en un aula vacia puedo cargar un curso"() {
        given: "un aula vacia"
        Aula aula1 = new Aula(numero: 100, piso:1)

        when: "intento cargar un curso"
        def horariosCurso = new ArrayList<Horario>()
        def horario1 = new Horario(dia: "Lunes", horaDesde: 10, horaHasta: 14)
        def horario2 = new Horario(dia: "Miercoles", horaDesde: 10, horaHasta: 14)
        horariosCurso.add(horario1)
        horariosCurso.add(horario2)
        Curso curso = new Curso(horarios: horariosCurso)
        aula1.agregarCurso(curso)

        then:"se pudo agregar el curso"
            aula1.cursos.size() == 1
    }

    void "en un aula no puedo cargar un curso en un horario que existe otro curso"() {
        given: "un aula con un curso"
        Aula aula1 = new Aula(numero: 100, piso:1)
        def horariosCurso = new ArrayList<Horario>()
        def horario1 = new Horario(dia: "Lunes", horaDesde: 10, horaHasta: 14)
        def horario2 = new Horario(dia: "Miercoles", horaDesde: 10, horaHasta: 14)
        horariosCurso.add(horario1)
        horariosCurso.add(horario2)
        Curso curso = new Curso(horarios: horariosCurso)
        aula1.agregarCurso(curso)

        when: "intento cargar un curso con un horario que se superpone"
        def horariosCurso2 = new ArrayList<Horario>()
        def horario3 = new Horario(dia: "Lunes", horaDesde: 11, horaHasta: 15)
        def horario4 = new Horario(dia: "Miercoles", horaDesde: 11, horaHasta: 15)
        horariosCurso.add(horario3)
        horariosCurso.add(horario4)
        Curso curso2 = new Curso(horarios: horariosCurso2)
        aula1.agregarCurso(curso2)

        then:"no se pudo agregar el curso"
        aula1.cursos.size() == 1
        aula1.cursos.get(0) == curso
    }

}
