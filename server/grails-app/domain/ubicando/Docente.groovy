package ubicando

class Docente {

    String nombre
    String apellido
    ArrayList<Curso> titularDeCursos = new ArrayList<Curso>()

    static constraints = {
    }

    void cambiarAulaDeCurso(Curso curso, Aula aulaNueva){
        if (titularDeCursos.contains(curso) && aulaNueva.puedeAgregarCurso(curso)){
            curso.getAulaActual().getCursos().remove(curso)
            aulaNueva.agregarCurso(curso)
        }
    }
}
