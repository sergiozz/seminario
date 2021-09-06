package ubicando

class UrlMappings {

    static mappings = {
        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")
        "/"(controller: 'application', action:'index')

        "/curso/getCursosPorCodigo/${codMateria}"(controller: 'curso', action:'getCursosPorCodigo')
        "/alumno/getAllCursosInscriptos/${idAlumno}"(controller: 'alumno', action:'getAllCursosInscriptos')
        post "/alumno/suscribirCurso/"(controller: 'alumno', action:'suscribirCurso')
        post "/alumno/puntuarCurso/"(controller: 'alumno', action:'puntuarCurso')

        "/alumno/getAll"(controller: 'alumno', action:'getAll')
        "/docente/getAll"(controller: 'docente', action:'getAll')
        "/application/acciones"(controller: 'application', action:'getAllActions')
        "/application/getMaterias"(controller: 'application', action:'getAllMaterias')

        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
