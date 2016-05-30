/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.avaje.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import play.data.validation.Constraints;

/**
 *
 * @author usuario
 */
@Entity
public class Estudiant extends Model {
    
   @Id
    @Constraints.Min(10)
    public Long id;
//erroren long
    @Constraints.Required
    public String nombre;
     
     @Constraints.Required
    public String direccion;
     
      @Constraints.Required
    public String cedula;
 
        @Constraints.Required
    public String telefono;
        
         public boolean requerida;
    public String textoAyuda;
    
    
    public static Finder<Long, Estudiant> find = new Finder<Long, Estudiant>(Estudiant.class); 
    
}

