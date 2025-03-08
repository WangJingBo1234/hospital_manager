<!--
 /*
 * @Description: 
 * @Author: tangxm
 * @Date: 2024-04-08 20:02:29
 */
-->
<template>
    <div>
        <el-card>
            <div>
                <div id="arrangeIndex" style="font-size: 18px;">
                    <i class="iconfont icon-txm-list" style="margin: 10px; font-size: 24px">
                    </i>
                    请选择值班日期：
                </div>
                <br />
                <ul class="dateUl">
                    <li v-for="monthDay in monthDays" :key="monthDay">
                        <el-button type="primary" style="margin: 5px;font-size: 18px;" @click="dateClick(monthDay)">
                            <i class="iconfont icon-txm-paper" style="font-size: 24px;"></i>
                            {{ monthDay }}</el-button>
                    </li>
                </ul>
            </div>
            <div class="router-view">
                <router-view></router-view>
            </div>
        </el-card>
    </div>
</template>
<script>
import { getActivePath, setActivePath } from "@/utils/storage.js";
import { toLoad } from "@/utils/initialize.js";
const ARRANGEDATE = "arrangeDate";
export default {
    name: "ArrangeIndex",
    data() {
        return {
            monthDays: [],
            monthDay: "",
            activePath: "",
        };
    },

    methods: {
        //日历点击
        dateClick(monthDay) {
            console.log(monthDay);

            const nowDate = new Date();
            let year = nowDate.getFullYear();
            let dateTime = year + "-" + monthDay;
            sessionStorage.setItem(ARRANGEDATE, dateTime);

            this.activePath = "sectionIndex";
            setActivePath("sectionIndex");
            if (this.$route.path !== "/sectionIndex")
                this.$router.push("sectionIndex");
        },
        //获取当天及后7天的日期星期
        nowDay(num) {
            var nowDate = new Date();
            nowDate.setDate(nowDate.getDate() + num);
            var month = nowDate.getMonth() + 1;
            var date = nowDate.getDate();
            if (date < 10) {
                date = "0" + date;
            }
            if (month < 10) {
                month = "0" + month;
            }
            var time = month + "-" + date;
            this.monthDays.push(time);
        },
    },
    mounted() {
        toLoad();
    },
    created() {
        //获取当天的后7天
        for (var i = 0; i < 25; i++) {
            this.nowDay(i);
            //  获取激活路径
            this.activePath = getActivePath();
        }
    },
};
</script>
<style scoped lang="scss">
.disabled {
    background-color: #ddd;
    border-color: #ddd;
    color: black;
    cursor: not-allowed; // 鼠标变化
    pointer-events: none;
}

.router-view {
    margin-top: 20px;
}

.sectionUl li {
    display: inline;
    padding: 60px;
}

.dateUl li {
    display: inline;
    //margin: 5px;
    padding: 1px;
}
</style>