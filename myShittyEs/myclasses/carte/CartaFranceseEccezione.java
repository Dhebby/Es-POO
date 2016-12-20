/*
Si modifichino le classi CartaFrancese e CartaTrevisana nelle corrispondenti 
classi CartaFranceseEccezione e CartaTrevisanaEccezione in modo che ciascuna 
di esse sollevi un'eccezione a fronte del tentativo di creazione di una carta 
che non può appartenere al mazzo. L'eccezione, non controllata, venga definita 
creando un oggetto della classe CartaOutOfBoundsException il quale informa su 
seme o numero scorretti. La carta da gioco errata viene comunque creata. 
*/
package myclasses.carte;

public class CartaFranceseEccezione extends Carta{

	public static final int N_SEMI = 4;
	public static final int N_NUMERI = 13;

	static final String[] semi = {"Cuori", "Quadri", "Fiori", "Picche"};
	static final String[] figure = {"Jack", "Regina", "Re"};
	static final String[] asso = {"Asso"};

	public CartaFranceseEccezione(int s, int n){
		seme = s;
		numero = n;
		valore = true;

		//controllo eccezione
		if(s<1 || s>N_SEMI){
			throw new CartaOutOfBoundsException("Seme errato: " + s);
		}
		if(n<1 || n>N_NUMERI){
			throw new CartaOutOfBoundsException("Numero errato: " + n);
		}
	}

	public String toString(){
		String s;

		switch (this.numero){
			case 1: s = asso[0];
					break;
			case 11: s = figure[0];
					break;
			case 12: s = figure[1];
					break;
			case 13: s = figure[2];
					break;
			default: s = "" + this.numero;
					break;
		}
		return s += " di " + semi[this.seme-1];
	}
}