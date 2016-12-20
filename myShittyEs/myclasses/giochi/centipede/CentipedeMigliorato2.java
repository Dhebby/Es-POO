/*
condivide con i thread che ne invochino l'esecuzione un metodo
sincronizzato per lo spostamento del centopiedi e un metodo sincronizzato per la stampa del campo di gioco,
entambi ispirati a quelli elencati. In piu' essa condivida i campi pubblici boolean ok, byte mossa e int punti
che contengono rispettivamente lo stato di vita o morte del centopiedi, la sua direzione di marcia espressa con un
numero, e il punteggio conseguito fino a quel momento;
MODIFICHE:
-la testa del centopiedi sia raffigurata col carattere `O' invece che genericamente con `X'.
-la velocita' del centopiedi sia di un passo ogni 2 secondi fino al 30 passo; successivamente
divenga di un passo al secondo.
-un nuovo punto venga accumulato non gia' dopo ogni passo, bensi' ogni qualvolta la testa del
centopiedi intercetta un premio che viene raffigurato con il carattere `$' nel campo di gioco. A quel
punto, il premio appena intercettato scompare ed e' sostituito da un nuovo premio che compare in una
posizione casuale del campo di gioco che non sia gia' occupata da una sezione del centopiedi.
*/
package myclasses.giochi.centipede;
import java.util.*;

public class CentipedeMigliorato2 {
	private static final int LATO = 8;

	public boolean ok; //true: vivo, false:morto
	public byte mossa;
	public int punti;
	public int speed;
	public int premio_x, premio_y;

	private NodoCoda primo;
	private class NodoCoda {
		int x,y;
		NodoCoda prox;
		boolean testa;
	}

	public CentipedeMigliorato2() {
		primo = new NodoCoda();

		/*Random r = new Random();
		primo.x = r.nextInt(LATO);
		primo.y = r.nextInt(LATO);*/
		primo.x = LATO/2;
		primo.y = LATO/2;

		primo.prox = null;
		primo.testa = true;

		ok = true;
		punti = 0;
		mossa = 1;
		speed = 2000; //ogni 2 secondi

		premio_x = (new Random()).nextInt(LATO);
		premio_y = (new Random()).nextInt(LATO);
	}

	public synchronized void setSpeed(int speed){
		this.speed = speed;
	}

	public synchronized int getSpeed(){
		return speed;
	}

	public synchronized void sposta(int x, int y, boolean accoda) {
		NodoCoda temp;
		primo.testa = false;

		NodoCoda nuovo = new NodoCoda();
		nuovo.testa = true;
		nuovo.x = primo.x + x;
		nuovo.y = primo.y + y;
		nuovo.prox = primo;
		primo = nuovo;

		temp = primo;
		while (temp.prox.prox != null)
			temp = temp.prox;

		if(!accoda)
			temp.prox = null;

		temp = primo.prox;
		while(temp != null) {
			if(primo.x==temp.x && primo.y==temp.y){
				ok = false;
				return;
			}
			temp = temp.prox;
		}

		if (primo.x < 0 || primo.y < 0 || primo.x >= LATO || primo.y >= LATO){
			ok = false;
		}else{
			ok = true;
		}

		if(primo.x==premio_x && primo.y==premio_y){
			punti++;
			premio_x = (new Random()).nextInt(LATO);
			premio_y = (new Random()).nextInt(LATO);
			temp = primo;
			do{
				if(temp.x==premio_x && temp.y==premio_y){
					premio_x = (new Random()).nextInt(LATO);
					premio_y = (new Random()).nextInt(LATO);
					temp = primo;
				}else{
					temp = temp.prox;
				}
			}while(temp != null);
		}
		return;
	}

	public synchronized String toString() {
		char field[][] = new char[LATO][LATO];
		NodoCoda temp = primo;

		for(int j=0; j<LATO; j++){
			for(int i=0; i<LATO; i++){
				field[i][j] = ' ';
			}
		}

		field[premio_x][premio_y] = '$';

		do{
			field[temp.x][temp.y] = temp.testa? 'O' : 'X';
			temp = temp.prox;
		} while(temp != null);

		String output = "|";

		for (int i=0; i<LATO; i++)
			output += "-";
		output += "|\n";

		for (int j=0; j<LATO; j++) {
			output += "|";
			for (int i=0; i<LATO; i++)
				output += field[i][j];	
			output += "|\n";
		}

		output += "|";
		for (int i=0; i<LATO; i++)
			output += "-";
		output += "|\n";

		punti++;
		output += "Punti: " + punti + "\n";
		
		return output;
	}
}