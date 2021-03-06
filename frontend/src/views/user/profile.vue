<template>
  <div class="profile">
    <section class="profile"></section>
    <div class="container">
      <h2 class="myH2">안녕하세요?</h2>
      <p class="myP">제가 누구냐면요!</p>
      <p class="myP">

        그게 좀 비밀이긴 한데...
      </p>
      <p class="myP">I HOPE YOU FIND THIS USEFULL</p>
      <p class="myP">Albi</p>
      <p class="myP">
        이거 다 만들면 알려드릴게요!
      </p>
    </div>
  </div>
</template>
<script>
// import axios from "axios";

export default {
  methods: {},

  components: {},
};
</script>
<style scoped>
.profile {
  height: 200px;
}

.myH2 {
  text-align: center;
  font-size: 4rem;
}
.myP {
  text-align: justify;
  padding-left: 15%;
  padding-right: 15%;
  font-size: 20px;
}
@media all and (max-width: 700px) {
  .myP {
    padding: 2%;
  }
}
</style>
