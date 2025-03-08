<template>
    <!-- 卡片 -->
    <el-card>
        <!-- 搜索栏及增加医生 -->
        <el-row type="flex">
            <el-col :span="8">
                <el-input v-model="query" placeholder="请输入姓名查询">
                    <el-button slot="append" style="font-size: 18px;" @click="requestDoctors"><i
                            class="iconfont icon-txm-find" style="font-size: 18px;"></i> 搜索</el-button>
                </el-input>
            </el-col>
            <el-col :span="4"></el-col>
            <el-col :span="4">
                <el-button type="primary" @click="addFormVisible = true" style="font-size: 18px;">
                    <i class="iconfont icon-txm-add" style="font-size: 18px;"></i>
                    增加医生</el-button>
            </el-col>
            <el-col :span="4"></el-col>
            <!-- 导入数据 -->
            <!-- <el-col :span="2">
                <el-upload
                    class="upload-demo"
                    action="doctor/uploadExcel"
                    accept=".xlsx,.xls "
                    :limit="1"
                    :show-file-list="false"
                    :on-progress="handleProgress"
                    :on-exceed="handleExceed"
                    :on-success="handleSuccess"
                    :on-error="handleError"
                    :file-list="fileList"
                >
                    <el-button size="small" type="success">一键导入</el-button>
                </el-upload>
            </el-col>

            <el-col :span="2">
                <el-button size="small" type="success" @click="exportDoctors"
                    >一键导出</el-button
                ></el-col
            > -->
        </el-row>
        <!-- 表格 -->
        <el-table :data="doctorData" stripe style="width: 100%" border>
            <el-table-column prop="dId" label="账号" width="100">
            </el-table-column>
            <el-table-column prop="dName" label="姓名" width="80">
            </el-table-column>
            <el-table-column prop="dGender" label="性别" width="60">
            </el-table-column>
            <el-table-column prop="dPost" label="职位" width="100">
            </el-table-column>
            <el-table-column prop="dSection" label="科室" width="100">
            </el-table-column>
            <el-table-column prop="dCard" label="证件号">
            </el-table-column>
            <el-table-column prop="dPhone" label="手机号">
            </el-table-column>
            <el-table-column prop="dEmail" label="邮箱" width="170">
            </el-table-column>
            <el-table-column prop="dAvgStar" label="评分/5分" width="80">
            </el-table-column>
            <el-table-column prop="dPrice" label="挂号费/元" width="100">
            </el-table-column>
            <el-table-column prop="dState" label="是否在职" width="80">
                <template slot-scope="scope">
                    <el-tag type="success" v-if="scope.row.dState === 1">在职</el-tag>
                    <el-tag type="danger" v-else>离职</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="240" fixed="right">
                <template slot-scope="scope">
                    <el-button size="mini" style="font-size: 18px;" type="success"
                        @click="modifyDialog(scope.row.dId)"><i class="iconfont icon-txm-edit"
                            style="font-size: 18px;"></i> 编辑</el-button>
                    <el-button size="mini" style="font-size: 18px;" type="danger"
                        @click="deleteDialog(scope.row.dId)"><i class="iconfont icon-txm-del"
                            style="font-size: 18px;"></i> 删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页 -->
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" background
            layout="total, sizes, prev, pager, next, jumper" :current-page="pageNumber" :page-size="size"
            :page-sizes="[1, 2, 4, 8, 16]" :total="total">
        </el-pagination>
        <!-- 增加医生对话框 -->
        <el-dialog title="增加医生" :visible.sync="addFormVisible">
            <el-form :model="addForm" :rules="rules" ref="ruleForm">
                <el-form-item label="账号" label-width="80px" prop="dId">
                    <el-input v-model.number="addForm.dId" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="密码" label-width="80px">
                    <el-input v-model="addForm.dPassword" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="姓名" label-width="80px" prop="dName">
                    <el-input v-model="addForm.dName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="性别" label-width="80px">
                    <el-radio v-model="addForm.dGender" label="男">男</el-radio>
                    <el-radio v-model="addForm.dGender" label="女">女</el-radio>
                </el-form-item>
                <el-form-item label="职位" label-width="80px" prop="dPost">
                    <el-select v-model="addForm.dPost" placeholder="请选择职称">
                        <el-option v-for="post in posts" :key="post" :label="post" :value="post">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="科室" label-width="80px" prop="dSection">
                    <el-select v-model="addForm.dSection" filterable placeholder="请选择科室">
                        <el-option v-for="section in sections" :key="section" :label="section" :value="section">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="身份证号" label-width="80px" prop="dCard">
                    <el-input v-model="addForm.dCard" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="手机号" label-width="80px" prop="dPhone">
                    <el-input v-model="addForm.dPhone" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" label-width="80px" prop="dEmail">
                    <el-input v-model="addForm.dEmail" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="挂号费" label-width="80px" prop="dPrice">
                    <el-input v-model="addForm.dPrice" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="简介" label-width="80px" prop="dIntroduction">
                    <el-input type="textarea" :rows="5" placeholder="请输入内容" v-model="addForm.dIntroduction">
                    </el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addFormVisible = false" style="font-size: 18px;"><i class="iconfont icon-txm-left"
                        style="font-size: 20px;"></i> 取 消</el-button>
                <el-button type="primary" @click="addDoctor('ruleForm')" style="font-size: 18px;"><i
                        class="iconfont icon-txm-yes" style="font-size: 20px;"></i> 确 定</el-button>
            </div>
        </el-dialog>

        <!-- 修改医生对话框 -->
        <el-dialog title="修改医生信息" :visible.sync="modifyFormVisible">
            <el-form :model="modifyForm" :rules="rules" ref="ruleForm">
                <el-form-item label="账号" label-width="80px" prop="dId">
                    <el-input v-model.number="modifyForm.dId" autocomplete="off" disabled></el-input>
                </el-form-item>

                <el-form-item label="姓名" label-width="80px" prop="dName">
                    <el-input v-model="modifyForm.dName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="性别" label-width="80px">
                    <el-radio v-model="modifyForm.dGender" label="男">男</el-radio>
                    <el-radio v-model="modifyForm.dGender" label="女">女</el-radio>
                </el-form-item>
                <el-form-item label="职位" label-width="80px" prop="dPost">
                    <el-select v-model="modifyForm.dPost" placeholder="请选择职称">
                        <el-option v-for="post in posts" :key="post" :label="post" :value="post">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="科室" label-width="80px" prop="dSection">
                    <el-select v-model="modifyForm.dSection" filterable placeholder="请选择科室">
                        <el-option v-for="section in sections" :key="section" :label="section" :value="section">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="身份证号" label-width="80px" prop="dCard">
                    <el-input v-model="modifyForm.dCard" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="手机号" label-width="80px" prop="dPhone">
                    <el-input v-model="modifyForm.dPhone" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" label-width="80px" prop="dEmail">
                    <el-input v-model="modifyForm.dEmail" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="挂号费" label-width="80px" prop="dPrice">
                    <el-input v-model="modifyForm.dPrice" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="简介" label-width="80px" prop="dIntroduction">
                    <el-input type="textarea" :rows="5" placeholder="请输入内容" v-model="modifyForm.dIntroduction">
                    </el-input>
                </el-form-item>
                <el-form-item label="状态" label-width="80px" prop="dState">
                    <el-input v-model="modifyForm.dState" autocomplete="off" disabled></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="modifyFormVisible = false" style="font-size: 18px;"><i class="iconfont icon-txm-left"
                        style="font-size: 20px;"></i> 取 消</el-button>
                <el-button type="primary" @click="modifyDoctor('ruleForm')" style="font-size: 18px;"><i
                        class="iconfont icon-txm-yes" style="font-size: 20px;"></i> 确 定</el-button>
            </div>
        </el-dialog>
    </el-card>
</template>
<script>
import request from "@/utils/request.js";
import { toLoad } from "@/utils/initialize.js";
export default {
    name: "DoctorList",
    data() {
        var validateMoblie = (rule, value, callback) => {
            if (value === undefined) {
                callback(new Error("请输入手机号"));
            } else {
                let reg =
                    /^1(3[0-9]|4[5,7]|5[0,1,2,3,5,6,7,8,9]|6[2,5,6,7]|7[0,1,7,8]|8[0-9]|9[1,8,9])\d{8}$/;
                if (!reg.test(value)) {
                    callback(new Error("请输入合法的手机号"));
                }
                callback();
            }
        };
        var validateCard = (rule, value, callback) => {
            if (value === undefined) {
                callback(new Error("请输入身份证号"));
            } else {
                let reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
                if (!reg.test(value)) {
                    callback(new Error("请输入合法的身份证号码"));
                }
                callback();
            }
        };
        return {
            //文件上传
            fileList: [],
            pageNumber: 1,
            size: 8,
            query: "",
            doctorData: [],
            total: 3,
            addFormVisible: false,
            addForm: {
                dPassword: 123456,
                dGender: "男",
            },
            posts: ["主任医师", "副主任医师", "主治医生"],
            sections: [
                "神经内科",
                "内分泌科",
                "呼吸与危重症医学科",
                "消化内科",
                "心血管内科",
                "发热门诊",
                "手足外科",
                "普通外科",
                "肛肠外科",
                "神经外科",
                "骨科",
                "烧伤整形外科",
                "妇科",
                "产科",
                "儿科",
                "耳鼻咽喉科",
                "眼科",
                "中医科",
                "急诊科",
                "皮肤病科",
                "口腔科",
            ],
            rules: {
                dId: [
                    { required: true, message: "请输入账号", trigger: "blur" },
                    {
                        type: "number",
                        message: "账号必须数字类型",
                        trigger: "blur",
                    },
                ],
                dName: [
                    { required: true, message: "请输入姓名", trigger: "blur" },
                    {
                        min: 2,
                        max: 5,
                        message: "账号必须是2到5个字符",
                        trigger: "blur",
                    },
                ],
                dPost: [
                    { required: true, message: "请选择职位", trigger: "blur" },
                ],
                dSection: [
                    {
                        required: true,
                        message: "请选择所属科室",
                        trigger: "blur",
                    },
                ],
                dEmail: [
                    { required: true, message: "请输入邮箱", trigger: "blur" },
                    {
                        type: "email",
                        message: "请输入正确的邮箱地址",
                        trigger: ["blur", "change"],
                    },
                ],
                dPrice: [
                    {
                        required: true,
                        message: "请输入挂号费",
                        trigger: "blur",
                    },
                ],
                dPhone: [{ validator: validateMoblie }],
                dCard: [{ validator: validateCard }],
                dIntroduction: [
                    {
                        required: true,
                        message: "请输入您的个人简介",
                        trigger: "blur",
                    },
                ],
            },
            modifyFormVisible: false,
            modifyForm: {},
        };
    },
    methods: {
        //导出医生信息
        exportDoctors() {
            window.location.href = "http://localhost:9281/doctor/downloadExcel";
        },
        //文件上传
        handleProgress() {
            this.$message.warning("文件正在解析中！");
        },
        // 文件上传成功时的钩子
        handleSuccess() {
            this.$message.success("数据导入成功！");
            this.requestDoctors();
        },
        handleError() {
            //this.$message.error("数据导入失败！");
            this.$message.success("数据导入成功！");
            this.requestDoctors();
        },
        handleExceed() {
            this.$message.warning("当前限制选择 1 个文件");
        },
        //点击修改医生信息
        modifyDoctor(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    request
                        .get("admin/modifyDoctor", {
                            params: {
                                dId: this.modifyForm.dId,
                                dGender: this.modifyForm.dGender,
                                dName: this.modifyForm.dName,
                                dPost: this.modifyForm.dPost,
                                dSection: this.modifyForm.dSection,
                                dPhone: this.modifyForm.dPhone,
                                dEmail: this.modifyForm.dEmail,
                                dCard: this.modifyForm.dCard,
                                dPrice: this.modifyForm.dPrice,
                                dIntroduction: this.modifyForm.dIntroduction,
                            },
                        })
                        .then((res) => {
                            if (res.data.status !== 200)
                                return this.$message.error("修改信息失败！");
                            this.modifyFormVisible = false;
                            this.requestDoctors();
                            this.$message.success("修改医生信息成功！");
                            console.log(res);
                        });
                } else {
                    console.log("error submit!!");
                    return false;
                }
            });
        },
        //打开修改对话框
        modifyDialog(id) {
            request
                .get("admin/findDoctor", {
                    params: {
                        dId: id,
                    },
                })
                .then((res) => {
                    if (res.data.status !== 200)
                        this.$message.error("请求数据失败");
                    this.modifyForm = res.data.data;
                    this.modifyFormVisible = true;
                    console.log(res);
                });
        },
        //删除医生操作
        deleteDoctor(id) {
            request
                .get("admin/deleteDoctor", {
                    params: {
                        dId: id,
                    },
                })
                .then((res) => {
                    this.requestDoctors();
                    console.log(res);
                });
        },
        //删除对话框
        deleteDialog(id) {
            this.$confirm("此操作将删除该医生信息, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning",
            })
                .then(() => {
                    this.deleteDoctor(id);
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
        // 增加医生
        addDoctor(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    request
                        .get("admin/addDoctor", {
                            params: {
                                dId: this.addForm.dId,
                                dGender: this.addForm.dGender,
                                dPassword: this.addForm.dPassword,
                                dName: this.addForm.dName,
                                dPost: this.addForm.dPost,
                                dSection: this.addForm.dSection,
                                dPhone: this.addForm.dPhone,
                                dEmail: this.addForm.dEmail,
                                dCard: this.addForm.dCard,
                                dPrice: this.addForm.dPrice,
                                dIntroduction: this.addForm.dIntroduction,
                                doctor: this.addForm,
                            },
                        })
                        .then((res) => {
                            if (res.data.status !== 200)
                                return this.$message.error(
                                    "账号不合法或已被占用！"
                                );
                            this.addFormVisible = false;
                            this.requestDoctors();
                            this.$message.success("增加医生成功！");
                            console.log(res);
                        });
                } else {
                    console.log("error submit!!");
                    return false;
                }
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
        // 加载医生列表
        requestDoctors() {
            request
                .get("admin/findAllDoctors", {
                    params: {
                        pageNumber: this.pageNumber,
                        size: this.size,
                        query: this.query,
                    },
                })
                .then((res) => {
                    this.doctorData = res.data.data.doctors;

                    this.total = res.data.data.total;
                    toLoad()
                });
        },
    },
    created() {
        this.requestDoctors();
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