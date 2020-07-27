import java.awt.Color;

public class FormatoCor extends FormatoDecorator {
	private Formato formato;
	private Color cor;
	
	public FormatoCor(Formato formato, Color cor) {
		super(formato);
		this.formato = formato;
		this.cor = cor;
	}
	
	public String formatacao() {
		return "<span style=\"color:rgb(" + cor.getRed() + "," + cor.getGreen() + "," + cor.getBlue() + ")\">" + formato.formatacao();
	}
	
	public String finalizaFormatacao() {
		return "</span>";
	}
}
