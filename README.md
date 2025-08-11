# AnyDesk - Screen Sharing Application
A desktop-based **screen sharing application** inspired by AnyDesk, built using **Java** and **Java Swing**.  
It allows users to share their screen in real-time and remotely control another system within the same network.

# Database Configuration

# 1. MySQL Setup
Start MySQL Server
Run these commands:

```
CREATE DATABASE test3;
USE test3;

-- Table: Student
CREATE TABLE Student (
    Rollno INT PRIMARY KEY,
    Name VARCHAR(45),
    Marks VARCHAR(45)
);

-- Table: user
CREATE TABLE user (
    username VARCHAR(30) PRIMARY KEY,
    password VARCHAR(45),
    gmail VARCHAR(45)
);
```

# 2. Update Credentials

Edit src/vmm_team_viewer/DBLoader.java:

```

Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test3", "username", "password");
```

# 3. Add Jar Files

Add all the libraries/jar files in src/LIBS folder

#

# Run the Application

Run **main.java** file.
