1. 首先启动configserver
2. start eureka server jvm configuration

#Instance 1
-Dspring.profiles.active="dev" -Dserver.port=8761 -Deureka.instance.hostname="eureka_1" 
-Deureka.client.serviceUrl.defaultZones="http://localhost:8762/eureka"

#Instance 2
-Dspring.profiles.active="dev" -Dserver.port=8762 -Deureka.instance.hostname="eureka_2" 
-Deureka.client.serviceUrl.defaultZones="http://localhost:8761/eureka"


#Coniguration
Renews threshold: Eureka Server 期望收到的心跳次数
Renews (last min)： 上一分钟收到的心跳次数


#Eureka client confiuration
lease-renewal-interval-in-seconds: 5          # eureka client 发送给eureka server 心跳的频率
lease-expiration-duration-in-seconds: 15      # 从上一次接受到eureka client 心跳到下一次接受到心跳的最长间隔,如果间隔时间长于设置的数值并且不是在自我保护的模式下,eureka server会删除这个服务