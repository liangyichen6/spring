# Gen keystore file
keytool -genkeypair -alias {jwt} -keyalg RSA -keypass {password} -keystore {jwt.jks} -storepass {password}

# Get Public key
keytool -list -rfc --keystore {jwt.jks} | openssl x509 -inform pem -pubkey