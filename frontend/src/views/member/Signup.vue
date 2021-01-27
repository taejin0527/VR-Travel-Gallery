<template>
  <div class="col-md-12">
    <div class="card card-container">
      <img
        id="profile-img"
        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
        class="profile-img-card"
      />
      <form name="form" @submit.prevent="handleRegister">
        <div v-if="!successful">
          <div class="form-group">
            <label for="username">Username</label>
            <input
              v-model="user.username"
              v-validate="'required|min:3|max:20'"
              type="text"
              class="form-control"
              name="username"
            />
            <div
              v-if="submitted && errors.has('username')"
              class="alert-danger"
            >
              {{ errors.first("username") }}
            </div>
          </div>
          <div class="form-group">
            <label for="email">Email</label>
            <input
              v-model="user.email"
              v-validate="'required|email|max:50'"
              type="email"
              class="form-control"
              name="email"
            />
            <div v-if="submitted && errors.has('email')" class="alert-danger">
              {{ errors.first("email") }}
            </div>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input
              v-model="user.password"
              v-validate="'required|min:6|max:40'"
              type="password"
              class="form-control"
              name="password"
            />
            <div
              v-if="submitted && errors.has('password')"
              class="alert-danger"
            >
              {{ errors.first("password") }}
            </div>
          </div>
          <div class="form-group">
            <button class="btn btn-primary btn-block">Sign Up</button>
          </div>
        </div>
      </form>

      <div
        v-if="message"
        class="alert"
        :class="successful ? 'alert-success' : 'alert-danger'"
      >
        {{ message }}
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
const Auth = namespace("Auth");

@Component
export default class Register extends Vue {
  private user: any = { username: "", email: "", password: "" };

  private submitted = false;
  private successful = false;
  private message = "";

  @Auth.Getter
  private isLoggedIn!: boolean;

  @Auth.Action
  private register!: (data: any) => Promise<any>;

  mounted() {
    if (this.isLoggedIn) {
      this.$router.push("/profile");
    }
  }

  handleRegister() {
    this.message = "";
    this.submitted = true;

    this.$validator.validate().then((isValid) => {
      if (isValid) {
        this.register(this.user).then(
          (data) => {
            this.message = data.message;
            this.successful = true;
          },
          (error) => {
            this.message = error;
            this.successful = false;
          }
        );
      }
    });
  }
}
</script>
