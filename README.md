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

------
## Servicios REST
 ### Servicios de autenticación /api/v0/authentication
  #### Registrar usuario /signup POST</summary>
  #### Obtener Token de usuario /login POST
 ### Servicio de usuarios /api/v0/users
  #### Obtener todos los usuarios / GET  
------
## Servicios de autenticación /api/v0/authentication

### Registrar usuario /signup POST</summary>  
---
  <details>
  <summary>Datos de entrada en el body</summary>
  
  >```json
  >{
  >    "name":"nombre usuario",
  >    "lastName":"apellidos usuario",
  >    "email":"mail@mail.com",
  >    "password":"XXXXXXXXX"
  >}
  >```
  
  </details>
  
<details>
<summary>Respuesta</summary>
  
>| ResponseStatus | Valor | 
>|:-------------- |:----- |
>| CREATED        | 201   |

</details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0/authentication/singup/
  
### Obtener Token de usuario /login POST
---
  <details>
  <summary>Datos de entrada en el body</summary>
  
  >```json
  >{
  >    "email":"mail@mail.com",
  >    "password":"XXXXXXXXX"
  >}
  >```
  
  </details>
  
  <details>
  <summary>Respuestas</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| OK             | 200   |
  
  </details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0/authentication/login/

  <details>
  <summary>Datos de salida</summary>
  
  >```json
  >{
  >    "token":   "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsb3J0ZWdhQGJpcnQuZXVzIiwiaWF0IjoxNjY4MDk2MzkwLCJleHAiOjE2NjgxMzk1OTAsIm5hbWUiOiJsb3J0ZWdhIn0.Z35qlb3L0FS1WbNqHyt7UyoDB2qsIgx4_7ei_Ybzyw0"
  >}
  >```

  </details>
  
## Servicio de usuarios /api/v0/users
 ### Obtener todos los usuarios / GET
 ---
  <details>
  <summary>Datos de entrada como parámetros de la request</summary>
   
  > | Parámetro | Descripción                      | Obligatorio |
  > | :--------------- | :--------------- | :--------------- |
  > | page      | Página a visualizar            | No          |
  > | size      | Número de elementos de la página            | No          |
  > | name      | Busca por nombre del usuario, se puede especificar una expresión regular  | No    |
  > | lastname  | Busca por los apellidos del usuario, se puede especificar una expresión regular | No    |
  > | email     | Busca por el correo del usuario | No    |
 
 </details>

<details> 
  <summary>Respuestas</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| OK             | 200   |
  >| NOT_FOUND      | 404   |

</details>
  
  URLs de ejemplo
  >http://localhost:8080/api/v0/users/
  >
  >http://localhost:8080/api/v0/users/?page=1&size=10
  >
  >http://localhost:8080/api/v0/users/?page=2&size=15&name=nombre
  >
  >http://localhost:8080/api/v0/users/?name=nombreUsuario&lastname=apellidos

<details>  
  <summary>Datos de salida</summary>
  
  >```json
  >{
  >  "currentPage": 1,
  >  "totalItemsPage": 3,
  >  "totalPages": 1,
  >  "totalItems": 3,
  >  "content": [
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
  
  </details>
  
 ### Obtener un usuario pasandole un id /id/{id} GET
 ---
 
 <details>
  <summary>Respuestas</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| OK             | 200   |
  >| NOT_FOUND     | 404   |
  
 </details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0/users/id/6346b36111e0803c6675d541

<details>
  <summary>Datos de salida</summary>

  >```json
  >{
  >  "id": "6346ad5c11e0803c6675d530",
  >  "name": "lort",
  >  "lastName": "lorte",
  >  "email": "lort@birt.eus"
  >}
  >```

</details>

 ### Obtener un usuario pasandole el mail /email/{email} GET
 ---
 <details>
  <summary>Respuestas</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| OK             | 200   |
  >| NOT_FOUND     | 404   |
  
 </details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0/users/email/lort@birt.eus

<details>
  <summary>Datos de salida</summary>
  
  >```json
  >{
  >  "id": "6346ad5c11e0803c6675d530",
  >  "name": "lort",
  >  "lastName": "lorte",
  >  "email": "lort@birt.eus"
  >}
  >```

</details>
  
 ### Actualiza los datos de un usuario, solo name, lastName y email /{id} PUT
 ---
  
 <details>
  <summary>Datos de entrada en el body</summary>
  
  >```json
  >{
  >    "name":"nombre",
  >    "lastName":"apellidos",
  >    "email":"mail@mail.com"
  >}
  >```
  
 </details>
  
 <details>
  <summary>Respuesta</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| CREATED        | 201   |
  >| NOT_FOUND      | 404   |
  
  </details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0/users/6346b36111e0803c6675d541

<details>
  <summary>Datos de salida</summary>
  
  >```json
  >{
  >  "id": "6346b36111e0803c6675d541",
  >  "name": "nombre",
  >  "lastName": "apellidos",
  >  "email": "mail@mail.com"
  >}
  >```

 </details> 
  
 ### Borra un usuario pasandole su id /{id} DEL
 ---
 
<details>
  <summary>Respuestas</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| NO_CONTENT     | 204   |
  >| NOT_FOUND      | 404   |
  
</details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0/users/6346b36111e0803c6675d54
  
## Servicio de itinerarios /api/v0/itinerarys
### Obtener todos los itinerarios / GET
---

 <details>
  <summary>Datos de entrada como parámetros de la request</summary>
   
  > | Parámetro | Descripción                      | Obligatorio |
  > | :--------------- | :--------------- | :--------------- |
  > | page      | Página a visualizar            | No          |
  > | size      | Número de elementos de la página            | No          |
  > | userId    | Id del usuario  | No    |
  > | beginDate | Fecha de inicio en UTC, formato DateTimeFormat.ISO.DATE_TIME 2022-10-14T14:29:06.612 | No    |
  > | endDate   | Fecha fin en UTC, formato DateTimeFormat.ISO.DATE_TIME 2022-10-14T14:29:06.612 | No    |
  > | name     | Nombre del itinerariom, se admite expresión regular | No    |
 
 </details>

<details> 
  <summary>Respuestas</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| OK             | 200   |
  >| NOT_FOUND      | 404   |

</details>

URLs de ejemplo
  >http://localhost:8080/api/v0/itinerarys/
  >
  >http://localhost:8080/api/v0/itinerarys/?page=1&size=10
  >
  >http://localhost:8080/api/v0/itinerarys/?page=2&size=15&beginDate=2022-10-14T14:29:06.612&endDate=2022-14-19T10:29:06.612
  >
  >http://localhost:8080/api/v0/itinerarys/?name=nombreItinerario

<details>  
  <summary>Datos de salida</summary>
  
  >```json
  >{
  >  "currentPage": 1,
  >  "totalItemsPage": 2,
  >  "totalPages": 1,
  >  "totalItems": 2,
  >  "content": [
  >      {
  >          "id": "634956123825654bfcb165ab",
  >          "beginDate": "2022-10-14T14:29:06.612",
  >          "endDate": "2022-10-14T14:29:06.613",
  >          "name": "Itinerario",
  >          "description": "Lorem ipsum dolor sit amet, consectetur.",
  >          "idUser": "6346ad5c11e0803c6675d530",
  >          "user": {
  >              "id": "6346ad5c11e0803c6675d530",
  >              "name": "lort",
  >              "lastName": "lort",
  >              "email": "lort@birt.eus"
  >          }
  >      },
  >      {
  >          "id": "634956123825654bfcb165ac",
  >          "beginDate": "2022-10-14T14:29:06.705",
  >          "endDate": "2022-10-14T14:29:06.705",
  >          "name": "Itinerario2",
  >          "description": "descripcion2",
  >          "idUser": "6346ad5c11e0803c6675d530",
  >          "user": {
  >              "id": "6346ad5c11e0803c6675d530",
  >              "name": "lort",
  >              "lastName": "lort",
  >              "email": "lort@birt.eus"
  >          }
  >      }
  >  ]
  >}
  >```
  
  </details>

### Obtener un itinerario pasandole un id /id/{id} GET
---

 <details>
  <summary>Respuestas</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| OK             | 200   |
  >| NOT_FOUND      | 404   |
  
 </details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0/itinerarys/id/634956123825654bfcb165ab

<details>
  <summary>Datos de salida</summary>
  
  >```json
  >{
  >   "id": "634956123825654bfcb165ab",
  >  "beginDate": "2022-10-14T14:29:06.612",
  >  "endDate": "2022-10-14T14:29:06.613",
  >  "name": "Itinerario",
  >  "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
  >  "idUser": "6346ad5c11e0803c6675d530",
  >  "user": {
  >      "id": "6346ad5c11e0803c6675d530",
  >      "name": "lort",
  >      "lastName": "lort",
  >      "email": "lort@birt.eus"
  >  }
  >}
  >```
  
 </details>
 
### Obtener los itinerarios de un usuario, pasando el id del usuario /id/{id} GET
---

 <details>
  <summary>Respuestas</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| OK             | 200   |
  >| NOT_FOUND      | 404   |
  
 </details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0/itinerarys/userID/634956123825654bfcb165ab

<details>
  <summary>Datos de salida</summary>
  
  >```json
  >[
  >  {
  >     "id": "634956123825654bfcb165ab",
  >     "beginDate": "2022-10-14T14:29:06.612",
  >     "endDate": "2022-10-14T14:29:06.613",
  >     "name": "Itinerario",
  >     "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
  >     "idUser": "6346ad5c11e0803c6675d530",
  >     "user": {
  >          "id": "6346ad5c11e0803c6675d530",
  >          "name": "lort",
  >          "lastName": "lort",
  >          "email": "lort@birt.eus"
  >      }
  >  }
  >]
  >```
  
 </details>
 
### Obtener los itinerarios por nombre,  /mane/{name} GET
---

<details>
  <summary>Respuestas</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| OK             | 200   |
  >| NOT_FOUND      | 404   |
  
 </details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0/itinerarys/name/Itinerario

<details>
  <summary>Datos de salida</summary>
  
  >```json
  >[
  >  {
  >      "id": "634956123825654bfcb165ab",
  >      "beginDate": "2022-10-14T14:29:06.612",
  >      "endDate": "2022-10-14T14:29:06.613",
  >      "name": "Itinerario",
  >      "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
  >      "idUser": "6346ad5c11e0803c6675d530",
  >      "user": {
  >          "id": "6346ad5c11e0803c6675d530",
  >          "name": "lort",
  >          "lastName": "lort",
  >          "email": "lort@birt.eus"
  >      }
  >  }
  >]  
  >```
  
  </details>
  
### Crea un itinerario / POST
---

 <details>
  <summary>Datos de entrada en el body</summary>
  
  >```json
  >{
  >  "beginDate" : "2022-10-14T14:33:24.226",
  >  "endDate" : "2022-10-18T19:18:24.226",
  >  "name" : "Itinerario3",
  >  "description" : "Desc Itinerario 3",
  >  "user": {
  >              "id": "6346b36111e0803c6675d541"
  >          }
  >}
  >```
  
 </details>
  
 <details>
  <summary>Respuesta</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| CREATED        | 201   |
  
  </details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0//itinerarys/

<details>
  <summary>Datos de salida</summary>
  
  >```json
  >{
  >  "id": "637273ffb171f03926a91bb7",
  >  "beginDate": "2022-10-14T14:33:24.226",
  >  "endDate": "2022-10-18T19:18:24.226",
  >  "name": "Itinerario3",
  >  "description": "Desc Itinerario 3",
  >  "idUser": "6346b36111e0803c6675d541",
  >  "user": {
  >      "id": "6346b36111e0803c6675d541",
  >      "name": null,
  >      "lastName": null,
  >      "email": null
  >  }
  >}
  >```

 </details> 

### Actualiza un itinerario pasandole un id /{id} PUT
---
 <details>
  <summary>Datos de entrada en el body</summary>
  
  >```json
  >{
  >  "beginDate" : "2022-10-14T14:33:24.226",
  >  "endDate" : "2022-10-18T19:18:24.226",
  >  "name" : "Mod Itinerario3",
  >  "description" : "Mod Desc Itinerario 3",
  >  "user": {
  >              "id": "6346b36111e0803c6675d541"
  >          }
  >}
  >```
  
 </details>
  
 <details>
  <summary>Respuesta</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| CREATED        | 201   |
  >| NOT_FOUND      | 404   |
  
  </details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0//itinerarys/637273ffb171f03926a91bb7

<details>
  <summary>Datos de salida</summary>
  
  >```json
  >{
  >  "id": "637273ffb171f03926a91bb7",
  >  "beginDate": "2022-10-14T14:33:24.226",
  >  "endDate": "2022-10-18T19:18:24.226",
  >  "name": "Mod Itinerario3",
  >  "description": "Mod Desc Itinerario 3",
  >  "idUser": "6346b36111e0803c6675d541",
  >  "user": {
  >      "id": "6346b36111e0803c6675d541",
  >      "name": null,
  >      "lastName": null,
  >      "email": null
  >  }
  >}
  >```

 </details> 


### Borra un itinerario pasandole un id /{id} DELETE
---

<details>
  <summary>Respuestas</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| NO_CONTENT     | 204   |
  >| NOT_FOUND      | 404   |
  
</details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0/itinerarys/637273ffb171f03926a91bb7

### Borra los itinerarios de un suarios pasandole el id de usuario /delelteitineraryuser/{userid} DELETE
---

<details>
  <summary>Respuestas</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| NO_CONTENT     | 204   |
  >| NOT_FOUND      | 404   |
  
</details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0/itinerarys/delelteitineraryuser/6346b36111e0803c6675d541


## Servicio de localizaciones api/v0/geolocations

### Obtener todas las localizaciones / GET
---

 <details>
  <summary>Datos de entrada como parámetros de la request</summary>
   
  > | Parámetro | Descripción                      | Obligatorio |
  > | :--------------- | :--------------- | :--------------- |
  > | page      | Página a visualizar            | No          |
  > | size      | Número de elementos de la página            | No          |
  > | beginDate | Fecha de inicio en UTC, formato DateTimeFormat.ISO.DATE_TIME 2022-10-14T14:29:06.612 | No    |
  > | endDate   | Fecha fin en UTC, formato DateTimeFormat.ISO.DATE_TIME 2022-10-14T14:29:06.612 | No    |
  > | itineraryId | Id del itinerario  | No    |
  > | userId    | Id del usuario  | No    |

 </details>

<details> 
  <summary>Respuestas</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| OK             | 200   |
  >| NOT_FOUND      | 404   |

</details>

URLs de ejemplo
  >http://localhost:8080/api/v0/geolocations/
  >
  >http://localhost:8080/api/v0/geolocations/?page=1&size=10
  >
  >http://localhost:8080/api/v0/geolocations/?page=2&size=15&beginDate=2022-10-14T14:29:06.612&endDate=2022-14-19T10:29:06.612
  >
  >http://localhost:8080/api/v0/geolocations/?userId=6346b36111e0803c6675d541

<details>  
  <summary>Datos de salida</summary>
  
  >```json
  >{
  >  "currentPage": 0,
  >  "totalItemsPage": 3,
  >  "totalPages": 5,
  >  "totalItems": 13,
  >  "content": [
  >      {
  >          "id": "63782555e7e5ee0f8a753981",
  >          "timestamp": "2022-11-14T09:41:01.178",
  >          "itineraryId": "636b4b7cbc36ff41b4c06bdb",
  >          "userId": "6346b36111e0803c6675d541",
  >          "location": {
  >              "type": "Point",
  >              "coordinates": [
  >                  42.082716,
  >                  -2.082716
  >              ]
  >          }
  >      },
  >      {
  >          "id": "637824adf7d93c6235295741",
  >          "timestamp": "2022-11-19T01:34:53.364",
  >          "itineraryId": "637824adf7d93c623529573e",
  >          "userId": "6346b36111e0803c6675d541",
  >          "location": {
  >              "type": "Point",
  >              "coordinates": [
  >                 42.082726,
  >                 -2.082816
  >              ]
  >          }
  >      },
  >      {
  >          "id": "637824adf7d93c6235295742",
  >          "timestamp": "2022-11-19T01:34:53.439",
  >          "itineraryId": "637824adf7d93c623529573e",
  >          "userId": "6346b36111e0803c6675d541",
  >          "location": {
  >              "type": "Point",
  >              "coordinates": [
  >                  42.904234,
  >                  -2.904234
  >              ]
  >          }
  >      }
  >  ]
  >}
  >```
  
  </details>

### Obtener una localización pasandole un id /id/{id} GET
---

 <details>
  <summary>Respuestas</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| OK             | 200   |
  >| NOT_FOUND      | 404   |
  
 </details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0/geolocations/id/63782555e7e5ee0f8a753981

<details>
  <summary>Datos de salida</summary>
  
  >```json
  >{
  >  "id": "63782555e7e5ee0f8a753981",
  >  "timestamp": "2022-11-14T09:41:01.178",
  >  "itineraryId": "636b4b7cbc36ff41b4c06bdb",
  >  "userId": "6346b36111e0803c6675d541",
  >  "location": {
  >      "type": "Point",
  >      "coordinates": [
  >          42.082716,
  >          -2.082716
  >      ]
  >  }
  >}
  >```
  
 </details>

### Obtener una localización pasandole un id /id/{id} GET
---

 <details>
  <summary>Respuestas</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| OK             | 200   |
  >| NOT_FOUND      | 404   |
  
 </details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0/geolocations/id/63782555e7e5ee0f8a753981

<details>
  <summary>Datos de salida</summary>
  
  >```json
  >{
  >  "id": "63782555e7e5ee0f8a753981",
  >  "timestamp": "2022-11-14T09:41:01.178",
  >  "itineraryId": "636b4b7cbc36ff41b4c06bdb",
  >  "userId": "6346b36111e0803c6675d541",
  >  "location": {
  >      "type": "Point",
  >      "coordinates": [
  >          42.082716,
  >          -2.082716
  >      ]
  >  }
  >}
  >```
  
 </details>

### Obtener las localizaciones de un itinerario pasandole su id /itineraryID/{itineraryID} GET
---

 <details>
  <summary>Respuestas</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| OK             | 200   |
  >| NOT_FOUND      | 404   |
  
 </details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0/geolocations/itineraryID/636b4b7cbc36ff41b4c06bdb

<details>
  <summary>Datos de salida</summary>
  
  >```json
  >{
  >  "id": "63782555e7e5ee0f8a753981",
  >  "timestamp": "2022-11-14T09:41:01.178",
  >  "itineraryId": "636b4b7cbc36ff41b4c06bdb",
  >  "userId": "6346b36111e0803c6675d541",
  >  "location": {
  >      "type": "Point",
  >      "coordinates": [
  >          42.082716,
  >          -2.082716
  >      ]
  >  }
  >}
  >```
  
 </details>

### Obtener las localizaciones de un usuario pasandole su id /userID/{userID} GET
---

 <details>
  <summary>Respuestas</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| OK             | 200   |
  >| NOT_FOUND      | 404   |
  
 </details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0/geolocations/userID/6346b36111e0803c6675d541

<details>
  <summary>Datos de salida</summary>
  
  >```json
  >{
  >  "id": "63782555e7e5ee0f8a753981",
  >  "timestamp": "2022-11-14T09:41:01.178",
  >  "itineraryId": "636b4b7cbc36ff41b4c06bdb",
  >  "userId": "6346b36111e0803c6675d541",
  >  "location": {
  >      "type": "Point",
  >      "coordinates": [
  >          42.082716,
  >          -2.082716
  >      ]
  >  }
  >}
  >```
  
 </details>
 
### Crea una localización / POST
---

 <details>
  <summary>Datos de entrada en el body</summary>
  
  >```json
  >{           
  >  "timestamp": "2022-11-14T09:41:01.178",
  >  "itineraryId": "636b4b7cbc36ff41b4c06bdb",
  >  "userId": "6346b36111e0803c6675d541",
  >  "location": {
  >      "type": "Point",
  >      "coordinates": [
  >          42.082716,
  >          -2.082716
  >      ]
  >  }
  >}  
  >```
  
 </details>
  
 <details>
  <summary>Respuesta</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| CREATED        | 201   |
  
  </details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0//geolocations/

<details>
  <summary>Datos de salida</summary>
  
  >```json
  >{  
  >  "id": "63782555e7e5ee0f8a753981",
  >  "timestamp": "2022-11-14T09:41:01.178",
  >  "itineraryId": "636b4b7cbc36ff41b4c06bdb",
  >  "userId": "6346b36111e0803c6675d541",
  >  "location": {
  >      "type": "Point",
  >      "coordinates": [
  >          42.082716,
  >          -2.082716
  >      ]
  >  }
  >}  
  >```

 </details> 

### Actualiza una localización pasndole su id /{id} PUT
---

 <details>
  <summary>Datos de entrada en el body</summary>
  
  >```json
  >{           
  >  "timestamp": "2022-11-14T09:41:01.178",
  >  "itineraryId": "636b4b7cbc36ff41b4c06bdb",
  >  "userId": "6346b36111e0803c6675d541",
  >  "location": {
  >      "type": "Point",
  >      "coordinates": [
  >          42.082716,
  >          -2.082716
  >      ]
  >  }
  >}  
  >```
  
 </details>
  
 <details>
  <summary>Respuesta</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| CREATED        | 201   |
  
  </details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0//geolocations/63782555e7e5ee0f8a753981

<details>
  <summary>Datos de salida</summary>
  
  >```json
  >{  
  >  "id": "63782555e7e5ee0f8a753981",
  >  "timestamp": "2022-11-14T09:41:01.178",
  >  "itineraryId": "636b4b7cbc36ff41b4c06bdb",
  >  "userId": "6346b36111e0803c6675d541",
  >  "location": {
  >      "type": "Point",
  >      "coordinates": [
  >          42.082716,
  >          -2.082716
  >      ]
  >  }
  >}  
  >```

 </details> 

 ### Borra una localización pasandole su id /{id} DEL
 ---
 
<details>
  <summary>Respuestas</summary>
  
  >| ResponseStatus | Valor | 
  >|:-------------- |:----- |
  >| NO_CONTENT     | 204   |
  >| NOT_FOUND      | 404   |
  
</details>
  
  URL de ejemplo
  >http://localhost:8080/api/v0/geolocations/63782555e7e5ee0f8a753981
