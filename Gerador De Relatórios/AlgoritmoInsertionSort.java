public class AlgoritmoInsertionSort implements Algoritmo {
	public void ordena(Produto[] produtos, Criterio criterio, int ini, int fim) {
		for (int i = ini; i <= fim; i++) {
			Produto x = produtos[i];
			int j = (i - 1);
			while (j >= ini) {
				if (criterio.selecionar(x, produtos[j])) {
					produtos[j + 1] = produtos[j];
					j--;
				} else
					break;
			}
			produtos[j + 1] = x;
		}
	}
}