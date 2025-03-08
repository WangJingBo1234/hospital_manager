<template>
  <div class="Echarts">
    <div id="orderPeople" style="width: 1200px; height: 400px;"></div>
    <div id="orderSection" style="width: 1200px; height: 400px;"></div>
    <div id="orderGender" style="width: 540px; height: 500px;float:left"></div>
    <div id="patientAge" style="width: 600px; height: 500px;float:right"></div>


  </div>
</template>
<script>
import request from "@/utils/request.js";
export default {
  name: "dataExpore",
  data() {
    return {
      sevenDate: [],
      sevenOrder: [],
    };
  },
  methods: {
    //统计患者年龄分布
    patientAge(){
      var myChart = this.$echarts.init(document.getElementById("patientAge"));
      request.get("patient/patientAge")
      .then(res => {
        var option = {
           title: {
        text: '患者年龄段分布',
        left: 'center'
    },
    tooltip: {
        trigger: 'item'
    },
    legend: {
        top: '5%',
        left: 'center'
    },
    series: [
        {
            name: '年龄段',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
            },
            label: {
                show: false,
                position: 'center'
            },
            emphasis: {
                label: {
                    show: true,
                    fontSize: '40',
                    fontWeight: 'bold'
                }
            },
            labelLine: {
                show: false
            },
            data: [
                {value: res.data.data[0], name: '0-9岁'},
                {value: res.data.data[1], name: '10-19岁'},
                {value: res.data.data[2], name: '20-29岁'},
                {value: res.data.data[3], name: '30-39岁'},
                {value: res.data.data[4], name: '40-49岁'},
                {value: res.data.data[5], name: '50-59岁'},
                {value: res.data.data[6], name: '60-69岁'},
                {value: res.data.data[7], name: '70-79岁'},
                {value: res.data.data[8], name: '80-89岁'},
                {value: res.data.data[9], name: '90-99岁'},
            ]
        }
    ]
};
       
            

// 使用刚指定的配置项和数据显示图表。
          myChart.setOption(option);


      })
      .catch(err => {
        console.error(err); 
      })
    },
    //统计挂号科室人数
    orderSection(){
      var myChart = this.$echarts.init(document.getElementById("orderSection"));
      request.get("order/orderSection")
      .then(res => {
        console.log(res.data.data.map((item) => item.countSection));
        console.log(res.data.data.map((item) => item.doctor.dSection))
        var option = {
             title: {
        text: '挂号科室人数统计',
        left: 'center'
    },
    xAxis: {
        type: 'category',
        data: res.data.data.map((item) => item.doctor.dSection),
        axisLabel: {//解决各个不显示问题
           interval:0,
           rotate:10,
           }

    },
    yAxis: {
        type: 'value'
    },
    series: [{
        data: res.data.data.map((item) => item.countSection),
        type: 'bar',
        showBackground: true,
        backgroundStyle: {
            color: 'rgba(180, 180, 180, 0.2)'
        }
    }]
};
// 使用刚指定的配置项和数据显示图表。
          myChart.setOption(option);


        

      })
      .catch(err => {
        console.error(err); 
      })
    },
    //挂号男女比例
    orderGender(){
      var myChart = this.$echarts.init(document.getElementById("orderGender"));
      request.get("order/orderGender",)
      .then(res => {
      var option = {
    title: {
        text: '患者性别比例',
        left: 'center'
    },
    tooltip: {
        trigger: 'item'
    },
    legend: {
        orient: 'vertical',
        left: 'left',
    },
    series: [
        {
            name: '人数',
            type: 'pie',
            radius: '50%',
            data: [
                {value: res.data.data.map((item) => item.countGender)[0], name: res.data.data.map((item) => item.patient.pGender)[0]},
                {value: res.data.data.map((item) => item.countGender)[1], name: res.data.data.map((item) => item.patient.pGender)[1]},
                
            ],
            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
          // 使用刚指定的配置项和数据显示图表。
          myChart.setOption(option);

      })
      .catch(err => {
        console.error(err); 
      });


    },
    //获取过去num天日期
    pastSeven(num) {
      var date = new Date();
      date.setDate(date.getDate() - num);
      var time = date.getMonth() + 1 + "-" + date.getDate();
      return time;
    },
    //挂号人数图表
    orderPeople() {
      var myChart = this.$echarts.init(document.getElementById("orderPeople"));
      request
        .get("order/orderSeven")
        .then((res) => {
          if (res.data.status !== 200)
            return this.$message.error("数据请求失败！");
          var option = {
            title: {
              text: "近20天挂号人数折线图",
              left: "5%",
            },
            xAxis: {
              type: "category",
              data: this.sevenDate,
            },
            yAxis: {
              type: "value",
            },
            series: [
              {
                data: res.data.data,
                type: "line",
              },
            ],
          };
          // 使用刚指定的配置项和数据显示图表。
          myChart.setOption(option);
        })
        .catch((err) => {
          console.error(err);
        });
    },
  },
  mounted() {
    this.orderPeople();
    this.orderGender();
    this.orderSection();
    this.patientAge();
  },
  created() {
    //获取过去7天日期
    for (var i = 20; i > 0; i--) {
      this.sevenDate.push(this.pastSeven(i));
    }
  },
};
</script>

<style>
</style>
