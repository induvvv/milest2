package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Market extends EconomicBuilding {

	public Market() {
		super(1500, 700);
	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException{
		super.upgrade();
		super.setLevel(super.getLevel() + 1);
		super.setUpgradeCost(1000);
		super.setCoolDown(true);
	}
	
	public int harvest(){
		if(super.getLevel() == 1)
			return 1000;
		
		else if(super.getLevel() == 2)
			return 1500;
		
		else
			return 2000;
	}
	
}
