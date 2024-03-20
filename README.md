# WheelsHub - Simulação de Arquitetura de Microserviços

Bem-vindo ao projeto WheelsHub, uma simulação de arquitetura de microserviços para gerenciamento de dados e autenticação. Este projeto é composto por quatro serviços principais: WheelsHubDataService, WheelsHubAuthService, WheelsHubServiceRegistry e WheelsHubGateway. Aqui está um guia completo sobre como executar, configurar e entender cada componente deste sistema distribuído.

## Visão Geral

O WheelsHub é uma simulação de um sistema distribuído baseado em microserviços. Ele foi projetado para fornecer gerenciamento de dados e autenticação em um ambiente escalável e altamente disponível. Cada serviço desempenha um papel específico na arquitetura, garantindo a modularidade, flexibilidade e robustez do sistema.

## Componentes

### 1. WheelsHubDataService

O WheelsHubDataService é responsável pelo gerenciamento de dados da aplicação. Ele oferece operações CRUD (Create, Read, Update, Delete) para os recursos da aplicação. Além disso, é um cliente Eureka, o que significa que registra seus serviços no servidor Eureka para descoberta e balanceamento de carga.

### 2. WheelsHubAuthService

O WheelsHubAuthService cuida do gerenciamento de autenticação do sistema. Ele oferece recursos para autenticar usuários e gerar tokens de acesso. Assim como o WheelsHubDataService, também é um cliente Eureka para registro e descoberta de serviços.

### 3. WheelsHubServiceRegistry

O WheelsHubServiceRegistry é o servidor Eureka centralizado. Ele gerencia o registro e a descoberta de todos os serviços disponíveis na arquitetura. O Service Registry é essencial para garantir a comunicação entre os microserviços de forma dinâmica e escalável.

### 4. WheelsHubGateway

O WheelsHubGateway atua como um gateway de API para os microserviços. Ele direciona as solicitações dos clientes para os serviços apropriados, além de lidar com tarefas como autenticação, autorização e monitoramento de tráfego. É o ponto de entrada para a arquitetura de microserviços.

## Configuração e Execução

Para configurar e executar o WheelsHub, siga estas etapas:

1. **Configuração do Ambiente**: Certifique-se de ter o JDK e o Apache Maven instalados em seu sistema.

2. **Clonar o Repositório**: Clone este repositório para sua máquina local.

   ```
   git clone https://github.com/seu-usuario/wheelshub.git
   ```

3. **Compilação dos Serviços**: Navegue até o diretório de cada serviço e execute o comando Maven para compilar.

   ```
   cd WheelsHubDataService
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
   
   java -jar WheelsHubDataService/target/WheelsHubDataService.jar
   
   java -jar WheelsHubAuthService/target/WheelsHubAuthService.jar
   
   java -jar WheelsHubGateway/target/WheelsHubGateway.jar
   ```

5. **Acesso à Aplicação**: Acesse o Gateway através da URL fornecida após a inicialização e comece a interagir com os endpoints disponíveis.

## Contribuindo

Se você quiser contribuir para o projeto, sinta-se à vontade para fazer um fork do repositório, implementar melhorias e enviar um pull request. Todas as contribuições são bem-vindas!

## Suporte

Se você tiver alguma dúvida, problema ou sugestão, por favor, abra uma issue no repositório. Estamos aqui para ajudar!

## License

Este projeto está licenciado sob a MIT License.

---

Esperamos que este guia seja útil para entender e executar o projeto WheelsHub. Se tiver mais perguntas ou precisar de assistência adicional, não hesite em nos contatar. Obrigado por escolher o WheelsHub para suas necessidades de simulação de arquitetura de microserviços! 🚀
