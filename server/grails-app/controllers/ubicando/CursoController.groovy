package ubicando

class CursoController {

    static scaffold = Curso

	def cursoService

    def getCursosPorCodigo(Integer codMateria) {     
        respond cursoService.getCursosPorCodigo(codMateria)
    }
}
