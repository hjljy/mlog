$(function () {

    let violet = '#DF99CA',
        red = '#F0404C',
        green = '#7CF29C',
        blue = '#4680ff';
    let ctx1 = $("canvas").get(0).getContext("2d");
    let gradient2 = ctx1.createLinearGradient(10, 0, 150, 300);
    gradient2.addColorStop(0, 'rgba(252, 117, 176, 0.84)');
    gradient2.addColorStop(1, 'rgba(250, 199, 106, 0.92)');

    var lifePieData=new Array();
    var skillPieData=new Array();
    getAllCount();
    function getAllCount() {
        $.ajax({
            url: "/mlog/allCount",
            dataType: "json",
            async: false,
            success: function (result) {
                var data = result.data;
                lifePieData.push(data.lifeCount);
                lifePieData.push(data.articleCount-data.lifeCount);
                skillPieData.push(data.skillCount);
                skillPieData.push(data.articleCount-data.lifeCount);
                $(".life").text(data.lifeCount);
                $(".skill").text(data.skillCount);
                $(".article").text(data.articleCount);
                $(".view").text(data.viewCount);
                $(".comment").text(data.commentCount);
            }
        })
    }
    var LINECHART = $('#lineChart1');
    var myLineChart = new Chart(LINECHART, {
        type: 'bar',
        options: {
            scales: {
                xAxes: [{
                    display: true,
                    gridLines: {
                        color: '#fff'
                    }
                }],
                yAxes: [{
                    ticks: {
                        min: 0
                    },
                    display: false
                }]
            },
            legend: {
                display: false
            }
        },
        data: {
            labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"],
            datasets: [{
                label: "Page Visitors",
                borderWidth: 3,
                backgroundColor: [
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2
                ],
                hoverBackgroundColor: [
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2
                ],
                borderColor: [
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2,
                    gradient2
                ],
                data: [111, 92, 83, 75, 72, 70, 67, 59, 57, 50],
                spanGaps: false
            }]
        }
    });


    var PIECHART = $('#pieChartHome1');
    var myPieChart = new Chart(PIECHART, {
        type: 'doughnut',
        options: {
            cutoutPercentage: 90,
            legend: {
                display: false
            }
        },
        data: {
            labels: [
                "生活类文章",
                "总文章",
                "Third"
            ],
            datasets: [{
                data: lifePieData,
                borderWidth: [0, 0],
                backgroundColor: [
                    green,
                    "#eee",
                ],
                hoverBackgroundColor: [
                    green,
                    "#eee",
                ]
            }]
        }
    });


    var PIECHART = $('#pieChartHome2');
    var myPieChart = new Chart(PIECHART, {
        type: 'doughnut',
        options: {
            cutoutPercentage: 90,
            legend: {
                display: false
            }
        },
        data: {
            labels: [
                "First",
                "Second"
            ],
            datasets: [{
                data: skillPieData,
                borderWidth: [0, 0],
                backgroundColor: [
                    blue,
                    "#eee",
                ],
                hoverBackgroundColor: [
                    blue,
                    "#eee",
                ]
            }]
        }
    });



});

