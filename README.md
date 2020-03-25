## COVID-19
ISS 2020, group 925

# Steps to setup Frontend
* Open project in IDE (WebStorm, VS Code)
* Go inside folder 'frontend' with the terminal (the IDE may have 'open folder in terminal' on right click).
* Run the command npm install_ to install folder 'node_modules'.
* Start app with WebStorm, VS Code or with command 'ng serve' in terminal.
* The app will run on the address http://localhost:4200/  -> the default port (4200) for angular projects 

# Steps to setup Backend 
### MySQL Database setup

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

```spring:
     jpa:
       show-sql: true
       hibernate:
         ddl-auto: update
       properties:
         hibernate:
           dialect: org.hibernate.dialect.MySQL5Dialect
     datasource:
       url: jdbc:mysql://localhost:3306/cms
       username: root
       password: iss2020
       driverClassName: com.mysql.cj.jdbc.Driver
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
