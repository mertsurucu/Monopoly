
public class Chance extends Card {

	public Chance() {
		// TODO Auto-generated constructor stub
	}
	public void takeCard(int count,Player player,Player player2){
			if(count==0){//first card
				player.pLocation=1;
				player.pMoney+=200;
			}
			else if(count==1){//second card
				if(player.pLocation>27){//it goes square 27
					player.pMoney+=200;
				}
				player.pLocation=27;
			}
			else if(count==2){//third card
				player.pLocation=player.pLocation-3;//it goes 3 square back
			}
			else if(count==3){//fourth card
				player.pMoney-=15;
			}
			else if(count==4){//fifth card
				player.pMoney+=150;
			}
			else if(count==5){//sixth card
				player.pMoney+=100;
			}

		
	}
	

}
