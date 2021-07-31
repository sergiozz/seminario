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

      <div class="col-5">
        <h3 v-if="$store.state.seleccionRol != consts.ROLSD">{{$store.state.seleccionRol}}</h3>

        <div v-if="$store.state.seleccionRol == consts.ROLDC">
          <b-form-select v-model="seleccionAccion" :options="actionsDoc" class="m-2 roleselecion"/>
        </div>

        <div v-if="$store.state.seleccionRol == consts.ROLAL">
          <b-form-select v-model="seleccionAccion" :options="actionsAlum" class="m-2 roleselecion"/>
        </div>


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
      { value: 0, text: '-- Seleccione Accion --'}]
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
    }
  }
 
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
