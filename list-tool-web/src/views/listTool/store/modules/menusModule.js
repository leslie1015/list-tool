
let menus = toArray();

function toArray() {
    var obj =  sessionStorage.getItem("menuJson");
    return JSON.parse(obj);
}


let resourceLevelNum = 1;
let startExpand = []; // 保存刷新后当前要展开的菜单项
function setExpand(source, resourceUrl) {

    let sourceItem = '';
    for (let i = 0; i < source.length; i++) {
        sourceItem = JSON.stringify(source[i]); // 把菜单项转为字符串
        // 查找当前 resourceUrl 所对应的子菜单属于哪一个祖先菜单，并且初始路径不为'/'，否则会把菜单第一个选然后选中并展开
        if (sourceItem.indexOf(resourceUrl) > -1 && resourceUrl != '/') {
            // debugger;
            if (source[i].resourceType === 'button') { // 导航菜单为按钮
                source[i].selected = true; // 设置选中高亮
                source[i].expanded = true; // 设置为展开
                startExpand.push(source[i]);
                // 递归下一级菜单，以此类推
                setExpand(source[i].childQueues, resourceUrl);
            }
            break;
        }
    }
}

const state = {
    menus,
    resourceLevelNum
};
const mutations = {

    findParents(state, payload) {
        if (payload.menu.resourceType === "button") {
            payload.menu.expanded = !payload.menu.expanded;
        } else if (payload.menu.resourceType === "link") {
            if (startExpand.length > 0) {
                for (let i = 0; i < startExpand.length; i++) {
                    startExpand[i].selected = false;
                }
            }
            startExpand = []; // 清空展开菜单记录项
            setExpand(state.menus, payload.menu.resourceUrl);
        };
    },
    firstInit(state, payload) {
        setExpand(state.menus, payload.url);
    },
    // 初始化state中的菜单信息
    initStateMenus(state, payload) {
        state.menus = payload.menus;
    }
}
export default {
    state,
    mutations
};

