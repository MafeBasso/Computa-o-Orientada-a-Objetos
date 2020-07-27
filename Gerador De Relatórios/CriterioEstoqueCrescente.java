public class CriterioEstoqueCrescente implements Criterio {
	public boolean selecionar(Produto x, Produto y) {
		return (x.getQtdEstoque() < y.getQtdEstoque());
	}
}