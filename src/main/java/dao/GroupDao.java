package dao;


import domain.Group;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@ApplicationScoped
@Named
public class GroupDao {

    @PersistenceContext(unitName = "KwetterGlassfish")
    private EntityManager em;

    public void createGroup(Group group) {
        em.merge(group);
    }

    public Group findGroup(String groupname) {
        Query q = em.createQuery("SELECT g FROM Group g WHERE g.groupname = :groupname");
        q.setParameter("groupname", groupname);

        try {
            return (Group) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }


}
