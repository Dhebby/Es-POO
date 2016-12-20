/*
La classe Contatori contenga due classi statiche Counter1 e Counter2, le quali realizzano due contatori indipendenti implementando l'interfaccia Runnable in modo tale che, una volta che i loro metodi run() sono eseguiti:
- incrementano a ogni secondo rispettivamente una variabile i1 e i2 statica della classe Contatori;
- inviano il valore della stessa variabile allo standard output in modo da far comparire solo il decimale più a destra: 1,2,3,...,8,9,0,1,2,... -- idealmente la stampa di un nuovo valore da parte di un contatore dovrebbe sovrascrivere il valore precedentemente stampato dallo stesso contatore;
- stampano rispettivamente "Ok, I stop counter 1!" e "Ok, I stop counter 2!" se viene sollevata l'interruzione del corrispondente contatore.

L'esecuzione dei due thread avvenga per mano di un metodo main appartenente alla classe Contatori il quale, dopo avere creato e avviato i rispettivi thread:
- alternativamente interrompe e riavvia il primo thread se viene ricevuto il carattere '1' dallo standard input;
- alternativamente interrompe e riavvia il secondo thread se viene ricevuto il carattere '2' dallo standard input;
- stampa "Stop 1." e invia un messaggio d'interruzione al primo thread se viene ricevuto il carattere '3' dallo standard input;
- stampa "Stop 2." e invia un messaggio d'interruzione al secondo thread se viene ricevuto il carattere '4' dallo standard input;
- stampa "All counters dead! Exiting.." e conclude l'esecuzione del programma se entrambi i contatori sono stati interrotti.

Si osservino le problematiche di gestione delle risorse condivise dai contatori (es: standard input e standard output) in assenza di una politica di accesso sincronizzato.
*/
import prog.io.*;

public class Contatori {

	static int i1, i2;
	static boolean paused1, paused2;

	static void threadMessage(String mess){
		ConsoleOutputManager out = new ConsoleOutputManager();

		String threadName = Thread.currentThread().getName();
		out.println(threadName + ":" + mess);
	}

	private static class Counter1 implements Runnable{
		ConsoleOutputManager out = new ConsoleOutputManager();

		public void run(){
			while(true){

				if(!paused1){
					out.print(i1%10);
					i1++;
				}

				try{
					Thread.sleep(1000);
				}catch(InterruptedException e){
					threadMessage("Ok, I stop Counter 1!");
					return;
				}

			}

		}
	}

	private static class Counter2 implements Runnable{
		ConsoleOutputManager out = new ConsoleOutputManager();

		public void run(){
			while(true){

				if(!paused2){
					out.print("\t" + i2%10);
					i2++;
				}

				try{
					Thread.sleep(1000);
				}catch(InterruptedException e){
					threadMessage("Ok, I stop Counter 2!");
					return;
				}
			}

		}
	}

	public static void main(String[] args) throws InterruptedException {
		ConsoleInputManager in = new ConsoleInputManager();

		Thread counter1 = new Thread(new Counter1());
		Thread counter2 = new Thread(new Counter2());

		i1 = i2 = 0;
		paused1 = paused2 = false;

		counter1.start();
		counter2.start();

		while(counter1.isAlive() || counter2.isAlive()){
			switch (in.readLine().charAt(0)) {
				case 1 : paused1 = !paused1;
						break;
				case 2 : paused2 = !paused2;
						break;
				case 3 : threadMessage("Stop 1.");
						counter1.interrupt();
						break;
				case 4 : threadMessage("Stop 2.");
						counter2.interrupt();
						break;
			}
		}

		threadMessage("All counters dead! Exiting..");
	}
}