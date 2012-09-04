/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inicio;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class MapaExceptionHandlerFactory extends ExceptionHandlerFactory {
   private ExceptionHandlerFactory base;
    
    public MapaExceptionHandlerFactory(ExceptionHandlerFactory base) {
        this.base = base;
    }
    
    @Override
    public ExceptionHandler getExceptionHandler() {
        return new MapaExceptionHandler(base.getExceptionHandler());
    } 
}
