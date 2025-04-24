# AWS 중간고사 대비
## *스케일 업/아웃(Vertical, Horizental)
* Scale-Up (Vertical Scaling)
![image](https://github.com/user-attachments/assets/7cecb4b5-0d7c-4db0-9a1d-a39773658991)

* 장점
  - 단순하고 설정 쉬움
* 단점
  - 물리적 한계 존재
  - 재시작 필요
  - 서버 하나에만 의존 > 장애 발생 시 위험

* Scale-Out (Horizontal Scaling)
![image](https://github.com/user-attachments/assets/df718c28-9efe-4afc-b828-7c333752dd65)

* 장점
  - 무중단 확장 가능
* 단점
  - 복잡한 아키텍쳐
  - Load Balancer(ELB) 등 구성 필요

## *Auto Scaling Group(설정값)
* Launch Template
  - 새 인스턴스를 생성할 때 필요한 설정을 담고 있는 설계도
  - 어떤 AMI(이미지)로 만들지
  - 인스턴스 타입(t3.micro 등)
  - 키 페어, 보안 그룹
  - UserData 스크립트 (초기 셋팅 자동화)
* ASG
  - 인스턴스를 묶어서 관리하는 단위
  - 최소/최대/원하는(Desired) 인스턴스 수 설정
  - 실제 인스턴스 수를 계속 모니터링하고 자동 조절
  - Availability Zone 간 분산 가능
  - 설정 값 조건
    - 최소 용량 ≤ 원하는 용량 ≤ 최대 용량
* Scaling Policy
  - 언제, 어떻게 서버 수를 조절할지에 대한 규칙
  - Target Tracking: 평균 CPU 60% 유지처럼 목표 설정
  - Step Scaling: CPU 70~80% → +1대, 80% 이상 → +2대
  - Scheduled Scaling: 특정 시간에 확장 (ex. 매일 오후 6시)
* CloudWatch
  - 지표를 감시하다가 스케일링 정책을 실행하는 역할
  - CPU 사용률, 네트워크 트래픽, 메모리 등 모니터링
  - 조건 충족 시 Scaling Policy 트리거
  - 실시간 알람 + 로그 기록 가능
  
## *온프레미스, 클라우드 정의 (P, S...)
* 온프레미스 - 전통적인 방법
  - 사용자 입장에서 공간, 자원 등 모은 것을 자체적으로 구축 및 운영하는 방식
* 클라우드
  - 인터넷 구간 어딘가 눈에 보이지 않는 형태로 구성된 IT 자원 집합
* 클라우드 컴퓨팅 - 온디맨드
  - 인터넷을 통해 IT 자원 요구에 따라 사용한 만큼 비용을 지불하는 서비스
* 클라우드 컴퓨팅 유형
  - IaaS - 서버, 스토리지, 네트워크, 가상화 제공 / 사용자가 OS 및 애플리케이션 관리
    > EC2, S3, VPC, EBS (스타트업이 자체 서버 없이 EC2와 S3로 웹사이트 호스팅)
  - Paas - 개발 및 배포 위한 플랫폼 제공 / 서버 및 OS 관리 필요X
    > AWS Elastic Beanstalk, Lambda, RDS, Fargate (개발자가 Lambda 사용해 서버 관리 없이 코드 실행)
  - Saas - 사용자가 소프트웨어 설치 없이 이용 / 유지보수 및 업데이트 제공자가 수행
    > Amazon WorkDocs, Amazon Chrime, AWS Managed Services (기업이 Amazon Chime 통해 원격 회의 진행)
    > 타 클라우드 : Google Workspace, GitHub, Dropbox, Microsoft365

## *리전, 가용영역
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
  ![image](https://github.com/user-attachments/assets/de7846f9-c40e-4999-b710-f92c24b83360)

## *엣지 로케이션
* AWS의 CloudFront(CDN) 등의 여러 서비스들을 가장 빠른 속도로 제공(캐싱)하기 위한 거점
* Global Accelerator와 유저를 연결하는 거점
  - 출장소 같은 개념보안 이슈, 한 AZ로 몰림 현상 방지
* 전 세계 여러장소에 흩어져 있음
* CDN (Contents Delivery Network): 컨텐츠를 보다 빠르게 전송하는 기술
  - ex) 미국에 있는 넷플릭스 한국에서 볼 때, 아프리카에 있는 트위치 한국에서 볼 때
* 엣지로케이션 없으면 : 느림, 스트리밍 불가

## *글로벌/리전 서비스
* 서비스 종류
  - AWS에는 서비스 제공되는 지역의 기반에 따라 글로벌 서비스와 리전 서비스로 분류
* 글로벌 서비스
  - 데이터 및 서비스를 전 세계 모든 인프라가 공유
  - ex) IAM, Route53, WAF, Billing
* 지역 서비스
  - 특정 리전을 기반으로 데이터 및 서비스 제공
  - 대부분 지역 서비스

## *IMA, *MFA*
* AWS 리소스에 대한 접근을 안전하게 제어하는 서비스
* 누가(AWS 사용자)가 어떤 AWS 서비스에 어떤 권한으로 접근할 수 있는지를 관리하는 역할
* IAM 요소
  - 사용자(User) → AWS 리소스에 접근하는 개별 계정
  - 그룹(Group) → 여러 사용자에게 동일한 권한을 부여할 수 있는 그룹
  - 역할(Role) → 특정 권한을 임시적으로 부여하는 역할 (AWS 서비스 간 권한 위임 가능)
  - 정책(Policy) → JSON 형식으로 작성된 접근 권한 규칙
  - **MFA(Multi-Factor Authentication) → 다중 인증으로 보안 강화**
* 주요 기능
  - AWS 리소스 접근 제어
    - IAM을 사용하여 AWS 서비스와 리소스에 대한 접근을 세밀하게 관리할 수 있음
    - 특정 사용자나 그룹에게 EC2, S3, RDS 등 개별 서비스에 대한 권한을 부여 가능
  - 정책(Policy) 기반 접근 관리
    - JSON 형식의 Policy 문서를 사용하여 세부적인 접근 권한 설정
  - IAM 역할(Role) 및 서비스 연동
    - EC2, Lambda, RDS 등의 AWS 서비스에 특정 권한을 부여 가능
    - ex) EC2 인스턴스가 S3 버킷 데이터 읽을 수 있도록 IAM 역할 부여 가능
  - **MFA(다중 인증) 적용 가능**
    - IAM 사용자 로그인 시 OTP 등 추가 인증 단계(MFA)를 설정하여 보안 강화
  - AWS 루트 계정 보호
    - AWS 계정을 생성하면 root user가 생성됨, 모든 권한을 가짐
    - 보안 강화를 위해 루트 계정 대신 IAM 사용자를 생성하여 사용하고, 루트 계정의  MFA 활성화 권장
* 주의할 점
  - 루트 계정(root user)은 최소한으로 사용
  - 최소 권한 원칙(Least Privilige Principle) 준수 → 꼭 필요한 권한만 부여
  - IAM 액세스 키(access key) 노출 금지 → GitHub 등 코드 저장소에 올리지 않기
  - IAM 정책을 잘못 설정하면 보안 사고 발생 가능 → 사용자별 최소 권한 유지
 
## EC2 생성
* AWS 콘솔 로그인
* EC2 서비스 접근(서울 리전)
* 인스턴스 > 인스턴스 시작
* 이름 설정 / 애플리케이션 및 이미지 : Amazon Linux 2 AMI
* 인스턴스 유형 설정 (t3.nano ~> 비싸짐)
* 키 페어 생성(있으면 선택, 생성 유형은 RSA, .pem/절대 공개X)
* 보안 그룹 생성(있으면 선택)
* 다음에서 SSH 트래픽 허용 체크 > 시작

  - EC2: 가상 서버 제공하는 AWS 서비스
  - 인스턴스: EC2에서 생성한 개별 가상 서버
 
## PEM. PPK
* PEM
  - Linux, macOS에서 기본적으로 지원, ssh 명령어로 쉽게 사용 가능
* PPK
  - Windows에서 많이 사용하는 SSH 클라이언트인 PuTTY에서 사용

## S3
- AWS에서 EC2 서비스와 더불어 가장 오래되고 기본이 되는 객체 스토리지 서비스
- Amazon S3 는 확장성, 내구성, 보안성 뛰어남
- 99.9% (11 9's) 내구성 제공, 데이터 손실 최소화
- 데이터 저장 공간 거의 무제한
- 전 세계 수백만 기업의 데이터 저장하는 핵심 서비스
* 특징
  - 객체 스토리지 서비스: 파일 저장 전용(EBS/EFS 등 블록 스토리지와 구분) > 어플리케이션 설치 불가
  - 글로벌 서비스 단위: 데이터는 특정 리전에 저장
  - 무제한 용량 지원: 단일 객체는 0byte ~ 5TB 저장 가능
  - 최소 3개 이상 물리적으로 분리된 가용 영역에 데이터 복제
  - 높은 내구성, 고가용성
  - OS 도움 없이 객체별 접근 가능, 데이터 저장/활용 용이

## S3 스토리지 클래스
* (이름 - 내구성 - 가용성 - AZ 수 - 최소 기간 - 사용 사례 - 고려 사항 / 아래로 갈 수록 비싸짐)
* *Standard* - 99.999999% - 99.99 - 3개 이상 - X - 자주 액세스하는 데이터 - _
* *Intelligant-Tiering* - .. - 99.99 - 3개 이상 - X - 액세스 패턴 예측 불가능한 데이터 - 객체별 액세스 패턴 모디터링 및 자동 요금 최적화 가능
* *Standard-IA* - .. - 99.99 - 3개 이상 - 3개 이상 - 30일 - 수명 길고 자주 액세스하지 않는 데이터 - 객체당 검색 비용 발생
* *One Zone-IA* - 99.9999% - 99.95 - 1개 - 30일 - 중요도 낮고 자주 액세스하지 않는 데이터 - 단일 AZ에만 저장, 더 낮은 내구성
* *Glacier* - .. - 99.99(복원 후) - 3개 이상 - 90일 - 장기 보관 데이터(ex. 백업, 아카이브) - 즉시 엑세스 불가, 복원 시 지연
* *Glacier Flexible* - .. - .. - 3개 이상 - 90일 - 장애 복구용, 백업 데이터 등 - 분~시간 단위 복원 시간 소요
* *Glacier Deep Archive* - .. - .. - 3개 이상 - 90일 - 법적 보관 의무 있는 데이터 - 복원까지 최대 12시간 소요
* *on Outposts* - _ - _ - 온프레미스 - _ - 온프레미스 환경에서의 데이터 저장 - 로컬 스토리지 환경, S3 SDK 및 IAM 연동 필요
> 파일 저장 : Standard > Standard IA > Intalligent >  One Zone-IA <br>
> 아카이브 : Glacier > Glacier Flexible > Glacier Deep Archive

## 버킷, 객체
* 버킷
  - S3에서 저장 공간 구분하는 단위(디렉토리 개념)
  - 전 세계 고유 이름 가져야 함 > 리전과 무관하게 중복 불가
  - 한번 생성 시 이름 변경 불가
* 객체
  - S3에 저장되는 기본 매체
  - Owner: 소유자 정보
  - Key: 객체 이름(경로 역할)
  - Value: 파일 데이터 객체
  - Version ID: 파일 버전 식별자(버전 관리 활성화 시에만 생성)
  - Metadata: 파일 관련 정보(기본 메타데이터 외 사용자 정의도 가능)

## *S3 실습 내용
* 버킷 생성
  - 암호화 유형 - Amazon S3 관리형 키(SSE-S3) 사용한 서버 측 암호화
  - 버킷 키 - 활성화
* 버킷에 index.php 넣기
* ec2 생성
* IAM 인스턴스 프로파일 - 본인 계정 선택
* 사용자 데이터 넣고 인스턴스 시작
```
#!/bin/bash
yum install httpd php -y
aws s3 cp s3://sgu-202500-s3/index.php /var/www/html --region ap-northeast-2
systemctl restart httpd
```

## *Security Group
> EC2 생성 > 보안그룹 생성 > 인스턴스 시작 > EC2 보안 > 보안그룹 > 인바운드 규칙 편집 > 규칙 추가 > 모든 ICMP-IPv4 > 내IP > 규칙 저장

## 인스턴스 유형 변경
* *인스턴스 중지* > 인스턴스 설정 > 인스턴스 유형 변경

## EC2 모니터링
* 생성한 EC2 PuTTY로 접근
* 슈퍼 유저로 변경: sudo su -
* http 데몬 설치: yum install httpd -y
* http 데몬 실행: systemctl start httpd
* index.html 만들기: cd /var/www/html/, vi index.html
* 이렇게만 하고 ec2 IP 검색하면 안나옴 > EC2 인스턴스 모니터링 설정 필요

## EC2 인스턴스 모니터링 설정
* 모니터링 탭
* 대시보드에 추가 > 새로 생성
* 새 대시보드 이름 입력 > 생성
* 대시보드에 추가 > 저장
* 인스턴스 세부 모니터링 관리 활성화

## 경보 생성
* Cloud Watch > 경보 생성 > 지표 선택 > CPUUtilization > 인스턴스 별 지표 > 나의 인스턴스 체크 > 옵션 > 누적 면적 > 지표 선택
* 기간 설정 > 임계 값 설정 > 추가 구성 펼치기 > 누락된 데이터 처리 > 다음
* 새 주제 생성 > High_CPU_Alarm > 알림 수신할 이메일 > 이메일에서 구독 승인

* 경보 울리기
  - Amazon Linux 리포지토리 package 활성화: amazon-linux-extra install -y epel
  - CPU 부하 설정 툴 설치: yum install -y stress-ng
  - CPU 부하 발생: stress-ng --cpu 1 --timeout 10m --metrics --times
  - 네트워크 경고 만들기
  - sudo yum install -y inperf3
  - iperf3 -s & (서버 모드 활성화) > iperf3 -s & sleep 2 && stress-ng --cpu 1 --timeout 10m --metrics --times & iperf3 -c 127.0.0.1 -t 600 -b 100M (네트워크 부하 발생)
  - CloudWatch 지표: NetworkOut, 임계 값 설정, ec2 재기동 > 이메일 알람

## *특정 IP에서만 다운로드 허용하는 법
```
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "PublicWebsiteAllow",
            "Effect": "Allow",
            "Principal": "*",
            "Action": "s3:GetObject",
            "Resource": [
                "arn:aws:s3:::sgu-202500-s3-test/index.html",
                "arn:aws:s3:::sgu-202500-s3-test/class/img/*"
            ],
            // 접근 허용할 IP
            "Condition": {
                "IpAddress": {
                    "aws:SourceIp": "123.123.123.123"
                }
            }
        },
        // Resource에 입력한 폴더 다운로드 제한
        {
            "Sid": "DenyGetObject",
            "Effect": "Deny",
            "Principal": "*",
            "Action": "s3:GetObject",
            "Resource": "arn:aws:s3:::sgu-202500-s3-test/class/test/*"
        }
    ]
}
```

## *S3에서 JSON 사용

## 특정 S3 버킷에 읽기 권한만 부여
* 권한 설정 추가
```
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": "s3:GetObject",
      "Resources": "arn:aws:s3:::my-bucket-name/*"
    }
  ]
}
```

## *S3에서 정적 화면 출력 방법
1. 퍼블릭 엑세스 허용
2. index.html 생성
3. 사진 추가
4. 파일 업로드
5. html 접근해 객체 URL 복사
6. S3 버킷 권한 추가
7. 웹 브라우저에 url 입력

## S3 버킷 정책
* 언제, 어디서, 누가, 무엇을, 어떻게 할 수 있는지 정의
* ex. arn:aws:s3:::my-bucket/images/* > 해당 경로에 있는 모든 객체 대상으로 권한 설정
* 익명 사용자/다른 계정에 대한 권한 부여 가능
```
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "PublicWebsiteAllow",
            "Effect": "Allow",
            "Principal": "*",
            "Action": "s3:GetObject",
            "Resource": [
                "arn:aws:s3:::sgu-202500-s3-test/index.html",
                "arn:aws:s3:::sgu-202500-s3-test/class/img/*"
            ]
        }
    ]
}
```
* class/img/만 허용
```
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "PublicWebsiteAllow",
            "Effect": "Allow",
            "Principal": "*",
            "Action": "s3:GetObject",
            "Resource": [
                "arn:aws:s3:::sgu-202500-s3-test/index.html",
                "arn:aws:s3:::sgu-202500-s3-test/class/img/*"
            ]
        }
    ]
}
```

## S3 보안
* 모든 객체는 기본적으로 비공개 상태로 생성
* 퍼블릭 접근은 명시적으로 허용해야 함(웹 호스팅 등)
* Bucket Policy: 버킷 단위 정책
* IAM 정책
  - Identity-based Policy (자격 증명 기반 정책)
    - IAM 사용자, 그룹, 역할에 부여하는 정책
    - "누가 무엇을 할 수 있는가" 설정
  - Resource-based Policy (리소스 기반 정책)
    - 리소스(S3, SQS 등)에 직접 부여하는 정책
    - "이 리소스에 누가 접근 가능한가" 정의


## *클라우드 컴퓨팅 장점 - 온디맨드/내구성/고가용성
  - 온디맨드 : IT자원을 사용자가 필요할 때 원하는 만큼 즉각적으로 제공할 수 있음
  - Agility : 민첩성, 빠르고 혁신적으로 구축
  - Elastic : 탄력성, 확장가능, 손쉽게 확장 및 축소가능
  - Coast saving : 비용절감, 사용한 만큼 지불, Case by Case
  - 손쉬운 글로벌 서비스
  - 예상치 못한 트래픽 폭주 대응
  - 빅데이터, 인공지능 서비스 확장

## AWS CLI
* Amazon Web Service Command Line Interface
* AWS 리소스(S3, EC2, IAM 등)를 터미널이나 명령 프롬프트로 제어할 수 있는 도구
* 콘솔 클릭 없이 명령어만으로 S3 버킷 생성, 파일 업로드, EC2 실행 같은 작업 가능

## *CLI 명령어
  - aws s3 ls : 버킷 목록 확인
  - **aws s3 ls s3://버킷이름/** : 버킷 내부 파일 목록
  - **aws s3 cp 로컬경로 s3://버킷이름/경로** : 파일 업로드
  - **aws s3 cp s3://버킷이름/경로 로컬경로** : 파일 다운로드
  - 폴더 업로드/다운로드
    - 다운로드 : **aws s3 cp s3://my-bucket/folder ./local-folder --recursive**
    - 업로드: **aws s3 cp ./local-folder s3://my-bucket/folder --recursive**

## *ELB 정의
* AWS에서 제공하는 트래픽 분산 서비스
* 들어오는 어플리케이션 트래픽을 Amazon 인스턴스, 컨테이너, IP 주소, 람다 함수 같은 여러 대상에 자동 분산
* 단일 가용영역, 여러 가용 영역에서 다양한 어플리케이션 부하 처리 가능
* ELB에서 제공하는 3가지 로드밸런서는 모두 어플리케이션 결합성에 필요한 고가용성, 자동 확장/축소, 강력한 보안 보유
![image](https://github.com/user-attachments/assets/8ac4f86d-318c-498d-9173-330aaab43875)

## 정적 호스팅 실습 내용
* **경로 기반 리디렉션**
* 버킷 속성 > 정적 웹 사이트 호스팅 활성
* 인덱스 문서 기입
* 버킷 웹 사이트 엔트포인트 확인
* 해당 ALB 클릭 > 리스너 규칙 추가 - 이름: redirection to S3
* 조건 추가 > 경로 선택 > 경로: /s3
* URL로 리디렉션 > 부분URL > 사용자 지정 호스트, 경로, 쿼리 체크 > 해당 S3 엔드포인트 > 경로 /s3 > 상태코드 302 > 다음
* 우선 순위: 1 > 생성

## **ALB*, ELB 특징
* ELB 특징
  - 다수 서비스에 트래픽 분산 시켜주는 서비스
  - Health Check: 직접 트래픽 발생시켜 Instance 살아있는지 확인
  - AutoScaling 연동 가능
  - 여러 가용영역에 분산 가능
  - 지속적으로 IP 주소 변경, IP 고정 불가: 항상 도메인 기반으로 사용
* ALB 특징
  * 타겟 그룹: ALB가 트래픽 분산시킬 대상(Target)을 논리적으로 묶어놓은 그룹
  * 타겟 그룹 기준으로 요청 분배
  * EC2 인스턴스, IP 주소, Lambda 함수, ELB 타겟 그룹 가능
  * 역할
    - ALB > 라우팅 규칙에 따라 타겟 그룹으로 요청 전달
    - 타겟 그룹은 내부에서 등록된 인스턴스 중 헬스체크 통과한 대상에게 트래픽 전달
    - 타겟 그룹별로 포트, 프로토콜, 헬스체크 설정 가능
  * 구성 예<br>
![image](https://github.com/user-attachments/assets/3bae189b-19ad-4c9f-95bc-44f174752d28)

## *ALB 실습 내용
* 시작 템플릿 > 작업 > 템플릿 수정(새 버전 생성)
* 고급 세부정보 > 사용자 데이터 수정 > 템플릿 버전 생성
* 사용자 데이터(인스턴스 ID 가져오는 스크립트)<br>
```
#!/bin/bash
yum install httpd -y
systemctl enable httpd
systemctl start httpd
TOKEN=$(curl -X PUT "http://169.254.169.254/latest/api/token" -H "X-aws-ec2-metadata-token-ttl-seconds: 21600")
INSTANCE_ID=$(curl -H "X-aws-ec2-metadata-token: $TOKEN" -s http://169.254.169.254/latest/meta-data/instance-id)
echo "$INSTANCE_ID" > /var/www/html/index.html
```
* 시작 템플릿 > 작업 > 템플릿으로 인스턴스 시작 > 최신 버전으로 인스턴스 시작
* 로드 밸런싱 > 대상 그룹 > 대상 그룹 생성 - 기본 구성: 인스턴스
* 생성한 EC2 체크 > 아래에 보류 중인 것으로 포함 > 대상 그룹 생성
* 로드 밸런서 생성 - Application Load Balancer 생성
* 인터넷 경계 체계, 가용 영역 전부 선택, 본인 보안 그룹 선택
* 리스너 및 라우팅: 포트 80 내가 생성한 대상 그룹에 전달 > 로드 밸런서 생성 > 활성화 전 *프로비저닝* > DNS 정보 복사 > 검색

## *VPC 정의
- VPC (Virtual Private Cloud): AWS 내 네트워크 구성
  - 외부와 격리된 네트워크 만들기 위해 탄생
  - AWS 계정 전용 가상 네트워크
  - 가상으로 존재하는 데이터 센터
  - 원하는 대로 사설망 구축 가능
  - 리전 단위
  - 구조 : AWS Cloud > Amazon VPC

## 각종 오류 발생 시 대처법

## 그 외 정의 내용
