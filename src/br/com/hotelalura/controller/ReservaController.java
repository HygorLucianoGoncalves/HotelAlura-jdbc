package br.com.hotelalura.controller;

import br.com.hotelalura.dao.*;
import br.com.hotelalura.model.*;

import java.util.*;

public class ReservaController {
    
    private ReservasDAO reservasDAO;

    public ReservaController() {
        this.reservasDAO = new ReservasDAO();
    }

    public void salvar(Reservas reservas){
        this.reservasDAO.salvar(reservas);}
    
    public List<Reservas> buscar(){
        return this.reservasDAO.buscaReservas();
    }
    
    public void atualizar(Reservas reservas){
        this.reservasDAO.atualizar(reservas);
    }
    
    public void delete(int id){
        this.reservasDAO.deleteById(id);
    }
    
    public List<Reservas> buscarById(int id){
        return  this.reservasDAO.buscaReservaById(id);
      
    }
}

