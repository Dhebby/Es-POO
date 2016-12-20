/*
Si modifichi la classe CartePiuAlteVinconoMigliorato nella classe 
CartePiuAlteVinconoMiglioratoEccezioni, la quale adopera le funzionalità di 
MazzoEccezione e MazzoOrdinatoEccezione in luogo delle precedenti Mazzo e 
MazzoOrdinato. Si verifichi con l'iserimento di opportuni errori nel rispettivo 
metodo main il corretto funzionamento delle eccezioni realizzate 
rispettivamente nelle classi MazzoEccezione e MazzoOrdinatoEccezione.
*/
import myclasses.carte.*;
import prog.io.ConsoleOutputManager;
import prog.io.ConsoleInputManager;
import prog.utili.*;

public class CartePiuAlteVinconoMiglioratoEccezioni{
	public static void main(String[] args){

		final int C_MANO = 5; //carte in mano ai giocatori

		ConsoleOutputManager out = new ConsoleOutputManager();
		ConsoleInputManager in = new ConsoleInputManager();

		MazzoEccezione<CartaFranceseOrdinabile> mazzo = new MazzoEccezione<>();

		//mani
		Sequenza<CartaFranceseOrdinabile> m1 = new Sequenza<>();
		Sequenza<CartaFranceseOrdinabile> m2 = new Sequenza<>();

		//riempio il mazzo
		/*
		for(int i=1; i<=CartaFranceseOrdinabile.N_SEMI; i++){
			for(int j=1; j<=CartaFranceseOrdinabile.N_NUMERI; j++){
				mazzo.add(new CartaFranceseOrdinabile(i, j));
			}
		}
		*/

		//gioco delle carte piu alte

		mazzo.mescola();

		//distribuisco una carta a testa ai giocatori
		for(int i=0; i<2*C_MANO; i++){
			if(i%2 > 0){
				m2.add(mazzo.pescaCarta());
			}else{
				m1.add(mazzo.pescaCarta());
			}
		}

		//giocatori
		MazzoOrdinatoEccezione<CartaFranceseOrdinabile> g1 = new MazzoOrdinatoEccezione<>(m1); 
		MazzoOrdinatoEccezione<CartaFranceseOrdinabile> g2 = new MazzoOrdinatoEccezione<>(m2);

		//stampo le carte dei giocatori
		out.println("----- Carte Giocatore 1 -----");
		for(CartaFranceseOrdinabile c : g1){
			out.println(c);
		}
		out.println("----- Carte Giocatore 2 -----");
		for(CartaFranceseOrdinabile c : g2){
			out.println(c);
		}

		//vincitore
		out.println("-----------------------------");
		out.println("Carta max G1: " + g1.pescaCartaMaggiore());
		out.println("Carta max G2: " + g2.pescaCartaMaggiore());
		out.println("VINCITORE: "+(g1.pescaCartaMaggiore().compareTo(g2.pescaCartaMaggiore()) >0 ? "g1" : "g2"));

	}
}
