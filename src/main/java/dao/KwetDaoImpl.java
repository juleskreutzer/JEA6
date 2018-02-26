package dao;

import domain.Kwet;

import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 23-02-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: dao
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

@Stateless
@Startup
public class KwetDaoImpl implements IKwetDoa{

    @PersistenceContext(unitName = "KwetterGlassfish")
    private EntityManager em;

    public void create(Kwet k) {
        em.persist(k);

    }

    public void edit(Kwet k) {
        em.merge(k);
    }

    public void remove(Kwet k) {
        if(em.contains(k)) {
            k = em.merge(k);
        }

        em.remove(k);
    }

    public List<Kwet> findAll() {
        return (List<Kwet>) em.createNamedQuery("Kwet.FindAll", Kwet.class).getResultList();
    }

    public List<Kwet> findByText(String text) {
        Query q = em.createNamedQuery("Kwet.GetByText");
        q.setParameter("text", "%" + text + "%"); // use % to mark that other text can come before or after given text
        try {
            return (List<Kwet>) q.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public Kwet getById(String id) {
        Query q = em.createNamedQuery("Kwet.GetById");
        q.setParameter("id", id);
        try {
            return (Kwet) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
