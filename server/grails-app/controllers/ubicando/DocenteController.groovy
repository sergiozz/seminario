package ubicando

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DocenteController {

    static scaffold = Docente

	def docenteService

    def getAll(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond docenteService.list(params), model:[docenteCount: docenteService.count()]
    }

    def getAllTitularDeCursos(Integer idDocente) {
        Docente docente = Docente.findById(idDocente)
        respond docente.titularDeCursos 
    }

    def solicitarCambioDeAula(Integer idDocente, Integer idCurso, Integer idAula){
        respond docenteService.solicitarCambioDeAula(idDocente, idCurso, idAula) 
        //TODO conectar con la historia de solicitar intercambio de aula con otro curso si no hay aulas disponibles
    }
/* 
    Integer idDocente
    Integer idCurso
    Integer duracionExamen
    Integer nroAlumnos
    String fechaExamen
    String horaExamen */   

    def solicitarAulaParaExamen(RequestReservarAula requestReservarAula){

        final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");        
        final LocalDate fechaExamen = LocalDate.parse(requestReservarAula.fechaExamen + ' ' + requestReservarAula.horaExamen, DATE_FORMAT);
        println(fechaExamen)

        def aulasDisponibles = aulaService.obtenerAulasDisponiblesParaExamen(fechaExamen, requestReservarAula.nroAlumnos)
/* 
        Examen examen = new Examen(diaExamen: fechaExamen, horario: horario3, cantidadAlumnos: requestReservarAula.nroAlumnos)
        def respuesta = aulaService.solicitarAulaParaExamen(requestSuscribirCurso.idAlumno, requestSuscribirCurso.idCurso) */
 
        def mapResponse
        if (aulasDisponibles.isEmpty()) mapResponse = [status: 400, mensaje: 'No se encontraron aulas disponibles']
        else mapResponse = [status: 200, data: aulasDisponibles]

        respond mapResponse 
    }
}
