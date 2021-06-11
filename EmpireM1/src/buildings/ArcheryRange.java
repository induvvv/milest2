package buildings;

import units.Archer;
import units.Unit;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;

public class ArcheryRange extends MilitaryBuilding {

	public ArcheryRange() {
		super(1500, 800, 400);
	}
	
	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException{
		 if(super.getCurrentRecruit() == super.getMaxRecruit())
			 throw new MaxRecruitedException("Cannot upgrade, maximum recruit reached.");
		 
		 if(super.isCoolDown() == true)
			 throw new BuildingInCoolDownException("Cannotupgrade, building cooling down.");
		 
		 Archer a;
		 if(super.getLevel() == 1)
			 a = new Archer(1, 60, 0.4, 0.5, 0.6);
		 
		 else if(super.getLevel() == 2)
			 a = new Archer(2, 60, 0.4, 0.5, 0.6);
		 
		 else
			 a = new Archer(3, 70, 0.5, 0.6, 0.7);
		 
		 super.setCurrentRecruit(super.getCurrentRecruit() + 1);
		 return a;	 
	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException{
		super.upgrade();
		
		if(super.getLevel() == 1){
			super.setLevel(2);
			super.setUpgradeCost(700);
			super.setRecruitmentCost(450);
		} else{
			super.setLevel(3);
			super.setRecruitmentCost(500);
		}
		super.setCoolDown(true);
	}

}
