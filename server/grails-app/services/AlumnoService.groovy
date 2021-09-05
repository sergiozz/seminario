package ubicando

import java.time.LocalDateTime
import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
//import static Constants.*

@Service(Alumno)
@Transactional
abstract class AlumnoService {

    protected abstract Alumno get(Serializable id)

    protected abstract List<Alumno> list(Map args)

    protected abstract Long count()

    protected abstract void delete(Serializable id)

    protected abstract Alumno save(Alumno alumno)

    def String suscribirCurso(Integer idAlumno, Integer idCurso){
        Alumno alumno = Alumno.findById(idAlumno)
        Curso curso = Curso.findById(idCurso)

        return alumno.suscribirseACurso(curso)
    }

    def puntuarCurso(Integer idAlumno, Integer idCurso, Integer calificacion, String comentario){
        Alumno alumno = Alumno.findById(idAlumno)
        Curso curso = Curso.findById(idCurso)

        return alumno.puntuarCurso(curso, calificacion, comentario)
    }

    //TODO revisar
    def obtenerCursosSuscriptos (Integer idAlumno){
        Alumno alumno = Alumno.findById(idAlumno)
        return alumno.getCursosSuscriptos().keySet()
    }

}