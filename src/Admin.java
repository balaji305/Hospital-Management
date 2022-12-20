package Hsp_Mng_Sys;

import java.io.*;
import java.util.*;

/**
 *
 * @author Balaji
 */
public class Admin extends Systems implements Methods,Serializable {
    
    Scanner sc=new Scanner(System.in);
    
    ArrayList<Patient> patientList=new ArrayList<>();
    ArrayList<Doctor> doctorList=new ArrayList<>();
    ArrayList<Medicine> medicineList=new ArrayList<>();
    ArrayList<Appointment> appList=new ArrayList<>();
   
    Admin(){
        try{
            FileInputStream fis=new FileInputStream("doctordata.ser");
            ObjectInputStream ois=new ObjectInputStream(fis);
            doctorList=(ArrayList<Doctor>)ois.readObject();
            ois.close();
            fis.close();
        }
        catch(Exception e){}
        
        try{
            FileInputStream fis=new FileInputStream("patientdata.ser");
            ObjectInputStream ois=new ObjectInputStream(fis);
            patientList=(ArrayList<Patient>)ois.readObject();
            ois.close();
            fis.close();
        }
        catch(Exception e){}
        
        try{
            FileInputStream fis=new FileInputStream("medicinedata.ser");
            ObjectInputStream ois=new ObjectInputStream(fis);
            medicineList=(ArrayList<Medicine>)ois.readObject();
            ois.close();
            fis.close();
        }
        catch(Exception e){}
        
        try{
            FileInputStream fis=new FileInputStream("appointmentdata.ser");
            ObjectInputStream ois=new ObjectInputStream(fis);
            appList=(ArrayList<Appointment>)ois.readObject();
            ois.close();
            fis.close();
        }
        catch(Exception e){}
        
        
    }
    
    public int login(String userName,String pwd){
        if(userName.equals("admin")&&pwd.equals("admin")){
            return 1;
        }
        else{
            return 0;
        }
    }
    
    public void menu(String userName){
        boolean check=false;
        
        while(!check){
            print("\t**********************************************************************************************\n");
            print("\t*                                   1. DOCTOR-LIST                                           *\n");
            print("\t*                                   2. PATIENT-LIST                                          *\n");
            print("\t*                                   3. MEDICINE LIST                                         *\n");
            print("\t*                                   4. ADD DOCTOR                                            *\n");
            print("\t*                                   5. ADD MEDICINE                                          *\n");
            print("\t*                                   6. REMOVE DOCTOR                                         *\n");
            print("\t*                                   7. REMOVE MEDICINE                                       *\n");
            print("\t*                                   8. APPOINTMENT LIST                                      *\n");
            print("\t*                                   9. LOGOUT                                                *\n");
            print("\t**********************************************************************************************\n");	
            print("\n\tEnter Your Choice : ");
            int choice = sc.nextInt();
            switch(choice){
                case 1:{
                    println("\n\tDOCTOR'S LIST\n");
                    for(int i=0;i<doctorList.size();i++){
                        showDoctorDetails(doctorList.get(i));
                    }
                    break;
                }
                case 2:{
                    println("\n\tPATIENT'S LIST\n");
                    for(int i=0;i<patientList.size();i++){
                        showPatientDetails(patientList.get(i));
                    }
                    break;
                }
                case 3:{
                    println("\n\tMEDICINE'S LIST\n");
                    for(int i=0;i<medicineList.size();i++){
                        showMedicineDetails(medicineList.get(i));
                    }
                    break;
                }
                case 4:{
                    addDoctor();
                    break;
                }
                case 5:{
                    addMed();
                    break;
                }
                case 6:{
                    delDoctor();
                    break;
                }
                case 7:{
                    delMed();
                    break;
                }
                case 8:{
                    println("\n\tAPPOINTMENT'S LIST\n");
                    for(int i=0;i<appList.size();i++){
                        showAppDetails(appList.get(i));
                    }
                    break;
                }
                case 9:{
                    check=true;
                    break;
                }
                default :{
                    println("\n\tPLEASE CHOOSE AN APPROPRIATE OPTION\n");
                }
            }
        }
    }
    
    public void showDoctorDetails(Doctor d){
        println("\tName : "+d.docName);
        println("\tID : "+d.docId);
        println("\tQualification : "+d.docQual);
        println("\tType : "+d.docType);
        println("\tFees : "+d.docFees);
        println("");
    }
    
    public void showPatientDetails(Patient p){
        println("\tName : "+p.patName);
        println("\tID : "+p.patId);
        println("");
    }
    
    public void showMedicineDetails(Medicine m){
        println("\tName : "+m.medName);
        println("\tID : "+m.medId);
        println("");
    }
    
    public void showAppDetails(Appointment a){
        println("\tDoctor : "+a.appDoc);
        println("\tPatient : "+a.appPatient);
        println("\tID : "+a.appId);
        println("\tFees : "+a.appFees);
        println("");
    }
    
    public void addDoctor(){
        println("\n\tADDING NEW DOCTOR\n");
        Doctor d=new Doctor();
        print("\tNAME-->");
        d.docName=sc.next();
        if(doctorList.size()>0){
            d.docId= doctorList.get(doctorList.size()-1).docId+1;
        }
        else{
            d.docId=501;
        }
        print("\tQUALIFICATION-->");
        d.docQual=sc.next();
        print("\tTYPE-->");
        d.docType=sc.next();
        print("\tFEES-->");
        d.docFees=sc.nextInt();
        println("");
        doctorList.add(d);
        try{
            FileOutputStream fos=new FileOutputStream("doctordata.ser");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(doctorList);
            oos.flush();
            fos.close();
        }
        catch(Exception e){}
        
    }
    
    public void addMed(){
        println("\n\tADDING NEW MEDICINE\n");
        Medicine m=new Medicine();
        print("\tNAME-->");
        m.medName=sc.next();
        if(medicineList.size()>0){
            m.medId=medicineList.get(medicineList.size()-1).medId+1;
        }
        else{
            m.medId=301;
        }
        println("");
        medicineList.add(m);
        try{
            FileOutputStream fos=new FileOutputStream("medicinedata.ser");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(medicineList);
            oos.flush();
            fos.close();
        }
        catch(Exception e){}
    }
    
    public void delDoctor(){
        println("\n\tREMOVING DOCTOR\n");
        print("\tID-->");
        int id=sc.nextInt();
        int index=-1;
        for(int i=0;i<doctorList.size();i++){
            if(doctorList.get(i).docId==id){
                index=i;
            }
        }
        if(index==-1){
            println("\tDoctor Not Found\n");
        }
        else{
            doctorList.remove(index);
            println("\tRemoved Sucessfully\n");
        }
        try{
            FileOutputStream fos=new FileOutputStream("doctordata.ser");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(doctorList);
            oos.flush();
            fos.close();
        }
        catch(Exception e){}
    }
    
    public void delMed(){
        println("\n\tREMOVING MEDICINE\n");
        print("\tID-->");
        int id=sc.nextInt();
        int index=-1;
        for(int i=0;i<medicineList.size();i++){
            if(medicineList.get(i).medId==id){
                index=i;
            }
        }
        if(index==-1){
            println("\tDoctor Not Found\n");
        }
        else{
            medicineList.remove(index);
            println("\tRemoved Sucessfully\n");
        }
        try{
            FileOutputStream fos=new FileOutputStream("medicinedata.ser");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(medicineList);
            oos.flush();
            fos.close();
        }
        catch(Exception e){}
    }
}
