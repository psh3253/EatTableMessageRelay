# EatTable's Message Relay Server

Spring Boot로 구현한 음식점 예약 서버입니다.

이 프로젝트는 현재 개발 중에 있으며 자세한 설명은 아래의 링크를 참고해주세요.

+ [EatTable's Server](https://github.com/psh3253/EatTable)

### Prerequisites / 선행 조건

아래 사항들이 설치 및 세팅이 되어있어야 합니다.

```
JDK 17 이상, MySQL Server 8.0 이상
```


### 사전 설정
1. application.properties 파일에 아래 내용 추가
```
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url={{MySQL URL}}
spring.datasource.username={{MySQL Username}}
spring.datasource.password={{MySQL Password}}
spring.kafka.properties.sasl.mechanism=PLAIN
spring.kafka.bootstrap-servers={{Kafka URL}}
spring.kafka.properties.sasl.jaas.config=org.apache.kafka.common.security.plain.PlainLoginModule required username='{{Kafka Username}}' password='{{Kafka Password}}';
spring.kafka.properties.security.protocol=SASL_SSL
```



