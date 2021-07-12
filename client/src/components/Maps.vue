<template>
  <div style="width: 100%;">
      <div v-for="(fac, index) in mapas" :key="index">
      <div v-for="(sed, index2) in fac.sedes" :key="index2"> 
        <br>
        <h4  class="bg-dark tag-foto">{{sed.nombre}}</h4> 
        <h4 v-if="sed.pisos.length === 0" class="bg-dark tag-foto">Sin cargar</h4>
      <div v-for="(piso, index3) in sed.pisos" :key="index3">
      <b-container v-if="index3 == 0 || index3 % 2 == 0" fluid class="p-4 bg-dark tag-foto">
          <b-row>
            <b-col>
              <div style="width:500px"> {{sed.pisos[index3].nombre}}
                <zoom-on-hover :img-normal="'/img/'+ sed.pisos[index3].imagen" :scale="1.5"></zoom-on-hover>
                </div>
            </b-col>
            <b-col v-if="index3+1 < sed.pisos.length">
              <div style="width:500px"> {{sed.pisos[index3+1].nombre}}
                <zoom-on-hover :img-normal="'/img/'+ sed.pisos[index3+1].imagen" :scale="1.5"></zoom-on-hover>
                </div>
            </b-col>
          </b-row>
      </b-container>
      

      </div></div></div>

  </div>
</template>

<script>
export default {
  name: 'Maps',
  data () {
    return {
      mapas: []
    }
  },
  created () {
    this.mapas = [{             
      universidad: "fiuba",
      sedes:  [{
        nombre: "paseo colon",
        pisos: [{
          nombre: "planta baja",
          imagen: "pc-planta_baja.jpg "
        },
        {
          nombre: "primer subsuelo",
          imagen: "pc-subsuelo.jpg "
        },
        {
          nombre: "segundo subsuelo",
          imagen: "pc-subsuelo_2.jpg "
        },
        {
          nombre: "primer piso",
          imagen: "pc-primer_piso.jpg "
        },
        {
          nombre: "segundo piso",
          imagen: "pc-segundo_piso.jpg "
        },
        {
          nombre: "tercer piso",
          imagen: "pc-tercer_piso.jpg "
        },
        {
          nombre: "cuarto piso",
          imagen: "pc-cuarto_piso.jpg "
        },
        {
          nombre: "quito piso",
          imagen: "pc-quinto_piso.jpg "
        }]
      },
      {
        nombre: "las heras",
        pisos: []
      }]
    }];

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .tag-foto {
    text-align: center;
    color: greenyellow;
    font-size: 20px;
  }
</style>
