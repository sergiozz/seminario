package ubicando
import grails.converters.JSON

class AlumnoController {

    static scaffold = Alumno

	def alumnoService

    def getAll(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond alumnoService.list(params), model:[alumnoCount: alumnoService.count()]
    }

    def getAllCursosInscriptos(Integer id) {
        respond alumnoService.obtenerCurosSuscriptos(id)
    }

    def suscribirCurso(Integer idAlumno, Integer idCurso) {
        def respuesta = alumnoService.suscribirCurso(idAlumno, idCurso)
        def mapResponse
        if (respuesta.contains("Error")) mapResponse = [status: 400, mensaje: respuesta]
        else mapResponse = [status: 200, mensaje:respuesta]

        respond mapResponse  
    }

    def suscribirCurso2(RequestSuscribirCurso requestSuscribirCurso) {
        def respuesta = alumnoService.suscribirCurso(requestSuscribirCurso.idAlumno, requestSuscribirCurso.idCurso)
        def mapResponse
        if (respuesta.contains("Error")) mapResponse = [status: 400, mensaje: respuesta]
        else mapResponse = [status: 200, mensaje:respuesta]

        respond mapResponse  
    }

    def puntuarCurso(Integer idAlumno, Integer idCurso, Integer calificacion, String comentario){
        respond alumnoService.puntuarCurso(idAlumno, idCurso)
    }

}
