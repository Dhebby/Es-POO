import myclasses.giochi.centipedeJun.*;
import prog.io.*;

public class GiocaCentipedeNew{
	public static void main(String[] args){
		ConsoleInputManager in = new ConsoleInputManager();
		ConsoleOutputManager out = new ConsoleOutputManager();

		CentipedeNew partita = new CentipedeNew();

		(new Thread(new StampatoreNew(partita))).start();
		(new Thread(new MoverNew(partita))).start();

		while(partita.ok){
			partita.mossa = (byte)Integer.parseInt(in.readLine());
		}
	}
}