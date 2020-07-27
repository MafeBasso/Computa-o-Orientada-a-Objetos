public class FiltroCategoriaIgualA implements Filtro {
	public boolean selecionar(Produto x, Object argFiltro) {
		return (x.getCategoria().equalsIgnoreCase((String) argFiltro));
	}
}
