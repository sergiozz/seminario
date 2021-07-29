package ubicando

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

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

	ArrayList getCurrent() {
        ["Failed to update book","prueba","otra opcion"]
    }

/*     LinkedHashSet<Pedido> getPedidosEnProceso(Alumno alumno) {
        return alumno.getPedidosEnProceso();
    }

    boolean tienePedidosEnProceso(Alumno alumno) {
        return alumno.tienePedidosEnProceso();
    } */

}