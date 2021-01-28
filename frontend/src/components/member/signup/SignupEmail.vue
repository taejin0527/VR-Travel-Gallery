<template>
  <v-card class="mx-auto" flat max-width="350">
    <v-text-field
      v-model="email"
      :messages="error.email"
      label="E-mail."
      @input="emailChecked = false"
      outlined
      solo
      required
      autofocus
      color="#424242"
      autocomplete="off"
    >
      <v-icon
        slot="append"
        :color="emailChecked ? '' : '#ea907a'"
        @click="emailCheck2(email)"
        >mdi-check</v-icon
      >
    </v-text-field>

    <div>
      <v-btn color="grey" class="white--text" @click="$emit('pageDown')"
        >뒤로가기</v-btn
      >
      <v-divider class="mr-5" vertical></v-divider>
      <v-btn
        :disabled="!emailChecked"
        @click="emailVerification(email)"
        color="#ea907a"
        class="white--text"
        >인증번호 받기</v-btn
      >
      <v-overlay :value="overlay">
        <v-progress-circular indeterminate size="64"></v-progress-circular>
      </v-overlay>
    </div>
  </v-card>
</template>

<script lang="ts">
import swal from "sweetalert2";

import { Component, Watch, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";

const Signup = namespace("Signup");

@Component
export default class SignupEmail extends Vue {
  email = "";
  error = {
    email: "",
  };
  isSubmit = false;
  component = this;
  emailChecked = false;
  overlay = false;

  created() {
    this.component = this;
  }
  @Watch("email")
  onEmailChanged() {
    this.checkForm();
  }

  @Signup.Action
  private getConfirmCode!: (data: any) => Promise<any>;

  @Signup.Action
  private emailCheck!: (data: any) => Promise<any>;

  checkForm() {
    if (this.email.length >= 0 && !this.$validator.validate(this.email)) {
      this.error.email = "이메일 형식이 아닙니다.";
    } else if (!this.emailChecked) {
      this.error.email = "오른쪽 체크를 눌러서 이메일 중복 확인해주세요.";
    }

    let isSubmit = true;

    Object.values(this.error).map((v) => {
      if (v) isSubmit = false;
    });
    this.isSubmit = isSubmit;
  }
  emailVerification(email: string) {
    // email 보내기 + 받아서
    const signupEmailComponent = this;
    signupEmailComponent.overlay = !signupEmailComponent.overlay;
    this.getConfirmCode(email).then((code) => {
      if (code.status === 200) {
        signupEmailComponent.overlay = !signupEmailComponent.overlay;
        swal.fire("인증번호가 발송되었습니다.");
        this.$emit("toEmailVerification", {
          confirmCode: code,
          userEmail: email,
        });
      } else {
        signupEmailComponent.overlay = !signupEmailComponent.overlay;
        swal.fire({
          text: "인증번호 발송에 실패하였습니다.",
          icon: "error",
        });
      }
    });
  }
  emailCheck2(email: string) {
    this.emailCheck(email).then((res) => {
      if (res === true) {
        swal.fire("사용 가능한 이메일입니다.");
        this.emailChecked = true;
      } else {
        swal.fire({
          icon: "error",
          text: "이미 사용 중인 이메일입니다.",
        });
        this.emailChecked = false;
      }
      if (this.emailChecked) {
        this.error.email = "";
      } else {
        this.error.email = "오른쪽 체크를 눌러서 이메일 중복 확인해주세요.";
      }
    });
  }
}
</script>
