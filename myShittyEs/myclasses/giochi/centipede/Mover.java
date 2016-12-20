/*
a ogni secondo sposta il centopiedi nella direzione di marcia determinata dal campo
mossa eseguendo un metodo public void run();
*/
package myclasses.giochi.centipede;

public class Mover implements Runnable{
	private CentipedeMigliorato partita;
	private int passi;
	private int diff = 3;

	public Mover(CentipedeMigliorato partita){
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

			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){}
		}
	}
}