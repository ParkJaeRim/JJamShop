<template>
  <div class="about">
    <h1>This is hiiiiiiiiiiii about page</h1>
    <button v-on:click="go">카카오 로그인</button>
    <br />
  </div>
</template>

<script>
import axios from "axios";

export default {
  methods: {
    go() {
      axios.get("http://localhost:8080/hi").then(res => {
        console.log(res);
        window.open(res.data)
        
      });
    }
  }
};
</script>
