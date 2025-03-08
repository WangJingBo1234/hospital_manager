<template>
  <div>
    <el-card>
      <el-row>
        <el-col :span="7">
          挂号单号：<el-input disabled v-model="oId" class="orderInput"></el-input>
        </el-col>
        <el-col :span="7">
          患者账号：<el-input disabled v-model="pId" class="orderInput"></el-input></el-col>
        <el-col :span="7">
          患者姓名：<el-input disabled v-model="pName" class="orderInput"></el-input>
        </el-col>
        <el-col :span="3">
          <el-button type="success" @click="submitClick" style="font-size: 18px;"><i class="iconfont icon-txm-yes"
              style="font-size: 22px"></i> 提交</el-button>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="7">
          患者性别：<el-input disabled v-model="pGender" class="orderInput"></el-input>
        </el-col>
        <el-col :span="7">
          联系方式：<el-input disabled v-model="pPhone" class="orderInput"></el-input></el-col>
        <el-col :span="7">
          医生姓名：<el-input disabled v-model="dName" class="orderInput"></el-input>
        </el-col>
        <el-col :span="3">
          <el-button type="warning" @click="openReason" style="font-size: 18px;"><i class="iconfont icon-txm-paper"
              style="font-size: 22px"></i> 病因编写</el-button>
        </el-col>
      </el-row>
      <!-- 药物表格 -->
      <el-row>
        <el-col :span="12">
          <el-input v-model="queryDrug" placeholder="请输入名称查询" class="drugInput">
            <el-button slot="append" icon="el-icon-search" @click="requestDrug"></el-button>
          </el-input>
          <el-table :data="drugData" stripe border>
            <el-table-column label="编号" prop="drId"></el-table-column>
            <el-table-column label="名称" prop="drName"></el-table-column>
            <el-table-column label="剩余数量" prop="drNumber"></el-table-column>
            <el-table-column label="单位" prop="drUnit"></el-table-column>
            <el-table-column label="单价" prop="drPrice"></el-table-column>
            <el-table-column label="操作" width="140" fixed="right">
              <template slot-scope="scope">
                <el-button type="success" style="font-size: 14px;" @click="addDrug(scope.row.drId)">
                  <i class="iconfont icon-txm-add" style="font-size: 16px;"></i>
                  增加</el-button>
              </template>
            </el-table-column>
          </el-table>
          <!-- 分页 -->
          <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" background
            layout="total, sizes, prev, pager, next" :total="total" :page-size="size" :page-sizes="[1, 2, 4, 8, 16]">
          </el-pagination>
          <el-row></el-row>
        </el-col>
        <!-- 右边已选择的药物 -->
        <el-col :span="12" class="drugRigth">
          <el-table stripe border :data="drugBuyData" class="rigthTable">
            <el-table-column label="编号" prop="drId"></el-table-column>
            <el-table-column label="名称" prop="drName"></el-table-column>
            <el-table-column label="单价" prop="drPrice"></el-table-column>
            <el-table-column label="数量" prop="drNum"></el-table-column>
            <el-table-column label="小记" prop="drSum"></el-table-column>
            <el-table-column label="操作" width="140" fixed="right">
              <template slot-scope="scope">
                <el-button type="danger" style="font-size: 14px;" icon="iconfont icon-txm-del"
                  @click="deleteDrug(scope.row.drId)"> 移除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-tag>共计：{{ drugTotalPrice }}元</el-tag>
        </el-col>
      </el-row>
      <!-- 检查项目编写 -->
      <el-row>
        <el-col :span="12">
          <el-input v-model="queryCheck" placeholder="请输入名称查询" class="drugInput">
            <el-button slot="append" icon="el-icon-search" @click="requestCheck"></el-button>
          </el-input>
          <el-table stripe border :data="checkData">
            <el-table-column label="编号" prop="chId"></el-table-column>
            <el-table-column label="项目" prop="chName"></el-table-column>
            <el-table-column label="价格" prop="chPrice"></el-table-column>
            <el-table-column label="操作" width="140" fixed="right">
              <template slot-scope="scope">
                <el-button type="success" style="font-size: 14px;" icon="iconfont icon-txm-add"
                  @click="addCheck(scope.row.chId)"> 增加</el-button>
              </template>
            </el-table-column>
          </el-table>
          <!-- 分页 -->
          <el-pagination @size-change="checkSizeChange" @current-change="checkCurrentChange" background
            layout="total, prev, pager, next" :total="checkTotal" :page-size="checkSize">
          </el-pagination>
        </el-col>
        <!-- 右边已选择的检查 -->
        <el-col :span="12" class="drugRigth">
          <el-table stripe border class="rigthTable" :data="checkBuyData">
            <el-table-column label="编号" prop="chId"></el-table-column>
            <el-table-column label="项目" prop="chName"></el-table-column>
            <el-table-column label="价格" prop="chPrice"></el-table-column>
            <el-table-column label="操作" width="120" fixed="right">
              <template slot-scope="scope">
                <el-button type="danger" style="font-size: 14px;" icon="iconfont icon-txm-del"
                  @click="deleteCheck(scope.row.chId)"> 移除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-tag>共计：{{ checkTotalPrice }}元</el-tag>
        </el-col>
      </el-row>
    </el-card>

    <!-- 病因编写对话框 -->
    <el-dialog title="病因编写" :visible.sync="reasonFormVisible">
      <el-input type="textarea" :rows="8" placeholder="请输入内容" v-model="reason">
      </el-input>
      <div slot="footer" class="dialog-footer">
        <el-button @click="reasonFormVisible = false" style="font-size: 18px;"><i class="iconfont icon-txm-left"
            style="font-size: 20px;"></i> 取 消</el-button>
        <el-button type="primary" @click="holdReason">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import jwtDecode from "jwt-decode";
import { getToken } from "@/utils/storage.js";
import request from "@/utils/request.js";
import { toLoad } from "@/utils/initialize.js";
export default {
  name: "dealOrder",
  data() {
    return {
      oId: 1,
      pId: 1,
      dId: 0,
      pName: "aa",
      pGender: "as",
      pPhone: "asd",
      dName: "",
      drugData: [],
      size: 4,
      pageNumber: 1,
      total: 3,
      queryDrug: "",
      drugBuyData: [],
      drugTotalPrice: 0,
      //病因
      reason: "",
      //项目表格数据
      checkData: [],
      queryCheck: "",
      checkTotal: 3,
      checkSize: 4,
      checkPageNumber: 1,
      checkTotalPrice: 0,
      checkBuyData: [],
      // 病因编写对话框显示参数
      reasonFormVisible: false,
    };
  },
  methods: {
    //根据id减少药物数量
    reduceDrugNumber(drId, usedNumber) {
      request
        .get("drug/reduceDrugNumber", {
          params: {
            drId: drId,
            usedNumber: usedNumber,
          },
        })
        .then((res) => {
          if (res.data.status !== 200) {
            this.$message.error("药物数量不足！！");
          }
        });
    },
    //点击提交按钮
    submitClick() {
      for (let i = 0; i < this.drugBuyData.length; i++) {
        this.reduceDrugNumber(
          this.drugBuyData[i].drId,
          this.drugBuyData[i].drNum
        );
      }
      let data = {
        oId: this.dataPackage().oId,
        pId: this.pId,
        dId: this.dId,
        oRecord: this.dataPackage().oRecord,
        oDrug: this.dataPackage().oDrug,
        oCheck: this.dataPackage().oCheck,
        oTotalPrice: this.dataPackage().oTotalPrice,
      };
      request
        .post("order/updateOrder", data)
        .then((res) => {
          if (res.data.status !== 200) {
            this.$message.error("请求信息错误");
            return;
          }
          this.$message.success("提交成功！请通知患者登录系统自助缴费！");
          this.$router.push("/orderToday");
          console.log(res.data);
        })
        .catch((err) => {
          console.error(err);
        });
    },
    //封装数据
    dataPackage() {
      let oDrug = "";
      let oCheck = "";
      let oTotalPrice = 0;
      let oId = this.oId;
      let oRecord = this.reason;
      for (let i = 0; i < this.drugBuyData.length; i++) {
        oDrug +=
          this.drugBuyData[i].drName +
          "*" +
          this.drugBuyData[i].drPrice +
          "(元)*" +
          this.drugBuyData[i].drNum +
          " ";
      }
      for (let i = 0; i < this.checkBuyData.length; i++) {
        oCheck +=
          this.checkBuyData[i].chName +
          "*" +
          this.checkBuyData[i].chPrice +
          "(元) ";
      }
      oCheck += " 项目总价" + this.checkTotalPrice + "元 ";
      oDrug += " 药物总价" + this.drugTotalPrice + "元 ";
      oTotalPrice = this.checkTotalPrice + this.drugTotalPrice;
      return { oId, oRecord, oDrug, oCheck, oTotalPrice };
    },
    //点击病因保存按钮
    holdReason() {
      this.reasonFormVisible = false;
      this.$message.success("信息保存成功！");
    },
    //打开病因编写对话框
    openReason() {
      this.reasonFormVisible = true;
    },
    //检查列表点击移除按钮
    deleteCheck(chId) {
      for (let i = 0; i < this.checkBuyData.length; i++) {
        if (this.checkBuyData[i].chId === chId) {
          this.checkTotalPrice -= this.checkBuyData[i].chPrice; //药物价格总计
          this.checkBuyData.splice(i, 1); //！！！！！！删除数组中下标为i的元素
        }
      }
    },
    //检查列表点击增加按钮
    addCheck(chId) {
      request
        .get("check/findCheck", {
          params: {
            chId: chId,
          },
        })
        .then((res) => {
          if (res.data.status != 200) return this.$message.error("信息请求失败");
          //后端传过来的是对象，表格绑定的数组
          this.checkBuyData.push({
            chId: res.data.data.chId,
            chPrice: res.data.data.chPrice,
            chName: res.data.data.chName,
          });
          this.checkTotalPrice += res.data.data.chPrice; //药物价格总计
          console.log(res.data.data);
        });
    },
    //药物页面大小切换时触发
    checkSizeChange(size) {
      this.checkSize = size;
      this.requestCheck();
    },
    //检查页数切换时触发
    checkCurrentChange(num) {
      this.checkPageNumber = num;
      this.requestCheck();
    },
    //请求检查项目
    requestCheck() {
      request
        .get("check/findAllChecks", {
          params: {
            size: this.checkSize,
            pageNumber: this.checkPageNumber,
            query: this.queryCheck,
          },
        })
        .then((res) => {
          toLoad();
          if (res.data.status != 200) this.$message.error("获取信息失败");
          this.checkData = res.data.data.checks;
          this.checkTotal = res.data.data.total;
          console.log(res);
        });
    },
    /**
     * 此处逻辑较复杂，容易出现错误
     */
    //药物列表点击移除按钮
    deleteDrug(drId) {
      for (let i = 0; i < this.drugBuyData.length; i++) {
        if (this.drugBuyData[i].drId === drId) {
          for (let j = 0; j < this.drugData.length; j++) {
            if (this.drugData[j].drId === drId) this.drugData[j].drNumber += 1;
          }
          this.drugBuyData[i].drNum -= 1;
          this.drugBuyData[i].drSum =
            this.drugBuyData[i].drPrice * this.drugBuyData[i].drNum;
          this.drugTotalPrice -= this.drugBuyData[i].drPrice; //药物价格总计
          if (this.drugBuyData[i].drNum === 0) this.drugBuyData.splice(i, 1); //！！！！！！删除数组中下标为i的元素
        }
      }
    },
    //药物列表点击增加按钮
    addDrug(drId) {
      request
        .get("drug/findDrug", {
          params: {
            drId: drId,
          },
        })
        .then((res) => {
          if (res.data.status != 200) return this.$message.error("信息请求失败");
          //后端传过来的是对象，表格绑定的数组
          for (let i = 0; i < this.drugBuyData.length; i++) {
            if (this.drugBuyData[i].drId === res.data.data.drId) {
              for (let j = 0; j < this.drugData.length; j++) {
                if (
                  this.drugData[j].drId === res.data.data.drId &&
                  this.drugData[j].drNumber > 0
                ) {
                  this.drugData[j].drNumber -= 1;
                  this.drugBuyData[i].drNum += 1;
                  this.drugBuyData[i].drSum =
                    this.drugBuyData[i].drPrice * this.drugBuyData[i].drNum;
                  this.drugTotalPrice += this.drugBuyData[i].drPrice; //药物价格总计
                  //return;
                }
              }
              return;
            }
          }
          for (let j = 0; j < this.drugData.length; j++) {
            if (
              this.drugData[j].drId === res.data.data.drId &&
              this.drugData[j].drNumber <= 0
            )
              return;
          }
          this.drugBuyData.push({
            drId: res.data.data.drId,
            drPrice: res.data.data.drPrice,
            drName: res.data.data.drName,
            drNum: 1,
            drSum: res.data.data.drPrice,
          });
          for (let j = 0; j < this.drugData.length; j++) {
            if (this.drugData[j].drId === res.data.data.drId)
              this.drugData[j].drNumber -= 1;
          }
          this.drugTotalPrice += res.data.data.drPrice; //药物价格总计

          console.log(res.data.data);
        });
    },
    //药物页面大小切换时触发
    handleSizeChange(size) {
      this.size = size;
      this.requestDrug();
    },
    //药物页数切换时触发
    handleCurrentChange(num) {
      this.pageNumber = num;
      this.requestDrug();
    },

    //获取药物列表
    requestDrug() {
      request
        .get("drug/findAllDrugs", {
          params: {
            size: this.size,
            pageNumber: this.pageNumber,
            query: this.queryDrug,
          },
        })
        .then((res) => {
          if (res.data.status != 200) this.$message.error("获取信息失败");
          this.drugData = res.data.data.drugs;
          this.total = res.data.data.total;

          console.log(res);
        });
    },
    //获取患者信息
    requestPatient() {
      request
        .get("doctor/findPatientById", {
          params: {
            pId: this.pId,
          },
        })
        .then((res) => {
          console.log(res);
          if (res.data.status != 200) this.$message.error("获取信息失败");
          this.pName = res.data.data.pName;
          this.pGender = res.data.data.pGender;
          this.pPhone = res.data.data.pPhone;
        });
    },
    //token解码
    tokenDecode(token) {
      if (token !== null) return jwtDecode(token);
    },
  },
  created() {
    this.dName = this.tokenDecode(getToken()).dName;
    this.dId = this.tokenDecode(getToken()).dId;
    this.oId = this.$route.query.oId;
    this.pId = this.$route.query.pId;
    this.requestPatient();
    this.requestDrug();
    this.requestCheck();
  },
};
</script>
<style lang="scss">
// 让文字居中
.drugRigth {
  text-align: center;
}

.el-tag {
  margin: 8px;
}

.rigthTable {
  margin-top: 56px;
  margin-left: 8px;
}

.drugInput {
  margin-top: 8px;
  margin-bottom: 8px;
}

.el-row {
  margin: 5px;
}

.orderInput {
  width: 240px;
}

.el-pagination {
  margin: 8px;
}
</style>