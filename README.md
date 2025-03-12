# Tarea 3. Desarrollo de una Aplicación Móvil Nativa con Consumo de API REST
Este proyecto consistió en la implementación de dos ejercicios prácticos utilizando Android Studio, donde se realizaron conexiones a servicios REST, configurando los permisos necesarios, enviando solicitudes HTTP y procesando respuestas en formato JSON.
## Ejercicio 1: Creación de un Backend Básico y Conexión con Android
En este primer ejercicio, se creó un servicio REST sencillo y se desarrolló una aplicación Android para consumir dicho servicio. Los archivos correspondientes a este ejercicio se encuentran en el directorio AplicacionREST.
### BackEnd
El BackEnd del ejercicio se realizo con el framework Spring Boot y expone un endpoint sencillo con el mensaje "Hola mundo desde Spring Boot" en modo de un JSON. El proyecto realizado con Spring Boot se encuentra en el directorio llamado Backend y solo necesita ser ejecutado para su funcionamiento.

El proyecto esta configurado para que el servicio REST:
- Se ejecute en el puerto 8080
- Devuelva un JSON con un solo dato llamado "mensaje"
### Aplicacion Android
La aplicacion hace uso de la libreria HttpURLConnection la cual permite la conexion con el servicio REST a traves de una peticion HTTP de tipo GET hacia el localhost (10.0.2.2 en el emulador de Android Studio) a traves del puerto 8080.

La aplicacion consiste en un simple TextView que permite ver el mensaje recibido desde el JSON. Solo es necesario ejecutar la funcion para ver el funcionamiento de la conexion y los datos que recibe ya que no se realiza ninguna accion más.
## Ejercicio 2: Consumo de una API Pública
La aplicacion desarrollada para el segundo ejercicio es una aplicacion que permite visualizar y buscar libros a traves de la libreria Open Library API de la cual se obtienen datos y portadas de los libros que se deseen buscar.

La aplicacion consiste en una barra de busqueda que permite ingresar texto para buscar los libros deseados y una lista de elementos que permite ver los resultados entregados por la API.
### Uso
Para realizar una busqueda solo se necesita dar click en la barra de busqueda, escribir el nombre del libro a buscar o autor y dar enter. La aplicacion posteriormente mostrara los resultados retornados por la API.
