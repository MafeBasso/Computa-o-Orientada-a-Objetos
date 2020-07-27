public class FiltroEstoqueMenorOuIgualA implements Filtro {
	public boolean selecionar(Produto x, Object argFiltro) {
		return (x.getQtdEstoque() <= (Integer) argFiltro);
	}
}
