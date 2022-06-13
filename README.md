# Deep Dive de Serverless

Deep Dive sobre Serverless no Openshift. Instalando Operators e implantando aplicações e funções utilizando `Quarkus`.


## Requisitos
* Ambiente para Quarkus: `VSCode`, `JDK`, `mvn`
* CLI configurado: `kn`, `oc`


## Projetos

* kafka-producer
  * Envia várias mensagens para um kafka implantado no mesmo `namespace`: `my-kafka`. Acessar paths: /1, /10, /100 ou /1000
    * Nome do cluster do kafka: `my-cluster`
    * Tópico: `my-topic`
* service-and-eventing
  * Aplicação padrão com Quarkus, não tem nenhuma extensão específica para serverless/function.
  * Já existe imagens disponíveis para facilitar o Deep Dive
    * [viniciusfcf/quarkus-serving-and-eventing-native](https://hub.docker.com/r/viniciusfcf/quarkus-serving-and-eventing-native)
    * [viniciusfcf/quarkus-serving-and-eventing](https://hub.docker.com/r/viniciusfcf/quarkus-serving-and-eventing)
* quarkus-funq-http
  * Projeto que disponibiliza uma função para requisições `HTTP`
  * Projeto criado utilizando o comando: `kn func create -l quarkus -t http quarkus-funq-http`
* quarkus-funq-event
  * Projeto que disponibiliza uma função que recebe eventos
  * Projeto criado utilizando o comando: `kn func create -l quarkus -t cloudevent quarkus-funq-event`

## Como instalar o comando `kn`

* [Link](https://docs.openshift.com/container-platform/4.10/serverless/cli_tools/installing-kn.html#installing-cli-web-console_installing-kn)


## Apresentação 
[Visualizar](https://docs.google.com/presentation/d/1T5T9faNJpU_8nnLeApG_fHK6JaYNjZiz4-I_h4ZIiE4/edit#slide=id.g6b619a1e04_0_2548)


## Demo

De preferência, ter 2 clusters. O primeiro zerado, o segundo com todo o ambiente configurado para pularmos o primeiro passo de "Configuração do OpenShift"

## Ambiente

* OpenShift 4.9
* Criar um BOT no Telegram a partir do [BotFather](https://t.me/BotFather). Ex: 15502170628:AAFTYAVcKd2EKYIKcOP9JLpnck1qk8CBOQc1

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
    * O field `source` deve estar com `enabled` = `true`
    * Remover o field broker
    * em `channel` colocar o `bootstrapServers` = `my-cluster-kafka-bootstrap.my-kafka.svc:9092` ou `enabled` = `false`
  
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

* quarkus-func-event e quarkus-funq-event
  * `kn func deploy -n namespace` dentro de cada projeto
  
## Como criar um Ping Source

* Adicionar um Event Source, do tipo `Ping Source` com os campos `data`: `{"message": "Hello world!"}`, `schedule`: `* * * * *` e `contentType`: `application/json`
  * De minuto em minuto será enviado o JSON acima

## Test using `kn`

`kn func invoke --content-type=application/json --data="Hello World"`

## Cloud Event

https://cloudevents.io/

## Link para este Repositório

* https://github.com/viniciusfcf/red-hat-serverless

## Documentacao Function

https://docs.openshift.com/container-platform/4.10/serverless/functions/serverless-developing-quarkus-functions.html

Deploy:

https://docs.openshift.com/container-platform/4.10/serverless/functions/serverless-functions-getting-started.html#serverless-deploy-func-kn_serverless-functions-getting-started

## Serverless documentation

[LINK](https://docs.openshift.com/container-platform/4.10/serverless/serverless-release-notes.html)

## Bugs encontrados

* o comando `kn func invoke` de uma function `http` não está funcionando, já foi aberto `Issue`
  * Como fazer funcionar: alterar no arquivo `func.yaml` o `format: ` para `cloudevent`
* Ao criar um `Ping Source` pela web console, está incluindo o campo `jsonData` que é deprecated.
  * Versão 4.11 já está corrigido [PR](https://github.com/openshift/console/pull/11548)