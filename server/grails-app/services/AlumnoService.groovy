package ubicando

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
//import static Constants.*

@Service(Alumno)
abstract class AlumnoService {

    @Transactional
    protected abstract Alumno get(Serializable id)

    @Transactional
    protected abstract List<Alumno> list(Map args)

    @Transactional
    protected abstract Long count()

    @Transactional
    protected abstract void delete(Serializable id)

    @Transactional
    protected abstract Alumno save(Alumno alumno)

    def suscribirCurso(Integer idAlumno, Integer idCurso){
      def alumno = Alumno.findById(idAlumno)
      def curso = Curso.findById(idCurso)

      return alumno.suscribirseACurso(curso)
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