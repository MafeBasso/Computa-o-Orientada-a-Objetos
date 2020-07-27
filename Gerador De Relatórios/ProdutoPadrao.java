import java.text.NumberFormat;

public class ProdutoPadrao implements Produto {
	private static final String SEPARADOR = ", ";

	private int id;
	private String descricao;
	private String categoria;
	private int qtdEstoque;
	private double preco;

	public ProdutoPadrao(int id, String descricao, String categoria, int qtdEstoque, double preco) {
		setId(id);
		setDescricao(descricao);
		setCategoria(categoria);
		setQtdEstoque(qtdEstoque);
		setPreco(preco);
	}

	private void setId(int id) {
		this.id = id;
	}

	private void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	private void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getId() {
		return this.id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public int getQtdEstoque() {
		return this.qtdEstoque;
	}

	public double getPreco() {
		return this.preco;
	}

	public String formataParaImpressao() {
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
		return getDescricao() + SEPARADOR + getCategoria() + SEPARADOR + fmt.format(getPreco()) + SEPARADOR + getQtdEstoque() + " unidade(s) em estoque";
	}
}
