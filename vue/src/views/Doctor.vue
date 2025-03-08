<template>
    <el-container>
        <!-- 头部 -->
        <el-header>
            <div class="words" @click="menuClick('doctorLayout')">
                <img src="@/assets/hospital.png" alt="" srcset="">
                <b style="margin-left: 10px;">医院管理系统</b>
            </div>
            <div class="words">
                <span>欢迎您，<b>{{ userName }}</b>&nbsp;医生&nbsp;</span>
                <span><el-button type="danger" size="mini" @click="logout">退出</el-button></span>
            </div>
        </el-header>
        <el-container>
            <!-- 侧边栏 -->
            <el-aside width="200px">
                <!-- 导航菜单 -->
                <el-menu background-color="white" text-color="grey" active-text-color="black"
                    :default-active="activePath" style="font-size: 22px">
                    <el-menu-item index="doctorLayout" @click="menuClick('doctorLayout')" style="font-size: 16px">
                        <i class="iconfont icon-txm-menu1" style="font-size: 22px">
                        </i>
                        首页
                    </el-menu-item>
                    <el-menu-item index="orderToday" @click="menuClick('orderToday')" style="font-size: 16px">
                        <i class="iconfont icon-txm-today" style="font-size: 22px">
                        </i>
                        今日挂号列表
                    </el-menu-item>
                    <el-menu-item index="doctorOrder" @click="menuClick('doctorOrder')" style="font-size: 16px">
                        <i class="iconfont icon-txm-list" style="font-size: 22px">
                        </i>
                        历史挂号列表
                    </el-menu-item>
                    <el-menu-item index="inBed" @click="menuClick('inBed')" style="font-size: 16px">
                        <i class="iconfont icon-txm-building" style="font-size: 22px">
                        </i>
                        住院申请管理
                    </el-menu-item>
                    <el-menu-item index="doctorCard" @click="menuClick('doctorCard')" style="font-size: 16px">
                        <i class="iconfont icon-txm-yonghu" style="font-size: 22px">
                        </i>
                        个人信息查询
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-main>
                <!-- 子路由入口 -->
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>
<script>
import jwtDecode from "jwt-decode";
import { toLoad } from "@/utils/initialize.js";
import {
    getToken,
    clearToken,
    getActivePath,
    setActivePath,
} from "@/utils/storage.js";
export default {
    name: "Doctor",
    data() {
        return {
            userName: "",
            activePath: "",
        };
    },
    methods: {
        //token解码
        tokenDecode(token) {
            if (token !== null) return jwtDecode(token);
        },
        //导航栏点击事件
        menuClick(path) {
            this.activePath = path;
            setActivePath(path);
            if (this.$route.path !== "/" + path) this.$router.push(path);
            console.log(path);
        },
        //退出登录
        logout() {
            this.$confirm("此操作将退出登录, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning",
            })
                .then(() => {
                    clearToken();
                    this.$message({
                        type: "success",
                        message: "退出登录成功!",
                    });
                    this.$router.push("login");
                })
                .catch(() => {
                    this.$message({
                        type: "info",
                        message: "已取消",
                    });
                });
        },
    },
    created() {
        //  获取激活路径
        this.activePath = getActivePath();
        // 解码token
        this.userName = this.tokenDecode(getToken()).dName;
    },
    mounted() {
        toLoad();
    },
};
</script>
<style scoped lang="scss">
.title {
    cursor: pointer;
}

.el-header {
    background-color: #fff;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid lightgrey;

    .words {
        text-align: center;
        display: flex;
        flex-direction: row;
        align-items: center;
        color: #ca4444;

        span {
            color: black;
            margin-right: 10px;
        }
    }
}

.el-container {
    height: 100%;
}

.el-aside {
    background-color: white;
    border-right: 1px solid lightgrey;
}

.el-menu {
    border: 0;
}
</style>
