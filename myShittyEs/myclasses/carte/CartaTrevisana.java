package myclasses.carte;

public class CartaTrevisana extends Carta{

	public static final int N_SEMI = 4;
	public static final int N_NUMERI = 10;

	static final String[] semi = {"Coppe 52", "Bastoni II", "Spade X", "Denari O"};
	static final String[] figure = {"Fante", "Cavallo", "Re"};

	public CartaTrevisana(int s, int n){
		super(s, n, false);
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