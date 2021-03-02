<template>
  <div class="klogin">
    <h1>엥</h1>
    <div>
      <b-form @submit="onSubmit" @reset="onReset" v-if="show">
        <b-form-group
          id="input-group-1"
          label="Email address:"
          label-for="input-1"
          description="We'll never share your email with anyone else."
        >
          <b-form-input
            id="input-1"
            v-model="form.email"
            type="email"
            v-text="form.email"
            required
            disabled
          >
          </b-form-input>
        </b-form-group>

        <b-form-group id="input-group-2" label="Your Name:" label-for="input-2">
          <b-form-input
            id="input-2"
            v-model="form.uname"
            placeholder="Enter name"
            required
          ></b-form-input>
        </b-form-group>

        <b-button type="submit" variant="primary">Submit</b-button>
        <b-button type="reset" variant="danger">Reset</b-button>
      </b-form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  created() {
    this.create();
  },
  data() {
    return {
      codes: "",
      form: {
        password: "",
        email: "",
        uname: "",
      },
      show: true,
    };
  },
  methods: {
    create() {
      this.codes = this.$route.query.code;
      this.getToken();
    },
    login() {
      axios.post("http://localhost:8080/login", this.form).then((res) => {
        if (res.data != null) {
          document.cookie = `accessToken=${res.data}`;
          axios.defaults.headers.common["x-access-token"] = res.data;
          this.$router.push("/");
        }
      });
    },
    getToken() {
      axios
        .get("http://localhost:8080/klogin?authorize_code=" + this.codes)
        .then((res) => {
          this.form.email = res.data.email;
          this.form.password = res.data.id;
          if (this.form.password == undefined) {
            alert("올바르지 못한 접근입니다.");
            this.$router.push("/");
          } else {
            this.login();
          }
        });
    },
    onSubmit(event) {
      event.preventDefault();
      // alert(JSON.stringify(this.form));
      axios.post("http://localhost:8080/join", this.form).then((res) => {
        console.log(res.status);
        this.login();
      });
    },
    onReset(event) {
      event.preventDefault();
      this.form.email = "";
      this.form.uname = "";
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
  },
};
</script>
<style scoped></style>
