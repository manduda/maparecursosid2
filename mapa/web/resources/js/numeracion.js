seleccionId = "";
seleccionIdAnterior = "-1";
seleccionRango = false;
function seleccionarNumeracion(id){
    
    longitug = id.length;
    x = parseInt(id.substring(0, longitug-2));
    y = parseInt(id.substring(longitug-2, longitug-1));
    z = parseInt(id.substring(longitug-1, longitug));

    if ((!seleccionRango) && (seleccionIdAnterior == "-1")){
        colorCelda(x,y,z,true);
        //RequestContext.getCurrentInstance().update("columna"+seleccionId);
        seleccionId = id;
        seleccionIdAnterior = seleccionId;
    } else if ((!seleccionRango) && (seleccionIdAnterior != "-1")) {
        longitugA = seleccionIdAnterior.length;
        xA = parseInt(seleccionIdAnterior.substring(0, longitugA-2));
        yA = parseInt(seleccionIdAnterior.substring(longitugA-2, longitugA-1));
        zA = parseInt(seleccionIdAnterior.substring(longitugA-1, longitugA));
        
        orden = true;
        if (xA < x){
            orden = true;
        } else if (xA > x) {
            orden = false;
        } else {
            if (yA < y) {
                orden = true;
            } else if (yA > y) {
                orden = false;
            } else {
                if (zA < z) {
                    orden = true;
                } else if (zA > z) {
                    orden = false;
                } else {
                    orden = true;
                }
            }
        }
        
        if (orden) {
            colorCeldas(xA,yA,zA,x,y,z,true);
        } else {
            colorCeldas(x,y,z,xA,yA,zA,true);
        }
        
        seleccionRango = true;
        seleccionIdAnterior = seleccionId;
        seleccionId = id;
    } else {
        longitug = seleccionId.length;
        x = parseInt(seleccionId.substring(0, longitug-2));
        y = parseInt(seleccionId.substring(longitug-2, longitug-1));
        z = parseInt(seleccionId.substring(longitug-1, longitug));
        longitugA = seleccionIdAnterior.length;
        xA = parseInt(seleccionIdAnterior.substring(0, longitugA-2));
        yA = parseInt(seleccionIdAnterior.substring(longitugA-2, longitugA-1));
        zA = parseInt(seleccionIdAnterior.substring(longitugA-1, longitugA));
        
        orden = true;
        if (xA < x){
            orden = true;
        } else if (xA > x) {
            orden = false;
        } else {
            if (yA < y) {
                orden = true;
            } else if (yA > y) {
                orden = false;
            } else {
                if (zA < z) {
                    orden = true;
                } else if (zA > z) {
                    orden = false;
                } else {
                    orden = true;
                }
            }
        }

        if (orden) {
            colorCeldas(xA,yA,zA,x,y,z,false);
        } else {
            colorCeldas(x,y,z,xA,yA,zA,false);
        }
        
        
        longitug = id.length;
        x = parseInt(id.substring(0, longitug-2));
        y = parseInt(id.substring(longitug-2, longitug-1));
        z = parseInt(id.substring(longitug-1, longitug));
        colorCelda(x,y,z,true);
        seleccionRango = false;
        seleccionIdAnterior = id;
        seleccionId = id;
    }


}

function colorCelda(x, y, z, seleccionar){
    estilo = "background:#828282;color:#000000";

    backgroud = 'rgb(130, 130, 130)';
    color = 'rgb(0, 0, 0)';
    border = 'rgb(0, 0, 0)';
    //alert(document.getElementById('h'+id).style.border);
    if (!seleccionar){
        backgroud = document.getElementById('hcolumna'+x.toString()+y.toString()+z.toString()).style.background;
        color = document.getElementById('hcolumna'+x.toString()+y.toString()+z.toString()).style.color;
        //border = document.getElementById('hcolumna'+x.toString()+y.toString()+z.toString()).style.border;
    }
    document.getElementById('columna'+x.toString()+y.toString()+z.toString()).style.background = backgroud;
    document.getElementById('columna'+x.toString()+y.toString()+z.toString()).style.color = color;
    //document.getElementById('columna'+x.toString()+y.toString()+z.toString()).style.border = border;
}

function colorCeldas(x1, y1, z1, x2, y2, z2, seleccionar){
    //colorCelda(x2,y2,z2,true);

    for(i = x1; i <= x2; i++){
        sizeX1 = 0;
        sizeX2 = 9;
        if (i == x1){
            sizeX1 = y1;
        }
        if (i == x2){
            sizeX2 = y2;
        }
        for(j = sizeX1; j <= sizeX2; j++){
            sizeY1 = 0;
            sizeY2 = document.getElementById('lista'+i.toString()+j.toString()+'0').value;
            if ((i == x1)&&(j == y1)){
                sizeY1 = z1;
            }
            if ((i == x2)&&(j == y2)){
                sizeY2 = z2+1;
            }
            for(k = sizeY1; k < sizeY2; k++){
                colorCelda(i,j,k,seleccionar);
            }

        }
    }
}


