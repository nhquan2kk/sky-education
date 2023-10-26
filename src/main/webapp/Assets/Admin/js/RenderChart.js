var options = {
	scales: {
		yAxes: [{
			ticks: {
				beginAtZero: true
			}
		}]
	},
	legend: {
		display: false
	},
	elements: {
		point: {
			radius: 0
		}
	}

};
var doughnutPieOptions = {
	responsive: true,
	animation: {
		animateScale: true,
		animateRotate: true
	}
};
const horizontalBarOptions = {
	scales: {
		xAxes: [{
			ticks: {
				beginAtZero: true
			}
		}]
	},
	legend: {
		display: false
	}
}
function getMemberData(numberMembers) {
	var xValues = numberMembers.map(item => item.createAt);
	var yValues = numberMembers.map(item => item.countMember);
	return {
		labels: xValues,
		datasets: [{
			label: 'Members',
			data: yValues,
			backgroundColor: [
				'rgba(255, 99, 132, 0.2)',
				'rgba(54, 162, 235, 0.2)',
				'rgba(255, 206, 86, 0.2)',
				'rgba(75, 192, 192, 0.2)',
				'rgba(153, 102, 255, 0.2)',
				'rgba(255, 159, 64, 0.2)'
			],
			borderColor: [
				'rgba(255,99,132,1)',
				'rgba(54, 162, 235, 1)',
				'rgba(255, 206, 86, 1)',
				'rgba(75, 192, 192, 1)',
				'rgba(153, 102, 255, 1)',
				'rgba(255, 159, 64, 1)'
			],
			borderWidth: 1,
			fill: false
		}]
	}
}
function getEleTotalData(eleTotal){
	return {
			datasets: [{
				data: eleTotal,
				backgroundColor: [
					'rgba(255, 99, 132, 0.5)',
					'rgba(54, 162, 235, 0.5)',
					'rgba(255, 206, 86, 0.5)',
					'rgba(75, 192, 192, 0.5)',
					'rgba(153, 102, 255, 0.5)',
					'rgba(255, 159, 64, 0.5)'
				],
				borderColor: [
					'rgba(255,99,132,1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)'
				],
			}],

			// These labels appear in the legend and in the tooltips when hovering different arcs
			labels: [
				'Grammar',
				'Examination',
				'Vocabulary',
				'Reading',
				'Listening'
			]
		}
}
function getExLvData(exLvChart){
	var xValues = exLvChart.map(item => item.nameLevel);
	var yValues = exLvChart.map(item => item.totalExLv);
	return {
			labels: xValues,
			datasets: [{
				axis: 'y',
				/*label: 'Number of examination by level',*/
				data: yValues,
				fill: false,
				backgroundColor: [
					'rgba(255, 99, 132, 0.2)',
					'rgba(255, 159, 64, 0.2)',
					'rgba(255, 205, 86, 0.2)',
					'rgba(75, 192, 192, 0.2)',
					'rgba(54, 162, 235, 0.2)',
					'rgba(153, 102, 255, 0.2)',
					'rgba(201, 203, 207, 0.2)'
				],
				borderColor: [
					'rgb(255, 99, 132)',
					'rgb(255, 159, 64)',
					'rgb(255, 205, 86)',
					'rgb(75, 192, 192)',
					'rgb(54, 162, 235)',
					'rgb(153, 102, 255)',
					'rgb(201, 203, 207)'
				],
				borderWidth: 1
			}]
		}
}
$.ajax({
	async: false,
	url : "AdminMemberChartController",
	dataType :  "json",
	success: function(jsonData) {
		console.log("jsonData : ", jsonData)
		new Chart("lineChart", {
			type: "bar",
			data: getMemberData(jsonData.numberMembers),
			options: options
		});
		new Chart("pieChart", {
			type: 'pie',
			data: getEleTotalData(jsonData.eleTotal),
			options: doughnutPieOptions
		});
		new Chart('horizontalBarChart', {
			type: 'horizontalBar',
			data: getExLvData(jsonData.exLvChart),
			options: horizontalBarOptions
		});
	},
});

;