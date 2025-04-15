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
  - AWS 구조

## 엣지로케이션 (Edge location)
* AWS의 CloudFront(CDN) 등의 여러 서비스들을 가장 빠른 속도로 제공(캐싱)하기 위한 거점
* Global Accelerator와 유저를 연결하는 거점
  - 출장소 같은 개념보안 이슈, 한 AZ로 몰림 현상 방지
* 전 세계 여러장소에 흩어져 있음
* CDN (Contents Delivery Network): 컨텐츠를 보다 빠르게 전송하는 기술
  - ex) 미국에 있는 넷플릭스 한국에서 볼 때, 아프리카에 있는 트위치 한국에서 볼 때
* 엣지로케이션 없으면 : 느림, 스트리밍 불가

## 글로벌 서비스와 리전 서비스
* 서비스 종류
  - AWS에는 서비스 제공되는 지역의 기반에 따라 글로벌 서비스와 리전 서비스로 분류
* 글로벌 서비스
  - 데이터 및 서비스를 전 세계 모든 인프라가 공유
  - ex) IAM, Route53, WAF, Billing
* 지역 서비스
  - 특정 리전을 기반으로 데이터 및 서비스 제공
  - 대부분 지역 서비스

* ARN (Amazon Resource Names)
  - AWS의 리소스에 부여되는 고유 아이디
  - ex) arn:aws:ec2:us-east-1:123456789012:vpc...
* AWS 서비스 구조
  - VPC는 원칙적으로 퍼블릭인터넷으로 접근 불가
  - AWS 내부에 있는 서비스라도 원칙적으로 퍼블릭 인터넷 통해서 접근
  - 인터넷게이트웨이 통해 외부와 접근

* VPC (Virtual Private Cloud)
  - 외부와 격리된 네트워크 만들기 위해 탄생
  - AWS 계정 전용 가상 네트워크
  - 가상으로 존재하는 데이터 센터
  - 원하는 대로 사설망 구축 가능
  - 리전 단위
  - 구조 : AWS Cloud > Amazon VPC
 
* VPC 구성요소
  - subnet: VPC를 특정 범위로 나눈 범위
  - RouteTable: 네트워크 트래픽을 전달할 위치가 명시된 규칙 집합 테이블
  - Internet GW: VPC 리소스에서의 인터넷 통신을 활성화 하기 위한 게이트웨이
  - Nat GW: 네트워크 주소 변환을 통해 private subnet 에서 인터넷 통신을 연결하는 게이트웨이
  - VPC endpoint: Nat, IGW 등을 통하지 않고 AWS의 서비스를 비공개로 연결 가능하게 하는 서비스
 
## IAM (Identity and Access Management)
* AWS 리소스에 대한 접근을 안전하게 제어하는 서비스
* 누가(AWS 사용자)가 어떤 AWS 서비스에 어떤 권한으로 접근할 수 있는지를 관리하는 역할
* IAM 요소
  - 사용자(User) → AWS 리소스에 접근하는 개별 계정
  - 그룹(Group) → 여러 사용자에게 동일한 권한을 부여할 수 있는 그룹
  - 역할(Role) → 특정 권한을 임시적으로 부여하는 역할 (AWS 서비스 간 권한 위임 가능)
  - 정책(Policy) → JSON 형식으로 작성된 접근 권한 규칙
  - MFA(Multi-Factor Authentication) → 다중 인증으로 보안 강화
* 주요 기능
  - AWS 리소스 접근 제어
    - IAM을 사용하여 AWS 서비스와 리소스에 대한 접근을 세밀하게 관리할 수 있음
    - 특정 사용자나 그룹에게 EC2, S3, RDS 등 개별 서비스에 대한 권한을 부여 가능
  - 정책(Policy) 기반 접근 관리
    - JSON 형식의 Policy 문서를 사용하여 세부적인 접근 권한 설정
  - IAM 역할(Role) 및 서비스 연동
    - EC2, Lambda, RDS 등의 AWS 서비스에 특정 권한을 부여 가능
    - ex) EC2 인스턴스가 S3 버킷 데이터 읽을 수 있도록 IAM 역할 부여 가능
  - MFA(다중 인증) 적용 가능
    - IAM 사용자 로그인 시 OTP 등 추가 인증 단계(MFA)를 설정하여 보안 강화
  - AWS 루트 계정 보호
    - AWS 계정을 생성하면 root user가 생성됨, 모든 권한을 가짐
    - 보안 강화를 위해 루트 계정 대신 IAM 사용자를 생성하여 사용하고, 루트 계정의  MFA 활성화 권장
* 주의할 점
  - 루트 계정(root user)은 최소한으로 사용
  - 최소 권한 원칙(Least Privilige Principle) 준수 → 꼭 필요한 권한만 부여
  - IAM 액세스 키(access key) 노출 금지 → GitHub 등 코드 저장소에 올리지 않기
  - IAM 정책을 잘못 설정하면 보안 사고 발생 가능 → 사용자별 최소 권한 유지
**********
# EC2
* 가상서버를 제공하는 AWS 서비스
* 인스턴스: EC2에서 생성한 개별 가상 서버

## 인스턴스 수명 주기

![image](https://github.com/user-attachments/assets/776b0402-64ca-4c88-9821-543526ae1697)

## PEM, PPK
* PEM (.pem)
  - PEM (Privacy-Enhanced Mail) 형식으로 저장된 키 파일
  - Linux, MacOS에서 기본적으로 지원
  - ssh 명령어로 쉽게 사용 가능
* PPK (.ppk)
  - PPK (PuTTY Private Key) 형식으로 저장된 키 파일
  - Windows에서 많이 사용하는 SSH 클라이언트인 PuTTY에서 사용
**********
# S3
- AWS에서 EC2 서비스와 더불어 가장 오래되고 기본이 되는 객체 스토리지 서비스
- Amazon S3 는 확장성, 내구성, 보안성 뛰어남
- 99.9% (11 9's) 내구성 제공, 데이터 손실 최소화
- 데이터 저장 공간 거의 무제한
- 전 세계 수백만 기업의 데이터 저장하는 핵심 서비스

## S3 특징
* 객체 스토리지 서비스 : 파일 저장 전용 (EBS/EFS 등 블록 스토리지와 구분) > 어플리케이션 설치 불가
* 글로벌 서비스 단위 : 데이터는 특정 리전에 저장
* 무제한 용량 지원 : 단일 객체는 0byte ~ 5TB 저장 가능
* 최소 3개 이상 물리적으로 분리된 가용 영역에 데이터 복제
* 높은 내구성, 고가용성 제공
* OS 도움 없이 객체별 접근 가능, 데이터 저장 및 활용 용이

## 버킷과 객체
* 버킷
  - S3에서 저장공간을 구분하는 단위 (디렉토리 개념)
  - 전 세계 고유 이름 가져야 함 > 리전과 무관하게 중복 불가
  - 한번 생성 시, 이름 변경 불가
* 객체: S3에 저장되는 기본 매체
  - Owner: 소유자 정보
  - Key: 객체 이름(경로 역할)
  - Value: 파일 데이터 자체
  - Version ID: 파일 버전 식별자(버전 관리 활성화 시에만 생성)
  - Metadata: 파일 관련 정보(기본 메타데이터 외에 사용자 정의도 가능)
 
## S3 내구성 & 가용성
* 내구성 : 파일을 잃어버리지 않을 능력(99.999999999%, 극히 낮음)
* 가용성 : 내가 원할 때 쓸 수 있는 능력(*SLA 99.9%)
* 최소 3개 AZ에 중복 저장(Standard 기준)
* SLA : Service Level Agreement(서비스 수준 협약)
  - 이 서비스를 얼마나 안정적으로 사용할 수 있는가
  - 99.99% - 1년 중 약 52분 이하 다운타임/1년 중 약 1일 18시간 이하 다운
## S3 스토리지 클래스
* 클래스 별로 저장의 목적, 예산의 방법에 따라 다른 저장 방법 적용
* S3 standard
  - 자주 액세스하는 데이터
    - 주요 사용 데이터
    - 빅데이터 분석
    - 작업용 임시 백업
* S3-IA
  - 액세스 빈도 낮은 데이터
    - 파일 동기화
    - 데이터 백업
    - 재해 복구용
* glacier
  - 거의 액세스하지 않는 데이터
    - 장기 보존용
    - 시스템 백업

![Image](https://github.com/user-attachments/assets/4389b951-1096-4285-ab5e-6c4490f5c19b)
![Image](https://github.com/user-attachments/assets/b401d952-ba78-46a5-8a85-76ec0867d9f7)

## S3 보안
* 기본적으로 모든 객체는 Private(비공개) 상태로 생성
* 퍼블릭 접근은 명시적으로 허용해야함(웹 호스팅 등)
* Bucket Policy: 버킷 단위 정책
* IAM 정책
  - Identity-based Policy (자격 증명 기반 정책)
    - IAM 사용자, 그룹, 역할 부여하는 정책
    - "누가 무엇을 할 수 있는가" 설정
  - Resource-based Policy (리소스 기반 정책)
    - 리소스(S3, SQS 등)에 직접 부여하는 정책
    - "이 리소스에 누가 접근 가능한가"를 정의
## S3 버킷 정책
* S3 리소스(버킷 및 그 안의 객체들)에 대한 접근 제어 정책
* JSON 포멧의 문서
* 버킷 단위로 적용되는 리소스 기반 정책 > 버킷 안의 모든 객체에 적용 가능
* IAM 정책과 달리 리스소 자체에 붙음
* 언제, 어디서 누가, 무엇을, 어떻게 할 수 있는지 정의
* ex) arn:aws:s3:::my-bucket/images/* > 해당 경로에 있는 모든 객체 대상으로 권한 설정
* 익명 사용자 또는 다른 계정에 대한 권한 부여도 가능
```json
{
"Effect": "Allow",
"Principal": "*",
"Action": "s3:GetObject",
"Resource": "arn:aws:s3:::demo.rwlecture.com/*"
}
```
**********
# AWS CLI
## AWS CLI란?
  - Amazon Web Services Command Line Interface
  - AWS 리소스(S3, EC2, IAM 등)를 터미널/명령 프롬프트로 제어할 수 있는 도구
  - 콘솔 클릭하지 않아도 명령어만으로 S3 버킷 생성, 파일 업로드, EC2 실행 같은 작업 가능
* AWS S3 CLI 주요 명령어
  - aws s3 ls : 버킷 목록 확인
  - **aws s3 ls s3://버킷이름/** : 버킷 내부 파일 목록
  - **aws s3 cp 로컬경로 s3://버킷이름/경로** : 파일 업로드
  - **aws s3 cp s3://버킷이름/경로 로컬경로** : 파일 다운로드
  - 폴더 업로드/다운로드
    - 다운로드 : **aws s3 cp s3://my-bucket/folder ./local-folder --recursive**
    - 업로드: **aws s3 cp ./local-folder s3://my-bucket/folder --recursive**
*********
# Auto Scaling
## Scaling이란?
  - 애플리케이션/시스템 성능 높이기 위해 컴퓨팅 리소스를 확장/축소하는 것
  - 요청이 많아질 때 처리 가능하도록 서버 키우는 것
## Scale-Up (Vertical Scaling)
![image](https://github.com/user-attachments/assets/7cecb4b5-0d7c-4db0-9a1d-a39773658991)

* 장점
  - 단순하고 설정 쉬움
* 단점
  - 물리적 한계 존재
  - 재시작 필요
  - 서버 하나에만 의존 > 장애 발생 시 위험
## Scale-Out (Horizontal Scaling)
![image](https://github.com/user-attachments/assets/df718c28-9efe-4afc-b828-7c333752dd65)

* 장점
  - 무중단 확장 가능
* 단점
  - 복잡한 아키텍쳐
  - Load Balancer(ELB) 등 구성 필요

## Auto Scaling
  - 트래픽 상황에 맞춰 서버 수 자동으로 확장/축소해주는 AWS 서비스
    - 트래픽 증가 > 인스턴스 증가
    - 사용량 감소 > 인스턴스 축소
    - 리소스 비용 절감 + 고가용성 유지
## 구성 요소
* Launch Template
  - 새 인스턴스 생성할 때 필요한 설정 들어있는 설계도
  - 어떤 AMI(이미지)로 만들지
  - 인스턴스 타입(t3, micro 등)
  - 키 페어, 보안 그룹
  - UserData 스크립트(초기 세팅 자동화)
* Auto Scaling Group(ASG)
  - 인스턴스 묶어서 관리하는 단위
  - 최소/최대/원하는(Desired) 인스턴스 수 설정
  - 실제 인스턴스 수 계속 모니터링하고 자동 조절
  - Availability Zone 간 분산 가능
* Scaling Policy(스케일링 정책)
  - 언제, 어떻게 서버 수 조절할 지에 대한 규칙
  - Target Tracking : 평균 CPU 60% 유지 같은 목표 설정
  - Step Scaling : CPU 70 ~ 80% > + 1대, 80% 이상 > + 2대
  - Scheduled Scaling : 특정 시간에 확장
* CloudWatch 알람
  - 지표 감시하다가 스케일링 정책 실행하는 역할
  - CPU 사용률, 네트워크 트래픽, 메모리 등 모니터링
  - 조건 충족 시 Scaling Policy 트리거
  - 실시간 알람 + 로그 기록 가능
 
## Auto Scaling 생성
* 사용자 데이터
```java
#!/bin/bash
yum update -y
amazon-linux-extras install -y epel
yum install -y httpd stress-ng
systemctl enable httpd
systemctl start httpd
echo "Hello from Auto Scaling Instance" > /var/www/html/index.html
```
* 상태 확인 유예 기간
  - 인스턴스 부팅되자마자 "상태 확인 실패"로 종료되지 않게 유예 기간 설정
* ASG 설정 값
  - 원하는 용량 (Desired Capacity) : 현재 운영하고 싶은 인스턴스 수
  - 최소 용량 (Min Capacity) : 줄어들 수 있는 하한선
  - 최대 용량 (Max Capacity) : 늘어날 수 있는 상한선
  - 원하는 용량은 **최소보다 작을 수 없고 최대보다 클 수 없음**
* 원하는 용량, 최소 용량, 최대 용량 2로 설정 > 서버 하나 종료
  > 다른 인스턴스가 실행됨
  > 원하는 용량, 최소 용량, 최대 용량 1로 설정
    > 생성된 서버 중 하나 종료됨

*********
# ELB
## ELB - AWS에서 제공하는 트래픽 분산 서비스
* 사용자가 EC2 접근하려면 모든 EC2 IP 주소 알아야 함
* 만약 EC2 한대 종료된다면? 만약 사용자가 관리 못할 정도로 EC2가 많다면?
  > 그래서 ELB 필요
![image](https://github.com/user-attachments/assets/8ac4f86d-318c-498d-9173-330aaab43875)

## Elastic Load Balancer(ELB)
* 들어오는 어플리케이션 트래픽을 Amazon 인스턴스, 컨테이너, IP 주소, 람다 함수 같은 여러 대상에 자동 분산
* 단일 가용영역, 여러 가용 영역에서 다양한 어플리케이션 부하 처리 가능
* ELB에서 제공하는 3가지 로드밸런서는 모두 어플리케이션 결합성에 필요한 고가용성, 자동 확장/축소, 강력한 보안 보유
## ELB 특징
* 다수 서비스에 트래픽 분산 시켜주는 서비스
* Health Check: 직접 트래픽 발생시켜 Instance 살아있는지 확인
* AutoScaling 연동 가능
* 여러 가용영역에 분산 가능
* 지속적으로 IP 주소 변경, IP 고정 불가: 항상 도메인 기반으로 사용
## ELB 종류
* Application Load Balancer: 똑똑함
  - 트래픽 모니터링하여 라우팅 가능
  - image.test.com > 이미지 서버, web.test.com > 웹 서버로 트래픽 분산
* Network Load Balancer: 빠름
  - TCP 기반 빠른 트래픽 분산
  - Elastic IP 할당 가능
* Classic Load Balancer: 옛날 버전
  - 현재 거의 사용X
## ALB - Target Group
* 타겟 그룹: ALB가 트래픽 분산시킬 대상(Target)을 논리적으로 묶어놓은 그룹
* 타겟 그룹 기준으로 요청 분배
* EC2 인스턴스, IP 주소, Lambda 함수, ELB 타겟 그룹 가능
* 역할
  - ALB > 라우팅 규칙에 따라 타겟 그룹으로 요청 전달
  - 타겟 그룹은 내부에서 등록된 인스턴스 중 헬스체크 통과한 대상에게 트래픽 전달
  - 타겟 그룹별로 포트, 프로토콜, 헬스체크 설정 가능
* 구성 예<br>
![image](https://github.com/user-attachments/assets/3bae189b-19ad-4c9f-95bc-44f174752d28)
