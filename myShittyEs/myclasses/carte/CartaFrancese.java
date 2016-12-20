package myclasses.carte;

public class CartaFrancese extends Carta{

	public static final int N_SEMI = 4;
	public static final int N_NUMERI = 13;

	static final String[] semi = {"Cuori <3", "Quadri []", "Fiori 8B", "Picche <^>"};
	static final String[] figure = {"Jack", "Regina", "Re"};
	static final String[] asso = {"Asso"};

	public CartaFrancese(int s, int n){
		super(s, n, true);
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