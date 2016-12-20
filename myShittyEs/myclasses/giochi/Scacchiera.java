/*
Si progetti una classe di nome Scacchiera la quale realizza una comune scacchiera da gioco quadrata
formata da quadranti alternati bianchi e neri, in cui ciascun quadrante puo' essere libero oppure ospitare una
pedina bianca o una pedina nera come quelle, ad esempio, per il gioco della dama o degli scacchi. Ogni oggetto
Scacchiera e' costituito da 
i) un campo colore che identifica il colore di ogni quadrante; 
ii) un campo stato che identifica se un quadrante e' libero, oppure occupato da una pedina bianca, oppure occupato da una pedina nera; 
iii) un campo numPedina che contiene il numero identificativo dell'eventuale pedina che occupa ciascun quadrante.
*/
package myclasses.giochi;

public class Scacchiera{

	private static final int LATO = 8;
	private static final int CASELLE = LATO*LATO;

	private boolean[][] colore; //true: bianco, false: nero
	private int[][] stato; // 0: libero, 1: bianca, -1: nera
	private int[][] numPedina;

	/*
	costruisce una scacchiera di N x N quadranti disposti su N righe e N colonne, assegnando a ciascun 
	quadrante il relativo colore bianco oppure nero, e disponendo le pedine su fronti opposti muovendo 
	da destra verso sinistra per righe successive a partire da quelle piu' esterne. L'identificativo di 
	ogni pedina e' contenuto nel rispettivo elemento, di tipo int, che viene passato dai due array in 
	argomento rispettivamente per le pedine bianche e quelle nere;
	*/
	public Scacchiera(int[] pedineBianche, int[] pedineNere){
		colore = new boolean[LATO][LATO];
		stato = new int[LATO][LATO];
		numPedina = new int[LATO][LATO];

		//coloro la scacchiera vuota
		for(int i=0; i<LATO; i++){
			for(int j=0; j<LATO; j++){
				if((i+j)%2 == 0){
					colore[i][j] = true;
				}else{
					colore[i][j] = false;
				}
				stato[i][j] = 0;
				numPedina[i][j] = 0;
			}
		}

		//inserisco le pedine bianche
		int k = 0;
		for(int i=0; i<LATO; i++){
			for(int j=0; j<LATO; j++){
				if(k < pedineBianche.length){
					stato[i][j] = 1;
					numPedina[i][j] = pedineBianche[k];
					k++;
				}
			}
		}

		//inserisco le pedine nere
		k = 0;
		for(int i=LATO-1; i>=0; i--){
			for(int j=LATO-1; j>=0; j--){
				if(k < pedineNere.length){
					stato[i][j] = -1;
					numPedina[i][j] = pedineNere[k];
					k++;
				}
			}
		}
	}

	/*
	prova a spostare di una casella verso una direzione della scacchiera convenzionalmente identificata come 
	"alto" la pedina identificata da id e avente colore identificato da s, che varra' 1 se la pedina e' 
	bianca e -1 se la pedina e' nera. Lo spostamento avverra' regolarmente se la casella d'arrivo e' libera 
	oppure occupata da una pedina di colore diverso che di conseguenza scomparira': in tali casi il metodo 
	restituisce la stringa OK; se, invece, la casella e' occupata da una pedina dello stesso colore allora 
	lo spostamento non avviene e il metodo restituisce la stringa Mossa negata: Casella occupata da pedina 
	dello stesso colore; infine, se non esiste la casella d'arrivo il metodo dovra' intercettare la relativa 
	eccezione limitandosi a restituire la stringa Mossa negata: Raggiunto limite superiore della scacchiera 
	senza interrompere l'esecuzione;
	*/
	public String spostaSu(int id, short s){

		int x, y;
		x = y = -1;

		for(int i=0; i<LATO; i++){
			for(int j=0; j<LATO; j++){
				if(numPedina[i][j] == id && stato[i][j] == s){
					x = i;
					y = j;
				}
			}
		}

		if (s == 1){
			try{
				if(stato[x+1][y] != 1){
					numPedina[x+1][y] = numPedina[x][y];
					numPedina[x][y] = 0;
					stato[x+1][y] = 1;
					stato[x][y] = 0;
					return "OK";
				}else{
					return "Mossa negata: Casella occupata da pedina dello stesso colore";
				}
			}catch(IndexOutOfBoundsException e){
				return "Mossa negata: Raggiunto limite superiore della scacchiera";
			}

		}else{
			try{
				if(stato[x-1][y] != -1){
					numPedina[x-1][y] = numPedina[x][y];
					numPedina[x][y] = 0;
					stato[x-1][y] = -1;
					stato[x][y] = 0;
					return "OK";
				}else{
					return "Mossa negata: Casella occupata da pedina dello stesso colore";
				}
			}catch(IndexOutOfBoundsException e){
				return "Mossa negata: Raggiunto limite superiore della scacchiera";
			}
		}
	}

	/*
	esegue la stessa operazione del precedente ma verso una direzione della scacchiera convenzionalmente 
	identificata come "basso" - lo studente si limiti a riportare nella soluzione solamente le modifiche 
	da apportare al precedente metodo;
	*/
	public String spostaGiu(int id, short s){

		int x, y;
		x = y = -1;

		for(int i=0; i<LATO; i++){
			for(int j=0; j<LATO; j++){
				if(numPedina[i][j] == id && stato[i][j] == s){
					x = i;
					y = j;
				}
			}
		}

		if (s == 1){
			try{
				if(stato[x-1][y] != 1){
					numPedina[x-1][y] = numPedina[x][y];
					numPedina[x][y] = 0;
					stato[x-1][y] = 1;
					stato[x][y] = 0;
					return "OK";
				}else{
					return "Mossa negata: Casella occupata da pedina dello stesso colore";
				}
			}catch(IndexOutOfBoundsException e){
				return "Mossa negata: Raggiunto limite inferiore della scacchiera";
			}

		}else{
			try{
				if(stato[x+1][y] != -1){
					numPedina[x+1][y] = numPedina[x][y];
					numPedina[x][y] = 0;
					stato[x+1][y] = -1;
					stato[x][y] = 0;
					return "OK";
				}else{
					return "Mossa negata: Casella occupata da pedina dello stesso colore";
				}
			}catch(IndexOutOfBoundsException e){
				return "Mossa negata: Raggiunto limite inferiore della scacchiera";
			}	
		}
	}
	/*
	esegue la stessa operazione del precedente ma verso una direzione della scacchiera convenzionalmente 
	identificata come "destra" - lo studente si limiti a riportare nella soluzione solamente le modifiche
	da apportare al precedente metodo;
	*/
	public String spostaDx(int id, short s){

		int x, y;
		x = y = -1;

		for(int i=0; i<LATO; i++){
			for(int j=0; j<LATO; j++){
				if(numPedina[i][j] == id && stato[i][j] == s){
					x = i;
					y = j;
				}
			}
		}

		if (s == 1){
			try{
				if(stato[x][y-1] != 1){
					numPedina[x][y-1] = numPedina[x][y];
					numPedina[x][y] = 0;
					stato[x][y-1] = 1;
					stato[x][y] = 0;
					return "OK";
				}else{
					return "Mossa negata: Casella occupata da pedina dello stesso colore";
				}
			}catch(IndexOutOfBoundsException e){
				return "Mossa negata: Raggiunto limite destro della scacchiera";
			}

		}else{
			try{
				if(stato[x][y+1] != -1){
					numPedina[x][y+1] = numPedina[x][y];
					numPedina[x][y] = 0;
					stato[x][y+1] = -1;
					stato[x][y] = 0;
					return "OK";
				}else{
					return "Mossa negata: Casella occupata da pedina dello stesso colore";
				}
			}catch(IndexOutOfBoundsException e){
				return "Mossa negata: Raggiunto limite destro della scacchiera";
			}
		}
	}

	/*
	esegue la stessa operazione del precedente ma verso una direzione della scacchiera convenzionalmente 
	identificata come "sinistra" - lo studente si limiti a riportare nella soluzione solamente le modifiche
	da apportare al precedente metodo;
	*/
	public String spostaSx(int id, short s){

		int x, y;
		x = y = -1;

		for(int i=0; i<LATO; i++){
			for(int j=0; j<LATO; j++){
				if(numPedina[i][j] == id && stato[i][j] == s){
					x = i;
					y = j;
				}
			}
		}

		if (s == 1){
			try{
				if(stato[x][y+1] != 1){
					numPedina[x][y+1] = numPedina[x][y];
					numPedina[x][y] = 0;
					stato[x][y+1] = 1;
					stato[x][y] = 0;
					return "OK";
				}else{
					return "Mossa negata: Casella occupata da pedina dello stesso colore";
				}
			}catch(IndexOutOfBoundsException e){
				return "Mossa negata: Raggiunto limite sinistro della scacchiera";
			}	

		}else{
			try{
				if(stato[x][y-1] != -1){
					numPedina[x][y-1] = numPedina[x][y];
					numPedina[x][y] = 0;
					stato[x][y-1] = -1;
					stato[x][y] = 0;
					return "OK";
				}else{
					return "Mossa negata: Casella occupata da pedina dello stesso colore";
				}
			}catch(IndexOutOfBoundsException e){
				return "Mossa negata: Raggiunto limite sinistro della scacchiera";
			}	
		}
	}

	/*
	tampa la scacchiera con le pedine denotando, per ogni quadrante, il
	suo colore bianco o nero rispettivamente coi simboli [ ] e f g, e ogni pedina con il rispettivo identificatore
	numerico seguito da B o N rispettivamente nel caso in cui essa sia bianca oppure nera. Ad esempio, se il
	gioco prevede inizialmente la presenza di 9 pedine bianche e 9 pedine nere allora il metodo toString()
	eseguira' la stampa seguente:
	[1B]{2B}[3B]{4B}[5B]{6B}[7B]{8B}
	{9B}[  ]{  }[  ]{  }[  ]{  }[  ]
	[  ]{  }[  ]{  }[  ]{  }[  ]{  }
	{  }[  ]{  }[  ]{  }[  ]{  }[  ]
	[  ]{  }[  ]{  }[  ]{  }[  ]{  }
	{  }[  ]{  }[  ]{  }[  ]{  }[  ]
	[  ]{  }[  ]{  }[  ]{  }[  ]{9N}
	{8N}[7N]{6N}[5N]{4N}[3N]{2N}[1N]
	*/
	public String toString(){
		String s = "";
		for(int i=0; i<LATO; i++){
			for(int j=0; j<LATO; j++){
				//colore
				if(colore[i][j] == true){
					s += "[";
				}else{
					s += "{";
				}

				switch (stato[i][j]){
					case -1 : s += numPedina[i][j] + "N";
							break;
					case 0 : s += "  ";
							break;
					case 1 : s += numPedina[i][j] + "B";
							break;
				}

				if(colore[i][j] == true){
					s += "]";
				}else{
					s += "}";
				}

				if(j == LATO-1){
					s += "\n";
				}
			}
		}
		return s;
	}
}