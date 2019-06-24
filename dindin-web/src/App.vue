<template>
  <div id="app" style="background-color: rgba(235, 235, 235, 0.08)">
    <router-view v-if="isRouterAlive"/>
  </div>
</template>

<script>

export default {
  name: 'app',
   mounted () {
    window.addEventListener('unload', this.saveState)
  },
  provide (){
     return {
       reload:this.reload
     }
  },
  data(){
    return{
      isRouterAlive:true,
    }
  },
  methods: {
    reload (){
       this.isRouterAlive = false
       this.$nextTick(function(){
          this.isRouterAlive = true
       })
    },
    saveState () {
      sessionStorage.setItem('state', JSON.stringify(this.$store.state))
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
