public class FiltroPrecoEntre implements Filtro {	
	private double min;
	private double max;
	
	public FiltroPrecoEntre(double min, double max) {
		this.min = min;
		this.max = max;
	}
	
	public boolean selecionar(Produto x, Object argFiltro) {
		return (min <= x.getPreco() && x.getPreco() <= max);
	}
}
