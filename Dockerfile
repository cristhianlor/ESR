FROM OPENJDK

WORKDIR /app

COPY target/ecommerce-0.0.1-SNAPSHOT.jar /app/ecommerce.jar

ENTRYPOINT ["java", ".jar", "ecommerce.jar"]



