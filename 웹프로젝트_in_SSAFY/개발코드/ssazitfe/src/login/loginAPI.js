import axios from "axios";

const getUserInfo = (uid, password) => {
  // 유저 정보 가져와서 저장하기
  return axios.get("/endpoint-for-get-user-info", {
    params: {
      uid: uid,
      password: password
    }
  });
};

const isFinished = uid => {
  // 유저 로그아웃하기
  return axios.get("/endpoint-for-is-finished", {
    params: {
      uid: uid
    }
  });
};

export default {
  async login(uid, password) {
    //비동기 로그인,
    try {
      const getUserInfoPromise = await getUserInfo(uid, password);
      const isFinishedPromise = await isFinished(uid); // Promise.all의 예시를 위해 집어넣음
      const [userInfoResponse, isFinishedResponse] = await Promise.all([
        getUserInfoPromise,
        isFinishedPromise
      ]);
      if (userInfoResponse.data.length === 0) return "noAuth"; // 로그인 결과에 따른 분기 처리를 해준다
      if (isFinishedResponse.data[0].CNT > 0) return "done";
      return userInfoResponse;
    } catch (err) {
      console.error(err);
    }
  }
};
