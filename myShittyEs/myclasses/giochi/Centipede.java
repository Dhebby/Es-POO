package myclasses.giochi;
import java.util.*;

public class Centipede {
	private static final int LATO = 8;

	private NodoCoda primo;
	private class NodoCoda {
		int x,y;
		NodoCoda prox;
	}

	public Centipede() {
		Random r = new Random();
		primo = new NodoCoda();
		primo.x = r.nextInt(LATO);
		primo.y = r.nextInt(LATO);
		primo.prox = null;
	}

	public boolean sposta(int x, int y, boolean accoda) {
		NodoCoda temp;
		NodoCoda nuovo = new NodoCoda();

		nuovo.x = primo.x + x;
		nuovo.y = primo.y + y;

		nuovo.prox = primo;
		primo = nuovo;
		temp = primo;

		while (temp.prox.prox != null)
			temp = temp.prox;

		if(!accoda)
			temp.prox = null;

		temp = primo.prox;

		while(temp != null) {
			if(primo.x==temp.x && primo.y==temp.y)
				return false;
			temp = temp.prox;
		}

		if (primo.x < 0 || primo.y < 0 || primo.x >= LATO || primo.y >= LATO)
			return false;
		else
			return true;
	}

	public String toString() {
		boolean field[][] = new boolean[LATO][LATO];
		NodoCoda temp = primo;

		do{
			field[temp.x][temp.y] = true;
			temp = temp.prox;
		} while(temp != null);

		String output = "|";

		for (int i=0; i<LATO; i++)
			output += "-";

		output += "|\n";

		for (int j=0; j<LATO; j++) {
			output += "|";
			for (int i=0; i<LATO; i++)
				if (field[i][j])
					output += "X";
				else
					output += " ";
				output += "|\n";
		}
		output += "|";

		for (int i=0; i<LATO; i++)
			output += "-";

		output += "|\n";
		
		return output;
	}
}