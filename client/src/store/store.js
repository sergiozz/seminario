import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export let store = new Vuex.Store({
  state: {
    seleccionRol: 0
  }
});