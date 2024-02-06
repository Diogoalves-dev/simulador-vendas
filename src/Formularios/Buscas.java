package Formularios;

import java.sql.Connection;
import Conexao.Conexao;
import Tabelas.Produtos;
import Tabelas.Vendedor;
import Tabelas.Vendas;
import Tabelas.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author diogo
 */
public class Buscas {
    //atributos de conexão
    private Conexao conexao;
    private Connection conect;
    
    //estabelecendo a conexão da classe e o Database no construtor
    public Buscas(){
        this.conexao = new Conexao();
        this.conect = conexao.getConexao();
    }
    
    //metodo para buscar vendedor
    public Vendedor BuscarVendedor(long cpf){  
        String sql = "SELECT * FROM vendedor WHERE cpf_vendedor = ? ";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE);
            stmt.setLong(1, cpf);
            ResultSet rs = stmt.executeQuery();
            Vendedor vendedor = new Vendedor();
            
            //passando os atributos recebidos do banco de dados direto para o meu ojeto vendedor
            rs.next();
            vendedor.setCpf_vendedor(cpf);
            vendedor.setNome_vendedor(rs.getString("nome_vendedor"));
            return vendedor;
        }catch(Exception e){
            return null;
        }
    }
    
    //esse metodo foi criado para verificar se o vendedor foi encontrado
    public boolean EcontrarVendedor(long cpf){  
        String sql = "SELECT * FROM vendedor WHERE cpf_vendedor = ? ";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE);
            stmt.setLong(1, cpf);
            ResultSet rs = stmt.executeQuery();
            Vendedor vendedor = new Vendedor();
            
            //passando os atributos recebidos do banco de dados direto para o meu ojeto vendedor
            rs.next();
            vendedor.setCpf_vendedor(cpf);
            vendedor.setNome_vendedor(rs.getString("nome_vendedor"));
            return true;
        }catch(Exception e){
            return false;
        }
    }
            
    //metodo para buscar produto pelo nome 
    public Produtos PegarProduto(String nome){ 
        String sql = "SELECT * FROM produto WHERE nome_produto LIKE ? ";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE);
            stmt.setString(1,nome);
            ResultSet rs = stmt.executeQuery();
            Produtos produto = new Produtos();
            
            //passando os atributos recebidos do banco de dados direto para o meu ojeto produto
            rs.next();
            produto.setCod_barras(rs.getInt("cod_barras"));
            produto.setNome_produto(rs.getString("nome_produto"));
            produto.setValor("R$" + String.format("%.2f",rs.getFloat("valor")));
            return produto;
        }catch(Exception e){
            return null;
        }
    }
    
    //esse segundo metodo de busca de produtos seria na hora da venda, para indicar o código de barras ao invés do nome do produto
    public Produtos PegarProduto(long cod_barra){
        String sql = "SELECT * FROM produto WHERE cod_barras = ? ";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE);
            stmt.setLong(1,cod_barra);
            ResultSet rs = stmt.executeQuery();
            Produtos produto = new Produtos();
            
            //passando os atributos recebidos do banco de dados direto para o meu ojeto produto
            rs.next();
            produto.setCod_barras(cod_barra);
            produto.setNome_produto(rs.getString("nome_produto"));
            produto.setValor("R$" + String.format("%.2f",rs.getFloat("valor")));
            return produto;
        }catch(Exception e){
            return null;
        }
    }
    
    public boolean EncontrarProduto(long cod_barra){
        String sql = "SELECT * FROM produto WHERE cod_barras = ? ";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE);
            stmt.setLong(1,cod_barra);
            ResultSet rs = stmt.executeQuery();
            Produtos produto = new Produtos();
            
            //passando os atributos recebidos do banco de dados direto para o meu ojeto produto
            rs.next();
            produto.setCod_barras(cod_barra);
            produto.setNome_produto(rs.getString("nome_produto"));
            produto.setValor("R$" + String.format("%.2f",rs.getFloat("valor")));
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    //metodo para buscar cliente
    public Cliente BuscarCliente(long cpf){ 
        String sql = "SELECT * FROM cliente WHERE cpf_cliente = ? ";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE);
            stmt.setLong(1, cpf);
            ResultSet rs = stmt.executeQuery();
            Cliente cliente = new Cliente();
            
            //passando os atributos recebidos do banco de dados direto para o meu ojeto cliente
            rs.next();
            cliente.setCpf_cliente(cpf);
            cliente.setNome_cliente(rs.getString("nome_cliente"));
            return cliente;
        }catch(Exception e){
            return null;
        }
    }
    
    //esse metodo foi criado para verificar se o cliente foi encontrado
    public boolean EncontrarCliente(long cpf){
        String sql = "SELECT * FROM cliente WHERE cpf_cliente = ? ";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE);
            stmt.setLong(1, cpf);
            ResultSet rs = stmt.executeQuery();
            Cliente cliente = new Cliente();
            
            //passando os atributos recebidos do banco de dados direto para o meu ojeto cliente
            rs.next();
            cliente.setCpf_cliente(cpf);
            cliente.setNome_cliente(rs.getString("nome_cliente"));
            return true;
        }catch(Exception e){
            System.out.println("nao foi possivel buscar o cliente\nErro: " + e.getMessage());
            return false;
        }
    }
    
    //buscar lista de vendedores cadastrados
    public List ListaVendedor(){
        String sql = "SELECT * FROM vendedor ORDER BY nome_vendedor";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet rs = stmt.executeQuery();
            List<Vendedor> listaVendedor = new ArrayList<Vendedor>();
            
            while(rs.next()){
                Vendedor vendedor = new Vendedor();
                vendedor.setCpf_vendedor(rs.getLong("cpf_vendedor"));
                vendedor.setNome_vendedor(rs.getString("nome_vendedor"));
                listaVendedor.add(vendedor);
            }
            return listaVendedor;
        }catch(Exception e){
            System.out.println("erro ao criar lista de Vendedor. ERRO:" + e.getMessage());
            return null;
        }
    }
    
    //buscar lista de produtos cadastrados
    public List ListaProduto(){
        String sql = "SELECT * FROM produto ORDER BY cod_barras ";
        
        try{
           PreparedStatement stmt = this.conect.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE);
           ResultSet rs = stmt.executeQuery();
           List<Produtos> listaProdutos = new ArrayList<Produtos>();
           
           while(rs.next()){
               Produtos produto = new Produtos();
               produto.setCod_barras(rs.getLong("cod_barras"));
               produto.setNome_produto(rs.getString("nome_produto"));
               produto.setValor("R$" + String.format("%.2f", rs.getFloat("valor")));
               listaProdutos.add(produto);
           }
           return listaProdutos;
        }catch(Exception e){
            System.out.println("erro ao criar a lista " + e.getMessage());
            return null;
        }
    }
    
    //buscar listas de clientes cadastrados
    public List ListaCliente(){
        String sql = "SELECT * FROM cliente ORDER BY nome_cliente ";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet rs = stmt.executeQuery();
            List<Cliente> listaCliente = new ArrayList<Cliente>();
            
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setCpf_cliente(rs.getLong("cpf_cliente"));
                cliente.setNome_cliente(rs.getString( "nome_cliente"));
                listaCliente.add(cliente);
            }
            return listaCliente;
        }catch(Exception e){
            System.out.println("erro ao criar a lista " + e.getMessage());
            return null;
        }
    }
    
    //buscar listas de vendas realizadas
    public List ListaVendas(){
        String sql = "SELECT vendedor.cpf_vendedor, vendedor.nome_vendedor,cliente.cpf_cliente, " +
                     "cliente.nome_cliente, produto.nome_produto, produto.valor, vendas.data_venda " +
                     "FROM vendas JOIN vendedor JOIN cliente JOIN produto " +
                     "ON vendas.cpf_vendedor = vendedor.cpf_vendedor AND vendas.cpf_cliente = cliente.cpf_cliente " +
                     "AND vendas.cod_produto = produto.cod_barras ";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet rs = stmt.executeQuery();
            List<FormularioVendas> formVenda = new ArrayList<FormularioVendas>();
            
            while(rs.next()){
                //criando objetos do banco de dados
                Vendas venda = new Vendas();
                Vendedor vendedor = new Vendedor();
                Cliente cliente = new Cliente();
                Produtos produto = new Produtos();
                FormularioVendas formulario = new FormularioVendas();
                
                //passando os valores necessários para cada objeto
                venda.setData(rs.getDate("data_venda"));
                vendedor.setCpf_vendedor(rs.getLong("cpf_vendedor"));
                vendedor.setNome_vendedor(rs.getString("nome_vendedor"));
                cliente.setCpf_cliente(rs.getLong("cpf_cliente"));
                cliente.setNome_cliente(rs.getString("nome_cliente"));
                produto.setNome_produto(rs.getString("nome_produto"));
                produto.setValor("R$" + String.format("%.2f", rs.getFloat("valor")));
                
                //passando os valores dos objetos pra outro objeto que engloba todos
                formulario.setCpf_vendedor(vendedor.getCpf_vendedor());
                formulario.setNome_vendedor(vendedor.getNome_vendedor());
                formulario.setCpf_cliente(cliente.getCpf_cliente());
                formulario.setNome_cliente(cliente.getNome_cliente());
                formulario.setNome_produto(produto.getNome_produto());
                formulario.setValor_produto(produto.getValor());
                formulario.setData_venda(venda.getData());
                
                //adicionando esse objeto a minha lista
                formVenda.add(formulario);
            }
            return formVenda;
        }catch(Exception e){
            System.out.println("erro ao criar a lista " + e.getMessage());
            return null;
        }
    }
    
    //metodo para editar vendedor
    public boolean EditarVendedor(long cpf,String nome){
        String sql = "UPDATE vendedor SET nome_vendedor = ? WHERE cpf_vendedor = ? ";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setLong(2, cpf);
            stmt.execute();
            return true;
        }catch(Exception e){
            System.out.println("erro ao editar os dados. erro: " + e.getMessage());
            return false; 
        }
    }
    
    //metodo para editar produto
    public boolean EditarProduto(long cod_barra, String nome, String valor){
        String sql = "UPDATE produto SET nome_produto = ? , valor = ? WHERE cod_barras = ? ";
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql);
            stmt.setString(1,nome);
            stmt.setString(2, valor);
            stmt.setLong(3, cod_barra);
            stmt.execute();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    //metodo para editar cliente
    public boolean EditarCliente(long cpf, String nome){
        String sql = "UPDATE cliente SET nome_cliente = ? WHERE cpf_cliente = ? " ;
        
        try{
            PreparedStatement stmt = this.conect.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setLong(2, cpf);
            stmt.execute();
            return true;
        }catch(Exception e){
            System.out.println("erro ao editar os dados. erro: " + e.getMessage());
            return false;
        }
    }
    
}
