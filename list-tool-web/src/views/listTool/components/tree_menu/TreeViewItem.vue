<template>
  <div class="tree-view-item">
    <div
      class="level"
      :class="'level-' + menu.resourceLevel"
      v-for="menu in menus"
      :key="menu.id"
    >
      <div v-if="menu.resourceType === 'link'">
        <router-link
          class="link"
          v-bind:to="menu.resourceUrl"
          @click.native="toggle(menu)"
          >{{ menu.resourceName }}</router-link
        >
      </div>
      <div v-if="menu.resourceType === 'button'">
        <div
          class="button heading"
          :class="{ selected: menu.selected, expand: menu.expanded }"
          @click="toggle(menu)"
        >
          <span
            class="ats-tree-node"
            :title="handleTitleVisible(menu.resourceName)"
            >{{ menu.resourceName }}</span
          >
          <!--{{menu.resourceName}}-->
          <div class="icon">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              focusable="false"
              viewBox="0 0 24 24"
            >
              <path
                d="M8.59 16.34l4.58-4.59-4.58-4.59L10 5.75l6 6-6 6z "
              ></path>
            </svg>
          </div>
        </div>
        <transition name="fade">
          <div
            class="heading-children"
            v-show="menu.expanded"
            v-if="menu.childQueues"
          >
            <Tree-view-item :menus="menu.childQueues"></Tree-view-item>
          </div>
        </transition>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "TreeViewItem",
  props: ["menus"],
  created() {
    this.$store.commit("firstInit", { url: this.$route.path });
  },
  mounted() {},
  data() {
    return {
      treeData: []
    };
  },
  methods: {
    toggle(menu) {
      this.$store.commit("findParents", { menu });
    },
    //超长菜单名后面用...代替，并且加上title属性
    handleTitleVisible(title) {
      let titleLen = title.replace(/[^\x00-\xff]/g, "..").length;
      if (titleLen > 10) {
        return title;
      } else {
        return "";
      }
    }
  }
};
</script>
<style scoped>
a {
  text-decoration: none;
  color: #333;
}

.level-1 {
  font-size: 20px;
  font-weight: bold;
}
.level-2 {
  font-size: 18px;
  font-weight: lighter;
}
.level-3 {
  font-size: 16px;
  font-weight: lighter;
}

.link {
  font-size: 14px;
  font-weight: lighter;
}
.button {
  /*选中最靠近标题的菜单时，避免背景色一致导致视觉上连在一起*/
  margin-bottom: 2px;
}

.link,
.button {
  color: #ffffff;
  display: block;
  padding: 10px 15px;
  transition: background-color 0.2s ease-in-out 0s, color 0.3s ease-in-out 0.1s;
  -moz-user-select: none;
  -webkit-user-select: none;
  -ms-user-select: none;
  -khtml-user-select: none;
  user-select: none;
}
.button {
  position: relative;
}
.link:hover,
.button:hover {
  color: #1976d2;
  /*color: #ffffff;*/
  /*background-color: #eee;*/
  cursor: pointer;
}
.icon {
  position: absolute;
  right: 0;
  display: inline-block;
  height: 24px;
  width: 24px;
  fill: currentColor;
  transition: -webkit-transform 0.15s;
  transition: transform 0.15s;
  transition: transform 0.15s, -webkit-transform 0.15s;
  transition-timing-function: ease-in-out;
}

.heading-children {
  padding-left: 14px;
  overflow: hidden;
}
.expand {
  display: block;
}
.collapsed {
  display: none;
}
.expand .icon {
  -webkit-transform: rotate(90deg);
  transform: rotate(90deg);
}
.selected {
  /*background-color: #0071ff;*/
  color: #0071ff;
  font-weight: bold;
}
.fade-enter-active {
  transition: all 0.5s ease 0s;
}
.fade-enter {
  opacity: 0;
}
.fade-enter-to {
  opacity: 1;
}
.fade-leave-to {
  height: 0;
}

.ats-tree-node {
  max-width: 130px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  display: inline-block;
}
</style>
