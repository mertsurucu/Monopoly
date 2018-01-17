import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Square {//it's an operation class 
	List<String> command;//it gets command 
	public Square(List<String> command2) {//from txt file using constructor
		this.command=command2;
	}
	List<Integer> chanceSquare = new ArrayList<Integer>();
	List<Integer> chestSquare = new ArrayList<Integer>();
	List<Integer> player1 = new ArrayList<Integer>();
	List<Integer> player2 = new ArrayList<Integer>();
	/*
	 * players starting moneys and locations
	 */
	Player p1=new Player(15000,1,0,player1);
	Player p2=new Player(15000,1,0,player2);
	User b1=new Banker(100000);

	public void main(List<String> command2) throws IOException {
		String a=";";//this variable for to differentiate show() and moves
		int count=0,count2=0,jail=-1,jail2=-1,exit=0;
		chanceSquare.add(8);//these are the squares of chance cards
		chanceSquare.add(23);
		chanceSquare.add(37);
		chestSquare.add(3);//these are the squares of chest community cards
		chestSquare.add(18);
		chestSquare.add(34);
		Read r2=new Read();
		Chance c1=new Chance();
		Community co=new Community();
		String pw = "";//this is the string to write results
		int prevMoney1 = 0,prevMoney2 = 0;
		for(String i:command2){//i variable gets commands
			r2.readJsonProperty();
			r2.readJsonList();
			int forward=0;
			String[] s=i.split(";");//split according to ; and gets forward variable
			if(i.contains(a)){
				forward=Integer.parseInt(s[1]);}//gets the string of number and convert it to integer
			if(i.contains(a)&&p1.pMoney>=0&&p2.pMoney>=0){	
				/*if i variable contains ; that it tells us one of the players going to move
				 *  
				 * PLAYER1
				 */
				if(s[0].contains("Player 1")){//perceives Player 1's move
					prevMoney1=p1.pMoney;//gets previous moneys to variable 
					prevMoney2=p2.pMoney;
					if(jail==-1){
						p1.changeLocation(forward, p1);//gets the location and what does it do in that square 
						if(r2.propertyId.containsKey(p1.pLocation)){//perceives if its property
							if(p2.player.contains(p1.pLocation)){//if the player2 has this property pay rent
								Property pro=new Property();
								p1.pMoney=p1.pMoney-(pro.calculateRent(p1.pLocation,forward,p2.railRoads));
								p2.pMoney=p2.pMoney+(pro.calculateRent(p1.pLocation,forward,p2.railRoads));
								pw += "Player 1\t"+forward+"\t"+p1.pLocation+
										"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
										"Player 1 paid rent for "+r2.propertyId.get(p1.pLocation)+"\n";//add the result to string
							}
							else if (p1.player.contains(p1.pLocation)){//if the player already has it just stays there do nothing
								pw+="Player 1\t"+forward+"\t"+p1.pLocation+//add the result to string
										"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
										"Player 1 has "+r2.propertyId.get(p1.pLocation)+"\n";

							}
							else{//if no one has this property buy it
								p1.buyProperty(p1.pLocation,p1);
								((Banker) b1).sellProperty(p1.pLocation);
								p1.player.add(p1.pLocation);//add player 1 properties list
								pw += "Player 1\t"+forward+"\t"+p1.pLocation+//add the result to string
										"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
										"Player 1 bought "+r2.propertyId.get(p1.pLocation)+"\n";	
							}
						}
						else if(chanceSquare.contains(p1.pLocation)){//place into chance square
							List<String> chanceCard=r2.chanceList;			
							count=count%6;//there is only 6 card
							c1.takeCard(count, p1,p2);//calls take card function
							if(r2.propertyId.containsKey(p1.pLocation)){// if the squares is property
								if(p2.player.contains(p1.pLocation)){//if the player2 has this property pay rent
									Property pro=new Property();
									p1.pMoney=p1.pMoney-(pro.calculateRent(p1.pLocation,forward,p2.railRoads));
									p2.pMoney=p2.pMoney+(pro.calculateRent(p1.pLocation,forward,p2.railRoads));
									pw+="Player 1\t"+forward+"\t"+p1.pLocation+//add the result to string
											"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
											"Player 1 draw "+chanceCard.get(count)+" Player 1 paid rent for "
											+r2.propertyId.get(p1.pLocation)+"\n";
								}
								else if(!p1.player.contains(p1.pLocation)){//buys the property
									p1.buyProperty(p1.pLocation,p1);
									p1.player.add(p1.pLocation);

									pw+="Player 1\t"+forward+"\t"+p1.pLocation+//add the result to string
											"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
											"Player 1 draw "+chanceCard.get(count)+
											" Player 1 bought "+r2.propertyId.get(p1.pLocation)+"\n";}

								else if (p1.player.contains(p1.pLocation)){//if the player already has it just stays there do nothing
									pw+="Player 1\t"+forward+"\t"+p1.pLocation+
											"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+//add the result to string
											"Player 1 draw "+chanceCard.get(count)
											+"Player 1 has "+r2.propertyId.get(p1.pLocation)+"\n";}
							}else if(p1.pLocation==5){//if the square is tax square pay the tax(for go back 3 spaces)
								p1.pMoney-=100;//pays 100TL
								pw +="Player 1\t"+forward+"\t"+p1.pLocation+//add the result to string
										"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
										"Player 1 draw "+chanceCard.get(count)+
										" Player 1 paid Tax\n";
							}
							else if(p1.pLocation==34){//its community chest card (for go back 3 spaces)
								List<String> communityCard=r2.communityChestList;
								count2=count2%11;
								co.takeCard(count2, p1, p2);
								pw += "Player 1\t"+forward+"\t"+p1.pLocation+
										"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+//add the result to string
										"Player 1 draw "+chanceCard.get(count)+
										" Player 1 draw "+communityCard.get(count2)+"\n";
								count2++;//its means take the card and put it the bottom of the deck
							}
							else{//if the chance card just pays you a money
								pw+="Player 1\t"+forward+"\t"+p1.pLocation+//add the result to string
										"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
										"Player 1 draw "+chanceCard.get(count)+"\n";
							}	
							count++;}//its means take the card and put it the bottom of the deck
						else if(chestSquare.contains(p1.pLocation)){//player in chest cards square						
							List<String> communityCard=r2.communityChestList;
							count2=count2%11;//there is only 6 card
							co.takeCard(count2, p1, p2);//it takes a card by using the method
							pw += "Player 1\t"+forward+"\t"+p1.pLocation+//add the result to string
									"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
									"Player 1 draw "+communityCard.get(count2)+"\n";

							count2++;//its means take the card and put it the bottom of the deck
						}
						else if(p1.pLocation==31){//player in go to jail square
							p1.pLocation=11;
							jail++;//this for counting the 3 turns jail
							pw += "Player 1\t"+forward+"\t"+p1.pLocation+//add the result to string
									"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
									"Player 1 went to jail\n";
						}
						else if(p1.pLocation==11){//this for counting the 3 turns jail
							jail++;
							pw += "Player 1\t"+forward+"\t"+p1.pLocation+//add the result to string
									"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
									"Player 1 went to jail\n";
						}
						else if(p1.pLocation==21){
							pw += "Player 1\t"+forward+"\t"+p1.pLocation+//add the result to string
									"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
									"Player 1 is in Free Parking\n";
							//free parking
						}
						else if(p1.pLocation==39 || p1.pLocation==5){//tax Squares
							p1.pMoney-=100;
							pw += "Player 1\t"+forward+"\t"+p1.pLocation+
									"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+//add the result to string
									"Player 1 paid Tax\n";
						}else if(p1.pLocation==1){
							pw+="Player 1\t"+forward+"\t"+p1.pLocation+//add the result to string
									"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
									"Player 1 is in GO Square\n";
						}
						else{//and when the player in the prison it going to add those to output
							jail++;
							p1.pLocation=11;
							pw += "Player 1\t"+forward+"\t"+p1.pLocation+
									"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+//add the result to string
									"Player 1 in jail (count="+(jail)+")\n";
							if(jail==3){
								jail=-1;}					
						}}}
				/*
				 * PLAYER2
				 */
				else if(s[0].contains("Player 2")){
					prevMoney1=p1.pMoney;
					prevMoney2=p2.pMoney;
					if(jail2==-1){
						p2.changeLocation(forward,p2);//gets the location and what does it do in that square
						if(r2.propertyId.containsKey(p2.pLocation)){
							if(p1.player.contains(p2.pLocation)){
								Property pro2=new Property();
								p2.pMoney=p2.pMoney-(pro2.calculateRent(p2.pLocation,forward,p1.railRoads));
								p1.pMoney=p1.pMoney+(pro2.calculateRent(p2.pLocation,forward,p1.railRoads));
								pw += "Player 2\t"+forward+"\t"+p2.pLocation+//add the result to string
										"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
										"Player 2 paid rent for "+r2.propertyId.get(p2.pLocation)+"\n";
							}else if (p2.player.contains(p2.pLocation)){//if the player already has it just stays there do nothing
								pw+="Player 2\t"+forward+"\t"+p2.pLocation+
										"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+//add the result to string
										"Player 2 has "+r2.propertyId.get(p2.pLocation)+"\n";
							}
							else{//if no one has this property buy it
								p2.buyProperty(p2.pLocation,p2);
								p2.player.add(p2.pLocation);
								((Banker) b1).sellProperty(p2.pLocation);
								pw+="Player 2\t"+forward+"\t"+p2.pLocation+//add the result to string
										"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
										"Player 2 bought "+r2.propertyId.get(p2.pLocation)+"\n";
							}}

						else if(chanceSquare.contains(p2.pLocation)){
							List<String> chanceCard=r2.chanceList;
							count=count%6;
							c1.takeCard(count, p2,p1);
							if(r2.propertyId.containsKey(p2.pLocation)){
								if(p1.player.contains(p2.pLocation)){// if its belong to another player  pay rent
									Property pro=new Property();
									p2.pMoney=p2.pMoney-(pro.calculateRent(p2.pLocation,forward,p1.railRoads));
									p1.pMoney=p1.pMoney+(pro.calculateRent(p2.pLocation,forward,p1.railRoads));
									pw+="Player 2\t"+forward+"\t"+p2.pLocation+//add the result to string
											"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
											"Player 2 draw "+chanceCard.get(count)+" "+
											"Player 2 paid rent for "+r2.propertyId.get(p2.pLocation)+"\n";
								}
								else if(!p2.player.contains(p2.pLocation)){//if no one has this property buy it
									p2.buyProperty(p2.pLocation,p2);
									p2.player.add(p2.pLocation);
									pw+="Player 2\t"+forward+"\t"+p2.pLocation+
											"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+//add the result to string
											"Player 2 draw "+chanceCard.get(count)+
											" Player 2 bought "+r2.propertyId.get(p2.pLocation)+"\n";
								}else if (p2.player.contains(p2.pLocation)){//if the player already has it just stays there do nothing
									pw+="Player 2\t"+forward+"\t"+p2.pLocation+//add the result to string
											"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
											"Player 2 draw "+chanceCard.get(count)+
											" Player 2 has "+r2.propertyId.get(p2.pLocation)+"\n";}
							}else if(p2.pLocation==5){
								p2.pMoney-=100;
								pw += "Player 2\t"+forward+"\t"+p2.pLocation+//add the result to string
										"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
										"Player 2 draw "+chanceCard.get(count)+
										" Player 2 paid Tax\n";
							}
							else if(p2.pLocation==34){
								List<String> communityCard=r2.communityChestList;
								count2=count2%11;
								co.takeCard(count2, p2, p1);
								pw += "Player 2\t"+forward+"\t"+p2.pLocation+//add the result to string
										"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
										"Player 2 draw "+chanceCard.get(count)+
										" Player 2 draw "+communityCard.get(count2)+"\n";
								count2++;
							}
							else{
								pw+="Player 2\t"+forward+"\t"+p2.pLocation+//add the result to string
										"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
										"Player 2 draw "+chanceCard.get(count)+"\n";
							}count++;
						}else if(chestSquare.contains(p2.pLocation)){
							List<String> communityCard=r2.communityChestList;		
							count2=count2%11;
							co.takeCard(count2, p2, p1);
							pw += "Player 2\t"+forward+"\t"+p2.pLocation+//add the result to string
									"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
									"Player 2 draw "+
									communityCard.get(count2)+"\n";

							count2++;	
						}
						else if(p2.pLocation==31){//player in go to jail square
							p2.pLocation=11;
							jail2++;
							pw += "Player 2\t"+forward+"\t"+p2.pLocation+
									"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+//add the result to string
									"Player 2 went to jail\n";
						}
						else if(p2.pLocation==21){
							pw += "Player 2\t"+forward+"\t"+p2.pLocation+//add the result to string
									"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
									"Player 2 is in Free Parking\n";//free parking
						}
						else if(p2.pLocation==11){
							jail2++;
						}
						else if(p2.pLocation==39 || p2.pLocation==5){
							p2.pMoney-=100;
							pw += "Player 2\t"+forward+"\t"+p2.pLocation+//add the result to string
									"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
									"Player 2 paid Tax\n";
						}
						else if(p2.pLocation==1){
							pw+="Player 2\t"+forward+"\t"+p2.pLocation+//add the result to string
									"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
									"Player 2 is in GO Square\n";
						}
					}
					else{//and when the player in the prison it going to add those to output
						jail2++;
						p2.pLocation=11;
						pw += "Player 2\t"+forward+"\t"+p2.pLocation+//add the result to string
								"\t"+p1.pMoney+"\t"+p2.pMoney+"\t"+
								"Player 2 in jail (count="+(jail2)+")\n";
						if(jail2==3){
							jail2=-1;
						}
					}
				}}
			else if(i.contains("show()")){//show()
				String have1="have: ";
				String have2="have: ";
				int c=0,c2=0;
				for(int haveId:p1.player){
					if(c>0){
						have1+=", ";
					}
					have1+=r2.propertyId.get(haveId);
					c++;
				}
				for(int haveId:p2.player){
					if(c2>0){
						have2+=", ";
					}
					have2+=r2.propertyId.get(haveId);
					c2++;
				}
				pw+="-----------------------------------------------------------------------------------------------------------\n";
				pw+="Player 1"+"\t"+p1.pMoney+"\t"+have1+"\n";
				pw+="Player 2"+"\t"+p2.pMoney+"\t"+have2+"\n";//add the result to string
				pw+="Banker\t"+(130000-(p1.pMoney+p2.pMoney))+"\n";
				if(p1.pMoney>p2.pMoney){
					pw+="Winner Player 1"+"\n";
				}
				else if(p1.pMoney==p2.pMoney){
					pw+="Draw\n";
				}else{
					pw+="Winner Player 2"+"\n";
				}
				pw+="-----------------------------------------------------------------------------------------------------------\n";
			}
			if(((p1.pMoney<0||p2.pMoney<0)&&exit==0)//its looking for the bankrupt 
					||command2.get(command2.size()-1)==i){
				if((p1.pMoney<0||p2.pMoney<0)){
					/*
					 * deleting the last line of output(string variable)
					 * because the last line is writes the minus of money  
					 */
					String temp=pw.substring(pw.lastIndexOf("\nP"), pw.lastIndexOf("\n")); 
					pw=pw.replace(temp, "");
				}
				if(p1.pMoney<0){//if the player 1 bankrupt  
					pw += "Player 1\t"+forward+"\t"+p1.pLocation+
							"\t"+prevMoney1+"\t"+prevMoney2+"\t"+//add the result to string
							"Player 1 goes bankrupt\n";
				}
				if(p2.pMoney<0){//if the player 2 bankrupt  
					pw += "Player 2\t"+forward+"\t"+p2.pLocation+
							"\t"+prevMoney1+"\t"+prevMoney2+"\t"+//add the result to string
							"Player 2 goes bankrupt\n";}
				String have1="have: ";
				String have2="have: ";
				int c=0,c2=0;
				for(int haveId:p1.player){
					if(c>0){
						have1+=", ";
					}
					have1+=r2.propertyId.get(haveId);
					c++;
				}
				for(int haveId:p2.player){
					if(c2>0){
						have2+=", ";
					}
					have2+=r2.propertyId.get(haveId);
					c2++;
				}
				pw+="-----------------------------------------------------------------------------------------------------------\n";
				if((p1.pMoney<0||p2.pMoney<0)){
					pw+="Player 1"+"\t"+prevMoney1+"\t"+have1+"\n";//add the result to string
					pw+="Player 2"+"\t"+prevMoney2+"\t"+have2+"\n";}
				else{
					pw+="Player 1"+"\t"+p1.pMoney+"\t"+have1+"\n";
					pw+="Player 2"+"\t"+p2.pMoney+"\t"+have2+"\n";}
				pw+="Banker\t"+(130000-(prevMoney1+prevMoney2))+"\n";//add the result to string
				if(p1.pMoney>p2.pMoney){
					pw+="Winner Player 1"+"\n";
				}
				else if(p1.pMoney==p2.pMoney){
					pw+="Player 1 Tie Player 2"+"\n";
				}else{
					pw+="Winner Player 2"+"\n";
				}
				pw+="-----------------------------------------------------------------------------------------------------------\n";
				exit++;
			}	
			p1.writeFile(pw);//write the result to output.txt
		}
	}}
