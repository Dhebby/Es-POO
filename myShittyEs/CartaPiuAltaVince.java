import myclasses.carte.*;
import prog.io.ConsoleOutputManager;
import prog.io.ConsoleInputManager;

public class CartaPiuAltaVince{
	public static void main(String[] args){

		ConsoleOutputManager out = new ConsoleOutputManager();
		ConsoleInputManager in = new ConsoleInputManager();

		Mazzo<CartaFranceseOrdinabile> mazzo = new Mazzo<CartaFranceseOrdinabile>();

		CartaFranceseOrdinabile g1, g2;

		//riempio il mazzo
		for(int i=1; i<=CartaFranceseOrdinabile.N_SEMI; i++){
			for(int j=1; j<=CartaFranceseOrdinabile.N_NUMERI; j++){
				mazzo.add(new CartaFranceseOrdinabile(i, j));
			}
		}

		//gioco della carta piu alta
		mazzo.mescola();
		out.println("Premi y per iniziare il gioco, n per vedere il mazzo.");
		while(!mazzo.isEmpty() && in.readLine().equals("y")){
			g1 = mazzo.pescaCarta();
			g2 = mazzo.pescaCarta();
			out.println("Giocatore 1: "+g1);
			out.println("Giocatore 2: "+g2);
			out.println("Vincitore: " + (g1.compareTo(g2)>0 ? "G1" : "G2"));
			out.println();
			out.println("Vuoi rigiocare? premi y. Altrimenti n.");
		}

		//stampo mazzo
		out.println("Carte restanti nel mazzo:\n");
		for(CartaFranceseOrdinabile c : mazzo){
			out.println(c.toString());
		}
	}
}