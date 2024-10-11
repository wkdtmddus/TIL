FROM openjdk:17-jdk

WORKDIR /app

COPY . .

# 빌드된 JAR 파일을 변수로 설정 후 복사
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# Spring 프로파일을 'prod'로 설정하여 애플리케이션 실행
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]