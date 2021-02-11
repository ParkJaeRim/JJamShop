<template>
  <div class="klogin">
    <h1>엥</h1>
    <br />{{ kakao_email }}
<div>
   <mdb-btn color="pink" rounded>Middle</mdb-btn>
</div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  created() {
    this.create();
  },
  mounted(){
  },

  data() {
    return {
      codes: "",
      kakao_email:"",
      kakao_id : "",
    };
  },
  methods: {
    create() {
      this.codes = this.$route.query.code;
          this.getToken();

    },
    getToken() {
      axios
        .get("http://localhost:8080/klogin?authorize_code=" + this.codes)
        .then(res => {
          this.kakao_email= res.data.email;
          this.kakao_id= res.data.id;
        });
    }
  }
};
</script>
<style scoped>




</style>