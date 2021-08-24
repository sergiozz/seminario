package ubicando

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

import java.time.LocalDate

class ExamenSpec extends Specification implements DomainUnitTest<Examen> {

    def setup() {
    }

    def cleanup() {
    }

    void "en un aula vacia puedo cargar un examen"() {
        given: "un aula vacia"
        Aula aula1 = new Aula(numero: 100, piso:1)

        when: "intento cargar un examen"
        def horario1 = new Horario(dia: "Lunes", horaDesde: 10, horaHasta: 14)
        Examen examen = new Examen(diaExamen: LocalDate.now(), horario: horario1)
        aula1.reservarParaExamen(examen)

        then:"se pudo agregar el examen"
        aula1.examenes.size() == 1
    }

    void "en un aula no puedo cargar un examen en un horario que se superpone con otro"() {
        given: "un aula vacia"
        Aula aula1 = new Aula(numero: 100, piso:1, capacidad:  50)

        when: "intento cargar un examen que se superpone con otro"
        def horario1 = new Horario(dia: "Lunes", horaDesde: 10, horaHasta: 14)
        Examen examen = new Examen(diaExamen: LocalDate.now(), horario: horario1)
        aula1.reservarParaExamen(examen)

        def horario2 = new Horario(dia: "Lunes", horaDesde: 10, horaHasta: 13)
        Examen examen2 = new Examen(diaExamen: LocalDate.now(), horario: horario2, cantidadAlumnos: 20)
        aula1.reservarParaExamen(examen2)

        then:"se pudo agregar el examen"
        aula1.examenes.size() == 1
        aula1.examenes.get(0) == examen
    }

    void "en un aula con un curso no puedo cargar un examen si se superponen los horario"() {
        given: "un aula con un curso"
        Aula aula1 = new Aula(numero: 100, piso:1, capacidad: 50)
        def horariosCurso = new ArrayList<Horario>()
        def horario1 = new Horario(dia: "Lunes", horaDesde: 10, horaHasta: 14)
        def horario2 = new Horario(dia: "Miercoles", horaDesde: 10, horaHasta: 14)
        horariosCurso.add(horario1)
        horariosCurso.add(horario2)
        Curso curso = new Curso(horarios: horariosCurso)
        aula1.agregarCurso(curso)

        when: "intento cargar un examen"
        def horario3 = new Horario(dia: "Lunes", horaDesde: 11, horaHasta: 15)
        Examen examen = new Examen(diaExamen: LocalDate.now(), horario: horario3, cantidadAlumnos: 20)
        aula1.reservarParaExamen(examen)

        then:"no se pudo agregar el examen"
        aula1.examenes.size() == 0
    }


}
