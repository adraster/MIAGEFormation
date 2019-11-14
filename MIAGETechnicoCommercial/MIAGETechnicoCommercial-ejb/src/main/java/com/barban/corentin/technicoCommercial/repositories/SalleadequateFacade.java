/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.technicoCommercial.repositories;

import com.barban.corentin.technicoCommercial.entities.Salleadequate;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Corentin
 */
@Stateless
public class SalleadequateFacade extends AbstractFacade<Salleadequate> implements SalleadequateFacadeLocal {

    @PersistenceContext(unitName = "com.barban.corentin_MIAGETechnicoCommercial-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SalleadequateFacade() {
        super(Salleadequate.class);
    }
    
}