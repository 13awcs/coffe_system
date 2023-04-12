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
        Thông tin cửa hàng
      </router-link>
    </hero-bar>
    <section class="section is-main-section">

      <card-component
        class="has-table has-mobile-sort-spaced"
        title="Khách hàng"
        icon="account-multiple"
      >
        <clients-table-sample
          checkable
        />
      </card-component>

      <hr>

      <card-component
        class="has-table has-mobile-sort-spaced"
        title="Nhân viên"
        icon="account-multiple"
      >
        <clients-table-sample
          checkable
        />
      </card-component>

      <hr>

      <card-component
        class="has-table has-mobile-sort-spaced"
        title="Hoá đơn"
        icon="account-multiple"
      >
        <clients-table-sample
          checkable
        />
      </card-component>
    </section>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import NotificationBar from "@/components/NotificationBar.vue";
import ClientsTableSample from "@/components/ClientsTableSample.vue";
import CardComponent from "@/components/CardComponent.vue";
import TitleBar from "@/components/TitleBar.vue";
import HeroBar from "@/components/HeroBar.vue";

export default defineComponent({
  name: "TablesView",
  components: {
    HeroBar,
    TitleBar,
    CardComponent,
    ClientsTableSample,
    NotificationBar
  },
  data () {
    return {
      titleStack: ["Admin", "Tables"]
    };
  }
});
</script>
