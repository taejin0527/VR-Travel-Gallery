export default {
  BASE_URL: "https://id4110.p.ssafy.io:8080/api/",
  BOARD_BASE_URL: "https://id4110.p.ssafy.io:8080/board/",
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
