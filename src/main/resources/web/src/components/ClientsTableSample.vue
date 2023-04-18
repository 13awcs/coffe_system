<template>
  <div>
    <modal-box
      :is-active="isModalActive"
      :trash-object-name="trashObject ? trashObject.name : null "
      @confirm="trashConfirm"
      @cancel="trashCancel"
    />
    <b-table
      :checked-rows.sync="checkedRows"
      :checkable="checkable"
      :paginated="paginated"
      :per-page="perPage"
      :data="customers"
      default-sort="name"
      striped
      hoverable
    >
      <b-table-column
        v-slot="props"
        cell-class="has-no-head-mobile is-image-cell"
      >
        <div class="image">
          <img
            :src="props.row.avatar"
            class="is-rounded"
          >
        </div>
      </b-table-column>
      <b-table-column
        v-slot="props"
        label="Tên"
        field="name"
        sortable
      >
        {{ props.row.name }}
      </b-table-column>
      <b-table-column
        v-slot="props"
        label="Số điện thoại"
        field="phone"
        sortable
      >
        {{ props.row.phone }}
      </b-table-column>
      <b-table-column
        v-slot="props"
        label="Điểm"
        field="point"
        sortable
      >
        {{ props.row.point }}
      </b-table-column>
      <b-table-column
        v-slot="props"
        label="Ngày tạo"
      >
        <small
          class="has-text-grey is-abbr-like"
          :title="props.row.createTime"
        >{{ props.row.createTime }}</small>
      </b-table-column>
      <b-table-column
        v-slot="props"
        custom-key="actions"
        cell-class="is-actions-cell"
      >
        <div class="buttons is-right no-wrap">
          <router-link
            :to="{name:'client.edit', params: {id: props.row.id}}"
            class="button is-small is-info"
          >
            <b-icon
              icon="account-edit"
              size="is-small"
            />
          </router-link>
          <b-button
            type="is-danger"
            size="is-small"
            @click="prompt(props.row.id)"
          >
            <b-icon
              icon="trash-can"
              size="is-small"
            />
          </b-button>
        </div>
      </b-table-column>

      <section
        slot="empty"
        class="section"
      >
        <div class="content has-text-grey has-text-centered">
          <p>
            <b-icon
              icon="emoticon-sad"
              size="is-large"
            />
          </p>
          <p>Không có dữ liệu&hellip;</p>
        </div>
      </section>
    </b-table>
  </div>
</template>

<script>
  import {defineComponent} from "vue";
  import {mapState} from "vuex";
  import ModalBox from "@/components/ModalBox.vue";
  import axios from "axios";

  export default defineComponent({
    name: "ClientsTableSample",
    components: {ModalBox},
    props: {
      checkable: Boolean,
      isEmpty: Boolean,
      perPage: {
        type: Number,
        default: 10
      }
    },
    data() {
      return {
        checkedRows: [],
        isModalActive: false,
        trashObject: null,
        customers: [],
        instance: '',
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
          const token = localStorage.getItem('token');
          if (token) {
            config.headers['Authorization'] = `Bearer ${token}`;
          }

          return config;
        },

        (error) => {
          return Promise.reject(error);
        }
      );
      this.loadCustomer();
    },

    computed: {

      paginated() {
        return this.customers.length > this.perPage;
      },
      ...mapState([
        "clients"
      ])
    },
    methods: {
      loadCustomer() {
        console.log("token: ", localStorage.getItem("token"));
        this.instance.get("/customer/list")
          .then((response) => {
            this.customers = response.data;
          })
          .catch((e) => {
            this.error.push(e);
          });
      },
      prompt(id) {
        this.$buefy.dialog.prompt({
          message: `What's your name?`,
          inputAttrs: [{
            placeholder: 'e.g. Walter',
            maxlength: 10
          }],
          trapFocus: true,
          onConfirm: (value) => this.$buefy.toast.open(`Your name is: ${value}`)
        })
      },
      trashModalOpen(obj) {
        this.trashObject = obj;
        this.isModalActive = true;
      },
      trashConfirm() {
        this.isModalActive = false;

        this.$buefy.snackbar.open({
          message: "Confirmed",
          queue: false
        });
      },
      trashCancel() {
        this.isModalActive = false;
      }
    }
  });
</script>
