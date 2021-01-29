<template>
  <v-card class="mx-auto" flat max-width="350">
    <v-text-field
      label="아이디"
      name="signup"
      prepend-icon="mdi-account"
      type="text"
      v-model="signupData.uid"
      color="#424242"
      :messages="idcheck ? '' : '오른쪽의 체크를 눌러 중복확인해주세요'"
      @input="idcheck = true"
      autofocus
      autocapitalize="off"
      autocorrect="off"
      autocomplete="off"
    >
      <v-icon
        slot="append"
        :color="idcheck ? '' : '#ea907a'"
        @click="idCheck2(signupData.uid)"
        >mdi-check</v-icon
      >
    </v-text-field>

    <v-text-field
      :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
      @click:append="show1 = !show1"
      :type="show1 ? 'text' : 'password'"
      label="비밀번호"
      name="password"
      prepend-icon="mdi-lock-outline"
      v-model="signupData.upw"
      color="#424242"
      :messages="
        pwdCheck(signupData.upw)
          ? ''
          : '비밀번호는 영문과 숫자를 섞어서 8자 이상 되어야 합니다'
      "
      autocomplete="off"
    ></v-text-field>

    <v-text-field
      :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
      @click:append="show2 = !show2"
      :type="show2 ? 'text' : 'password'"
      label="비밀번호 확인"
      name="password confirm"
      prepend-icon="mdi-lock-outline"
      v-model="signupData.upw2"
      color="#424242"
      :messages="
        pwdCheck2(signupData.upw, signupData.upw2)
          ? ''
          : '비밀번호와 동일하게 입력해주세요'
      "
      autocomplete="off"
    ></v-text-field>

    <!-- 영문, 숫자 혼용 확인 필요 -->
    <div>
      <v-btn
        color="grey"
        class="white--text"
        @click="$router.push({ name: 'Login' }), saveSignupData({})"
        >뒤로가기</v-btn
      >
      <v-divider class="mr-5" vertical></v-divider>
      <v-btn
        :disabled="
          !signupData.uid ||
            !signupData.upw ||
            !signupData.upw2 ||
            !idcheck ||
            !pwdCheck(signupData.upw) ||
            !pwdCheck2(signupData.upw, signupData.upw2)
        "
        @click="toEmailVerification()"
        color="#ea907a"
        class="white--text"
        >다음으로</v-btn
      >
    </div>
  </v-card>
</template>

<script>
import swal from "sweetalert2";
import { mapActions } from "vuex";

export default {
  name: "SignupForm",
  props: {
    signupData2: Object,
  },
  mounted() {
    console.log("signupform mounted!");
    if (this.signupData2 != {}) {
      this.signupData = this.signupData2;
    }
  },
  data: function() {
    return {
      signupData: {
        uid: "",
        uname: "",
        upw: "",
        upw2: "",
        uemail: "",
      },
      show1: false,
      show2: false,
      idcheck: true,
    };
  },
  methods: {
    ...mapActions("Signup", ["idCheck", "saveSignupData"]),
    toEmailVerification() {
      for (const [key, value] of Object.entries(this.signupData)) {
        if (key === "uemail") continue;
        if (value === "") {
          const key2 = this.signupData2[key];
          swal.fire({
            icon: "error",
            text: `${key2} 확인해주세요.`,
          });
          return;
        }
      }
      // 다른 방식 고려
      this.signupData.upw2 = "";
      this.$emit("toEmailVerification", this.signupData);
    },
    pwdCheck(upw) {
      const pattern1 = /[0-9]/;
      const pattern2 = /[A-Za-z]/;
      //특수문자 확인
      if (pattern1.test(upw) == false) {
        return false;
      }
      if (pattern2.test(upw) == false) {
        return false;
      }
      // if(pattern3.test(pwd) == false){
      //     return false;
      // }
      if (upw.length < 8) return false;
      return true;
    },
    pwdCheck2(upw, upw2) {
      if (!this.pwdCheck(upw2)) return false;
      if (upw !== upw2) return false;
      return true;
    },
    idCheck2(uid) {
      this.idCheck(uid).then((res) =>
        res ? (this.idcheck = true) : (this.idcheck = false)
      );
    },
  },
};
</script>
