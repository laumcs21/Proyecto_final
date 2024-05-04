package co.edu.uniquindio.poo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

public class LoginCliente {
    private static final String ARCHIVO_USUARIOS = "usuarios.ser";
    private Map<String, Cliente> clientesRegistrados;

    public LoginCliente() {
        cargarClientesRegistrados();
    }

    private void cargarClientesRegistrados() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO_USUARIOS))) {
            clientesRegistrados = (Map<String, Cliente>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            clientesRegistrados = new HashMap<>();
        }
    }

    public boolean login(String usuario, String contraseña) {
        Cliente cliente = clientesRegistrados.get(usuario);
        return cliente != null && cliente.getContraseña().equals(contraseña);
    }
}
