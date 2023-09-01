package br.com.hotelalura.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.*;
import java.time.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.hotelalura.controller.*;
import br.com.hotelalura.model.Hospedes;
import br.com.hotelalura.model.Reservas;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHospedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloHospedes;
    private JLabel labelAtras;
    private JLabel labelExit;
    int xMouse, yMouse;
    private ReservaController reservaController;
    private HospedesController hospedesController;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Buscar frame = new Buscar();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Buscar() {
        this.hospedesController = new HospedesController();
        this.reservaController = new ReservaController();
        setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/br/com/hotelalura/imagenes/lOGO-50PX.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);


        JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
        lblTitulo.setForeground(new Color(12, 138, 199));
        lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblTitulo.setBounds(331, 62, 280, 42);
        contentPane.add(lblTitulo);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);

        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Data Check In");
        modelo.addColumn("Data Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");
        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/br/com/hotelalura/imagenes/reservado.png")), scroll_table, null);
        scroll_table.setVisible(true);


        tbHospedes = new JTable();
        tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
        modeloHospedes.addColumn("Numero de Hóspede");
        modeloHospedes.addColumn("Nome");
        modeloHospedes.addColumn("Sobrenome");
        modeloHospedes.addColumn("Data de Nascimento");
        modeloHospedes.addColumn("Nacionalidade");
        modeloHospedes.addColumn("Telefone");
        modeloHospedes.addColumn("Numero de Reserva");
        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
        panel.addTab("Huéspedes", new ImageIcon(Buscar.class.getResource("/br/com/hotelalura/imagenes/pessoas.png")), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/br/com/hotelalura/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (txtBuscar.getText().isEmpty()) {
                    preencherTabela();
                } else {
                    preencherTabelaHospedesBySobrenomeAndId();
                }
            }
        });
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEditar = new JPanel();
        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int reservas = tbReservas.getSelectedRow();
                int hospedes = tbHospedes.getSelectedRow();
                if (reservas >= 0){
                    alterarReserva();
                } 
                else if (hospedes >= 0){
                    alterarHospedes();
                }
            }
        });
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        JPanel btnDeletar = new JPanel();
        btnDeletar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                delete();
            }
        });
        btnDeletar.setLayout(null);
        btnDeletar.setBackground(new Color(12, 138, 199));
        btnDeletar.setBounds(767, 508, 122, 35);
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnDeletar);

        JLabel lblExcluir = new JLabel("DELETAR");
        lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
        lblExcluir.setForeground(Color.WHITE);
        lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblExcluir.setBounds(0, 0, 122, 35);
        btnDeletar.add(lblExcluir);
        setResizable(false);

    }

    private List<Reservas> buscarReservas() {
        return this.reservaController.buscar();
    }

    private List<Hospedes> buscarHospedes() {
        return hospedesController.buscar();
    }

    private void preencherTabela() {

        List<Hospedes> hospedesList = buscarHospedes();
        List<Reservas> reservaLista = buscarReservas();

        try {
            modeloHospedes.getDataVector().clear();
            tbHospedes.updateUI();
            modelo.getDataVector().clear();
            tbReservas.updateUI();
            for (Reservas reservas : reservaLista) {
                modelo.addRow(new Object[]{
                        reservas.getId(),
                        reservas.getDataEntrada(),
                        reservas.getDataSaida(),
                        reservas.getValor(),
                        reservas.getFormaPagamento()});
            }
            for (Hospedes hospedes : hospedesList) {
                modeloHospedes.addRow(new Object[]{
                        hospedes.getId(),
                        hospedes.getNome(),
                        hospedes.getSobrenome(),
                        hospedes.getDataNascimento(),
                        hospedes.getNacionalidade(),
                        hospedes.getTelefone(),
                        hospedes.getIdReserva()});
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private List<Hospedes> buscarHospedesBySobrenome(JTextField sobreNome) {
        return this.hospedesController.buscaBySobrenome(sobreNome.getText());
    }

    private List<Reservas> buscarReservasById(JTextField id) {
        if (id != null && !id.getText().isEmpty()) {
            int a = Integer.parseInt(id.getText());
            return this.reservaController.buscarById(a);
        }
        return null;
    }


    private void preencherTabelaHospedesBySobrenomeAndId() {

        List<Hospedes> hospedesList = buscarHospedesBySobrenome(txtBuscar);
        List<Reservas> reservaLista = buscarReservasById(txtBuscar);
        try {

            modeloHospedes.getDataVector().clear();
            tbHospedes.updateUI();
            modelo.getDataVector().clear();
            tbReservas.updateUI();

            for (Hospedes hospedes : hospedesList) {
                modeloHospedes.addRow(new Object[]{hospedes.getId(), hospedes.getNome(), hospedes.getSobrenome(), hospedes.getDataNascimento(), hospedes.getNacionalidade(), hospedes.getTelefone(), hospedes.getIdReserva()});
            }
            for (Reservas reservas : reservaLista) {
                modelo.addRow(new Object[]{reservas.getId(), reservas.getDataEntrada(), reservas.getDataSaida(), reservas.getValor(), reservas.getFormaPagamento()});
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //DELETE OK
    private void delete() {
        Object objetoNaLinhaSelecionada = modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn());
        System.out.println(objetoNaLinhaSelecionada);
        if (objetoNaLinhaSelecionada instanceof Integer) {
            Integer id = (Integer) objetoNaLinhaSelecionada;
            hospedesController.deleta(id);
            reservaController.delete(id);
            modelo.removeRow(tbReservas.getSelectedRow());
            JOptionPane.showMessageDialog(this, "Item excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
        }
    }

    //EDIT
    private void alterarReserva() { // testa pra ver ser da certo assim
        Object objetoDaLinhaSelecionado = modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn());
        if (objetoDaLinhaSelecionado instanceof Integer) {
            Integer id = (Integer) objetoDaLinhaSelecionado;
            Date dataEntrada = (Date) modelo.getValueAt(tbReservas.getSelectedRow(), 1);
            Date dataSaida = (Date) modelo.getValueAt(tbReservas.getSelectedRow(), 2);
            String valor = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 3);
            String pagamento = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
            Reservas reservas = new Reservas(id, dataEntrada, dataSaida, valor, pagamento.toUpperCase());
            reservaController.atualizar(reservas);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
        }
    }

    private void alterarHospedes() {
        Object objetoDaLinhaSelecionado = modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn());
        if (objetoDaLinhaSelecionado instanceof Integer) {
            Integer id = (Integer) objetoDaLinhaSelecionado;
            String nome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 1);
            String sobreNome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 2);
            Date dataNacimento = (Date) modelo.getValueAt(tbReservas.getSelectedRow(), 3);
            String nacionalidade = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 4);
            String telefone = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 5);

            Hospedes hospedes = new Hospedes(nome, sobreNome, dataNacimento, nacionalidade, telefone);
            hospedesController.atualizar(hospedes);

        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
        }
    }

    //Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}

