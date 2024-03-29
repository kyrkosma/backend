# alias and keystore.p12 are used in application.properties
# if you don't want to do any changes to application.properties, use password 123456
keytool -genkeypair -alias alias -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 365 -ext san=dns:localhost
# install the certificate which is generated by the following command
keytool -export -keystore keystore.p12 -alias alias -file self-signed-cert.crt