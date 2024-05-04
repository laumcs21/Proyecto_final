package co.edu.uniquindio.poo;

import java.io.Serializable;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String cedula;
    private String telefono;
    private String usuario;
    private String contraseña;

    public Cliente(String nombre, String cedula, String telefono, String usuario, String contraseña) {
        assert usuario != null;
        assert contraseña != null;

        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

}