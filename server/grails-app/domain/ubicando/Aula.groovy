package ubicando

import java.time.LocalDateTime

class Aula {

    String numero
    Integer piso
    ArrayList<Curso> cursos = new ArrayList<Curso>()
    ArrayList<Examen> examenes = new ArrayList<Examen>()

    static constraints = {
    }

    boolean puedeAgregarCurso(Curso curso){
        boolean superpuesto = false
        int i = 0
        while (!superpuesto && i < cursos.size()){
            superpuesto = curso.superpuesto(cursos.get(i))
            i ++
        }
       return !superpuesto
    }

    void agregarCurso(Curso curso){
        if(puedeAgregarCurso(curso)){
            cursos.add(curso)
            curso.setAulaActual(this)
        }
    }

    boolean  puedeAgregarExamen(LocalDateTime fecha, Examen examen){
        //TODO
    }

    void agregarExamen(LocalDateTime fecha, Examen examen){
        //TODO
    }
}
