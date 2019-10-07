/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Amaya
 */
@Entity
@Table(name = "TELEFONOSPROVEEDOR")
@NamedQueries({
    @NamedQuery(name = "Telefonosproveedor.findAll", query = "SELECT t FROM Telefonosproveedor t")})
public class Telefonosproveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "TELEFONO")
    private String telefono;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTELEFONO")
    private BigDecimal idtelefono;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;
    @JoinColumn(name = "CODIGOPROVEEDOR", referencedColumnName = "CODIGOPROVEEDOR")
    @ManyToOne(optional = false)
    private Proveedores codigoproveedor;

    public Telefonosproveedor() {
    }

    public Telefonosproveedor(BigDecimal idtelefono) {
        this.idtelefono = idtelefono;
    }

    public Telefonosproveedor(BigDecimal idtelefono, String telefono, String tipo) {
        this.idtelefono = idtelefono;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public BigDecimal getIdtelefono() {
        return idtelefono;
    }

    public void setIdtelefono(BigDecimal idtelefono) {
        this.idtelefono = idtelefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        hash += (idtelefono != null ? idtelefono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefonosproveedor)) {
            return false;
        }
        Telefonosproveedor other = (Telefonosproveedor) object;
        if ((this.idtelefono == null && other.idtelefono != null) || (this.idtelefono != null && !this.idtelefono.equals(other.idtelefono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CapaDatos.Telefonosproveedor[ idtelefono=" + idtelefono + " ]";
    }
    
}
