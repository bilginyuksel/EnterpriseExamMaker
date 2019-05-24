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
public class Lecturer {
    
    private String identificationumber;
    private String name;
    private String surname;
    
    public Lecturer(String identificationnumber,String surname,String name){
        this.identificationumber=identificationnumber;
        this.name = name;
        this.surname = surname;    
    }   
    
    public void addLecturer(){
        Model.Add.Add("LECTURER",this.name,this.surname,this.identificationumber);
       
    }
    public String getLecturerFullName(){
        return this.identificationumber + " "+this.surname;
    }
    public String getIdentificationNumber(){
        return this.name;
    }
    @Override
    public String toString(){
        return "<html><b>Identification Number : " +this.name +"<b><br>"
                + "<b>Name : " +this.identificationumber +"<b><br>"
                + "<b>Surname : " +this.surname +"<b></html>";
    }
}
