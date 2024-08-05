package com.propostapdv.formulario;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerFormulario {

        @GetMapping("/home")
        public ModelAndView home(){
            PDV.AtualizarListaString();
            ModelAndView mv = new ModelAndView("index-TEST"); // Mudar para "index" no final
            mv.addObject("listaDeProdutos", PDV.ListaProdutosStr);
            mv.addObject("carrinho", Carrinho.CarrinhoStr);
            mv.addObject("infoCarrinho", Carrinho.Info());
            return mv;
        }

        @PostMapping("/cadproduto")
        public ModelAndView cadProduto(String nome, double preco, int quantidade) throws SQLException{
            PDV.AddProduto(nome, preco, quantidade);
            ModelAndView mv = new ModelAndView("produtos");
            mv.addObject("listaDeProdutos", PDV.ListaProdutosStr);
            return mv;

        }
        @PostMapping("/lista")
        public ModelAndView lista(){
            ModelAndView mv = new ModelAndView("produtos");
            mv.addObject("listaDeProdutos", PDV.ListaProdutosStr);
            return mv;
        }

        @PostMapping("/addcarrinho")
        public ModelAndView addCarrinho(String nomecar, int quantidadecar){
            Carrinho.AddNoCarrinho(nomecar, quantidadecar);
            ModelAndView mv = new ModelAndView("carrinho");
            mv.addObject("carrinho", Carrinho.CarrinhoStr);
            return mv;
        }
        @PostMapping("/vendercarrinho")
        public ModelAndView venderCarrinho(){
            Carrinho.VenderLista();
            ModelAndView mv = new ModelAndView("carrinho");
            mv.addObject("carrinho", Carrinho.CarrinhoStr);
            return mv;
        }
        
}
