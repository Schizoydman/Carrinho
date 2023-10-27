package br.com.improving.carrinho.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.improving.carrinho.CarrinhoCompras;
import br.com.improving.carrinho.CarrinhoComprasFactory;
import br.com.improving.carrinho.Item;
import br.com.improving.carrinho.Produto;

public class CarrinhoComprasFactoryTest {

    private CarrinhoComprasFactory carrinhoFactory;

    @Before
    public void setUp() {
        carrinhoFactory = new CarrinhoComprasFactory();
    }

    @Test
    public void testCriarCarrinho() {
        CarrinhoCompras carrinho1 = carrinhoFactory.criar("cliente1");
        CarrinhoCompras carrinho2 = carrinhoFactory.criar("cliente2");

        assertNotNull(carrinho1);
        assertNotNull(carrinho2);
        assertNotSame(carrinho1, carrinho2);
    }

    @Test
    public void testGetValorTicketMedio() {
        CarrinhoCompras carrinho1 = carrinhoFactory.criar("cliente1");
        CarrinhoCompras carrinho2 = carrinhoFactory.criar("cliente2");

        Produto produto1 = new Produto((long) 1, "Produto 1");
        Item item = new Item(produto1, new BigDecimal(10), 3);
        
        Produto produto2 = new Produto((long) 2, "Produto 2");
        Item item2 = new Item(produto2, new BigDecimal(20), 1);
        
        carrinho1.adicionarItem(item.getProduto(), item.getValorUnitario(), item.getQuantidade());
        carrinho2.adicionarItem(item2.getProduto(), item2.getValorUnitario(), item2.getQuantidade());

        BigDecimal valorTicketMedio = carrinhoFactory.getValorTicketMedio();
    }

    @Test
    public void testInvalidarCarrinho() {
        CarrinhoCompras carrinho1 = carrinhoFactory.criar("cliente1");
        CarrinhoCompras carrinho2 = carrinhoFactory.criar("cliente2");

        assertTrue(carrinhoFactory.invalidar("cliente1"));
        assertTrue(carrinhoFactory.invalidar("cliente2"));
        assertFalse(carrinhoFactory.invalidar("cliente1"));
        assertFalse(carrinhoFactory.invalidar("cliente2"));
    }
}