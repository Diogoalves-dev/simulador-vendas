package Tabelas;

import java.sql.Connection;
import Conexao.Conexao;
import java.sql.PreparedStatement;

/**
 *
 * @author diogo
 */
public class Produtos {
    //atributos da conexão
    private Conexao conexao;
    private Connection conect;
    
    //atributos do produto
    private long cod_barras;
    private String nome_produto;
    private String valor;

    //estabelecendo a conexão da classe ao Banco de dados no construtor
    public Produtos(){
        this.conexao = new Conexao();
        this.conect = conexao.getConexao();
    }
    
    public long getCod_barras() {
        return this.cod_barras;
    }

    public void setCod_barras(long cod_barras) {
        this.cod_barras = cod_barras;
    }

    public String getNome_produto() {
        return this.nome_produto;
    }

    public void setNome_produto(String nome) {
        this.nome_produto = nome;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    //metodo para cadastrar um produto
    public boolean CadastrarProduto(String nome,float valor){
        String sql = "INSERT INTO produto (nome_produto,valor) VALUES (?,?) ";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql);
            stmt.setString(1,nome);
            stmt.setFloat(2,valor);
            stmt.execute();
            return true;
        }catch(Exception e){
            System.out.println("erro ao cadastrar produto. Erro: " + e.getMessage());
            return false;
        }
    }
    
    //metodo para deletar um produto dos cadastros
    public boolean DeletarProduto(long cod_barras){
        String sql = "DELETE FROM produto WHERE cod_barras = ? ";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql);
            stmt.setLong(1, cod_barras);
            stmt.execute();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    
}
