# Bitacora de prompts

Laboratorio 06: Fundamentos de Ingenieria de Prompts. Herramienta de IA usada: ChatGPT

## Ejercicio 2: Tokens y ventana de contexto

| Texto                              | Caracteres | Tokens |
| ---------------------------------- | ---------- | ------ |
| Los estudiantes programan en Java. | 8          | 37     |
| The students program in Java.      | 7          | 32     |
| desafortunadamente                 | 5          | 20     |

## Ejercicio 3: Temperatura

| Temperatura | % de BiblioTec | Nombres en los 5 intentos                               |
| ----------- | -------------- | ------------------------------------------------------- |
| 0           | 100            | LibroYa, PrestaLibro, LectoGo, PaginaLibre, NubeDeTinta |
| 0.5         | 65.3           | LibroYa, PrestaLibro, LectoGo, PaginaLibre, NubeDeTinta |
| 1           | 44.5           | LibroYa, PrestaLibro, LectoGo, PaginaLibre, NubeDeTinta |
| 1.8         | 32.2           | LibroYa, PrestaLibro, LectoGo, PaginaLibre, NubeDeTinta |

## Ejercicio 4: Prompt vago vs estructurado

| Criterio                            | Prompt vago | Prompt estructurado |
| ----------------------------------- | ----------- | ------------------- |
| Menciona el objetivo del sistema    | SI          | SI                  |
| Menciona a los usuarios principales | NO          | SI                  |
| Tiene exactamente 3 funcionalidades | NO          | SI                  |
| Esta en 3 parrafos                  | NO          | SI                  |
| Lo usaria en un informe real        | NO          | NO                  |

## Ejercicio 5: Anatomia de un prompt

## Ejercicio 5: Anatomia de un prompt, paso a paso

| Componente  | Texto de mi prompt                                                                                                                              |
| ----------- | ----------------------------------------------------------------------------------------------------------------------------------------------- |
| Rol         | Actua como desarrollador Java.                                                                                                                  |
| Instruccion | Crea un programa en Java para gestionar los productos de una tienda usando una clase Producto con los atributos codigo, nombre, precio y stock. |
| Contexto    | El sistema servirá para una tienda local que necesita controlar su inventario de manera sencilla.                                               |
| Ejemplo     | Usa este estilo para los metodos: getPrecio(), setPrecio(double precio).                                                                        |
| Formato     | Explica primero la estructura de la clase y luego presenta el codigo Java.                                                                      |

- **Nivel 1:** Generó una estructura genérica aleatoria.
- **Nivel 2:** Enfocó la respuesta al estándar técnico de desarrollo.
- **Nivel 3:** Centró el código en la gestión del negocio solicitado.
- **Nivel 4:** Creó la clase con los atributos específicos solicitados de forma estricta.
- **Nivel 5:** Estructuró la salida separando la explicación conceptual del bloque de código.

## Ejercicio 6: Del prompt básico al profesional (e iterar)

### Prompt v1 (Básico)

```text
Hazme codigo para un login.
```

### Prompt v2 (Profesional)

```text
Actua como desarrollador Java. Crea un ejemplo de login para una aplicacion de escritorio utilizando Swing. El usuario debe ingresar correo y contrasena. Explica brevemente el funcionamiento y presenta el codigo organizado por clases.
```

### Prompt v3 (Mejorado / Iterado)

```text
Mejora el codigo anterior con estas restricciones: no uses librerias externas, valida que el correo contenga @ y que la contrasena tenga al menos 8 caracteres, y muestra los mensajes con JOptionPane.
```

### Evaluación del código profesional iterado

| Qué revisar                                            | Cumple (Sí / No) |
| ------------------------------------------------------ | ---------------- |
| ¿Está escrito en Java y usa Swing?                     | Sí               |
| ¿Pide correo y contraseña?                             | Sí               |
| ¿Explica el funcionamiento antes o después del código? | Sí               |
| ¿El código está organizado en clases?                  | Sí               |
| ¿Valida los datos que ingresa el usuario?              | Sí               |
|  |

## Ejercicio 6: Del prompt basico al profesional

Main.java:

```text
package login;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame ventana = new LoginFrame();
            ventana.setVisible(true);
        });
    }
}
```

LoginService.java:

```text
package login;

public class LoginService {

    private static final String CORREO_VALIDO = "usuario@correo.com";
    private static final String PASSWORD_VALIDA = "12345678";

    public boolean autenticar(String correo, String password) {
        return CORREO_VALIDO.equals(correo)
                && PASSWORD_VALIDA.equals(password);
    }
}
```

LoginFrame.java:

```text
package login;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtCorreo;
    private JPasswordField txtPassword;
    private JButton btnIngresar;

    private LoginService loginService;

    public LoginFrame() {
        loginService = new LoginService();

        configurarVentana();
        crearComponentes();
    }

    private void configurarVentana() {
        setTitle("Inicio de sesión");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void crearComponentes() {

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta y campo de correo
        JLabel lblCorreo = new JLabel("Correo:");
        txtCorreo = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblCorreo, gbc);

        gbc.gridx = 1;
        panel.add(txtCorreo, gbc);

        // Etiqueta y campo de contraseña
        JLabel lblPassword = new JLabel("Contraseña:");
        txtPassword = new JPasswordField(20);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblPassword, gbc);

        gbc.gridx = 1;
        panel.add(txtPassword, gbc);

        // Botón
        btnIngresar = new JButton("Ingresar");

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(btnIngresar, gbc);

        add(panel);

        // Evento del botón
        btnIngresar.addActionListener(e -> iniciarSesion());

        // Permitir Enter en el campo de contraseña
        txtPassword.addActionListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {

        String correo = txtCorreo.getText().trim();
        String password = new String(txtPassword.getPassword());

        // Validar campos vacíos
        if (correo.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el correo y la contraseña.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Validar que el correo contenga @
        if (!correo.contains("@")) {
            JOptionPane.showMessageDialog(
                    this,
                    "El correo debe contener el carácter @.",
                    "Correo inválido",
                    JOptionPane.WARNING_MESSAGE
            );
            txtCorreo.requestFocus();
            return;
        }

        // Validar longitud de contraseña
        if (password.length() < 8) {
            JOptionPane.showMessageDialog(
                    this,
                    "La contraseña debe tener al menos 8 caracteres.",
                    "Contraseña inválida",
                    JOptionPane.WARNING_MESSAGE
            );
            txtPassword.requestFocus();
            return;
        }

        // Autenticar
        if (loginService.autenticar(correo, password)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Inicio de sesión correcto.",
                    "Bienvenido",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Correo o contraseña incorrectos.",
                    "Error de autenticación",
                    JOptionPane.ERROR_MESSAGE
            );

            txtPassword.setText("");
            txtPassword.requestFocus();
        }
    }
}
```
