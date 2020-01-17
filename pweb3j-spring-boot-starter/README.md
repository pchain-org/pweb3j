# pweb3j Spring Boot Starter

Integrate pweb3j into your Spring Boot applications via Spring's dependency injection.


## Getting started

A sample application is available [here](https://github.com/web3j/examples/tree/master/spring-boot)

To use, create a new [Spring Boot Application](https://spring.io/guides/gs/spring-boot/), and 
include the following dependencies:

Maven:

```xml
<dependency>
    <groupId>io.github.pchain-org</groupId>
    <artifactId>pweb3j-spring-boot-starter</artifactId>
    <version>1.0.1</version>
</dependency>
```

Gradle:

```groovy
compile ('io.github.pchain-org:pweb3j-spring-boot-starter:1.0.1')
```

Now Spring can inject pweb3j instances for you where ever you need them:

```java
@Autowired
private Web3j web3j;
```

No additional configuration is required if you want to connect via HTTP to the default URL 
http://localhost:8545.

Otherwise simply add the address of the endpoint in your application properties:

```properties
# An infura endpoint
web3j.client-address = https://rinkeby.infura.io/

# Or, an IPC endpoing
web3j.client-address = /path/to/file.ipc
```


## Admin clients

If you wish to make use of the personal module methods that are common to both
[Parity](https://github.com/ethcore/parity/wiki/JSONRPC-personal-module) and 
[Geth](https://github.com/ethereum/go-ethereum/wiki/Management-APIs#personal)  
to manage accounts, enable the admin client:

```properties
web3j.admin-client = true
```

Then Spring can inject admin clients:

 ```java
 @Autowired
 private Admin admin;
 ```


## HTTP client configuration

Some Ethereum operations take longer than the default HTTP timeout set by the `OkHttp3` library
used by `pweb3j`. To configure those timeouts set the web3j `httpTimeoutSeconds` property:

```properties
web3j.httpTimeoutSeconds = 600  
```

This sets all three OkHttp3 timeouts: `connect`, `read`, and `write`.  

Valid values are any non-negative integer.

A value of '`0`' means: no timeout.  


**Note**: This is not required for transacting with pweb3j.  
