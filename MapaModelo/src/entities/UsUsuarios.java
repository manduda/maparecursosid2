/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import vo.PaPermisosAsesorVO;
import vo.PtTipoPermisoVO;
import vo.TuTipoUsuarioVO;
import vo.UsUsuariosVO;
import vo.UsersVO;
import vo.UsuariosVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "US_USUARIOS")
public class UsUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "USN_CODIGO")
    private int usnCodigo;
    
    @JoinColumn(name = "CODIGO_SIUST", referencedColumnName = "USER_CODE")
    @ManyToOne(optional = false)
    private Usuarios codigoSIUST;
    
    @JoinColumn(name = "TUN_CODIGO", referencedColumnName = "TUN_CODIGO")
    @ManyToOne(optional = false)
    private TuTipoUsuario tunCodigo;
        
    @Basic(optional = false)
    @Column(name = "USN_ESTADO")
    private int usnEstado;
    
    @Basic(optional = false)
    @Column(name = "USF_FECHA")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date usfFecha;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usnCodigo")
    private Collection<PaPermisosAsesor> paPermisosAsesorCollection;

    public UsUsuarios(){
    }

    public UsUsuariosVO toVO(){
        UsUsuariosVO vo = new UsUsuariosVO();
        // Datos Usuario
        UsuariosVO datosusuario = new UsuariosVO();
        datosusuario.setUserCode(this.getCodigoSIUST().getUserCode());
        //datosusuario.setName(this.getCodigoSIUST().getName());
        //datosusuario.setLastName(this.getCodigoSIUST().getLastName());
        datosusuario.setEmail(this.getCodigoSIUST().getEmail());
        //datosusuario.setLogin(this.getCodigoSIUST().getLogin());
        //datosusuario.setPassword(this.getCodigoSIUST().getPassword());
        vo.setCodigoSIUST(datosusuario);
        //------------------------------------
        // Tipo Usuario
        TuTipoUsuarioVO tipousuario = new TuTipoUsuarioVO();
        tipousuario.setTunCodigo(this.getTunCodigo().getTunCodigo());
        tipousuario.setTutNombre(this.getTunCodigo().getTutNombre());
        vo.setTunCodigo(tipousuario);
        //------------------------------------
        vo.setUsnCodigo(this.getUsnCodigo());
        vo.setUsnEstado(this.getUsnEstado());
        vo.setUsfFecha(this.getUsfFecha());
        // Permisos Usuario
        if (this.getPaPermisosAsesorCollection() != null){
            Collection<PaPermisosAsesor> permisos = this.getPaPermisosAsesorCollection();
            Collection<PaPermisosAsesorVO> permisosVO = new ArrayList<PaPermisosAsesorVO>();
            for (PaPermisosAsesor p : permisos) {
                PaPermisosAsesorVO permiso = new PaPermisosAsesorVO();
                permiso.setPanCodigo(p.getPanCodigo());
                permiso.setPtnCodigo(p.getPtnCodigo().toVO());
                UsUsuariosVO usuario = new UsUsuariosVO();
                usuario.setUsnCodigo(p.getUsnCodigo().getUsnCodigo());
                permiso.setUsnCodigo(usuario);
                permisosVO.add(permiso);
            }
            vo.setPaPermisosAsesorCollection(permisosVO);
        }
        
        tipousuario.setTunCodigo(this.getTunCodigo().getTunCodigo());
        tipousuario.setTutNombre(this.getTunCodigo().getTutNombre());
        vo.setTunCodigo(tipousuario);
        //------------------------------------
        
        return vo;
    }

    public Usuarios getCodigoSIUST() {
        return codigoSIUST;
    }

    public void setCodigoSIUST(Usuarios codigoSIUST) {
        this.codigoSIUST = codigoSIUST;
    }

    public TuTipoUsuario getTunCodigo() {
        return tunCodigo;
    }

    public void setTunCodigo(TuTipoUsuario tunCodigo) {
        this.tunCodigo = tunCodigo;
    }

    public int getUsnCodigo() {
        return usnCodigo;
    }

    public void setUsnCodigo(int usnCodigo) {
        this.usnCodigo = usnCodigo;
    }

    public int getUsnEstado() {
        return usnEstado;
    }

    public void setUsnEstado(int usnEstado) {
        this.usnEstado = usnEstado;
    }

    public Date getUsfFecha() {
        return usfFecha;
    }

    public void setUsfFecha(Date usfFecha) {
        this.usfFecha = usfFecha;
    }

    public Collection<PaPermisosAsesor> getPaPermisosAsesorCollection() {
        return paPermisosAsesorCollection;
    }

    public void setPaPermisosAsesorCollection(Collection<PaPermisosAsesor> paPermisosAsesorCollection) {
        this.paPermisosAsesorCollection = paPermisosAsesorCollection;
    }
    
    
}
