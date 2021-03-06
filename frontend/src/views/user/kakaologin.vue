<template>
  <div class="klogin">
    <section class="klogin"></section>

    <div class="container d-flex justify-content-center align-items-center">
      <div class="card">
        <div class="row">
          <div class="col-md-6">
            <b-form
              class="form"
              @submit="onSubmit"
              @reset="onReset"
              v-if="show"
            >
              <h2>회원가입</h2>

              <div class="inputbox mt-3">
                <span>이름</span>
                <input
                  type="text"
                  placeholder="실명을 입력해주세요"
                  class="form-control"
                  v-model="form.uname"
                  required
                />
              </div>
              <div class="inputbox mt-3">
                <span>이메일</span>
                <input
                  type="text"
                  v-text="form.email"
                  v-model="form.email"
                  class="form-control"
                  required
                  disabled
                />
              </div>
              <div class="inputbox mt-3">
                <span>핸드폰 번호</span>
                <input
                  type="text"
                  placeholder="010-0000-0000"
                  name=""
                  class="form-control"
                />
              </div>
              <div class="d-flex justify-content-between align-items-center">
                <div class="text-right">
                  <button
                    type="submit"
                    class="btn btn-success register btn-block"
                  >
                    Register
                  </button>
                </div>
                <div class="text-right">
                  <button type="reset" class="btn btn-success reset btn-block">
                    Reset
                  </button>
                </div>
              </div>
            </b-form>
          </div>
          <div class="col-md-6">
            <div class="text-center mt-5">
              <img src="https://i.imgur.com/98GXnDD.png" width="400" />
            </div>
          </div>
        </div>
      </div>
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
          location.reload();
        }
      });
    },
    isUser() {
      axios.post("http://localhost:8080/isUser", this.form).then((res) => {
        console.log(res);
        if (res.data == "Exist") {
          this.login();
        }
      });
    },
    getToken() {
      axios
        .get("http://localhost:8080/klogin?authorize_code=" + this.codes)
        .then((res) => {
          this.form.email = res.data.email;
          this.form.password = res.data.id;
          console.log(this.form);
          if (this.form.password == undefined) {
            alert("올바르지 못한 접근입니다.");

            this.$router.push("/");
          } else {
            this.isUser();
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
      this.form.uname = "";
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
  },
};
</script>
<style scoped>
body {
  background-color: #f50057;
}

.container {
  height: 80vh;
}

.card {
  width: 100%;
  padding: 30px;
}

.form {
  padding: 20px;
}

.form-control {
  height: 50px;
  background-color: #eee;
}

.form-control:focus {
  color: #495057;
  background-color: #fff;
  border-color: #f50057;
  outline: 0;
  box-shadow: none;
  background-color: #eee;
}

.inputbox {
  margin-bottom: 15px;
}

.register {
  width: 200px;
  height: 51px;
  background-color: #f50057;
  border-color: #f50057;
}
.reset {
  width: 200px;
  height: 51px;
  background-color: #828282;
  border-color: #828282;
}

.reset:hover {
  width: 200px;
  height: 51px;
  background-color: #828282;
  border-color: #828282;
}

.register:hover {
  width: 200px;
  height: 51px;
  background-color: #f50057;
  border-color: #f50057;
}

.login {
  color: #f50057;
  text-decoration: none;
}

.login:hover {
  color: #f50057;
  text-decoration: none;
}

.form-check-input:checked {
  background-color: #f50057;
  border-color: #f50057;
}

.klogin {
  height: 150px;
}
</style>
