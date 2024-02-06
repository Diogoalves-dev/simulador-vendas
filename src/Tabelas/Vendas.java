package Tabelas;

import java.sql.Connection;
import Conexao.Conexao;
import java.util.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
/**
 *
 * @author diogo
 */
public class Vendas {
    //atributos da conexão
    private Conexao conexao;
    private Connection conect;
    
    //atributos da venda
    private int id_venda;
    private int id_produto;
    private int id_cliente;
    private int id_vendedor;
    private String pagamento;
    private Date data_venda = new Date();

    //estabelecendo a conexão da classe ao Banco de dados no construtor
    public Vendas(){
        this.conexao = new Conexao();
        this.conect = conexao.getConexao();
    }
    
    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public Date getData() {
        return data_venda;
    }

    public void setData(Date data) {
        this.data_venda = data;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }
    
    public String getPagamento(){
        return this.pagamento;
    }
    
    public void setPagamento(String pagamento){
        this.pagamento = pagamento;
    }
    
    //metodo para criar uma nova venda
    public boolean CriarVenda(String data_venda,Vendedor vendedor,Cliente cliente,Produtos produto,String pagamento){
        String slq = "INSERT INTO vendas (data_venda,cpf_vendedor,cpf_cliente,cod_produto,pagamento) VALUES (?,?,?,?,?)";
        try{
            
            PreparedStatement stmt = this.conect.prepareStatement(slq);
            stmt.setString(1, data_venda);
            stmt.setLong(2,vendedor.getCpf_vendedor());
            stmt.setLong(3, cliente.getCpf_cliente());
            stmt.setLong(4,produto.getCod_barras());
            stmt.setString(5,pagamento);
            stmt.execute();
            return true;
        }catch(Exception e){
            System.out.println("Erro ao criar a venda. Erro: " + e.getMessage());
            return false;
        }
        
    }
    
}
