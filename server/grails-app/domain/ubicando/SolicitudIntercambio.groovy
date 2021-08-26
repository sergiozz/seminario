package ubicando

import java.time.LocalDateTime

class SolicitudIntercambio {

    Curso cursoSolicitante
    Curso cursoSolicitado
    Aula aulaCursoSolicitante
    Aula aulaCursoSolicitado
    boolean rechazada = false
    LocalDateTime diaSolicitud = LocalDateTime.now()

    static constraints = {
    }

    void aceptarIntercambio(Docente docente) {}

    void rechazarIntercambio(Docente docente) {}
}
