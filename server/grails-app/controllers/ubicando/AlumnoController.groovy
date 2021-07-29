package ubicando

class AlumnoController {

    static scaffold = Alumno


	def alumnoService

/*     def index() {
        def Alumno persona1 = new Alumno(nombre: "someone", apellido: "stuck")
        [alumno:persona1]
    } */

    def acciones() {        
        def materias = alumnoService.getCurrent()
        respond (status: 200, data: materias)
    }




/*     def getMaterias(Integer alumnoId) {
        def alumno = Alumno.findById(alumnoId)
        def materias = alumnosService.obtenerMateriasCursadas(alumnoId)
        return [alumno: alumno, materias: materias]
    }

    def seleccionarAlumno(Integer alumnoId, String codigoMateria) {
        def alumno = Alumno.findById(alumnoId)
        def alumnos = alumnosService.obtenerAlumnos(alumnoId, codigoMateria)
        render([alumnos: alumnos, materias: materias])
    }

    def votar() {
        println(params)
        redirect(action: "listado", controller: "intencionesDeCursada", params: [alumnoId: params["alumnoId"]])
    }

	def votacionAlumno() {
		println(params)
		def alumnoVotadoId = Long.valueOf(params.alumnoVotadoId)
		def puntuacion = Integer.valueOf(params.puntuacion)
		def alumnoVotado = Alumno.findById(alumnoVotadoId)
		alumnoVotado.puntuar(puntuacion)
		alumnoVotado.save()
		[
				alumnoVotado: alumnoVotado
		]
	} */

}
