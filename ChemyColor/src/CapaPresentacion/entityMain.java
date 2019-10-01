/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaPresentacion;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Amaya
 */
public class entityMain {
    private static final EntityManagerFactory ent = Persistence.createEntityManagerFactory("ChemyColorPU");
    
    public entityMain(){}
    
    public static EntityManagerFactory getInstance(){
        return ent;
    }
}
