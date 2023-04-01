# Post Box

Post Box is an email client that allows you to send and receive emails from multiple accounts in one place.

## Features

- Supports Gmail, Outlook, Yahoo and other popular email services
- Allows you to compose, reply, forward, delete and archive emails
- Supports attachments, images, emojis and rich text formatting
- Allows you to manage your contacts, folders and labels
- Supports notifications, search and filters
- Provides a simple and intuitive user interface

## Tech Stack
[![Java](https://img.shields.io/badge/Java-007396?style=flat-square&logo=java&logoColor=white)](https://www.java.com/)
[![JavaFX](https://img.shields.io/badge/JavaFX-F80000?style=flat-square&logo=java&logoColor=white)](https://openjfx.io/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![XML](https://img.shields.io/badge/XML-1572B6?style=flat-square&logo=xml&logoColor=white)](https://www.w3.org/XML/)

## Installation

To install Post Box, you need to have Node.js and npm installed on your system. Then, follow these steps:

1. Clone this repo using `git clone https://github.com/kunalsaxena/postBox.git`
2. Navigate to the project directory using `cd postBox`
3. Install the dependencies using `mvn clean install`
4. Navigate to the target directory using `cd target` 
5. Start the app using `java -jar postbox-1.0-SNAPSHOT.jar --module-path=/Users/kunalsaxena/Work/DevTools/javafx-sdk-17.0.6/lib/ --add-modules=javafx.controls,javafx.fxml Main-Class: com.mailclient.App`
6. Windows application login screen should popup 

## Usage
To use Post Box, you need to create an account and log in with your email credentials. You can add multiple email accounts and switch between them using the sidebar. You can also customize your settings and preferences using the menu icon on the top right corner.

## Contributers
Kunal Saxena

## Goals
- Save emails and decide format
- File handling
- Index emails
- Read emails from file
- Load indexes to redis
- Improve ui

## License
Post Box is licensed under the MIT License. See [LICENSE](https://github.com/kunalsaxena/postBox/blob/master/LICENSE) for more details.

