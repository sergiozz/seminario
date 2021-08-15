package ubicando

class DocenteController {

    static scaffold = Docente


	def docenteService

    def getAll(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond docenteService.list(params), model:[docenteCount: docenteService.count()]
    }
}
