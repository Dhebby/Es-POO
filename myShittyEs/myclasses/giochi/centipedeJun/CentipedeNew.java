/*
condivide con i thread che ne invochino l'esecuzione un metodo
sincronizzato per lo spostamento del centopiedi e un metodo sincronizzato per la stampa del campo di gioco,
entambi ispirati a quelli elencati. In piu' essa condivida i campi pubblici boolean ok, byte mossa e int punti
che contengono rispettivamente lo stato di vita o morte del centopiedi, la sua direzione di marcia espressa con un
numero, e il punteggio conseguito fino a quel momento;
La testa del centopiedi sia raffigurata col carattere `O' invece che genericamente con `X'.
La velocita' del centopiedi sia di un passo ogni 2 secondi fino al 30 passo; successivamente
divenga di un passo al secondo.
Un nuovo punto venga accumulato non gia' dopo ogni passo, bensi' ogni qualvolta la testa del
centopiedi intercetta un premio che viene raffigurato con il carattere `$' nel campo di gioco. A quel
punto, il premio appena intercettato scompare ed e' sostituito da un nuovo premio che compare in una
posizione casuale del campo di gioco che non sia gia' occupata da una sezione del centopiedi.
MODIFICHE:
-si introduca nel gioco un centopiedi nemico, costantemente lungo 2 sezioni e denotato dai
caratteri `8H' in cui `8' e' la testa, il quale appare all'inizio in basso a destra nel campo di gioco e poi va
a caccia del premio procedendo alla stessa velocita' del centopiedi del giocatore. La strategia di caccia del
centopiedi nemico sia semplicemente quella di far collimare dapprima la posizione verticale, e poi quella
orizzontale della sua testa con la posizione del premio. Se raggiunge il premio prima del giocatore allora
acquista un punto; a quel punto, un nuovo premio nascera' in una casella in posizione casuale del campo di
gioco che non sia occupata da alcun centopiedi.
*/
package myclasses.giochi.centipedeJun;
import java.util.*;

public class CentipedeNew {
	private static final int LATO = 8;

	public boolean ok, ok_nemico; //true: vivo, false:morto
	public byte mossa;
	public int punti, punti_nemico;
	public int speed;
	public int premio_x, premio_y;

	private NodoCoda primo, nemico;
	private class NodoCoda {
		int x,y;
		NodoCoda prox;
		boolean testa;
	}

	public CentipedeNew() {
		primo = new NodoCoda();
		/*Random r = new Random();
		primo.x = r.nextInt(LATO);
		primo.y = r.nextInt(LATO);*/
		primo.x = LATO/2;
		primo.y = LATO/2;
		primo.prox = null;
		primo.testa = true;

		//modifica
		nemico = new NodoCoda();
		nemico.x = LATO-1;
		nemico.y = LATO-2;
		nemico.prox = new NodoCoda();
		nemico.prox.x = LATO-1;
		nemico.prox.y = LATO-1;
		nemico.prox.prox = null;

		ok = ok_nemico = true;
		punti = punti_nemico = 0;
		mossa = 1;
		speed = 2000; //ogni 2 secondi

		premio_x = (new Random()).nextInt(LATO/2);
		premio_y = (new Random()).nextInt(LATO/2);
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

		//spostamento nemico
		nemico.prox.x = nemico.x;
		nemico.prox.y = nemico.y;

		if(premio_y < nemico.y){
			nemico.y--;
		}else if(premio_y > nemico.y){
			nemico.y++;
		}else if(premio_x < nemico.x){
			nemico.x--;
		}else if(premio_x > nemico.x){
			nemico.x++;
		}

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

		//collisione con se stesso
		temp = primo.prox;
		while(temp != null) {
			if(primo.x==temp.x && primo.y==temp.y){
				ok = false;
				return;
			}
			temp = temp.prox;
		}

		//nemico collide col corpo del centipede
		temp = primo.prox;
		while(temp != null){
			if(nemico.x==temp.x && nemico.y==temp.y){
				ok_nemico = false;
				return;
			}
			temp = temp.prox;
		}

		//se esce dai bordi o se collide col corpo del nemico
		if (primo.x < 0 || primo.y < 0 || primo.x >= LATO || primo.y >= LATO || (primo.x==nemico.prox.x && primo.y==nemico.prox.y)){
			ok = false;
			return;
		}

		//nemico raggiunge premio
		if(nemico.x==premio_x && nemico.y==premio_y){
			punti_nemico++;
			premio_x = (new Random()).nextInt(LATO);
			premio_y = (new Random()).nextInt(LATO);
			while((nemico.x==premio_x && nemico.y==premio_y)||(nemico.prox.x==premio_x && nemico.prox.y==premio_y)){
				premio_x = (new Random()).nextInt(LATO);
				premio_y = (new Random()).nextInt(LATO);
			}

		}

		//centipede raggiunde premio
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
		field[nemico.x][nemico.y] = '8';
		field[nemico.prox.x][nemico.prox.y] = 'H';

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
		output += "Punti nemico: " + punti_nemico + "\n";
		
		return output;
	}
}