package ubicando

class Horario {

    Integer horaDesde
    Integer horaHasta
    String dia

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
