<template>
    <div>
        <el-card>
            <!-- 搜索栏 -->
            <el-row type="flex">
                <el-col :span="6">
                    <el-input v-model="query" placeholder="请输入患者id查询">
                        <el-button slot="append" style="font-size: 18px;" @click="requestOrders"><i
                                class="iconfont icon-txm-find" style="font-size: 18px;"></i> 查询</el-button>
                    </el-input>
                </el-col>
            </el-row>
            <el-table :data="orderData" stripe style="width: 100%" border>
                <el-table-column prop="oId" label="挂号单号" width="80px"></el-table-column>
                <el-table-column prop="dId" label="本人id" width="80px"></el-table-column>

                <el-table-column prop="pId" label="患者id" width="100px">
                </el-table-column>

                <el-table-column prop="oStart" label="挂号时间" width="190px"></el-table-column>
                <el-table-column prop="oEnd" label="结束时间" width="180px"></el-table-column>
                <el-table-column prop="oRecord" label="病因" width="400px"></el-table-column>
                <el-table-column prop="oDrug" label="药物" width="180px"></el-table-column>
                <el-table-column prop="oCheck" label="检查项目" width="180px"></el-table-column>
                <el-table-column prop="oTotalPrice" label="需交费用/元" width="80px"></el-table-column>
                <el-table-column prop="oPriceState" label="缴费状态" width="100px">
                    <template slot-scope="scope">
                        <el-tag type="success" v-if="scope.row.oPriceState === 1">已缴费</el-tag>
                        <!-- <el-tag type="danger" v-if="scope.row.oPriceState === 0 && scope.row.oState === 1">未缴费</el-tag> -->
                        <el-tag type="danger" v-if="
                            scope.row.oPriceState === 0 &&
                            scope.row.oState === 1
                        ">未缴费</el-tag>
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
                <el-table-column label="操作" width="140" fixed="right">
                    <template slot-scope="scope">
                        <el-button type="warning" style="font-size: 18px"
                            @click="dealClick(scope.row.oId, scope.row.pId)" v-if="
                                scope.row.oState === 1 &&
                                scope.row.oPriceState === 1
                            "><i class="iconfont icon-txm-yes" style="font-size: 18px;"></i> 追诊</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <!-- 分页 -->
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" background
                layout="total, sizes, prev, pager, next, jumper" :current-page="pageNumber" :page-size="size"
                :page-sizes="[1, 2, 4, 8, 16]" :total="total">
            </el-pagination>
        </el-card>
    </div>
</template>
<script>
import request from "@/utils/request.js";
import jwtDecode from "jwt-decode";
import { toLoad } from "@/utils/initialize.js";
import { getToken } from "@/utils/storage.js";
export default {
    name: "DoctorOrder",
    data() {
        return {
            userId: 1,
            orderData: [],
            pageNumber: 1,
            size: 8,
            query: "",
            total: 3,
        };
    },
    methods: {
        //点击追诊按钮
        dealClick(oId, pId) {
            this.$router.push({
                path: "/dealOrderAgain",
                query: {
                    oId: oId,
                    pId: pId,
                },
            });
        },
        //页面大小改变时触发
        handleSizeChange(size) {
            console.log(size);
            this.size = size;
            this.requestOrders();
        },
        //   页码改变时触发
        handleCurrentChange(num) {
            console.log(num);
            this.pageNumber = num;
            this.requestOrders();
        },

        //请求挂号信息
        requestOrders() {
            request
                .get("order/findOrderByDid", {
                    params: {
                        dId: this.userId,
                        pageNumber: this.pageNumber,
                        size: this.size,
                        query: this.query,
                    },
                })
                .then((res) => {
                    toLoad()
                    if (res.data.status !== 200)
                        this.$message.error("请求数据失败");
                    this.orderData = res.data.data.orders;
                    this.total = res.data.data.total;

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
        this.userId = this.tokenDecode(getToken()).dId;
        console.log(this.orderData.pName);
        //this.orderData.pName = "dasda"
        this.requestOrders();
    },
};
</script>
<style lang="scss" scoped>
.el-table {
    margin-top: 20px;
    margin-bottom: 20px;
}
</style>