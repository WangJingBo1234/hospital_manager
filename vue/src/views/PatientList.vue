<template>
    <!-- 卡片 -->
    <el-card>
        <!-- 搜索栏 -->
        <el-row type="flex">
            <el-col :span="6">
                <el-input v-model="query" placeholder="请输入姓名查询">
                    <el-button slot="append" style="font-size: 18px;" @click="requestPatients"><i
                            class="iconfont icon-txm-find" style="font-size: 18px;"></i> 搜索</el-button>
                </el-input>
            </el-col>
        </el-row>
        <!-- 表格 -->
        <el-table :data="patientData" stripe style="width: 100%" border>
            <el-table-column prop="pId" label="账号" width="100">
            </el-table-column>
            <el-table-column prop="pName" label="姓名" width="80">
            </el-table-column>
            <el-table-column prop="pGender" label="性别" width="60">
            </el-table-column>
            <el-table-column prop="pAge" label="年龄/岁" width="180">
            </el-table-column>
            <el-table-column prop="pCard" label="证件号"> </el-table-column>
            <el-table-column prop="pPhone" label="手机号"> </el-table-column>
            <el-table-column prop="pEmail" label="邮箱" width="170">
            </el-table-column>
            <el-table-column prop="pState" label="状态" width="80">
                <template slot-scope="scope">
                    <el-tag type="success" v-if="scope.row.pState === 1">正常</el-tag>
                    <el-tag type="danger" v-else>已删除</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="160" fixed="right">
                <template slot-scope="scope">
                    <el-button style="font-size: 16px" size='mini' type="danger" @click="deleteDialog(scope.row.pId)"><i
                            class="iconfont icon-txm-del" style="font-size: 18px;"></i> 删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页 -->
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" background
            layout="total, sizes, prev, pager, next, jumper" :current-page="pageNumber" :page-size="size"
            :page-sizes="[1, 2, 4, 8, 16]" :total="total">
        </el-pagination>
    </el-card>
</template>
<script>
import request from "@/utils/request.js";
import { toLoad } from "@/utils/initialize.js";
export default {
    name: "PatientList",
    data() {
        return {
            pageNumber: 1,
            size: 8,
            query: "",
            patientData: [],
            total: 3,
        };
    },
    methods: {
        //删除病人操作
        deletePatient(id) {
            request
                .get("admin/deletePatient", {
                    params: {
                        pId: id,
                    },
                })
                .then((res) => {
                    this.requestPatients();
                    console.log(res);
                });
        },
        //删除对话框
        deleteDialog(id) {
            this.$confirm("此操作将删除该患者信息, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning",
            })
                .then(() => {
                    this.deletePatient(id);
                    this.$message({
                        type: "success",
                        message: "删除成功!",
                    });
                })
                .catch(() => {
                    this.$message({
                        type: "info",
                        message: "已取消删除",
                    });
                });
        },
        //页面大小改变时触发
        handleSizeChange(size) {
            this.size = size;
            this.requestPatients();
        },
        //   页码改变时触发
        handleCurrentChange(num) {
            console.log(num);
            this.pageNumber = num;
            this.requestPatients();
        },
        // 加载患者列表
        requestPatients() {
            request
                .get("admin/findAllPatients", {
                    params: {
                        pageNumber: this.pageNumber,
                        size: this.size,
                        query: this.query,
                    },
                })
                .then((res) => {
                    this.patientData = res.data.data.patients;

                    this.total = res.data.data.total;
                    toLoad()
                });
        },
    },
    created() {
        this.requestPatients();
    },
};
</script>
<style scoped lang="scss">
.el-table {
    margin-top: 20px;
    margin-bottom: 20px;
}

.el-form {
    margin-top: 0;
}
</style>