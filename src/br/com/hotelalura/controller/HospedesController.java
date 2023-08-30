package br.com.hotelalura.controller;

import br.com.hotelalura.dao.*;
import br.com.hotelalura.model.*;

import java.util.*;

public class HospedesController {
    private HospedesDAO hospedesDAO;

    public HospedesController() {
        this.hospedesDAO = new HospedesDAO();
    }
    
    public void salvar(Hospedes hospedes){
        this.hospedesDAO.salvar(hospedes);
    }
    public List<Hospedes> buscar(){
        return this.hospedesDAO.buscar();
    }
    
    public void atualizar(Hospedes hospedes){
        this.hospedesDAO.atualizar(hospedes);
    }
    
    public void deleta(int id){
        this.hospedesDAO.deleteById(id);
    }
}
