import prog.io.*;

public class ContatoriMiglioratoArrestabili{
	static int nCont; //numero contatori

	public static class ContatoreArrestabile implements Runnable{
		int[] valCont = new int[nCont]; //valori contatori
		boolean[] statiCont = new boolean[nCont]; //stato contatori
		int k; //k-esimo contatore

		public ContatoreArrestabile(int k){
			this = k;
		}

		public void run(){
			while(true){
				if(!statiCont[k]){
					valCont[k] ++;
				}

				try{
					Thread.sleep(1000);
				}catch(InterruptedException e){}
			}
		}

		public synchronized int[] lettura(){
			int[] bufferCont = new int[valCont.length];
			for(int i=0; i<valCont.length; i++){
				bufferCont[i] = valCont[i];
			}
			return bufferCont;
		}

		public synchronized void scrittura(int val, int i){
			valCont[i] = val;
		}

		public synchronized void resetCont(){
			for(int i=0; i<statiCont.length; i++){
				statiCont[i] = false;
			}
		}

		public synchronized void changeStato(int i){
			statiCont[i] = !statiCont[i];
		}
	}

	public static class Stampa implements Runnable{

	}

	public static void main(String[] args){
		ConsoleInputManager in = new ConsoleInputManager();

		nCont = Integer.parseInt(args[0]);

		Thread[] threads = new Threads[nCont];

		int selected;

		for(int i=0; i<nCont; i++){
			threads[i] = new Thread(new ContatoreArrestabile(i));
			threads[i].start();
		}
		
		while(true){
			selected = new Integer.parseInt(in.readLine());
			if(selected < nCont){
				changeStato(selected);
			}else if(selected == nCont){
				resetCont();
			}
		}
		

	}
}