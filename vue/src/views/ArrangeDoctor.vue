<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/sectionIndex' }">返回科室</el-breadcrumb-item>
            <el-breadcrumb-item>{{ section }}</el-breadcrumb-item>
        </el-breadcrumb>
        <el-input v-model="query" placeholder="请输入姓名查询" class="doctorInput">
            <el-button slot="append" icon="el-icon-search" @click="requestDoctors"></el-button>
        </el-input>
        <el-table :data="doctorData" border>
            <el-table-column label="账号" prop="dId" v-model="doctorData.dId"></el-table-column>
            <el-table-column label="姓名" prop="dName" v-model="doctorData.dName"></el-table-column>
            <el-table-column label="性别" prop="dGender" v-model="doctorData.dGender"></el-table-column>
            <el-table-column label="职位" prop="dPost" v-model="doctorData.dPost"></el-table-column>
            <el-table-column label="部门" prop="dSection" v-model="doctorData.dSection"></el-table-column>
            <el-table-column label="操作" prop="dSection" width="180" fixed="right">
                <template slot-scope="scope">
                    <el-button v-if="scope.row.arrangeId == null" type="success" style="font-size: 18px"
                        @click="arrangeClick(scope.row.dId)">
                        <i class="iconfont icon-txm-yes" style="font-size: 22px"></i>
                        排班</el-button>
                    <el-button v-if="scope.row.arrangeId != null" type="danger" style="font-size: 18px"
                        @click="deleteArrange(scope.row.arrangeId)">
                        <i class="iconfont icon-txm-del" style="font-size: 22px"></i>
                        取消排班</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页 -->
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" background
            layout="total, sizes, prev, pager, next, jumper" :current-page="pageNumber" :page-size="size"
            :page-sizes="[1, 2, 4, 8, 16]" :total="total">
        </el-pagination>
    </div>
</template>
<script>
import request from "@/utils/request.js";
import { toLoad } from "@/utils/initialize.js";
export default {
    name: "ArrangeDoctor",
    data() {
        return {
            section: this.$route.query.section,
            doctorData: [],
            total: 3,
            pageNumber: 1,
            size: 8,
            query: "",
        };
    },
    methods: {
        //排班点击
        arrangeClick(dId) {
            request
                .get("arrange/addArrange", {
                    params: {
                        arId: dId + sessionStorage.getItem("arrangeDate"),
                        arTime: sessionStorage.getItem("arrangeDate"),
                        dId: dId,
                    },
                })
                .then((res) => {
                    if (res.data.status !== 200)
                        return this.$message.error("已排班");
                    this.$message.success("排班成功！");
                    this.requestDoctors();
                })
                .catch((e) => {
                    if (e.response.data == undefined) {
                        this.$message.error(e);
                    } else {
                        this.$message.error(e.response.data);
                    }
                });
        },
        deleteArrange(arrangeId) {
            request
                .get("arrange/deleteArrange", {
                    params: {
                        arId: arrangeId,
                    },
                })
                .then((res) => {
                    if (res.data.status !== 200)
                        return this.$message.error("排班信息不存在");
                    this.$message.success("删除排班信息成功！");
                    this.requestDoctors();
                });
        },
        //页面大小改变时触发
        handleSizeChange(size) {
            this.size = size;
            this.requestDoctors();
        },
        //   页码改变时触发
        handleCurrentChange(num) {
            console.log(num);
            this.pageNumber = num;
            this.requestDoctors();
        },
        //根据部门请求医生信息
        requestDoctors() {
            request
                .get("doctor/findDoctorBySectionPage", {
                    params: {
                        pageNumber: this.pageNumber,
                        size: this.size,
                        query: this.query,
                        dSection: this.section,
                        arrangeDate: sessionStorage.getItem("arrangeDate"),
                    },
                })
                .then((res) => {
                    console.log(res.data);
                    if (res.data.status !== 200)
                        return this.$message.error("数据请求失败");
                    this.doctorData = res.data.data.doctors;
                    this.total = res.data.data.total;
                    toLoad();
                });
        },
    },
    created() {
        this.requestDoctors();
    },
};
</script>
<style scope lang="scss">
.el-breadcrumb {
    margin-bottom: 10px;
}

.doctorInput {
    width: 30%;
    margin-bottom: 10px;
}
</style>