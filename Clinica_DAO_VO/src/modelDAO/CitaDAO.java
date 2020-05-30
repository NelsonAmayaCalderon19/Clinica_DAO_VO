/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelVO.CitaVO;

/**
 *
 * @author NELSON
 */
public class CitaDAO {
     private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL="";
    public Integer totalregistros;
    
     public DefaultTableModel mostrar(String buscar){
    DefaultTableModel modelo;
    String [] titulos = {"Documento","Tipo Consulta","Fecha Consulta","Medico","Valor a Pagar"};
    String [] registro = new String[5];
    modelo = new DefaultTableModel(null,titulos);
   
    sSQL = "SELECT * FROM CITAS WHERE Numero_Documento LIKE '%"+ buscar +"%' ORDER BY Numero_Documento";
    try{
        Statement st = cn.createStatement();
        ResultSet rs=st.executeQuery(sSQL);
        
        while(rs.next()){
            registro [0]=rs.getString("Numero_Documento");
            registro [1]=rs.getString("Tipo_Consulta");
            registro [2]=rs.getString("Fecha_Cita");
            registro [3]=rs.getString("Medico");
            registro [4]=rs.getString("Valor_Pagar");
            modelo.addRow(registro);
        }
        return modelo;
    }catch(Exception e){
      JOptionPane.showConfirmDialog(null, e);
      return null;
    }
}
      public DefaultTableModel mostrar2(String buscar){
    DefaultTableModel modelo;
    String [] titulos = {"Documento","Tipo Consulta","Fecha Consulta","Medico","Valor a Pagar"};
    String [] registro = new String[5];
    modelo = new DefaultTableModel(null,titulos);
   
    sSQL = "SELECT * FROM CITAS WHERE Numero_Documento LIKE '"+ buscar +"' ORDER BY Numero_Documento";
    try{
        Statement st = cn.createStatement();
        ResultSet rs=st.executeQuery(sSQL);
        
        while(rs.next()){
            registro [0]=rs.getString("Numero_Documento");
            registro [1]=rs.getString("Tipo_Consulta");
            registro [2]=rs.getString("Fecha_Cita");
            registro [3]=rs.getString("Medico");
            registro [4]=rs.getString("Valor_Pagar");
            modelo.addRow(registro);
        }
        return modelo;
    }catch(Exception e){
      JOptionPane.showConfirmDialog(null, e);
      return null;
    }
}
    public boolean insertar(CitaVO dts){
    
        sSQL ="INSERT INTO CITAS (Numero_Documento,Tipo_Consulta,Fecha_Cita,Medico,Valor_Pagar)"+ 
                "VALUES (?,?,?,?,?)";
        
      try{ 
          PreparedStatement pst = cn.prepareStatement(sSQL);
          pst.setString(1, dts.getDocumento());
          pst.setString(2, dts.getTipo_consulta());
          pst.setDate(3,(Date) dts.getFecha_cita());
          pst.setString(4, dts.getMedico());
          pst.setDouble(5, Double.parseDouble(dts.getValor_pagar().toString()));
          
          int n=pst.executeUpdate();
          if(n!=0){
              return true;
          }else{
              return false;
          }
    }catch(Exception e){
        JOptionPane.showConfirmDialog(null, e);
        return false;
    }
}
     public boolean buscar(CitaVO dts,String buscar){
     int cont=0;
   
    sSQL = "SELECT * FROM CITAS WHERE Numero_Documento LIKE "+ buscar +" ";
    try{
        Statement st = cn.createStatement();
        ResultSet rs=st.executeQuery(sSQL);
        
        while(rs.next()){
            
            dts.setDocumento(rs.getString("Numero_Documento"));
            dts.setTipo_consulta(rs.getString("Tipo_Consulta"));
            dts.setFecha_cita(rs.getDate("Fecha_Cita"));
            dts.setMedico(rs.getString("Medico"));
            dts.setValor_pagar(Double.parseDouble(rs.getString("Valor_Pagar")));
            cont++;
        }
           
          if(cont!=0){
              cont=0;
              return true;              
          }else{
              cont=0;
              return false;           
          }
        
        
    }catch(Exception e){
      JOptionPane.showConfirmDialog(null, e);
      cont=0;
       return false;      
    }
}
    public boolean actualizar(CitaVO dts){
    sSQL ="UPDATE CITAS SET Tipo_Consulta=?,Fecha_Cita=?,Medico=?,Valor_Pagar=?" +
            " WHERE Numero_Documento=?";
    
    
    try{
        PreparedStatement pst = cn.prepareStatement(sSQL);
          
          pst.setString(1, dts.getTipo_consulta());
          pst.setDate(2, (Date) dts.getFecha_cita());
          pst.setString(3, dts.getMedico());
          pst.setDouble(4, dts.getValor_pagar());
          
          pst.setString(5, dts.getDocumento());
          
          
          int n=pst.executeUpdate();
          if(n!=0){
              return true;
          }else{
              return false;
          }
    }catch(Exception e){
        JOptionPane.showConfirmDialog(null, e.getMessage());
        return false;
    }
}
    public boolean eliminar(CitaVO dts){
    sSQL ="DELETE FROM CITAS WHERE Numero_Documento=?";
    try{
        PreparedStatement pst = cn.prepareStatement(sSQL);
          
          pst.setString(1, dts.getDocumento());
          
          int n=pst.executeUpdate();
          if(n!=0){
              return true;
          }else{
              return false;
          }
    }catch(Exception e){
        JOptionPane.showConfirmDialog(null, e);
        return false;
    }
} 
}
