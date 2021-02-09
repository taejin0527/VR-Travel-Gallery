export default {
  BASE_URL: "https://i4110.p.ssafy.io:8080/api/",
  BOARD_BASE_URL: "https://i4110.p.ssafy.io:8080/board/",
  PAY_BASE_URL: "https://i4110.p.ssafy.io:8080/",
  ROUTES: {
    auth: {
      URL: "auth/",
      login: "auth/signin",
      signup: "auth/signup",
      idCheck: "auth/idcheck",
      emailCheck: "auth/emailcheck",
      emailValidate: "auth/emailvalidate",
    },
    files: {
      URL: "files/",
      upload: "upload/multipleFiles",
    },
  },
};
