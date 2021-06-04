
//	内存使用情况代码
var zxtDom = document.getElementById("zxt");
var zxtChart = echarts.init(zxtDom);
/**保存时间
 * */
var memorytime=[];
/**保存利用率
 * */
var memorydata=[];
setInterval(function () {
    $.ajax({
        type : "post",
        async : true, //异步请求
        url : "/MS/getmemoryinfo",
        data : {},
        dataType : "json",
        success : function(result) {
            memorytime.push(result.time);
            memorydata.push(result.data);
            if (result.status) {
                zxtChart.hideLoading();    //隐藏加载动画
                zxtChart.setOption({        //加载数据图表
                    title: {
                        text: '内存使用率(MB)'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    color:"#8c7ae6",
                    xAxis: {
                        data:memorytime
                    },
                    yAxis: {
                        splitLine: {show: false},
                        max:8191
                    },
                    series: {
                        type: 'line',
                        showSymbol: false,
                        name:"内存使用率",
                        areaStyle: {
                            normal: { }
                        },
                        data:memorydata
                    }
                });

            }

        },
        error : function(errorMsg) {
            //请求失败时执行该函数
            layer.alert("图表请求数据失败!");
            zxtChart.hideLoading();
        }
    });
}, 400);

setTimeout(function(){
    setInterval(function(){
        memorytime.shift();
        memorydata.shift();
    },400);
},10000);

// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('disk'));
    $.ajax({
        type : "post",
        async : true, //异步请求
        url : "/MS/getdiskinfo",
        data : {},
        dataType : "json",
        success : function(result) {
            if (result.status) {
                myChart.hideLoading();    //隐藏加载动画
                myChart.setOption({
                    title:{
                        text:"磁盘使用情况(GB)"
                    },
                    tooltip:{trigger: 'item'},
                    series : [
                        {
                            title:"磁盘使用情况",
                            name: '磁盘使用情况',
                            type: 'pie',    // 设置图表类型为饼图
                            radius: '70%',  // 饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长度。
                            data:[          // 数据数组，name 为数据项名称，value 为数据项值
                                {value:result.data.sumSize.size, name:result.data.sumSize.name,itemStyle: { color: '#22a6b3' }},
                                {value:result.data.surplusSize.size, name:result.data.surplusSize.name,itemStyle: { color: '#778ca3' }},
                            ],
                        }
                    ]
                });
            }

        },
        error : function(errorMsg) {
            //请求失败时执行该函数
            layer.alert("图表请求数据失败!");
            myChart.hideLoading();
        }
    });