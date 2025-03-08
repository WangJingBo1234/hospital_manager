<template>
    <div>
        <el-card>
            <el-table :data="orderData" stripe style="width: 100%" border>
                <el-table-column prop="oId" label="挂号单号" width="75px"></el-table-column>
                <el-table-column prop="pId" label="本人id" width="75px"></el-table-column>
                <el-table-column prop="pName" label="姓名" width="75px"></el-table-column>
                <el-table-column prop="dId" label="医生id" width="75px"></el-table-column>
                <el-table-column prop="dName" label="医生姓名" width="75px"></el-table-column>

                <el-table-column prop="oStart" label="挂号时间" width="195px"></el-table-column>
                <el-table-column prop="oEnd" label="结束时间" width="185px"></el-table-column>
                <el-table-column prop="oTotalPrice" label="需交费用/元" width="80px"></el-table-column>
                <el-table-column prop="oPriceState" label="缴费状态" width="150">
                    <template slot-scope="scope">
                        <el-tag type="success" v-if="scope.row.oPriceState === 1">已缴费</el-tag>
                        <!-- <el-tag type="danger" v-if="scope.row.oPriceState === 0 && scope.row.oState === 1">未缴费</el-tag> -->
                        <el-button type="warning" icon="iconfont icon-txm-mark1" style="font-size: 14px" v-if="
                            scope.row.oPriceState === 0 &&
                            scope.row.oState === 1
                        " @click="priceClick(scope.row.oId, scope.row.dId)">
                            点击缴费</el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="oState" label="挂号状态" width="100px">
                    <template slot-scope="scope">
                        <el-tag type="success" v-if="
                            scope.row.oState === 1 &&
                            scope.row.oPriceState === 1
                        ">已完成</el-tag>
                        <el-tag type="danger" v-if="
                            scope.row.oState === 0 && scope.row.oState === 0
                        ">未完成</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="报告单">
                    <template slot-scope="scope">
                        <el-button type="success" icon="iconfont icon-txm-find" style="font-size: 14px"
                            @click="seeReport(scope.row.oId)" v-if="
                                scope.row.oState === 1 &&
                                scope.row.oPriceState === 1
                            "> 查看</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
        <!-- 评价对话框 -->
        <el-dialog title="用户评价" :visible.sync="starVisible">
            <div>
                <h3>
                    请对工号：{{ dId }}&nbsp;医生：{{ dName }}&nbsp;进行评价
                </h3>
            </div>
            <div>
                <el-rate v-model="star" show-text> </el-rate>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button @click="starVisible = false" style="font-size: 18px;"><i class="iconfont icon-txm-left"
                        style="font-size: 20px;"></i> 取 消</el-button>
                <el-button type="primary" @click="starClick" style="font-size: 18px;"><i class="iconfont icon-txm-yes"
                        style="font-size: 20px;"></i> 确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
import request from "@/utils/request.js";
import jwtDecode from "jwt-decode";
import { getToken } from "@/utils/storage.js";
export default {
    name: "MyOrder",
    data() {
        return {
            userId: 1,
            orderData: [],
            star: 5,
            starVisible: false,
            dId: 1,
            dName: "",
        };
    },
    methods: {
        //评价点击确认
        starClick() {
            console.log(this.star);
            console.log(this.dId);
            request
                .get("doctor/updateStar", {
                    params: {
                        dId: this.dId,
                        dStar: this.star,
                    },
                })
                .then((res) => {
                    if (res.data.status !== 200)
                        return this.$message.error("评价失败");
                    this.$message.success("谢谢您的评价");
                    this.starVisible = false;
                });
        },
        //查看报告单
        seeReport(id) {
            window.location.href =
                "http://localhost:9282/patient/pdf?oId=" + id;
        },
        //点击缴费按钮
        priceClick(oId, dId) {
            request
                .get("order/updatePrice", {
                    params: {
                        oId: oId,
                    },
                })
                .then((res) => {
                    if (res.data.status !== 200) {
                        this.$message.error("请求数据失败");
                        return;
                    }
                    this.$message.success("单号 " + oId + " 缴费成功！");
                    request
                        .get("admin/findDoctor", {
                            params: {
                                dId: dId,
                            },
                        })
                        .then((res) => {
                            if (res.data.status !== 200)
                                return this.$message.error("请求数据失败");
                            this.dId = res.data.data.dId;
                            this.dName = res.data.data.dName;
                        });
                    this.starVisible = true;
                    this.requestOrder();
                });
        },
        //请求挂号信息
        requestOrder() {
            request
                .get("patient/findOrderByPid", {
                    params: {
                        pId: this.userId,
                    },
                })
                .then((res) => {
                    if (res.data.status !== 200)
                        this.$message.error("请求数据失败");
                    this.orderData = res.data.data;
                    //this.orderData.dSection = res.data.data.map(item => item.doctor.dSection);
                    //console.log(res.data.data.map(item => item.doctor.dSection));
                    console.log(this.orderData.oId);
                    console.log(this.orderData.pName);
                    console.log(res);
                });
        },
        //token解码
        tokenDecode(token) {
            if (token !== null) return jwtDecode(token);
        },
    },
    created() {
        // 解码token
        //this.orderData.pName = this.tokenDecode(getToken()).pName;
        //this.orderData.pCard = this.tokenDecode(getToken()).pCard;
        this.userId = this.tokenDecode(getToken()).pId;
        console.log(this.orderData.pName);
        //this.orderData.pName = "dasda"
        this.requestOrder();
    },
};
</script>
<style lang="scss" scoped>
.el-dialog div {
    text-align: center;
    margin-bottom: 8px;
}
</style>