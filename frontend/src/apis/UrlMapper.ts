export default {
  BASE_URL: "http://localhost:8080/api/",
  ROUTES: {
    auth: {
      URL: "auth/",
      login: "auth/signin",
      signup: "auth/signup",
      idCheck: "auth/idcheck",
      emailCheck: "auth/emailcheck",
      emailValidate: "auth/emailvalidate"
    },
    files: {
      URL: "files/",
      upload: "upload/multipleFiles"
    }
  }
};
