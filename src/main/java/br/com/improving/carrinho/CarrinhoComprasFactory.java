package br.com.improving.carrinho;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
public class CarrinhoComprasFactory {
	
	private Map<String, CarrinhoCompras> carrinhos = new HashMap<>();

	public CarrinhoComprasFactory() {
	}

    /**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     *
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
     *
     * @param identificacaoCliente
     * @return CarrinhoCompras
     */
	public CarrinhoCompras criar(String identificacaoCliente) {
	    CarrinhoCompras carrinho = carrinhos.get(identificacaoCliente);
	    if (carrinho == null) {
	        carrinho = new CarrinhoCompras();
	        carrinhos.put(identificacaoCliente, carrinho);
	    }
	    return carrinho;
	}

    /**
     * Retorna o valor do ticket médio no momento da chamada ao método.
     * O valor do ticket médio é a soma do valor total de todos os carrinhos de compra dividido
     * pela quantidade de carrinhos de compra.
     * O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
     * 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
     *
     * @return BigDecimal
     */
	public BigDecimal getValorTicketMedio() {
	    BigDecimal somaTotal = BigDecimal.ZERO;

	    for (CarrinhoCompras carrinho : carrinhos.values()) {
	        BigDecimal valorCarrinho = carrinho.getValorTotal();
	        somaTotal = somaTotal.add(valorCarrinho);
	    }

	    int quantidadeCarrinhos = carrinhos.size();

	    if (quantidadeCarrinhos == 0) {
	        return BigDecimal.ZERO;
	    }
	    
	    BigDecimal ticketMedio = somaTotal.divide(BigDecimal.valueOf(quantidadeCarrinhos), 2, RoundingMode.HALF_UP);
	    return ticketMedio;
	}

    /**
     * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar.
     * Deve ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos de compras.
     *
     * @param identificacaoCliente
     * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
     * e false caso o cliente não possua um carrinho.
     */
    public boolean invalidar(String identificacaoCliente) {
		if (carrinhos.containsKey(identificacaoCliente)) {
		    carrinhos.remove(identificacaoCliente);
		    
		    return true;
		} else {
			
		    return false;
		}
    }
}