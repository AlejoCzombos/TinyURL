# 🔗 TinyURL - Acortador de URLs ✂️

## Descripción del Proyecto
Este proyecto es un acortador de URLs que permite a los usuarios acortar URLs largas y personalizarlas con un alias único. Además, los usuarios pueden establecer una fecha de expiración y gestionar todas sus URLs de forma eficiente.

## Enlaces 
🚀 Deploy Front : https://tinyurl.alejoczombos.com.ar
🚀 Deploy Docs : https://tinyurl-back.alejoczombos.com.ar/docs
(¡El enlace lleva a la API donde puedes probar los endpoints y ver la documentación en tiempo real!) *Demora un poco en cargar al principio por el hosting*

## 🛠️ Tecnologías Utilizadas

- Backend:
    - Java ☕
    - Spring Boot 🌱
    - MongoDB 🗄️
    - Lombok 🔧
    - JUnit + Mockito 🧪
    - Swagger 📝

## 🗂️ Índice

- [Descripción del Proyecto](#descripción-del-proyecto)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Configuración del Entorno](#configuración-del-entorno)
- [Descripción de Entidades](#descripción-de-entidades)
    - [URL](#url)
- [API REST](#api-rest)
    - [Endpoints de URL](#endpoints-de-url)
        - [Get All URLs](#get-all-urls)
        - [Get URL by id or Alias](#get-url-by-key-or-alias)
        - [Create a URL](#create-url)
        - [Update a URL by id](#update-url)
        - [Delete a URL by id](#delete-url)
    - [Endpoint de Redirección](#endpoint-de-redirección)
        - [Redirect](#redirect)

## ⚙️ Configuración del Entorno

1. Clona el repositorio.
2. Abre el proyecto en tu IDE favorito 💻.
3. Configura la conexión a la base de datos MongoDB en `application.properties`
````properties
spring.data.mongodb.uri=mongodb://localhost:27017/tinyurl
````
4. Ejecuta la aplicación 🚀:
```bash
./mvnw spring-boot:run
```
6. Accede a la documentación de la API en `http://localhost:8080/docs`

## 📚 Descripción de Entidades

### URL
Descripción: Representa una URL acortada en el sistema.

Atributos:

`key`: String (ID único de la URL)
`url`: String (URL original)
`alias`: String (Alias único para la URL)
`hits`: Integer (Número de visitas)
`createdAt`: DateTime (Fecha de creación)
`expiresAt`: DateTime (Fecha de expiración, opcional)

## API REST

### 🌐 API REST - Endpoints de URL

| Método | Endpoint                  | Descripción                                  | Enlace Rápido               |
|--------|---------------------------|----------------------------------------------|-----------------------------|
| GET    | `api/urls/`               | Obtiene todas las URLs.                      | [Get All URLs](#get-all-urls)|
| GET    | `api/urls/{key_or_alias}` | Obtiene una URL por ID o Alias.              | [Get URL by key or Alias](#get-url-by-key-or-alias) |
| POST   | `api/urls/`               | Crea una nueva URL.                          | [Create URL](#create-url)    |
| PUT    | `api/urls/{key}`          | Actualiza una URL existente.                 | [Update URL](#update-url)    |
| DELETE | `api/urls/{key}`          | Elimina una URL por su ID.                   | [Delete URL](#delete-url)    |

### Get All URLs
**Endpoint:** `GET api/urls/`

**Descripción:** Obtiene todas las URLs almacenadas en la base de datos.

**Respuesta:**
- `200 OK`: Lista de URLs obtenida exitosamente.

### Get URL by Key or Alias
**Endpoint:** `GET api/urls/{key_or_alias}`

**Descripción:** Obtiene una URL por su Key o Alias.

**Parámetros:**
- `key_or_alias`: String (Key o Alias de la URL)

**Respuesta:**
- `200 OK`: URL obtenida exitosamente.
- `400 Bad Request`: Url has expired.
- `404 Not Found`: URL no encontrada.

### Create URL
**Endpoint:** `POST /`

**Descripción:** Crea una nueva URL.

**Parámetros:**
- `url`: URL (Cuerpo de la solicitud con la información de la URL)

**Respuesta:**
- `201 Created`: URL creada exitosamente.
- `400 Bad Request`: Alias ya existe o input inválido.

**Cuerpo de la solicitud**
```json
{
    "url": "String",
    "alias": "String",
    "expiresAt": "datetime"
}
```

### Update URL
**Endpoint:** `PUT /{key}`

**Descripción:** Actualiza una URL existente.

**Parámetros:**
- `key`: String (Key de la URL a actualizar)
- `url`: URLUpdate (Cuerpo de la solicitud con la nueva información de la URL)

**Respuesta:**
- `200 OK`: URL actualizada exitosamente.
- `400 Bad Request`: Alias ya existe o input inválido.
- `404 Not Found`: URL no encontrada.

**Cuerpo de la solicitud**
```json
{
  "url": "String", 
  "alias": "String",
  "expiresAt": "datetime"
}
```

### Delete URL
**Endpoint:** `DELETE /{key}`

**Descripción:** Elimina una URL por su Key.

**Parámetros:**
- `key`: String (Key de la URL)

**Respuesta:**
- `200 OK`: URL eliminada exitosamente.
- `404 Not Found`: URL no encontrada.

### Endpoint de Redirección

#### Redirect
**Endpoint:** `GET /{alias}`

**Descripción:** Redirige a la URL a partir del Alias o la key.

**Parámetros:**
- `key_or_alias`: String (Key o Alias de la URL)

**Respuesta:**
- `302 Found`: Redirección exitosa.
- `400 Bad Request`: Url has expired.
- `404 Not Found`: URL no encontrada.
