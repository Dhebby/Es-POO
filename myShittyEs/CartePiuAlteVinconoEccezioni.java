/*
Si modifichi la classe CartePiuAlteVincono in una nuova classe 
CartePiuAlteVinconoEccezioni, il cui metodo cercaMax() solleva un'eccezione non 
controllata nel caso in cui venga tentata una ricerca della carta di valor 
massimo in un array vuoto, inviando al chiamante la stringa "Array vuoto!".

Il metodo main della stessa classe intercetti l'eccezione stampando la stringa 
"Un giocatore ha eccezionalmente esaurito le carte...", interrompendo la 
partita, e infine stampando il punteggio conseguito dai giocatori fino a quel 
momento. Si verifichi che il metodo intercetta la stessa eccezione. Si 
verifichi anche che un errore sulla generazione del mazzo di carte solleva 
un'eccezione del tipo CartaOutOfBoundsException
*/
import myclasses.carte.*;
import prog.io.ConsoleOutputManager;
import prog.io.ConsoleInputManager;

public class CartePiuAlteVinconoEccezioni{

	public static final int C_MANO = 5; //carte in mano ai giocatori

	public static void main(String[] args){

		ConsoleOutputManager out = new ConsoleOutputManager();
		ConsoleInputManager in = new ConsoleInputManager();

		Mazzo<CartaFranceseOrdinabile> mazzo = new Mazzo<>();

		CartaFranceseOrdinabile[] g1 = new CartaFranceseOrdinabile[C_MANO]; 
		CartaFranceseOrdinabile[] g2 = new CartaFranceseOrdinabile[C_MANO];

		int puntiG1, puntiG2;
		CartaFranceseOrdinabile maxG1, maxG2;

		//riempio il mazzo
		try{
			for(int i=1; i<=CartaFranceseOrdinabile.N_SEMI; i++){
				for(int j=1; j<=CartaFranceseOrdinabile.N_NUMERI; j++){
					mazzo.add(new CartaFranceseOrdinabile(i, j));
				}
			}
		}catch(CartaOutOfBoundsException e){
			out.println(e);
		}

		//gioco delle carte piu alte

		mazzo.mescola();
		//distribuisco una carta a testa ai giocatori
		int p = 0;
		int q = 0;
		for(int i=0; i<2*C_MANO; i++){
			if(i%2 > 0){
				g2[p] = mazzo.pescaCarta();
				p++;
			}else{
				g1[q] = mazzo.pescaCarta();
				q++;
			}
		}

		//stampo le carte dei giocatori
		out.println("----- Carte Giocatore 1 -----");
		for(int i=0; i<C_MANO; i++){
			out.println(g1[i]);
		}
		out.println("----- Carte Giocatore 2 -----");
		for(int i=0; i<C_MANO; i++){
			out.println(g2[i]);
		}

		//vincitore
		puntiG1 = puntiG2 = 0;
		for(int i=1; i<=C_MANO; i++){

			try{
				maxG1 = cercaMax(g1);
				maxG2 = cercaMax(g2);

				out.println("-----------------------------");
				out.println("Carta max G1: " + maxG1);
				out.println("Carta max G2: " + maxG2);
				out.print("Vince la "+i+"a mano : ");
				if(maxG1.compareTo(maxG2)>0){
					out.println("g1");
					puntiG1++;
				}else{
					out.println("g2");
					puntiG2++;
				}

			}catch(ArrayIndexOutOfBoundsException e){
				out.println(e + "Un giocatore ha eccezionalmente esaurito le carte...");
				break;
			}

			in.readLine();
		}
		
		out.println("-----------------------------");
		out.println("-----------------------------");
		out.println("Punti finali:\n\tg1 : "+puntiG1+"\n\tg2 : "+puntiG2);
		out.println("Vince la partita: " + (puntiG1>puntiG2 ? "g1" : "g2"));
		
	}

	public static CartaFranceseOrdinabile cercaMax(CartaFranceseOrdinabile[] mano){
		
		if(mano.length == 0){
			throw new ArrayIndexOutOfBoundsException("Array vuoto!");
		}

		CartaFranceseOrdinabile max = mano[0];
		int k=0;
		for(int j=0; j<C_MANO; j++){
			k++;
			if(mano[j]!=null){
				max = mano[j];
				break;
			}
		}

		int j=0;
		for(int i=k; i<C_MANO; i++){
			if(mano[i]!= null && max.compareTo(mano[i])<0){
				max = mano[i];
				j = i;
			}
		}
		mano[j] = null;

		return max;
	}
}