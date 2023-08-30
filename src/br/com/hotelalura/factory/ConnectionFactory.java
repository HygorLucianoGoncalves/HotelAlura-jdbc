package br.com.hotelalura.factory;



import java.sql.Connection;
import java.sql.DriverManager;


import javax.sql.DataSource;

public class ConnectionFactory {


    private DataSource dataSource;



    public ConnectionFactory() {
        ComboPooledDaataSOUce
    }
    //NOME DO USUÁRIO DO MYSQL
    private static final String USERNAME = "root";

    //SENHA DO BANCO
    private static final String PASSWORD = "root";

    //CAMINHO DO BANCO DE DADOS, PORTA , NOME DO BANCO DE DADOS
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/hotelalura";
    /*
     * CONEXÃO COM O BANCO DE DADOS
     */
    public static Connection createConccectionToMySql() throws Exception {
        // CRIA A CONEXÃO COM COM O BANCO
        Connection connection = DriverManager
                .getConnection(
                        DATABASE_URL,
                        USERNAME,
                        PASSWORD);
        return connection;
    }
}

