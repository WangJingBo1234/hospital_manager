<!--
 /*
 * @Description: 
 * @Author: tangxm
 * @Date: 2024-04-08 20:02:29
 */
-->
<template>
    <div>
        <div class="indexPeople">
            <div class="userImage">
                <i class="iconfont icon-txm-see" style="font-size: 132px"></i>
            </div>

            <div class="userFont">
                <div class="spanCure">
                    <span>就诊概况</span>
                </div>
                <div class="spanPeople">
                    <span>今天我的预约人数：{{ orderPeople }}</span>
                </div>
            </div>
        </div>

        <el-row>
            <el-col :span="24">
                <img src="@/assets/back.jpg" style="width: 941px;margin-left: 25px;">
            </el-col>
        </el-row>
    </div>
</template>
<script>
import request from "@/utils/request.js";
import jwtDecode from "jwt-decode";
import { getToken } from "@/utils/storage.js";
import { toLoad } from "@/utils/initialize.js";
export default {
    name: "DoctorLayout",
    data() {
        return {
            userId: 1,
            orderPeople: 1,
        };
    },
    methods: {
        //token解码
        tokenDecode(token) {
            if (token !== null) return jwtDecode(token);
        },
        requestPeople() {
            request
                .get("order/orderPeopleByDid", {
                    params: {
                        dId: this.userId,
                    },
                })
                .then((res) => {
                    if (res.data.status !== 200)
                        return this.$message.error("数据请求失败");
                    this.orderPeople = res.data.data;
                });
        },
    },
    created() {
        this.userId = this.tokenDecode(getToken()).dId;
        this.requestPeople();
    },
    mounted() {
        toLoad()
    }
};
</script>
<style lang="scss" scoped>
.userFont {
    height: 150px;
    width: 250px;
    color: white;
    float: right;

    .spanCure {
        font-size: 15px;
        margin-top: 60px;
        margin-bottom: 15px;
    }

    .spanPeople {
        font-size: 18px;
    }
}

.userImage {
    height: 150px;
    width: 150px;
    font-size: 130px;
    color: white;
    float: left;
    position: relative;
    left: 40px;
    top: 10px;
}

.indexPeople {
    height: 200px;
    width: 500px;
    background: #58b9ae;
    margin-top: 10px;
    margin-left: 30px;
}
</style>