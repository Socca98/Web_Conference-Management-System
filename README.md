## Conference-Management-System
This web app manages conferences, different user roles, abstracts submissions, 
and reviews. Made with **Angular** and **Spring**.

Icons are attributed in the footer of the Home page.
Left-up icon changes theme.

## INDEX
 * [App Photos](#app-photos)
 * [Role-based authorization](#role-based-authorization)
 * [Requirements](#requirements)
 * [Steps to setup Frontend](#steps-to-setup-frontend)
 * [Steps to setup Backend](#steps-to-setup-backend)
 * [Diagrams](#frontend)

## App photos
Light theme             |  Alien Theme
:-------------------------:|:-------------------------:
<img src="https://i.imgur.com/FN1i8Fq.png" width="100%" /> |  <img src="https://i.imgur.com/LV6kTKL.png" width="100%" />
<img src="https://i.imgur.com/n5zW4J9.png" width="100%" /> | <img src="https://i.imgur.com/RJvfPXm.png" width="100%" />

 <img src="https://i.imgur.com/FN1i8Fq.png" width="90%" />
 <hr><img src="https://i.imgur.com/HP6kqr7.png" width="90%" />
 <hr><img src="https://i.imgur.com/tueLwwE.png" width="90%" />
 <hr><img src="https://i.imgur.com/ZpwBVOk.png" width="90%" />
 <hr><img src="https://i.imgur.com/an4PNMl.png" width="90%" />
 <hr><img src="https://i.imgur.com/lJhNJbY.png" width="80%" />
 <hr><img src="https://i.imgur.com/LBwvnYZ.png" width="80%" />
 <hr><img src="https://i.imgur.com/n5zW4J9.png" width="80%" />
 <hr><img src="https://i.imgur.com/VThtGrV.png" width="80%" />
 <hr><img src="https://i.imgur.com/cfctZPf.png" width="80%" />


## Role-based authorization
 Roles: Chair, Author, PC Member, Co-Chair, SC Member.
 
 Each user may have role X in conference 1 and role Y in another conference.
 
 Except the 'admin', which is always Chair. 
 
 With default register, you are Author. 
 Other roles get email invitation links when Chair creates a conference.
 
 **Author**
 * submit scientific paper
 * see results
 * participate in sections where papers are presented
 
 **PC Member**
 * submit scientific paper
 * see results
 * participate in sections where papers are presented
 * bid papers (show which you would like to review)
 * review papers
 
 **Co-Chair**
 * participate in sections where papers are presented
 * bid papers (show which you would like to review)
 * review papers
 * assign a paper to a bidder
 * see all reviews:
    * choose the final verdict of a paper
    * send to another reviewer
    * request discussion (reset reviews to _NOT_REVIEWED_)

 **Chair**
 * same as Co-Chair
 * create section
 * create conference
 
 **SC-Member**
 * participate in sections where papers are presented
 

## Requirements
  * Angular + Angular Material
  * MySql server
  * Java
  
## Steps to setup Frontend

  * Install nodejs https://nodejs.org/en/, LTS
  * Hope that WebStorm recognizes 'npm install' command
  * Open project in IDE (WebStorm, VS Code)
  * Go inside folder 'frontend' with the terminal (the IDE may have 'open folder in terminal' on right click).
  * Run the command npm install_ to install folder 'node_modules'.
  * Start app with WebStorm, VS Code or with command 'ng serve' in terminal.
  * The app will run on the address http://localhost:4200/  -> the default port (4200) for angular projects 



# Steps to setup Backend 
### MySQL Database setup
`In brief, you need: MySQL Server (username: root, password: iss2020) and an empty database 'cms'`

* Download MySQL Server from google. Windows (x86, 32-bit), MSI Installer.
https://dev.mysql.com/downloads/windows/installer/8.0.html

    * (mysql-installer-web-community-8.0.19.0.msi)
    * Choose 'Server only'
    * Click 'Next' until you can choose password
    * Put password 'iss2020'
    * Click 'Next' all the way
    
* Download MySQL Workbench from google (same functionality as SSMS-Sql Server Management Studio)
    * (mysql-workbench-community-8.0.19-winx64.msi)
    * Click 'Local Instance MySQL80' or smth like that
    * Left, middle of the screen, choose Schemas (belongs to tab Navigator), 
    here you can create a DB with 'Create Schema' from Toolbar
    * create DB called 'cms'

## Backend setup
##### Already done for this project. Written in case you want to replicate in the future.

* Create project on the internet with Spring Initializr, add dependencies: Web, JPA. Choose Gradle, Java 11.
* Open project in IDE (IntelliJ, Eclipse)
* Paste the bellow code in 'resources/application.properties'. It connects the project with MySQL.
Configures it to know the connection string, DB name, port of the DB and others.

```
spring:
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
  datasource:
    url: jdbc:mysql://localhost/cms?serverTimezone=UTC
    username: root
    password: iss2020
    driverClassName: com.mysql.cj.jdbc.Driver
  servlet:
    multipart:
      enabled: true
      max-file-size: 10MB
      file-size-threshold: 10KB
      max-request-size: 15MB
server:
  servlet:
    context-path: /cms/api/
```

* Change 'application.properties' to 'application.yml'. Its just a simpler form of
text format.
* Add this in 'build.gradle' -> dependencies{..}.
 It imports the jdbc driver.
```
implementation 'mysql:mysql-connector-java'
```
* Run

## Diagrams
Found in '~diagrams and documents'.
