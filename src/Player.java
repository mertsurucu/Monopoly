import java.util.List;

public class Player extends User{
		 int pMoney;
		 int pLocation;
		 int railRoads;
		 List<Integer> player;
		 Read r2=new Read();
	public Player(int pMoney,int pLocation,int railRoads,List<Integer> player) {//it gets players money and their location
		this.pMoney=pMoney;
		this.pLocation=pLocation;
		this.railRoads=railRoads;//and how many railroads they so that we can calculate the rent 
		this.player=player;
	}@Override
	public void buyProperty(int id,Player player){// if the player does not have this property, buy it 
		r2.readJsonProperty();
		int cost=r2.propertyCost.get(r2.propertyId.get(id));
		player.pMoney=player.pMoney-cost;

	}@Override
	public void changeLocation(int spaces,Player player){//when we get spaces from command lines players location change 
		player.pLocation=player.pLocation+spaces; 
		if(player.pLocation>40){//to perceives one turn 
			 player.pMoney+=200;
			 player.pLocation=(player.pLocation)%41;
			 player.pLocation++;
		 } 
		 
	}
	
	public void writeFile(String fileContent){
		WriteFile write = new WriteFile(fileContent);
		write.write("output.txt");
	}

	



}
