package buildings;

import units.Cavalry;
import units.Unit;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;

public class Stable extends MilitaryBuilding {

	public Stable() {
		super(2500, 1500, 600);
	}

	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException{
		 if(super.getCurrentRecruit() == super.getMaxRecruit())
			 throw new MaxRecruitedException("Cannot upgrade, maximum recruit reached.");
		 
		 if(super.isCoolDown() == true)
			 throw new BuildingInCoolDownException("Cannotupgrade, building cooling down.");
		 
		 Cavalry c;
		 if(super.getLevel() == 1)
			 c = new Cavalry(1, 40, 0.6, 0.7, 0.75);
		 
		 else if(super.getLevel() == 2)
			 c = new Cavalry(2, 40, 0.6, 0.6, 0.75);
		 
		 else
			 c = new Cavalry(3, 60, 0.7, 0.8, 0.9);
		 
		 super.setCurrentRecruit(super.getCurrentRecruit() + 1);
		 return c;	 
	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException{
		super.upgrade();
		
		if(super.getLevel() == 1){
			super.setLevel(2);
			super.setUpgradeCost(2000);
			super.setRecruitmentCost(650);
		} else{
			super.setLevel(3);
			super.setRecruitmentCost(700);
		}
		super.setCoolDown(true);
	}
	
}
