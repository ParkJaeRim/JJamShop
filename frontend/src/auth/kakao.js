const kakao = {
    clientID: "d8a07bcf4f4992d4a841f569e9e68f57",
    redirectUri: "http://localhost:3000/kakaologin"
  };

  router.get("/", (req, res) => {
    const kakaoAuthUrl = `https://kauth.kakao.com/oauth/authorize?client_id=${
      kakao.clientID
    }&redirect_uri=${kakao.redirectUri}&response_type=code`;
  
    return res.redirect(kakaoAuthUrl);
  });