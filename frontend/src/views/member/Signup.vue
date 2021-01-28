<template>
  <v-container class="signup" fill-height>
    <v-row class="text-center" align="center" justify="center">
      <v-col cols="12">
        <SignupForm
          v-if="page == 1"
          @toEmailVerification="setSignupData"
          :signupData2="signupData"
        />
        <SignupEmail
          v-if="page == 2"
          @toEmailVerification="emailVerification"
          @pageDown="(page = '1'), setPage(1)"
        />
        <SignupEmailVerification
          v-if="page == 3"
          @finishSignup="doSignup"
          @pageDown="(page = '2'), setPage(2)"
        />
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import Swal from "sweetalert2";

import SignupForm from "@/components/member/signup/SignupForm.vue";
import SignupEmail from "@/components/member/signup/SignupEmail.vue";
import SignupEmailVerification from "@/components/member/signup/SignupEmailVerification.vue";

import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";

const Form = namespace("Signup");

@Component({
  components: {
    SignupForm,
    SignupEmail,
    SignupEmailVerification,
  },
})
export default class Signup extends Vue {
  private signupData: any;
  private page = localStorage.getItem("page")
    ? localStorage.getItem("page")
    : 1;

  @Form.Action
  private signup!: (data: any) => Promise<any>;
  @Form.Action
  private saveSignupData!: (data: any) => void;
  @Form.Action
  private setPage!: (no: any) => void;

  created() {
    console.log("hi~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    this.signupData = this.$store.state.Signup.signupData;

    console.log(this.signupData);
  }

  setSignupData(signupData: any) {
    this.saveSignupData(signupData);
    this.signupData = this.$store.state.Signup.signupData;

    this.page = "2";
    this.setPage(this.page);
  }
  emailVerification(userEmailData: any) {
    this.signupData = this.$store.state.Signup.signupData;
    this.signupData.uemail = userEmailData.userEmail;
    this.saveSignupData(this.signupData);
    this.page = "3";
    this.setPage(this.page);
  }
  doSignup() {
    this.signup(this.signupData);
    Swal.fire({
      title: "가입되었습니다!",
      text: `${this.signupData.uname} 님\n허니콤보에 오신 것을 환영합니다.`,
      background: "#fff url(/images/trees.png)",
      backdrop: `
    rgba(0,0,123,0.4)
    url("/images/nyan-cat.gif")
    left top
    no-repeat
  `,
    });
    this.saveSignupData({});
    this.setPage(1);
    this.$router.push({ name: "Home" });
  }
}
</script>
