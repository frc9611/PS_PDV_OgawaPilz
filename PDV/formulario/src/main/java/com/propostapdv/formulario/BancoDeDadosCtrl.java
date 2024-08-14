package com.propostapdv.formulario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BancoDeDadosCtrl {
    private static String UrlSql = "jdbc:mysql://localhost";
    private static String Usuario = "pdvapp";
    private static String Senha = "2b@*[BCrXB/E]nt1";
    private static String Driver = "com.mysql.jdbc.Driver";
    private static String MsgErroDriver = "Driver não encontrado";
    private static String MsgErroSQL = "Erro de SQL: ";

    public static void AtualizarProdutosLocal() throws SQLException{
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(UrlSql, Usuario, Senha);
            Class.forName(Driver);
            ResultSet rsCliente = conexao.createStatement().executeQuery("SELECT * FROM pdv.produtos");
            PDV.ListaProdutos.clear();           
            while (rsCliente.next()){
                PDV.ListaProdutos.add(new Produto(rsCliente.getString("nome"), rsCliente.getDouble("preco"), rsCliente.getInt("quantidade")));
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(MsgErroDriver);
        } catch (SQLException ex) {
            System.out.println(MsgErroSQL + ex.getMessage());
        } finally{
            if(conexao != null){
                conexao.close();
            }
        }
    }
    public static void AddProdutoDB() throws SQLException{
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(UrlSql, Usuario, Senha);
            Class.forName(Driver);
            Produto p = PDV.ListaProdutos.get(PDV.ListaProdutos.size()-1);
            conexao.createStatement().execute("INSERT INTO pdv.produtos (nome,preco,quantidade) VALUES ('" + p.Nome + "', " + p.Preco + ", " + p.Quantidade + ")");              
            
        } catch (ClassNotFoundException ex) {
            System.out.println(MsgErroDriver);
        } catch (SQLException ex) {
            System.out.println(MsgErroSQL + ex.getMessage());
        } finally{
            if(conexao != null){
                conexao.close();
            }
        }
    }
    public static void RemoverProdutoDB(String nome){
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(UrlSql, Usuario, Senha);
            Class.forName(Driver);
            
            conexao.createStatement().execute("DELETE FROM pdv.produtos WHERE produtos.nome = '" + nome + "'");              
            
        } catch (ClassNotFoundException ex) {
            System.out.println(MsgErroDriver);
        } catch (SQLException ex) {
            System.out.println(MsgErroSQL + ex.getMessage());
        } finally{
            if(conexao != null){
                try {
                    conexao.close();
                } catch (SQLException e) {
                    System.out.println("Falha ao fechar a conexao com o banco de dados: " + e.getMessage());
                }
            }
        }
    }
    public static void AlterarQuantidade(String nome, int quantidadeSubtrair){
        //Nome: Nome do produto, quantidade: qtd a ser subtraida
        Connection conexao = null;
        try {
            int id = 0;
            int qtd = 0;
            conexao = DriverManager.getConnection(UrlSql, Usuario, Senha);
            Class.forName(Driver);
            ResultSet rsCliente = conexao.createStatement().executeQuery("SELECT id, quantidade FROM pdv.produtos WHERE nome = '"+nome+"'");
            while (rsCliente.next()) {
                id = rsCliente.getInt("id");
                qtd = rsCliente.getInt("quantidade");
            }
            int novaQtd = qtd - quantidadeSubtrair;
            conexao.createStatement().execute("UPDATE pdv.produtos SET `quantidade` = '"+ novaQtd +"' WHERE (`id` = '"+ id +"')");              
            
        } catch (ClassNotFoundException ex) {
            System.out.println(MsgErroDriver);
        } catch (SQLException ex) {
            System.out.println(MsgErroSQL + ex.getMessage());
        } finally{
            if(conexao != null){
                try {
                    conexao.close();
                } catch (SQLException e) {
                    System.out.println("Falha ao fechar a conexao com o banco de dados: " + e.getMessage());
                }
            }
        }
    }


}
