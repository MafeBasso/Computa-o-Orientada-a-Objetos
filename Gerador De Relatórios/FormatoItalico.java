public class FormatoItalico extends FormatoDecorator {
	private Formato formato;
	
	public FormatoItalico(Formato formato) {
		super(formato);
		this.formato = formato;
	}
	
	public String formatacao() {
		return "<span style=\"font-style:italic\">" + formato.formatacao();
	}
	
	public String finalizaFormatacao() {
		return "</span>";
	}
}
