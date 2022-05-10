# rm -f server.jks server.key server.crt
keytool \
    -keystore server.jks  -storepass passpass  -deststoretype pkcs12 \
    -genkeypair -ext "bc:c" -keyalg EC -validity 365 \
    -dname "CN=$1" \
    -ext "SAN=dns:$1,ip:$2,ip:10.0.0.2"
keytool -list -v -keystore server.jks -storepass passpass
openssl pkcs12 -in server.jks -nodes -nocerts -out server.key -password pass:passpass
openssl pkcs12 -in server.jks -nokeys -out server.crt -password pass:passpass
rm server.jks
