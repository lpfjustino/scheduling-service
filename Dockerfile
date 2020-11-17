FROM openjdk:7
COPY . /usr/src/scheduling-service
WORKDIR /usr/src/scheduling-service
RUN javac ScheduleApplication.java
CMD ["java", "ScheduleApplication"]