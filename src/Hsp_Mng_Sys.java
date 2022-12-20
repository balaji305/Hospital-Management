package Hsp_Mng_Sys;

import java.util.*;

/**
 *
 * @author Balaji
 */
class AdminLogin extends Thread{
    String userName,pwd;
    Scanner sc;
    
    AdminLogin(){
        sc=new Scanner(System.in);
    }
    public void run(){
        Admin admin=new Admin();
        System.out.println("\n\t******************************   Welcome to Admins portal   **********************************");
        System.out.print("\tUSERNAME-->");
        userName=sc.next();
        System.out.print("\tPASSWORD-->");
        pwd=sc.next();
        if(admin.login(userName,pwd)==1){
            System.out.println("\tSuccess.....\n");
            admin.menu(userName);
        }
        else{
            System.out.println("\tFailed.....\n");
        }
    }
}

class PatientLogin extends Thread{
    String userName,pwd;
    Scanner sc;
    
    PatientLogin(){
        sc=new Scanner(System.in);
    }
    public void run(){
        Patients patients=new Patients();
    System.out.println("\n\t****************************   Welcome to Patients portal   **********************************");
        System.out.print("\tUSERNAME-->");
        userName=sc.next();
        System.out.print("\tPASSWORD-->");
        pwd=sc.next();
        if(patients.login(userName,pwd)==1){
            System.out.println("\tSuccess.....\n");
            patients.menu(userName);
        }
        else{
            System.out.println("\tFailed.....\n");
        }
    }
}

class PatientSignup extends Thread{
    public void run(){
        Patients patients=new Patients();
        System.out.println("\n\t*************************   Welcome to Patients Signup portal   *******************************");
        if(patients.signUp()==1){
            System.out.println("\tSuccess.....\n");
        }
        else{
            System.out.println("\tFailed.....\n");
        }
    }
}

public class Hsp_Mng_Sys extends Systems {
    
    public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);
        boolean check=false;
	println("\n\t******************************  Hospital-Management-Sytem  ***********************************\n");
        while(!check){
            print("\t**********************************************************************************************\n");
            print("\t*                                   1. ADMIN-LOGIN                                           *\n");
            print("\t*                                   2. PATIENT-LOGIN                                         *\n");
            print("\t*                                   3. PATIENT-SIGN-UP                                       *\n");
            print("\t*                                   4. EXIT                                                  *\n");
            print("\t**********************************************************************************************\n");	
            print("\n\tEnter Your Choice : ");
            int choice = sc.nextInt();
            switch (choice){
                case 1:{  
                    Thread t1= new AdminLogin();
                    t1.start();
                    t1.join();
                    break;
                }
                case 2:{
                    Thread t2=new PatientLogin();
                    t2.start();
                    t2.join();
                    break;
                }
                case 3:{
                    Thread t3=new PatientSignup();
                    t3.start();
                    t3.join();
                    break;
                }
                case 4:{
                    println("\n\t************************   THANKS FOR VISITING US - Have A Nice Day   ************************");
                    check = true;
                    break;
                }
                default :{
                    println("\n\tPLEASE CHOOSE AN APPROPRIATE OPTION\n");
                }
            }
        }
    }
}
    