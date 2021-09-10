package ubicando

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import java.time.LocalDate
import java.time.LocalDateTime

@Service(Aula)
@Transactional
abstract class AulaService {

    protected abstract Aula get(Serializable id)

    protected abstract List<Aula> list(Map args)

    protected abstract Long count()

    protected abstract void delete(Serializable id)

    protected abstract Aula save(Aula aula)

    def obtenerAulasDisponiblesParaCambioDeCurso(Integer idCurso, Integer capacidad){
        Curso curso = Curso.findById(idCurso)
        return Aula.findAllByCapacidadGreaterThanEquals(capacidad).findAll {aulaCandidata -> aulaCandidata.puedeAgregarCurso(curso)}
    }

    def obtenerAulasDisponiblesParaExamen(LocalDateTime fechaExamen, Integer duracionExamen, Integer capacidad){
        // ordena en forma ascendente por capacidad asi se toman primero las aulas mas cercanas a lo solicitado
        return Aula.findAllByCapacidadGreaterThanEquals(capacidad, [max: 10, sort: "capacidad", order: "asc"]).findAll {aulaCandidata -> aulaCandidata.puedeAgregarExamen(fechaExamen, duracionExamen)}
    }

    def guardarRelacion(def idAula, def idExamen){
        Examen examen = Examen.findById(idExamen)
        Aula aulaCand =  Aula.findById(idAula)

         aulaCand.addToExamenes(examen)        
    }


}
