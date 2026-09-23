# Tarea: Mi prompt profesional

## Funcionalidad elegida

**Sistema de Registro y Autenticación de Usuarios con JWT (Node.js / Express)**
Se busca generar un módulo backend que permita registrar nuevos usuarios, validar sus credenciales y retornar un token JWT seguro.

---

## Version 1: prompt basico

```text
Crea un código para registrar usuarios y darles un token en Node.js.
```

- **Qué cambié:** Se partió de una instrucción simple y general sin contexto técnico ni limitaciones.
- **Por qué:** Para poner a prueba qué asume la IA por defecto cuando el requerimiento es ambiguo.
- **Qué mejoró en la respuesta:** La respuesta dio un código básico funcional, pero mezcló tecnologías (usó una base de datos en memoria), no aplicó buenas prácticas de seguridad (password en texto plano) y no estructuró la salida de forma profesional.

---

## Version 2

```text
Actúa como un desarrollador backend Senior. Diseña un módulo de registro de usuarios en Node.js utilizando Express.
El código debe cifrar la contraseña antes de guardarla y generar un JSON Web Token (JWT) al completar el registro.
Usa JavaScript estándar.
```

- **Qué cambié:** Se asignó un **Rol** (Desarrollador Backend Senior), se especificó el stack técnico (Express, bcrypt, JWT) y se agregaron reglas de seguridad implícitas (cifrado de contraseñas).
- **Por qué:** La v1 era demasiado genérica y no garantizaba buenas prácticas de seguridad ni librerías específicas.
- **Qué mejoró en la respuesta:** El código generado incluyó manejo de `bcrypt` y `jsonwebtoken`, además de una estructura de rutas básica. Sin embargo, no incluía validaciones de entradas ni un formato de respuesta estructurado.

---

## Version 3: prompt final

```text
Actúa como un Ingeniero de Software Senior especializado en Backend y Seguridad Web.
Estamos construyendo el módulo de autenticación para una API RESTful en Node.js con Express. Necesitamos asegurar que el registro de usuarios sea robusto, validando los datos de entrada antes de persistirlos.

Escribe el código completo para la ruta POST /api/register. La función debe validar que el email tenga un formato válido, cifrar la contraseña con bcrypt, generar un JWT y manejar los errores. No uses librerías externas de validación ni ORMs.

Usa funciones asíncronas (async/await) para el manejo de los datos.
Explica primero el flujo de las rutas y luego presenta el código JavaScript limpio.

```

- **Qué cambié:** Se integraron los 5 componentes estructurados dentro del prompt final (Rol, Instrucción, Contexto, Ejemplo y Formato) agregando restricciones explícitas.
- **Por qué:** Para guiar a la IA hacia una respuesta con una estructura predecible y estandarizada.
- **Qué mejoró en la respuesta:** La IA organizó la respuesta siguiendo estrictamente el orden solicitado (explicación previa seguida del bloque de código) y respetando las limitaciones técnicas.

---

## Componentes del prompt final

| Componente  | Texto de mi prompt                                                                                                                                                                                                                                 |
| ----------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Rol         | Actúa como un Ingeniero de Software Senior especializado en Backend y Seguridad Web.                                                                                                                                                               |
| Instruccion | Escribe el código completo para la ruta POST /api/register. La función debe validar que el email tenga un formato válido, cifrar la contraseña con bcrypt, generar un JWT y manejar los errores. No uses librerías externas de validación ni ORMs. |
| Contexto    | Estamos construyendo el módulo de autenticación para una API RESTful en Node.js con Express. Necesitamos asegurar que el registro de usuarios sea robusto, validando los datos de entrada antes de persistirlos.                                   |
| Ejemplo     | Usa este estilo para los metodos: getPrecio(), setPrecio(double precio).                                                                                                                                                                           |
| Formato     | Explica primero la estructura de la clase y luego presenta el codigo Java.                                                                                                                                                                         |

---

## Evaluacion del resultado

| Criterio de Evaluación                                                | Cumplimiento (Sí / No) | Observación                                                                               |
| :-------------------------------------------------------------------- | :--------------------: | :---------------------------------------------------------------------------------------- |
| ¿El resultado incluye la explicación estructural antes del código?    |         **Sí**         | Siguió estrictamente la instrucción definida en el componente Formato.                    |
| ¿Se respetó la restricción de no usar ORMs ni validadores externos?   |         **Sí**         | La validación de entrada se realizó exclusivamente mediante condicionales en código base. |
| ¿El código generado es ejecutable y funcional?                        |         **Sí**         | Mantiene la estructura de rutas y respuestas HTTP correspondientes.                       |
| ¿Se aplicaron medidas de seguridad estándar (hashing de contraseñas)? |         **Sí**         | Implementa `bcrypt` correctamente previo a la creación del token JWT.                     |

---

## Errores que evite

1. **Ser demasiado general:**
   - _Cómo lo evité:_ En lugar de solicitar un "registro de usuarios" genérico como en la v1, definí el endpoint específico (`POST /api/register`), los requisitos de validación y las librerías a evitar.
2. **No indicar el formato:**
   - _Cómo lo evité:_ Definí explícitamente la secuencia de entrega en el componente Formato, asegurando que primero se exponga la estructura explicativa y posteriormente la implementación en código.
