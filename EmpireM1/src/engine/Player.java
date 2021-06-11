package engine;

import java.util.ArrayList;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import units.Army;
import units.Status;
import units.Unit;

public class Player {
	private String name;
	private ArrayList<City> controlledCities;
	private ArrayList<Army> controlledArmies;
	private double treasury;
	private double food;

	public Player(String name) {
		this.name = name;
		this.controlledCities = new ArrayList<City>();
		this.controlledArmies = new ArrayList<Army>();
	}
	
	public void recruitUnit(String name1, String name2, String type, String cityName) throws
	BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException{
		for(int i = 0; i < controlledCities.size(); i++){
			City c = controlledCities.get(i);
			if(c.getName().equals(cityName)){
				for(int j = 0; j < c.getMilitaryBuildings().size(); j++){
					MilitaryBuilding m = null;
					if(c.getMilitaryBuildings().get(j) instanceof ArcheryRange && type.equals("Archer"))
						m = c.getMilitaryBuildings().get(j);
					
					if(c.getMilitaryBuildings().get(j) instanceof Barracks && type.equals("Infantry"))
						m = c.getMilitaryBuildings().get(j);
					
					if(c.getMilitaryBuildings().get(j) instanceof Stable && type.equals("Cavalry"))
						m = c.getMilitaryBuildings().get(j);
					
					if(m != null){
						if(treasury >= m.getRecruitmentCost()){
							Unit u = m.recruit();
							
							c.getDefendingArmy().getUnits().add(u);
							u.setParentArmy(c.getDefendingArmy());
							
							treasury = treasury - m.getRecruitmentCost();
						} else{
							throw new NotEnoughGoldException("Not enough gold.");
						}
					}
				}
			}
		}
	}
	
	public void build(String type,String cityName) throws NotEnoughGoldException{
		for(int i = 0; i < controlledCities.size(); i++){
			City c = controlledCities.get(i);
			if(c.getName().equals(cityName)){
				if(type.equals("Farm")){
					int count = 0;
					for(int j = 0; j < c.getEconomicalBuildings().size(); j++){
						if(c.getEconomicalBuildings().get(j) instanceof Farm)
							count++;
					}
					if(count == 0){
						Farm f = new Farm();
						if(treasury >= f.getCost()){
							c.getEconomicalBuildings().add(f);
							treasury = treasury - f.getCost();
						} else{
							throw new NotEnoughGoldException("Not enough gold.");
						}
					}
				} else if(type.equals("Market")){
					int count = 0;
					for(int j = 0; j < c.getEconomicalBuildings().size(); j++){
						if(c.getEconomicalBuildings().get(j) instanceof Market)
							count++;
					}
					if(count == 0){
						Market m = new Market();
						if(treasury >= m.getCost()){
							c.getEconomicalBuildings().add(m);
							treasury = treasury - m.getCost();
						} else{
							throw new NotEnoughGoldException("Not enough gold.");
						}
					}
				} else if(type.equals("ArcheryRange")){
					int count = 0;
					for(int j = 0; j < c.getMilitaryBuildings().size(); j++){
						if(c.getMilitaryBuildings().get(j) instanceof ArcheryRange)
							count++;
					}
					if(count == 0){
						ArcheryRange a = new ArcheryRange();
						if(treasury >= a.getCost()){
							c.getMilitaryBuildings().add(a);
							treasury = treasury - a.getCost();
						} else{
							throw new NotEnoughGoldException("Not enough gold.");
						}
					}
				} else if(type.equals("Barracks")){
					int count = 0;
					for(int j = 0; j < c.getMilitaryBuildings().size(); j++){
						if(c.getMilitaryBuildings().get(j) instanceof Barracks)
							count++;
					}
					if(count == 0){
						Barracks b = new Barracks();
						if(treasury >= b.getCost()){
							c.getMilitaryBuildings().add(b);
							treasury = treasury - b.getCost();
						} else{
							throw new NotEnoughGoldException("Not enough gold.");
						}
					}
				} else if(type.equals("Stable")){
					int count = 0;
					for(int j = 0; j < c.getMilitaryBuildings().size(); j++){
						if(c.getMilitaryBuildings().get(j) instanceof Stable)
							count++;
					}
					if(count == 0){
						Stable s = new Stable();
						if(treasury >= s.getCost()){
							c.getMilitaryBuildings().add(s);
							treasury = treasury - s.getCost();
						} else{
							throw new NotEnoughGoldException("Not enough gold.");
						}
					}
				}
			}
		}
	}
	
	public void upgradeBuilding(Building b)throws NotEnoughGoldException,
	BuildingInCoolDownException, MaxLevelException{
		if(treasury >= b.getUpgradeCost()){
			int cost = b.getUpgradeCost();
			b.upgrade();
			treasury = treasury - cost;
		}else{
			throw new NotEnoughGoldException("Not enough gold.");
		}
	}
	
	public void initiateArmy(City city,Unit unit){
		Army army = new Army(city.getName());
		
		city.getDefendingArmy().getUnits().remove(unit);
		
		army.getUnits().add(unit);
		unit.setParentArmy(army);
		
		controlledArmies.add(army);
	}
	
	public void laySiege(Army army,City city) throws TargetNotReachedException,
	FriendlyCityException{
		if(army.getCurrentLocation().equals(city.getName()))
			throw new TargetNotReachedException("Army doesn't reach target city to attack.");
	
		for(int i = 0; i < controlledCities.size(); i++){
			City c = controlledCities.get(i);
			if(c.getName().equals(army.getCurrentLocation()))
				throw new FriendlyCityException("Cannot attack a friendly city");
		}
		army.setCurrentStatus(Status.BESIEGING);
		city.setUnderSiege(true);
		city.setTurnsUnderSiege(0);
	}
	
	public double getTreasury() {
		return treasury;
	}

	public void setTreasury(double treasury) {
		this.treasury = treasury;
	}

	public double getFood() {
		return food;
	}

	public void setFood(double food) {
		this.food = food;
	}

	public String getName() {
		return name;
	}

	public ArrayList<City> getControlledCities() {
		return controlledCities;
	}

	public ArrayList<Army> getControlledArmies() {
		return controlledArmies;
	}
}
