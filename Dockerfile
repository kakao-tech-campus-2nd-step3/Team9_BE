# 도커 이미지를 빌드할 때 필요한 커맨드를 가짐

# jdk21 image start
FROM openjdk:21

# 인자 설정 - JAR_File
ARG JAR_FILE=build/libs/*.jar

# jar 파일 복제
COPY ${JAR_FILE} app.jar

# 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]