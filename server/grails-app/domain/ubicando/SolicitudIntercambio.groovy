package ubicando

import java.time.LocalDateTime

class SolicitudIntercambio {

    Curso cursoSolicitante
    Curso cursoSolicitado
    boolean rechazada = false
    boolean respondida = false
    LocalDateTime diaSolicitud = LocalDateTime.now()

    static constraints = {
    }

    def aceptarIntercambio(Docente docente) {
        if(rechazada){
            return "Ya fue rechazada"
        }
        if(respondida){
            return "ya fue respondida"
        }
        if (diaSolicitud.plusDays(14).isBefore(LocalDateTime.now())){
            return"Paso el tiempo limite para responder"
        }
        if (!rechazada && !respondida && diaSolicitud.plusDays(14).isAfter(LocalDateTime.now())){
            def aulaCursoSolicitado = cursoSolicitado.getAulaActual()
            def aulaCursoSolicitante = cursoSolicitante.getAulaActual()
            aulaCursoSolicitado.getCursos().remove(cursoSolicitado)
            aulaCursoSolicitante.getCursos().remove(cursoSolicitante)
            aulaCursoSolicitado.agregarCurso(cursoSolicitante)
            aulaCursoSolicitante.agregarCurso(cursoSolicitado)
            respondida = true
            return "OK"
        }
    }

    def rechazarIntercambio(Docente docente) {
        if(rechazada){
            return "Ya fue rechazada"
        }
        if(respondida){
            return "ya fue respondida"
        }
        if (diaSolicitud.plusDays(14).isBefore(LocalDateTime.now())){
            return"Paso el tiempo limite para responder"
        }
        rechazada = true
        respondida = true
        return "OK"
    }
}
