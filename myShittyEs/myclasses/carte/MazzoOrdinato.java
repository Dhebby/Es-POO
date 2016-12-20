package myclasses.carte;
import prog.utili.SequenzaOrdinata;
import prog.utili.Sequenza;
import java.util.Iterator;

public class MazzoOrdinato<E extends java.lang.Comparable<? super E>> extends SequenzaOrdinata<E>{

	public MazzoOrdinato(Sequenza<E> s){
		super(s);
	} 

	public E pescaCartaMaggiore(){
		Iterator<E> mazzo = this.iterator();
		E carta;

		do{
			carta = mazzo.next();
		}while(mazzo.hasNext());

		return carta;
	}
}

