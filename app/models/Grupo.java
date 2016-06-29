/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.avaje.ebean.Model;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;
import play.data.validation.Constraints;

/**
 *
 * @author usuario
 */
@Entity
public class Grupo extends Model{
    @Id
    @Constraints.Min(10)
    public Long id;
    
      @Constraints.Required
   public String nombre;
    
   @Constraints.Required
   public String identificador;
   
 
   
    static public ArrayList<Long>listaEstudiantes;
   
   static int cont=0;
   
//   metodo donde creamos un array que va a guardar estudiantes en un array
   public static  boolean agregarCEstud(Long estud){
       if(cont<25){
           
           listaEstudiantes.add(estud);
           cont++;
           System.out.println("hola");
           return true;
       }
       return false;
   }
   
   public static Finder<Long, Grupo> find = new Finder<Long, Grupo>(Grupo.class); 
    
}

