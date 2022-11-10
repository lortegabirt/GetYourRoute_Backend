# Get Your Route Backend

Applicación backend para el **Proyecto Final** de DAM

### Levantar la aplicación
Se puede levantar la aplicación desde la terminal ejecutando el siguiente comando 
desde la carpeta del proyecto

`./mvnw spring-boot:run`

Pulsa `Ctrl-C` para parar la aplicación

### Autores
- Luis Ángel Ortega San Pedro
- Iñaki Angulo Tarancon
- Ramiro Iglesias Munitiz

## Servicio de autenticación /api/v0/authentication
### Registrar usuario /signup POST

  Datos de entrada en el body
  >```json
  >{
  >    "name":"nombre usuario",
  >    "lastName":"apellidos usuario",
  >    "email":"mail@mail.com",
  >    "password":"XXXXXXXXX"
  >}
  >```
    
  Respuesta
  >ResponseStatus CREATED 201
    
  URL de ejemplo
  >http://localhost:8080/api/v0/authentication/singup/

### Obtener Token de usuario /login POST
  Datos de entrada en el body
  >```json
  >{
  >    "email":"mail@mail.com",
  >    "password":"XXXXXXXXX"
  >}
  >```
    
  Respuestas
  >ResponseStatus OK o NotFound 201
    
  URL de ejemplo
  >http://localhost:8080/api/v0/authentication/singup/

  Datos de salida
  >```json
  >{
  >    "token":   "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsb3J0ZWdhQGJpcnQuZXVzIiwiaWF0IjoxNjY4MDk2MzkwLCJleHAiOjE2NjgxMzk1OTAsIm5hbWUiOiJsb3J0ZWdhIn0.Z35qlb3L0FS1WbNqHyt7UyoDB2qsIgx4_7ei_Ybzyw0"
  >}
  >```

## Servicio de usuarios /api/v0/users
 ### Obtener todos los usuarios / GET
   Datos de entrada como parámetros de la request
   
  > | Parámetro | Descripción                      | Obligatorio |
  > | :--------------- | :--------------- | :--------------- |
  > | page      | Página a visualizar              | No          |
  > | name      | Busca por nombre del usuario, se puede especificar una expresión regular  | No    |
  > | lastname  | Busca por los apellidos del usuario, se puede especificar una expresión regular | No    |
  > | email     | Busca por el correo del usuario | No    |
 
 Respuestas
  >ResponseStatus OK 200 o NotFound 404
    
  URLs de ejemplo
  >http://localhost:8080/api/v0/users/
  >http://localhost:8080/api/v0/users/?page=1&size=10
  >http://localhost:8080/api/v0/users/?page=2&size=15&name=nombre
  >http://localhost:8080/api/v0/users/?name=nombreUsuario&lastname=apellidos
  
 ### Obtener un usuario pasandole un id /id/{id} GET

 ### Obtener un usuario pasandole el mail /email/{email} GET

 ### Actuliza los datos de un usuario, solo name, lastName y email /{id} PUT

 ### Boora un usuario pasandole su id /{id} DEL

## Servicio de itinerarios /api/v0/itinerarys

## Servicio de localizaciones api/v0/geolocations
