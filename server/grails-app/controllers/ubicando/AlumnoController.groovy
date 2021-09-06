package ubicando
//import grails.converters.JSON

class AlumnoController {

    static scaffold = Alumno

	def alumnoService

    def getAll(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond alumnoService.list(params), model:[alumnoCount: alumnoService.count()]
    }

    def getAllCursosInscriptos(Integer idAlumno) {
        respond alumnoService.obtenerCursosSuscriptos(idAlumno)
    }

    def suscribirCurso(RequestSuscribirCurso requestSuscribirCurso) {
        def respuesta = alumnoService.suscribirCurso(requestSuscribirCurso.idAlumno, requestSuscribirCurso.idCurso)
        def mapResponse
        if (respuesta.contains("Error")) mapResponse = [status: 400, mensaje: respuesta]
        else mapResponse = [status: 200, mensaje: respuesta]

        respond mapResponse  
    }

    def puntuarCurso(RequestPuntuarCurso requestPuntuarCurso){
        def respuesta = alumnoService.puntuarCurso(requestPuntuarCurso.idAlumno, requestPuntuarCurso.calificacion, requestPuntuarCurso.comentario, requestPuntuarCurso.idCurso)
        def mapResponse
        if (respuesta.contains("Error")) mapResponse = [status: 400, mensaje: respuesta]
        else mapResponse = [status: 200, mensaje: respuesta]

        respond mapResponse  
    }

}
