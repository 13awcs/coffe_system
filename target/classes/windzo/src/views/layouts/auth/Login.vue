<template>
  <div class="w-full h-screen">
    <div class="flex shadow rounded-md h-screen">
      <div class="bg-white dark:bg-gray-900 w-full">
        <form>
          <div
            class="form-body lg:max-w-xl mx-auto lg:p-20 p-8 lg:mt-20 mt-5 space-y-8"
          >
            <div
              class="form-head cursor-pointer"
              @click="$router.push('/')"
            >
              <img
                src="../../../assets/logo/logo_cf.jpg"
                alt=""
                class="w-10"
              />
            </div>
            <div class="space-y-3">
              <h2 class="dark:text-white font-semibold text-gray-800 text-4xl">
                Welcome, to ThuyCf<span class="text-primary">.</span>
              </h2>
              <p class="dark:text-gray-400 text-gray-700">
                Please enter your account to continue.
              </p>
            </div>
            <div class="space-y-5">
              <div class="relative z-0 w-full mb-6 group">
                <input
                  type="username"
                  v-model="form.username"
                  name="floating_username"
                  id="floating_username"
                  class="block py-2.5 px-0 w-full text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-primary focus:outline-none focus:ring-0 focus:border-primary peer"
                  placeholder=" "
                  required
                />
                <label
                  for="floating_username"
                  class="peer-focus:font-medium absolute username text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-primary peer-focus:dark:text-primary peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6"
                  >Username</label
                >
              </div>
              <div class="relative z-0 w-full mb-6 group">
                <input
                  type="password"
                  name="floating_password"
                  v-model="form.password"
                  id="floating_password"
                  class="block py-2.5 px-0 w-full text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-primary focus:outline-none focus:ring-0 focus:border-primary peer"
                  placeholder=" "
                  required
                />
                <label
                  for="floating_password"
                  class="peer-focus:font-medium absolute username text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-primary peer-focus:dark:text-primary peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6"
                  >Password</label
                >
              </div>
              <div v-if="isUnValid">Username or password is wrong !</div>
            </div>
            <div class="flex justify-between">
              <div class="flex items-start">
                <div class="flex items-center h-5">
                  <input
                    id="remember"
                    type="checkbox"
                    value=""
                    autocomplete="off"
                    class="accent-primary focus:ring-4 cursor-pointer w-4 h-4 border border-gray-300 rounded dark:bg-gray-700 bg-gray-50 focus:ring-3 focus:ring-primary/30"
                  />
                </div>
                <label
                  for="remember"
                  class="ml-2 text-sm cursor-pointer font-normal dark:text-white text-gray-500"
                  >Remember for 30 days</label
                >
              </div>
              <button
                @click="$router.push('/auth/forgot-password')"
                class="text-sm dark:text-white hover:text-primary text-gray-700"
              >
                Forgot password?
              </button>
            </div>

            <button
              class="text-white bg-primary hover:bg-primary/80 p-3 w-full rounded-md"
              @click="login"
            >
              Login
            </button>
            <p class="dark:text-white text-center text-gray-700">
              Don't have an account?<button
                type="button"
                @click="$router.push('/auth/register')"
                class="ml-2 text-primary"
              >
                Register here
              </button>
            </p>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
  import {ref} from "vue";
  import axios from "axios";
  import router from "@/router";

  const isUnValid = ref(false);
  const token = ref("");
  const form = ref({
    username: "",
    password: ""
  });
  const login = () => {
    console.log('vao day',form.value.username)
    setTimeout(() => {
      axios.post("http://localhost:8080/auth/login", { username: form.value.username, password: form.value.password })
          .then((response) => {
            console.log(response);
            token.value = response.data.data.accessToken;
            if (response.data.status.code !== 1000) {
              console.log("Login fail !");
              isUnValid.value = true;
            }else {
              console.log("token: ", token.value);
              localStorage.setItem("token", token.value);
              localStorage.setItem("username", response.data.data.user.username);
              router.push("/");
            }

          });
    }, 750);
  };
</script>
<style>
  /* custom pattern https://superdesigner.co/tools/css-backgrounds */
  .bg-wave {
    background: radial-gradient(
        circle at top left,
        transparent 25%,
        #4f46e5 25.5%,
        #4f46e5 36%,
        transparent 37%,
        transparent 100%
      ),
      radial-gradient(
        circle at bottom right,
        transparent 34%,
        #4f46e5 34.5%,
        #4f46e5 45.5%,
        transparent 46%,
        transparent 100%
      );
    background-size: 6em 6em;
    opacity: 1;
  }

  .username {
    line-height: 10px;
  }
</style>
