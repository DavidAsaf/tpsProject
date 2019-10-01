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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Amaya
 */
@Entity
@Table(name = "TIPOSMOVIMIENTOS")
@NamedQueries({
    @NamedQuery(name = "Tiposmovimientos.findAll", query = "SELECT t FROM Tiposmovimientos t")})
public class Tiposmovimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "CODTIPOMOV")
    private BigDecimal codtipomov;
    @Column(name = "TIPOMOV")
    private String tipomov;

    public Tiposmovimientos() {
    }

    public Tiposmovimientos(BigDecimal codtipomov) {
        this.codtipomov = codtipomov;
    }

    public BigDecimal getCodtipomov() {
        return codtipomov;
    }

    public void setCodtipomov(BigDecimal codtipomov) {
        this.codtipomov = codtipomov;
    }

    public String getTipomov() {
        return tipomov;
    }

    public void setTipomov(String tipomov) {
        this.tipomov = tipomov;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codtipomov != null ? codtipomov.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiposmovimientos)) {
            return false;
        }
        Tiposmovimientos other = (Tiposmovimientos) object;
        if ((this.codtipomov == null && other.codtipomov != null) || (this.codtipomov != null && !this.codtipomov.equals(other.codtipomov))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CapaDatos.Tiposmovimientos[ codtipomov=" + codtipomov + " ]";
    }
    
}
