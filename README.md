# tictactoe-with-computer

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Docker](https://www.docker.com)
- [Docker Compose](https://docs.docker.com/compose/)



## Running the application locally

clone the repo with the command given below: 
```shell
git clone https://github.com/mozammal/tictactoe-with-computer.git
```


One way to run this application is to use docker-compose from the command line
(tested on ubuntu 18.04.1):

```shell
cd  tictactoe-with-computer
cd  tictactoe-server
mvn clean package
sudo docker-compose up -d
cd ..
cd tictactoe-client
sudo docker-compose up -d
```

One way to stop this application is to use docker-compose from the command line
(tested on ubuntu 18.04.1):

```shell
cd  tictactoe-client
sudo docker-compose stop
cd ..
cd  tictactoe-server
sudo docker-compose stop

```

The can now play the tic tac toe game at the follwing url 
- http://localhost:3001




## Enjoy beating the computer!






