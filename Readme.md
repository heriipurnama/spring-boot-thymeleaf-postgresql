## Spring Boot Thymeleaf PostgreSQL

This project is a web application built using Spring Boot, Thymeleaf, and PostgreSQL. It includes features for administrator login, member registration, member login, and a JSON API for listing member data based on the member name.

### Project Structure

```
spring-boot-thymeleaf-postgresql
│   pom.xml
│
└───src
    └───main
        ├───java
        │   └───com
        │       └───example
        │           └───springbootthymeleafpostgresql
        │               │   SpringBootThymeleafPostgresqlApplication.java
        │               │
        │               ├───controller
        │               │       AdminController.java
        │               │       AuthController.java
        │               │       MemberController.java
        │               │
        │               ├───model
        │               │       Member.java
        │               │       User.java
        │               │
        │               ├───repository
        │               │       MemberRepository.java
        │               │       UserRepository.java
        │               │
        │               ├───service
        │               │       MemberExcelExporter.java
        │               │       MemberPDFExporter.java
        │               │       UserService.java
        │               │
        │               └───security
        │                       SecurityConfig.java
        │
        └───resources
            │   application.properties
            │
            ├───static
            │   └───css
            │           style.css
            │
            ├───templates
            │   ├───admin
            │   │       members.html
            │   │
            │   ├───member
            │   │       profile.html
            │   │
            │   └───auth
            │       login.html
            │       register.html
            │
            └───sql
                schema.sql
    └───test
        └───java
            └───com
                └───example
                    └───springbootthymeleafpostgresql
                        SpringBootThymeleafPostgresqlApplicationTests.java
```

### Features

1. **Administrator Login:**
    - Data: username, password
    - Functionality: View, add, edit, delete user data; view, edit member data; export member data to Excel and PDF.

2. **Member Registration:**
    - Data: name, password, phone number, date of birth, email (unique), gender, KTP number, profile picture (max 1 MB).
    - Functionality: Add, edit, delete, view own member data.

3. **Member Login:**
    - Data: email, password
    - Functionality: After login, display member's own data.

4. **JSON API:**
    - Endpoint: `/api/members`
    - Functionality: Return a list of members based on the name.

### Setup

1. **Database Setup:**

   Ensure PostgreSQL is installed and running. Create a database and update `application.properties` with your database details.

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
   spring.datasource.username=your_db_user
   spring.datasource.password=your_db_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```

   Create the necessary tables using the `schema.sql` script:

   ```sql
   CREATE TABLE users (
       id SERIAL PRIMARY KEY,
       username VARCHAR(50) UNIQUE NOT NULL,
       password VARCHAR(100) NOT NULL,
       role VARCHAR(10) NOT NULL
   );

   CREATE TABLE members (
       id SERIAL PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       password VARCHAR(100) NOT NULL,
       email VARCHAR(100) UNIQUE NOT NULL,
       phone_number VARCHAR(20),
       dob DATE,
       gender VARCHAR(10),
       ktp_number VARCHAR(20),
       profile_picture BYTEA
   );
   ```

2. **Build and Run the Project:**

   ```sh
   mvn clean package
   java -jar target/spring-boot-thymeleaf-postgresql-0.0.1-SNAPSHOT.jar
   ```

3. **Access the Application:**

   Open a web browser and go to `http://localhost:8080`.

### Export Database

Use the following command to export the database to an SQL file:

```sh
pg_dump -U your_db_user -d your_db_name -f database_dump.sql
```

### License

This project is licensed under the MIT License.

### Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.