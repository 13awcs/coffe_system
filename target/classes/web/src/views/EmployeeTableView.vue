<template>
  <div>
    <title-bar :title-stack="titleStack" />
    <hero-bar>
      Danh sách
      <router-link
        slot="right"
        to="/"
        class="button"
      >
        Danh sách nhân viên
      </router-link>
    </hero-bar>
    <section class="section is-main-section">

      <card-component
        class="has-table has-mobile-sort-spaced"
        title="Nhân viên"
        icon="account-multiple"
      >
        <employee-table
          checkable
        />
      </card-component>

      <hr>
    </section>
  </div>
</template>

<script>
  import { defineComponent } from "vue";
  import NotificationBar from "@/components/NotificationBar.vue";
  import CardComponent from "@/components/CardComponent.vue";
  import TitleBar from "@/components/TitleBar.vue";
  import HeroBar from "@/components/HeroBar.vue";
  import ItemsTable from "@/components/ItemTable";
  import EmployeeTable from "@/components/EmployeeTable";


  export default defineComponent({
    name: "EmployeeTableView",
    components: {
      ItemsTable,
      HeroBar,
      TitleBar,
      CardComponent,
      NotificationBar,
      EmployeeTable
    },
    data () {
      return {
        titleStack: ["Admin", "Nhân viên"]
      };
    }
  });
</script>
