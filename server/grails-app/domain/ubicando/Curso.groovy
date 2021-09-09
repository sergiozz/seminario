package ubicando
import java.time.LocalDateTime

class Curso {

    ArrayList<Horario> horarios = new ArrayList<Horario>()
    //List horarios = []
    Docente docenteTitular
    String codMateria
    String descripcion
    Integer numCurso
    boolean aceptaSuscripcion = true
    Aula aulaActual
    LocalDateTime fechaDeSuscripciones = LocalDateTime.now()
    List puntajes = []
    BigDecimal calificacionMedia = 0

    static hasOne = [docenteTitular: Docente]
    static hasMany = [ puntajes: Puntaje]

    static constraints = {
        aulaActual nullable: true
        puntajes nullable: true
        docenteTitular nullable: true
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

    boolean puedePuntuar(Alumno alumno){
        def cantidad = (puntajes.findAll { it.alumno == alumno }).size()
        return cantidad == 0
    }

    void recibirPuntuacion(Puntaje puntaje){
        if (puedePuntuar(puntaje.alumno)){
            calificacionMedia = (calificacionMedia * puntajes.size() + puntaje.calificacion) / (puntajes.size() + 1)
            addToPuntajes(puntaje)
        }
    }
}
