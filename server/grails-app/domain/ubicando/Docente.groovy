package ubicando

class Docente {

    String Nombre
    String Apellido
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
