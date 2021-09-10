package ubicando
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DocenteController {

    static scaffold = Docente

	def docenteService
	def aulaService
	def examenService

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
    }

    def solicitarAulaParaExamen(RequestReservarAula requestReservarAula){

        final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");        
        final LocalDateTime fechaExamen = LocalDateTime.parse(requestReservarAula.fechaExamen + ' ' + requestReservarAula.horaExamen, DATE_FORMAT);
        //println(fechaExamen)

        def aulasDisponibles = aulaService.obtenerAulasDisponiblesParaExamen(fechaExamen, requestReservarAula.duracionExamen, requestReservarAula.nroAlumnos)

        def mapResponse
        if (aulasDisponibles.isEmpty()) mapResponse = [status: 400, mensaje: 'Error: No se encontraron aulas disponibles. Pruebe en otro dia u horario.']
        else {            
            Curso curso = Curso.findById(requestReservarAula.idCurso)
            // toma la primera disponible
            def aulaCandidata = aulasDisponibles.get(0)
            Examen examen = new Examen(fechaExamen: fechaExamen, 
                                        duracion: requestReservarAula.duracionExamen, 
                                        cantidadAlumnos: requestReservarAula.nroAlumnos, 
                                        codMateria: curso.codMateria, 
                                        curso: curso, 
                                        aula: aulaCandidata)
            def idAsignado = examenService.save(examen)
            aulaCandidata.addToExamenes(Examen.findById(idAsignado.id))

            mapResponse = [status: 200, mensaje: 'Solicitud agendada exitosamente. El aula asignada es la ' + aulaCandidata.numero + 
                                            ' del piso '+ aulaCandidata.piso +' de capacidad '+ aulaCandidata.capacidad + 
                                            (aulaCandidata.pendienteDesinfeccion? '' : ' Atención: El aula se encuentra pendiente de desinfección! Realice un seguimiento de su estado.') ]
        }

        respond mapResponse  
    }
}
