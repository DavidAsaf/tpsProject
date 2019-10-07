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
import CapaDatos.Proveedores;
import CapaDatos.Telefonosproveedor;
import CapaNegocios.exceptions.NonexistentEntityException;
import CapaNegocios.exceptions.PreexistingEntityException;
import CapaPresentacion.entityMain;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author Amaya
 */
public class TelefonosproveedorJpaController implements Serializable {

    public TelefonosproveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

//findIdTelProveedor(idProv IN INT, tipoTel IN VARCHAR2, idTel OUT INT) 
    public int findIdTel(int id, String tipo) {

        EntityManagerFactory factory = entityMain.getInstance();
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("findIdTelProveedor");

        storedProcedure.registerStoredProcedureParameter("idProv", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("tipoTel", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("idTel", Integer.class, ParameterMode.OUT);

        storedProcedure.setParameter("idProv", id);
        storedProcedure.setParameter("tipoTel", tipo);
        storedProcedure.execute();

        int idT = Integer.parseInt(storedProcedure.getOutputParameterValue("idTel").toString());

        em.getTransaction().commit();
        em.close();

        return idT;
    }
    
    public void editTelProveedor(int idP, String telT, String telC) {

        EntityManagerFactory factory = entityMain.getInstance();
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("telEditarProveedor");

        storedProcedure.registerStoredProcedureParameter("idProv", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("telefonoT", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("telefonoC", String.class, ParameterMode.IN);

        storedProcedure.setParameter("idProv", idP);
        storedProcedure.setParameter("telefonoT", telT);
        storedProcedure.setParameter("telefonoC", telC);
        storedProcedure.execute();

        em.getTransaction().commit();
        em.close();

    }

    public String findTelProveedor(int id, String tipo) {

        EntityManagerFactory factory = entityMain.getInstance();
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("telProveedor");

        storedProcedure.registerStoredProcedureParameter("idProv", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("tipoTel", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("telefonoP", String.class, ParameterMode.OUT);

        storedProcedure.setParameter("idProv", id);
        storedProcedure.setParameter("tipoTel", tipo);
        storedProcedure.execute();

        String telefono = storedProcedure.getOutputParameterValue("telefonoP").toString();

        em.getTransaction().commit();
        em.close();

        return telefono;
    }

    public void create(Telefonosproveedor telefonosproveedor) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedores codigoproveedor = telefonosproveedor.getCodigoproveedor();
            if (codigoproveedor != null) {
                codigoproveedor = em.getReference(codigoproveedor.getClass(), codigoproveedor.getCodigoproveedor());
                telefonosproveedor.setCodigoproveedor(codigoproveedor);
            }
            em.persist(telefonosproveedor);
            if (codigoproveedor != null) {
                codigoproveedor.getTelefonosproveedorList().add(telefonosproveedor);
                codigoproveedor = em.merge(codigoproveedor);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTelefonosproveedor(telefonosproveedor.getIdtelefono()) != null) {
                throw new PreexistingEntityException("Telefonosproveedor " + telefonosproveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Telefonosproveedor telefonosproveedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Telefonosproveedor persistentTelefonosproveedor = em.find(Telefonosproveedor.class, telefonosproveedor.getIdtelefono());
            Proveedores codigoproveedorOld = persistentTelefonosproveedor.getCodigoproveedor();
            Proveedores codigoproveedorNew = telefonosproveedor.getCodigoproveedor();
            if (codigoproveedorNew != null) {
                codigoproveedorNew = em.getReference(codigoproveedorNew.getClass(), codigoproveedorNew.getCodigoproveedor());
                telefonosproveedor.setCodigoproveedor(codigoproveedorNew);
            }
            telefonosproveedor = em.merge(telefonosproveedor);
            if (codigoproveedorOld != null && !codigoproveedorOld.equals(codigoproveedorNew)) {
                codigoproveedorOld.getTelefonosproveedorList().remove(telefonosproveedor);
                codigoproveedorOld = em.merge(codigoproveedorOld);
            }
            if (codigoproveedorNew != null && !codigoproveedorNew.equals(codigoproveedorOld)) {
                codigoproveedorNew.getTelefonosproveedorList().add(telefonosproveedor);
                codigoproveedorNew = em.merge(codigoproveedorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = telefonosproveedor.getIdtelefono();
                if (findTelefonosproveedor(id) == null) {
                    throw new NonexistentEntityException("The telefonosproveedor with id " + id + " no longer exists.");
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
            Telefonosproveedor telefonosproveedor;
            try {
                telefonosproveedor = em.getReference(Telefonosproveedor.class, id);
                telefonosproveedor.getIdtelefono();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The telefonosproveedor with id " + id + " no longer exists.", enfe);
            }
            Proveedores codigoproveedor = telefonosproveedor.getCodigoproveedor();
            if (codigoproveedor != null) {
                codigoproveedor.getTelefonosproveedorList().remove(telefonosproveedor);
                codigoproveedor = em.merge(codigoproveedor);
            }
            em.remove(telefonosproveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Telefonosproveedor> findTelefonosproveedorEntities() {
        return findTelefonosproveedorEntities(true, -1, -1);
    }

    public List<Telefonosproveedor> findTelefonosproveedorEntities(int maxResults, int firstResult) {
        return findTelefonosproveedorEntities(false, maxResults, firstResult);
    }

    private List<Telefonosproveedor> findTelefonosproveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Telefonosproveedor.class));
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

    public Telefonosproveedor findTelefonosproveedor(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Telefonosproveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getTelefonosproveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Telefonosproveedor> rt = cq.from(Telefonosproveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
