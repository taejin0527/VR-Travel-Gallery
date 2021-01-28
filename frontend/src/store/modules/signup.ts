import axios from "axios";
import swal from "sweetalert2";

import SERVER from "@/apis/UrlMapper";
import ROUTER from "@/router";

import { VuexModule, Module, Mutation, Action } from "vuex-module-decorators";

type User = {
  uid: string;
  uname: string;
  upw: string;
  upw2: string;
  uemail: string;
};

@Module({ namespaced: true })
export default class UserData extends VuexModule {
  /************************* States ************************************/
  public signupData = {} as User;
  public page = localStorage.getItem("page") ? localStorage.getItem("page") : 1;
  public confirmCode2 = "";

  /************************* Mutation **********************************/
  @Mutation
  SET_SIGNUPDATA(state: any, signupData: any) {
    this.signupData = signupData;
  }
  @Mutation
  SET_PAGE(state: any, page: any) {
    this.page = page;
    localStorage.setItem("page", page);
  }
  @Mutation
  SET_CODE(state: any, code: any) {
    state.confirmCode2 = code;
  }
  /************************** Action ***********************************/
  @Action
  saveSignupData({ commit }: any, signupData: any) {
    commit("SET_SIGNUPDATA", signupData);
  }
  @Action
  setPage({ commit }: any, page: any) {
    commit("SET_PAGE", page);
  }
  /** Page 1 : Basic Info */
  @Action({ rawError: true })
  signup({ dispatch }: any, signupData: any): void {
    const info = {
      data: signupData,
      route: SERVER.ROUTES.auth.signup,
    };
    dispatch("postAuthData", info);
  }

  @Action
  postAuthData({ commit }: any, info: any): Promise<any> {
    return axios
      .post(SERVER.BASE_URL + info.route, info.data, {
        headers: {
          "content-type": "application/json",
        },
      })
      .then((res) => {
        ROUTER.push({ name: "Login" });
      })
      .catch((err) => {
        swal.fire({
          icon: "error",
          title: "Oops...",
          text: "로그인 정보를 확인해주세요.",
        });
      });
  }

  @Action({ rawError: true })
  idCheck(uid: any) {
    if (uid === "") {
      swal.fire("아이디를 입력하세요.");
      return false;
    }
    return axios
      .get(SERVER.BASE_URL + SERVER.ROUTES.auth.idCheck + "/" + uid)
      .then((res) => {
        if (res.data === "success") {
          swal.fire("사용 가능한 아이디입니다.");
          return true;
        } else {
          swal.fire({
            icon: "error",
            text: "이미 사용 중인 아이디입니다.",
          });
          return false;
        }
      })
      .catch((err) => console.log(err.response));
  }

  /** Page 2 : Email */
  @Action({ rawError: true })
  emailCheck(context: any, email: string) {
    return axios
      .post(SERVER.BASE_URL + SERVER.ROUTES.auth.emailCheck, {
        userEmail: email,
      })
      .then((res) => {
        if (res.data === "success") {
          return true;
        } else {
          return false;
        }
      })
      .catch((err) => console.log(err));
  }
  @Action({ rawError: true })
  getConfirmCode({ commit }: any, email: any) {
    return axios
      .post(SERVER.BASE_URL + SERVER.ROUTES.auth.emailValidate, {
        userEmail: email,
      })
      .then((confirmCode: any) => {
        if (confirmCode === "fail") {
          swal.fire("이메일을 확인해주세요.");
          return "";
        } else {
          commit("SET_CODE", confirmCode.data);
          return confirmCode;
        }
      })
      .catch((err) => {
        return err.response;
      });
  }
}
