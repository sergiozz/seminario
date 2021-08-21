package ubicando

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
//import static Constants.*

@Service(Curso)
abstract class CursoService {

    @Transactional
    protected abstract Curso get(Serializable id)

    @Transactional
    protected abstract List<Curso> list(Map args)

    @Transactional
    protected abstract Long count()

    @Transactional
    protected abstract void delete(Serializable id)

    @Transactional
    protected abstract Curso save(Curso curso)

    @Transactional
    def getCursosPorCodigo(Integer codMateria) {
		  return Curso.findAllByCodMateria(codMateria)
	  }

}