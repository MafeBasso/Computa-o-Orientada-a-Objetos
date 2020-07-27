public class AlgoritmoQuickSort implements Algoritmo {
	public void ordena(Produto[] produtos, Criterio criterio, int ini, int fim) {
		if (ini < fim) {
			int q = particiona(produtos, criterio, ini, fim);

			ordena(produtos, criterio, ini, q);
			ordena(produtos, criterio, q + 1, fim);
		}
	}

	private int particiona(Produto[] produtos, Criterio criterio, int ini, int fim) {
		Produto x = produtos[ini];
		int i = (ini - 1);
		int j = (fim + 1);

		while (true) {
			do {
				j--;
			} while (criterio.selecionar(x, produtos[j]));

			do {
				i++;
			} while (criterio.selecionar(produtos[i], x));

			if (i < j) {
				Produto temp = produtos[i];
				produtos[i] = produtos[j];
				produtos[j] = temp;
			} else
				return j;
		}
	}
}