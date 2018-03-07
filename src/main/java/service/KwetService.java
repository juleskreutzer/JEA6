package service;

import dao.KwetDaoImpl;
import domain.Kwet;
import exceptions.KwetNotFoundException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

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

    public List<Kwet> getAllKwets() {
        return kwetDao.findAll();
    }

    public Kwet findById(long id) {
        return kwetDao.findById(id);
    }

    public List<Kwet> findByText(String text) {
        return kwetDao.findByText(text);
    }

    public List<Kwet> findByOwnerId(long id) {
        return kwetDao.findByOwnerId(id);
    }

    public void create(Kwet kwet) {
        kwetDao.create(kwet);
    }

    public void update(Kwet kwet) throws KwetNotFoundException {
        kwetDao.edit(kwet);
    }
}
