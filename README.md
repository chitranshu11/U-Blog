# U-Blog
Console based blog management project in Java

### To run this project*
#### 1) Clone this repository.
#### 2) Import in your faviorate IDE.
#### 3) Add JDBC mySQL Driver to project directory (If not downloaded use <a href = "https://dev.mysql.com/downloads/connector/j/" target = "_blank"> MYSQL Driver</a>). <br>
#### 4)Run all the queries mentioned below or mentioned in the setup.sql file in your MySQL workbench.
        This will ensure that all the required tables get created in the MySQL database.
#### 4) Run Application.java file.   

*Java and MySQl needed to run on your machine.
        

#### Queries 
<p>*****************Start***************</p>
-> CREATE DATABASE ublog;

-> USE ublog;

-> CREATE TABLE user (
    userId INTEGER AUTO_INCREMENT PRIMARY KEY,
    emailId VARCHAR(100),
    password VARCHAR(100)
);

-> CREATE TABLE post (
    postId INTEGER AUTO_INCREMENT PRIMARY KEY,
    emailId VARCHAR(100),
    tag VARCHAR(10),
    title VARCHAR(200),
    description VARCHAR(1000),
    timestamp VARCHAR(100)
);
<p>*****************End******************</p>
