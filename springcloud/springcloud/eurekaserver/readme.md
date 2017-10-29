1. 首先启动configserver
2. start eureka server jvm configuration

#Instance 1
-Dspring.profiles.active="local" -Dserver.port=8761 -Deureka.instance.hostname="eureka_1" 
-Deureka.client.serviceUrl.defaultZones="http://localhost:8762/eureka"

#Instance 2
-Dspring.profiles.active="local" -Dserver.port=8762 -Deureka.instance.hostname="eureka_2" 
-Deureka.client.serviceUrl.defaultZones="http://localhost:8762/eureka"
