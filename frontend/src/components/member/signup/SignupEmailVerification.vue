<template>
  <v-card class="mx-auto" flat max-width="350">
    <v-spacer>
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
    </v-spacer>
    <v-text-field
      v-model="confirm"
      :messages="error.confirm"
      label="Verification Code"
      outlined
      solo
      required
      color="#424242"
      autocomplete="off"
    ></v-text-field>
    <v-spacer>
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
    </v-spacer>
    <div>
      <v-btn color="grey" class="white--text" @click="$emit('pageDown')"
        >뒤로가기</v-btn
      >
      <v-divider class="mr-5" vertical></v-divider>
      <v-btn
        color="#ea907a"
        class="white--text"
        :disabled="!isSubmit"
        @click="verify()"
        >회원가입 완료</v-btn
      >
    </div>
    <v-spacer>
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
    </v-spacer>
  </v-card>
</template>

