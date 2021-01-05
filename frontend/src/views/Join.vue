<template>
  <div class="about">
    <div v-if="{id} !=null"></div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  created() {
  },
  data() {
    return {
    };
  },
  methods: {
    go() {
      axios.get("http://localhost:8080/hi").then(res => {
        console.log(res);
        window.open(res.data, "_self");
      });
    },

  }
};
</script>
