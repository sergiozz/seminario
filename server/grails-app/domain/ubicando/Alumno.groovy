package ubicando

import java.time.LocalDateTime
import static Constants.*

class Alumno {

    String nombre
    String apellido
    List cursosSuscriptos = []
    List puntuacionesRealizadas = []
    static hasMany = [ cursosSuscriptos: Curso, puntuacionesRealizadas: Puntaje]

    static constraints = {
        cursosSuscriptos nullable: true
        puntuacionesRealizadas nullable: true
    }

    private boolean suscriptoACurso (Curso curso){
        return cursosSuscriptos?.contains(curso)
    }

    String suscribirseACurso(Curso curso){
        if (suscriptoACurso(curso)) return "Error: Ya se encuentra suscripto el alumno al curso"
        if (cursosSuscriptos.size() + 1 > Constants.MAXSUSCRIPCIONES ) return "Error: Supera el numero de suscripciones permitidas"
        if (!curso.getAceptaSuscripcion()) return "Error: El curso no acepta nuevas suscripciones"
        
        addToCursosSuscriptos(curso)
        return "El alumno fue suscripto al curso exitosamente"
    }

    private boolean puedePuntuarCurso (Curso curso, LocalDateTime dia){
        return (suscriptoACurso(curso) && curso.fechaDeSuscripciones.isBefore(dia.minusDays(Constants.MIN_DIAS_PARA_PUNTUAR)))
    }

    String puntuarCurso (Curso curso, Integer calificacion, String comentario){
        if (puedePuntuarCurso(curso, LocalDateTime.now())){
            Puntaje puntuacion = new Puntaje(alumno: this, calificacion: calificacion, comentario: comentario, curso: curso)
            if (curso.puedePuntuar(this)){
                addToPuntuacionesRealizadas(puntuacion)
                curso.recibirPuntuacion(puntuacion)
                return "La puntuación fue realizada con éxito"
            }
            else {
                return "Error: El curso no admite nuevas puntuaciones"
            }
        }
        else {
            return "Error: El alumno debe estar suscripto al curso por al menos de "+ Constants.MIN_DIAS_PARA_PUNTUAR +" días"
        }
    }
}
