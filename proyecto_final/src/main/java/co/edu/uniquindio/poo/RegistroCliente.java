package co.edu.uniquindio.poo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.mail.internet.*;

public class RegistroCliente {
    private static final String ARCHIVO_USUARIOS = "usuarios.ser";
    private Map<String, Cliente> clientesRegistrados;

    public RegistroCliente() {
        clientesRegistrados = cargarClientesRegistrados();
    }

    private Map<String, Cliente> cargarClientesRegistrados() {
        Map<String, Cliente> clientesRegistrados = new HashMap<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO_USUARIOS))) {
            clientesRegistrados = (Map<String, Cliente>) in.readObject();
        } catch (FileNotFoundException e) {
            // Maneja el caso en que el archivo no existe
            System.err.println("El archivo de usuarios registrados no existe.");
        } catch (IOException | ClassNotFoundException e) {
            // Maneja cualquier error de E/S o clase no encontrada
            System.err.println("Error al cargar clientes registrados: " + e.getMessage());
        }
        return clientesRegistrados;
    }

    public boolean registrarUsuario(String nombre, String cedula, String telefono, String usuario, String contraseña) {
        if (!clientesRegistrados.containsKey(usuario)) {
            Cliente cliente = new Cliente(nombre, cedula, telefono, usuario, contraseña);
            clientesRegistrados.put(usuario, cliente);
            guardarClientesRegistrados();
            return true;
        }
        return false;
    }

    private void guardarClientesRegistrados() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO_USUARIOS))) {
            out.writeObject(clientesRegistrados);
        } catch (IOException e) {
            // Maneja la excepción apropiadamente, por ejemplo, registrándola
            System.err.println("Error al guardar clientes registrados: " + e.getMessage());
        }
    }

    public boolean validarCorreoElectronico(String correo) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }
}
