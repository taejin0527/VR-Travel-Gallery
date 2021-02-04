export default {
  BASE_URL: "http://i4d110.p.ssafy.io/apis/api/",
  BOARD_BASE_URL:"http://i4d110.p.ssafy.io/apis/board/",
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
