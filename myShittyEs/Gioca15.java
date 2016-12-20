/*
Con riferimento alla classe Qundici in cui sia N = 16 si realizzi, all'interno di una classe di
nome Gioca15, un metodo main che 
i) istanzia e inizialmente stampa una partita; 
ii) permette al giocatore di spostare le tessere secondo le regole del gioco, visualizzando dopo ogni 
spostamento lo stato della partita e interrompendo il gioco quando il giocatore vince la partita, 
segnalando la vittoria visualizzando il messaggio Hai vinto!.
*/
import myclasses.giochi.*;
import prog.io.ConsoleOutputManager;
import prog.io.ConsoleInputManager;

public class Gioca15{
	public static void main(String[] args){
		ConsoleOutputManager out = new ConsoleOutputManager();
		ConsoleInputManager in = new ConsoleInputManager();

		Quindici partita= new Quindici();

		while(!partita.vinto()){
			out.println(partita);
			switch(in.readLine().charAt(0)){ //uso apici singoli xk char
				case('u'): partita.spostaSu();
							break;
				case('d'): partita.spostaGiu();
							break;
				case('l'): partita.spostaSx();
							break;
				case('r'): partita.spostaDx();
							break;
			}
		}
		out.println("Hai vinto!!");
	}
}
