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
@Table(name = "FORMASPAGO")
@NamedQueries({
    @NamedQuery(name = "Formaspago.findAll", query = "SELECT f FROM Formaspago f")})
public class Formaspago implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGOFORMAPAGO")
    private BigDecimal codigoformapago;
    @Column(name = "FORMAPAGO")
    private String formapago;
    @ManyToMany(mappedBy = "formaspagoList")
    private List<Bodegas> bodegasList;

    public Formaspago() {
    }

    public Formaspago(BigDecimal codigoformapago) {
        this.codigoformapago = codigoformapago;
    }

    public BigDecimal getCodigoformapago() {
        return codigoformapago;
    }

    public void setCodigoformapago(BigDecimal codigoformapago) {
        this.codigoformapago = codigoformapago;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
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
        hash += (codigoformapago != null ? codigoformapago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formaspago)) {
            return false;
        }
        Formaspago other = (Formaspago) object;
        if ((this.codigoformapago == null && other.codigoformapago != null) || (this.codigoformapago != null && !this.codigoformapago.equals(other.codigoformapago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CapaDatos.Formaspago[ codigoformapago=" + codigoformapago + " ]";
    }
    
}
