package ubicando

class AlumnoController {

    static scaffold = Alumno


	def alumnoService

/*     def save(Alumno alumno) {
        alumnoService.save(alumno)
    } */

    def getAll(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond alumnoService.list(params), model:[alumnoCount: alumnoService.count()]
    }

    def suscribirCurso(Integer idAlumno, Integer idCurso) {
        respond alumnoService.suscribirCurso(idAlumno, idCurso)
        //if (respuesta != "OK") respond [[ status: 400, mensaje: respuesta]]
        //respond {[ status: 200, mensaje:"OK"]} 
       
    }

    def puntuarCurso(Integer idAlumno, Integer idCurso, Integer calificacion, String comentario){
        respond alumnoService.puntuarCurso(idAlumno, idCurso)
    }

/*     def index() {
        def Alumno persona1 = new Alumno(nombre: "someone", apellido: "stuck")
        [alumno:persona1]
    } 

    def acciones() {        
        def materias = alumnoService.getCurrent()
        respond materias
    }


    def getMaterias(Integer alumnoId) {
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
