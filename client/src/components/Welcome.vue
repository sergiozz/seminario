<template>
  <div>

    <nav class="navbar navbar-expand-lg navbar-dark navbar-static-top" role="navigation">
      <a class="navbar-brand" href="/#"><img src="../assets/images/grails.svg" alt="Grails Logo"/></a>
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
              <li v-if="serverInfo" class="dropdown-item"><a href="#/maps">Ver mapas</a></li>
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

          </li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="true">About<span class="caret"></span></a>
          </li>

        </ul>
      </div>
    </nav>

    <div id="content" role="main">
      <section class="row colset-2-its">
        <h1>Ubicando</h1> 
        
        <a  href="#/maps"> <h3> Ver mapas</h3></a>

        <router-view />
       
        <div id="controllers" role="navigation"> <br> <br>
          <h4>Available Controllers from server:</h4>
          <ul v-if="serverInfo && serverInfo.controllers">
            <li v-for="controller in serverInfo.controllers" :key="controller.name">
              <a :href="serverURL + '/' + controller.logicalPropertyName">{{controller.name }}</a></li>
          </ul>
        </div>

      </section>
    </div>

    <div class="footer row" role="contentinfo">
    </div>

  </div>
</template>

<script>
export default {
  name: 'welcome',
  data () {
    return {
      msg: 'Welcome to Your Grails & Vue.js App',
      serverInfo: null,
      showLinks: false,
      serverURL: process.env.VUE_APP_SERVER_URL
    }
  },
  created () {
    fetch(`${this.serverURL}/application`)
      .then(response => response.json())
      .then(json => (this.serverInfo = json))
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
  
  .footer {
    font-size: 0.9em;
  }
</style>
