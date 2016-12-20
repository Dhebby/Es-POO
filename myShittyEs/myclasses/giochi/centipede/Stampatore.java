/*
a ogni secondo stampa la configurazione del campo di gioco e il punteggio
ottenuto fino a quel momento eseguendo un metodo public void run().
*/
package myclasses.giochi.centipede;

public class Stampatore implements Runnable{
	private CentipedeMigliorato partita;

	public Stampatore(CentipedeMigliorato partita){
		this.partita = partita;
	}

	public void run(){
		try{
			Thread.sleep(10);
		}catch(InterruptedException e){}
		
		while(partita.ok){
			System.out.println(partita);
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){}
		}
	}
}