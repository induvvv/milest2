package units;

import exceptions.FriendlyFireException;

public class Cavalry extends Unit {

	public Cavalry(int level, int maxSoldierConunt, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
		super(level, maxSoldierConunt, idleUpkeep, marchingUpkeep, siegeUpkeep);
	}
	
	public void attack(Unit target) throws FriendlyFireException{
		super.attack(target);
		double factor = 0;
		
		if(target instanceof Archer){
			switch(super.getLevel()){
				case 1: factor = 0.5;
				break;
				case 2: factor = 0.6;
				break;
				case 3: factor = 0.7;
				break;
			}
		} else if(target instanceof Infantry){
			switch(super.getLevel()){
				case 1: factor = 0.3;
				break;
				case 2: factor = 0.4;
				break;
				case 3: factor = 0.5;
				break;
			}
		} else{
			switch(super.getLevel()){
				case 1: factor = 0.2;
				break;
				case 2: factor = 0.2;
				break;
				case 3: factor = 0.3;
				break;
			}
		}
		target.setCurrentSoldierCount(target.getCurrentSoldierCount() - (int)(factor * this.getCurrentSoldierCount()));
	}
	
}
