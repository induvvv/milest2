package units;

import java.util.ArrayList;

import exceptions.MaxCapacityException;

/**
 * @author mohammad.hussein
 *
 */
public class Army{
	private Status currentStatus;
	private ArrayList<Unit> units;
	private int distancetoTarget;
	private String target;
	private String currentLocation;
	@SuppressWarnings("unused")
	private final int maxToHold = 10;

	public Army(String currentLocation) {
		this.currentLocation=currentLocation;
		currentStatus=Status.IDLE;
		units=new ArrayList<Unit>();
		distancetoTarget = -1;
		target="";
	}
	
	public void realocateUnit(Unit unit) throws MaxCapacityException{
		if(units.size() == maxToHold)
			throw new MaxCapacityException("Maximum capacity reached.");
		
		units.add(unit);
		unit.getParentArmy().getUnits().remove(unit);
	}
	
	public void handleAttackedUnit(Unit unit){
		if(unit.getCurrentSoldierCount() == 0)
			units.remove(unit);
	}
	
	public double foodNeeded(){
		double result = 0;
		for(int i = 0; i < units.size(); i++){
			switch(currentStatus){
				case IDLE: 
					result = result + units.get(i).getCurrentSoldierCount() * units.get(i).getIdleUpkeep();
					break;
				case MARCHING:
					result = result + units.get(i).getCurrentSoldierCount() * units.get(i).getMarchingUpkeep();
					break;
				case BESIEGING:
					result = result + units.get(i).getCurrentSoldierCount() * units.get(i).getSiegeUpkeep();
					break;
			}
		} return result;
	}
	
	public Status getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}

	public ArrayList<Unit> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}

	public int getDistancetoTarget() {
		return distancetoTarget;
	}

	public void setDistancetoTarget(int distancetoTarget) {
		this.distancetoTarget = distancetoTarget;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public int getMaxToHold() {
		return maxToHold;
	}
	
}
