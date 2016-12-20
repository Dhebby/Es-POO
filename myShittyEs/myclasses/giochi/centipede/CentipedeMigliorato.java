/*
condivide con i thread che ne invochino l'esecuzione un metodo
sincronizzato per lo spostamento del centopiedi e un metodo sincronizzato per la stampa del campo di gioco,
entambi ispirati a quelli elencati. In piu' essa condivida i campi pubblici boolean ok, byte mossa e int punti
che contengono rispettivamente lo stato di vita o morte del centopiedi, la sua direzione di marcia espressa con un
numero, e il punteggio conseguito fino a quel momento;
*/
package myclasses.giochi.centipede;
import java.util.*;

public class CentipedeMigliorato {
	private static final int LATO = 8;

	//MIGLIORATO
	public boolean ok; //true: vivo, false:morto
	public byte mossa;
	public int punti;

	private NodoCoda primo;
	private class NodoCoda {
		int x,y;
		NodoCoda prox;
	}

	public CentipedeMigliorato() {
		Random r = new Random();
		primo = new NodoCoda();
		primo.x = r.nextInt(LATO);
		primo.y = r.nextInt(LATO);
		primo.prox = null;

		//MIGLIORATO
		ok = true;
		punti = 0;
		mossa = 1;
	}

	//sincronizzato void
	public synchronized void sposta(int x, int y, boolean accoda) {
		NodoCoda temp;

		NodoCoda nuovo = new NodoCoda();
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
				//modifica
				ok = false;
				return;
			}
			temp = temp.prox;
		}

		if (primo.x < 0 || primo.y < 0 || primo.x >= LATO || primo.y >= LATO){
			//modifica
			ok = false;
			return;
		}else{
			//modifica
			ok = true;
			return;
		}
	}

	//sincronizzato
	public synchronized String toString() {
		boolean field[][] = new boolean[LATO][LATO];
		NodoCoda temp = primo;

		do{
			field[temp.x][temp.y] = true;
			temp = temp.prox;
		} while(temp != null);

		String output = "|";

		for (int i=0; i<LATO; i++)
			output += "-";

		output += "|\n";

		for (int j=0; j<LATO; j++) {
			output += "|";
			for (int i=0; i<LATO; i++)
				if (field[i][j])
					output += "X";
				else
					output += " ";
				output += "|\n";
		}
		output += "|";

		for (int i=0; i<LATO; i++)
			output += "-";

		//modifica
		punti++;
		output += "Punti: " + punti + "\n";
		
		return output;
	}
}