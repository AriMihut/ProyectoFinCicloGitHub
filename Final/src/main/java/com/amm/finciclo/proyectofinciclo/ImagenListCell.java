package com.amm.finciclo.proyectofinciclo;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImagenListCell extends ListCell<String>{
    
    @Override
    protected void updateItem(String elementoSeleccionado, boolean empty){
        super.updateItem(elementoSeleccionado, empty);
        
        if(elementoSeleccionado == null || empty){
            setItem(null);
            setGraphic(null);
        }else{            
            setText(elementoSeleccionado);
            String nombreImagen = elementoSeleccionado.toLowerCase() + ".png";
            Image imagen = new Image(getClass().getResourceAsStream("../../../../iconos/" + nombreImagen));
            ImageView imageView = new ImageView(imagen);
            Label labelConImagen = new Label();
            labelConImagen.setGraphic(imageView);
            setGraphic(labelConImagen);
        }
    }
}
