# dev-rabbitmq
Desenvolvimento de um projeto para estudo de RabbitMQ - listener e send message, utilizando Java11.

```bash
# Clone este repositório
$ git clone https://github.com/tiagomassashi/dev-rabbitmq.git
```


```yaml
# Configuração RabbitMQ docker-compose.yml
services:
 rabbitmq:
    image: rabbitmq:3-management
    container_name: rabbitmq
    restart: always
    ports:
      - 5672:5672
      - 15672:15672
    volumes:
      - ./data:/var/lib/rabbitmq/
    environment:
      - RABBITMQ_DEFAULT_USER=nagata
      - RABBITMQ_DEFAULT_PASS=nagata
```

## Funções
### Enviar mensagem na fila para incluir operação:
- URL: http://localhost:8080/dev-rabbitmq/api/v1/operation
- Method: PATCH
- Body:
```json
{
    "messageType": "INSERT",
    "reference": "ID_202202211844",
    "customerCode": "12345678",
    "negotiationDate": "2022-02-21"
}
```

### Enviar mensagem na fila para atualizar operação:
- URL: http://localhost:8080/dev-rabbitmq/api/v1/operation
- Method: PATCH
- Body:
```json
{
    "messageType": "UPDATE",
    "reference": "ID_202202211844",
    "operationStatus": "PROCESSING"
}
```

### Enviar mensagem na fila para excluir operação:
- URL: http://localhost:8080/dev-rabbitmq/api/v1/operation
- Method: PATCH
- Body:
```json
{
    "messageType": "DELETE",
    "reference": "ID_202202211844"
}
```

### Actuator:
- Health URL: http://localhost:8080/dev-rabbitmq/actuator/health
- Method: GET

```json
{
    "status": "UP",
    "components": {
        "diskSpace": {
            "status": "UP",
            "details": {
                "total": 494384795648,
                "free": 363677274112,
                "threshold": 10485760,
                "exists": true
            }
        },
        "ping": {
            "status": "UP"
        },
        "rabbit": {
            "status": "UP",
            "details": {
                "version": "3.9.13"
            }
        }
    }
}
```

- Info URL: http://localhost:8080/dev-rabbitmq/actuator/info
- Method: GET

```json
{
    "app": {
        "groupId": "br.com.nagata.dev",
        "artifactId": "dev-rabbitmq",
        "version": "0.0.1",
        "java": {
            "version": "17.0.2"
        }
    }
}
```

### Swagger
- URL: http://localhost:8080/dev-rabbitmq/swagger-ui/index.html#/

##

### Contato

[![Linkedin](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/tiago-nagata-5ba95ab6/)
