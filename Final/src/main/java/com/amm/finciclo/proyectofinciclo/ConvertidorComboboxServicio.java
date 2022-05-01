package com.amm.finciclo.proyectofinciclo;

import javafx.util.StringConverter;

public class ConvertidorComboboxServicio extends StringConverter<Servicio>{    

    @Override
    public String toString(Servicio servicio) {
        if(servicio.getNombreServicio()== null){
            return null;
        }
        return servicio.getNombreServicio();
    }

    @Override
    public Servicio fromString(String string) {
        return null;
    }
    
}
