export default [
  "Chung",
  [
    {
      to: "/",
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
      to: "/profile",
      label: "Cá nhân",
      icon: "account-circle"
    },
    {
      to: "/login",
      label: "Đăng nhập",
      icon: "lock"
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
  "Dành cho nhân viên",
  [
    {
      href: "",
      label: "Nhân viên",
      icon: "account",
      target: "_blank"
    },
    {
      href: "",
      label: "Khách hàng",
      icon: "account",
      target: "_blank"
    }
  ]
];
