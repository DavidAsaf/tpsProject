/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Amaya
 */
@Entity
@Table(name = "TIPOSFACTURAS")
@NamedQueries({
    @NamedQuery(name = "Tiposfacturas.findAll", query = "SELECT t FROM Tiposfacturas t")})
public class Tiposfacturas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGOTIPOFACT")
    private BigDecimal codigotipofact;
    @Column(name = "TIPOFACTURA")
    private String tipofactura;
    @ManyToMany(mappedBy = "tiposfacturasList")
    private List<Bodegas> bodegasList;

    public Tiposfacturas() {
    }

    public Tiposfacturas(BigDecimal codigotipofact) {
        this.codigotipofact = codigotipofact;
    }

    public BigDecimal getCodigotipofact() {
        return codigotipofact;
    }

    public void setCodigotipofact(BigDecimal codigotipofact) {
        this.codigotipofact = codigotipofact;
    }

    public String getTipofactura() {
        return tipofactura;
    }

    public void setTipofactura(String tipofactura) {
        this.tipofactura = tipofactura;
    }

    public List<Bodegas> getBodegasList() {
        return bodegasList;
    }

    public void setBodegasList(List<Bodegas> bodegasList) {
        this.bodegasList = bodegasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigotipofact != null ? codigotipofact.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiposfacturas)) {
            return false;
        }
        Tiposfacturas other = (Tiposfacturas) object;
        if ((this.codigotipofact == null && other.codigotipofact != null) || (this.codigotipofact != null && !this.codigotipofact.equals(other.codigotipofact))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CapaDatos.Tiposfacturas[ codigotipofact=" + codigotipofact + " ]";
    }
    
}
