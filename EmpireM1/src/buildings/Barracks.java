package buildings;

import units.Infantry;
import units.Unit;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;

public class Barracks extends MilitaryBuilding {

	public Barracks() {
		super(2000, 1000, 500);
	}
	
	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException{
		 if(super.getCurrentRecruit() == super.getMaxRecruit())
			 throw new MaxRecruitedException("Cannot upgrade, maximum recruit reached.");
		 
		 if(super.isCoolDown() == true)
			 throw new BuildingInCoolDownException("Cannotupgrade, building cooling down.");
		 
		 Infantry i;
		 if(super.getLevel() == 1)
			 i = new Infantry(1, 50, 0.5, 0.6, 0.7);
		 
		 else if(super.getLevel() == 2)
			 i = new Infantry(2, 50, 0.5, 0.6, 0.7);
		 
		 else
			 i = new Infantry(3, 60, 0.6, 0.7, 0.8);
		 
		 super.setCurrentRecruit(super.getCurrentRecruit() + 1);
		 return i;	 
	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException{
		super.upgrade();
		
		if(super.getLevel() == 1){
			super.setLevel(2);
			super.setUpgradeCost(1500);
			super.setRecruitmentCost(550);
		} else{
			super.setLevel(3);
			super.setRecruitmentCost(600);
		}
		super.setCoolDown(true);
	}

}
