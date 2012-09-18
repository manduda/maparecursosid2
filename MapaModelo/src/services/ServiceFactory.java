/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author miguel.duran
 */
public class ServiceFactory {
    public static CcCodigosCortosService createCcCodigosCortosService()  {
           return (new CcCodigosCortosService());
    }
    public static CdCodigosMncService createCdCodigosMncService()  {
           return (new CdCodigosMncService());
    }
    public static CiCodigosIinService createCiCodigosIinService()  {
           return (new CiCodigosIinService());
    }
    public static ClCodigosLdService createClCodigosLdService() {
           return (new ClCodigosLdService());
    }
    public static CmConfiguracionModulosService createCmConfiguracionModulosService() {
           return (new CmConfiguracionModulosService());
    }
    public static EsEstadoService createEsEstadoService() {
           return (new EsEstadoService());
    }
    public static MaMarcacionAbreviadaService createMaMarcacionAbreviadaService() {
           return (new MaMarcacionAbreviadaService());
    }
    public static MunicipiosService createMunicipiosService() {
           return (new MunicipiosService());
    }
    public static Nc1xyService createNc1xyService() {
           return (new Nc1xyService());
    }
    public static NdNdcService createNdNdcService() {
           return (new NdNdcService());
    }
    public static NrCodigosNrnService createNrCodigosNrnService() {
           return (new NrCodigosNrnService());
    }
    public static NuNumeracionService createNuNumeracionService() {
           return (new NuNumeracionService());
    }
    public static ReRegionService createReRegionService() {
           return (new ReRegionService());
    }
    public static RsReservasTemporalesService createRsReservasTemporalesService() {
           return (new RsReservasTemporalesService());
    }
    public static SeSenalizacionService createSeSenalizacionService() {
           return (new SeSenalizacionService());
    }
    public static TrTramitesService createTrTramitesService() {
           return (new TrTramitesService());
    }
    public static UsUsuariosService createUsUsuariosService() {
           return (new UsUsuariosService());
    }
    public static PaPermisosAsesorService createPaPermisosAsesorService() {
           return (new PaPermisosAsesorService());
    }
}
