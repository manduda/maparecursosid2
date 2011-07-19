/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 *
 * @author Personal 
 */
public class ConvertirListasHelper {

    public List createSelectItemsList(List originList, String stringValueMethodName, String integerValueMethodName, String labelMethodName, boolean activarSeleccionar, String elementoOmision)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        ArrayList selectItemsList = new ArrayList();
        Class itemClass = null;
        Iterator iter = originList.iterator();
        if (iter.hasNext()) {
            itemClass = iter.next().getClass();
        }
        /*else
        return null;*/
        SelectItem selectItem = new SelectItem();

        if (activarSeleccionar == true) {
            if (stringValueMethodName != null && stringValueMethodName.length() > 0) {
                selectItem.setValue("-1");
            }
            if (integerValueMethodName != null) {
                selectItem.setValue("-1");
            }


            selectItem.setLabel(elementoOmision);
            selectItemsList.add(selectItem);
        }

        for (iter = originList.iterator(); iter.hasNext();) {
            Object item = iter.next();
            selectItem = new SelectItem();
            if (stringValueMethodName != null && stringValueMethodName.length() > 0) {
                selectItem.setValue(obtainStringValue(item, itemClass, stringValueMethodName));
            }
            if (integerValueMethodName != null) {
                selectItem.setValue(String.valueOf(obtainIntegerValue(item, itemClass, integerValueMethodName)));
            }
            if (labelMethodName != null && labelMethodName.length() > 0) {
                selectItem.setLabel(obtainStringValue(item, itemClass, labelMethodName));
            }
            selectItemsList.add(selectItem);
        }

        return selectItemsList;
    }

    public List createSelectItemsList(List originList, String stringValueMethodName, String integerValueMethodName, String labelMethodName1, String labelMethodName2, boolean activarSeleccionar, String defaultValue)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        ArrayList selectItemsList = new ArrayList();
        Class itemClass = null;
        Iterator iter = originList.iterator();
        if (iter.hasNext()) {
            itemClass = iter.next().getClass();
        }
        /*else
        return null;*/
        SelectItem selectItem = new SelectItem();

        if (activarSeleccionar == true) {
            if (stringValueMethodName != null && stringValueMethodName.length() > 0) {
                selectItem.setValue("-1");
            }
            if (integerValueMethodName != null) {
                selectItem.setValue("-1");
            }
            //selectItem.setLabel("Seleccione...");
            selectItem.setLabel(defaultValue);
            selectItemsList.add(selectItem);
        }

        for (iter = originList.iterator(); iter.hasNext();) {
            Object item = iter.next();
            selectItem = new SelectItem();
            if (stringValueMethodName != null && stringValueMethodName.length() > 0) {
                selectItem.setValue(obtainStringValue(item, itemClass, stringValueMethodName));
            }
            if (integerValueMethodName != null) {
                selectItem.setValue(String.valueOf(obtainIntegerValue(item, itemClass, integerValueMethodName)));
            }
            if ((labelMethodName1 != null && labelMethodName1.length() > 0) && (labelMethodName2 != null && labelMethodName2.length() > 0)) {
                selectItem.setLabel(obtainStringValue(item, itemClass, labelMethodName1) + " " + obtainStringValue(item, itemClass, labelMethodName2));
            }
            selectItemsList.add(selectItem);
        }

        return selectItemsList;
    }

    public List createSelectItemsList(List<String> originList, String defaultValue) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        ArrayList<SelectItem> selectItemsList = new ArrayList();
        SelectItem selectItem = new SelectItem();
        
        int i = 1;
        selectItem.setValue(defaultValue);
        selectItem.setLabel("");
        selectItemsList.add(selectItem);
        i = i + 1;
        
        for (String s : originList) {
            selectItem = new SelectItem();
            selectItem.setLabel(s);
            selectItem.setValue(s);
            selectItemsList.add(selectItem);
            i = i + 1;
        }
        
        return selectItemsList;
        
    }

    private Integer obtainIntegerValue(Object obj, Class itemClass, String methodName)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method mtd = null;
        mtd = itemClass.getMethod(methodName, new Class[0]);
        Integer codigoInteger = (Integer) mtd.invoke(obj, new java.lang.Object[0]);
        return codigoInteger;
    }
    
    private String obtainStringValue(Object obj, Class itemClass, String methodName)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method mtd = itemClass.getMethod(methodName, new Class[0]);
        String codigoString = (String) mtd.invoke(obj, new java.lang.Object[0]);
        if (codigoString != null) {
            return codigoString.trim();
        } else {
            return null;
        }
    }
}
