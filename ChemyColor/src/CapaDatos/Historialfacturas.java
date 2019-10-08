/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Amaya
 */
@Entity
@Table(name = "HISTORIALFACTURAS")
@NamedQueries({
    @NamedQuery(name = "Historialfacturas.findAll", query = "SELECT h FROM Historialfacturas h")})
@NamedStoredProcedureQuery(
        name = "Historialfacturas.EntraFactura",
        procedureName = "EntraFactura",
        parameters = {
            @StoredProcedureParameter(name = "p_numFactura", mode = ParameterMode.IN, type = String.class),
            @StoredProcedureParameter(name = "p_cont_O_credit", mode = ParameterMode.IN, type = Integer.class),
            @StoredProcedureParameter(name = "p_CantidadDiasCredit", mode = ParameterMode.IN, type = Integer.class),
        @StoredProcedureParameter(name = "p_estado", mode = ParameterMode.IN, type = Integer.class),
        @StoredProcedureParameter(name = "p_codigoBodega", mode = ParameterMode.IN, type = Integer.class),
        @StoredProcedureParameter(name = "p_codigoArticulo", mode = ParameterMode.IN, type = Integer.class),
        @StoredProcedureParameter(name = "p_fecha", mode = ParameterMode.IN, type = Date.class),
        @StoredProcedureParameter(name = "p_Factura", mode = ParameterMode.IN, type = String.class),
        @StoredProcedureParameter(name = "p_CCF", mode = ParameterMode.IN, type = String.class),
        @StoredProcedureParameter(name = "p_ticket", mode = ParameterMode.IN, type = String.class),
        @StoredProcedureParameter(name = "p_ajusteInventario", mode = ParameterMode.IN, type = String.class),
        @StoredProcedureParameter(name = "p_codigoProveedor", mode = ParameterMode.IN, type = Integer.class),
        @StoredProcedureParameter(name = "p_eUnidad", mode = ParameterMode.IN, type = Integer.class),
        @StoredProcedureParameter(name = "p_ePrecio", mode = ParameterMode.IN, type = Double.class),
        @StoredProcedureParameter(name = "p_Detalle", mode = ParameterMode.IN, type = String.class),}
)

public class Historialfacturas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private BigDecimal codigo;
    @Basic(optional = false)
    @Column(name = "NUMFACTURA")
    private String numfactura;
    @Basic(optional = false)
    @Column(name = "CONT_O_CREDIT")
    private BigInteger contOCredit;
    @Column(name = "CANTIDADDIASCREDIT")
    private BigInteger cantidaddiascredit;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private BigInteger estado;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "FACTURA")
    private String factura;
    @Column(name = "CCF")
    private String ccf;
    @Column(name = "TICKET")
    private String ticket;
    @Column(name = "AJUSTEINVENTARIO")
    private String ajusteinventario;
    @Column(name = "EUNIDAD")
    private BigInteger eunidad;
    @Column(name = "EPRECIO")
    private BigDecimal eprecio;
    @Column(name = "ETOTAL")
    private BigDecimal etotal;
    @Column(name = "OUNIDAD")
    private BigInteger ounidad;
    @Column(name = "OPRECIO")
    private BigDecimal oprecio;
    @Column(name = "OTOTAL")
    private BigDecimal ototal;
    @Column(name = "OCLIENTE")
    private String ocliente;
    @Column(name = "SUNIDAD")
    private BigInteger sunidad;
    @Column(name = "SCOSTOPROMEDIO")
    private BigDecimal scostopromedio;
    @Column(name = "STOTAL")
    private BigDecimal stotal;
    @Column(name = "DETALLE")
    private String detalle;
    @JoinColumn(name = "CODIGOARTICULO", referencedColumnName = "CODIGOARTICULO")
    @ManyToOne
    private Articulos codigoarticulo;
    @JoinColumn(name = "CODIGOBODEGA", referencedColumnName = "CODIGOBODEGA")
    @ManyToOne
    private Bodegas codigobodega;
    @JoinColumn(name = "CODIGOPROVEEDOR", referencedColumnName = "CODIGOPROVEEDOR")
    @ManyToOne
    private Proveedores codigoproveedor;

    public Historialfacturas() {
    }

    public Historialfacturas(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public Historialfacturas(BigDecimal codigo, String numfactura, BigInteger contOCredit, BigInteger estado) {
        this.codigo = codigo;
        this.numfactura = numfactura;
        this.contOCredit = contOCredit;
        this.estado = estado;
    }

    public BigDecimal getCodigo() {
        return codigo;
    }

    public void setCodigo(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public String getNumfactura() {
        return numfactura;
    }

    public void setNumfactura(String numfactura) {
        this.numfactura = numfactura;
    }

    public BigInteger getContOCredit() {
        return contOCredit;
    }

    public void setContOCredit(BigInteger contOCredit) {
        this.contOCredit = contOCredit;
    }

    public BigInteger getCantidaddiascredit() {
        return cantidaddiascredit;
    }

    public void setCantidaddiascredit(BigInteger cantidaddiascredit) {
        this.cantidaddiascredit = cantidaddiascredit;
    }

    public BigInteger getEstado() {
        return estado;
    }

    public void setEstado(BigInteger estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getCcf() {
        return ccf;
    }

    public void setCcf(String ccf) {
        this.ccf = ccf;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getAjusteinventario() {
        return ajusteinventario;
    }

    public void setAjusteinventario(String ajusteinventario) {
        this.ajusteinventario = ajusteinventario;
    }

    public BigInteger getEunidad() {
        return eunidad;
    }

    public void setEunidad(BigInteger eunidad) {
        this.eunidad = eunidad;
    }

    public BigDecimal getEprecio() {
        return eprecio;
    }

    public void setEprecio(BigDecimal eprecio) {
        this.eprecio = eprecio;
    }

    public BigDecimal getEtotal() {
        return etotal;
    }

    public void setEtotal(BigDecimal etotal) {
        this.etotal = etotal;
    }

    public BigInteger getOunidad() {
        return ounidad;
    }

    public void setOunidad(BigInteger ounidad) {
        this.ounidad = ounidad;
    }

    public BigDecimal getOprecio() {
        return oprecio;
    }

    public void setOprecio(BigDecimal oprecio) {
        this.oprecio = oprecio;
    }

    public BigDecimal getOtotal() {
        return ototal;
    }

    public void setOtotal(BigDecimal ototal) {
        this.ototal = ototal;
    }

    public String getOcliente() {
        return ocliente;
    }

    public void setOcliente(String ocliente) {
        this.ocliente = ocliente;
    }

    public BigInteger getSunidad() {
        return sunidad;
    }

    public void setSunidad(BigInteger sunidad) {
        this.sunidad = sunidad;
    }

    public BigDecimal getScostopromedio() {
        return scostopromedio;
    }

    public void setScostopromedio(BigDecimal scostopromedio) {
        this.scostopromedio = scostopromedio;
    }

    public BigDecimal getStotal() {
        return stotal;
    }

    public void setStotal(BigDecimal stotal) {
        this.stotal = stotal;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Articulos getCodigoarticulo() {
        return codigoarticulo;
    }

    public void setCodigoarticulo(Articulos codigoarticulo) {
        this.codigoarticulo = codigoarticulo;
    }

    public Bodegas getCodigobodega() {
        return codigobodega;
    }

    public void setCodigobodega(Bodegas codigobodega) {
        this.codigobodega = codigobodega;
    }

    public Proveedores getCodigoproveedor() {
        return codigoproveedor;
    }

    public void setCodigoproveedor(Proveedores codigoproveedor) {
        this.codigoproveedor = codigoproveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historialfacturas)) {
            return false;
        }
        Historialfacturas other = (Historialfacturas) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CapaDatos.Historialfacturas[ codigo=" + codigo + " ]";
    }

}
