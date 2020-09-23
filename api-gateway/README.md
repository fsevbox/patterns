#api-gateway

Gateway implementation using `spring-cloud-gateway`:

* Gateway running on port 8080
    
  `curl --location --request GET 'http://localhost:8080/foo'`
  
  `curl --location --request GET 'http://localhost:8080/foo?delay=1`
  
  `curl --location --request GET 'http://localhost:8080/bar`

* Bar service running on port 8081

* Foo service running on port 8082