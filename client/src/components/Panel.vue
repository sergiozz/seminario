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
import aux from '../consts'
export default {
  name: 'Panel',
  data () {
    return {
      consts: aux,
      seleccionAccion: 0,
      actionsDoc : [
      { 
          value: 0, 
          text: '-- Seleccione Accion --' 
      },
      {
          value: 1, 
          text: "Anunciar Examen"
      },
      {
          value: 2, 
          text: "Cambio de Aula"
      },
      {
          value: 3, 
          text: "Reservar Aula"
      },
      {
          value: 4, 
          text: "Armado de burbujas"
      }],
      actionsAlum : [
      { 
          value: 0, 
          text: '-- Seleccione Accion --' 
      },
      {
          value: 1, 
          text: "Sumarse a un curso"
      },
      {
          value: 2, 
          text: "Puntuar un curso"
      },
      {
          value: 3, 
          text: "Notificar sintomas"
      }]
    }
  },
  created () {
   
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
