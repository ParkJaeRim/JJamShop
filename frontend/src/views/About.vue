<template>
  <div class="about">
    <h1>This is hiiiiiiiiiiii about page</h1>
    <button @click="go()">hi</button>
        <button @click="back()">back</button>

    <a
      href="https://kauth.kakao.com/oauth/authorize?client_id=d8a07bcf4f4992d4a841f569e9e68f57&redirect_uri=http://localhost:3000/kakaologin&response_type=code"
      >로그인2</a>
    <br />
  </div>
</template>

<script>
import axios from "axios";

export default {
  methods: {
    go() {
      axios
        .get(
          "https://kauth.kakao.com/oauth/authorize",{
          "client_id": "d8a07bcf4f4992d4a841f569e9e68f57",
          "redirect_uri" :"http://localhost:3000/kakaologin",
          "response_type":"code",
          'Access-Control-Allow-Origin': '*',
          // 'Content-Type': 'application/json; charset = utf-8'
        
            }         
        )
        .then(res => {
          console.log(res.location);
        });
    },
    // gos() {
    //   const params = {
    //     redirectUri: "http://localhost:3000/kakaologin"
    //   };
    //   window.Kakao.Auth.authorize(params);
    // }
  }
};
</script>
