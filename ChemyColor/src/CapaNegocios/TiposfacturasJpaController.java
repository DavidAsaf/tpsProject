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
import CapaDatos.Tiposfacturas;
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
public class TiposfacturasJpaController implements Serializable {

    public TiposfacturasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tiposfacturas tiposfacturas) throws PreexistingEntityException, Exception {
        if (tiposfacturas.getBodegasList() == null) {
            tiposfacturas.setBodegasList(new ArrayList<Bodegas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Bodegas> attachedBodegasList = new ArrayList<Bodegas>();
            for (Bodegas bodegasListBodegasToAttach : tiposfacturas.getBodegasList()) {
                bodegasListBodegasToAttach = em.getReference(bodegasListBodegasToAttach.getClass(), bodegasListBodegasToAttach.getCodigobodega());
                attachedBodegasList.add(bodegasListBodegasToAttach);
            }
            tiposfacturas.setBodegasList(attachedBodegasList);
            em.persist(tiposfacturas);
            for (Bodegas bodegasListBodegas : tiposfacturas.getBodegasList()) {
                bodegasListBodegas.getTiposfacturasList().add(tiposfacturas);
                bodegasListBodegas = em.merge(bodegasListBodegas);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTiposfacturas(tiposfacturas.getCodigotipofact()) != null) {
                throw new PreexistingEntityException("Tiposfacturas " + tiposfacturas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tiposfacturas tiposfacturas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tiposfacturas persistentTiposfacturas = em.find(Tiposfacturas.class, tiposfacturas.getCodigotipofact());
            List<Bodegas> bodegasListOld = persistentTiposfacturas.getBodegasList();
            List<Bodegas> bodegasListNew = tiposfacturas.getBodegasList();
            List<Bodegas> attachedBodegasListNew = new ArrayList<Bodegas>();
            for (Bodegas bodegasListNewBodegasToAttach : bodegasListNew) {
                bodegasListNewBodegasToAttach = em.getReference(bodegasListNewBodegasToAttach.getClass(), bodegasListNewBodegasToAttach.getCodigobodega());
                attachedBodegasListNew.add(bodegasListNewBodegasToAttach);
            }
            bodegasListNew = attachedBodegasListNew;
            tiposfacturas.setBodegasList(bodegasListNew);
            tiposfacturas = em.merge(tiposfacturas);
            for (Bodegas bodegasListOldBodegas : bodegasListOld) {
                if (!bodegasListNew.contains(bodegasListOldBodegas)) {
                    bodegasListOldBodegas.getTiposfacturasList().remove(tiposfacturas);
                    bodegasListOldBodegas = em.merge(bodegasListOldBodegas);
                }
            }
            for (Bodegas bodegasListNewBodegas : bodegasListNew) {
                if (!bodegasListOld.contains(bodegasListNewBodegas)) {
                    bodegasListNewBodegas.getTiposfacturasList().add(tiposfacturas);
                    bodegasListNewBodegas = em.merge(bodegasListNewBodegas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = tiposfacturas.getCodigotipofact();
                if (findTiposfacturas(id) == null) {
                    throw new NonexistentEntityException("The tiposfacturas with id " + id + " no longer exists.");
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
            Tiposfacturas tiposfacturas;
            try {
                tiposfacturas = em.getReference(Tiposfacturas.class, id);
                tiposfacturas.getCodigotipofact();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiposfacturas with id " + id + " no longer exists.", enfe);
            }
            List<Bodegas> bodegasList = tiposfacturas.getBodegasList();
            for (Bodegas bodegasListBodegas : bodegasList) {
                bodegasListBodegas.getTiposfacturasList().remove(tiposfacturas);
                bodegasListBodegas = em.merge(bodegasListBodegas);
            }
            em.remove(tiposfacturas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tiposfacturas> findTiposfacturasEntities() {
        return findTiposfacturasEntities(true, -1, -1);
    }

    public List<Tiposfacturas> findTiposfacturasEntities(int maxResults, int firstResult) {
        return findTiposfacturasEntities(false, maxResults, firstResult);
    }

    private List<Tiposfacturas> findTiposfacturasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tiposfacturas.class));
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

    public Tiposfacturas findTiposfacturas(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tiposfacturas.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiposfacturasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tiposfacturas> rt = cq.from(Tiposfacturas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
