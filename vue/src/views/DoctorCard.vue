<template>
    <div style="width: 100%;margin-top: -10px">
        <el-card shadow="hover">
            <table>
                <tr>
                    <td style="">姓名：</td>
                    <td>
                        <el-input
                            disabled
                            v-model="doctorData.dName"
                        ></el-input>
                    </td>
                </tr>
                <tr>
                    <td>账号：</td>
                    <td>
                        <el-input disabled v-model="doctorData.dId"></el-input>
                    </td>
                </tr>
                <tr>
                    <td>性别：</td>
                    <td>
                        <el-input
                            disabled
                            v-model="doctorData.dGender"
                        ></el-input>
                    </td>
                </tr>
                <tr>
                    <td>手机号：</td>
                    <td>
                        <el-input
                            disabled
                            v-model="doctorData.dPhone"
                        ></el-input>
                    </td>
                </tr>
                <tr>
                    <td>身份证号：</td>
                    <td>
                        <el-input
                            disabled
                            v-model="doctorData.dCard"
                        ></el-input>
                    </td>
                </tr>
                <tr>
                    <td>邮箱：</td>
                    <td>
                        <el-input
                            disabled
                            v-model="doctorData.dEmail"
                        ></el-input>
                    </td>
                </tr>
                <tr>
                    <td>职位：</td>
                    <td>
                        <el-input
                            disabled
                            v-model="doctorData.dPost"
                        ></el-input>
                    </td>
                </tr>
                <tr>
                    <td>所属科室：</td>
                    <td>
                        <el-input
                            disabled
                            v-model="doctorData.dSection"
                        ></el-input>
                    </td>
                </tr>
                <tr>
                    <td>挂号价格：</td>
                    <td>
                        <el-input
                            disabled
                            v-model="doctorData.dPrice"
                        ></el-input>
                    </td>
                </tr>
                <tr>
                    <td>评分：</td>
                    <td>
                        <el-input
                            disabled
                            v-model="doctorData.dAvgStar"
                        ></el-input>
                    </td>
                </tr>
                <tr>
                    <td>简介：</td>
                    <td>
                        <el-input
                            disabled
                            v-model="doctorData.dIntroduction"
                            type="textarea"
                            :rows="4"
                        ></el-input>
                    </td>
                </tr>
            </table>
        </el-card>
    </div>
</template>
<script>
import jwtDecode from "jwt-decode";
import { getToken } from "@/utils/storage.js";
import request from "@/utils/request.js";
export default {
    name: "DoctorCard",
    data() {
        return {
            userId: "",
            doctorData: {},
        };
    },
    methods: {
        //请求医生信息
        requestDoctor() {
            request
                .get("admin/findDoctor", {
                    params: {
                        dId: this.userId,
                    },
                })
                .then((res) => {
                    if (res.data.status != 200)
                        return this.$message.error("获取数据失败");
                    this.doctorData = res.data.data;
                });
        },
        //token解码
        tokenDecode(token) {
            if (token !== null) return jwtDecode(token);
        },
    },
    created() {
        //解码token信息
        this.userId = this.tokenDecode(getToken()).dId;
        this.requestDoctor();
        console.log(this.userId);
    },
};
</script>
<style lang="scss" scope>
td, th {
  white-space: nowrap;
  padding: 10px;
}
</style>