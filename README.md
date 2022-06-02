# Deep Dive de Serverless

Deep Dive sobre Serverless no Openshift.

Colocar aqui um resumo de cada projeto da demo


## Apresentação 
[Visualizar](https://docs.google.com/presentation/d/1T5T9faNJpU_8nnLeApG_fHK6JaYNjZiz4-I_h4ZIiE4/edit#slide=id.g6b619a1e04_0_2548)


## Demo

De preferência, ter 2 clusters. O primeiro zerado, o segundo com todo o ambiente configurado para pularmos o primeiro passo de "Configuração do OpenShift"

## Ambiente

* OpenShift 4.9
* Criar um BOT no Telegram a partir do [BotFather](https://t.me/BotFather)

## Configuração do OpenShift

* Instalar Operadores
  * Red Hat OpenShift Serverless
  * Red Hat Integration - Camel K
  * Red Hat Integration - AMQ Streams
* Red Hat Integration - AMQ Streams
  * Criar um Kafka `my-cluster` no namespace: `my-kafka`
    * Copiar o valor de bootstrapServers, ex: `'my-cluster-kafka-bootstrap.my-kafka.svc:9092'`
  * Criar um Kafka Topic `my-topic` no namespace: `my-kafka`
* Red Hat OpenShift Serverless
  * Criar Knative Serving no namespace `knative-serving`
    * Aguardar as `Conditions` estarem True
  * Criar Knative Eventing no namespace `knative-eventing`
    * Aguardar as `Conditions` estarem True
  * Criar Knative Kafka no namespace `knative-eventing`
  