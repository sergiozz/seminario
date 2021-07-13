import Vue from "vue";
import Vuex from "vuex";
import aux from '../consts'

Vue.use(Vuex);

export let store = new Vuex.Store({
  state: {
    seleccionRol: aux.ROLSD
  }
});