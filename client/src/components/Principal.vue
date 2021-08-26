<template>
  <div>

    <nav class="navbar navbar-expand-lg navbar-dark navbar-static-top" role="navigation">
      <a class="navbar-brand" href="/#"><img src="../assets/images/grails.svg" alt="Grails Logo"/></a>
      <a class="navbar-brand" href="/#"><img  style="width: 80px;" src="../assets/logo.png" alt="Vue Logo"/></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent"
              aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" aria-expanded="false" style="height: 0.8px;" id="navbarContent">
        <ul class="nav navbar-nav ml-auto">

          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="true">Acciones <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li v-if="serverInfo" class="dropdown-item"><a href="#">Reservar Aula</a></li>
              <li v-if="serverInfo" class="dropdown-item"><a href="#">Cambiar Aula</a></li>

              <li role="separator" class="divider dropdown-item"></li>
              <li class="dropdown-item"><a href="#/maps">Ver mapas</a></li>
            </ul>
          </li>

          <li class="dropdown"> 
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="true">Status<span class="caret"></span></a>
            <ul class="dropdown-menu" v-if="serverInfo">
              <li v-if="serverInfo.artefacts"><a href="#">Controllers: {{serverInfo.artefacts.controllers}}</a></li>
              <li v-if="serverInfo.artefacts"><a href="#">Domains: {{serverInfo.artefacts.domains}}</a></li>
              <li v-if="serverInfo.artefacts"><a href="#">Services: {{serverInfo.artefacts.services}}</a></li>
            </ul>
            <ul class="dropdown-menu" v-if="!serverInfo">
              <li><a href="#">Server Down</a></li>
            </ul>            

          </li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="true">About<span class="caret"></span></a>
          </li>

        </ul>
      </div>
    </nav>


    <div id="content" role="main">
      <section class="row colset-2-its" style=" max-width: none;">
        <h1 class="logot">Ubicando</h1> 

        <br><br>
        <b-container class="bv-example-row">
          <b-row class="loginTable">
            <b-col md="2" style="margin-top: 10px;"> <h3>Falso Login</h3></b-col>
            <b-col md="4"><b-form-select v-model="$store.state.seleccionRol" :options="consts.ROLES" @change="changeRol" class="m-2 roleselecion"/></b-col>
            <b-col md="4"><b-form-select v-model="$store.state.seleccionUser" :options="users" class="m-2 roleselecion"/></b-col>
            <b-col md="2" style="margin-top: 10px;"><b-button  @click="confirmLogin" variant="secondary">Confirmar</b-button></b-col>
          </b-row>
          <b-row>            
            <b-col md="2"><a href="#/maps" style="margin: 12px;"> <h3 class="buttonActions"> Ver mapas</h3></a></b-col>
            <b-col md="2"><a href="#/" style="margin: 12px;"> <h3 class="buttonActions"> Acciones</h3></a></b-col>
            <b-col md="8"></b-col>
          </b-row>
        </b-container>

        <router-view />

      </section>
    </div>


    <div class="footer row" role="contentinfo" style="padding-top: 200px;">
    </div>

  </div>
</template>

<script>
import aux from '../consts'
import axios from "axios"
/* eslint-disable no-console */
export default {
  name: 'principal',
  data () {
    return {
      serverInfo: null,
      serverURL: process.env.VUE_APP_SERVER_URL,
      users: aux.USERS,
      consts: aux
    }
  },
  created () {
    fetch(`${this.serverURL}/application`)
      .then(response => response.json())
      .then(json => (this.serverInfo = json))    
  },
  methods: {
    changeRol() {
      this.users = this.consts.USERS.slice();
      this.$store.state.seleccionUser = this.consts.SIN_SELECCION;

      if (this.$store.state.seleccionRol == this.consts.ROL_AL)
        axios.get(`${this.serverURL}/alumno/getAll`).then(response => {
            response.data.forEach(element => {
             let elem = {
                value: element, 
                text: element.nombre + ' ' + element.apellido
             }
             this.users.push(elem)            
            });
          }).catch(error => {
            console.log(error);
          })

      if (this.$store.state.seleccionRol == this.consts.ROL_DC)
        axios.get(`${this.serverURL}/docente/getAll`).then(response => {
            response.data.forEach(element => {
             let elem = {
                value: element, 
                text: element.nombre + ' ' + element.apellido
             }
             this.users.push(elem)            
            });
          }).catch(error => {
            console.log(error);
          })
    },
    confirmLogin() {
      if (this.$store.state.seleccionUser != this.consts.SIN_SELECCION){
        this.$store.state.userLogin = this.$store.state.seleccionUser;
        this.$store.state.rolUserLogin = this.$store.state.seleccionRol;
        console.log("new login")
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

  .navbar-nav li button {
    background: transparent;
    border: none;
    line-height: 20px;
    font-size: 18px;
    color: white;
    padding: 25px 15px;
  }

  .btn-primary:active, .btn-primary.active, .open > .dropdown-toggle.btn-primary {
    background-color: #e7e7e7;
  }

  button span.caret {
    margin-left: 2px;
  }

  .plus-logo {
    font-size: 10rem;
    margin-left: -100px;
    margin-right: -13px;
  }

  .hero-logo {
    width: 161px;
    margin-right: -161px;
    margin-bottom: 88px;
  }

  .logot { 
    color: #d54d7b; 
    font-family: "Great Vibes", cursive; 
    font-size: 56px !important;
    text-shadow: 0 1px 1px #fff; 
  }

  .loginTable{
    border: dashed;
    background-color: aliceblue;
  }

  .roleselecion {
    font-size: 18px;
    color: blue;
    border-radius: 2.25rem;
    background: #e7e7e7;
  }

  .buttonActions {
    border-style: solid;
    text-align: center;
  }
  
  .footer {
    max-width: none;
    font-size: 0.9em;
  }
</style>
