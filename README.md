# Worst Movie

Worst Movie is an API that handles the latest movies chosen by the Golden Raspberry Awards.

## Run the Application

First you will need to clone this project or download and save it in a directory of your choice.

Then, through a terminal window, access the project directory, and execute the following command:

```bash
mvnw spring-boot:run
```

After that, just have fun accessing the api through your browser on the default port 8080.

The API swagger and documentation can be found at URL:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Run Integration Tests

Through a terminal window, access the project directory, and execute the following command:

```bash
mvn test
```

## Import Custom Movie File

You can import custom movies, that's right! Just in your command line add the file directory.

Through a terminal window, access the project directory, and execute the following command (change "D:\movies" to your movies directory):

```bash
mvnw spring-boot:run -Dspring-boot.run.arguments=--goldenraspberryawards.worstmovie.loadmoviesfromfile.csv.path=C:\movies.csv
```
#### Important: always import csv files, separated by ";" (semicolon) and with the following columns:
* year - only the year of the award, and only numbers
* title - movie title
* studios - movie studio, if there is more than one studio, separate them (in the same column) by "," (comma).
* producers - producers of the film, if there is more than one producer, separate them (in the same column) by "," (comma).
* winner - optional if the film is the winner

Do you have any questions, it's simple, just access the example file: [movielist.csv](https://github.com/thiagosol/worst-movie/blob/main/src/main/resources/movielist.csv)
