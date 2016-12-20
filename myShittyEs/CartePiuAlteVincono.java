import myclasses.carte.*;
import prog.io.ConsoleOutputManager;
import prog.io.ConsoleInputManager;

public class CartePiuAlteVincono{

	public static final int C_MANO = 5; //carte in mano ai giocatori

	public static void main(String[] args){

		ConsoleOutputManager out = new ConsoleOutputManager();
		ConsoleInputManager in = new ConsoleInputManager();

		Mazzo<CartaFranceseOrdinabile> mazzo = new Mazzo<>();

		CartaFranceseOrdinabile[] g1 = new CartaFranceseOrdinabile[C_MANO]; 
		CartaFranceseOrdinabile[] g2 = new CartaFranceseOrdinabile[C_MANO];

		//riempio il mazzo
		for(int i=1; i<=CartaFranceseOrdinabile.N_SEMI; i++){
			for(int j=1; j<=CartaFranceseOrdinabile.N_NUMERI; j++){
				mazzo.add(new CartaFranceseOrdinabile(i, j));
			}
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
		out.println("-----------------------------");
		out.println("Carta max G1: " + cercaMax(g1));
		out.println("Carta max G2: " + cercaMax(g2));
		out.println("VINCITORE: "+(cercaMax(g1).compareTo(cercaMax(g2)) >0 ? "g1" : "g2"));

	}

	public static CartaFranceseOrdinabile cercaMax(CartaFranceseOrdinabile[] mano){
		CartaFranceseOrdinabile max = mano[0];
		for(int i=1; i<C_MANO; i++){
			if(max.compareTo(mano[i])<0){
				max = mano[i];
			}
		}
		return max;
	}
}