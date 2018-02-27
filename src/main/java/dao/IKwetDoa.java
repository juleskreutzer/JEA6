package dao;

import domain.Kwet;

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
public interface IKwetDoa {
    // Interface describing methods that can be called on Kwet instance

    /**
     * Create a new kwet
     * @param k The kwet to be created
     */
    void create(Kwet k);

    /**
     * Edit an existing kwet
     * @param k the kwet to be edited
     */
    void edit(Kwet k);

    /**
     * Remove an existing kwet
     * @param k the kwet to be removed
     */
    void remove(Kwet k);

    /**
     * Find all kwets
     * @return returns a list of all kwets
     */
    List<Kwet> findAll();

    /**
     * Find kwets based on text
     * @param text text to be searched for
     * @return returns a list of kwets that have the provided text
     */
    List<Kwet> findByText(String text);

    /**
     * Get a kwet based on the ID
     * @param id ID of the kwet
     * @return returns the specific kwet
     */
    Kwet getById(long id);


}
