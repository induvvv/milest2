package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Farm extends EconomicBuilding {

	public Farm() {
		super(1000, 500);
	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException{
		super.upgrade();
		super.setLevel(super.getLevel() + 1);
		super.setUpgradeCost(700);
		super.setCoolDown(true);
	}
	
	public int harvest(){
		if(super.getLevel() == 1)
			return 500;
		
		else if(super.getLevel() == 2)
			return 700;
		
		else
			return 1000;
	}

}
