/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Hasancan
 */
public class ClassRoom {
    private String buildingName;
    private String hallNo;
    private String hallCode;
    private int capacity;
    private Supervisor supervisor;
    private ArrayList<Student> students = new ArrayList<>();
    
    public ClassRoom(String buildingName,String hallNo,int capacity){
        this.buildingName = buildingName;
        this.hallNo = hallNo;
        this.capacity = capacity;
        this.hallCode = UUID.randomUUID().toString();
        
    }
    public ClassRoom(String buildingName,String hallNo,String hallCode,int capacity){
        this.buildingName = buildingName;
        this.hallNo = hallNo;
        this.hallCode = hallCode;
        this.capacity = capacity;
    }
    
    public String getBuildingName(){
        return buildingName;
    }
    public String getHallCode(){
        return hallCode;
    }
    public String getHallNo(){
        return hallNo;
    }
    public int getCapacity(){
        return capacity;
    }
    
    public void setStudent(Student s){
        
        this.students.add(s);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
    

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }
    
    
    public void addClassRoom(){
        Model.Add.Add(this.buildingName,this.hallNo,this.hallCode,this.capacity);
    }
    public String toString(){
        return "<html>Building : "+this.buildingName +"<br>Hall No : "+this.hallNo +"<br>Capacity : "+this.capacity;
    }
}

