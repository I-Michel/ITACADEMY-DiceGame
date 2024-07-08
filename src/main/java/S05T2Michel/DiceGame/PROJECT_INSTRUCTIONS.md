# SPRINT 5:<br>Spring Framework Avanzado
## Proyecto final: Juego de dados

********************************************************************************************
Este es tu proyecto final, una API 100% diseñada por ti donde aplicarás todo lo que has aprendido
hasta ahora para crear una aplicación completa, desde la base de datos hasta la seguridad. Aplica
todo lo que sabes e incluso lo que no se pide.
********************************************************************************************

Al juego de dados se juega con dos dados. Si el resultado de la suma de los dos dados es 7, la partida se gana, si no, se pierde. Un jugador puede ver un listado de todas las tiradas que ha hecho y el porcentaje de éxito.
Para poder jugar al juego y realizar una tirada, un usuario debe registrarse con un nombre no repetido. Al crearse, se le asigna un identificador númerico único y una fecha de registro. Si el usuario lo desea, puede no añadir ningún nombre y se llamará "ANÓNIMO". Puede haber más de un jugador "ANÓNIMO".

Cada jugador puede ver un listado de todas las tiradas que ha hecho, con el valor de cada dado y si ha ganado o no la partida. Además, puede ver su porcentaje de éxito por todas las tiradas que ha hecho.

No se puede eliminar una partida en concreto, pero sí se puede eliminar todo el listado de tiradas de un jugador.

El software debe permitir listar todos los jugadores que hay en el sistema, el porcentaje de éxito de cada jugador y el porcentaje de la media de éxito de todos los jugadores en el sistema.

El software debe respetar los principales patrones de diseño. Debes tener en cuenta los siguientes detalles de construcción (URL's):

* POST /players: crea un jugador.
* PUT /players: modifica el nombre del jugador.
* POST /players/{id}/games: un jugador específico realiza una tirada de dados.
* DELETE /players/{id}/games: elimina las tiradas del jugador.
* GET /players/: devuelve el listado de todos los jugadores del sistema con su porcentaje de éxito.
* GET /players/{id}/games: devuelve el listado de jugadas por un jugador.
* GET /players/ranking: devuelve el porcentaje de la media de éxito de todos los jugadores del sistema. 
* GET /players/ranking/loser: devuelve el jugador con peor porcentaje de éxito.
* GET /players/ranking/winner: devuelve el jugador con mejor porcentaje de éxito.

Añade seguridad: incluye autenticación por JWT en todos los accesos a las URL'S del microservicio.

Diseña el proyecto diversificando la persistencia para que utilice dos esquemas de bases de datos al mismo tiempo: MySQL y MongoDB.

---