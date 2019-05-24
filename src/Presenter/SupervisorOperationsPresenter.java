/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter;

import View.ISupervisorOperations;
import View.SupervisorOperationsFrame;
import Model.Supervisor;

/**
 *
 * @author Hasancan
 */
public class SupervisorOperationsPresenter {
    
    private ISupervisorOperations view;
    
    public SupervisorOperationsPresenter(ISupervisorOperations view){
        this.view =view;
    }
    
     public void btnAddSupervisor(){
         new Supervisor(view.getTxtName(),view.getTxtSurname(),view.getTxtIdentificationNumber()).addSupervisor();
    }
}
