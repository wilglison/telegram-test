FROM openjdk:11-jre-slim
#FROM openjdk:11-jdk-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

COPY TelegramBot.class .
#COPY TelegramBot.java .
#RUN javac TelegramBot.java

# Define o comando que será executado quando o contêiner iniciar
CMD ["java", "TelegramBot", "teste docker"]