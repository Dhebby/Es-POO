/*
Quindici e' un gioco in cui 15 tessere quadrate numerate da 1 a 15 sono casualmente disposte a riempire
una matrice 4x4. Lo spazio libero permette al giocatore di spostare su esso una delle tessere confinanti, col
risultato di riempire lo spazio nel contempo liberando quello che conteneva la tessera che e' stata spostata.
L'obiettivo del gioco e' ordinare le tessere da 1 a 15 da sinistra a destra, riga dopo riga, ripetendo spostamenti
come quello sopra descritto. Una configurazione casuale della matrice e' raffigurata qui sotto:
=============
| 4|15| 3| 9|
|12|13|10| 7|
| 5| 8| 0| 6|
| 1|14| 2|11|
=============
Si progetti una classe di nome Quindici la quale realizza una versione del gioco appena descritto utilizzando
N 􀀀1 tessere su una matrice LxL, in cui L e N = L2 sono due costanti intere positive. Ogni oggetto Quindici
sara' costituito da una matrice di elementi di tipo byte ciascuno rappresentativo di una tessera numerata da 1
a N-1 piu' lo spazio libero, che per semplicita' sara' identificato dal valore 0.
*/
package myclasses.giochi;
import java.lang.Math;
import java.util.*;

public class Quindici{

	private static final int CASELLE = 16;
	private static final int LATO = (int)Math.sqrt(CASELLE);

	byte[][] griglia;

	/*
	costruisce una matrice LxL e dispone su essa le N􀀀1 tessere piu' lo spazio li-
	bero in modo casuale. Allo scopo si adoperi il metodo int nextInt(int) della classe java.util.Random,
	il quale restituisce un valore intero compreso tra 0 e n-1, in cui n e' il valore dell'argomento int passato
	come parametro;
	*/
	public Quindici(){
		griglia = new byte[LATO][LATO];

		//mi servo di un array ausiliario il quale viene riempito con i numeri random da 0 a N-1
		int[] seq = new int[CASELLE];
		Random r = new Random();
		Boolean insert = false;

		seq[0] = r.nextInt(CASELLE);
		for(int i=1; i<CASELLE; i++){
			seq[i] = r.nextInt(CASELLE);
			for(int j=i-1; j>=0; j--){
				if(seq[j] == seq[i]){
					insert = true;
				}
			}
			if(insert){
				i--;
				insert = false;
			}
		}

		//riempio la griglia copiando gli elementi dall'array ausiliario seq
		byte k = 0;
		for(int i=0; i<LATO; i++){
			for(int j=0; j<LATO; j++){
				griglia[i][j] = (byte)seq[k];
				k++;
			}
		}
	}

	/*
	se possibile sposta sullo spazio libero la casella posta sotto di esso,
	non eseguendo alcuna operazione se lo stesso spostamento viceversa e' impossibile;
	*/
	public void spostaSu(){
		for(int i=1; i<LATO; i++){
			for(int j=0; j<LATO; j++){
				if(griglia[i-1][j] == 0){
					griglia[i-1][j] = griglia[i][j];
					griglia[i][j] = 0;
					return;
				}
			}
		}
	}

	public void spostaGiu(){
		for(int i=0; i<LATO-1; i++){
			for(int j=0; j<LATO; j++){
				if(griglia[i+1][j] == 0){
					griglia[i+1][j] = griglia[i][j];
					griglia[i][j] = 0;
					return;
				}
			}
		}
	}

	public void spostaSx(){
		for(int i=0; i<LATO; i++){
			for(int j=1; j<LATO; j++){
				if(griglia[i][j-1] == 0){
					griglia[i][j-1] = griglia[i][j];
					griglia[i][j] = 0;
					return;
				}
			}
		}
	}

	public void spostaDx(){
		for(int i=0; i<LATO; i++){
			for(int j=0; j<LATO-1; j++){
				if(griglia[i][j+1] == 0){
					griglia[i][j+1] = griglia[i][j];
					griglia[i][j] = 0;
					return;
				}
			}
		}
	}

	/*
	rappresenta la configurazione della partita restituendo una stringa
	che, una volta stampata a schermo, appare esattamente coma la figura sopra;
	*/
	public String toString(){
		String s = "=============";
		for(byte[] riga : griglia){
			s += "\n|";
			for(byte col : riga){
				if(col<10){
					s += " "+col+"|";
				}else{
					s += col+"|";
				}
			}
		}
		s += "\n=============";
		return s;
	}

	/*
	riconosce una configurazione vincente in cui per semplicita' lo spazio
	libero (valore 0) e' collocato in alto a sinistra nella matrice.
	*/
	public boolean vinto(){
		byte k = 0;
		for(byte[] riga : griglia){
			for(byte col : riga){
				if(col != k){
					return false;
				}
				k++;
			}
		}
		return true;
	}
}