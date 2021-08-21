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
          <div v-if="seleccionAccion == actionsAlum[1].value">
            <h4>Materia</h4>
            <b-form-select v-model="seleccionMateria" :options="materiasList" @change="changeMateria" class="m-2 wid80 roleselecion"/>
            <h4>Curso</h4>
            <b-form-select v-model="seleccionCurso" :options="cursosList" class="m-2 wid80 roleselecion"/>
          </div>
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
    }
  },
  created () {
    this.getAcciones();   
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
      switch (this.seleccionAccion) {
        //Sumarse a un curso
        case this.actionsAlum[1].value:
          console.log('Sumarse a un curso');
          this.consultaCursos();
          break;
      
        default:
          break;
      }
    },

    changeMateria(){
      this.cursosList = [{ value: 0, text: aux.SIN_SELECCION}]; //.slice();
        axios.get(`${this.serverURL}/curso/getCursosPorCodigo/${this.seleccionMateria}`).then(response => {
          console.log(response.data)
          response.data.forEach(element => {
            //let elemJson = JSON.stringify(element)
            //console.log(elemJson.docenteTitular)
            let elem = {
                value: element, 
                text: 'Curso ' + element.numCurso //+ element.docenteTitular.id
             }
             this.cursosList.push(elem)            
          });
        }).catch(error => {
          console.log(error);
        })
    },

    confirmAction(){
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
