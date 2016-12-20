/* Il metodo main() della classe MatriceTriangolare permetta di creare una matrice triangolare alta di dimensioni NxN, ciascun elemento della quale e' un riferimento a un oggetto Frazione il cui numeratore e' la riga, e il denominatore e' la colonna in cui lo stesso riferimento e' collocato.

Es. (N=4):

1/1 1/2 1/3 1/4
2/1 2/2 2/3
3/1 3/2
4/1

La struttura che rappresenta la matrice triangolare dovra' essere formata da un array di array, in cui ciascuno di questi ultimi contiene una riga di dimensione N-i+1 in cui i e' l'indice della riga.

Appoggiandosi ai package prog.* (v. libro di testo) si creino e, successivamente, si stampino gli elementi della struttura.


 */
import prog.utili.Frazione;
import prog.io.ConsoleOutputManager;

class MatriceTriangolare{
	public static void main(String[] args){

		final int N = 8;

		ConsoleOutputManager out = new ConsoleOutputManager();
		Frazione[][] matrTr = new Frazione[N][];

		for(int i=0; i<N; i++){
			matrTr[i] = new Frazione[N-i];
			for(int j=0; j<N-i; j++){
				matrTr[i][j] = new Frazione(i+1, j+1);
			}
		}
		
		for(Frazione[] riga : matrTr){
			for(Frazione colonna : riga){
				out.print(colonna.toString() + "\t");
			}
			out.println();
		}
	}
}