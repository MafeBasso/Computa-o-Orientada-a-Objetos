public class FormatoNegrito extends FormatoDecorator {
	private Formato formato;
	
	public FormatoNegrito(Formato formato) {
		super(formato);
		this.formato = formato;
	}
	
	public String formatacao() {
		return "<span style=\"font-weight:bold\">" + formato.formatacao();
	}
	
	public String finalizaFormatacao() {
		return "</span>";
	}
}
