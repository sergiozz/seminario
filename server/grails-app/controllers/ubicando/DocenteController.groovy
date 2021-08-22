package ubicando

class DocenteController {

    static scaffold = Docente


	def docenteService

    def getAll(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond docenteService.list(params), model:[docenteCount: docenteService.count()]
    }

    def solicitarCambioDeAula(Integer idDocente, Integer idCurso, Integer idAula){
        respond docenteService.solicitarCambioDeAula(idDocente, idCurso, idAula)
        //TODO conectar con la historia de solicitar intercambio de aula con otro curso si no hay aulas disponibles
    }
}
