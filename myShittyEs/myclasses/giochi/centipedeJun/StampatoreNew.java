/*
a ogni secondo stampa la configurazione del campo di gioco e il punteggio
ottenuto fino a quel momento eseguendo un metodo public void run().
*/
package myclasses.giochi.centipedeJun;

public class StampatoreNew implements Runnable{
	private CentipedeNew partita;

	public StampatoreNew(CentipedeNew partita){
		this.partita = partita;
	}

	public void run(){
		try{
			Thread.sleep(10);
		}catch(InterruptedException e){}
		
		while(partita.ok && partita.ok_nemico){
			System.out.println(partita);
			try{
				Thread.sleep(partita.getSpeed());
			}catch(InterruptedException e){}
		}

		if(!partita.ok){
			System.out.println("Hai perso.\n")
		}
		if(!partita.ok_nemico){
			System.out.println("Il nemico ha perso.\n")
		}

		System.out.println("Hai totalizzato "+ partita.punti + (partita.punti==1 ? " punto.":" punti."));
		System.out.println("Il nemico ha totalizzato "+ partita.punti_nemico + (partita.punti_nemico==1 ? " punto.":" punti."));
	}
}