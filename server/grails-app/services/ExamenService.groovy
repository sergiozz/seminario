package ubicando

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(Examen)
@Transactional
abstract class ExamenService {

    protected abstract Examen get(Serializable id)

    protected abstract List<Examen> list(Map args)

    protected abstract Long count()
    
    protected abstract void delete(Serializable id)

    protected abstract Examen save(Examen examen)

}