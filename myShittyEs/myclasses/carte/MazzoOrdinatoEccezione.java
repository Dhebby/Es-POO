/*
Si modifichi la classe MazzoOrdinato nella classe MazzoOrdinatoEccezione in 
modo che il metodo vedi() controlli l'eccezione che viene sollevata nel caso 
in cui viene tentata l'estrazione di una carta da un mazzo vuoto, inviando al 
chiamante la stringa "Mazzo ordinato vuoto!".
*/
package myclasses.carte;
import prog.utili.SequenzaOrdinata;
import prog.utili.Sequenza;
import java.util.*;

public class MazzoOrdinatoEccezione<E extends java.lang.Comparable<? super E>> extends SequenzaOrdinata<E>{

	public MazzoOrdinatoEccezione(Sequenza<E> s){
		super(s);
	} 

	public E pescaCartaMaggiore(){
		Iterator<E> mazzo = this.iterator();
		E carta;

		try{
			do{
				carta = mazzo.next();
			}while(mazzo.hasNext());

			remove(carta);
			return carta;
			
		}catch(NoSuchElementException e){
			System.out.println("Mazzo ordinato vuoto!");
			return null;
		}
	}
}

