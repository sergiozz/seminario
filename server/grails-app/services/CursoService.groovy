package ubicando

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
//import static Constants.*

@Service(Curso)
@Transactional
abstract class CursoService {

    protected abstract Curso get(Serializable id)

    protected abstract List<Curso> list(Map args)

    protected abstract Long count()
    
    protected abstract void delete(Serializable id)

    protected abstract Curso save(Curso curso)

    def getCursosPorCodigo(Integer codMateria) {
		  return Curso.findAllByCodMateria(codMateria)
	  }

}