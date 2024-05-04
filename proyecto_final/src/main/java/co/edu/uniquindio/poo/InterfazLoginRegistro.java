package co.edu.uniquindio.poo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InterfazLoginRegistro extends Application {

    private LoginCliente loginCliente;
    private RegistroCliente registroCliente;

    private boolean enRegistro = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        loginCliente = new LoginCliente();
        registroCliente = new RegistroCliente();

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        TextField usuarioField = new TextField();
        usuarioField.setPromptText("Correo electrónico");
        PasswordField contraseñaField = new PasswordField();
        contraseñaField.setPromptText("Contraseña");

        Button accionButton = new Button("Iniciar Sesión");
        Button registroButton = new Button("Registrarse");
        Button volverButton = new Button("Volver a Iniciar Sesión");

        // Crear campos adicionales para el registro
        TextField nombreField = new TextField();
        nombreField.setPromptText("Nombre");
        TextField cedulaField = new TextField();
        cedulaField.setPromptText("Cédula");
        TextField telefonoField = new TextField();
        telefonoField.setPromptText("Teléfono");

        Label mensajeLabel = new Label();

        // Manejar evento de registro
        registroButton.setOnAction(e -> {
            if (!enRegistro) {
                root.getChildren().clear(); // Limpiar todos los elementos de la raíz
                // Mostrar campos de registro, botón de registro y botón de volver
                root.getChildren().addAll(nombreField, cedulaField, telefonoField, usuarioField, contraseñaField,
                        registroButton, volverButton, mensajeLabel);
                enRegistro = true;
                mensajeLabel.setVisible(false); // Mostrar el mensaje de registro exitoso
            } else {
                // Realizar el proceso de registro
                String nombre = nombreField.getText();
                String cedula = cedulaField.getText();
                String telefono = telefonoField.getText();
                String usuario = usuarioField.getText();
                String contraseña = contraseñaField.getText();

                if (registroCliente.validarCorreoElectronico(usuario)) {
                    if (registroCliente.registrarUsuario(nombre, cedula, telefono, usuario, contraseña)) {
                        mensajeLabel.setText("Registro exitoso. Por favor, inicie sesión.");
                        mensajeLabel.setVisible(true); // Mostrar el mensaje de registro exitoso
                        // Limpiar los campos de registro
                        nombreField.clear();
                        cedulaField.clear();
                        telefonoField.clear();
                        usuarioField.clear();
                        contraseñaField.clear();
                    } else {
                        mensajeLabel.setText("El usuario ya está en uso");
                        mensajeLabel.setVisible(true);
                    }
                } else {
                    mensajeLabel.setText("El usuario debe ser una dirección de correo electrónico válida.");
                    mensajeLabel.setVisible(true);
                }
            }
        });

        // Manejar evento de volver a iniciar sesión
        volverButton.setOnAction(e -> {
            root.getChildren().clear(); // Limpiar todos los elementos de la raíz
            // mostrar elementos de inicio de sesión:
            root.getChildren().addAll(usuarioField, contraseñaField, accionButton, registroButton, mensajeLabel);
            enRegistro = false;
            mensajeLabel.setVisible(false); // Ocultar el mensaje de registro exitoso
        });

        // Manejar evento de inicio de sesión
        accionButton.setOnAction(e -> {
            // Realizar el proceso de inicio de sesión
            String usuario = usuarioField.getText();
            String contraseña = contraseñaField.getText();
            if (loginCliente.login(usuario, contraseña)) {
                mensajeLabel.setText("Inicio de sesión exitoso");
                mensajeLabel.setVisible(true); // Mostrar el mensaje de inicio de sesión exitoso

                usuarioField.clear();
                contraseñaField.clear();

            } else {
                mensajeLabel.setText("Nombre de usuario o contraseña incorrectos");
                mensajeLabel.setVisible(true); // Mostrar el mensaje de inicio de sesión fallido
            }
        });

        root.getChildren().addAll(usuarioField, contraseñaField, accionButton, registroButton, mensajeLabel);

        // Colocar el VBox dentro de un ScrollPane
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true); // Ajustar el ancho del ScrollPane al ancho de la ventana

        // Crear una escena con el ScrollPane
        Scene scene = new Scene(scrollPane, 300, 250);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Login y Registro");
        primaryStage.show();
    }
}
