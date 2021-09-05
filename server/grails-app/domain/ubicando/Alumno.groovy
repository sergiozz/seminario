package ubicando

import java.time.LocalDateTime
import static Constants.*

class Alumno {

    String nombre
    String apellido
    //TODOS los hashmap y arraylist creo q deberiamos borrarlos
    HashMap<Curso, LocalDateTime> cursosSuscriptos2 = new HashMap<Curso, LocalDateTime>()
    ArrayList<Puntaje> puntajes = new ArrayList<Puntaje>()

    List cursosSuscriptos = []
    static hasMany = [ cursosSuscriptos: Curso ]

    static constraints = {
        cursosSuscriptos nullable: true
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
        return (suscriptoACurso(curso) && cursosSuscriptos2.get(curso).isBefore(dia.minusDays(Constants.MIN_DIAS_PARA_PUNTUAR)))
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
