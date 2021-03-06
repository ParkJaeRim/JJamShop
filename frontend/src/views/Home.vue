<template>
  <div class="home">
    <section class="home"></section>
    <div class="container">
      <h2 class="myH2">What is this ?</h2>
      <p class="myP">This is a responsive fixed navbar animated on scroll</p>
      <p class="myP">
        I took inspiration from ABDO STEIF (<a
          href="https://codepen.io/abdosteif/pen/bRoyMb?editors=1100"
          >https://codepen.io/abdosteif/pen/bRoyMb?editors=1100</a
        >) and Dicson
        <a href="https://codepen.io/dicson/pen/waKPgQ"
          >(https://codepen.io/dicson/pen/waKPgQ)</a
        >
      </p>
      <p class="myP">I HOPE YOU FIND THIS USEFULL</p>
      <p class="myP">Albi</p>
      <p class="myP">
        Lorem, ipsum dolor sit amet consectetur adipisicing elit. Ipsum ratione
        facere animi impedit rem labore sint repellendus ipsa sapiente
        voluptatem aut excepturi quo itaque, ab earum cumque. Voluptatem beatae
        id inventore quod voluptate qui deserunt, quis placeat, tempora ex
        totam, dolore sequi harum eos voluptatibus animi labore officiis minus
        laboriosam Lorem, ipsum dolor sit amet consectetur adipisicing elit.
        Ipsum ratione facere animi impedit rem labore sint repellendus ipsa
        sapiente voluptatem aut excepturi quo itaque, ab earum cumque.
        Voluptatem beatae id inventore quod voluptate qui deserunt, quis
        placeat, tempora ex totam, dolore sequi harum eos voluptatibus animi
        labore officiis minus laboriosam Lorem, ipsum dolor sit amet consectetur
        adipisicing elit. Ipsum ratione facere animi impedit rem labore sint
        repellendus ipsa sapiente voluptatem aut excepturi quo itaque, ab earum
        cumque. Voluptatem beatae id inventore quod voluptate qui deserunt, quis
        placeat, tempora ex totam, dolore sequi harum eos voluptatibus animi
        labore officiis minus laboriosam Lorem, ipsum dolor sit amet consectetur
        adipisicing elit. Ipsum ratione facere animi impedit rem labore sint
        repellendus ipsa sapiente voluptatem aut excepturi quo itaque, ab earum
        cumque. Voluptatem beatae id inventore quod voluptate qui deserunt, quis
        placeat, tempora ex totam, dolore sequi harum eos voluptatibus animi
        labore officiis minus laboriosam Lorem, ipsum dolor sit amet consectetur
        adipisicing elit. Ipsum ratione facere animi impedit rem labore sint
        repellendus ipsa sapiente voluptatem aut excepturi quo itaque, ab earum
        cumque. Voluptatem beatae id inventore quod voluptate qui deserunt, quis
        placeat, tempora ex totam, dolore sequi harum eos voluptatibus animi
        labore officiis minus laboriosam
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",

  data() {
    return {};
  },
  components: {},
};
</script>

<style scoped>
.home {
  width: 100%;
  height: 100vh;
  background-image: url(../assets/nice1.jpg);
  background-position: center top;
  background-size: cover;
}

.container{
  margin-top:50px;
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