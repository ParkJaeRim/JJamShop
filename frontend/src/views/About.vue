<template>
  <div class="about">
    <h1>This is hiiiiiii about page</h1>
    <img src="../assets/kakao_login_btn.png" @click="login()" />
    <br />
  </div>
</template>
<script>
// import axios from "axios";

export default {
  methods: {
    login() {
      window.location.replace(
        "https://kauth.kakao.com/oauth/authorize?client_id=d8a07bcf4f4992d4a841f569e9e68f57&redirect_uri=http://localhost:3000/kakaologin&response_type=code"
      );
    },
  },
};
</script>
