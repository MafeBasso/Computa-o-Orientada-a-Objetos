public abstract class FormatoDecorator implements Formato {
	private Formato formato;
	
	public FormatoDecorator(Formato formato) {
		this.formato = formato;
	}
	
	public String formatacao() {
		return formato.formatacao();
	}
}