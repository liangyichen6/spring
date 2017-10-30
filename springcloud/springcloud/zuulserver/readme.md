#start configuration
-Dspring.profiles.active="local" -DDeureka.instance.hostname="zuulserver" 
-DEUREKA_SERVER_URL="http://localhost:8761/eureka"