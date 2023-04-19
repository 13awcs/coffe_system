<template>
  <div>
    <modal-box
      :is-active="isModalActive"
      :trash-object-name="trashObject ? trashObject.name : null "
      @confirm="trashConfirm"
      @cancel="trashCancel"
    />
    <b-table
      :paginated="paginated"
      :per-page="perPage"
      :data="items"
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
            :src="props.row.image"
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
        label="Danh mục"
        field="phone"
        sortable
      >
        {{ props.row.categoryName }}
      </b-table-column>
      <b-table-column
        v-slot="props"
        label="Price"
        field="point"
        sortable
      >
        {{ props.row.price }}
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
            @click=""
            v-b-modal.modal-prevent-closing
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
    name: "ItemsTable",
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
        items: [],
        storeId: '',
        instance: '',
        isCard: false,
        isComponentModalActive: false
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
      this.storeId = localStorage.getItem("storeId");
      this.loadItems(this.storeId);
      this.$root.$on('reload', (storeId) => {
        this.loadItems(storeId);
      })
    },

    computed: {

      paginated() {
        return this.items.length > this.perPage;
      },
      ...mapState([
        "clients"
      ])
    },
    methods: {
      loadItems(storeId) {
        this.storeId = localStorage.getItem("store");
        this.instance.get("/item/" +storeId+"/list")
          .then((response) => {
            this.items = response.data.content;
          })
          .catch((e) => {
            this.error.push(e);
          });
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
    },
    // reloadData() {
    //   this.$root.$on('reload', (storeId) => {
    //     this.loadItems(storeId);
    //   })
    // }

  });
</script>
