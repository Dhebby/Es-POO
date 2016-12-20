/*
Con riferimento al package centipede si realizzi, all'interno di una classe di nome GiocaCentipe-
deMigliorato, un metodo main che esegue una partita di CentipedeMigliorato lanciando un thread avente
per argomento un oggetto Stampatore, un thread avente per argomento un oggetto Mover, e successivamente
si mette indefinitamente in attesa dei valori che il giocatore via via immette da tastiera per direzionare il
centopiedi fintantoche esso non cessa di vivere. A quel punto il metodo stampi il punteggio finale. Per
semplicita', il centopiedi all'inizio di una nuova partita sia posizionato nel punto centrale del campo di gioco.
*/
import myclasses.giochi.centipede.*;
import prog.io.*;

public class GiocaCentipedeMigliorato{
	public static void main(String[] args){
		ConsoleInputManager in = new ConsoleInputManager();
		ConsoleOutputManager out = new ConsoleOutputManager();

		CentipedeMigliorato partita = new CentipedeMigliorato();

		(new Thread(new Stampatore(partita))).start();
		(new Thread(new Mover(partita))).start();

		while(partita.ok){
			partita.mossa = (byte)Integer.parseInt(in.readLine());
		}
		out.println("Fine partita. Punteggio tot: "+partita.punti);
	}
}