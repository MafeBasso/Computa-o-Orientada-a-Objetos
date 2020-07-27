public class CriterioDescricaoDecrescente implements Criterio {
	public boolean selecionar(Produto x, Produto y) {
		return (x.getDescricao().compareToIgnoreCase(y.getDescricao()) > 0);
	}
}