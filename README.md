# Registro de Integrantes — Java Swing con MVC

Aplicación de escritorio en Java que registra a los integrantes de proyectos educativos, valida los datos y los guarda en memoria (`ArrayList<Integrante>`), aplicando la arquitectura **Modelo–Vista–Controlador**.



## Objetivo

Diseñar e implementar un formulario con Java Swing, programación orientada a objetos y MVC que capture, valide, almacene y consulte integrantes durante la ejecución del programa (sin base de datos).

## Requisitos

- **JDK 11 o superior** (probado con JDK 21).
- Un entorno con interfaz gráfica (Windows, macOS o Linux con escritorio).
- Opcional: un IDE (NetBeans, Eclipse, IntelliJ IDEA) o solo la terminal.

## Estructura del proyecto

```
RegistroIntegrantes/
├── src/
│   ├── Main.java                              # Punto de entrada
│   ├── model/Integrante.java                  # MODELO
│   ├── view/FormularioIntegrante.java         # VISTA
│   └── controller/IntegranteController.java   # CONTROLADOR
├── docs/
│   ├── captura_ejecucion.png                  # Evidencia de ejecución
│   └── Explicacion_MVC.md                     # Explicación MVC y preguntas de reflexión
└── README.md
```

## Pasos de ejecución

### Desde la terminal (en la carpeta `RegistroIntegrantes`)

**Linux / macOS**
```bash
mkdir -p out
javac -encoding UTF-8 -d out src/Main.java src/model/*.java src/view/*.java src/controller/*.java
java -cp out Main
```

**Windows (CMD)**
```bat
mkdir out
javac -encoding UTF-8 -d out src\Main.java src\model\*.java src\view\*.java src\controller\*.java
java -cp out Main
```

### Desde un IDE

1. Crear un proyecto Java nuevo y copiar la carpeta `src` dentro del proyecto.
2. Ejecutar la clase `Main`.

## Cómo se usa

1. Escribe **Nombres**, **Apellidos** y la **URL del repositorio** (ej. `https://github.com/usuario/proyecto`).
2. Elige **Nuevo** o **Antiguo** (solo una opción).
3. Elige el **Proyecto educativo** en la lista desplegable.
4. Pulsa **Registrar**: si todo es válido aparece un mensaje de confirmación y el integrante se agrega a la tabla.
5. **Limpiar** vacía el formulario y **Consultar registros** actualiza la tabla con lo almacenado.

## Validaciones

- Nombres, Apellidos y Repositorio son obligatorios.
- La URL debe tener formato básico válido: `http://` o `https://`, un dominio y, opcionalmente, una ruta.
- Si hay un error se muestra un mensaje y no se guarda el registro.

## Cómo funciona (MVC)

| Capa | Clase | Responsabilidad |
|------|-------|-----------------|
| Modelo | `Integrante` | Atributos encapsulados, constructor, getters/setters y `toString()`. |
| Vista | `FormularioIntegrante` | Componentes Swing, distribución visual y acceso a los valores del formulario. |
| Controlador | `IntegranteController` | Atiende los botones, valida, crea el `Integrante`, lo guarda en el `ArrayList` y actualiza la tabla. |
| Main | `Main` | Crea la vista, crea el controlador y muestra la ventana. |

Más detalle y respuestas a las preguntas de reflexión en [`docs/Explicacion_MVC.md`](docs/Explicacion_MVC.md).
