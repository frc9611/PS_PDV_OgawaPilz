package com.propostapdv.formulario;

import java.util.ArrayList;

public class Carrinho {
    static ArrayList<String> CarrinhoStr = new ArrayList<>();
    static ArrayList<Produto> Cart = new ArrayList<>();

    public static void AddNoCarrinho(String nome, int quantidade) {
        if (!PDV.ProdutoExiste(nome, Cart)) {
            for (int n = 0; n < PDV.ListaProdutos.size(); n++) {
                String nomeProduto = PDV.ListaProdutos.get(n).Nome;
                if (nomeProduto.equals(nome) && PDV.ListaProdutos.get(n).Quantidade >= quantidade) {
                    Cart.add(new Produto(PDV.ListaProdutos.get(n).Nome, PDV.ListaProdutos.get(n).Preco, quantidade));
                }
            }

            PDV.AtualizarListaString();
        }

    }

    public static void VenderLista() {
        for (Produto p : Cart) {
            PDV.VenderProduto(p.Nome, p.Quantidade);
        }
        Cart.clear();
        PDV.AtualizarListaString();
    }

    public static double SomaPrecoCarrinho() {
        double total = 0.0;
        for (Produto p : Cart) {
            total += p.Preco * p.Quantidade;
        }
        return total;
    }

    public static String Info() {
        int qtd = 0;
        for (Produto p : Cart) {
            qtd += p.Quantidade;
        }
        return "Total: R$" + SomaPrecoCarrinho() + "<br> Quantidade: " + qtd;
    }
}
