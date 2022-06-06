# Deep Dive de Serverless

Deep Dive sobre Serverless no Openshift.

Colocar aqui um resumo de cada projeto da demo

# Requisitos
* Ambiente para Quarkus: VSCode, JDK, mvn
* CLI configurado: `kn`, `oc`, `kafka cli` (https://kafka.apache.org/quickstart#quickstart_send)


## Apresentação 
[Visualizar](https://docs.google.com/presentation/d/1T5T9faNJpU_8nnLeApG_fHK6JaYNjZiz4-I_h4ZIiE4/edit#slide=id.g6b619a1e04_0_2548)


## Demo

De preferência, ter 2 clusters. O primeiro zerado, o segundo com todo o ambiente configurado para pularmos o primeiro passo de "Configuração do OpenShift"

## Ambiente

* OpenShift 4.9
* Criar um BOT no Telegram a partir do [BotFather](https://t.me/BotFather). Ex: 5502170628:AAFTYAVcKd2EKYIKcOP9JLpnck1qk8CBOQc

## Configuração do OpenShift

* Instalar Operadores
  * Red Hat OpenShift Serverless
  * Red Hat Integration - AMQ Streams
  * Red Hat Integration - Camel K
* Red Hat Integration - AMQ Streams
  * Criar um Kafka `my-cluster` no namespace: `my-kafka`
    * Copiar o valor de bootstrapServers, ex: `my-cluster-kafka-bootstrap.my-kafka.svc:9092`
  * Criar um Kafka Topic `my-topic` no namespace: `my-kafka`
* Red Hat OpenShift Serverless
  * Criar Knative Serving no namespace `knative-serving`
    * Aguardar as `Conditions` estarem True
  * Criar Knative Eventing no namespace `knative-eventing`
    * Aguardar as `Conditions` estarem True
  * Criar Knative Kafka no namespace `knative-eventing`
  
## Deploy

* Alterar para visão do Developer
* Project: `my-project`
* +Add -> Container Image
  * viniciusfcf/quarkus-serving-and-eventing-native:latest
  * Serverless Deployment

* Kafka producer
  * Project: `my-kafka`
  * +add -> Container Image
    * viniciusfcf/kafka-producer:latest
    * Serverless Deployment
    * URLS: 
      * /1 -> Envia 1 msg
      * /10 
      * /100
      * /1000

## Event Sources

* Adicionar um Event Source, do tipo Ping, `{"message": "Hello world!"}` e schedule `* * * * *`
  * De minuto em minuto será enviado o JSON acima

## Criar uma function

`kn func create -l quarkus -t http quarkus-http`
`kn func create -l quarkus -t cloudevent quarkus-event`

## Código fonte

* https://github.com/viniciusfcf/red-hat-serverless

## Documentacao Function

https://docs.openshift.com/container-platform/4.10/serverless/functions/serverless-developing-quarkus-functions.html

Deploy: 

https://docs.openshift.com/container-platform/4.10/serverless/functions/serverless-functions-getting-started.html#serverless-deploy-func-kn_serverless-functions-getting-started