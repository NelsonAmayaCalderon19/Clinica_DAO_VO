
package control;

import modelDAO.*;
import modelVO.*;
import servicios.Consulta;
import java.sql.*;
import conexion.Conexion;
import vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author NELSON
 */
public class Controlador implements ActionListener{
    private Vista vista;
    Consulta consulta;
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL="";
    int cont2=0;
    public Controlador(Vista vista){       
    super(); 
    this.vista=vista;
    actionListener(this);
    this.vista.dc1.setDate(new java.util.Date());
    this.vista.dc2.setDate(new java.util.Date());
    vista.btnActualizar.setEnabled(false);
    vista.btnEliminar.setEnabled(false);
    vista.btnActualizar2.setEnabled(false);
    vista.btnEliminar2.setEnabled(false);
    mostrar("");
    
    }
private void actionListener(ActionListener controlador){
    vista.btnRegistrar.addActionListener(controlador);
    vista.btnLimpiarRegistro.addActionListener(controlador);
    vista.btnBuscar.addActionListener(controlador);
    vista.btnActualizar.addActionListener(controlador);
    vista.btnEliminar.addActionListener(controlador);   
    vista.btnAsignar.addActionListener(controlador);
    vista.btnLimpiarAsignacion.addActionListener(controlador);
    vista.btnBuscar2.addActionListener(controlador);
    vista.btnActualizar2.addActionListener(controlador);
    vista.btnEliminar2.addActionListener(controlador);  
    vista.btnListar.addActionListener(controlador);
    vista.btnListar2.addActionListener(controlador);
    }
void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            CitaDAO func = new CitaDAO();
            modelo = func.mostrar(buscar);
            vista.tabla.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }
void mostrar2(String buscar) {
        try {
            DefaultTableModel modelo;
            CitaDAO func = new CitaDAO();
            modelo = func.mostrar2(buscar);
            vista.tabla.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }
void Limpiar(){
vista.t1.setText("");
vista.t2.setText("");
vista.t3.setText("");
vista.cb1.setSelectedIndex(0);
vista.cb2.setSelectedIndex(0);
vista.cb3.setSelectedIndex(0);
vista.btnBuscar.setEnabled(true);
    vista.btnActualizar.setEnabled(false);
    vista.btnEliminar.setEnabled(false);
    vista.btnRegistrar.setEnabled(true);
}
void Limpiar2(){
vista.t4.setText("");
vista.t5.setText("");
vista.cb4.setSelectedIndex(0);
vista.cb5.setSelectedIndex(0);
vista.btnBuscar2.setEnabled(true);
    vista.btnActualizar2.setEnabled(false);
    vista.btnEliminar2.setEnabled(false);
    vista.btnAsignar.setEnabled(true);
}
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            int seleccion = vista.cb1.getSelectedIndex();
        String tipo=vista.cb1.getItemAt(seleccion);
        if(e.getSource()==vista.btnRegistrar){
         if (vista.t1.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar los Nombres del Usuario");
            vista.t1.requestFocus();
            return;
        }
        if (vista.t2.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar los Apellidos del Usuario");
            vista.t2.requestFocus();
            return;
        }

        if (vista.t3.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar el Numero de Documento del Usuario");
            vista.t3.requestFocus();
            return;
        }
        Consulta consulta = new Consulta();
        if(consulta.verificarduplicado(tipo,vista.t3.getText())==true){
JOptionPane.showMessageDialog(null,"Ojo Este Usuario Ya se Encuentra Registrado\n ","Advertencia",JOptionPane.WARNING_MESSAGE);
Limpiar();
}
        else{
        UsuarioVO dts = new UsuarioVO();
        UsuarioDAO func = new UsuarioDAO();       
        dts.setNombres(vista.t1.getText());
        dts.setApellidos(vista.t2.getText());
        int seleccionado = vista.cb1.getSelectedIndex();
        dts.setTipo_documento((String) vista.cb1.getItemAt(seleccionado));
        dts.setNum_documento(vista.t3.getText());
        Calendar cal;
        int d,m,a;
        cal=vista.dc1.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR) - 1900;
        dts.setFecha_nacimiento(new java.sql.Date(a,m,d));
        seleccionado = vista.cb2.getSelectedIndex();
        dts.setPais_origen((String) vista.cb2.getItemAt(seleccionado));
        String sexo="";        
        if (vista.rb1.isSelected()) {
            sexo = "Masculino";
            dts.setSexo(sexo);
        }if (vista.rb2.isSelected()) {
            sexo = "Femenino";
            dts.setSexo(sexo);
        }if (vista.rb3.isSelected()) {
            sexo = "Otro";
            dts.setSexo(sexo);
        }
        seleccionado = vista.cb3.getSelectedIndex();
        dts.setGrupo_sanguineo((String) vista.cb3.getItemAt(seleccionado));
        String rh="";
        if (vista.rb4.isSelected()) {
            rh = "Positivo";
            dts.setRh(rh);
        }if (vista.rb5.isSelected()) {
            rh = "Negativo";
            dts.setRh(rh);
        }
        if (func.insertar(dts)) {
                JOptionPane.showMessageDialog(null, "El Usuario fue Registrado Exitosamente");
                Limpiar();                
        }
        }
        }if(e.getSource()==vista.btnLimpiarRegistro){
        Limpiar();
        }if(e.getSource()==vista.btnBuscar){
            try{
        if (vista.t3.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar el Numero de Documento del Usuario");
            vista.t3.requestFocus();
            return;
        }
UsuarioVO dts = new UsuarioVO();
        UsuarioDAO func = new UsuarioDAO();
dts.setNum_documento(vista.t3.getText());
if (func.buscar(dts,vista.t3.getText())) {
                JOptionPane.showMessageDialog(null, "El Usuario fue Encontrado Satisfactoriamente");
                vista.t1.setText(dts.getNombres());
                vista.t2.setText(dts.getApellidos());                
                vista.cb1.setSelectedItem((dts.getTipo_documento()));
                vista.cb2.setSelectedItem((dts.getPais_origen()));
                vista.cb3.setSelectedItem((dts.getGrupo_sanguineo()));
                vista.dc1.setDate(dts.getFecha_nacimiento());
                if(dts.getSexo().equals("Masculino")){
                vista.rb1.setSelected(true);
                }if(dts.getSexo().equals("Femenino")){
                vista.rb2.setSelected(true);
                }if(dts.getSexo().equals("Otro")){
                vista.rb3.setSelected(true);
                }
                if(dts.getRh().equals("Positivo")){
                vista.rb4.setSelected(true);
                }if(dts.getRh().equals("Negativo")){
                vista.rb5.setSelected(true);
                }
                vista.btnActualizar.setEnabled(true);
    vista.btnEliminar.setEnabled(true);
    vista.btnRegistrar.setEnabled(false);
    vista.btnBuscar.setEnabled(false);
            }else{
JOptionPane.showMessageDialog(null, "El Usuario No fue Encontrado en la Base de Datos");
Limpiar();
}
        } catch (Exception ev) {
            JOptionPane.showConfirmDialog(null, ev.getMessage());
        }
        }if(e.getSource()==vista.btnActualizar){
        if (vista.t1.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar los Nombres del Usuario");
            vista.t1.requestFocus();
            return;
        }
        if (vista.t2.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar los Apellidos del Usuario");
            vista.t2.requestFocus();
            return;
        }
        if (vista.t3.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar el Numero de Documento Usuario");
            vista.t3.requestFocus();
            return;
        }
        UsuarioVO dts = new UsuarioVO();
        UsuarioDAO func = new UsuarioDAO();        
        dts.setNombres(vista.t1.getText());
        dts.setApellidos(vista.t2.getText());
        int seleccionado = vista.cb1.getSelectedIndex();
        dts.setTipo_documento((String) vista.cb1.getItemAt(seleccionado));
        dts.setNum_documento(vista.t3.getText());       
        seleccionado = vista.cb2.getSelectedIndex();
        dts.setPais_origen((String) vista.cb2.getItemAt(seleccionado));
        String sexo="";       
        if (vista.rb1.isSelected()) {
            sexo = "Masculino";
            dts.setSexo(sexo);
        }if (vista.rb2.isSelected()) {
            sexo = "Femenino";
            dts.setSexo(sexo);
        }if (vista.rb3.isSelected()) {
            sexo = "Otro";
            dts.setSexo(sexo);
        }
        seleccionado = vista.cb3.getSelectedIndex();
        dts.setGrupo_sanguineo((String) vista.cb3.getItemAt(seleccionado));
        String rh="";
        if (vista.rb4.isSelected()) {
            rh = "Positivo";
            dts.setRh(rh);
        }if (vista.rb5.isSelected()) {
            rh = "Negativo";
            dts.setRh(rh);
        }
        Calendar cal;
        int d,m,a;
        cal=vista.dc1.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR) - 1900;
        dts.setFecha_nacimiento(new java.sql.Date(a,m,d));       
        func.actualizar(dts);
                JOptionPane.showMessageDialog(null, "Los Datos del Usuario Fueron Actualizados Exitosamente");
                Limpiar();   
              
        }if(e.getSource()==vista.btnEliminar){
        if (!vista.t3.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "Estás seguro de Eliminar el Usuario?","Confirmar",2);         
            if (confirmacion==0) {
        UsuarioVO dts = new UsuarioVO();
        UsuarioDAO func = new UsuarioDAO();                
                dts.setNum_documento(vista.t3.getText());
                func.eliminar(dts);
                JOptionPane.showMessageDialog(null, "Usuario Eliminado Exitosamente");
               Limpiar();
            }          
        }  
        }if(e.getSource()==vista.btnAsignar){
        if (vista.t4.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar el Numero de Documento del Usuario");
            vista.t4.requestFocus();
            return;
        }
        if (vista.t5.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar el Valor de la Consulta");
            vista.t5.requestFocus();
            return;
        }  
        Consulta consulta = new Consulta();
        if(consulta.verificarduplicado2(vista.t4.getText())==false){
        JOptionPane.showMessageDialog(null,"Ojo Este Usuario No se Encuentra Registrado\n ","Advertencia Debe Registrarse",JOptionPane.WARNING_MESSAGE);
Limpiar2();
        }else{
        CitaVO dts = new CitaVO();
        CitaDAO func = new CitaDAO();       
        dts.setDocumento(vista.t4.getText());
        dts.setValor_pagar(Double.parseDouble(vista.t5.getText()));
        int seleccionado = vista.cb4.getSelectedIndex();
        dts.setTipo_consulta((String) vista.cb4.getItemAt(seleccionado));
        seleccionado = vista.cb5.getSelectedIndex();
        dts.setMedico((String) vista.cb5.getItemAt(seleccionado));
        Calendar cal;
        int d,m,a;
        cal=vista.dc2.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR) - 1900;
        dts.setFecha_cita(new java.sql.Date(a,m,d));
        func.insertar(dts);
                JOptionPane.showMessageDialog(null, "la Cita fue Asignada Exitosamente");
                Limpiar2();                
                mostrar("");
        
        }
        }if(e.getSource()==vista.btnBuscar2){
        try{
        if (vista.t4.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar el Numero de Documento del Usuario");
            vista.t4.requestFocus();
            return;
        }
        CitaVO dts = new CitaVO();
        CitaDAO func = new CitaDAO();
dts.setDocumento(vista.t4.getText());
if (func.buscar(dts,vista.t4.getText())) {
                JOptionPane.showMessageDialog(null, "La Cita fue Encontrada Satisfactoriamente");
                vista.cb4.setSelectedItem((dts.getTipo_consulta()));
                vista.cb5.setSelectedItem((dts.getMedico()));                
                vista.dc2.setDate(dts.getFecha_cita());
                vista.t5.setText(dts.getValor_pagar().toString());
                vista.btnActualizar2.setEnabled(true);
    vista.btnEliminar2.setEnabled(true);
    vista.btnAsignar.setEnabled(false);
    vista.btnBuscar2.setEnabled(false);               
            }else{
JOptionPane.showMessageDialog(null, "La Cita No fue Encontrada en la Base de Datos");
Limpiar2();
}        
        } catch (Exception ev) {
            JOptionPane.showConfirmDialog(null, ev.getMessage());
        }
        }if(e.getSource()==vista.btnActualizar2){
        if (vista.t4.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar el Numero de Documento del Usuario");
            vista.t4.requestFocus();
            return;
        }
        if (vista.t5.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar el Valor de la Consulta");
            vista.t5.requestFocus();
            return;
        }
        CitaVO dts = new CitaVO();
        CitaDAO fun = new CitaDAO();
        dts.setDocumento(vista.t4.getText());
        dts.setValor_pagar(Double.parseDouble(vista.t5.getText()));
        int seleccionado = vista.cb4.getSelectedIndex();
        dts.setTipo_consulta((String) vista.cb4.getItemAt(seleccionado));
        seleccionado = vista.cb5.getSelectedIndex();
        dts.setMedico((String) vista.cb5.getItemAt(seleccionado));
        Calendar cal;
        int d,m,a;
        cal=vista.dc2.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR) - 1900;
        dts.setFecha_cita(new java.sql.Date(a,m,d));
        
        fun.actualizar(dts);
                JOptionPane.showMessageDialog(null, "Las Caracteristicas de la Cita Fueron Actualizados Exitosamente");
                Limpiar2();
                mostrar("");
            
        }if(e.getSource()==vista.btnEliminar2){
        if (!vista.t4.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "Estás seguro de Eliminar la Consulta?","Confirmar",2);           
            if (confirmacion==0) {
        CitaVO dts = new CitaVO();
        CitaDAO func = new CitaDAO();
                
                dts.setDocumento(vista.t4.getText());
                func.eliminar(dts);
                JOptionPane.showMessageDialog(null, "Consulta Eliminada Exitosamente");
               Limpiar();                
               mostrar("");  
            }            
        }
        }if(e.getSource()==vista.btnLimpiarAsignacion){
        Limpiar2();
        }if(e.getSource()==vista.btnListar){
       mostrar("");
       vista.t6.setText("");
        }if(e.getSource()==vista.btnListar2){
       mostrar2(vista.t6.getText());
        }  
        }catch(Exception ev){
        JOptionPane.showMessageDialog(null,ev.getMessage());
        }  
    }   
}
