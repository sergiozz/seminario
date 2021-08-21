package ubicando

import grails.core.GrailsApplication
import grails.plugins.*
import static Constants.*

class ApplicationController implements PluginManagerAware {

    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager

    AlumnoService alumnoService
    DocenteService docenteService
    CursoService cursoService

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
        def mock_docente1
        def mock_docente2
        def mock_docente3
        def mock_docente4

        if (alumnoService.count() == 0){
            def mock_alumno1 = new Alumno(nombre: "Roberto", apellido: "Lopez")
            def mock_alumno2 = new Alumno(nombre: "Lucas", apellido: "Gonzalez")
            def mock_alumno3 = new Alumno(nombre: "Marta", apellido: "Perez")
            def mock_alumno4 = new Alumno(nombre: "Paula", apellido: "Ortiz")
            alumnoService.save(mock_alumno1)
            alumnoService.save(mock_alumno2)
            alumnoService.save(mock_alumno3)
            alumnoService.save(mock_alumno4)
        }

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

        if (cursoService.count() == 0){
            def mock_curso1 = new Curso(codMateria: 9268, docenteTitular: mock_docente1, numCurso: 1, descripcion: "Curso Gomez")
            def mock_curso2 = new Curso(codMateria: 6147, docenteTitular: mock_docente2, numCurso: 1, descripcion: "Curso Mendez")
            def mock_curso3 = new Curso(codMateria: 7085, docenteTitular: mock_docente3, numCurso: 1, descripcion: "Curso Cruz")
            def mock_curso4 = new Curso(codMateria: 9268, docenteTitular: mock_docente4, numCurso: 2, descripcion: "Curso Peralta")
            cursoService.save(mock_curso1)
            cursoService.save(mock_curso2)
            cursoService.save(mock_curso3)
            cursoService.save(mock_curso4)
        }

        return 
    }
}
