/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocios;

import CapaDatos.Proveedores;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import CapaDatos.Tipocontribuyente;
import CapaDatos.Tipoproveedores;
import CapaDatos.Telefonosproveedor;
import CapaNegocios.exceptions.IllegalOrphanException;
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
public class ProveedoresJpaController1 implements Serializable {

    public ProveedoresJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
   
    public void create(Proveedores proveedores) throws PreexistingEntityException, Exception {
        if (proveedores.getTelefonosproveedorList() == null) {
            proveedores.setTelefonosproveedorList(new ArrayList<Telefonosproveedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipocontribuyente codigotipocontribuyente = proveedores.getCodigotipocontribuyente();
            if (codigotipocontribuyente != null) {
                codigotipocontribuyente = em.getReference(codigotipocontribuyente.getClass(), codigotipocontribuyente.getCodigotipocontribuyente());
                proveedores.setCodigotipocontribuyente(codigotipocontribuyente);
            }
            Tipoproveedores codtipoprov = proveedores.getCodtipoprov();
            if (codtipoprov != null) {
                codtipoprov = em.getReference(codtipoprov.getClass(), codtipoprov.getCodtipoprov());
                proveedores.setCodtipoprov(codtipoprov);
            }
            List<Telefonosproveedor> attachedTelefonosproveedorList = new ArrayList<Telefonosproveedor>();
            for (Telefonosproveedor telefonosproveedorListTelefonosproveedorToAttach : proveedores.getTelefonosproveedorList()) {
                telefonosproveedorListTelefonosproveedorToAttach = em.getReference(telefonosproveedorListTelefonosproveedorToAttach.getClass(), telefonosproveedorListTelefonosproveedorToAttach.getIdtelefono());
                attachedTelefonosproveedorList.add(telefonosproveedorListTelefonosproveedorToAttach);
            }
            proveedores.setTelefonosproveedorList(attachedTelefonosproveedorList);
            em.persist(proveedores);
            if (codigotipocontribuyente != null) {
                codigotipocontribuyente.getProveedoresList().add(proveedores);
                codigotipocontribuyente = em.merge(codigotipocontribuyente);
            }
            if (codtipoprov != null) {
                codtipoprov.getProveedoresList().add(proveedores);
                codtipoprov = em.merge(codtipoprov);
            }
            for (Telefonosproveedor telefonosproveedorListTelefonosproveedor : proveedores.getTelefonosproveedorList()) {
                Proveedores oldCodigoproveedorOfTelefonosproveedorListTelefonosproveedor = telefonosproveedorListTelefonosproveedor.getCodigoproveedor();
                telefonosproveedorListTelefonosproveedor.setCodigoproveedor(proveedores);
                telefonosproveedorListTelefonosproveedor = em.merge(telefonosproveedorListTelefonosproveedor);
                if (oldCodigoproveedorOfTelefonosproveedorListTelefonosproveedor != null) {
                    oldCodigoproveedorOfTelefonosproveedorListTelefonosproveedor.getTelefonosproveedorList().remove(telefonosproveedorListTelefonosproveedor);
                    oldCodigoproveedorOfTelefonosproveedorListTelefonosproveedor = em.merge(oldCodigoproveedorOfTelefonosproveedorListTelefonosproveedor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProveedores(proveedores.getCodigoproveedor()) != null) {
                throw new PreexistingEntityException("Proveedores " + proveedores + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedores proveedores) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedores persistentProveedores = em.find(Proveedores.class, proveedores.getCodigoproveedor());
            Tipocontribuyente codigotipocontribuyenteOld = persistentProveedores.getCodigotipocontribuyente();
            Tipocontribuyente codigotipocontribuyenteNew = proveedores.getCodigotipocontribuyente();
            Tipoproveedores codtipoprovOld = persistentProveedores.getCodtipoprov();
            Tipoproveedores codtipoprovNew = proveedores.getCodtipoprov();
            List<Telefonosproveedor> telefonosproveedorListOld = persistentProveedores.getTelefonosproveedorList();
            List<Telefonosproveedor> telefonosproveedorListNew = proveedores.getTelefonosproveedorList();
            List<String> illegalOrphanMessages = null;
            for (Telefonosproveedor telefonosproveedorListOldTelefonosproveedor : telefonosproveedorListOld) {
                if (!telefonosproveedorListNew.contains(telefonosproveedorListOldTelefonosproveedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Telefonosproveedor " + telefonosproveedorListOldTelefonosproveedor + " since its codigoproveedor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codigotipocontribuyenteNew != null) {
                codigotipocontribuyenteNew = em.getReference(codigotipocontribuyenteNew.getClass(), codigotipocontribuyenteNew.getCodigotipocontribuyente());
                proveedores.setCodigotipocontribuyente(codigotipocontribuyenteNew);
            }
            if (codtipoprovNew != null) {
                codtipoprovNew = em.getReference(codtipoprovNew.getClass(), codtipoprovNew.getCodtipoprov());
                proveedores.setCodtipoprov(codtipoprovNew);
            }
            List<Telefonosproveedor> attachedTelefonosproveedorListNew = new ArrayList<Telefonosproveedor>();
            for (Telefonosproveedor telefonosproveedorListNewTelefonosproveedorToAttach : telefonosproveedorListNew) {
                telefonosproveedorListNewTelefonosproveedorToAttach = em.getReference(telefonosproveedorListNewTelefonosproveedorToAttach.getClass(), telefonosproveedorListNewTelefonosproveedorToAttach.getIdtelefono());
                attachedTelefonosproveedorListNew.add(telefonosproveedorListNewTelefonosproveedorToAttach);
            }
            telefonosproveedorListNew = attachedTelefonosproveedorListNew;
            proveedores.setTelefonosproveedorList(telefonosproveedorListNew);
            proveedores = em.merge(proveedores);
            if (codigotipocontribuyenteOld != null && !codigotipocontribuyenteOld.equals(codigotipocontribuyenteNew)) {
                codigotipocontribuyenteOld.getProveedoresList().remove(proveedores);
                codigotipocontribuyenteOld = em.merge(codigotipocontribuyenteOld);
            }
            if (codigotipocontribuyenteNew != null && !codigotipocontribuyenteNew.equals(codigotipocontribuyenteOld)) {
                codigotipocontribuyenteNew.getProveedoresList().add(proveedores);
                codigotipocontribuyenteNew = em.merge(codigotipocontribuyenteNew);
            }
            if (codtipoprovOld != null && !codtipoprovOld.equals(codtipoprovNew)) {
                codtipoprovOld.getProveedoresList().remove(proveedores);
                codtipoprovOld = em.merge(codtipoprovOld);
            }
            if (codtipoprovNew != null && !codtipoprovNew.equals(codtipoprovOld)) {
                codtipoprovNew.getProveedoresList().add(proveedores);
                codtipoprovNew = em.merge(codtipoprovNew);
            }
            for (Telefonosproveedor telefonosproveedorListNewTelefonosproveedor : telefonosproveedorListNew) {
                if (!telefonosproveedorListOld.contains(telefonosproveedorListNewTelefonosproveedor)) {
                    Proveedores oldCodigoproveedorOfTelefonosproveedorListNewTelefonosproveedor = telefonosproveedorListNewTelefonosproveedor.getCodigoproveedor();
                    telefonosproveedorListNewTelefonosproveedor.setCodigoproveedor(proveedores);
                    telefonosproveedorListNewTelefonosproveedor = em.merge(telefonosproveedorListNewTelefonosproveedor);
                    if (oldCodigoproveedorOfTelefonosproveedorListNewTelefonosproveedor != null && !oldCodigoproveedorOfTelefonosproveedorListNewTelefonosproveedor.equals(proveedores)) {
                        oldCodigoproveedorOfTelefonosproveedorListNewTelefonosproveedor.getTelefonosproveedorList().remove(telefonosproveedorListNewTelefonosproveedor);
                        oldCodigoproveedorOfTelefonosproveedorListNewTelefonosproveedor = em.merge(oldCodigoproveedorOfTelefonosproveedorListNewTelefonosproveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = proveedores.getCodigoproveedor();
                if (findProveedores(id) == null) {
                    throw new NonexistentEntityException("The proveedores with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedores proveedores;
            try {
                proveedores = em.getReference(Proveedores.class, id);
                proveedores.getCodigoproveedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedores with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Telefonosproveedor> telefonosproveedorListOrphanCheck = proveedores.getTelefonosproveedorList();
            for (Telefonosproveedor telefonosproveedorListOrphanCheckTelefonosproveedor : telefonosproveedorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedores (" + proveedores + ") cannot be destroyed since the Telefonosproveedor " + telefonosproveedorListOrphanCheckTelefonosproveedor + " in its telefonosproveedorList field has a non-nullable codigoproveedor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tipocontribuyente codigotipocontribuyente = proveedores.getCodigotipocontribuyente();
            if (codigotipocontribuyente != null) {
                codigotipocontribuyente.getProveedoresList().remove(proveedores);
                codigotipocontribuyente = em.merge(codigotipocontribuyente);
            }
            Tipoproveedores codtipoprov = proveedores.getCodtipoprov();
            if (codtipoprov != null) {
                codtipoprov.getProveedoresList().remove(proveedores);
                codtipoprov = em.merge(codtipoprov);
            }
            em.remove(proveedores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedores> findProveedoresEntities() {
        return findProveedoresEntities(true, -1, -1);
    }

    public List<Proveedores> findProveedoresEntities(int maxResults, int firstResult) {
        return findProveedoresEntities(false, maxResults, firstResult);
    }

    private List<Proveedores> findProveedoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedores.class));
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

    public Proveedores findProveedores(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedores.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedores> rt = cq.from(Proveedores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
