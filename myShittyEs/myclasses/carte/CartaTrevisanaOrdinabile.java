package myclasses.carte;

public class CartaTrevisanaOrdinabile extends CartaTrevisana implements Comparable<CartaTrevisanaOrdinabile>{
	public CartaTrevisanaOrdinabile(int s, int n){
		super(s, n);
	}

	public int compareTo(CartaTrevisanaOrdinabile c){
		if(this.numero < c.numero){
			return -1;
		}else if(this.numero > c.numero){
			return 1;
		}else{
			if(this.seme < c.seme){
				return -1;
			}else if(this.seme > c.seme){
				return 1;
			}else{
				return 0;
			}
		}
	}
}
