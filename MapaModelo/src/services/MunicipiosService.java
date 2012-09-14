/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.DepartamentosDAO;
import daos.MunicipiosDAO;
import entities.Departamentos;
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
    /*public MunicipiosVO getVOFromEntity(Municipios entity){
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
    }*/
    
    public MunicipiosVO getById(String id, EntityManager em){
        Municipios entity = MunicipiosDAO.findbyId(id, em);
        return entity.toVO();
    }

    public List<MunicipiosVO> getList(EntityManager em){
        List<Municipios> municipio = MunicipiosDAO.getList(em);
        List<MunicipiosVO> municipioVO = new ArrayList<MunicipiosVO>();        
        for (Municipios m : municipio) {
            municipioVO.add(m.toVO());
        }
        return municipioVO;
    }
    
    public List<MunicipiosVO> cargarMunicipios(String departamento, EntityManager em){
        List<Municipios> municipio = MunicipiosDAO.cargarMunicipios(departamento, em);
        List<MunicipiosVO> municipioVO = new ArrayList<MunicipiosVO>();
        for (Municipios m : municipio) {
            municipioVO.add(m.toVO());
        }
        return municipioVO;
    }
    
    public List<DepartamentosVO> cargarDepartamentos(EntityManager em){
        List<Departamentos> departamento = DepartamentosDAO.cargarDepartamentos(em);
        List<DepartamentosVO> departamentoVO = new ArrayList<DepartamentosVO>();
        for (Departamentos d : departamento) {
            departamentoVO.add(d.toVO());
        }
        return departamentoVO;
    }
}
