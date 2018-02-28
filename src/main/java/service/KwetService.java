package service;

import dao.KwetDaoImpl;
import domain.Kwet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * | Created by juleskreutzer
 * | Date: 27-02-18
 * |
 * | Project Info:
 * | Project Name: Kwetter
 * | Project Package Name: service
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

@Stateless
@Named
public class KwetService {

    @Inject
    KwetDaoImpl kwetDao;

    public Collection<Kwet> getAllKwets() {
        return kwetDao.findAll();
    }

    public Kwet findById(long id) {
        return kwetDao.findById(id);
    }

    public Collection<Kwet> findByText(String text) {
        return kwetDao.findByText(text);
    }

    public Collection<Kwet> findByOwnerId(long id) {
        return kwetDao.findByOwnerId(id);
    }

    public void create(Kwet kwet) {
        kwetDao.create(kwet);
    }

    public void update(Kwet kwet) {
        kwetDao.edit(kwet);
    }
}
