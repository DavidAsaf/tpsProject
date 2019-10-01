/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocios;

import CapaDatos.Tiposmovimientos;
import CapaNegocios.exceptions.NonexistentEntityException;
import CapaNegocios.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Amaya
 */
public class TiposmovimientosJpaController implements Serializable {

    public TiposmovimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tiposmovimientos tiposmovimientos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tiposmovimientos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTiposmovimientos(tiposmovimientos.getCodtipomov()) != null) {
                throw new PreexistingEntityException("Tiposmovimientos " + tiposmovimientos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tiposmovimientos tiposmovimientos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tiposmovimientos = em.merge(tiposmovimientos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = tiposmovimientos.getCodtipomov();
                if (findTiposmovimientos(id) == null) {
                    throw new NonexistentEntityException("The tiposmovimientos with id " + id + " no longer exists.");
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
            Tiposmovimientos tiposmovimientos;
            try {
                tiposmovimientos = em.getReference(Tiposmovimientos.class, id);
                tiposmovimientos.getCodtipomov();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiposmovimientos with id " + id + " no longer exists.", enfe);
            }
            em.remove(tiposmovimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tiposmovimientos> findTiposmovimientosEntities() {
        return findTiposmovimientosEntities(true, -1, -1);
    }

    public List<Tiposmovimientos> findTiposmovimientosEntities(int maxResults, int firstResult) {
        return findTiposmovimientosEntities(false, maxResults, firstResult);
    }

    private List<Tiposmovimientos> findTiposmovimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tiposmovimientos.class));
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

    public Tiposmovimientos findTiposmovimientos(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tiposmovimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiposmovimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tiposmovimientos> rt = cq.from(Tiposmovimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
