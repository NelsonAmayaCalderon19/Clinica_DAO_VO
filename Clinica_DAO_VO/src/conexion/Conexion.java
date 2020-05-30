package conexion;



import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
    public String db="clinica";
    public String url="jdbc:mysql://localhost/" +db;
    public String user="root";
    public String pass="";
    
    public Conexion(){
        
    }
    
    public Connection conectar(){
        Connection link=null;
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            link=DriverManager.getConnection(this.url, this.user, this.pass);
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return link;
    }
}
