/*
la classe CartaOutOfBoundsException estenda l'eccezione 
ArrayIndexOutOfBoundsException passando il messaggio d'errore alla superclasse.
*/
package myclasses.carte;

public class CartaOutOfBoundsException extends ArrayIndexOutOfBoundsException{
	public CartaOutOfBoundsException(String msg){
		super(msg);
	}
}