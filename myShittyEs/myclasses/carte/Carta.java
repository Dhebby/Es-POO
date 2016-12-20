package myclasses.carte;

public class Carta{
	protected int numero;
	protected int seme;
	protected boolean valore; //true francesi, false trevisane

	public Carta(int s, int n, boolean v){
		numero = n;
		seme = s;
		valore = v;
	}

	public boolean controllaValore(int n, boolean v){
		if(n>10 && v==false){
			return false;
		}else{
			return true;
		}
	}

}