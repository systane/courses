<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <!--we can use the extended form: v-on:click="incrementYear" instead of @click=incrementYear -->
    <button @click=incrementYear>
      me clica
    </button>
    <HelloWorld :msg=msg :number=year />
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  },
  data: () => ({
    year: 2020,
    msg: "Welcome to Your Vue.js App"
  }),
  methods: {
    incrementYear() {
      this.year++;
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
