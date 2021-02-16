<template>
  <v-dialog transition="dialog-bottom-transition" max-width="600">
    <template v-slot:activator="{ on, attrs }">
      <v-btn class="ml-2 mt-5" outlined rounded small v-bind="attrs" v-on="on">
        Edit password
      </v-btn>
    </template>

    <!-- Modal 창 -->
    <template v-slot:default="dialog">
      <v-card>
        <v-toolbar color="#7597ab" dark>비밀번호 변경</v-toolbar>
        <div class="card-form" style="margin: 20px">
          <!-- 비밀번호  -->
          <div class="card-input">
            <label for="password" class="card-input__label">
              비밀번호
            </label>
            <input
              @blur="blurPassword"
              class="card-input__input"
              :value="formData.pwd"
              autocomplete="off"
            />
            <button
              class="card-input__eye"
              title="Show/Hide card number"
              tabindex="-1"
              @click="toggleMask"
            ></button>
          </div>
          <!-- 비밀번호 확인 -->
          <div class="card-input">
            <label for="password" class="card-input__label">
              비밀번호 확인
            </label>
            <input
              @blur="blurPassword"
              class="card-input__input"
              :value="formData.pwdConfirm"
              autocomplete="off"
            />
            <button
              class="card-input__eye"
              title="Show/Hide card number"
              tabindex="-1"
              @click="toggleMask"
            ></button>
          </div>
        </div>
        <v-card-actions class="justify-end">
          <v-btn
            color="#7c9986"
            @click="changeProfile(), (dialog.value = false)"
            >Submit</v-btn
          >
          <v-btn color="#af8a83" @click="dialog.value = false">Close</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
export default {
  name: "ProfileForm",
  components: {},
  data() {
    return {
      formData: {
        pwd: "",
        pwdConfirm: "",
        pwdNotMask: "",
        pwdConfirmNotMask: ""
      },
      isPasswordMasked: false
    };
  },
  methods: {
    maskPassword() {
      this.formData.pwdNotMask = this.formData.pwd;
      this.mainPassword = this.formData.pwd;
      const arr = this.formData.pwd.split("");

      arr.forEach((element, index) => {
        if (element.trim() !== "") {
          arr[index] = "*";
        }
      });
      this.formData.pwd = arr.join("");
    },
    unMaskPassword() {
      this.formData.pwd = this.mainPassword;
    },
    blurPassword() {
      if (this.isPasswordMasked) {
        this.maskPassword();
      }
    },
    toggleMask() {
      this.isPasswordMasked = !this.isPasswordMasked;
      if (this.isPasswordMasked) {
        this.maskPassword();
      } else {
        this.unMaskPassword();
      }
    },
    changeProfile() {
      console.log(this.formData);
    }
  }
};
</script>
