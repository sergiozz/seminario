package ubicando

class Aula {

    String numero
    Integer Piso
    ArrayList<Curso> cursos

    static constraints = {
    }

    private boolean puedeAgregarCurso(Curso curso){
        return cursos.find({ c -> curso.superpuesto(c) })
    }

    def agregarCurso(Curso curso){
        if(puedeAgregarCurso(curso)){
            cursos.add(curso)
        }
    }
}
