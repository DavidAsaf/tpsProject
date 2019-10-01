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
import CapaDatos.Formaspago;
import CapaNegocios.exceptions.NonexistentEntityException;
import CapaNegocios.exceptions.PreexistingEntityException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Amaya
 */
public class FormaspagoJpaController implements Serializable {

    public FormaspagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Formaspago formaspago) throws PreexistingEntityException, Exception {
        if (formaspago.getBodegasList() == null) {
            formaspago.setBodegasList(new ArrayList<Bodegas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Bodegas> attachedBodegasList = new ArrayList<Bodegas>();
            for (Bodegas bodegasListBodegasToAttach : formaspago.getBodegasList()) {
                bodegasListBodegasToAttach = em.getReference(bodegasListBodegasToAttach.getClass(), bodegasListBodegasToAttach.getCodigobodega());
                attachedBodegasList.add(bodegasListBodegasToAttach);
            }
            formaspago.setBodegasList(attachedBodegasList);
            em.persist(formaspago);
            for (Bodegas bodegasListBodegas : formaspago.getBodegasList()) {
                bodegasListBodegas.getFormaspagoList().add(formaspago);
                bodegasListBodegas = em.merge(bodegasListBodegas);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFormaspago(formaspago.getCodigoformapago()) != null) {
                throw new PreexistingEntityException("Formaspago " + formaspago + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Formaspago formaspago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formaspago persistentFormaspago = em.find(Formaspago.class, formaspago.getCodigoformapago());
            List<Bodegas> bodegasListOld = persistentFormaspago.getBodegasList();
            List<Bodegas> bodegasListNew = formaspago.getBodegasList();
            List<Bodegas> attachedBodegasListNew = new ArrayList<Bodegas>();
            for (Bodegas bodegasListNewBodegasToAttach : bodegasListNew) {
                bodegasListNewBodegasToAttach = em.getReference(bodegasListNewBodegasToAttach.getClass(), bodegasListNewBodegasToAttach.getCodigobodega());
                attachedBodegasListNew.add(bodegasListNewBodegasToAttach);
            }
            bodegasListNew = attachedBodegasListNew;
            formaspago.setBodegasList(bodegasListNew);
            formaspago = em.merge(formaspago);
            for (Bodegas bodegasListOldBodegas : bodegasListOld) {
                if (!bodegasListNew.contains(bodegasListOldBodegas)) {
                    bodegasListOldBodegas.getFormaspagoList().remove(formaspago);
                    bodegasListOldBodegas = em.merge(bodegasListOldBodegas);
                }
            }
            for (Bodegas bodegasListNewBodegas : bodegasListNew) {
                if (!bodegasListOld.contains(bodegasListNewBodegas)) {
                    bodegasListNewBodegas.getFormaspagoList().add(formaspago);
                    bodegasListNewBodegas = em.merge(bodegasListNewBodegas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = formaspago.getCodigoformapago();
                if (findFormaspago(id) == null) {
                    throw new NonexistentEntityException("The formaspago with id " + id + " no longer exists.");
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
            Formaspago formaspago;
            try {
                formaspago = em.getReference(Formaspago.class, id);
                formaspago.getCodigoformapago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formaspago with id " + id + " no longer exists.", enfe);
            }
            List<Bodegas> bodegasList = formaspago.getBodegasList();
            for (Bodegas bodegasListBodegas : bodegasList) {
                bodegasListBodegas.getFormaspagoList().remove(formaspago);
                bodegasListBodegas = em.merge(bodegasListBodegas);
            }
            em.remove(formaspago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Formaspago> findFormaspagoEntities() {
        return findFormaspagoEntities(true, -1, -1);
    }

    public List<Formaspago> findFormaspagoEntities(int maxResults, int firstResult) {
        return findFormaspagoEntities(false, maxResults, firstResult);
    }

    private List<Formaspago> findFormaspagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Formaspago.class));
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

    public Formaspago findFormaspago(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Formaspago.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormaspagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Formaspago> rt = cq.from(Formaspago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
