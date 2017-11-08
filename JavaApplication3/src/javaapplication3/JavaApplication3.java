/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

/**
 *
 * @author usuario
 */
public class JavaApplication3 {

    
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
       
        MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
    }

}

    
    

