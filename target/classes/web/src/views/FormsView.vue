<template>
  <div>
    <title-bar :title-stack="titleStack"/>
    <hero-bar>
      Thông tin
      <router-link
        slot="right"
        class="button"
        to="/"
      >
        Thông tin
      </router-link>
    </hero-bar>
    <section class="section is-main-section">
      <card-component
        icon="ballot"
        title="Thông tin"
      >
        <form @submit.prevent="formAction">
          <b-field
            horizontal
            label="Tên"
          >
            <b-field>
              <b-input
                v-model="employeeRequest.name"
                icon="account"
                name="name"
                placeholder="Tên"
                required
              />
            </b-field>
            <b-field>
              <b-input
                v-model="employeeRequest.email"
                icon="email"
                name="email"
                placeholder="E-mail"
                required
                type="email"
              />
            </b-field>
          </b-field>
          <b-field
            horizontal
            message="Không bắt đầu bằng số 0"
          >
            <b-field>
              <p class="control">
                <a class="button is-static">
                  +84
                </a>
              </p>
              <b-input
                v-model="employeeRequest.phone"
                expanded
                name="phone"
                type="tel"
              />
            </b-field>
          </b-field>

          <b-field
            horizontal
            label="Địa chỉ"
            message="Nhập địa chỉ"
          >
            <b-input
              v-model="employeeRequest.address"
              placeholder="Ví dụ: Hà Nội"
              required
            />
          </b-field>
          <b-field
            horizontal
            label="Ngày sinh"
          >
            <b-datepicker v-model="employeeRequest.dob"
                          :first-day-of-week="1"
                          placeholder="Chọn ngày sinh...">

            </b-datepicker>
          </b-field>

        </form>
      </card-component>
      <card-component
        icon="ballot-outline"
        title="Thông tin ca làm việc"
      >
        <b-field
          class="has-check"
          horizontal
          label="Ca"
        >
          <div v-for="shift in shifts">
            <input  type="radio" id="shift" :value="shift.id" v-model="employeeRequest.shiftId" /> {{shift.name}}
          </div>
        </b-field>
        <hr>
        <b-field
          class="has-check"
          horizontal
          label="Cơ sở cửa hàng"

        >
        <div v-for="store in stores">
          <input  type="radio" id="store" :value="store.id" v-model="employeeRequest.storeId" /> {{store.name}}
        </div>

        </b-field>

      </card-component>

      <b-field horizontal>
        <b-field grouped>
          <div class="control">
            <b-button
              native-type="submit"
              type="is-info"
              @click="submit"
            >
              Xác nhận
            </b-button>
          </div>
          <div class="control">
            <b-button
              type="is-info is-outlined"
              @click.prevent="formAction"
            >
              Xóa
            </b-button>
          </div>
        </b-field>
      </b-field>
    </section>
  </div>
</template>

<script>
  import {defineComponent} from "vue";
  import TitleBar from "@/components/TitleBar.vue";
  import CardComponent from "@/components/CardComponent.vue";
  import FilePicker from "@/components/FilePicker.vue";
  import HeroBar from "@/components/HeroBar.vue";
  import CheckboxRadioPicker from "@/components/CheckboxRadioPicker.vue";
  import axios from "axios";

  export default defineComponent({
    name: "FormsView",
    components: {
      CheckboxRadioPicker,
      HeroBar,
      FilePicker,
      CardComponent,
      TitleBar
    },
    data() {
      return {
        titleStack: ["Admin", "Thông tin"],
        date: new Date(),
        stores: [],
        shifts: [],
        employeeRequest: {
          id: '',
          name: '',
          dob: '',
          address: '',
          phone: '',
          email: '',
          shiftId: '',
          storeId: '',
        }
      };
    },

    mounted() {
      const baseDomain = "http://localhost:8080";

      const baseURL = `${baseDomain}`;
      this.instance = axios.create({
        baseURL,
      });
      this.instance.interceptors.request.use(
        (config) => {
          const token = localStorage.getItem("token");
          if (token) {
            config.headers["Authorization"] = `Bearer ${token}`;
          }

          return config;
        },

        (error) => {
          return Promise.reject(error);
        }
      );
      this.storeId = localStorage.getItem("storeId");

      this.loadStore();
      this.loadShift(this.storeId);
      this.employeeRequest.storeId = this.stores[0].id;
      this.employeeRequest.shiftId = this.shifts[0].id;
    },
    watch: {
      storeId() {
        this.employeeRequest.storeId = this.stores[0].id;
        this.employeeRequest.shiftId = this.shifts[0].id;
      }
    },
    methods: {
      loadStore() {
        this.instance.get("/store/list")
          .then((response) => {
            this.stores = response.data.data;
            this.employeeRequest.storeId = this.stores[0].id
          })
          .catch((e) => {
            this.error.push(e);
          });
      },

      loadShift(storeId) {
        this.instance.get("admin/" + storeId + "/shift/list")
          .then((response) => {
            this.shifts = response.data;
            this.employeeRequest.shiftId = this.shifts[0].id;
          })
          .catch((e) => {
            this.error.push(e);
          });
      },

      formAction() {
        this.$buefy.snackbar.open({
          message: "Demo only",
          queue: false
        });
      },


      submit() {
        this.instance.post("/admin/employee/save", this.employeeRequest)
          .then((response) => {
            if (response.data.status.code === 1000) {
              this.resetForm();
              this.$buefy.toast.open({
                message: 'Lưu thành công',
                type: 'is-success'
              })
            }
          });
      },

      resetForm() {
        this.employeeRequest.id = '',
        this.employeeRequest.name = '',
        this.employeeRequest.address = '',
        this.employeeRequest.dob = '',
        this.employeeRequest.phone = '',
        this.employeeRequest.email = '',
        this.employeeRequest.shiftId = '',
        this.employeeRequest.storeId = ''
      }
    }
  });
</script>
