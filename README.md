# 필기용 Github


# 웹자바프로그래밍응용
## Java
1. Write Once, Run Anywhere
  - 컴파일러 > 바이트 코드
  - 바이트 코드는 가상머신에서 실행
2. Java API는 JCP로 표준화
3. 라이선스 (Oracle)

## Spring Framework
1. 대규모 자바 어플개발 신속 편리 오픈소스 프레임워크
2. 의미
   - 복잡성 극복한 새로운 세대 기술
   - 필요에 따라 쉽게 확장 가능한 탄력적
3. 사용 이유
   - 복잡 X, 경량화
   - 동적인 웹 사이트 개발 지원 서비스 제공
   - 생산성, 품질 우수
   - 유지보수 해결책 제시
4. 역사
   - 2004년 3월 Java 애플리케이션 쉽게 개발 플랫폼 목적으로 1.0 버전 배포
   - EJB2.x 에 대한 대안 제공
     * Enterprise JavaBeans(현재:Jakarta Enterprise Beans)
     * 분산 컴포넌트 아키텍처
     * E..2는 개발/배포/관리 어려움
     * E..3에서는 개선됨
   - 일반적인 개발 과제 단순하게 해결하는 프레임워크 지향
   - Spring Framework 6.x 지원 환경
     * Java 17+
     * Jakarta EE 9
     * Tomcat 10.1, Jetty 11, Undertow 2.3
5. 특징
   - 일반적 자바 객체 위해 POJO 지원
   - 결합도 줄이는 의존성 주입(DI) 지원
   - 공통 모듈 재사용 위한 AOP 지원
   - 일관성 있는 모듈 트랜잭션 지원
   - 전자정부 표준 프레임워크 기반 기술

## Spring Modules(Spring Projects)
* Spring Framework(Core)
  - 핵심 컨테이너 • DI 프레임워크
  - Spring Beans, MVC, Web...
* Spring WebFlux
  - Web Framework의 Reactive Stack
  - 완전한 비차단* 지원
    > I/O 처리 시 스레드 대기X, 다른 작업 수행하도록 전환되는 방식
      비동기적 처리 방식 구현, 이벤트 루프 같은 구현체
* Spring Boot
  - 독립실행형 Spring 애플리케이션 지원
  - 내장 서블릿 컨테이너(Tomcat, Jetty, Undertow) 사용
  - 빌드 구성 단순화 위해 스타터 종속성 사용
  - Spring 및 타사 라이브러리 자동 구성
  - XML 구성 필요X
* Spring Data
  - 애플리케이션 Data 저장소 Java 인터페이스로 정의
  - 데이터 저장, 검색 방법 명명 규칙 사용
  - 여러 종류 데이터베이스와 연동 기능 제공
* Spring Security
  - 강력한 보안 프레임워크
  - 인증, 권한부여, API 보안 포함 애플리케이션 보안 처리
* Spring Intergration
  - 메시징 통해 내/외부 애플리케이션 통합 제공
* Spring Batch
  - 배치 애플리케이션 개발 위해 설계된 가볍고 포괄적 배치 프레임워크
  - 로깅/추적, 트랜잭션 관리, 작업 처리 통계, 작업 재시작, 건너뛰기 및 리소스 관리
  - 대량 레코드 처리하는데 필수적인 재사용 가능 기능 제공
* Spring Cloud
  - Spring 이용한 클라우드 네이티브 애플리케이션 개발 지원
  - 개발자가 분산 시스템에서 일반적인 패턴 빠르게 빌드할 수 있는 도구 제공
    > 구성관리, 서비스 검색, 회로 차단기, 지능형 라우팅, 마이크로 플록시, 제어버스...
* Spring Native
  - Spring Boot 프로젝트 네이티브 실행 파일로 컴파일 가능하게 지원
