<template>
  <div class="search-container-wrapper">
    <div class="search-info" style="height:100%;overflow:auto">
      <div class="search-container">
        <template v-for="(field, index) in fieldInfo">
          <!--查询方式：0不搜索，1模糊搜索，2精确搜索，3下拉搜索，4时间区间搜索，5数字区间，6下拉多选搜索-->
          <template v-if="field.searchType === 1">
            <div class="search-field flex-row ">
              <label class="search-title">{{ field.showName }} : &nbsp</label>
              <span>
                <Input
                  v-model="value[index]"
                  @on-change="changeInput(value[index], index, field)"
                  clearable
                  placeholder="模糊"
                  @on-enter="searchClick"
                ></Input>
              </span>
            </div>
          </template>
          <template v-else-if="field.searchType === 2">
            <div class="search-field flex-row ">
              <label class="search-title">{{ field.showName }} : &nbsp</label>
              <span>
                <Input
                  v-model="value[index]"
                  @on-change="changeInput(value[index], index, field)"
                  clearable
                  placeholder="精确"
                  @on-enter="searchClick"
                ></Input>
              </span>
            </div>
          </template>
          <template v-else-if="field.searchType === 3">
            <div class="search-field flex-row ">
              <label class="search-title">{{ field.showName }} : &nbsp</label>
              <span>
                <Select
                  clearable="clearable"
                  v-model="value[index]"
                  @on-change="changeInput(value[index], index, field)"
                  style="width:200px"
                >
                  <!--<Option :value='1' :key='2'>请选择</Option>-->
                  <Option
                    v-for="item in field.selectParams"
                    :value="item.paramKey"
                    :key="item.paramKey"
                    >{{ item.paramValue }}</Option
                  >
                </Select>
              </span>
            </div>
          </template>
          <template v-else-if="field.searchType === 4">
            <div class="search-field flex-row ">
              <label class="search-title">{{ field.showName }} : &nbsp</label>
              <span class="flex-row">
                <DatePicker
                  type="date"
                  placeholder="开始时间"
                  style="width: 108px"
                  v-model="value[index]"
                  @on-change="changeInput(value[index], index, field)"
                ></DatePicker>
                -
                <DatePicker
                  type="date"
                  placeholder="结束时间"
                  style="width: 108px"
                  v-model="multipleValue[index]"
                  @on-change="
                    changeMultipleInput(multipleValue[index], index, field)
                  "
                ></DatePicker>
              </span>
            </div>
          </template>
          <template v-else-if="field.searchType === 5">
            <div class="search-field flex-row ">
              <label class="search-title">{{ field.showName }} : &nbsp</label>
              <span flex-row>
                <Input
                  @on-enter="searchClick"
                  v-model="value[index]"
                  style="width: 93px"
                  @on-change="changeInput(value[index], index, field)"
                  clearable
                  placeholder="开始"
                ></Input>
                -
                <Input
                  @on-enter="searchClick"
                  v-model="multipleValue[index]"
                  style="width: 93px"
                  @on-change="
                    changeMultipleInput(multipleValue[index], index, field)
                  "
                  clearable
                  placeholder="结束"
                ></Input>
              </span>
            </div>
          </template>
          <template v-else-if="field.searchType === 6">
            <div class="search-field flex-row ">
              <label class="search-title">{{ field.showName }} : &nbsp</label>
              <span>
                <Select
                  clearable="clearable"
                  multiple
                  v-model="value[index]"
                  @on-change="changeInput(value[index], index, field)"
                  style="width:200px"
                >
                  <!--<Option :value='1' :key='2'>请选择</Option>-->
                  <Option
                    v-for="item in field.selectParams"
                    :value="item.paramKey"
                    :key="item.paramKey"
                    >{{ item.paramValue }}</Option
                  >
                </Select>
              </span>
            </div>
          </template>
          <template v-else>
            <!--nothing-->
          </template>
        </template>
      </div>
    </div>
    <div class="btn-wrapper">
      <div class="custom-btn-container">
        <div class="custom-btn">
          <a>新增</a>
        </div>
        <div class="custom-btn">
          <a>上传</a>
        </div>
        <div class="custom-btn">
          <a>导入</a>
        </div>
        <div class="custom-btn">
          <a>导出</a>
        </div>
      </div>
      <div class="search-btn-container">
        <Button type="primary" icon="ios-search" v-on:click="searchClick"
          >查询</Button
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "search-info",
  data() {
    return {
      clearable: true,
      value: [],
      multipleValue: [],
      searchCondArr: [],
      multipleCondArr: [],
      dateArray: [] // 日期控件不支持v-model绑定，只能用on-change事件，再传到这个值上面
    };
  },
  props: {
    fieldInfo: Array,
    selectArrMap: Map
  },
  methods: {
    changeInput(val, index, field) {
      if (val === "" || val === undefined || val === null) {
        this.searchCondArr[index] = null;
        return;
      }

      var moreOrLessType = "";
      // 如果是按照时间搜索
      if (field.searchType === 4) {
        moreOrLessType = 1;
        //格式化时间，iview的时间控件必须用onchange事件才能返回yyyy-mm-dd格式，这样就不好传其他参数，折中一下做格式转换，下同
        val = this.formatDate(val);
      } else if (field.searchType === 5) {
        moreOrLessType = 1;
      } else if (field.searchType === 6) {
        var arr = new Array();
        arr = val;
        // 强下拉多选
        var str = "";
        arr.forEach(function(item, index) {
          if (index === arr.length - 1) {
            str += item;
          } else {
            str += item + ",";
          }
        });

        val = str;
      }

      var searchParam = new Object();
      searchParam.type = field.searchType;
      searchParam.fieldName = field.fieldName;
      searchParam.moreOrLessType = moreOrLessType;
      searchParam.value = val;
      this.searchCondArr[index] = searchParam;
    },
    changeMultipleInput(val, index, field) {
      if (field.searchType === 4) {
        val = this.formatDate(val);
      }
      if (val === "" || val === undefined || val === null) {
        this.multipleCondArr[index] = null;
        return;
      }
      var searchParam = new Object();
      searchParam.type = field.searchType;
      searchParam.fieldName = field.fieldName;
      searchParam.moreOrLessType = 2;
      searchParam.value = val;
      this.multipleCondArr[index] = searchParam;
    },
    // 点击查询按钮，pageSize传0，会默认当前pageSize，默认第一页
    searchClick: function(event) {
      var searchInfo = this.searchCondArr.concat(this.multipleCondArr);

      this.$emit("freshTableDataByCondition", searchInfo, 0, 1, "", "");
    },
    formatDate(date) {
      if (date === "" || date === undefined || date === null) {
        return "";
      }
      var y = date.getFullYear();
      var m = date.getMonth() + 1;
      m = m < 10 ? "0" + m : m;
      var d = date.getDate();
      d = d < 10 ? "0" + d : d;
      return y + "-" + m + "-" + d;
    },
    clearSearchInfo() {
      //清空搜索信息
      this.multipleValue = [];
      this.value = [];
      this.searchCondArr = [];
      this.multipleCondArr = [];
      this.dateArray = [];
    }
  }
};
</script>

<style scoped>
.search-container-wrapper {
  display: flex;
  flex-direction: column;
  width: 100%;
}

.search-info {
  width: 100%;
  border: 1px solid #dddee1;
  border-bottom: 0px;
  border-radius: 3px;
  /*上右下左*/
  padding: 20px 20px 20px 20px;
}

.search-title {
  text-align: right;
  width: 30%;
  white-space: nowrap;
}

.flex-row {
  display: flex;
  flex-direction: row;
}

.search-container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: flex-start;
  height: 100px;
}

.search-field {
  float: left;
  margin-left: 20px;
  width: 30%;
  margin-bottom: 8px;
}

.search-field label {
  float: left;
  line-height: 32px;
}

.search-field span {
  float: left;
}

.btn-wrapper {
  display: -webkit-flex;
  align-items: center;
  display: flex;
  flex-direction: row;
  height: 55px;
  border-bottom: 1px solid #eee;
  border-right: 1px solid #dddee1;
}

.custom-btn-container {
  display: flex;
  flex-direction: row;
  align-items: center; /* 垂直居中 */
  height: 100%;
  width: 90%;
  text-align: left;
  padding-left: 5px;
  border-top: 1px solid #dddee1;
  padding-top: 3px;
}
.custom-btn {
  margin-right: 10px;
}

.search-btn-container {
  width: 10%;
  text-align: center;
}
</style>
