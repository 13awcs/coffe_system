<template>
  <div>
    <title-bar :title-stack="titleStack" />
    <hero-bar>
      Thông tin
      <router-link
        slot="right"
        to="/"
        class="button"
      >
        Thông tin
      </router-link>
    </hero-bar>
    <section class="section is-main-section">
      <card-component
        title="Thông tin"
        icon="ballot"
      >
        <form @submit.prevent="formAction">
          <b-field
            label="Tên"
            horizontal
          >
            <b-field>
              <b-input
                v-model="form.name"
                icon="account"
                placeholder="Tên"
                name="name"
                required
              />
            </b-field>
            <b-field>
              <b-input
                v-model="form.email"
                icon="email"
                type="email"
                placeholder="E-mail"
                name="email"
                required
              />
            </b-field>
          </b-field>
          <b-field
            message="Không bắt đầu bằng số 0"
            horizontal
          >
            <b-field>
              <p class="control">
                <a class="button is-static">
                  +84
                </a>
              </p>
              <b-input
                v-model="form.phone"
                type="tel"
                name="phone"
                expanded
              />
            </b-field>
          </b-field>
          <b-field
            label="Chức vụ"
            horizontal
          >
            <b-select
              v-model="form.department"
              placeholder="Chọn chức vụ"
              required
            >
              <option
                v-for="(department, index) in departments"
                :key="index"
                :value="department"
              >
                {{ department }}
              </option>
            </b-select>
          </b-field>
          <hr>
          <b-field
            label="Địa chỉ"
            message="Nhập địa chỉ"
            horizontal
          >
            <b-input
              v-model="form.subject"
              placeholder="Ví dụ: Hà Nội"
              required
            />
          </b-field>
          <b-field
            label="Thông tin cá nhân"
            message="Không quá 255 kí tự"
            horizontal
          >
            <b-input
              v-model="form.question"
              type="textarea"
              placeholder="Miêu tả về bản thân"
              maxlength="255"
              required
            />
          </b-field>
          <hr>
          <b-field horizontal>
            <b-field grouped>
              <div class="control">
                <b-button
                  native-type="submit"
                  type="is-info"
                >
                  Xác nhân
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
        </form>
      </card-component>
      <card-component
        title="Thông tin ca làm việc"
        icon="ballot-outline"
      >
        <b-field
          label="Ca"
          class="has-check"
          horizontal
        >
          <checkbox-radio-picker
            v-model="customElementsForm.checkbox"
            :options="{ lorem: 'Sáng', ipsum: 'Chiều', dolore: 'Tối' }"
            type="is-info"
          />
        </b-field>
        <hr>
        <b-field
          label="Cơ sở cửa hàng"
          class="has-check"
          horizontal
        >
          <checkbox-radio-picker
            v-model="customElementsForm.radio"
            :options="{ one: 'Cơ sở 1', two: 'Cơ sở 2' }"
            type="is-info"
          />
        </b-field>
        <hr>
        <hr>
        <b-field
          label="Ảnh"
          horizontal
        >
          <file-picker
            v-model="customElementsForm.file"
            type="is-info"
          />
        </b-field>
      </card-component>
    </section>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import TitleBar from "@/components/TitleBar.vue";
import CardComponent from "@/components/CardComponent.vue";
import FilePicker from "@/components/FilePicker.vue";
import HeroBar from "@/components/HeroBar.vue";
import CheckboxRadioPicker from "@/components/CheckboxRadioPicker.vue";

export default defineComponent({
  name: "FormsView",
  components: {
    CheckboxRadioPicker,
    HeroBar,
    FilePicker,
    CardComponent,
    TitleBar
  },
  data () {
    return {
      titleStack: ["Admin", "Thông tin"],
      departments: ["Phục vụ", "Pha chế", "Thu ngân"],
      form: {
        name: null,
        email: null,
        phone: null,
        department: null,
        subject: null,
        question: null
      },
      customElementsForm: {
        checkbox: ["lorem"],
        radio: "one",
        switch: true,
        file: null
      }
    };
  },
  methods: {
    formAction () {
      this.$buefy.snackbar.open({
        message: "Demo only",
        queue: false
      });
    }
  }
});
</script>
