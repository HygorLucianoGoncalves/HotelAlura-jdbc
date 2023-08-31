package br.com.hotelalura.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.hotelalura.factory.ConnectionFactory;
import br.com.hotelalura.model.Reservas;

public class ReservasDAO {

    private Connection connection;

    public ReservasDAO() {
    }

    public ReservasDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Reservas reservas) {

        String sql = "INSERT INTO reservas(dataEntrada, dataSaida, valor, formaPagamento ) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            // CRIANDO UMA CONEXÃO COM O BANCO DE DADOS
            conn = ConnectionFactory.createConccectionToMySql();

            //CRIAMOS UMA PREPAREDSTRAMENT, PARA EXECUTAR UMA QUERY
            pstm = (PreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            //ADICIONAR OS VALORES QUE SÃO ESPERADOS PELA QUERY
            pstm.setDate(1, new Date(reservas.getDataEntrada().getTime()));
            pstm.setDate(2,new Date(reservas.getDataSaida().getTime()));
            pstm.setString(3,reservas.getValor());
            pstm.setString(4, reservas.getFormaPagamento());
            //EXECUTAR A QUERY
            pstm.execute();
            // Obtendo o ID gerado pelo banco de dados
            try (ResultSet resultSet = pstm.getGeneratedKeys()) {
                while (resultSet.next()) {
                    reservas.setId(resultSet.getInt(1));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //fechar as conexões
            try {
                if (pstm!=null) {
                    pstm.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    public void atualizar(Reservas reservas) {
        String sql = "UPDATE reservas SET dataEntrada = ?, dataSaida = ?, formaPagamento = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            // CRIA CONEXÃO COM O BANCO
            conn = ConnectionFactory.createConccectionToMySql();

            // CRIA A CLASSE PARA EXECUTAR A QUERY
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //ADICIONAR OS VALORES PARA ATUALIZAR
            //ADICIONAR OS VALORES QUE SÃO ESPERADOS PELA QUERY
            pstm.setDate(1, new Date(reservas.getDataEntrada().getTime()));
            pstm.setDate(2,new Date(reservas.getDataSaida().getTime()));
            pstm.setString(3, reservas.getFormaPagamento());

            //QUAL O ID DO REGISTRO QUE DESEJA ATUALIZAR
            pstm.setInt(4, reservas.getId());

            pstm.execute();
            
            System.out.println("Atualizado com sucesso");
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            //fechar as conexões
            try {
                if (pstm!=null) {
                    pstm.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void deleteById(int id){
        String sql = "DELETE FROM reservas WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConccectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.execute();

        }catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }finally {
            //fechar as conexões
            try {
                if (pstm!=null) {
                    pstm.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public List<Reservas> buscaReservas() {

        String sql = "SELECT * FROM reservas";

        List<Reservas> reservas = new ArrayList<Reservas>();

        Connection conn = null;
        PreparedStatement pstm = null;

        // CLASSE QUE VAI RECUERAR A DADOS DO BANCO DE DADOS 
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConccectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Reservas reservas2 = new Reservas();

                // RECUPERA O ID
                reservas2.setId(rset.getInt(1));
                // RECUPERAR A DATA DE ENTRADA
                reservas2.setDataEntrada(rset.getDate(2));
                //RECUPERA A DATA DE SAIDA 
                reservas2.setDataSaida(rset.getDate(3));
                //RECUPERA O VALOR DA RESERVA
                reservas2.setValor(rset.getString(4));

                reservas2.setFormaPagamento(rset.getString(5));

                reservas.add(reservas2);

            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }finally {
            //fechar as conexões
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm!=null) {
                    pstm.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return reservas;

    }

    public List<Reservas> buscaReservaById(int id) {

        String sql = "SELECT * FROM reservas WHERE id = ?";

        List<Reservas> reservas = new ArrayList<Reservas>();


        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {

            conn = ConnectionFactory.createConccectionToMySql();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Reservas reservas2 = new Reservas();

                // RECUPERA O ID
                reservas2.setId(rset.getInt(1));
                // RECUPERAR A DATA DE ENTRADA
                reservas2.setDataEntrada(rset.getDate(2));
                //RECUPERA A DATA DE SAIDA
                reservas2.setDataSaida(rset.getDate(3));
                //RECUPERA O VALOR DA RESERVA
                reservas2.setValor(rset.getString(4));

                reservas2.setFormaPagamento(rset.getString(5));

                reservas.add(reservas2);

            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }finally {
            //fechar as conexões
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm!=null) {
                    pstm.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return reservas;

    }



}
