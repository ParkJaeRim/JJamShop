<template>
  <div class="head" v-if="isCookie != null">
    <nav class="nav" :class="{ affix: !showNavbar }">
      <div class="container">
        <div class="logo">
          <a href="/"
            ><img src="../assets/text.png" style="width: 100px; height: 40px"
          /></a>
        </div>
        <div id="mainListDiv" class="main_list">
          <ul class="navlinks">
            <li><a href="/profile">저는요!</a></li>
            <li><a href="#">포트폴리오</a></li>
            <li><a href="#">상품</a></li>
            <li><a href="#">회원정보</a></li>
            <li><a href="#" @click="deleteCookie(isCookie)">로그아웃</a></li>
          </ul>
        </div>
        <span
          v-bind:class="{ navTrigger: isNavTrigger, active: isActive }"
          @click="handleToggle"
        >
          <i></i>
          <i></i>
          <i></i>
        </span>
      </div>
    </nav>
  </div>
  <div v-else class="head">
    <nav class="nav" :class="{ affix: !showNavbar }">
      <div class="container">
        <div class="logo">
          <a href="/"
            ><img src="../assets/text.png" style="width: 100px; height: 40px"
          /></a>
        </div>
        <div id="mainListDiv" class="main_list">
          <ul class="navlinks">
            <li><a href="#">Portfolio</a></li>
            <li><a href="#">Services</a></li>
            <li><a href="#">Contact</a></li>
            <li><a href="#" @click="login()">Login</a></li>
          </ul>
        </div>
        <span
          v-bind:class="{ navTrigger: isNavTrigger, active: isActive }"
          @click="handleToggle"
        >
          <i></i>
          <i></i>
          <i></i>
        </span>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: "Header",
  props: {},
  data() {
    return {
      lst: false,
      isCookie: null,
      isActive: false,
      isNavTrigger: true,

      showNavbar: true,
      lastScrollPosition: 0,
    };
  },

  created() {
    this.isCookie = this.getCookie("accessToken");
  },
  mounted() {
    window.addEventListener("scroll", this.onScroll);
  },
  beforeDestroy() {
    window.removeEventListener("scroll", this.onScroll);
  },
  methods: {
    login() {
      window.location.replace(
        "https://kauth.kakao.com/oauth/authorize?client_id=d8a07bcf4f4992d4a841f569e9e68f57&redirect_uri=http://localhost:3000/kakaologin&response_type=code"
      );
    },
    getCookie(name) {
      var value = document.cookie.match("(^|;) ?" + name + "=([^;]*)(;|$)");
      return value ? value[2] : null;
    },
    deleteCookie() {
      this.$cookies.remove("accessToken");
      location.reload();
    },
    handleToggle() {
      this.isActive = !this.isActive;
    },
    onScroll() {
      const currentScrollPosition =
        window.pageYOffset || document.documentElement.scrollTop;
      if (currentScrollPosition < 0) {
        return;
      }
      // Stop executing this function if the difference between
      // current scroll position and last scroll position is less than some offset
      if (Math.abs(currentScrollPosition - this.lastScrollPosition) < 20) {
        return;
      }
      this.showNavbar = currentScrollPosition < this.lastScrollPosition;
      this.lastScrollPosition = currentScrollPosition;
    },
  },
};
</script>
<style scoped>

@import "../css/header.css";

.head,
body {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-size: 12px;
}
</style>
