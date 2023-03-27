<template>
  <card-component
    title="Login"
    icon="lock"
  >
    <router-link
      slot="button"
      to="/"
      class="button is-small"
    >
      Dashboard
    </router-link>

    <form
      method="POST"
      @submit.prevent="login"
    >
      <b-field label="Username">
        <b-input
          v-model="form.username"
          name="username"
          type="username"
          required
        />
      </b-field>

      <b-field label="Password">
        <b-input
          v-model="form.password"
          type="password"
          name="password"
          required
        />
      </b-field>

      <b-field>
        <b-checkbox
          v-model="form.remember"
          type="is-black"
          class="is-thin">
          Remember me
        </b-checkbox>
        <div v-if="checkLogin" class="alert alert-danger" style="color: red">
          Username or password is wrong !
        </div>
      </b-field>

      <hr>

      <b-field grouped>
        <div class="control">
          <b-button
            native-type="submit"
            type="is-black"
            :loading="isLoading"
          >
            Login
          </b-button>
        </div>
        <div class="control">
          <router-link
            to="/"
            class="button is-outlined is-black"
          >
            Dashboard
          </router-link>
        </div>
      </b-field>
    </form>
  </card-component>
</template>

<script setup>
  import {ref} from "vue";
  import axios from "axios";
  import CardComponent from "@/components/CardComponent.vue";
  import router from "@/router";

  const checkLogin = ref(false);
  const isLoading = ref(false);
  const token = ref("");
  const form = ref({
      username: "",
      password: ""
  });

  // const login = () => {
  //   isLoading.value = true;
  //   setTimeout(() => {
  //     axios.post("http://localhost:8080/auth/login", { username: form.value.username, password: form.value.password })
  //       .then((response) => {
  //         console.log(response);
  //         token.value = response.data.data.accessToken;
  //         if (response.data.status.code !== 1000) {
  //           console.log("Login fail !");
  //           checkLogin.value = true;
  //           isLoading.value = false;
  //         }else {
  //           console.log("token: ", token.value);
  //           localStorage.setItem("token", token.value);
  //           localStorage.setItem("username", response.data.data.user.username);
  //           isLoading.value = false;
  //           router.push("/");
  //         }
  //
  //       });
  //   }, 750);
  // };
</script>

