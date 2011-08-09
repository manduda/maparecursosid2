/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.MunicipiosDAO;
import entities.Municipios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.DepartamentosVO;
import vo.MunicipiosVO;

/**
 *
 * @author miguel.duran
 */
public class MunicipiosService {
    public MunicipiosVO getVOFromEntity(Municipios entity){
        MunicipiosVO vo = new MunicipiosVO();
        // Departamento
        DepartamentosVO departamento = new DepartamentosVO();
        departamento.setCodigoDepartamento(entity.getCodigoDepartamento().getCodigoDepartamento());
        departamento.setNombreDepartamento(entity.getCodigoDepartamento().getNombreDepartamento());
        vo.setCodigoDepartamento(departamento);
        //------------------------------------
        vo.setCodigoMunicipio(entity.getCodigoMunicipio());
        vo.setNombreMunicipio(entity.getNombreMunicipio());
        return vo;
    }
    
    public MunicipiosVO getById(String id, EntityManager em){
        Municipios entity = MunicipiosDAO.findbyId(id, em);
        return getVOFromEntity(entity);
    }

    public List<MunicipiosVO> getList(EntityManager em){
        List<Municipios> municipio = MunicipiosDAO.getList(em);
        List<MunicipiosVO> municipioVO = new ArrayList<MunicipiosVO>();        
        MunicipiosVO vo = new MunicipiosVO();
        int size = municipio.size();
        for (int i = 0; i < size; i++) {
            vo = getVOFromEntity(municipio.get(i));
            municipioVO.add(vo);
        }
        return municipioVO;
    }
}
