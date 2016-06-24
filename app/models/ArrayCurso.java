/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class ArrayCurso {
    
    private ArrayList<Estudiant> listaEstudiante;
 
      public ArrayCurso()
      {
         listaEstudiante=new ArrayList<Estudiant>();
      }
  
    public void insertarCurso(Estudiant estudiante){  
    listaEstudiante.add(estudiante);
    JOptionPane.showMessageDialog(null,"Estudiante insertado a la lista");
    }
    
}
