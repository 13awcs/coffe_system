<template>
  <div>
    <title-bar :title-stack="titleStack"/>
    <hero-bar :has-right-visible="false">
      Dashboard
    </hero-bar>
    <div class="row">
      <div class="col col-sm-4">
        <div class="card shadow mb-4">
          <div class="card-header py-3">Danh sách bàn</div>
          <div class="card-body">
            <div class="table-area" v-for="(numberTable, index) in listOrder">
              <b-button style="width: 130px; border: double; height: 70px"
                        rounded
                        type="is-light"
                        v-model="numberTable.id"
                        class=""
              >
                {{ numberTable.name }}
              </b-button>
            </div>
          </div>

        </div>
      </div>

      <div class="col col-sm-8 detail-order-block">
        <div class="card shadow mb-4">
          <div class="card-header py-3">Chi tiết order: Bàn 1</div>
          <div class="detail-order">
            <order-detail-table>

            </order-detail-table>
          </div>
        </div>
        <b-button class="btn-payment" type="is-danger">Thanh toán</b-button>
      </div>
    </div>

  </div>


</template>

<script>
  import {defineComponent} from "vue";
  import ClientsTableSample from "@/components/ClientsTableSample";
  import OrderDetailTable from "@/components/OrderDetailTable";
  import LineChart from "@/components/Charts/LineChart";
  import CardComponent from "@/components/CardComponent";
  import CardWidget from "@/components/CardWidget";
  import TilesBlock from "@/components/TilesBlock";
  import HeroBar from "@/components/HeroBar";
  import TitleBar from "@/components/TitleBar";
  import NotificationBar from "@/components/NotificationBar";
  import * as chartConfig from "@/components/Charts/chart.config";
  import axios from "axios";

  export default defineComponent({
    name: "Order",
    components: {
      ClientsTableSample,
      OrderDetailTable,
      LineChart,
      CardComponent,
      CardWidget,
      TilesBlock,
      HeroBar,
      TitleBar,
      NotificationBar
    },
    data() {
      return {
        titleStack: ["Admin", "Order"],
        instance: '',
        error: [],
        stores: [],
        listTable: [],
        listOrder: []
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
      this.fillChartData();

      this.$buefy.snackbar.open({
        message: "Welcome back",
        queue: false
      });
      this.loadStore();
      this.loadTable();

    },

    methods: {
      fillChartData() {
        this.chartData = chartConfig.sampleChartData();
      },
      loadStore() {
        axios.interceptors.request.use(
          (config) => {
            const token = localStorage.getItem('authtoken');

            if (token) {
              config.headers['Authorization'] = `Bearer ${token}`;
            }

            return config;
          },

          (error) => {
            return Promise.reject(error);
          }
        );
        console.log("token: ", localStorage.getItem("token"));
        this.instance.get("/store/list")
          .then((response) => {
            console.log("store", response);
            this.stores = response.data.data;
            localStorage.setItem("stores", JSON.stringify(this.stores));
          })
          .catch((e) => {
            this.error.push(e);
          });
      },

      loadTable() {
        // let storeId = localStorage.getItem("store");
        let storeId = 1;
        console.log("storeId", storeId);
        this.instance.get("/admin/" + storeId + "/table/list")
          .then((response) => {
            console.log("table", response);
            this.listOrder = response.data.data;
          });
      },


    },

  });
</script>

<style scoped>

  .row {
    display: flex;
    flex-wrap: wrap;
    margin-left: 40px;
    margin-right: 10px;
    margin-top: 10px;
    height: 500px;
    padding: 10px;
  }

  .card {
    position: relative;
    display: flex;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #fff;
    background-clip: border-box;
    border: 1px solid #e3e6f0;
    border-radius: 0.35rem;
  }

  .shadow {
    box-shadow: 0 .15rem 1.75rem 0 rgba(58, 59, 69, .15) !important;
  }

  .card-header {
    padding: 0.75rem 1.25rem;
    margin-bottom: 0;
    background-color: #f8f9fc;
    border-bottom: 1px solid #e3e6f0;
    font-weight: bold;
    font-size: 20px;
  }

  .card-body {
    flex: 1 1 auto;
    min-height: 1px;
    padding: 1.25rem;
    display: flex;
    flex-wrap: wrap;
    width: 500px;
    height: 500px;
    overflow: scroll;
  }

  .detail-order {
    width: 1000px;
    height: 500px;
    overflow: scroll;
  }

  .table-area {
    padding: 5px;
    display: inline-block;
    flex-grow: 1 0;
  }

  .detail-order-block {
    margin-left: 15px;
  }

  .btn-payment {
    margin-top: 10px;
    position: absolute;
    right: 8%;
  }

</style>
