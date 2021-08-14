package ubicando

public class Constants {

    def static final ROL_DOCENTE = "Rol Docente";
    def static final ROL_ALUMNO = "Rol Alumno";
    def static final ROL_NO_DOCENTE = "Rol No docente";
    def static final ROL_MIEMBRO_DEPARTAMENTO = "Rol Miembro de Departamento";
    def static final ROL_ADMIN = "Rol Administrador";

    def static final ACCIONES_ALUMNO = [
        [ value: 101, text: 'Sumarse a un curso'],
        [ value: 102, text: 'Puntuar un curso'],
        [ value: 103, text: 'Notificar sintomas'],
        [ value: 104, text: 'Consultar cursos inscriptos']
    ]
    
    def static final ACCIONES_DOCENTE = [
        [ value: 201, text: 'Anunciar Examen'],
        [ value: 202, text: 'Cambio de Aula'],
        [ value: 203, text: 'Reservar Aula'],
        [ value: 204, text: 'Armado de burbujas'],
        [ value: 205, text: 'Reservar Multiples Aulas'],
        [ value: 206, text: 'Consultar cursos inscriptos'],
        [ value: 207, text: 'Sumarse a un curso'],
        [ value: 208, text: 'Cambio de Aula']
    ]

    def static final ACCIONES_NO_DOCENTE  = [
        [ value: 301, text: 'Notificar desinfeccion de Aula'],
        [ value: 302, text: 'Puntuar un curso'],
        [ value: 303, text: 'Notificar sintomas']
    ]

    def static final ACCIONES_MIEMBRO_DEPARTAMENTO  = [
        [ value: 401, text: 'Crear un curso'],
        [ value: 402, text: 'Ver peticiones de cambio'],
        [ value: 403, text: 'Peticion para desinfeccion de Aula']
    ]

    def static final ACCIONES_ADMIN  = [
        [ value: 501, text: 'Crear un curso'],
        [ value: 502, text: 'Ver peticiones de cambio'],
        [ value: 503, text: 'Peticion para desinfeccion de Aula']
    ]

    def static final todasLasAcciones = [ 
        accionesDocente: ACCIONES_DOCENTE, 
        accionesNoDocente: ACCIONES_NO_DOCENTE, 
        accionesMiembroDeDepartamento: ACCIONES_MIEMBRO_DEPARTAMENTO, 
        accionesAdministrador: ACCIONES_ADMIN, 
        accionesAlumno: ACCIONES_ALUMNO 
    ]

}