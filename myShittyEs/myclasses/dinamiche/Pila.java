/*
Una versione ricorsiva alternativa di una pila, o stack, prevede di organizzare 
i nodi come oggetti NodoPila, ciascuno dei quali è costituito da un campo per 
il dato più un campo che contiene un riferimento alla pila sottostante. 
In altre parole, definiamo la pila come formata da:
- un riferimento a una struttura vuota, oppure
- un nodo in cima a una pila.
Si noti l'analogia con la definizione di albero binario.

All'interno del package dinamiche si realizzi una classe di nome Pila definita 
come sopra, in cui il tipo di dato E sia generico. 
si faccia uso ove possibile di procedure ricorsive.
*/
package myclasses.dinamiche;

public class Pila<E>{

	//classe nidificata (private: visibile solo dentro Pila)
	private NodoPila p;
	private class NodoPila{
		E dato;
		Pila<E> succ;
	}

	private static int conta = 0; //variabile x il conteggio delle pile successive

	/*
	costruttore: costruisce una pila vuota;
	*/
	public Pila(){
		p = null;
	}

	/*
	in grado di scorrere la pila inserendo un nodo 
	contenente il dato x IN FONDO (non in cima!);
	*/
	public void inserisci(E x){
		if(p == null){
			p = new NodoPila();
			p.dato = x;
			p.succ = new Pila<E>();
		}else{
			p.succ.inserisci(x);
		}
	}

	/*
	in grado di scorrere la pila restituendo la 
	posizione (1,2,...) del nodo in cui occorre per la prima volta il dato x, 
	oppure il valore -1 se lo stesso dato non è presente;
	*/
	public int trova(E x){
		int pos;
		if(p != null){
			conta++;
			if(p.dato.equals(x)){
				pos = conta;
				conta = 0;
				return pos;
			}else{
				return p.succ.trova(x);
			}
		}else{
			conta = 0;
			return -1;
		}
	}
	/*
	in grado di privare la pila del nodo in cima;
	*/
	public void eliminaInizio(){
		if(p != null){
			p = p.succ.p;
		}
	}

	/*
	in grado di privare la pila del nodo in fondo;
	*/
	public void eliminaFine(){
		if(p != null){
			if(p.succ.p != null){
				p.succ.eliminaFine();
			}else{
				p = null;
			}
		}
	}

	/*
	in grado di ribaltare la pila;
	*/
	public void ribalta(){
		E tmp;
		if(p != null){
			tmp = p.dato;
			eliminaInizio();
			ribalta();
			inserisci(tmp);
		}
	}

	/*
	restituisce una stringa contenente 
	i dati partendo dal nodo in cima, interponendo il separatore tra dati adiacenti 
	e chiudendo la stringa con il segno ".".
	*/
	public String toString(String separatore){
		if(p != null){
			return p.dato.toString() + separatore + p.succ.toString(separatore);
		}else{
			return ".";
		}
	}

}