$(function () {

    var violet = '#DF99CA',
        red = '#F0404C',
        green = '#7CF29C',
        blue = '#4680ff';
    var ctx1 = $("canvas").get(0).getContext("2d");
    var gradient2 = ctx1.createLinearGradient(10, 0, 150, 300);
    gradient2.addColorStop(0, 'rgba(252, 117, 176, 0.84)');
    gradient2.addColorStop(1, 'rgba(250, 199, 106, 0.92)');

    // ------------------------------------------------------- //
    // Line Chart
    // ------------------------------------------------------ //

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


    // ------------------------------------------------------- //
    // Line Chart
    // ------------------------------------------------------ //

    var LINECHART = $('#lineChart2');
    var myLineChart = new Chart(LINECHART, {
        type: 'line',
        options: {
            scales: {
                xAxes: [{
                    display: false
                }],
                yAxes: [{
                    ticks: {
                        max: 50,
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
            labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"],
            datasets: [{
                label: "Page Visitors",
                fill: true,
                lineTension: 0.4,
                backgroundColor: "transparent",
                borderColor: blue,
                pointBorderColor: blue,
                pointHoverBackgroundColor: blue,
                borderCapStyle: 'butt',
                borderDash: [],
                borderDashOffset: 0.0,
                borderJoinStyle: 'miter',
                borderWidth: 3,
                pointBackgroundColor: "#fff",
                pointBorderWidth: 5,
                pointHoverRadius: 5,
                pointHoverBorderColor: "#fff",
                pointHoverBorderWidth: 1,
                pointRadius: 0,
                pointHitRadius: 1,
                data: [20, 14, 21, 15, 22, 8, 18, 13, 21, 13, 17, 13, 20, 15],
                spanGaps: false
            }]
        }
    });


    // ------------------------------------------------------- //
    // Line Chart 3
    // ------------------------------------------------------ //

    var LINECHART = $('#lineChart3');
    var myLineChart = new Chart(LINECHART, {
        type: 'line',
        options: {
            scales: {
                xAxes: [{
                    display: false
                }],
                yAxes: [{
                    ticks: {
                        max: 50,
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
            labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"],
            datasets: [{
                label: "Page Visitors",
                fill: true,
                lineTension: 0.4,
                backgroundColor: "transparent",
                borderColor: red,
                pointBorderColor: red,
                pointHoverBackgroundColor: red,
                borderCapStyle: 'butt',
                borderDash: [],
                borderDashOffset: 0.0,
                borderJoinStyle: 'miter',
                borderWidth: 3,
                pointBackgroundColor: "#fff",
                pointBorderWidth: 5,
                pointHoverRadius: 5,
                pointHoverBorderColor: "#fff",
                pointHoverBorderWidth: 1,
                pointRadius: 0,
                pointHitRadius: 1,
                data: [20, 14, 21, 15, 22, 8, 18, 13, 21, 13, 17, 13, 20, 15],
                spanGaps: false
            }]
        }
    });


    // ------------------------------------------------------- //
    // Pie Chart
    // ------------------------------------------------------ //
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
                "First",
                "Second",
                "Third"
            ],
            datasets: [{
                data: [250, 200],
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


    // ------------------------------------------------------- //
    // Pie Chart
    // ------------------------------------------------------ //
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
                data: [300, 50],
                borderWidth: [0, 0],
                backgroundColor: [
                    blue,
                    "#eee"
                ],
                hoverBackgroundColor: [
                    blue,
                    "#eee"
                ]
            }]
        }
    });


    // ------------------------------------------------------- //
    // Pie Chart
    // ------------------------------------------------------ //
    var PIECHART = $('#pieChartHome3');
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
                data: [300, 50],
                borderWidth: [0, 0],
                backgroundColor: [
                    violet,
                    "#eee"
                ],
                hoverBackgroundColor: [
                    violet,
                    "#eee"
                ]
            }]
        }
    });


    // ------------------------------------------------------- //
    // Pie Chart
    // ------------------------------------------------------ //
    var PIECHART = $('#pieChartHome4');
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
                data: [200, 80],
                borderWidth: [0, 0],
                backgroundColor: [
                    green,
                    "#eee"
                ],
                hoverBackgroundColor: [
                    green,
                    "#eee"
                ]
            }]
        }
    });


});