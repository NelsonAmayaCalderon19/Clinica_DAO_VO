/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelVO;

import java.util.Date;

/**
 *
 * @author NELSON
 */
public class CitaVO {
    private String documento;
    private String tipo_consulta;
    private Date fecha_cita;
    private String medico;
    private Double valor_pagar;

    public CitaVO() {
    }

    public CitaVO(String documento, String tipo_consulta, Date fecha_cita, String medico, Double valor_pagar) {
        this.documento = documento;
        this.tipo_consulta = tipo_consulta;
        this.fecha_cita = fecha_cita;
        this.medico = medico;
        this.valor_pagar = valor_pagar;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipo_consulta() {
        return tipo_consulta;
    }

    public void setTipo_consulta(String tipo_consulta) {
        this.tipo_consulta = tipo_consulta;
    }

    public Date getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(Date fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public Double getValor_pagar() {
        return valor_pagar;
    }

    public void setValor_pagar(Double valor_pagar) {
        this.valor_pagar = valor_pagar;
    }

    
    
}
