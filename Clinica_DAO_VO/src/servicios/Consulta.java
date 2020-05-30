/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelDAO.CitaDAO;
import vista.Vista;
/**
 *
 * @author NELSON
 */
public class Consulta {
     private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    int cont2=0;
    Vista vista;
    String sSQL;
    public boolean verificarduplicado(String buscar,String buscar2){
        cont2=0;
        boolean estado=false;
String SQL2= "SELECT * FROM usuarios WHERE Tipo_Documento LIKE '"+buscar+"' AND Numero_Documento LIKE '"+buscar2+"'";

try{
        Statement st = cn.createStatement();
        ResultSet rs=st.executeQuery(SQL2);
        
        while(rs.next()){
cont2++;
estado=true;
        }
    }catch(Exception e){
      JOptionPane.showConfirmDialog(null, e);
    }
return estado;
}
public boolean verificarduplicado2(String buscar){
    cont2=0;
    boolean estado=false;
String SQL2= "SELECT * FROM usuarios WHERE Numero_Documento LIKE '"+buscar+"'";
try{
        Statement st = cn.createStatement();
        ResultSet rs=st.executeQuery(SQL2);        
        while(rs.next()){
cont2++;
estado=true;
        }
    }catch(Exception e){
      JOptionPane.showConfirmDialog(null, e);
    }
return estado;
}

}