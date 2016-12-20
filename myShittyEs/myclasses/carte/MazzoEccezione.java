/*
Si modifichi la classe Mazzo nella classe MazzoEccezione in modo che il metodo 
vedi() intercetti l'eccezione nel caso in cui venga tentata l'estrazione di 
una carta da un mazzo vuoto, in tal caso stampando la stringa "Mazzo vuoto!" 
e restituendo un riferimento null.
*/
package myclasses.carte;
import prog.utili.Sequenza;

public class MazzoEccezione<E> extends Sequenza<E>{

	public E pescaCarta(){
		E[] carte = (E[])this.toArray();

		try{
			this.remove(carte[0]);
			return carte[0];

		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Mazzo vuoto!");
			return null;
		}
	}
}