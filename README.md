# Plataforma Vapor API

Esta es una API REST desarrollada con Spring Boot 3 diseñada para la gestion de un catalogo de videojuegos, administracion de usuarios, control de sus respectivas bibliotecas y la consulta de perfiles reales de la plataforma Steam mediante su API externa oficial.

# Entornos y Aplicaciones de Uso

La API esta disenada bajo una arquitectura estandar que le permite conectarse y ser consumida por multiples tipos de aplicaciones cliente:

1. Herramientas de Testeo de APIs (Postman / Insomnia): Ideal para interactuar de forma inmediata con los endpoints, enviar cuerpos JSON y validar las respuestas del servidor en entorno de desarrollo.
2. Aplicaciones Frontend (Web / Movil): Puede ser consumida por frameworks modernos como React, Angular, Vue o aplicaciones moviles nativas mediante peticiones HTTP (Fetch, Axios) para renderizar la tienda de juegos y perfiles de usuario.
3. Integraciones de Terceros: Capaz de servir como puente de datos o microservicio para otras aplicaciones del ecosistema que requieran consultar informacion unificada de videojuegos o datos publicos de Steam.

# Mapeo de Endpoints (Documentacion de la API)

El servidor local corre por defecto en la direccion raiz http://localhost:8080.

# 1. Manejo de Juegos
Permite la administracion total del catalogo de videojuegos disponibles en la plataforma.

| Metodo | Endpoint | Descripcion |
| :--- | :--- | :--- |
| GET | /juegos | Muestra la lista completa de videojuegos registrados. |
| GET | /juegos/{id} | Busca y retorna un juego especifico ingresando su ID numerico. |
| POST | /juegos | Registra un nuevo videojuego en el sistema. |
| PUT | /juegos/{id} | Actualiza los datos de un juego existente (El ID no es modificable). |
| DELETE | /juegos/{id} | Elimina de forma permanente un juego del catalogo segun su ID. |

* Ejemplo de JSON para POST / PUT (Juegos):
{
    "Id": 0,
    "NombreJuego": "Goat Simulator",
    "FechaLanzamiento": "2013-05-23",
    "Calificacion": 100,
    "Descripcion": "El mejor juego de la historia"
}

# 2. Manejo de Usuarios
Gestion de las cuentas de usuario locales dentro del ecosistema de la plataforma.

| Metodo | Endpoint | Descripcion |
| :--- | :--- | :--- |
| GET | /usuarios | Recupera la lista completa de usuarios del sistema. |
| GET | /usuarios/{id} | Busca un usuario en especifico a traves de su ID. |
| POST | /usuarios | Agrega un nuevo usuario a la plataforma. |
| PUT | /usuarios/{id} | Modifica la informacion del usuario correspondiente al ID enviado (El ID no es modificable). |
| DELETE | /usuarios/{id} | Da de baja y elimina a un usuario del sistema por su ID. |

* Ejemplo de JSON para POST / PUT (Usuarios):
{
    "Id": 0,
    "Nombre": "Nombre1",
    "Correo": "Correo@gmail.com",
    "Edad": 18
}

# 3. Manejo de Bibliotecas
Controla las relaciones de juegos que posee cada usuario dentro de su cuenta personal.

| Metodo | Endpoint | Descripcion |
| :--- | :--- | :--- |
| GET | /usuarios/{userId}/biblioteca | Retorna el listado de juegos adquiridos por el usuario especificado. |
| POST | /usuarios/{userId}/biblioteca/{juegoId} | Vincula de forma permanente un juego del catalogo a la biblioteca de ese usuario. |

* Ejemplo de URL de peticion para POST (Bibliotecas):

http://localhost:8080/usuarios/1/biblioteca/2

# 4. Manejo DTO Steam
Mapeo directo y externo con los servidores de Valve para consultar informacion publica de cuentas reales de Steam en tiempo real.

| Metodo | Endpoint | Descripcion |
| :--- | :--- | :--- |
| GET | /Perfil_Steam/{steamId} | Retorna un objeto DTO procesado con el resumen publico del jugador (apodo, avatar, estado, etc.) consumido mediante WebClient. |

* Ejemplo de URL de peticion para GET (Manejo DTO Steam):

http://localhost:8080/Perfil_Steam/76561198975528727

# Clonar el repositorio
git clone https://github.com/PitoPaez/Vapor.git
cd plataforma