# Explicación breve: relación entre Modelo, Vista y Controlador

**Asignatura:** Programación Avanzada — Quiz práctico Java Swing con MVC

## Cómo se relacionan

- **Modelo (`Integrante`)**: guarda los datos de cada integrante (nombres, apellidos, repositorio, tipo de estudiante y proyecto educativo). Es una clase simple con atributos privados, constructor, getters/setters y `toString()`. No sabe nada de la interfaz.
- **Vista (`FormularioIntegrante`)**: es la ventana Swing. Muestra campos de texto, radio buttons, lista desplegable, botones y la tabla. Solo ofrece métodos para leer lo que el usuario escribió (`getNombres()`, etc.) y para mostrar resultados (`mostrarRegistros()`, `mostrarMensaje()`). No valida ni guarda nada.
- **Controlador (`IntegranteController`)**: recibe la vista, escucha los clics de los botones, valida los datos, crea el objeto `Integrante`, lo guarda en el `ArrayList<Integrante>` y le pide a la vista que muestre la tabla actualizada.
- **`Main`**: crea la vista, le pasa la vista al controlador y hace visible la ventana.

## Flujo al pulsar «Registrar»

1. El usuario llena el formulario y pulsa **Registrar** (Vista).
2. El controlador detecta el evento y lee los valores desde la vista.
3. El controlador valida: campos obligatorios y formato de URL.
4. Si hay error, pide a la vista mostrar el mensaje. Si es válido, crea un `Integrante` (Modelo) y lo agrega al `ArrayList`.
5. El controlador pide a la vista mostrar la confirmación, limpiar el formulario y refrescar la tabla.

## Preguntas de reflexión

**1. ¿Cuál es la responsabilidad de la clase Integrante dentro del modelo?**
Representar los datos de un integrante y protegerlos mediante encapsulamiento (atributos privados con getters/setters). Es la unidad de información que se almacena y consulta.

**2. ¿Por qué los eventos del botón Registrar deben coordinarse desde el controlador?**
Porque el controlador es quien decide qué hacer con la acción del usuario: validar, crear el objeto, almacenarlo y avisar a la vista. Si la vista lo hiciera, mezclaría diseño con lógica y sería difícil de probar o cambiar.

**3. ¿Qué ventaja ofrece ArrayList frente a declarar una cantidad fija de variables para guardar integrantes?**
El `ArrayList` crece dinámicamente, permite guardar tantos integrantes como se necesiten, se recorre fácilmente con un ciclo (por ejemplo, para llenar la tabla) y evita crear variables repetidas como `integrante1`, `integrante2`, etc.

**4. ¿Cómo evita MVC que la lógica del sistema quede mezclada con el diseño de la interfaz?**
Cada capa tiene una única responsabilidad: la vista solo dibuja y entrega valores, el controlador contiene las validaciones y la coordinación, y el modelo solo contiene los datos. Así se puede cambiar el diseño visual sin tocar las reglas, y modificar las reglas sin rehacer la interfaz.
