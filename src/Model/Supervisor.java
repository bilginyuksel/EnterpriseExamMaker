/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Hasancan
 */
public class Supervisor {
    
    private String name;
    private String surname;
    private String identificationumber;
    
    public Supervisor(String name,String surname,String identificationnumber){
        this.name = name;
        this.surname = surname;
        this.identificationumber=identificationnumber;
                  
    }   
    
    public void addSupervisor(){
        Model.Add.Add("SUPERVISOR",this.name,this.surname,this.identificationumber);
       
    }
    
    public String toString(){
        return "<html>Supervisor ID : "+this.identificationumber+"<br>Supervisor : "+this.name+" "+this.surname;
    }
}
