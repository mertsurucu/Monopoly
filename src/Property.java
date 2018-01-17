
public class Property{

	public int calculateRent(int id,int dice,int railRoads){
		Read r1=new Read();
		r1.readJsonProperty();
		int rent;
		if(id==13||id==29){//if the property is company 
			rent=dice*4;
		}
		else if(id==6||id==16||id==26||id==36){//if the property is station
			rent=railRoads*25;
		}
		else{//if the property is place
			int cost=r1.propertyCost.get(r1.propertyId.get(id));
			if(cost>=0 && cost<=2000){
				rent=(cost*40)/100;}
			else if(cost>=2001 && cost<=3000){
				rent=(cost*30)/100;}
			else {
				rent=(cost*35)/100;}
		}
		return rent;}//calculates the rent and returns it

}
