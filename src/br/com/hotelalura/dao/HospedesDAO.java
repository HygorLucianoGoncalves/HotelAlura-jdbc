package br.com.hotelalura.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.hotelalura.factory.ConnectionFactory;
import br.com.hotelalura.model.Hospedes;
import br.com.hotelalura.model.Reservas;

public class HospedesDAO {

    public static void salvar(Hospedes hospedes) {

        String sql = "INSERT INTO hospedes(nome, sobrenome, dataNascimento, nacionalidade, telefone, idReserva ) VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            // CRIANDO UMA CONEXÃO COM O BANCO DE DADOS
            conn = ConnectionFactory.createConccectionToMySql();

            //CRIAMOS UMA PREPAREDSTRAMENT, PARA EXECUTAR UMA QUERY
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //ADICIONAR OS VALORES QUE SÃO ESPERADOS PELA QUERY
            pstm.setString(1,hospedes.getNome());
            pstm.setString(2, hospedes.getSobrenome());
            pstm.setDate(3, new Date(hospedes.getDataNascimento().getTime()));
            pstm.setString(4, hospedes.getNacionalidade());
            pstm.setString(5, hospedes.getTelefone());
            pstm.setInt(6, hospedes.getIdReserva());

            //EXECUTAR A QUERY
            pstm.execute();

            System.out.println("Hospede salva com sucesso");

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            //FECHAR AS CONEXÕES
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

    public void atualizar(Hospedes hospedes) {
        String sql = "UPDATE hospedes SET nome = ?, sobrenome = ?, dataNascimento = ?, nacionalidade = ?, telefone = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            // CRIA CONEXÃO COM O BANCO
            conn = ConnectionFactory.createConccectionToMySql();

            // CRIA A CLASSE PARA EXECUTAR A QUERY
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //ADICIONAR OS VALORES PARA ATUALIZAR
            //ADICIONAR OS VALORES QUE SÃO ESPERADOS PELA QUERY
            pstm.setString(1,hospedes.getNome());
            pstm.setString(2, hospedes.getSobrenome());
            pstm.setDate(3, new Date(hospedes.getDataNascimento().getTime()));
            pstm.setString(4, hospedes.getNacionalidade());
            pstm.setString(5, hospedes.getTelefone());

            //QUAL O ID DO QUE DESEJA ATUALIZAR
            pstm.setInt(6, hospedes.getId());

            pstm.execute();

            System.out.println("Atualizado com sucesso");

        }catch (Exception e) {

            e.printStackTrace();

        } finally {

            //FECHA AS CONEXÕES
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

        String sql = "DELETE FROM hospedes WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = ConnectionFactory.createConccectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.execute();

        }catch (Exception e) {

            e.printStackTrace();

        }finally {

            //FECHA AS CONEXÕES
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

    public List<Hospedes> buscarBySobrenome(String sobrenome) {

        String sql =  "SELECT * FROM hospedes WHERE sobrenome = ?";

        List<Hospedes> hospedesList = new ArrayList<Hospedes>();

        Connection conn = null;
        PreparedStatement pstm = null;

        // CLASSE QUE VAI RECUERAR A DADOS DO BANCO DE DADOS 
        ResultSet rset = null;

        try {

            conn = ConnectionFactory.createConccectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, sobrenome); // Define o valor do parâmetro
            rset = pstm.executeQuery();

            while (rset.next()) {

                Hospedes hospedes = new Hospedes(
                        rset.getInt("id"),
                        rset.getString("nome"),
                        rset.getString("sobrenome"),
                        rset.getDate("dataNascimento"),
                        rset.getString("nacionalidade"),
                        rset.getString("telefone"),
                        rset.getInt("idReservas")
                );

                hospedesList.add(hospedes);

            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

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
        return hospedesList;

    }

    public List<Hospedes> buscar() {

        String sql =  "SELECT * FROM hospedes";

        List<Hospedes> hospedesList = new ArrayList<Hospedes>();

        Connection conn = null;
        PreparedStatement pstm = null;

        // CLASSE QUE VAI RECUERAR A DADOS DO BANCO DE DADOS 
        ResultSet rset = null;

        try {

            conn = ConnectionFactory.createConccectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Hospedes hospedes = new Hospedes(
                        rset.getInt("id"),
                        rset.getString("nome"),
                        rset.getString("sobrenome"),
                        rset.getDate("dataNascimento"),
                        rset.getString("nacionalidade"),
                        rset.getString("telefone"),
                        rset.getInt("idReservas")
                );

                hospedesList.add(hospedes);

            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

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
        return hospedesList;

    }
}
