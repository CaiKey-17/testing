# --------------------------------------------------------
# STAGE 1: BUILD
# --------------------------------------------------------
    FROM ubuntu:latest AS build

    # Cài đặt OpenJDK 17 và Maven
    RUN apt-get update && \
        apt-get install -y openjdk-17-jdk maven
    
    # Tạo thư mục làm việc
    WORKDIR /app
    
    # Sao chép toàn bộ project vào container
    COPY . .
    
    # Build ứng dụng (tạo file .jar trong thư mục target/)
    RUN mvn clean package -DskipTests
    
    # --------------------------------------------------------
    # STAGE 2: RUN
    # --------------------------------------------------------
    FROM openjdk:17-jdk-slim
    
    # Tạo thư mục chạy ứng dụng
    WORKDIR /app
    
    # Mở cổng 8080
    EXPOSE 8080
    
    # Sao chép file JAR đã build từ stage 1
    # Giả sử file .jar tên là demo-1.0.jar
    COPY --from=build /app/target/demo-1.0.jar app.jar
    
    # Khởi chạy ứng dụng
    ENTRYPOINT ["java", "-jar", "app.jar"]
    