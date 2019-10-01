/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocios;

import CapaDatos.Emailrecuperador;
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
public class EmailrecuperadorJpaController implements Serializable {

    public EmailrecuperadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Emailrecuperador emailrecuperador) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(emailrecuperador);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmailrecuperador(emailrecuperador.getCodigo()) != null) {
                throw new PreexistingEntityException("Emailrecuperador " + emailrecuperador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Emailrecuperador emailrecuperador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            emailrecuperador = em.merge(emailrecuperador);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = emailrecuperador.getCodigo();
                if (findEmailrecuperador(id) == null) {
                    throw new NonexistentEntityException("The emailrecuperador with id " + id + " no longer exists.");
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
            Emailrecuperador emailrecuperador;
            try {
                emailrecuperador = em.getReference(Emailrecuperador.class, id);
                emailrecuperador.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The emailrecuperador with id " + id + " no longer exists.", enfe);
            }
            em.remove(emailrecuperador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Emailrecuperador> findEmailrecuperadorEntities() {
        return findEmailrecuperadorEntities(true, -1, -1);
    }

    public List<Emailrecuperador> findEmailrecuperadorEntities(int maxResults, int firstResult) {
        return findEmailrecuperadorEntities(false, maxResults, firstResult);
    }

    private List<Emailrecuperador> findEmailrecuperadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Emailrecuperador.class));
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

    public Emailrecuperador findEmailrecuperador(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Emailrecuperador.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmailrecuperadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Emailrecuperador> rt = cq.from(Emailrecuperador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
