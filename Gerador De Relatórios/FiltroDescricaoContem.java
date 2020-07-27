public class FiltroDescricaoContem implements Filtro {
	public boolean selecionar(Produto x, Object argFiltro) {
		return (x.getDescricao().contains((String) argFiltro));
	}
}
