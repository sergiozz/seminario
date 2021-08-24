package ubicando

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

        def respuesta = alumno.suscribirseACurso(curso)
        //Es necesario?
        //if (!respuesta.contains("Error")) save(alumno)

        return respuesta
    }

    def puntuarCurso(Integer idAlumno, Integer idCurso, Integer calificacion, String comentario){
        Alumno alumno = Alumno.findById(idAlumno)
        Curso curso = Curso.findById(idCurso)

        return alumno.puntuarCurso(curso, calificacion, comentario)
    }

    //TODO revisar
    def obtenerCurosSuscriptos (Integer idAlumno){
        Alumno alumno = Alumno.findById(idAlumno)
        return alumno.getCursosSuscriptos().keySet()
    }

    
/*
	ArrayList getCurrent() {
        return Constants.todasLasAcciones
    }

     LinkedHashSet<Pedido> getPedidosEnProceso(Alumno alumno) {
        return alumno.getPedidosEnProceso();
    }

    boolean tienePedidosEnProceso(Alumno alumno) {
        return alumno.tienePedidosEnProceso();
    } */

}