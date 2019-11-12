/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocios;

import CapaDatos.Articulos;
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
import CapaPresentacion.entityMain;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

    //SELECT DISTINCT (EXTRACT(YEAR FROM h.fecha)) AS Fecha FROM Historialfacturas h WHERE CodigoBodega = 1 GROUP BY Fecha;
//    public void fillComboAnnio(JComboBox cb, int codB) {
//        DefaultComboBoxModel dc = new DefaultComboBoxModel();
//        CapaDatos.Historialfacturas h = new CapaDatos.Historialfacturas();
//        
//       String fecha = "fecha";
//       String nCodigo = "codigobodega";
//        
//        List<Historialfacturas> ListaHistorial = getEntityManager() 
//                .createQuery("SELECT DISTINCT (EXTRACT(YEAR FROM h." + fecha + ")) AS Annio FROM Historialfacturas h "
//                        + "WHERE h." + nCodigo + " = " + codB + " GROUP BY "+ fecha + "")
//                .getResultList();
//        cb.removeAllItems();
//
//        for (Historialfacturas li : ListaHistorial) {
//            cb.addItem(li.getAnnio());
//        }
//    }
    
    public void fillJTable(JTable jtable, int codigo) {
        
        String[] titulos = {"Correlativo", "Fecha", "Factura", "CCF", "Ajuste Inventario",
                        "Proveedor", "Entradas Unidad", "Entradas Precio", "Entradas Total", "Salidas Unidad",
                        "Salidas Precio", "Salidas Total", "Saldos Unidad", "Saldos Cto. Prom.", "Saldo Total"};
        String fecha = "fecha";
        String cb = "codigobodega";
        String tabla = "Historialfacturas";
        
        CapaDatos.Bodegas b = new CapaDatos.Bodegas();
        b.setCodigobodega(BigDecimal.valueOf(codigo));
        
        List<Historialfacturas> Lista = getEntityManager().createNamedQuery("Historialfacturas.findByCodigos")
                .setParameter("codigobodega", b).getResultList();
        
//        List<Historialfacturas> listado = getEntityManager() 
//                .createQuery("SELECT h FROM " + tabla + " h WHERE h." + cb + " = " + codigo + "")
//                .getResultList();
        
        DefaultTableModel Modelo = new DefaultTableModel(null, titulos);
        int correlativo = 1;
        
        for (Historialfacturas h : Lista) {
            Modelo.addRow(new Object[]{correlativo, h.getFecha(), h.getFactura(), h.getCcf(), h.getAjusteinventario(), 
                h.getCodigoproveedor().getNombres(), h.getEunidad(), h.getEprecio(), h.getEtotal(), h.getOunidad(), 
                h.getOprecio(), h.getOtotal(), h.getSunidad(), h.getScostopromedio(), h.getStotal()});
            
            correlativo++;
        }
        jtable.setModel(Modelo);
        //jtable.setDefaultEditor(Object.class, null);
    }
    
    
    public int findNextId() { 

        EntityManagerFactory factory = entityMain.getInstance();
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("nextIdHistorialF");

        storedProcedure.registerStoredProcedureParameter("id", Integer.class, ParameterMode.OUT);
        storedProcedure.execute();

        int codigo = Integer.parseInt(storedProcedure.getOutputParameterValue("id").toString());

        em.getTransaction().commit();
        em.close();

        return codigo;
    } 
    
    public void EntraFactura(int codigo, String numFactura, int contOcredit, int cantDiasCredito, int estado, Integer codigoBodega,
            int codigoArticulo, Date fecha, String factura, String ccf, String ticket, String ajusteInv,
            int codigoProveedor, int eUnidad, double ePrecio, String detalle) {

        StoredProcedureQuery np = getEntityManager().createNamedStoredProcedureQuery("Historialfacturas.EntraFactura")
                .setParameter("p_Codigo", codigo)
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

    public void AddDetalleFact(int codigo, String nit, String nrc, String giro, String dui, String direccion, 
            String ivaCredito) {

        StoredProcedureQuery np = getEntityManager().createNamedStoredProcedureQuery("Historialfacturas.insertarDetalleFact")
                .setParameter("p_codigo", codigo)
                .setParameter("p_nit", nit)
                .setParameter("p_nrc", nrc)
                .setParameter("p_giro", giro)
                .setParameter("p_dui", dui)
                .setParameter("p_direccion", direccion)
                .setParameter("p_ivacreditofiscal", ivaCredito);
        np.execute();

    }
    
    public void SalidaFactura(int codigo, String numFactura, int contOcredit, int cantDiasCredito, int codigoBodega,
            int codigoArticulo, Date fecha, int oUnidad, String cliente) {

        StoredProcedureQuery np = getEntityManager().createNamedStoredProcedureQuery("Historialfacturas.SalidaFactura")
                .setParameter("p_Codigo", codigo)
                .setParameter("p_NumFactura", numFactura)
                .setParameter("p_Fecha", fecha)
                .setParameter("p_cont_O_credit", contOcredit)
                .setParameter("p_CantidadDiasCredit", cantDiasCredito)
                .setParameter("p_oCliente", cliente)
                .setParameter("p_CodigoArticulo", codigoArticulo)
                .setParameter("p_oUnidad", oUnidad)
                .setParameter("p_CodigoBodega", codigoBodega);
                
        np.execute();

    }
    
    /*
p_Codigo NUMBER, p_NumFactura VARCHAR2, p_Fecha DATE, p_cont_O_credit NUMBER, p_CantidadDiasCredit NUMBER,
p_oCliente VARCHAR2,p_CodigoArticulo NUMBER, p_oUnidad NUMBER,p_CodigoBodega NUMBER
*/
    
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
