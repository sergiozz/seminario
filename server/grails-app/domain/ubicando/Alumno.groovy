package dominio
import grails.rest.Resource

@Resource(uri = '/alumno')
class Alumno {

    String Nombre
    String Apellido
    ArrayList<Curso> cursosSuscriptos = new ArrayList<Curso>()

    final Integer MAXSUSCRIPCIONES = 10

    static constraints = {
    }

    private suscriptoACurso (Curso curso){
        return cursosSuscriptos.contains(curso)
    }

    void suscribirseACurso(Curso curso){
        if (!suscriptoACurso(curso) && cursosSuscriptos.size() + 1 <= MAXSUSCRIPCIONES && curso.getAceptaSuscripcion()){
            cursosSuscriptos.add(curso)
        }
    }
}
