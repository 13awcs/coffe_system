export default [
  "Chung",
  [
    {
      to: "/home",
      icon: "desktop-mac",
      label: "Trang chủ"
    },
    {
      to: "/order",
      icon: "desktop-mac",
      label: "Khu vực bàn"
    }
  ],
  "Danh mục",
  [
    {
      to: "/tables",
      label: "Danh sách",
      icon: "table",
      updateMark: true
    },
    {
      to: "/forms",
      label: "Thông tin",
      icon: "square-edit-outline"
    },
    {
      label: "Đồ uống",
      subLabel: "Submenus Example",
      icon: "view-list",
      menu: [
        {
          href: "#void",
          label: "Thêm đồ uống"
        },
        {
          href: "#void",
          label: "Danh sách đồ uống"
        }
      ]
    }
  ],
  "Nhân viên và khách hàng",
  [
    {
      to: "/employee",
      label: "Nhân viên",
      icon: "account",
    },
    {
      to: "/client",
      label: "Khách hàng",
      icon: "account",
    }
  ]
];
