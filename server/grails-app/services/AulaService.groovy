package ubicando

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
//import static Constants.*

@Service(Docente)
@Transactional
abstract class AulaService {

    protected abstract Docente get(Serializable id)

    protected abstract List<Docente> list(Map args)

    protected abstract Long count()

    protected abstract void delete(Serializable id)

    protected abstract Docente save(Docente docente)

    def obtenerAulasDisponibles(Integer idCurso, Integer capacidad){

        Curso curso = Curso.FindById(idCurso)
        //TODO probar en vista para seleccionar aula para el cambio de curso
        return Aula.findAllByCapacidadGreaterThanEquals(capacidad).findAll {aulaCandidata -> aulaCandidata.puedeAgregarCurso(curso)}
    }

    def obtenerAulasDisponiblesParaExamen(LocalDate fechaExamen, Integer capacidad){
        return Aula.findAllByCapacidadGreaterThanEquals(capacidad).findAll {aulaCandidata -> aulaCandidata.puedeAgregarExamen(fechaExamen)}
    }

}
