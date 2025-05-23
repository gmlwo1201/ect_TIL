# 2학기 aws

# DynamoDB
## RDBMS
* 데이터의 관계에 집중하여 정형화된 형식으로 데이터 관리하는 DB 관리 시스템
  - 행과 열로 구성된 2차원 테이블 기반으로 정형화된 스키마 형식으로 데이터 저장
* **ACID** 형태로 Transaction 처리

## ACID
* Transaction : 데이터베이스의 상태 변화를 수행하는 작업들 묶은 단위
  - DB의 상태변화가 올바르게 이뤄질 수 있도록 논리적으로 묶은 단위
  - 여러 작업이 한꺼번에 완전히 성공 or 실패해야 함
  - ex. 내 계좌에서 돈 빠짐 + 친구 계좌에 돈 들어감 > 한세트
* Atomicity(원자성) : All or Nothing, 전부 성공 or 실패해야 함
* Consistency(일관성) : 트랜잭션 수행 전, 후에 일관적으로 데이터가 올바른 상태로 유지해야 함
  > 실행 전 후 데이터 상태가 논리적으로 맞아햐 함
* Isolation(독립성) : 트랜잭션 간에는 서로 독립적으로 수행돼야 함
  > 동시에 여러 트랜잭션 실행돼도 서로 간섭 없어야 함
* Durability(지속성) : 트랜잭션 수행 결과는 영구적으로 데이터베이스에 반영돼야 함
  > 디스크에 기록돼야 함

## RDBMS
* RDBMS(Relational Database Management System)
  - 데이터의 관계에 집중하여 정형화된 형식으로 데이터 관리하는 DB 관리 시스템
  - 행과 열로 구성된 2차원 테이블 기반으로 정형화된 스키마 형식으로 데이터 저장
* ACID 형태로 Transaction 처리
* 데이터 간 관계 기반으로 데이터 처리 가능
  - SQL, Join 등을 활용한 복잡한 쿼리 처리 가능
* 주요 AWS 서비스
  - Amazon RDS, Amazon Aurora, Amazon Redshift
* 주요 사용 사례
  - 대부분의 어플리케이션 메인 DB
