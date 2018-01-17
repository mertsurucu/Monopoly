

public class Community extends Card {
	public void takeCard(int count,Player player,Player player2){//there is 6 different card for chest
		
			if(count==0){//first card
				player.pLocation=1;
				player.pMoney+=200;		
			}
			else if(count==1){//second card
					player.pMoney+=75;	
			}
			else if(count==2){//third card
				player.pMoney-=50;}
			else if(count==3){//fourth card
				player.pMoney+=10;
				player2.pMoney-=10;
			}
			else if(count==4){//fifth card
				player.pMoney+=50;
				player2.pMoney-=50;
			}
			else if(count==5){//sixth card
				player.pMoney+=20;
			}
			else if(count==6){//seventh card
				player.pMoney+=100;
			}
			else if(count==7){//eighth card
				player.pMoney-=100;
			}
			else if(count==8){//ninth card
				player.pMoney-=50;
			}
			else if(count==9){//tenth card
				player.pMoney+=100;
			}
			else if(count==10){//eleventh card
				player.pMoney+=50;
			}

	}

	
	
}
