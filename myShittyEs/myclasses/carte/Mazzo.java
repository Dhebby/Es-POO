package myclasses.carte;
import prog.utili.Sequenza;

public class Mazzo<E> extends Sequenza<E>{

	public E pescaCarta(){
		E[] carte = (E[])this.toArray();
		this.remove(carte[0]);
		return carte[0];
	}
}