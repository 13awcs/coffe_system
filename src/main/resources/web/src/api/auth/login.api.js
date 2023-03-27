import axios from "axios";
import router from "@/router";

const BASE_URL = 'http://localhost:8080';

export const login = (form) => {
  isLoading.value = true;
  setTimeout(() => {
    axios.post("http://localhost:8080/auth/login", { username: form.value.username, password: form.value.password })
      .then((response) => {
        console.log(response);
        token.value = response.data.data.accessToken;
        if (response.data.status.code !== 1000) {
          console.log("Login fail !");
          checkLogin.value = true;
          isLoading.value = false;
        }else {
          console.log("token: ", token.value);
          localStorage.setItem("token", token.value);
          localStorage.setItem("username", response.data.data.user.username);
          isLoading.value = false;
          router.push("/");
        }

      });
  }, 750);
};
