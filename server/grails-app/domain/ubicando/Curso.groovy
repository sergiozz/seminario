package dominio
import grails.rest.Resource

@Resource(uri = '/curso')
class Curso {

    ArrayList<Horario> horarios
    Docente docenteTitular
    String codMateria
    String materia
    Integer numCurso
    boolean aceptaSuscripcion = true
    Aula aulaActual

    static constraints = {
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
}
