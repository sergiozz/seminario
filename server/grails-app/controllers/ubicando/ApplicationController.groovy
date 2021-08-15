package ubicando

import grails.core.GrailsApplication
import grails.plugins.*
import static Constants.*

class ApplicationController implements PluginManagerAware {

    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager

    AlumnoService alumnoService
    DocenteService docenteService

    def index() {
        crearEstadoinicial();
        [grailsApplication: grailsApplication, pluginManager: pluginManager]
    }

    def getAllActions() {
        respond Constants.todasLasAcciones
    }

    def private crearEstadoinicial(){
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
            def mock_docente1 = new Docente(nombre: "Julio", apellido: "Gomez")
            def mock_docente2 = new Docente(nombre: "Lucio", apellido: "Mendez")
            def mock_docente3 = new Docente(nombre: "Don Pedro", apellido: "Cruz")
            docenteService.save(mock_docente1)
            docenteService.save(mock_docente2)
            docenteService.save(mock_docente3)
        }
        return 
    }
/* 
    def getAllUsers() {
        params.max = Math.min(max ?: 10, 100)
        
        def alumnosEncontrados = alumnoService.list(params), model:[alumnoCount: alumnoService.count()]
        def docentesEncontrados = docenteService.list(params), model:[docenteCount: docenteService.count()]
        respond [ 
            docentes: docentesEncontrados,
            alumnos: alumnosEncontrados
        ]
    } */
}
