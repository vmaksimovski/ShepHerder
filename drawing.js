var canvas = document.getElementById('canvas');
var ctx = canvas.getContext('2d');

canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

var size = Math.min(canvas.width, canvas.height) / 2;

var pointsX = [];
var pointsY = [];
pointsX.length = 1500;
pointsY.length = 1500;

var repulsorX = [800, 100];
var repulsorY = [800, 100];

for (var i = 0; i < 1500; i++){
	pointsX[i] = Math.floor(Math.random() * canvas.height);
	pointsY[i] = Math.floor(Math.random() * canvas.width);
}

main();
function main(){
	ctx.fillStyle = "#fff";
	ctx.fillRect(0, 0, canvas.width, canvas.height);

	ctx.fillStyle = "#f00";
	ctx.fillRect(repulsorX[0], repulsorY[0], 4, 4);
	ctx.fillRect(repulsorX[1], repulsorY[1], 4, 4);
	var forceX = [];
	var forceY = [];
	forceX.length = 1500;
	forceY.length = 1500;

	for(var i = 0; i < 1500; i++){
		forceX[i] = 0;
		forceY[i] = 0;
	}

	for(var i = 0; i < repulsorY.length; i++){
		for(var j = 0; j < pointsY.length; j++){
			var r = Math.sqrt((pointsY[j] - repulsorY[i]) * (pointsY[j] - repulsorY[i]) + (pointsX[j] - repulsorX[i]) * (pointsX[j] - repulsorX[i]));
			var theta = Math.atan2(pointsY[j] - repulsorY[i], pointsX[j] - repulsorX[i]);
			forceY[j] += 200000/(r*r)*Math.sin(theta);
			forceX[j] += 200000/(r*r)*Math.cos(theta);
			// forceY[j] += 5000/(r*Math.sqrt(r))*Math.sin(theta);
			// forceX[j] += 5000/(r*Math.sqrt(r))*Math.cos(theta);
			console.log(r);
		}
	}

	for(var i = 0; i < pointsY.length; i++){
		pointsY[i] += forceY[i];
		pointsX[i] += forceX[i];
	}

	for (var i = 0; i < 1500; i++){
		ctx.fillStyle = "#00f";
		ctx.fillRect(pointsX[i], pointsY[i], 4, 4);
	}

	setTimeout(main, 16);
}
