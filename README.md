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
### Registrar usuario /signup
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

## Servicio de usuarios /api/v0/users

## Servicio de itinerarios /api/v0/itinerarys

## Servicio de localizaciones api/v0/geolocations
