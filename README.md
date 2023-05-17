# blogApi
My Blog Api

Simple Blog Api -- Java 17 Spring boot 2.5.5 -- Hibernate / JPA -- MYSQL Workbench -- RSQL Filter -- Unit Test

İnstallation:
- Download project. 
- Open in eclipse or Intellij idea.
- You must make a database connection carefully.
- Create a schema in Mysql Workbench.
- git clone https://github.com/BerkTas20/blogApi.git or download GithubDesktop. Enter file -> clone repository

#DB CONNECTION
> spring.datasource.url =jdbc:mariadb://localhost:3306/blogapi_develop
> 
> spring.datasource.username =root
> 
> spring.datasource.password =12345

- You must enter your own password.
- This is our database name => blogapi_develop

- Run project in editor , automatic tables will be created in the database.
- This is swagger url : http://localhost:8090/api/v1/swagger-ui/index.html

What can do this backend application ? 
- Admin and user registration and login can be done.
- User can add profile photo and cover photo.
- User can share post, another user may like the post.
- View of the post can be seen.
- Posts can be filtered by user and category.
- User recent posts can be listed.
- Users can post scores. Scores are averaged and post score determined.
- Users can comment on posts.

Db Diagram:
![Image of Yaktocat](https://i.hizliresim.com/qvrtxfj.png)
