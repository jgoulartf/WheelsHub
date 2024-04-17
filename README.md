# WheelsHub - Simulação de Arquitetura de Microserviços

<img src="https://i.imgur.com/oh4OTor.png"></img>

> **Instituição:** UFERSA
 
> **Período:** 2023.2

> **Disciplina:** Sistemas Distribuídos

> **Professor:** Prof. Dr. Paulo Henrique Lopes Silva

> **Propósito:** Este projeto foi desenvolvido para fins acadêmicos.

> By: João Goulart && Arthur Lennon

Bem-vindo ao projeto WheelsHub, uma simulação de arquitetura de microserviços para gerenciamento de dados e autenticação. Este projeto é composto por quatro serviços principais: WheelsHubDataService0 e suas três réplicas: WheelsHubDataService1, WheelsHubDataService2, WheelsHubDataService3, WheelsHubAuthService, WheelsHubServiceRegistry e WheelsHubGateway. Aqui está um guia completo sobre como executar, configurar e entender cada componente deste sistema distribuído.

## Visão Geral

O WheelsHub é uma simulação de um sistema distribuído baseado em microserviços. Ele foi projetado para fornecer gerenciamento de dados e autenticação em um ambiente escalável e altamente disponível. Cada serviço desempenha um papel específico na arquitetura, garantindo a modularidade, flexibilidade e robustez do sistema.

## Visão Geral do Eureka e API Gateway

Além dos componentes principais do WheelsHub, dois elementos essenciais desempenham funções-chave na arquitetura de microserviços: o Eureka e o API Gateway.

### Eureka: Servidor de Registro de Serviço

O Eureka, utilizado no WheelsHub como o WheelsHubServiceRegistry, é um servidor de registro de serviço que permite aos microserviços registrarem-se e descobrirem uns aos outros dinamicamente. Ele desempenha um papel fundamental na construção de arquiteturas de microserviços altamente escaláveis e distribuídas, facilitando a comunicação entre os diversos componentes do sistema.

### API Gateway

O API Gateway, representado no WheelsHub pelo WheelsHubGateway, atua como o ponto de entrada para os clientes que desejam acessar os serviços do sistema. Ele direciona as solicitações dos clientes para os microserviços apropriados, garantindo uma camada de abstração entre o cliente e os serviços subjacentes. Além disso, o API Gateway pode realizar tarefas como autenticação, autorização, balanceamento de carga e monitoramento de tráfego, simplificando o desenvolvimento, a manutenção e a escalabilidade da arquitetura de microserviços.

O Eureka e o API Gateway são peças-chave na arquitetura do WheelsHub, garantindo a descoberta e o roteamento eficientes dos serviços, além de fornecer uma interface simplificada para os clientes interagirem com o sistema distribuído.


## Componentes

### 1. WheelsHubDataService0 e Réplicas (WheelsHubDataService1, WheelsHubDataService2, WheelsHubDataService3)

O WheelsHubDataService0 é o serviço principal responsável pelo gerenciamento de dados da aplicação. Ele oferece operações CRUD (Create, Read, Update, Delete) para os recursos da aplicação. 
> As réplicas WheelsHubDataService1, WheelsHubDataService2 e WheelsHubDataService3 são réplicas do serviço principal, garantindo alta disponibilidade e escalabilidade. Cada réplica possui sua própria instância de banco de dados, replicada para garantir a integridade dos dados.

### 2. WheelsHubAuthService

O WheelsHubAuthService cuida do gerenciamento de autenticação do sistema. Ele oferece recursos para autenticar usuários e gerar tokens de acesso. Assim como o WheelsHubDataService, também é um cliente Eureka para registro e descoberta de serviços.

### 3. WheelsHubServiceRegistry

O WheelsHubServiceRegistry é o servidor Eureka centralizado. Ele gerencia o registro e a descoberta de todos os serviços disponíveis na arquitetura. O Service Registry é essencial para garantir a comunicação entre os microserviços de forma dinâmica e escalável.

### 4. WheelsHubGateway

O WheelsHubGateway atua como um gateway de API para os microserviços. Ele direciona as solicitações dos clientes para os serviços apropriados, além de lidar com tarefas como autenticação, autorização e monitoramento de tráfego. É o ponto de entrada para a arquitetura de microserviços. O serviço do gateway roda na porta 8084.

## Configuração e Execução

Para configurar e executar o WheelsHub, siga estas etapas:

1. **Configuração do Ambiente**: Certifique-se de ter o JDK e o Apache Maven instalados em seu sistema.

2. **Clonar o Repositório**: Clone este repositório para sua máquina local.

   ```
   git clone https://github.com/jgoulartf/NetWheelsHub.git
   ```

3. **Compilação dos Serviços**: Navegue até o diretório de cada serviço e execute o comando Maven para compilar.

   ```
   cd WheelsHubDataService0
   mvn clean package

   cd WheelsHubDataService1
   mvn clean package

   cd WheelsHubDataService2
   mvn clean package

   cd WheelsHubDataService3
   mvn clean package
   
   cd ../WheelsHubAuthService
   mvn clean package
   
   cd ../WheelsHubServiceRegistry
   mvn clean package
   
   cd ../WheelsHubGateway
   mvn clean package
   ```

4. **Execução dos Serviços**: Inicie os serviços em ordem específica. Primeiro, inicie o Service Registry, seguido pelo Data Service, Auth Service e, finalmente, o Gateway.

   ```
   java -jar WheelsHubServiceRegistry/target/WheelsHubServiceRegistry.jar
   
   java -jar WheelsHubDataService0/target/WheelsHubDataService0.jar
   java -jar WheelsHubDataService1/target/WheelsHubDataService1.jar
   java -jar WheelsHubDataService2/target/WheelsHubDataService2.jar
   java -jar WheelsHubDataService3/target/WheelsHubDataService3.jar
   
   java -jar WheelsHubAuthService/target/WheelsHubAuthService.jar
   
   java -jar WheelsHubGateway/target/WheelsHubGateway.jar
   ```

5. **Acesso à Aplicação**: Acesse o Gateway através da URL fornecida após a inicialização e comece a interagir com os endpoints disponíveis.

## Contribuindo

Se você quiser contribuir para o projeto, sinta-se à vontade para fazer um fork do repositório, implementar melhorias e enviar um pull request. Todas as contribuições são bem-vindas!

Link para documentação postman contendo as rotas: https://encurtador.com.br/fyHIN

## Suporte

Se você tiver alguma dúvida, problema ou sugestão, por favor, abra uma issue no repositório. Estamos aqui para ajudar!

## License

Este projeto está licenciado sob a MIT License.

---
Esperamos que este guia seja útil para entender e executar o projeto WheelsHub. Se tiver mais perguntas ou precisar de assistência adicional, não hesite em nos contatar. Obrigado por escolher o WheelsHub para suas necessidades de simulação de arquitetura de microserviços! 🚀
