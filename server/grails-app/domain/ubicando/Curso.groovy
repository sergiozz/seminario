package ubicando

class Curso {

    ArrayList<Horario> horarios

    static constraints = {
    }

    //TODO logica de superposicion de horarios
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



        /*
        int superposiciones = 0
        horarios.each {
            Horario h1 -> otroCurso.horarios.each {
                Horario h2 -> superposiciones += h1.superposicion(h2)
            }
        }
        return (superposiciones > 0)
         */
    }
}
