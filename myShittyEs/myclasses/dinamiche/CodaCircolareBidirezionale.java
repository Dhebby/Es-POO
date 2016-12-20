/*
Una coda circolare e una coda il cui ultimo elemento contiene un riferimento al primo elemento della coda,
garantendo in tal modo la circolarita' della struttura dati.
Una coda bidirezionale e una coda in cui coppie di elementi adiacenti si riferiscono a vicenda, garantendo
in tal modo la possibilita' di visitare la coda in avanti oppure all'indietro.
Si progetti una classe generica di nome CodaCircolareBidirezionale, la quale realizza una coda circolare
bidirezionale in cui ciascun nodo, modellato come come un oggetto NodoCoda, e' costituito da un campo dato per
il dato (il cui tipo e' rappresentato dal parametro della classe generica) piu' due campi puntaDx e puntaSx i quali
contengono rispettivamente un riferimento al nodo adiacente successivo e a quello precedente. Vi sia, infine, un
campo della classe inizio che si riferisce a quello che convenzionalmente chiameremo nodo di partenza della
coda circolare bidirezionale.
*/
package myclasses.dinamiche;

public class CodaCircolareBidirezionale<E>{

	private NodoCoda inizio;
	private class NodoCoda{
		E dato;
		NodoCoda puntaDx, puntaSx;
	}

	/*
	iniziando dal nodo di partenza costruisce una coda in cui ogni nodo
	contiene il corrispondente dato presente nell'array passato come argomento
	*/
	public CodaCircolareBidirezionale(E[] dati){
		inizio = new NodoCoda();
		inizio.dato = dati[0];
		inizio.puntaSx = inizio;
		inizio.puntaDx = inizio;

		NodoCoda tmp = inizio;

		for(int i=1; i<dati.length; i++){
			tmp.puntaDx = new NodoCoda();
			tmp = tmp.puntaDx;
			tmp.dato = dati[i];
		}

		tmp.puntaDx = inizio;
		tmp = inizio;

		for(int i=0; i<dati.length; i++){
			tmp.puntaDx.puntaSx = tmp;
			tmp = tmp.puntaDx;
		}
	}

	/*
	sposta il riferimento al nodo di partenza in avanti di n nodi
	*/
	public void ruotaOrario(int n){
		NodoCoda tmp = inizio;
		for(int i=0; i<n; i++){
			tmp = tmp.puntaDx;
		}
		inizio = tmp;
	}

	/*
	sposta il riferimento al nodo di partenza all'indietro di n nodi
	*/
	public void ruotaAntiOrario(int n){
		NodoCoda tmp = inizio;
		for(int i=0; i<n; i++){
			tmp = tmp.puntaSx;
		}
		inizio = tmp;
	}

	/*
	restituisce il dato contenuto nel nodo di partenza
	*/
	public E leggi(){
		return inizio.dato;
	}

	/*
	sovrascrive un dato nel nodo di partenza
	*/
	public void sovrascrivi(E dati){
		inizio.dato = dati;
	}

	/*
	se trova il dato riferito dall'argomento fa diventare nodo di partenza
	il corrispondente nodo e restituisce true, altrimenti false
	*/
	public boolean trova(E x){
		NodoCoda tmp;
		if(inizio.dato.equals(x)){
			return true;
		}else{
			tmp = inizio.puntaDx;
			while(tmp != inizio){
				if(tmp.dato.equals(x)){
					tmp = inizio;
					return true;
				}
				tmp = tmp.puntaDx;
			}
			return false;
		}
	}

	/*
	stampa ordinatamente i dati contenuti nella coda opportunamente separati
	dalla stringa separatore, in avanti se flag direzione vale true, viceversa
	all'indietro se lo stesso flag vale false
	*/
	public String toString(String separatore, boolean direzione){
		NodoCoda tmp;
		String s;

		if(direzione){
			tmp = inizio.puntaDx;
		}else{
			tmp = inizio.puntaSx;
		}

		s = inizio.dato.toString();

		while(tmp != inizio){
			s += separatore + tmp.dato.toString();
			if(direzione){
				tmp = tmp.puntaDx;
			}else{
				tmp = tmp.puntaSx;
			}
		}
		return s;
	}
}