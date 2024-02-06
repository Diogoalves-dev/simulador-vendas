package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author diogo
 */
public class Conexao {
    
    public Connection getConexao(){
        try{
            Connection conexao = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mercado?Timezone=UTC",
                    "root",
                    "@Dev2004");
            return conexao;
        }catch(Exception e){
            System.out.println("Connection: unstable\nErro: " + e.getMessage());
            return null;
        }
    }
}
