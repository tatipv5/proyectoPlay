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
   
    @Constraints.Required
   public String estudiante;
   
 public String getEstudiante(){
     return estudiante;
 }
   
   public void setEstudiante(String nombre){
       estudiante=nombre;
   }
   
     public ArrayList<String>listaEstudiantes =new ArrayList<>();
    int cont=0;
   
//   metodo donde creamos un array que va a guardar estudiantes en un array
   //@Constraints.Required
   public   boolean agregarCEstud(String estud){
       if(cont<25){
         
           listaEstudiantes.add(estud);
           estudiante=nombre;
           cont++;
           System.out.println("El estudiante agregado es: "+estud);
           return true;
       }
       return false;
   }
   
   public static Finder<Long, Grupo> find = new Finder<Long, Grupo>(Grupo.class); 
    
}

