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
import modelVO.UsuarioVO;

/**
 *
 * @author NELSON
 */
public class UsuarioDAO {
     private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL="";
    public Integer totalregistros;
    
    public boolean insertar(UsuarioVO dts){
    
        sSQL ="INSERT INTO USUARIOS (Nombres,Apellidos,Tipo_Documento,Numero_Documento,Fecha_Nacimiento,Pais_Origen,Sexo,Grupo_Sanguineo,RH)"+ 
                "VALUES (?,?,?,?,?,?,?,?,?)";
        
      try{ 
          PreparedStatement pst = cn.prepareStatement(sSQL);
          pst.setString(1, dts.getNombres());
          pst.setString(2, dts.getApellidos());
          pst.setString(3, dts.getTipo_documento());
          pst.setString(4, dts.getNum_documento());
          pst.setDate(5, (Date) dts.getFecha_nacimiento());
          pst.setString(6, dts.getPais_origen());
          pst.setString(7, dts.getSexo());
          pst.setString(8, dts.getGrupo_sanguineo());
          pst.setString(9, dts.getRh());
          
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
     public boolean buscar(UsuarioVO dts,String buscar){
     int cont=0;
   
    sSQL = "SELECT * FROM USUARIOS WHERE Numero_Documento LIKE "+ buscar +" ";
    try{
        Statement st = cn.createStatement();
        ResultSet rs=st.executeQuery(sSQL);
        
        while(rs.next()){
            dts.setNombres(rs.getString("Nombres"));
            dts.setApellidos(rs.getString("Apellidos"));
            dts.setTipo_documento(rs.getString("Tipo_Documento"));
            dts.setNum_documento(rs.getString("Numero_Documento"));
            dts.setFecha_nacimiento(rs.getDate("Fecha_Nacimiento"));
            dts.setPais_origen(rs.getString("Pais_Origen"));
            dts.setSexo(rs.getString("Sexo"));
            dts.setGrupo_sanguineo(rs.getString("Grupo_Sanguineo"));
            dts.setRh(rs.getString("RH"));
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
    public boolean actualizar(UsuarioVO dts){
    sSQL ="UPDATE USUARIOS SET Nombres=?,Apellidos=?,Tipo_Documento=?,Numero_Documento=?,Pais_Origen=?,Sexo=?,Grupo_Sanguineo=?,RH=?" +
            " WHERE Fecha_Nacimiento=?";
    
    
    try{
        PreparedStatement pst = cn.prepareStatement(sSQL);
          
          pst.setString(1, dts.getNombres());
          pst.setString(2, dts.getApellidos());
          pst.setString(3, dts.getTipo_documento());
          pst.setString(4, dts.getNum_documento());
          pst.setString(5, dts.getPais_origen());
          pst.setString(6, dts.getSexo());
          pst.setString(7, dts.getGrupo_sanguineo());
          pst.setString(8, dts.getRh());
          
          pst.setDate(9, (Date) dts.getFecha_nacimiento());
          
          
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
    public boolean eliminar(UsuarioVO dts){
    sSQL ="DELETE FROM USUARIOS WHERE Numero_Documento=?";
    try{
        PreparedStatement pst = cn.prepareStatement(sSQL);
          
          pst.setString(1, dts.getNum_documento());
          
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
