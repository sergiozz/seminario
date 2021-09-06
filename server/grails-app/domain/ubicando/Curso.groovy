package ubicando
import java.time.LocalDateTime

class Curso {

    ArrayList<Horario> horarios = new ArrayList<Horario>()
    Docente docenteTitular
    //static hasOne = [docenteTitular: Docente]
    String codMateria
    String descripcion
    Integer numCurso
    boolean aceptaSuscripcion = true
    Aula aulaActual
    LocalDateTime fechaDeSuscripciones = LocalDateTime.now()
    List puntajes = []
    //List horarios = []
    BigDecimal calificacionMedia = 0

    static hasMany = [ puntajes: Puntaje]
    static belongsTo = [alumno: Alumno]

    static constraints = {
        aulaActual nullable: true
        alumno nullable: true
        puntajes nullable: true
        //horarios nullable: true
    }

    boolean superpuesto(Curso otroCurso) {
        boolean superpuesto = false
        int i = 0
        while (!superpuesto && i < this.horarios.size()){
            int j = 0
            while (!superpuesto && j < otroCurso.horarios.size()) {
                superpuesto = otroCurso.horarios.get(j).superposicion(this.horarios.get(i))
                j++
            }
            i++
        }
        return superpuesto
    }

    // TODO verificar q funcione
    boolean puedePuntuar(Integer idAlumno){
        if (puntajes) return true
        return puntajes.findAllByIdAlumno(idAlumno).isEmpty()
    }

    // TODO verificar q funcione
    void recibirPuntuacion(Puntaje puntaje){
        if (puedePuntuar(puntaje.idAlumno)){
            calificacionMedia = (calificacionMedia * puntajes.size() + puntaje.calificacion) / (puntajes.size() + 1)
            addToPuntajes(puntaje)
        }
    }
}
