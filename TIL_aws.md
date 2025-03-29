# **AWS TIL**

## AWS 핵심 서비스
* 컴퓨팅 서비스
  - EC2 (Elastic Compute Cloud): 가상 서버, 온디맨드/스팟 인스턴스
  - Lambda: 서버리스 컴퓨팅, 이벤트 기반 실행
* 스토리지 서비스
  - S3 (Simple Storage Service): 객체 스토리지, 정적 웹 호스팅
  - EBS (Elastic Block Store): EC2 인스턴스를 위한 블록 스토리지
* 네트워크 및 콘텐츠 전송
  - VPC (Virtual Private Cloud): AWS 내 네트워크 구성
  - Route 53: 도메인 네임 서비스(DNS)
  - CloudFront: CDN(콘텐츠 전송 네트워크)
* 데이터베이스 서비스
  - RDS (Relational Database Service): 관리형 데이터베이스(MySQL, PostgreSQL, MariaDB 등)
  - DynamoDB: NoSQL, 키-값 저장소
* 보안 및 접근관리
  - IAM (Identity and Access Management): 사용자 및 권한 관리
  - Security Group (보안 그룹): 인바운드 및 아웃바운드 트래픽 제어

## AWS 구조
* 리전
  - 가장 큰 단위, 리전 안에 다양한 가용영역 존재
  - AWS의 서비스가 제공되는 서버의 물리적 위치
  - 전 세계에 흩어져 있음, 큰 부분 (동남아, 유럽, 북아메리카 등)으로 묶여있음
  - 각 리전에는 고유한 코드 부여 ex) 서울: ap-northeast-2
  - 미국동부(버지니아 북부) 리전 > us-east-1, 뭐든지 최초
  - 리전별 가능한 서비스 다름
  - 리전 선택할 때 고려할 점
    - 지연속도 ex)북미서버면 북미리전
    - 법률 (데이터, 서비스 제광 관련) ex) 개인정보는 해당 국가 서버에 둬야한다 등
    - 사용가능한 AWS 서비스: 각 리전별로 런칭 다름
  - us-east-1 리전
    - 모든 AWS의 서비스가 최초로 서비스되는 리전
    - 기타 글로벌 서비스의 서비스 리전 ex) 빌링, 클라우드 프론트 (CDN)
* 가용영역 (Availability Zone)
  - 리전의 하부 단위
    - 하나의 리전은 2개 이상의 가용영역으로 구성
    - AZ라고 부름
    - 1개 이상의 물리 데이터 센터를 묶은 논리적 데이터 센터
  - 가용영역 구성
    - 하나 이상의 데이터 센터로 구성
    - AZ간의 연결은 매우 빠른 전용 네트워크로 구성
    - 단, 모든  AZ는 서로 100km 이내의 거리에 위치
    - 여러 재해에 대한 대비 및 보안
  - 가용영역 위치
    - 각 계정별로 AZ 코드와 실제 위치 다름
      - 계정 dev1의 AZ-A와 계정 dev2의 AZ-A와 다른 위치(랜덤)
      - 보안 이슈, 한 AZ로 몰림 현상 방지
