package com.amm.finciclo.proyectofinciclo;

public enum TipoUsuario {
    ADMIN("admin"), 
    CLIENTE("cliente"), 
    EMPLEADO("empleado"),
    PROVEEDOR("proveedor");
   
    private String rolUsuario;
    
    TipoUsuario(String tipoUsuario){
    this.rolUsuario = tipoUsuario;
    }
    
    public String getValue() {
       return this.rolUsuario;
    }
};