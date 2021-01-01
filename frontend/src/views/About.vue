<template>
  <div class="about">
    <h1>This is hiiiiiiiiiiii about page</h1>
    <button v-on:click="go">hiiiiiiiiiiii</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  methods: {
    go() {
      axios.get("http://localhost:8080/").then((res) => {
        console.log(res);
      });
    },
  },
};
</script>
