package ubicando

import java.time.LocalDateTime
import static Constants.*

class Alumno {

    String nombre
    String apellido
    HashMap<Curso, LocalDateTime> cursosSuscriptos = new HashMap<Curso, LocalDateTime>()
    ArrayList<Puntaje> puntajes = new ArrayList<Puntaje>()

    static constraints = {
    }

    private boolean suscriptoACurso (Curso curso){
        return cursosSuscriptos.containsKey(curso)
    }

    String suscribirseACurso(Curso curso){
        if (suscriptoACurso(curso)) return "Error: Ya se encuentra suscripto el alumno al curso"
        if (cursosSuscriptos.size() + 1 >= Constants.MAXSUSCRIPCIONES ) return "Error: El curso se encuentra lleno"
        if (!curso.getAceptaSuscripcion()) return "Error: El curso no acepta nuevas suscripciones"
        
        cursosSuscriptos.put(curso, LocalDateTime.now())
        return "OK"
    }

    private boolean puedePuntuarCurso (Curso curso, LocalDateTime dia){
        return (suscriptoACurso(curso) && cursosSuscriptos.get(curso).isBefore(dia.minusDays(Constants.MIN_DIAS_PARA_PUNTUAR)))
    }

    void puntuarCurso (Curso curso, Integer calificacion, String comentario){
        if (puedePuntuarCurso(curso, LocalDateTime.now())){
            Puntaje puntaje = new Puntaje(alumno: this, curso: curso, calificacion: calificacion, comentario: comentario)
            if (curso.puedePuntuar(this)){
                puntajes.add(puntaje)
                curso.recibirPuntuacion(puntaje)
            }
        }
    }
}
