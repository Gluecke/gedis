# gedis
A test application connected to a redis instance

## Auto-restart
`./gradlew build --continuous`

## To Run
`./gradlew bootRun`

### Redis
[Windows Setup](https://github.com/ServiceStack/redis-windows)
* extract to /redis
* cd /redis
* ./redis-server.exe


### Example
`http://localhost:8080/emails`