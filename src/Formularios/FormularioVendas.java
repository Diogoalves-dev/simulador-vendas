package Formularios;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author diogo
 */
public class FormularioVendas {
    
    
    private long cpf_vendedor;
    private String nome_vendedor;
    private long cpf_cliente;
    private String nome_cliente;
    private String nome_produto;
    private String valor_produto;
    private Date data_venda = new Date();
    
    public String getData_venda() {
        SimpleDateFormat spdate = new SimpleDateFormat("dd / MM / yyyy");
        String data = spdate.format(this.data_venda);
        return data;
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
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

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public String getValor_produto() {
        return this.valor_produto;
    }

    public void setValor_produto(String valor_produto) {
        this.valor_produto = valor_produto;
    }
    
    
}
