import loginAPI from "@/login/loginAPI.js";

export default {
  async login(uid, password) {
    try {
      const loginResponse = await loginAPI.login(uid, password);
      return loginResponse;
    } catch (err) {
      console.error(err);
    }
  }
};
