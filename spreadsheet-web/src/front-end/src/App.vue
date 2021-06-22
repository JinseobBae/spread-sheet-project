<template>
  <vue-excel-editor v-model="jsondata" >
    <vue-excel-column field="user"   label="User" />
    <vue-excel-column field="name"   label="Name" />
    <vue-excel-column field="phone"  label="Contact" />
    <vue-excel-column field="gender" label="Gender" />
    <vue-excel-column field="age"    label="Age" />
    <vue-excel-column field="birth"  label="Date Of Birth" />
  </vue-excel-editor>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'app',
  // components: {
  //   // HelloWorld
  // }
  data() {
    return {
      jsondata: [
        {user: 'hc', name: 'Harry Cole', phone: '1-415-2345678', gender: 'M', age: 25, birth: '1997-07-01'},
        {user: 'sm', name: 'Simon Minolta', phone: '1-123-7675682', gender: 'M', age: 20, birth: '1999-11-12'},
        {user: 'ra', name: 'Raymond Atom', phone: '1-456-9981212', gender: 'M', age: 19, birth: '2000-06-11'},
        {user: 'ag', name: 'Mary George', phone: '1-556-1245684', gender: 'F', age: 22, birth: '2002-08-01'},
        {user: 'kl', name: 'Kenny Linus', phone: '1-891-2345685', gender: 'M', age: 29, birth: '1990-09-01'}
      ]
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
