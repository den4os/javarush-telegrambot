FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=jrtest_D_bot
ENV BOT_TOKEN=6362381389:AAFTI6w47Xa7OlS2nZ5lxNy6TBtlnhXj3Og
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar", "/app.jar"]