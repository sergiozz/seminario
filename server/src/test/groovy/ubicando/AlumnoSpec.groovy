package ubicando

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AlumnoSpec extends Specification implements DomainUnitTest<Alumno> {

    def setup() {
    }

    def cleanup() {
    }

    void "un alumno puede suscribirse a un curso que admite suscripciones"() {
        given: "un alumno y un curso que admite suscripciones"
        Alumno alumno = new Alumno()
        Curso curso = new Curso()

        when:"el alumno intenta suscribirse al curso"
        alumno.suscribirseACurso(curso)

        then: "el alumno agrega correctamente al curso a su lista de suscripciones"
        alumno.getCursosSuscriptos().size() == 1
        alumno.getCursosSuscriptos().get(0) == curso
    }

    void "un alumno intenta suscribirse a un curso que admite suscripciones dos veces y no puede"() {
        given: "un alumno y un curso que admite suscripciones"
        Alumno alumno = new Alumno()
        Curso curso = new Curso()

        when:"el alumno intenta suscribirse al curso dos veces"
        alumno.suscribirseACurso(curso)
        alumno.suscribirseACurso(curso)

        then: "el alumno solo esta suscripto una vez al curso"
        alumno.getCursosSuscriptos().size() == 1
        alumno.getCursosSuscriptos().get(0) == curso
    }

    void "un alumno no puede suscribirse a un curso que no admite suscripciones"() {
        given: "un alumno y un curso que no admite suscripciones"
        Alumno alumno = new Alumno()
        Curso curso = new Curso(aceptaSuscripcion: false)

        when:"el alumno intenta suscribirse al curso"
        alumno.suscribirseACurso(curso)

        then: "el alumno agrega correctamente al curso a su lista de suscripciones"
        alumno.getCursosSuscriptos().size() == 0
    }

    void "un alumno intenta suscribirse a as de 10 cursos que admiten suscripciones y solo queda suscriptoa los 10 primeros"() {
        given: "un alumno y 11 cursos que admiten suscripciones"
        Alumno alumno = new Alumno()
        Curso curso1 = new Curso()
        Curso curso2 = new Curso()
        Curso curso3 = new Curso()
        Curso curso4 = new Curso()
        Curso curso5 = new Curso()
        Curso curso6 = new Curso()
        Curso curso7 = new Curso()
        Curso curso8 = new Curso()
        Curso curso9 = new Curso()
        Curso curso10 = new Curso()
        Curso curso11 = new Curso()

        when:"el alumno intenta suscribirse a los cursos"
        alumno.suscribirseACurso(curso1)
        alumno.suscribirseACurso(curso2)
        alumno.suscribirseACurso(curso3)
        alumno.suscribirseACurso(curso4)
        alumno.suscribirseACurso(curso5)
        alumno.suscribirseACurso(curso6)
        alumno.suscribirseACurso(curso7)
        alumno.suscribirseACurso(curso8)
        alumno.suscribirseACurso(curso9)
        alumno.suscribirseACurso(curso10)
        alumno.suscribirseACurso(curso11)


        then: "el alumno agrega correctamente a 10 cusrsos a su lista de suscripciones y el curso 11 no lo agrega"
        alumno.getCursosSuscriptos().size() == 10
    }
}
