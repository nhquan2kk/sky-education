<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<canvas id="myChart" style="width:100%;max-width:1000px"></canvas>
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	
	<script>
	console.log('running');
		$.ajax({

			async : false,

			url : "AdminMemberChartController",

			dataType : "json",

			success : function(jsonData) {

				console.log(jsonData);
				 var xValues = jsonData.numberMembers.map(item=>item.createAt);
				var yValues = studentJsonData.numberMembers.map(item=>item.countMember);
				console.log('xValues : ', xValues, 'y values : ', yValues);
				/* $.each(studentJsonData, function(index, aStudent) {

					formattedstudentListArray.push([ aStudent.mathematicsMark,
							aStudent.computerMark, aStudent.historyMark,
							aStudent.litratureMark, aStudent.geographyMark ]);
				}); 
			}
		});
	</script>
	
</body>
</html>