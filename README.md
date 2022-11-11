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

## Servicios de autenticación /api/v0/authentication

### Registrar usuario /signup POST
---
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
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| CREATED        | 201   |
    
  URL de ejemplo
  >http://localhost:8080/api/v0/authentication/singup/

### Obtener Token de usuario /login POST
---
  Datos de entrada en el body
  >```json
  >{
  >    "email":"mail@mail.com",
  >    "password":"XXXXXXXXX"
  >}
  >```
    
  Respuestas
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| OK             | 200   |
  >| NotFound       | 201   |
    
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
 ---
   Datos de entrada como parámetros de la request
   
  > | Parámetro | Descripción                      | Obligatorio |
  > | :--------------- | :--------------- | :--------------- |
  > | page      | Página a visualizar            | No          |
  > | name      | Busca por nombre del usuario, se puede especificar una expresión regular  | No    |
  > | lastname  | Busca por los apellidos del usuario, se puede especificar una expresión regular | No    |
  > | email     | Busca por el correo del usuario | No    |
 
 Respuestas
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| OK             | 200   |
  >| NotFound       | 401   |
    
  URLs de ejemplo
  >http://localhost:8080/api/v0/users/
  >
  >http://localhost:8080/api/v0/users/?page=1&size=10
  >
  >http://localhost:8080/api/v0/users/?page=2&size=15&name=nombre
  >
  >http://localhost:8080/api/v0/users/?name=nombreUsuario&lastname=apellidos

 Datos de salida
  >```json
  >{
  >  "currentPage": 1,
  >  "totalItemsPage": 3,
  >  "totalPages": 1,
  >  "totalItems": 3,
  >  "users": [
  >      {
  >          "id": "6346ad5c11e0803c6675d530",
  >          "name": "lortega",
  >          "lastName": "lortega",
  >          "email": "lort@birt.eus"
  >      },
  >      {
  >          "id": "634dd15192a0cc18d740d7fb",
  >          "name": "Ramiro",
  >          "lastName": "",
  >          "email": "rimb@birt.eus"
  >      },
  >      {
  >          "id": "6350291da2891d6129df3bc1",
  >          "name": "Iñaki",
  >          "lastName": "",
  >          "email": "inlirt@birt.eus"
  >      }
  >  ]
  >}
  >```
  
 ### Obtener un usuario pasandole un id /id/{id} GET
 ---
 Respuestas
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| OK             | 200   |
  >| NotFound       | 401   |
    
  URL de ejemplo
  >http://localhost:8080/api/v0/users/id/6346b36111e0803c6675d541

 Datos de salida
  >```json
  >{
  >  "id": "6346ad5c11e0803c6675d530",
  >  "name": "lort",
  >  "lastName": "lorte",
  >  "email": "lort@birt.eus"
  >}
  >```

 ### Obtener un usuario pasandole el mail /email/{email} GET
 ---
 Respuestas
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| OK             | 200   |
  >| NotFound       | 401   |
    
  URL de ejemplo
  >http://localhost:8080/api/v0/users/email/lort@birt.eus

 Datos de salida
  >```json
  >{
  >  "id": "6346ad5c11e0803c6675d530",
  >  "name": "lort",
  >  "lastName": "lorte",
  >  "email": "lort@birt.eus"
  >}
  >```

 ### Actuliza los datos de un usuario, solo name, lastName y email /{id} PUT
 ---
  Datos de entrada en el body
  >```json
  >{
  >    "name":"nombre",
  >    "lastName":"apellidos",
  >    "email":"mail@mail.com"
  >}
  >```
    
  Respuesta
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| CREATED        | 201   |
  >| NotFound       | 401   |
  
  URL de ejemplo
  >http://localhost:8080/api/v0/users/6346b36111e0803c6675d541

Datos de salida
  >```json
  >{
  >  "id": "6346b36111e0803c6675d541",
  >  "name": "nombre",
  >  "lastName": "apellidos",
  >  "email": "mail@mail.com"
  >}
  >```

 ### Borra un usuario pasandole su id /{id} DEL
 ---

## Servicio de itinerarios /api/v0/itinerarys

## Servicio de localizaciones api/v0/geolocations
