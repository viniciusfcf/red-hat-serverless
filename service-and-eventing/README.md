# service-and-eventing Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Packaging and running the application

The application can be packaged using:
Gerar as imagens: 
```shell script

./mvnw package -Dquarkus.container-image.build=true
./mvnw package -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true -Dquarkus.container-image.name=service-and-eventing-native
```