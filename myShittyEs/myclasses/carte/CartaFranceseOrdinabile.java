package myclasses.carte;

public class CartaFranceseOrdinabile extends CartaFrancese implements Comparable<CartaFranceseOrdinabile>{
	public CartaFranceseOrdinabile(int s, int n){
		super(s, n);
	}

	public int compareTo(CartaFranceseOrdinabile c){
		if(this.numero < c.numero){
			return -1;
		}else if(this.numero > c.numero){
			return 1;
		}else{
			if(this.seme < c.seme){
				return -1;
			}else{
				return 1;
			}
		}
	}
}
