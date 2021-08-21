import Vue from "vue";
import Vuex from "vuex";
import aux from '../consts'

Vue.use(Vuex);

export let store = new Vuex.Store({
  state: {
    seleccionRol: aux.ROL_NULL,
    seleccionUser: aux.SIN_SELECCION,
    userLogin: {},
    rolUserLogin: aux.ROL_NULL
  }
});