<template>
  <div style="width: 100%;">

  <div style="width: 100%;">
    <div class="row">
      <div class="col-7">
        <b-carousel
          :interval="4000"
          controls
          indicators
          background="#ababab"
          img-width="1024"
          img-height="480"
          style="text-shadow: 1px 1px 2px #333;"
        >
          <b-carousel-slide img-src="/img/facu1.jpg">
            <h1>Mejora tu experiencia con la facultad</h1>
          </b-carousel-slide>

          <b-carousel-slide img-src="/img/facu2.jpg">
            <h1>Mayor cuidado y organización</h1>
          </b-carousel-slide>

          <b-carousel-slide img-src="/img/facu3.jpg">
            <h1>Información más clara y accesible</h1>
          </b-carousel-slide>

          <b-carousel-slide img-src="/img/facu4.jpg">
            <h1>Armado de burbujas sanitarias</h1>
          </b-carousel-slide>

          <!-- <b-carousel-slide img-src="/img/facu5.jpg">
            <h1>Armado de burbujas sanitarias</h1>
          </b-carousel-slide> -->
        </b-carousel>
      </div>

      <div class="col-5" v-if="$store.state.userLogin.id">
        <h3 v-if="$store.state.rolUserLogin != consts.ROL_NULL">{{$store.state.rolUserLogin + ' - ' + 
          $store.state.userLogin.nombre + ' ' + $store.state.userLogin.apellido}}</h3>

        <div v-if="$store.state.rolUserLogin == consts.ROL_DC">
          <b-form-select v-model="seleccionAccion" :options="actionsDoc" @change="changeAction" class="m-2 roleselecion"/>
        </div>

        <div v-if="$store.state.rolUserLogin == consts.ROL_AL">
          <b-form-select v-model="seleccionAccion" :options="actionsAlum" @change="changeAction" class="m-2 roleselecion"/>
        </div>        

        <br>
        <div style="background: aliceblue;">
          <!--Suscribir a cursos -->         
          <div v-if="seleccionAccion == (actionsAlum[1] && actionsAlum[1].value)">
            <h4>Materia</h4>
            <b-form-select v-model="seleccionMateria" :options="materiasList" @change="changeMateria" class="m-2 wid80 roleselecion"/>
            <h4>Curso</h4>
            <b-form-select v-model="seleccionCurso" :options="cursosList" class="m-2 wid80 roleselecion"/>
          </div>

          <!--Consulta cursos inscriptos y puntuar cursos-->
          <div v-if="(seleccionAccion == (actionsAlum[4] && actionsAlum[4].value)) || 
                     (seleccionAccion == (actionsAlum[2] && actionsAlum[2].value))">
            <div v-if="cursosInscriptosList.length != 0">
              <h5 style="padding: 10px;">               
                  <li v-for="curso in cursosInscriptosList" :key="curso.id">
                    <input v-if="(seleccionAccion == actionsAlum[2].value)" type="radio" v-model="selectedPuntuacion" :value="curso.id">
                    {{curso.codMateria}} : {{curso.descripcion}}
                  </li>          
              </h5>
              <div v-if="(seleccionAccion == actionsAlum[2].value)">
                <b-row> <b-col md="2">            
                  <select v-model="nroPuntuacion">
                    <option v-for="n in 10" :key="n" :value="n">{{ n }}</option>
                  </select>
                  </b-col>
                  <b-col md="10"> <h5>Indique puntuación</h5></b-col> 
                </b-row>       
                <b-form-textarea                  
                  v-model="textPuntuacion"
                  placeholder="Escribir aquí su opinión..."
                  size="lg"
                ></b-form-textarea>
              </div>
            </div>
            <div v-else>
              <h4 style="padding: 10px;"> Sin suscripciones registradas</h4>
            </div>
          </div>

          <!--Reservar Aula para Examen y Consulta cursos inscriptos -->         
          <div v-if="(seleccionAccion == (actionsDoc[3] && actionsDoc[3].value)) || 
                    (seleccionAccion == (actionsDoc[6] && actionsDoc[6].value))">
            <div v-if="cursosInscriptosList.length != 0">
              <h5 style="padding: 10px;">               
                  <li v-for="curso in cursosInscriptosList" :key="curso.id">
                    <input v-if="(seleccionAccion == actionsDoc[3].value)" type="radio" v-model="selectedPuntuacion" :value="curso.id">
                    {{curso.codMateria}} : {{curso.descripcion}}
                  </li>          
              </h5>
              <div v-if="(seleccionAccion == actionsDoc[3].value)">
                <b-row> <b-col md="2">            
                  <select v-model="nroPuntuacion">
                    <option v-for="n in 20" :key="n" :value="n*5">{{ n*5 }}</option>
                  </select>
                  </b-col>
                  <b-col md="10"> <h5>Indique Nro de Alumnos</h5></b-col> 
                </b-row>   
                <br>    
                <b-form-datepicker v-model="fechaExamen" class="mb-2"></b-form-datepicker>
                <b-form-timepicker v-model="horaExamen" now-button reset-button locale="es"></b-form-timepicker>
                <br>
                 <b-row> <b-col md="2">            
                  <select v-model="duracionExamen">
                    <option v-for="n in 6" :key="n" :value="n">{{ n }}</option>
                  </select>
                  </b-col>
                  <b-col md="10"> <h5>Duracion del Examen</h5></b-col> 
                </b-row>   
              </div>
            </div>
            <div v-else>
              <h4 style="padding: 10px;"> Sin suscripciones registradas</h4>
            </div>
          </div>
          
          <!--Respuestas-->
          <h5 v-if="responseMsj" style="background: aquamarine; padding: 6px;">{{responseMsj}}</h5>
          <h5 v-if="responseError" style="background: crimson; padding: 6px;">{{responseError}}</h5>
        </div>

        <br>
        <b-button @click="confirmAction" variant="primary">Confirmar Acción</b-button>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import axios from "axios";
import aux from '../consts'
/* eslint-disable no-console */
export default {
  name: 'Panel',
  data () {
    return {
      consts: aux,
      serverURL: process.env.VUE_APP_SERVER_URL,
      seleccionAccion: 0,
      actionsDoc : [
      { value: 0, text: '-- Seleccione Accion --'}],
      actionsAlum : [
      { value: 0, text: '-- Seleccione Accion --'}],

      materiasList : [
      { value: 0, text: aux.SIN_SELECCION}],
      cursosList : [
      { value: 0, text: aux.SIN_SELECCION}],
      seleccionMateria: 0,
      seleccionCurso: 0,
      responseMsj: '',
      responseError: '',
      cursosInscriptosList: [],
      selectedPuntuacion: 0,
      nroPuntuacion: 0,
      duracionExamen: 0,
      textPuntuacion: '',
      fechaExamen: '',
      horaExamen: ''
    }
  },
  created () {
    this.getAcciones();   
  },
  watch: {
    '$store.state.userLogin.id'() {
     this.seleccionAccion = 0;
    },
  },
  methods: {
    getAcciones() {     
      axios.get(`${this.serverURL}/application/acciones`).then(response => {
          console.log(response.data)
          response.data.accionesAlumno.forEach(element => {
             this.actionsAlum.push(element)            
          });
          response.data.accionesDocente.forEach(element => {
             this.actionsDoc.push(element)            
          });
        }).catch(error => {
          console.log(error);
        })
    },

    changeAction(){
      this.responseMsj= '';
      this.responseError= '';
      this.selectedPuntuacion= 0;
      this.nroPuntuacion= 0;
      this.textPuntuacion= '';
      switch (this.seleccionAccion) {
        //Alumno - Sumarse a un curso
        case this.actionsAlum[1].value:
          console.log('Sumarse a un curso');
          this.consultaCursos();
          break;
        //Alumno - Puntuar un curso
        case this.actionsAlum[2].value:
          console.log('Puntuar un curso');
          this.consultaCursosInscriptos();

          break;
        //Alumno - Consultar cursos inscriptos
        case this.actionsAlum[4].value:
          console.log('Consultar cursos inscriptos');
          this.consultaCursosInscriptos();
          break;

        //Docente - Reservar Aula para Examen
        case this.actionsDoc[3].value:
          console.log('Reservar Aula para Examen');
          this.consultaCursosTitular();
          break;

        //Docente - Consultar cursos inscriptos
        case this.actionsDoc[6].value:
          console.log('Consultar cursos inscriptos');
          this.consultaCursosTitular();
          break;
      
        default:
          break;
      }
    },

    confirmAction(){
      this.responseMsj= '';
      this.responseError= '';
       switch (this.seleccionAccion) {
        //Sumarse a un curso
        case this.actionsAlum[1].value: {
            let request = { 
              idAlumno: this.$store.state.userLogin.id, 
              idCurso: this.seleccionCurso.id
            };
            axios.post(`${this.serverURL}/alumno/suscribirCurso/`, request).then(response => {
              console.log(response.data)
            if (response.data.status == 200)
                this.responseMsj = response.data.mensaje
              else
                this.responseError = response.data.mensaje
            }).catch(error => {
              console.log(error);
            });
            break;
          }

        //Puntuar un curso
        case this.actionsAlum[2].value: {
            console.log('Puntuar un curso');
            if (this.selectedPuntuacion == 0 || this.textPuntuacion.length == 0 || this.nroPuntuacion == 0) return;
            let request = { 
              idAlumno: this.$store.state.userLogin.id, 
              calificacion: this.nroPuntuacion, 
              comentario: this.textPuntuacion, 
              idCurso: this.selectedPuntuacion
            };
            axios.post(`${this.serverURL}/alumno/puntuarCurso/`, request).then(response => {
              console.log(response.data)
            if (response.data.status == 200)
                this.responseMsj = response.data.mensaje
              else
                this.responseError = response.data.mensaje
            }).catch(error => {
              console.log(error);
            });
            break;
        }

        //Reservar Aula para Examen 
        case this.actionsDoc[3].value: {
            console.log('Reservar Aula para Examen ');
            if (this.selectedPuntuacion == 0 || this.nroPuntuacion == 0  || this.duracionExamen == 0 || 
                this.fechaExamen == '' || this.horaExamen == '') return;
            let request = { 
              idDocente: this.$store.state.userLogin.id, 
              idCurso: this.selectedPuntuacion,
              duracionExamen: this.duracionExamen, 
              nroAlumnos: this.nroPuntuacion, 
              fechaExamen: this.fechaExamen,
              horaExamen: this.horaExamen
            };
            axios.post(`${this.serverURL}/docente/solicitarAulaParaExamen/`, request).then(response => {
              console.log(response.data)
            if (response.data.status == 200)
                this.responseMsj = response.data.mensaje
              else
                this.responseError = response.data.mensaje
            }).catch(error => {
              console.log(error);
            });
            break;
        }

        //Notificar sintomas TODO
        case this.actionsAlum[3].value: {
            console.log('Notificar sintomas');
            this.responseMsj = "Gracias por dar aviso. Respeta las medidas sanitarias."         
            break;
        }
      
        default:
          break;
      }
    },

    changeMateria(){
      this.cursosList = [{ value: 0, text: aux.SIN_SELECCION}]; //.slice();
      this.seleccionCurso=0;
        axios.get(`${this.serverURL}/curso/getCursosPorCodigo/${this.seleccionMateria}`).then(response => {
          console.log(response.data)
          response.data.forEach(element => {
            let elem = {
                value: element, 
                text: element.numCurso + ' ' + element.descripcion
             }
             this.cursosList.push(elem)            
          });
        }).catch(error => {
          console.log(error);
        })
    },

    consultaCursos(){
      if (this.materiasList.length == 1){ 
       axios.get(`${this.serverURL}/application/getMaterias`).then(response => {
          console.log(response.data)
          response.data.forEach(element => {
             this.materiasList.push(element)            
          });
        }).catch(error => {
          console.log(error);
        })
      }
    },

    consultaCursosInscriptos(){
       axios.get(`${this.serverURL}/alumno/getAllCursosInscriptos/${this.$store.state.userLogin.id}`).then(response => {
          console.log(response.data)
          this.cursosInscriptosList = [];//.slice();
          response.data.forEach(element => {
             this.cursosInscriptosList.push(element)            
          });
        }).catch(error => {
          console.log(error);
        })     
    },

    consultaCursosTitular(){
       axios.get(`${this.serverURL}/docente/getAllTitularDeCursos/${this.$store.state.userLogin.id}`).then(response => {
          console.log(response.data)
          this.cursosInscriptosList = [];//.slice();
          response.data.forEach(element => {
             this.cursosInscriptosList.push(element)            
          });
        }).catch(error => {
          console.log(error);
        })     
    }

  } 
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.wid80 {
    width: 80%;
  }

</style>
