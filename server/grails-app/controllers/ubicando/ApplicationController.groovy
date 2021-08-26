package ubicando

import grails.core.GrailsApplication
import grails.plugins.*
import java.time.LocalDateTime
import static Constants.*

class ApplicationController implements PluginManagerAware {

    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager

    AlumnoService alumnoService
    DocenteService docenteService
    CursoService cursoService
    AulaService aulaService

    def index() {
        crearEstadoinicial();
        [grailsApplication: grailsApplication, pluginManager: pluginManager]
    }

    def getAllActions() {
        respond Constants.TODAS_LAS_ACCIONES
    }
    
    def getAllMaterias() {
        respond Constants.TODAS_LAS_MATERIAS
    }

    def private crearEstadoinicial(){
        Docente mock_docente1
        Docente mock_docente2
        Docente mock_docente3
        Docente mock_docente4

        Curso mock_curso1
        Curso mock_curso2
        Curso mock_curso3
        Curso mock_curso4

        Aula mock_aula1
        Aula mock_aula2
        Aula mock_aula3
        Aula mock_aula4
        Aula mock_aula5

        Alumno mock_alumno1
        Alumno mock_alumno2
        Alumno mock_alumno3
        Alumno mock_alumno4

        if (docenteService.count() == 0){
            mock_docente1 = new Docente(nombre: "Julio", apellido: "Gomez")
            mock_docente2 = new Docente(nombre: "Lucio", apellido: "Mendez")
            mock_docente3 = new Docente(nombre: "Don Pedro", apellido: "Cruz")
            mock_docente4 = new Docente(nombre: "Luis", apellido: "Peralta")
            docenteService.save(mock_docente1)
            docenteService.save(mock_docente2)
            docenteService.save(mock_docente3)
            docenteService.save(mock_docente4)
        }

        if (aulaService.count() == 0){
            mock_aula1 = new Aula(numero: 101, piso: 1, capacidad: 80)
            mock_aula2 = new Aula(numero: 102, piso: 1, capacidad: 75)
            mock_aula3 = new Aula(numero: 300, piso: 3, capacidad: 100)
            mock_aula4 = new Aula(numero: 303, piso: 3, capacidad: 50)
            mock_aula5 = new Aula(numero: 402, piso: 4, capacidad: 60, pendienteDesinfeccion: true)
            aulaService.save(mock_aula1)
            aulaService.save(mock_aula2)
            aulaService.save(mock_aula3)
            aulaService.save(mock_aula4)
            aulaService.save(mock_aula5)
        }

        if (cursoService.count() == 0){
            def horariosCurso1 = new ArrayList<Horario>()
            def horario1 = new Horario(dia: "Lunes", horaDesde: 10, horaHasta: 14)
            def horario2 = new Horario(dia: "Miercoles", horaDesde: 10, horaHasta: 14)
            horariosCurso1.add(horario1)
            horariosCurso1.add(horario2)
            mock_curso1 = new Curso(codMateria: 9268, docenteTitular: mock_docente1, numCurso: 1, descripcion: "Curso Gomez", aulaActual:  mock_aula1, horarios: horariosCurso1)

            mock_curso2 = new Curso(codMateria: 6147, docenteTitular: mock_docente2, numCurso: 1, descripcion: "Curso Mendez", aulaActual:  mock_aula2, horarios: horariosCurso1)

            def horariosCurso3 = new ArrayList<Horario>()
            horario1 = new Horario(dia: "Martes", horaDesde: 10, horaHasta: 14)
            horario2 = new Horario(dia: "Jueves", horaDesde: 10, horaHasta: 14)
            horariosCurso3.add(horario1)
            horariosCurso3.add(horario2)
            mock_curso3 = new Curso(codMateria: 7085, docenteTitular: mock_docente3, numCurso: 1, descripcion: "Curso Cruz", aulaActual: mock_aula1, horarios: horariosCurso3)

            def horariosCurso4 = new ArrayList<Horario>()
            horario1 = new Horario(dia: "Martes", horaDesde: 12, horaHasta: 15)
            horario2 = new Horario(dia: "Jueves", horaDesde: 12, horaHasta: 15)
            horariosCurso4.add(horario1)
            horariosCurso4.add(horario2)
            mock_curso4 = new Curso(codMateria: 9268, docenteTitular: mock_docente4, numCurso: 2, descripcion: "Curso Peralta", aulaActual: mock_aula4, horarios: horariosCurso4)
            cursoService.save(mock_curso1)
            cursoService.save(mock_curso2)
            cursoService.save(mock_curso3)
            cursoService.save(mock_curso4)
        }

        if (alumnoService.count() == 0){
            mock_alumno1 = new Alumno(nombre: "Roberto", apellido: "Lopez")
            mock_alumno2 = new Alumno(nombre: "Lucas", apellido: "Gonzalez")
            mock_alumno3 = new Alumno(nombre: "Marta", apellido: "Perez")
            mock_alumno4 = new Alumno(nombre: "Paula", apellido: "Ortiz")
            mock_alumno4.getCursosSuscriptos().put(mock_curso1, LocalDateTime.now().minusDays(15))
            alumnoService.save(mock_alumno1)
            alumnoService.save(mock_alumno2)
            alumnoService.save(mock_alumno3)
            alumnoService.save(mock_alumno4)
        } 
    }
}
