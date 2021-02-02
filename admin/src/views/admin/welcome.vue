<template>
  <div>
    <h1>您好，欢迎进入权限管理系统控台</h1>
  </div>
</template>

<script>
export default {
  name: "welcome",
  mounted: function() {
    // sidebar激活样式方法一
     this.$parent.activeSidebar("welcome-sidebar");
  },
  methods: {
  }
}
</script>