public class CriterioPrecoCrescente implements Criterio {
	public boolean selecionar(Produto x, Produto y) {
		return (x.getPreco() < y.getPreco());
	}
}
