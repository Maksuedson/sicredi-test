# sicredi-teste
Projeto desenvolvido para o teste Sicredi - Backend Java

## Configuração
Java 11
Springboot 2.7.5
OpenCSV
Lombok

## Execução do Jar
1. Abrir o cmd na pasta raiz do projeto.
2. Na pasta raiz do projeto, tem um arquivo sicredi.csv. Este arquivo será utilizado na etapa 5.
3. Executar o comando -> mvn clean install
4. Acessar o diretorio /target que foi gerado no passo anterior, aqui será gerado o arquivo .jar.
5. Pegar o arquivo sicredi.csv e copiar para pasta target.
6. Executar o seguinte comando para processar o arquivo: java -jar Sicredi-test-1.0.jar sicredi.csv
7. Será exibido no cmd um log de retorno do serviço fake informando as etapas de processamento dos dados.
8. Na pasta onde o jar foi executado, será gerado um novo arquivo chamado sicredi_response.csv contendo a informação do resultado do serviço.
    



