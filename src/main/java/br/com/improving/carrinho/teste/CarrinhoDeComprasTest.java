package br.com.improving.carrinho.teste;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.improving.carrinho.CarrinhoCompras;
import br.com.improving.carrinho.Item;
import br.com.improving.carrinho.Produto;

public class CarrinhoDeComprasTest {
    private CarrinhoCompras carrinho;
    private Item item;
    private Produto produto1;
    private Produto produto2;

    @Before
    public void setUp() {
        carrinho = new CarrinhoCompras();
        produto1 = new Produto((long) 10, "Produto 1");
        produto2 = new Produto((long) 15, "Produto 2");

        item = new Item(produto1, new BigDecimal(10), 2);
        carrinho.adicionarItem(item.getProduto(), item.getValorUnitario(), item.getQuantidade());
    }

    @Test
    public void testAdicionarItem() {
        carrinho.adicionarItem(produto1, new BigDecimal(10), 1);
        assertEquals(1, carrinho.getItens().size());
        
        Item itemAdicionado = carrinho.getItens().iterator().next();
        assertEquals(produto1, itemAdicionado.getProduto());
    }

    @Test
    public void testCalcularValorTotal() {
        carrinho.adicionarItem(produto1, new BigDecimal(10), 2);
        carrinho.adicionarItem(produto2, new BigDecimal(15), 3);
        BigDecimal valorTotal = carrinho.getValorTotal();
        assertEquals(new BigDecimal("85"), valorTotal);
    }

    @Test
    public void testRemoverItem() {
        carrinho.adicionarItem(produto1, new BigDecimal(10), 2);
        assertTrue(carrinho.removerItem(produto1));
        assertFalse(carrinho.removerItem(produto2));
    }
}