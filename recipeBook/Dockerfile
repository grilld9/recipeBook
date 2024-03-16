FROM openjdk:17-jdk-alpine
COPY target/recipeBook-0.0.1-SNAPSHOT.jar recipeBook.jar
COPY src/main/resources/ingredients.txt ingredients
ENTRYPOINT ["java", "-jar", "recipeBook.jar"]