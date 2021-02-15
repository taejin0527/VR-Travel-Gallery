// URL을 재사용하기 위한 목적
export default {
  BASE_URL: "https://i4d110.p.ssafy.io/apis/api/",
  BOARD_BASE_URL: "https://i4d110.p.ssafy.io/apis/board/",
  PAY_BASE_URL: "https://i4d110.p.ssafy.io/apis/",
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
