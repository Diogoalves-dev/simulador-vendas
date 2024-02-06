package Tabelas;

import java.sql.PreparedStatement;
import Conexao.Conexao;
import java.sql.Connection;

/**
 *
 * @author diogo
 */
public class Cliente {
    //atributos de conexão
    private Conexao conexao;
    private Connection conect;
    
    //atributos do cliente
    private long cpf_cliente;
    private String nome_cliente;

    //estabelecendo a conexão da classe ao Banco de dados no construtor
    public Cliente(){
        this.conexao = new Conexao();
        this.conect = conexao.getConexao();
    }
    
    public long getCpf_cliente() {
        return cpf_cliente;
    }

    public void setCpf_cliente(long cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }
    
    //metodo para cadastrar cliente
    public boolean CadastrarCliente(long cpf,String nome){
        String sql = "INSERT INTO cliente (cpf_cliente,nome_cliente) VALUES (?,?) ";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql);
            stmt.setLong(1,cpf);
            stmt.setString(2,nome);
            stmt.execute();
            return true;
        }catch(Exception e){
            System.out.println("Erro ao cadastrar cliente. Erro: " + e.getMessage());
            return false;
        }
    }
    
    //metodo para deletar um cliente dos cadastros
    public boolean DeletarCliente(long cpf){
        String sql = "DELETE FROM cliente WHERE cpf_cliente = ? ";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql);
            stmt.setLong(1, cpf);
            stmt.execute();
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
