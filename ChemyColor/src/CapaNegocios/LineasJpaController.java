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
import CapaDatos.Grupos;
import CapaDatos.Articulos;
import CapaDatos.Lineas;
import CapaNegocios.exceptions.NonexistentEntityException;
import CapaNegocios.exceptions.PreexistingEntityException;
import CapaPresentacion.entityMain;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author Amaya
 */
public class LineasJpaController implements Serializable {

    public LineasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Lineas findIdLineas(String linea) {
        
        EntityManagerFactory factory = entityMain.getInstance();
        EntityManager em = factory.createEntityManager();
        
        em.getTransaction().begin();
        StoredProcedureQuery storedProcedure=em.createStoredProcedureQuery("idLineas");
        
        storedProcedure.registerStoredProcedureParameter("lineas", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("idlineas", Integer.class, ParameterMode.OUT);
        
        storedProcedure.setParameter("lineas", linea);        
        storedProcedure.execute();
        
        Lineas tipo = new Lineas();
        Integer codigo = (Integer) storedProcedure.getOutputParameterValue("idlineas");
        tipo.setCodigolinea(BigDecimal.valueOf(codigo));
        
        em.getTransaction().commit();
        em.close();
        
        return tipo;
    }
    
    public void create(Lineas lineas) throws PreexistingEntityException, Exception {
        if (lineas.getArticulosList() == null) {
            lineas.setArticulosList(new ArrayList<Articulos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupos codigogrupo = lineas.getCodigogrupo();
            if (codigogrupo != null) {
                codigogrupo = em.getReference(codigogrupo.getClass(), codigogrupo.getCodigogrupo());
                lineas.setCodigogrupo(codigogrupo);
            }
            List<Articulos> attachedArticulosList = new ArrayList<Articulos>();
            for (Articulos articulosListArticulosToAttach : lineas.getArticulosList()) {
                articulosListArticulosToAttach = em.getReference(articulosListArticulosToAttach.getClass(), articulosListArticulosToAttach.getCodigoarticulo());
                attachedArticulosList.add(articulosListArticulosToAttach);
            }
            lineas.setArticulosList(attachedArticulosList);
            em.persist(lineas);
            if (codigogrupo != null) {
                codigogrupo.getLineasList().add(lineas);
                codigogrupo = em.merge(codigogrupo);
            }
            for (Articulos articulosListArticulos : lineas.getArticulosList()) {
                Lineas oldCodigolineaOfArticulosListArticulos = articulosListArticulos.getCodigolinea();
                articulosListArticulos.setCodigolinea(lineas);
                articulosListArticulos = em.merge(articulosListArticulos);
                if (oldCodigolineaOfArticulosListArticulos != null) {
                    oldCodigolineaOfArticulosListArticulos.getArticulosList().remove(articulosListArticulos);
                    oldCodigolineaOfArticulosListArticulos = em.merge(oldCodigolineaOfArticulosListArticulos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLineas(lineas.getCodigolinea()) != null) {
                throw new PreexistingEntityException("Lineas " + lineas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lineas lineas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lineas persistentLineas = em.find(Lineas.class, lineas.getCodigolinea());
            Grupos codigogrupoOld = persistentLineas.getCodigogrupo();
            Grupos codigogrupoNew = lineas.getCodigogrupo();
            List<Articulos> articulosListOld = persistentLineas.getArticulosList();
            List<Articulos> articulosListNew = lineas.getArticulosList();
            if (codigogrupoNew != null) {
                codigogrupoNew = em.getReference(codigogrupoNew.getClass(), codigogrupoNew.getCodigogrupo());
                lineas.setCodigogrupo(codigogrupoNew);
            }
            List<Articulos> attachedArticulosListNew = new ArrayList<Articulos>();
            for (Articulos articulosListNewArticulosToAttach : articulosListNew) {
                articulosListNewArticulosToAttach = em.getReference(articulosListNewArticulosToAttach.getClass(), articulosListNewArticulosToAttach.getCodigoarticulo());
                attachedArticulosListNew.add(articulosListNewArticulosToAttach);
            }
            articulosListNew = attachedArticulosListNew;
            lineas.setArticulosList(articulosListNew);
            lineas = em.merge(lineas);
            if (codigogrupoOld != null && !codigogrupoOld.equals(codigogrupoNew)) {
                codigogrupoOld.getLineasList().remove(lineas);
                codigogrupoOld = em.merge(codigogrupoOld);
            }
            if (codigogrupoNew != null && !codigogrupoNew.equals(codigogrupoOld)) {
                codigogrupoNew.getLineasList().add(lineas);
                codigogrupoNew = em.merge(codigogrupoNew);
            }
            for (Articulos articulosListOldArticulos : articulosListOld) {
                if (!articulosListNew.contains(articulosListOldArticulos)) {
                    articulosListOldArticulos.setCodigolinea(null);
                    articulosListOldArticulos = em.merge(articulosListOldArticulos);
                }
            }
            for (Articulos articulosListNewArticulos : articulosListNew) {
                if (!articulosListOld.contains(articulosListNewArticulos)) {
                    Lineas oldCodigolineaOfArticulosListNewArticulos = articulosListNewArticulos.getCodigolinea();
                    articulosListNewArticulos.setCodigolinea(lineas);
                    articulosListNewArticulos = em.merge(articulosListNewArticulos);
                    if (oldCodigolineaOfArticulosListNewArticulos != null && !oldCodigolineaOfArticulosListNewArticulos.equals(lineas)) {
                        oldCodigolineaOfArticulosListNewArticulos.getArticulosList().remove(articulosListNewArticulos);
                        oldCodigolineaOfArticulosListNewArticulos = em.merge(oldCodigolineaOfArticulosListNewArticulos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = lineas.getCodigolinea();
                if (findLineas(id) == null) {
                    throw new NonexistentEntityException("The lineas with id " + id + " no longer exists.");
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
            Lineas lineas;
            try {
                lineas = em.getReference(Lineas.class, id);
                lineas.getCodigolinea();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lineas with id " + id + " no longer exists.", enfe);
            }
            Grupos codigogrupo = lineas.getCodigogrupo();
            if (codigogrupo != null) {
                codigogrupo.getLineasList().remove(lineas);
                codigogrupo = em.merge(codigogrupo);
            }
            List<Articulos> articulosList = lineas.getArticulosList();
            for (Articulos articulosListArticulos : articulosList) {
                articulosListArticulos.setCodigolinea(null);
                articulosListArticulos = em.merge(articulosListArticulos);
            }
            em.remove(lineas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lineas> findLineasEntities() {
        return findLineasEntities(true, -1, -1);
    }

    public List<Lineas> findLineasEntities(int maxResults, int firstResult) {
        return findLineasEntities(false, maxResults, firstResult);
    }

    private List<Lineas> findLineasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lineas.class));
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

    public Lineas findLineas(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lineas.class, id);
        } finally {
            em.close();
        }
    }

    public int getLineasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lineas> rt = cq.from(Lineas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public BigDecimal findidNewSubGrupo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
