package ubicando

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
//import static Constants.*

@Service(Docente)
abstract class DocenteService {

    @Transactional
    protected abstract Docente get(Serializable id)

    @Transactional
    protected abstract List<Docente> list(Map args)

    @Transactional
    protected abstract Long count()

    @Transactional
    protected abstract void delete(Serializable id)

    @Transactional
    protected abstract Docente save(Docente docente)

}