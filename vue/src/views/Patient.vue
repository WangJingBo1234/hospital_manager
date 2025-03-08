<template>
    <el-container>
        <!-- 头部 -->
        <el-header>
            <div class="words" @click="menuClick('patientLayout')">
                <img src="@/assets/hospital.png" alt="" srcset="">
                <b style="margin-left: 10px;">医院管理系统</b>
            </div>
            <div class="words">
                <span>欢迎您，<b>{{ userName }}</b>&nbsp;</span>
                <span><el-button type="danger" size="mini" @click="logout">退出</el-button></span>
            </div>
        </el-header>
        <el-container>
            <!-- 侧边栏 -->
            <el-aside width="200px">
                <!-- 导航菜单 -->
                <el-menu background-color="white" text-color="grey" active-text-color="black"
                    :default-active="activePath" style="font-size: 22px">
                    <el-menu-item index="patientLayout" @click="menuClick('patientLayout')" style="font-size: 16px">
                        <i class="iconfont icon-txm-menu1" style="font-size: 22px">
                        </i>
                        首页
                    </el-menu-item>
                    <el-menu-item index="orderOperate" @click="menuClick('orderOperate')" style="font-size: 16px">
                        <i class="iconfont icon-txm-building" style="font-size: 22px">
                        </i>
                        预约挂号
                    </el-menu-item>
                    <el-menu-item index="myOrder" @click="menuClick('myOrder')" style="font-size: 16px">
                        <i class="iconfont icon-txm-hao" style="font-size: 22px">
                        </i>
                        我的挂号
                    </el-menu-item>
                    <el-menu-item index="myBed" @click="menuClick('myBed')" style="font-size: 16px">
                        <i class="iconfont icon-txm-menu7" style="font-size: 22px">
                        </i>
                        住院信息
                    </el-menu-item>
                    <el-menu-item index="patientCard" style="font-size: 16px" @click="menuClick('patientCard')">
                        <i class="iconfont icon-txm-yonghu" style="font-size: 22px"></i>
                        个人信息
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
    name: "Patient",
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
    mounted() {
        toLoad();
    },
    created() {
        //  获取激活路径
        this.activePath = getActivePath();
        // 解码token
        this.userName = this.tokenDecode(getToken()).pName;
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
