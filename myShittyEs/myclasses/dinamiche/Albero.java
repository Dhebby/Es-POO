/*
Una versione meno elegante di un albero di ricerca prevede di organizzare i 
nodi come oggetti NodoAlbero, ciascuno dei quali è costituito da un campo 
per il dato più due campi che contengono un riferimento sx e dx ancora di 
tipo NodoAlbero, rispettivamente al nodo radice del figlio sinistro e di 
quello destro.

All'interno di un package denominato dinamiche si definisca una classe Albero 
la quale realizza un albero di ricerca definito come sopra, in cui il tipo di 
dato E sia generico.
*/
package myclasses.dinamiche;
import java.util.*;

public class Albero<E extends Comparable<? super E>> {

	private NodoAlbero a;
	private class NodoAlbero{
		E dato;
		NodoAlbero sx, dx;

		NodoAlbero(E x){
			dato = x;
			sx = dx = null;
		}

		/*
		in grado di trovare e restituire il dato x, ovvero di 
		inserirlo correttamente nell'albero di ricerca in tal caso restituendo un 
		riferimento nullo;
		*/
		E trovaInserisci(E x){

			if(x.compareTo(dato) < 0){
				if(sx != null){
					return sx.trovaInserisci(x);
				}else{
					sx = new NodoAlbero(x);
					return null;
				}

			}else if(x.compareTo(dato) > 0){
				if(dx != null){
					return dx.trovaInserisci(x);
				}else{
					dx = new NodoAlbero(x);
					return null;
				}

			}else{
				return dato;
			}
		}

		/*
		in grado di ristrutturare l'albero di ricerca in una sua 
		versione speculare, nella quale l'ordine di ricerca é a sua volta rovesciato;
		*/
		void ribalta(){
			NodoAlbero tmp;

			if(sx != null){
				if(dx != null){
					sx.ribalta();
					dx.ribalta();
				}else{
					sx.ribalta();
				}
			}else if(sx==null && dx!=null){
				dx.ribalta();
			}

			tmp = sx;
			sx = dx;
			dx = tmp;
		}

		/*
		in grado di stampare i dati secondo 
		l'ordinamento dato dall'albero, interponendo il separatore tra stampe di dati 
		adiacenti.
		*/
		String toString(String separatore){
			if(sx != null){
				if(dx != null){
					return sx.toString(separatore) + separatore + dato + separatore + dx.toString(separatore);
				}else{
					return sx.toString(separatore) + separatore + dato;
				}
			}else{
				if(dx != null){
					return dato + separatore + dx.toString(separatore);
				}else{
					return dato.toString();
				}
			}
		}


	}

	/*
	costruisce un albero contenente un dato nella radice;
	*/
	public Albero(E x){
		a = new NodoAlbero(x);
	}

	public E trovaInserisci(E x){
		return a.trovaInserisci(x);
	}

	public void ribalta(){
		a.ribalta();
	}

	public String toString(String separatore){
		return a.toString(separatore);
	}
}