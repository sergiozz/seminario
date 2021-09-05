package ubicando

import java.time.LocalDate
import java.time.LocalDateTime

class Docente {

    String nombre
    String apellido
    List titularDeCursos = []
    List solicitudIntercambio = []
    static hasMany = [titularDeCursos: Curso, solicitudIntercambio: SolicitudIntercambio]
    //ArrayList<Curso> titularDeCursos = new ArrayList<Curso>()
    //ArrayList<SolicitudIntercambio> solicitudIntercambios = new ArrayList<SolicitudIntercambio>()

    static constraints = {
    }

    void cambiarAulaDeCurso(Curso curso, Aula aulaNueva){
        if (titularDeCursos.contains(curso) && aulaNueva.puedeAgregarCurso(curso)){
            curso.getAulaActual().getCursos().remove(curso)
            aulaNueva.agregarCurso(curso)
        }
    }

    void solicitarIntercambio(Curso curso){
        curso.getDocenteTitular().getSolicitudIntercambios().add(new SolicitudIntercambio())
    }

    void aceptarSolicitudIntercambio(SolicitudIntercambio solicitud){
        solicitud.aceptarIntercambio(this)
    }

    void rechazrSolicitudIntercambio(SolicitudIntercambio solicitud){
        solicitud.rechazarIntercambio(this)
    }


    void reservarAulaParaExamen(Curso curso, Aula aula, Integer cantidadAlumnos, LocalDate diaExamen, Horario horarioExamen){
        if(aula.disponibleParaExamen(cantidadAlumnos, horaInicio, horaFin)){
            Examen examen = new Examen(diaExamen: diaExamen, horario: horarioExamen, materia: curso.getcodMateria(), cantidadAlumnos: cantidadAlumnos)
            aula.reservarParaExamen(examen)
            examen.getCursos().add(curso)
        }
    }
}
