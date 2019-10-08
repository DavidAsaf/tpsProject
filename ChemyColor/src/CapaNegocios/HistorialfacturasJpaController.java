/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocios;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import CapaDatos.Bodegas;
import CapaDatos.Historialfacturas;
import CapaDatos.Proveedores;
import CapaNegocios.exceptions.NonexistentEntityException;
import CapaNegocios.exceptions.PreexistingEntityException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author Amaya
 */
public class HistorialfacturasJpaController implements Serializable {

    public HistorialfacturasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void EntraFactura(String numFactura, int contOcredit, int cantDiasCredito, int estado, Integer codigoBodega,
            int codigoArticulo, Date fecha, String factura, String ccf, String ticket, String ajusteInv,
            int codigoProveedor, int eUnidad, double ePrecio, String detalle) {

        StoredProcedureQuery np = getEntityManager().createNamedStoredProcedureQuery("Historialfacturas.EntraFactura")
                .setParameter("p_NumFactura", numFactura)
                .setParameter("p_cont_O_credit", contOcredit)
                .setParameter("p_CantidadDiasCredit", cantDiasCredito)
                .setParameter("p_Estado", estado)
                .setParameter("p_CodigoBodega", codigoBodega)
                .setParameter("p_CodigoArticulo", codigoArticulo)
                .setParameter("p_Fecha", fecha)
                .setParameter("p_Factura", factura)
                .setParameter("p_CCF", ccf)
                .setParameter("p_Ticket", ticket)
                .setParameter("p_AjusteInventario", ajusteInv)
                .setParameter("p_CodigoProveedor", codigoProveedor)
                .setParameter("p_eUnidad", eUnidad)
                .setParameter("p_ePrecio", ePrecio)
                .setParameter("p_Detalle", detalle);
        np.execute();

    }

    public void create(Historialfacturas historialfacturas) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bodegas codigobodega = historialfacturas.getCodigobodega();
            if (codigobodega != null) {
                codigobodega = em.getReference(codigobodega.getClass(), codigobodega.getCodigobodega());
                historialfacturas.setCodigobodega(codigobodega);
            }
            Proveedores codigoproveedor = historialfacturas.getCodigoproveedor();
            if (codigoproveedor != null) {
                codigoproveedor = em.getReference(codigoproveedor.getClass(), codigoproveedor.getCodigoproveedor());
                historialfacturas.setCodigoproveedor(codigoproveedor);
            }
            em.persist(historialfacturas);
            if (codigobodega != null) {
                codigobodega.getHistorialfacturasList().add(historialfacturas);
                codigobodega = em.merge(codigobodega);
            }
            if (codigoproveedor != null) {
                codigoproveedor.getHistorialfacturasList().add(historialfacturas);
                codigoproveedor = em.merge(codigoproveedor);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHistorialfacturas(historialfacturas.getCodigo()) != null) {
                throw new PreexistingEntityException("Historialfacturas " + historialfacturas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historialfacturas historialfacturas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historialfacturas persistentHistorialfacturas = em.find(Historialfacturas.class, historialfacturas.getCodigo());
            Bodegas codigobodegaOld = persistentHistorialfacturas.getCodigobodega();
            Bodegas codigobodegaNew = historialfacturas.getCodigobodega();
            Proveedores codigoproveedorOld = persistentHistorialfacturas.getCodigoproveedor();
            Proveedores codigoproveedorNew = historialfacturas.getCodigoproveedor();
            if (codigobodegaNew != null) {
                codigobodegaNew = em.getReference(codigobodegaNew.getClass(), codigobodegaNew.getCodigobodega());
                historialfacturas.setCodigobodega(codigobodegaNew);
            }
            if (codigoproveedorNew != null) {
                codigoproveedorNew = em.getReference(codigoproveedorNew.getClass(), codigoproveedorNew.getCodigoproveedor());
                historialfacturas.setCodigoproveedor(codigoproveedorNew);
            }
            historialfacturas = em.merge(historialfacturas);
            if (codigobodegaOld != null && !codigobodegaOld.equals(codigobodegaNew)) {
                codigobodegaOld.getHistorialfacturasList().remove(historialfacturas);
                codigobodegaOld = em.merge(codigobodegaOld);
            }
            if (codigobodegaNew != null && !codigobodegaNew.equals(codigobodegaOld)) {
                codigobodegaNew.getHistorialfacturasList().add(historialfacturas);
                codigobodegaNew = em.merge(codigobodegaNew);
            }
            if (codigoproveedorOld != null && !codigoproveedorOld.equals(codigoproveedorNew)) {
                codigoproveedorOld.getHistorialfacturasList().remove(historialfacturas);
                codigoproveedorOld = em.merge(codigoproveedorOld);
            }
            if (codigoproveedorNew != null && !codigoproveedorNew.equals(codigoproveedorOld)) {
                codigoproveedorNew.getHistorialfacturasList().add(historialfacturas);
                codigoproveedorNew = em.merge(codigoproveedorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = historialfacturas.getCodigo();
                if (findHistorialfacturas(id) == null) {
                    throw new NonexistentEntityException("The historialfacturas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historialfacturas historialfacturas;
            try {
                historialfacturas = em.getReference(Historialfacturas.class, id);
                historialfacturas.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historialfacturas with id " + id + " no longer exists.", enfe);
            }
            Bodegas codigobodega = historialfacturas.getCodigobodega();
            if (codigobodega != null) {
                codigobodega.getHistorialfacturasList().remove(historialfacturas);
                codigobodega = em.merge(codigobodega);
            }
            Proveedores codigoproveedor = historialfacturas.getCodigoproveedor();
            if (codigoproveedor != null) {
                codigoproveedor.getHistorialfacturasList().remove(historialfacturas);
                codigoproveedor = em.merge(codigoproveedor);
            }
            em.remove(historialfacturas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historialfacturas> findHistorialfacturasEntities() {
        return findHistorialfacturasEntities(true, -1, -1);
    }

    public List<Historialfacturas> findHistorialfacturasEntities(int maxResults, int firstResult) {
        return findHistorialfacturasEntities(false, maxResults, firstResult);
    }

    private List<Historialfacturas> findHistorialfacturasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historialfacturas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Historialfacturas findHistorialfacturas(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historialfacturas.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialfacturasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historialfacturas> rt = cq.from(Historialfacturas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
