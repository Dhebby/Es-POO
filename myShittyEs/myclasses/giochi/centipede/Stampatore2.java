/*
a ogni secondo stampa la configurazione del campo di gioco e il punteggio
ottenuto fino a quel momento eseguendo un metodo public void run().
*/
package myclasses.giochi.centipede;

public class Stampatore2 implements Runnable{
	private CentipedeMigliorato2 partita;

	public Stampatore2(CentipedeMigliorato2 partita){
		this.partita = partita;
	}

	public void run(){
		try{
			Thread.sleep(10);
		}catch(InterruptedException e){}
		
		while(partita.ok){
			System.out.println(partita);
			try{
				Thread.sleep(partita.getSpeed());
			}catch(InterruptedException e){}
		}
	}
}