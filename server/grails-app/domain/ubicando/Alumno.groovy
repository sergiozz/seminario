package ubicando

import java.time.LocalDateTime

class Alumno {

    String nombre
    String apellido
    HashMap<Curso, LocalDateTime> cursosSuscriptos = new HashMap<Curso, LocalDateTime>()
    ArrayList<Puntaje> puntajes = new ArrayList<Puntaje>()

    final Integer MAXSUSCRIPCIONES = 10
    final Integer MIN_DIAS_PARA_PUNTUAR = 14

    static constraints = {
    }

    private boolean suscriptoACurso (Curso curso){
        return cursosSuscriptos.containsKey(curso)
    }

    void suscribirseACurso(Curso curso){
        if (!suscriptoACurso(curso) && cursosSuscriptos.size() + 1 <= MAXSUSCRIPCIONES && curso.getAceptaSuscripcion()){
            cursosSuscriptos.put(curso, LocalDateTime.now())
        }
    }

    private boolean puedePuntuarCurso (Curso curso, LocalDateTime dia){
        return (suscriptoACurso(curso) && cursosSuscriptos.get(curso).isBefore(dia.minusDays(MIN_DIAS_PARA_PUNTUAR)))
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
