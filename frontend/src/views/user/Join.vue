<template>
  <div class="about">
    <h1>{{signUpData.kakao_email}}{{signUpData.kakao_id}}</h1></div>
</template>

<script>
import axios from "axios";

export default {
  created() {
    this.signUpData.kakao_email = this.$route.query.kakao_email;
    this.signUpData.kakao_id = this.$route.query.kakao_id;
    console.log(this.signUpData.kakao_email);
  },
  data() {
    return {
      signUpData : {
        kakao_email:"",
        kakao_id: "",
      },
    };
  },
  methods: {

  }
};
</script>
