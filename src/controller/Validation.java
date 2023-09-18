
package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Flower;



public class Validation {
    private final Scanner sc = new Scanner(System.in);
    
    // get non-empty string 
    public String inputString(String msg)
    {
       
        String rs = "";
        while(true)
        {
            System.out.println(msg);
            rs = sc.nextLine();
            if(rs == "") System.out.println("Not allowed null please input again");
            else break;
        }
        return rs;
    }
    
   
    
   
      
    public String inputDescription(String msg)
    {
        String rs = "";
        while(true)
        {
             rs = inputString(msg);
             int lengthDescription = rs.length();
             if(lengthDescription< 3 || lengthDescription >50)
             {
                 System.out.println("Must be 3 to 50 characters");
             }
             else break;
        }
        return rs;
    }
    public String inputPhone(String msg)
    {
        String rs = "";
        while(true)
        {
             rs = inputString(msg);
             int lengthPhone = rs.length();
             
             if(lengthPhone < 10 || lengthPhone >11)
             {
                 System.out.println("Phone must be 10 to 11 numbers");
             }
             
             else break;
        }
        return rs;
    }
    // The age field must be a positive number.
    
    public int inputInt(String msg, int min, int max){
        System.out.println(msg);
        while(true)
        {
              String rs_raw = sc.nextLine();
              try{
                 int rs = Integer.parseInt(rs_raw);
                 if(rs<min || rs > max) 
                 {
                     System.out.println("This number must be " + min + " to " + max);
                     continue;
                 }
                 return rs;
              }catch(NumberFormatException e){
                  System.out.println("This must be number ");
                  
              }
             
        }
    }
    
    // The salary field must be a positive number.
    
    public double inputDouble(String msg, double min, double max){
        System.out.println(msg);
        while(true)
        {
              String rs_raw = sc.nextLine();
              try{
                 double rs = Double.parseDouble(rs_raw);
                 if(rs<min || rs > max) 
                 {
                     System.out.println("This number must be " + min + " to " + max);
                     continue;
                     
                 }
                 return rs;
              }catch(NumberFormatException e){
                  System.out.println("This must be number ");
              }
             
        }
    }
    
    // ▪ The admissionDate, dischargeDate fields must be a valid date format.
    
    public String inputDate(String msg)
    {
        String regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        String date = "";
       
        while(true){
            date = inputString(msg);
            Matcher matcher = pattern.matcher(date);
            if(!matcher.matches()){
                System.out.println("The format date incorrect");
                continue;
            }
            return date;
        }
    }
    
    
    public String inputID(Set<Flower> ls,String msg)
    {
      String rs = "";
      while(true)
      {
      int count = 0;
      rs = inputString(msg);
      Iterator<Flower> iterator = ls.iterator();
      while(iterator.hasNext()){
          Flower f = iterator.next();
          if(f.getId().equals(rs)){
              System.out.println(" ID existed !! Please input again");
              count++;
              break;
          }
      }
      if(count == 0) return rs;
      }
      
    }

    
    
        
        
    
    
     
    

        
    
    
}
