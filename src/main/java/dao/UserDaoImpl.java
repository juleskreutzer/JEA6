package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
public class UserDaoImpl {


    @PersistenceContext(unitName = "KwetterGlassfish")
    private EntityManager em;
}
