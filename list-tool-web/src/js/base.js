import { Loading } from "element-ui";
import { Message } from "element-ui";

export default {
  install(Vue) {
    Vue.prototype.LOCAL_AUTH_KEY = "LOCAL_AUTH_KEY";

    Vue.prototype.silkRouter = "/silk";
    Vue.prototype.iusRouter = "/ius";
    Vue.prototype.csbRouter = "/csb";

    /**
     * 统一get请求
     * @param url
     * @param params
     * @param loadParams
     * @returns {Promise<any>}
     */
    Vue.prototype.httpGet = function(url, params, useLoading) {
      let loadingInstance;
      //默认使用loading
      if (
        useLoading === undefined ||
        useLoading === null ||
        useLoading === true
      ) {
        loadingInstance = Loading.service({
          fullscreen: true,
          text: "正在加载..."
        });
      }
      return new Promise((resolve, reject) => {
        this.axios
          .get(url, {
            headers: {
              token: this.getLocalToken()
            },
            params: params
          })
          .then(response => {
            if (response.data.code === 2) {
              alert("请登录后操作");
              location.href = "/auth.html/#/login";
            } else if (response.data.code === 0) {
              resolve(response);
            } else if (response.data.code === 1) {
              this.errorNotice(response.data.value);
            }
            closeLoadingInstance(loadingInstance);
          })
          .catch(err => {
            reject(err);
            closeLoadingInstance(loadingInstance);
          });
      });
    };

    /**
     * 统一post请求
     * @param url
     * @param params
     * @param loadParams
     * @returns {Promise<any>}
     */
    Vue.prototype.httpPost = function(url, data, useLoading) {
      let loadingInstance;
      //默认使用loading
      if (
        useLoading === undefined ||
        useLoading === null ||
        useLoading === true
      ) {
        loadingInstance = Loading.service({
          fullscreen: true,
          text: "正在加载..."
        });
      }
      return new Promise((resolve, reject) => {
        this.axios({
          method: "post",
          url: url,
          headers: {
            "Content-type": "application/json;charset=UTF-8",
            token: this.getLocalToken()
          },
          data: data
        })
          .then(response => {
            if (response.data.code === 2) {
              alert("请登录后操作");
              location.href = "/auth.html/#/login";
            } else if (response.data.code === 0) {
              resolve(response);
            } else if (response.data.code === 1) {
              this.errorNotice(response.data.value);
            }
            closeLoadingInstance(loadingInstance);
          })
          .catch(err => {
            reject(err);
            closeLoadingInstance(loadingInstance);
          });
      });
    };

    /**
     * 关闭Loading
     * @param loadingInstance
     * @returns {boolean}
     */
    function closeLoadingInstance(loadingInstance) {
      if (loadingInstance === null || loadingInstance === undefined) {
        return false;
      }
      loadingInstance.close();
    }

    /**
     * 根据url参数名取参数值
     *
     * @param name
     * @returns {string}
     */
    Vue.prototype.getQueryString = function(name) {
      const reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
      const r = window.location.search.substr(1).match(reg);
      if (r != null) {
        return unescape(r[2]);
      }
      return "";
    };

    /**
     * 提示信息
     * @param value
     */
    Vue.prototype.successNotice = function(value) {
      Message.success({
        message: value,
        duration: 2000,
        center: true
      });
    };
    Vue.prototype.errorNotice = function(value) {
      Message.error({
        message: value,
        duration: 5000,
        showClose: true,
        center: true
      });
    };
    Vue.prototype.warningNotice = function(value) {
      Message.warning({
        message: value,
        duration: 5000,
        showClose: true,
        center: true
      });
    };
    Vue.prototype.infoNotice = function(value) {
      Message.info({
        message: value,
        duration: 3000,
        center: true
      });
    };
    /**
     * 获取token值
     *
     * @returns {*}
     */
    Vue.prototype.getLocalToken = function() {
      if (
        localStorage.getItem(this.LOCAL_AUTH_KEY) === null ||
        localStorage.getItem(this.LOCAL_AUTH_KEY) === undefined
      ) {
        return "";
      }
      return JSON.parse(localStorage.getItem(this.LOCAL_AUTH_KEY)).token;
    };

    /**
     * 获取当前登录用户名
     * @returns {*}
     */
    Vue.prototype.getLoginUser = function() {
      if (
        localStorage.getItem(this.LOCAL_AUTH_KEY) === null ||
        localStorage.getItem(this.LOCAL_AUTH_KEY) === undefined
      ) {
        return "";
      }
      return JSON.parse(localStorage.getItem(this.LOCAL_AUTH_KEY)).username;
    };

    /**
     * 登出
     */
    Vue.prototype.logout = function() {
      localStorage.removeItem(this.LOCAL_AUTH_KEY);
    };
  }
};
