package br.com.improving.carrinho;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
public class CarrinhoCompras {

    private List<Item> itens;

    public CarrinhoCompras() {
        this.itens = new ArrayList<>();
    }
	 
    /**
     * Permite a adição de um novo item no carrinho de compras.
     *
     * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
     * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
     * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
     * o passado como parâmetro.
     *
     * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
     *
     * @param produto
     * @param valorUnitario
     * @param quantidade
     */

    public void adicionarItem(Produto produto, BigDecimal valorUnitario, int quantidade) {
	    if (quantidade <= 0) {
	        throw new CarrinhoException("A quantidade deve ser maior que zero.");
	    }
	    
	    if (valorUnitario.compareTo(BigDecimal.ZERO) < 0) {
	        throw new CarrinhoException("O valor unitário não pode ser negativo.");
	    }

	    try {
	        for (Item item : itens) {
	            Produto itemProduto = item.getProduto();

	            if (itemProduto != null && itemProduto.getCodigo().equals(produto.getCodigo())) {
	                item.setQuantidade(item.getQuantidade() + quantidade);

	                if (!valorUnitario.equals(item.getValorUnitario())) {
	                    item.setValorUnitario(valorUnitario);
	                }
	                return;
	            }
	        }

	        Item newItem = new Item(produto, valorUnitario, quantidade);
	        itens.add(newItem);
	    } catch (CarrinhoException ex) {
	        throw ex;
	    }
    }

    /**
     * Permite a remoção do item que representa este produto do carrinho de compras.
     *
     * @param produto
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removerItem(Produto produto) {
        for (Item item : itens) {
            Produto itemProduto = item.getProduto();
            if (itemProduto != null && itemProduto.getCodigo().equals(produto.getCodigo())) {
                itens.remove(item);
                return true;
            }
        }
        return false;
    }

    /**
     * Permite a remoção do item de acordo com a posição.
     * Essa posição deve ser determinada pela ordem de inclusão do produto na 
     * coleção, em que zero representa o primeiro item.
     *
     * @param posicaoItem
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removerItem(int posicaoItem) {
    	if (posicaoItem >= 0 && posicaoItem < itens.size()) {
            itens.remove(posicaoItem);
            
            return true;
        }
    	
        return false;
    }

    /**
     * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
     * de todos os itens que compõem o carrinho.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTotal() {
    	BigDecimal valorTotal = BigDecimal.ZERO;
    	for (Item item : itens) {
    		BigDecimal valorItem = item.getValorTotal();
    		valorTotal = valorTotal.add(valorItem);
    	}
    	
    	return valorTotal;
    }

    /**
     * Retorna a lista de itens do carrinho de compras.
     *
     * @return itens
     */
    public Collection<Item> getItens() {
    	return itens;
    }
}