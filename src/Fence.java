package src;

public class Fence {
	enum Direction {
		vert,
		horiz,
		diag,
		antidiag;
	}

	Direction dir;
	int pX1, pX2, pY1, pY2;

	public Fence(int temppX1, int temppY1, int temppX2, int temppY2){
		pX1 = Math.min(temppX1, temppX2);
		pX2 = Math.max(temppX1, temppX2);
		pY1 = Math.min(temppY1, temppY2);
		pY2 = Math.max(temppY1, temppY2);

		if(pX1 == pX2){
			dir = Direction.vert;
		} else if(pY1 == pY2){
			dir = Direction.horiz;
		} else if(pY2 - pY1 == pX2 - pX1){
			dir = Direction.diag;
		} else if(pX1 + pY1 == pX2 + pX2){
			dir = Direction.antidiag;
		} else {
			System.out.println("ERROR: INCORRECT PADDLE SETUP");
		}
	}

	public int collides(Sheep sheep){
		if(dir == Direction.vert){
			if(sheep.x < pX1 && sheep.x + sheep.dX + sheep.diameter >= pX1 && pY1 < sheep.y + sheep.dY + sheep.diameter && sheep.y + sheep.dY < pY2){
				return 1;
			} else if(pX1 < sheep.x && sheep.x + sheep.dX <= pX1 && pY1 < sheep.y + sheep.dY + sheep.diameter && sheep.y + sheep.dY < pY2){
				return 2;
			}
		} else if(dir == Direction.horiz){
			if(sheep.y < pY1 && sheep.y + sheep.dY + sheep.diameter >= pY1 && pX1 < sheep.x + sheep.dX + sheep.diameter && sheep.x + sheep.dX < pX2){
				return 3;
			} else if(pY1 < sheep.y && sheep.y + sheep.dY <= pY1 && pX1 < sheep.x + sheep.dX + sheep.diameter && sheep.x + sheep.dX < pX2){
				return 4;
			}
		}

		return 0;
	}
	
	public double distance(Sheep sheep){
		if(dir == Direction.vert && collides(sheep) != 0){
			return Math.abs(sheep.x - pX1);
		} else if(dir == Direction.horiz && collides(sheep) != 0){
			return Math.abs(sheep.y - pY1);
		} else {
			// double A = x - x1; // position of point rel one end of line
			// double B = y - y1;
			// double C = x2 - x1; // vector along line
			// double D = y2 - y1;
			// double E = -D; // orthogonal vector
			// double F = C;

			// double dot = A * E + B * F;
			// double len_sq = E * E + F * F;

			// return (double) Math.abs(dot) / Math.sqrt(len_sq);
		}

		return 1e18;
	}

	public Sheep reflect(Sheep sheep) {
		assert collides(sheep) != 0 : "NO COLLISION";
		
		if(dir == Direction.vert) {
			if(collides(sheep) == 1) {
				sheep.applyForce();
				sheep.dX *= -1;
				sheep.x = pX1 - sheep.diameter - Math.abs(pX1 - sheep.x - sheep.diameter);
			} else {
				sheep.applyForce();
				sheep.dX *= -1;
				sheep.x = pX1 + Math.abs(pX1 - sheep.x);
			}
		} else if(dir == Direction.horiz) {
			if(collides(sheep) == 3) {
				sheep.applyForce();
				sheep.dY *= -1;
				sheep.y = pY1 - sheep.diameter - Math.abs(pY1 - sheep.y - sheep.diameter);
			} else {
				sheep.applyForce();
				sheep.dY *= -1;
				sheep.y = pY1 + Math.abs(pY1 - sheep.y);
			}
		}
		
		return sheep;
	}
}