import myclasses.giochi.*;
import prog.io.ConsoleOutputManager;
import prog.io.ConsoleInputManager;

public class GiocaCentipede {
	public static void main(String[] args) {
		ConsoleOutputManager out = new ConsoleOutputManager();
		ConsoleInputManager in = new ConsoleInputManager();

		Centipede partita = new Centipede();
		int punti = 0;
		boolean ok = true;

		while (ok) {
			out.println(partita);
			punti++;
			switch(in.readLine().charAt(0)) {
				case('u'):
					ok = partita.sposta(0,-1,punti%5==0);
					break;
				case('d'):
					ok = partita.sposta(0,1,punti%5==0);
					break;
				case('l'):
					ok = partita.sposta(-1,0,punti%5==0);
					break;
				case('r'):
					ok = partita.sposta(1,0,punti%5==0);
					break;
			}
		}
		out.println("\nHai totalizzato " + punti + (punti==1?" punto.":" punti."));
	}
}