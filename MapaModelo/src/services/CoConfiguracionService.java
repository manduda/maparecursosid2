/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.CoConfiguracionDAO;
import entities.CmConfiguracionModulos;
import entities.CoConfiguracion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.CmConfiguracionModulosVO;
import vo.CoConfiguracionVO;

/**
 *
 * @author miguel.duran
 */
public class CoConfiguracionService {
    public CoConfiguracionVO getById(int id, EntityManager em){
        CoConfiguracion entity = CoConfiguracionDAO.findbyId(id, em);
        return entity.toVO();
    }
    
    public CoConfiguracionVO getByName(String nombre, EntityManager em){
        CoConfiguracion entity = CoConfiguracionDAO.findbyName(nombre, em);
        return entity.toVO();
    }

}
