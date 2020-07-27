public class ProdutoFormatado extends ProdutoPadrao {
	private Formato formato;

	public ProdutoFormatado(int id, String descricao, String categoria, int qtdEstoque, double preco, Formato formato) {
		super(id, descricao, categoria, qtdEstoque, preco);
		this.formato = formato;
	}
	
	public String formataParaImpressao() {
		return formato.formatacao() + super.formataParaImpressao() + formato.finalizaFormatacao();
	}
}
