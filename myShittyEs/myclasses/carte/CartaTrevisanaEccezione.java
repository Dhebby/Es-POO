package myclasses.carte;

public class CartaTrevisanaEccezione extends Carta{

	public static final int N_SEMI = 4;
	public static final int N_NUMERI = 10;

	static final String[] semi = {"Coppe", "Bastoni", "Spade", "Denari"};
	static final String[] figure = {"Fante", "Cavallo", "Re"};

	public CartaTrevisanaEccezione(int s, int n){
		seme = s;
		numero = n;
		valore = false;

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
			case 8: s = figure[0];
					break;
			case 9: s = figure[1];
					break;
			case 10: s = figure[2];
					break;
			default: s = "" + this.numero;
					break;
		}
		return s += " di " + semi[this.seme-1];
	}
}