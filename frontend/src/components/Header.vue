<template>
  <div v-if="isCookie != null">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
      <router-link to="/profile">Profile</router-link>
    </div>  
  </div>
  <div v-else>
    <div>
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  props: {},
  data() {
    return {
      isCookie: null,
    };
  },

  created() {
    this.isCookie = this.getCookie("accessToken");
    console.log(this.isCookie);
  },

  methods: {
    getCookie(name) {
      var value = document.cookie.match("(^|;) ?" + name + "=([^;]*)(;|$)");
      return value ? value[2] : null;
    },
  },
};
</script>

<style scoped>
#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>