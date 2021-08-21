package ubicando

class Curso {

    ArrayList<Horario> horarios
    Docente docenteTitular
    //static hasOne = [docenteTitular: Docente]
    String codMateria
    String descripcion
    //Materia materia
    Integer numCurso
    boolean aceptaSuscripcion = true
    Aula aulaActual
    HashMap<Alumno, Puntaje> puntajes = new HashMap<Alumno, Puntaje>()
    BigDecimal calificacionMedia = 0

    static constraints = {
        aulaActual nullable: true
    }

    boolean superpuesto(Curso otroCurso) {
        boolean superpuesto = false
        int i = 0
        while (!superpuesto && i < horarios.size()){
            int j = 0
            while (!superpuesto && j < otroCurso.horarios.size()) {
                superpuesto = curso.superpuesto(cursos.get(i))
                j++
            }
            i++
        }
        return !superpuesto
    }

    boolean puedePuntuar(Alumno alumno){
        return (!puntajes.containsKey(alumno))
    }

    void recibirPuntuacion(Puntaje puntaje){
        if (puedePuntuar(puntaje.alumno)){
            calificacionMedia = (calificacionMedia * puntajes.size() + puntaje.calificacion) / (puntajes.size() + 1)
            puntajes.put(puntaje.alumno, puntaje)
        }
    }
}
