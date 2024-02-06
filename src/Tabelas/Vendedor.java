package Tabelas;

import java.sql.Connection;
import Conexao.Conexao;
import java.sql.PreparedStatement;

/**
 *
 * @author diogo
 */
public class Vendedor {
    //atributos de conexão
    private Conexao conexao;
    private Connection conect;
    
    //atributos do vendedor
    private long cpf_vendedor;
    private String nome_vendedor;

    //estabelecendo a conexão da classe ao Banco de dados no construtor
    public Vendedor(){
        this.conexao = new Conexao();
        this.conect = conexao.getConexao();
    }
    
    public long getCpf_vendedor() {
        return cpf_vendedor;
    }

    public void setCpf_vendedor(long cpf_vendedor) {
        this.cpf_vendedor = cpf_vendedor;
    }

    public String getNome_vendedor() {
        return nome_vendedor;
    }

    public void setNome_vendedor(String nome_vendedor) {
        this.nome_vendedor = nome_vendedor;
    }
    
    //metodos para cadastrar um vendedor
    public boolean CadastrarVendedor(long cpf,String nome){
        String sql = "INSERT INTO vendedor (cpf_vendedor,nome_vendedor) VALUES (?,?) ";
        
            try{
                PreparedStatement stmt = this.conect.prepareStatement(sql);
                stmt.setLong(1,cpf);
                stmt.setString(2,nome);
                stmt.execute();
                return true;
            }catch(Exception e){
                System.out.println("Erro ao cadastrar vendedor. Erro: " + e.getMessage());
                return false;
            }
    }
    
    //metodo para deletar um vendedor dos cadastros
    public boolean DeletarVendedor(long cpf){
        String sql = "DELETE FROM vendedor WHERE cpf_vendedor = ? ";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql);
            stmt.setLong(1,cpf);
            stmt.execute();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    
}
