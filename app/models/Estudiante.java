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
public class Estudiante extends Model {
    
   @Id
    @Constraints.Min(10)
    public long id;

    @Constraints.Required
    public String nombre;
     
     @Constraints.Required
    public String direccion;
     
      @Constraints.Required
    public String cedula;
 
        @Constraints.Required
    public String telefono;

    
    
    public static Finder<Long, Estudiante> find = new Finder<Long, Estudiante>(Estudiante.class); 
    
}

