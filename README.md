# mancala-backend
Backend API for Mancala UI, built with Java 21 (Spring boot 3.1.4) exposes a REST API to be used by [mancala-ui](https://github.com/erickturcios/mancala-ui) project
This API is designed to handle games per browser user session, which means:
- Each user will have his/her own game configuration as long as the browser is not closed
- Each game movement is saved to MySQL database, which allows "Undo movement" whenever the user requires it
- Each user can customize a custom configuration (not shared to any other user), eg. initial number of stones, auto rotate on each turn, allow "undo last movement"

This appliocation was developed based on the following basic rules:

## Game Setup
The player who begins with the first move picks up all the stones in any of his own six pits, and sows the stones on to the right, one in each of the following pits, including his own big pit. No stones are put in the opponents' big pit. If the player's last stone lands in his own big pit, he gets another turn. This can be repeated several times before it's the other player's turn.

## Capturing Stones
During the game the pits are emptied on both sides. Always when the last stone lands in an own empty pit, the player captures his own stone and all stones in the opposite pit (the other player’s pit) and puts them in his own (big or little?) pit.

## The Game Ends
The game is over as soon as one of the sides runs out of stones. The player who still has stones in his pits keeps them and puts them in his big pit. The winner of the game is the player who has the most stones in his big pit.

# Run the application 
Run it with this command:

    .\mvnw spring-boot:run


![start](assets/play-backend-01.jpg)

Then you can access:

## Open API UI (Swagger)

    http://localhost:8080/swagger-ui/index.html#/

![start](assets/play-backend-02.jpg)

## Open API Doc (for postman)

    http://localhost:8080/v3/api-docs

## Application basic model

This is a high level view of objects used in the application:

![start](assets/model.png)

## Application extended model

This is a more complete detail of the application design:

![start](assets/model_extended.jpg)


# Run Tests

## Unit Tests

    mvn test -Dgroups=IntegrationTest

## Integration Tests

    mvn test -Dgroups=UnitTest   
