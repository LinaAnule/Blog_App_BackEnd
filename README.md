# Website for blog entries

Blog is an web application that allows you to create blog entries about what you want, read existing blogs and leave
comments on selected blogs.
Program was designed for two roles: "ADMIN" and a "USER".

Logged-in users can:

- review list of existing blogs;
- read blog entry with comments;
- leave comments on blogs;
- edit their comments;
- delete their comments;

Admins can:

- review list of existing blogs;
- read blog entry with comments;
- create blog entries;
- edit their blog entries;
- delete blogs entries;
- delete selected comments made by whatever users;
- leave comments on blogs;
- edit their own comments;

Anonymous user can only view blogs and comments;

## Requirements

For building and running the application you need:

- JDK 17
- Maven 3
- PostgreSQL

## Run the program

In application.properties file, adjust database properties according to your credentials

````
#postgresql properties
spring.datasource.url=jdbc:postgresql://localhost:5432/blog_app
spring.datasource.username=postgres
spring.datasource.password=Taiga1
````

To start application press run key in your IDE.

There are 4 users already created on start up:

- Admin (password: admin)
- Admin1 (password: admin)
- User (password: user)
- User1 (passwordL user)

You may use these credentials to log into application or register to create a new USER;
