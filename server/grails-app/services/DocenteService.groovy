package ubicando

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
//import static Constants.*

@Service(Docente)
@Transactional
abstract class DocenteService {

    protected abstract Docente get(Serializable id)

    protected abstract List<Docente> list(Map args)

    protected abstract Long count()

    protected abstract void delete(Serializable id)

    protected abstract Docente save(Docente docente)

    def solicitarCambioDeAula(Integer idDocente, Integer idCurso, Integer idAula){ 
        Docente docente = Docente.findById(idDocente)
        Curso curso = Curso.findById(idCurso)
        Aula aula = Aula.findById(idAula)
        
        return docente.cambiarAulaDeCurso(curso, aula)
    }

}