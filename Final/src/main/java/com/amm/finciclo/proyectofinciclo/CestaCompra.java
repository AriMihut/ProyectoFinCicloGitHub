package com.amm.finciclo.proyectofinciclo;

import java.text.NumberFormat;

public class CestaCompra {
    
    private Servicio[] cesta;
    private int totalArticulosCesta;      
    private double precioTotal;  
    private int capacidad;       

    public CestaCompra(){

      capacidad = 15;
      cesta = new Servicio[capacidad];
      totalArticulosCesta = 0;
      precioTotal = 0.0;
    }
    
    public void anadirACesta(String nombreServicio, double precio, int cantidad){ 

        Servicio servicio = new Servicio(nombreServicio, precio, cantidad);
        precioTotal += (precio * cantidad);
        totalArticulosCesta += cantidad;
        totalArticulosCesta += 1;
        if(totalArticulosCesta==capacidad)
        {
            aumentarCapacidadCesta();
        }
    }
    
    public String toString()
    {
      NumberFormat fmt = NumberFormat.getCurrencyInstance();

      String contenido = "\nCesta de la compra\n";
      contenido += "\nServicio \t\tPrecio unidad \tCantidad \tTotal\n";
      
      for (int i = 0; i < totalArticulosCesta; i++)
          contenido += cesta[i].toString() + "\n";

      contenido += "\nPrecio total: " + fmt.format(precioTotal);
      contenido += "\n";

      return contenido;
    }

    private void aumentarCapacidadCesta()
    {
        Servicio[] servicio = new Servicio[capacidad + 3];
        for(int i=0; i < capacidad; i++)
        {
            servicio[i] = cesta[i];
        }
        cesta = servicio; 
        servicio = null;
        capacidad = cesta.length;
    }
    
}
