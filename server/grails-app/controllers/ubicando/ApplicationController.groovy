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
            def alumno1 = new Alumno(nombre: "Roberto", apellido: "Lopez")
            def alumno2 = new Alumno(nombre: "Lucas", apellido: "Gonzalez")
            def alumno3 = new Alumno(nombre: "Marta", apellido: "Perez")
            def alumno4 = new Alumno(nombre: "Paula", apellido: "Ortiz")
            alumnoService.save(alumno1)
            alumnoService.save(alumno2)
            alumnoService.save(alumno3)
            alumnoService.save(alumno4)
        }

        if (docenteService.count() == 0){
            def docente1 = new Docente(nombre: "Julio", apellido: "Gomez")
            def docente2 = new Docente(nombre: "Lucio", apellido: "Mendez")
            def docente3 = new Docente(nombre: "Don Pedro", apellido: "Cruz")
            docenteService.save(docente1)
            docenteService.save(docente2)
            docenteService.save(docente3)
        }
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
