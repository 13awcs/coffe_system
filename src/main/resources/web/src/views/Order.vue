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
              <b-button style="width: 130px; border: double; height: 70px" rounded type="is-light">{{ numberTable.numberTable }}</b-button>
            </div>
          </div>

        </div>
      </div>

      <div class="col col-sm-8 detail-order-block">
        <div class="card shadow mb-4">
        <div class="card-header py-3">Chi tiết order</div>
        <div class="detail-order">
          <order-detail-table>

          </order-detail-table>
        </div>
      </div>
        <b-button active>Active</b-button>
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
        listOrder: [{
          numberTable: 'Bàn 1',
        },
          {
            numberTable: 'Bàn 2',
          },{
            numberTable: 'Bàn 3',
          },
          {
            numberTable: 'Bàn 4',
          },
          {
            numberTable: 'Bàn 2',
          },{
            numberTable: 'Bàn 3',
          },
          {
            numberTable: 'Bàn 4',
          },
          {
            numberTable: 'Bàn 2',
          },{
            numberTable: 'Bàn 3',
          },
          {
            numberTable: 'Bàn 4',
          },
          {
            numberTable: 'Bàn 2',
          },{
            numberTable: 'Bàn 3',
          },
          {
            numberTable: 'Bàn 4',
          },
          {
            numberTable: 'Bàn 2',
          },{
            numberTable: 'Bàn 3',
          },
          {
            numberTable: 'Bàn 4',
          },
          {
            numberTable: 'Bàn 2',
          },{
            numberTable: 'Bàn 3',
          },
          {
            numberTable: 'Bàn 4',
          },
          {
            numberTable: 'Bàn 2',
          },{
            numberTable: 'Bàn 3',
          },
          {
            numberTable: 'Bàn 4',
          },
          {
            numberTable: 'Bàn 2',
          },{
            numberTable: 'Bàn 3',
          },
          {
            numberTable: 'Bàn 4',
          }
          ]
      };
    },
    mounted() {
      this.fillChartData();

      this.$buefy.snackbar.open({
        message: "Welcome back",
        queue: false
      });
    },
    methods: {
      fillChartData() {
        this.chartData = chartConfig.sampleChartData();
      }
    }
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
    box-shadow: 0 .15rem 1.75rem 0 rgba(58,59,69,.15)!important;
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

</style>
