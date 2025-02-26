# --------------------------------------------------------
# STAGE 1: BUILD
# --------------------------------------------------------
FROM maven:3.8.6-openjdk-17 AS build

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
# Dùng wildcard để copy file JAR, đổi tên thành app.jar
COPY --from=build /app/target/*.jar app.jar

# Khởi chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
