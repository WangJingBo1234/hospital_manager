<template>
    <div>
        <el-card>
            <el-table :data="bedData" border stripe>
                <el-table-column label="床号" prop="bId" v-model="bedData.bId"></el-table-column>
                <el-table-column label="用户id" prop="pId" v-model="bedData.pId"></el-table-column>
                <el-table-column label="医生id" prop="dId" v-model="bedData.dId"></el-table-column>
                <el-table-column label="原因" prop="bReason" v-model="bedData.bReason"></el-table-column>
                <el-table-column label="开始时间" prop="bStart" v-model="bedData.bStart"></el-table-column>
            </el-table>
        </el-card>
    </div>
</template>
<script>
import jwtDecode from "jwt-decode";
import request from "@/utils/request.js";
import { getToken } from "@/utils/storage.js";
export default {
    name: "MyBed",
    data() {
        return {
            bedData:[],
            userId:1,
        }
    },
    methods: {
        //请求病床信息
        requestBed(){
            request.get("bed/findBedByPid", {
                params: {
                    pId: this.userId
                }
            })
            .then(res => {
                if(res.data.status !== 200)
                return this.$message.error("请求数据失败");
                this.bedData = res.data.data;
            })

        },
           //token解码
    tokenDecode(token){
      if (token !== null)
      return jwtDecode(token);
    },

    },
    created(){
           // 解码token
            this.userId = this.tokenDecode(getToken()).pId;
            this.requestBed();
    }
}
</script>