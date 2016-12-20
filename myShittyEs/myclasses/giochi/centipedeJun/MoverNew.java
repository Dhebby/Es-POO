/*
a ogni secondo sposta il centopiedi nella direzione di marcia determinata dal campo
mossa eseguendo un metodo public void run();
la velocita' del centopiedi sia di un passo ogni 2 secondi fino al 30 passo; successivamente
divenga di un passo al secondo.
*/
package myclasses.giochi.centipedejun;

public class MoverNew implements Runnable{
	private CentipedeNew partita;
	private int passi;
	private int diff = 3;

	public MoverNew(CentipedeNew partita){
		this.partita = partita;
		passi = 0;
	}

	public void run(){
		while(true){
			passi ++;

			switch (partita.mossa){
				case 1 : partita.sposta(0, 1, passi%diff == 0); //verso l'alto
						break;
				case 2 : partita.sposta(-1, 0, passi%diff == 0); //verso sx
						break;
				case 3 : partita.sposta(1, 0, passi%diff == 0); //verso dx
						break;
				case 4 : partita.sposta(0, -1, passi%diff == 0); //verso il basso
						break;
			}

			if(passi==30){
				partita.setSpeed(1000);
			}
			try{
				Thread.sleep(partita.getSpeed());
			}catch(InterruptedException e){}
		}
	}
}