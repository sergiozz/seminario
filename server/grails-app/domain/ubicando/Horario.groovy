package ubicando

class Horario {
    Integer horaDesde
    Integer horaHasta
    String dia

    static constraints = {
        horaDesde nullable: false, min: 0, max: 23
        horaHasta nullable: false, min: 0, max: 23
    }

    int superposicion(Horario horario) {
        if (horario.dia == dia){
            if(horario.horaDesde >= horaDesde && horario.horaDesde < horaHasta){
                return 1
            }
            if(horario.horaHasta > horaDesde && horario.horaHasta <= horaHasta){
                return 1
            }
        }
        else return 0
    }

}
