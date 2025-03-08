<template>
    <el-card>
        <el-table :data="orderData" stripe border>
            <el-table-column label="序号" type="index" width="50">
            </el-table-column>
            <el-table-column label="挂号单号" prop="oId"></el-table-column>
            <el-table-column label="患者id" prop="pId"></el-table-column>
            <el-table-column label="患者姓名" prop="pName"></el-table-column>
            <el-table-column label="医生姓名" prop="dName"></el-table-column>
            <el-table-column label="挂号时间" prop="oStart" width="200px"></el-table-column>
            <el-table-column label="操作" fixed="right">
                <template slot-scope="scope">
                    <el-button type="warning" style="font-size: 18px" @click="dealClick(scope.row.oId, scope.row.pId)">
                        <i class="iconfont icon-txm-love" style="font-size: 22px"></i>
                        处理
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-card>
</template>
<script>
import jwtDecode from "jwt-decode";
import { getToken } from "@/utils/storage.js";
import { toLoad } from "@/utils/initialize.js";
import request from "@/utils/request.js";
export default {
    name: "orderToday",
    data() {
        return {
            userId: 1,
            userName: "dada",
            today: "",

            orderData: [],
        };
    },
    methods: {
        //挂号处理//页面跳转传值
        dealClick(oId, pId) {
            this.$router.push({
                path: "/dealOrder",
                query: {
                    oId: oId,
                    pId: pId,
                },
            });
        },
        //获取挂号信息
        requestOrder() {
            console.log(this.today)
            request
                .get("doctor/findOrderByNull", {
                    params: {
                        dId: this.userId,
                        oStart: this.today,
                    },
                })
                .then((res) => {
                    toLoad();
                    if (res.data.status != 200)
                        return this.$message.error("获取数据失败");
                    this.orderData = res.data.data;
                });
        },
        //token解码
        tokenDecode(token) {
            if (token !== null) return jwtDecode(token);
        },
        //获取当天日期
        nowDay() {
            const nowDate = new Date();
            let date = {
                year: nowDate.getFullYear(),
                month: nowDate.getMonth() + 1,
                date: nowDate.getDate(),
            };
            if (date.date < 10) {
                date.date = "0" + date.date;
            }
            if (date.month < 10) {
                date.month = "0" + date.month;
            }
            this.today = date.year + "-" + date.month + "-" + date.date;
        },
    },
    created() {
        //解码token信息
        this.userId = this.tokenDecode(getToken()).dId;
        this.userName = this.tokenDecode(getToken()).dName;
        console.log(this.userId);
        console.log(this.userName);
        //获取当天日期
        this.nowDay();
        //获取订单信息
        this.requestOrder();
    },
};
</script>