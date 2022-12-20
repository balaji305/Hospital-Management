package Hsp_Mng_Sys;

import java.io.*;
import java.util.*;

/**
 *
 * @author Balaji
 */
public class Patients implements Methods,Serializable{
    
    static Scanner sc=new Scanner(System.in);
    
    static void print(String s){
        System.out.print(s);
    }
    
    static void println(String s){
        System.out.println(s);
    }
    
    static ArrayList<Patient> patientList=new ArrayList<>();
    static ArrayList<Doctor> doctorList=new ArrayList<>();
    static ArrayList<Medicine> medicineList=new ArrayList<>();
    static ArrayList<Appointment> appList=new ArrayList<>();
    
    Patients(){
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
        for(int i=0;i<patientList.size();i++){
            if(patientList.get(i).patName.equals(userName) && 
                    patientList.get(i).patPwd.equals(pwd)){
                return 1;
            }
        }
        return 0;
    }
    
    public void menu(String userName){
        boolean check=false;
        
        while(!check){
            print("\t**********************************************************************************************\n");
            print("\t*                                   1. DOCTOR-LIST                                           *\n");
            print("\t*                                   2. MEDICINE LIST                                         *\n");
            print("\t*                                   3. SHOW APPOINTMENT                                      *\n");
            print("\t*                                   4. BOOK APPOINTMENT                                      *\n");
            print("\t*                                   5. CANCEL APPOINTMENT                                    *\n");
            print("\t*                                   6. LOGOUT                                                *\n");
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
                    println("\n\tMEDICINE'S LIST\n");
                    for(int i=0;i<medicineList.size();i++){
                        showMedicineDetails(medicineList.get(i));
                    }
                    break;
                }
                case 3:{
                    println("\n\tAPPOINTMENT'S LIST\n");
                    for(int i=0;i<appList.size();i++){
                        if(appList.get(i).appPatient.equals(userName)){
                            showAppDetails(appList.get(i));
                        }
                    }
                    break;
                }
                case 4:{
                    bookApp(userName);
                    break;
                }
                case 5:{
                    cancelApp(userName);
                    break;
                }
                case 6:{
                    check=true;
                    break;
                }
                default :{
                    println("\n\tPLEASE CHOOSE AN APPROPRIATE OPTION\n");
                }
            }
        }
    }
    
    public int signUp(){
        Patient p =new Patient();
        String cPass;
        print("\tNAME-->");
        p.patName=sc.next();
        print("\tPASSWORD-->");
        p.patPwd=sc.next();
        p.patId=patientList.size()+101;
        print("\tCONFIRM PASSWORD-->");
        cPass=sc.next();
        if(cPass.equals(p.patPwd)){
            patientList.add(p);
            try{
                FileOutputStream fos=new FileOutputStream("patientdata.ser");
                ObjectOutputStream oos=new ObjectOutputStream(fos);
                oos.writeObject(patientList);
                oos.flush();
                fos.close();
            }
            catch(Exception e){}
            return 1;
        }
        return 0;
        
    }
    
    public void showDoctorDetails(Doctor d){
        println("\tName : "+d.docName);
        println("\tID : "+d.docId);
        println("\tQualification : "+d.docQual);
        println("\tType : "+d.docType);
        println("\tFees : "+d.docFees);
        println("");
    }
    
    public void showMedicineDetails(Medicine m){
        println("\tName : "+m.medName);
        println("\tID : "+m.medId);
        println("");
    }
    
    public void bookApp(String userName){
        println("\n\tBOOKING APPOINTMENT\n");
        Appointment a=new Appointment();
        a.appPatient=userName;
        print("\tDOCTOR ID-->");
        int id=sc.nextInt();
        for(int i=0;i<doctorList.size();i++){
            if(doctorList.get(i).docId==id){
                a.appFees=doctorList.get(i).docFees;
                a.appDoc=doctorList.get(i).docName;
            }
        }
        if(appList.size()>0){
            a.appId=appList.get(appList.size()-1).appId+1;
        }
        else{
            a.appId=901;
        }
        appList.add(a);
        try{
            FileOutputStream fos=new FileOutputStream("appointmentdata.ser");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(appList);
            oos.flush();
            fos.close();
        }
        catch(Exception e){}

    }
    
    public void showAppDetails(Appointment a){
        println("\tDoctor : "+a.appDoc);
        println("\tPatient : "+a.appPatient);
        println("\tID : "+a.appId);
        println("\tFees : "+a.appFees);
        println("");
    }
    
    public void cancelApp(String userName){      
        println("\n\tCANCELLING APPOINTMENT\n");
        print("\tAPPOINTMENT ID-->");
        int id=sc.nextInt();
        int index=-1;
        for(int i=0;i<appList.size();i++){
            if(appList.get(i).appPatient.equals(userName)){
                if(appList.get(i).appId==id){
                    index=i;
                }
            }
        }
        if(index==-1){
            println("\tNo Such Appointment Found\n");
        }
        else{
            appList.remove(index);
            println("\tRemoved Sucessfully\n");
        }
        try{
            FileOutputStream fos=new FileOutputStream("appointmentdata.ser");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(appList);
            oos.flush();
            fos.close();
        }
        catch(Exception e){}
    }
}
