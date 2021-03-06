# **Cafe Management Application**

## Table of Contents
+ [Installation](#installation)

+ [Usage](#usage)
	
+ [Documentation](#documentation)

+ [Issues](#Issues)

+ [Author](#author)

+ [License](#license)


## Introduction
*Cafe Management* is a RESTful API.
Its main features are:

+ Create Customers.

+ Order Items from menu.

+ Check Servers' performance.

+ and many more.
+ Problems with the code: Package by layer instead of Package by Feature, Dependency injection done with Reflection(Autowired) instead of Constructor or Method DI.

## Installation

Run ``` ./mvnw spring-boot:run ``` command in root directory of the project.

Project can also be deployed on a web server/servlet container.

## Usage

API Request Examples:
```
Check EXAMPLES.md or Swagger UI
...
```
Or frontend implementation:
[Angular FrontEnd](https://github.com/furkanakharman/anglcafe)

## Documentation

[Swagger UI ](http://localhost:8080/swagger-ui.html)  
(link only works if app has deployed on localhost:8080, otherwise just go hostname/app-root/swagger-ui.html)

## Issues

There are lots of issues.
For an example I had no time to do error checking so expect lots of errors if not used correctly!

## Author

Furkan Akharman

## License

This program is licensed under non-profit license.
