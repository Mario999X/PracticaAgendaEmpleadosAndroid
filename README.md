# --- AGENDA DE EMPLEADOS ANDROID ---

Se trata de una aplicación Android, pensada para manejar una lista de empleados.
Se emplea una BBDD Room para almacenar los datos.
Cada empleado tiene las siguientes características:

    - ID (auto-generado).
    - Nombre.
    - Departamento.

## La aplicación ofrece:

    1. Explicación básica de como montar la BBDD.
    2. Un menú desplegable, con acceso a dos fragmentos.
        3. Fragmento que permite añadir un empleado, eliminar a uno basándose en su ID, o eliminarlos a todos.
        4. Fragmento que muestra una lista, se permite cambiar el orden de los empleados (ID y Alfabéticamente), 
        y filtrar por departamento. 
        Además, se incluye una vista detalle (activity) al pulsar sobre los elementos de la misma.
    5. Layouts diseñados tanto para Portrait y Landscape.
