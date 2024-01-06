# My Java App

This is a simple Java application built with Maven.

## Prerequisites

You need to have the following installed on your machine:

- Java Development Kit (JDK)
- Maven

## Building the Project

To build the project, navigate to the project directory and run the following command:

```
mvn clean install
```

This command cleans the target directory, compiles your code, runs your tests, and packages your code into a JAR file within the target directory.

## Running the Application

To run the application, use the following command:

```
java -cp target/my-java-app-1.0-SNAPSHOT.jar com.myapp.App
```

Replace `my-java-app-1.0-SNAPSHOT.jar` with the name of your generated JAR file and `com.myapp.App` with the main class of your application.

## Running the Tests

To run the tests, use the following command:

```
mvn test
```

This command runs the unit tests using the testing framework specified in the `pom.xml` file.

## Contributing

Please read `CONTRIBUTING.md` for details on our code of conduct, and the process for submitting pull requests to us.

## License

This project is licensed under the MIT License - see the `LICENSE.md` file for details.