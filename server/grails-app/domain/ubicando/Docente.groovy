package ubicando

import java.time.LocalDate
import java.time.LocalDateTime

class Docente {

    String nombre
    String apellido
    List titularDeCursos = []
    List solicitudIntercambios = []
    static hasMany = [titularDeCursos: Curso, solicitudIntercambios: SolicitudIntercambio]

    static constraints = {
        titularDeCursos nullable: true
        solicitudIntercambios nullable: true
    }

    void cambiarAulaDeCurso(Curso curso, Aula aulaNueva){
        if (titularDeCursos.contains(curso) && aulaNueva.puedeAgregarCurso(curso)){
            curso.getAulaActual().getCursos().remove(curso)
            aulaNueva.agregarCurso(curso)
        } 
    }

    void solicitarIntercambio(Curso cursoSolicitante, cursoSolicitado){
        if(cursoSolicitante.docenteTitular == this){
            cursoSolicitado.getDocenteTitular().getSolicitudIntercambios().add(new SolicitudIntercambio(cursoSolicitante: cursoSolicitante, cursoSolicitado: cursoSolicitado))
        }
    }

    def aceptarSolicitudIntercambio(SolicitudIntercambio solicitud){
        return solicitud.aceptarIntercambio(this)
    }

    def rechazarSolicitudIntercambio(SolicitudIntercambio solicitud){
        return solicitud.rechazarIntercambio(this)
    }

/*     void reservarAulaParaExamen(Curso curso, Aula aula, Integer cantidadAlumnos, LocalDate diaExamen, Horario horarioExamen){
        if(aula.disponibleParaExamen(cantidadAlumnos, horaInicio, horaFin)){
            Examen examen = new Examen(diaExamen: diaExamen, horario: horarioExamen, materia: curso.getcodMateria(), cantidadAlumnos: cantidadAlumnos)
            aula.reservarParaExamen(examen)
            examen.getCursos().add(curso)
        }
    } */
}
