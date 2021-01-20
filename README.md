# U-Blog
### Console based Blog Management Project in Java.
#### CRUD - Post Blog, Search Blog, Filter Blog, Register User, Login User
##### To see more into it, click <a href = "https://github.com/chitranshu11/U-Blog/blob/main/java-ublog/README.md" target = "_blank">Project Structure</a>
### To run this project*
#### 1) Clone this repository.
#### 2) Import in your faviorate IDE.
#### 3) Add JDBC mySQL Driver to project directory (If not downloaded, use <a href = "https://dev.mysql.com/downloads/connector/j/" target = "_blank"> MYSQL Driver</a>). <br>
#### 4)Run all the queries mentioned below or mentioned in the setup.sql file in your MySQL workbench.
        This will ensure that all the required tables get created in the MySQL database.
#### 4) Run Application.java file.   

*Java and MySQL needed to run on your machine.
        

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

#### Yes, this project can be improved. I will work on it in the future. Until feel free to raise issues and contribute.
### Happy Coding!!
