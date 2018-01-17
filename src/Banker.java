
public class Banker extends User {//banker class to calculate the money
	int bankerMoney;
	public Banker(int bankerMoney){
		this.bankerMoney=bankerMoney;
	}
	 Read r2=new Read();
	@Override
	public void changeLocation(int spaces,Player player) {		
	}
	public void sellProperty(int id){
		r2.readJsonProperty();
		int cost=r2.propertyCost.get(r2.propertyId.get(id));
		bankerMoney=bankerMoney+cost;
	}
	public int minusMoney(int minus){
		return bankerMoney-minus;
	}@Override
	public void buyProperty(int cost,Player player) {	
	}

}
