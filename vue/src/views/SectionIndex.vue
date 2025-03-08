<template>
    <div>
        <el-card>
            <!-- <span>科室门诊</span> -->
            <el-divider></el-divider>
            <div class="sectionIndex" style="font-size: 24px">
                <i class="iconfont icon-txm-love" style="font-size: 22px"></i>
                <b> 内科类</b>
            </div>
            <ul>
                <li v-for="inter in inters" :key="inter">
                    <el-button style="margin: 5px" @click="sectionClick(inter)">{{ inter }}</el-button>
                </li>
            </ul>
            <el-divider></el-divider>
            <div class="sectionIndex" style="font-size: 24px">
                <i class="iconfont icon-txm-love" style="font-size: 22px"></i>
                <b> 外科类</b>
            </div>
            <ul>
                <li v-for="out in outs" :key="out">
                    <el-button style="margin: 5px" @click="sectionClick(out)">{{
                        out
                        }}</el-button>
                </li>
            </ul>
            <el-divider></el-divider>
            <div class="sectionIndex">
                <div class="sectionIndex" style="font-size: 24px">
                    <i class="iconfont icon-txm-love" style="font-size: 22px"></i>
                    <b> 妇产科类</b>
                </div>
            </div>
            <ul>
                <li v-for="woman in women" :key="woman">
                    <el-button style="margin: 5px" @click="sectionClick(woman)">{{ woman }}</el-button>
                </li>
            </ul>
            <el-divider></el-divider>
            <div class="sectionIndex">
                <div class="sectionIndex" style="font-size: 24px">
                    <i class="iconfont icon-txm-love" style="font-size: 22px"></i>
                    <b> 儿科类</b>
                </div>
            </div>
            <ul>
                <li v-for="kid in kids" :key="kid">
                    <el-button style="margin: 5px" @click="sectionClick(kid)">{{
                        kid
                        }}</el-button>
                </li>
            </ul>
            <el-divider></el-divider>
            <div class="sectionIndex">
                <div class="sectionIndex" style="font-size: 24px">
                    <i class="iconfont icon-txm-love" style="font-size: 22px"></i>
                    <b> 五官科类</b>
                </div>
            </div>
            <ul>
                <li v-for="five in fives" :key="five">
                    <el-button style="margin: 5px" @click="sectionClick(five)">{{ five }}</el-button>
                </li>
            </ul>
            <el-divider></el-divider>
            <div class="sectionIndex">
                <div class="sectionIndex" style="font-size: 24px">
                    <i class="iconfont icon-txm-love" style="font-size: 22px"></i>
                    <b> 中医科类</b>
                </div>
            </div>
            <ul>
                <li v-for="chinese in chineses" :key="chinese">
                    <el-button style="margin: 5px" @click="sectionClick(chinese)">{{ chinese }}</el-button>
                </li>
            </ul>
            <el-divider></el-divider>
            <div class="sectionIndex">
                <div class="sectionIndex" style="font-size: 24px">
                    <i class="iconfont icon-txm-love" style="font-size: 22px"></i>
                    <b> 其他类</b>
                </div>
            </div>
            <ul>
                <li v-for="orther in orthers" :key="orther">
                    <el-button style="margin: 5px" @click="sectionClick(orther)">{{ orther }}</el-button>
                </li>
            </ul>
            <el-divider></el-divider>
        </el-card>
    </div>
</template>
<script>
import { toLoad } from "@/utils/initialize.js";
export default {
    name: "SectionIndex",
    data() {
        return {
            inters: [
                "神经内科",
                "呼吸与危重症医学科",
                "内分泌科",
                "消化内科",
                "心血管内科",
                "肾内科",
                "发热门诊",
            ],
            outs: [
                "手足外科",
                "普通外科",
                "肛肠外科",
                "神经外科",
                "泌尿外科",
                "骨科",
                "烧伤整形外科",
            ],
            women: ["妇科", "产科"],
            kids: ["儿科", "儿童保健科"],
            fives: ["耳鼻咽喉科", "眼科", "口腔科"],
            chineses: ["中医科"],
            orthers: ["康复医学科", "急诊科", "皮肤性病科", "功能科"],
        };
    },
    methods: {
        //科室点击
        sectionClick(section) {
            this.$router.push({ path: "/arrangeDoctor?section=" + section });
        },
    },
    mounted() {
        toLoad()
    }
};
</script>
<style scoped lang="scss">
ul li {
    display: inline;
}

.el-breadcrumb {
    margin: 8px;
}
</style>