<template>
  <div class="home">
    <h1 v-if="isCookie != null">로그인 됐지렁</h1>
    <img alt="Vue logo" src="../assets/logo.png" />
    <HelloWorld msg="Welcome to Your Vue.js App" />
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from "@/components/HelloWorld.vue";

export default {
  name: "Home",

  data() {
    return {
      isCookie: null,
    };
  },
  components: {
    HelloWorld,
  },
  created() {
    this.isCookie = this.getCookie("accessToken");
  },
  
  methods: {
    getCookie(name) {
      var value = document.cookie.match("(^|;) ?" + name + "=([^;]*)(;|$)");
      return value ? value[2] : null;
    },
  },
};
</script>
