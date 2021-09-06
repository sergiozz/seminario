package ubicando

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class SolicitudIntercambioSpec extends Specification implements DomainUnitTest<SolicitudIntercambio> {

    def setup() {
    }

    def cleanup() {
    }

    void "un docente acepta el intercambio de aula propuesto por otro docente y cambian correcatmente"() {
        given: "2 docentes con cursos en el mismo horario en diferentes aulas"
        Docente docente1 = new Docente()
        Docente docente2 = new Docente()
        def horariosCurso = new ArrayList<Horario>()
        def horario1 = new Horario(dia: "Lunes", horaDesde: 10, horaHasta: 14)
        def horario2 = new Horario(dia: "Miercoles", horaDesde: 10, horaHasta: 14)
        horariosCurso.add(horario1)
        horariosCurso.add(horario2)
        Curso curso1 = new Curso(horarios: horariosCurso, docenteTitular: docente1)
        Curso curso2 = new Curso(horarios: horariosCurso, docenteTitular: docente2)
        Aula aula1 = new Aula(numero: 100, piso:1)
        Aula aula2 = new Aula(numero: 200, piso:2)
        docente1.getTitularDeCursos().add(curso1)
        docente2.getTitularDeCursos().add(curso2)
        aula1.agregarCurso(curso1)
        aula2.agregarCurso(curso2)


        when: "docente1 intenta intercambia de aula con otro curso y el docente2 acepta el intercambio"
        docente1.solicitarIntercambio(curso1, curso2)
        print(docente2.aceptarSolicitudIntercambio(docente2.getSolicitudIntercambios().get(0)))

        then: "se intercambian correctamente los cursos de aula"
        docente2.getSolicitudIntercambios().get(0).respondida == true

        aula1.getCursos().contains(curso2)
        aula2.getCursos().contains(curso1)

    }

    void "un docente no acepta el intercambio de aula propuesto por otro docente y no cambian de aulas"() {
        given: "2 docentes con cursos en el mismo horario en diferentes aulas"
        Docente docente1 = new Docente()
        Docente docente2 = new Docente()
        def horariosCurso = new ArrayList<Horario>()
        def horario1 = new Horario(dia: "Lunes", horaDesde: 10, horaHasta: 14)
        def horario2 = new Horario(dia: "Miercoles", horaDesde: 10, horaHasta: 14)
        horariosCurso.add(horario1)
        horariosCurso.add(horario2)
        Curso curso1 = new Curso(horarios: horariosCurso, docenteTitular: docente1)
        Curso curso2 = new Curso(horarios: horariosCurso, docenteTitular: docente2)
        Aula aula1 = new Aula(numero: 100, piso:1)
        Aula aula2 = new Aula(numero: 200, piso:2)
        docente1.getTitularDeCursos().add(curso1)
        docente2.getTitularDeCursos().add(curso2)
        aula1.agregarCurso(curso1)
        aula2.agregarCurso(curso2)


        when: "docente1 intenta intercambia de aula con otro curso y el docente2 acepta el intercambio"
        docente1.solicitarIntercambio(curso1, curso2)
        docente2.rechazarSolicitudIntercambio(docente2.getSolicitudIntercambios().get(0))


        then: "no se intercambian los cursos de aula"
        docente2.getSolicitudIntercambios().get(0).respondida == true
        aula1.getCursos().contains(curso1)
        aula2.getCursos().contains(curso2)

    }
}
