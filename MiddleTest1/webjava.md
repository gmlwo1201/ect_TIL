
*^ 1991년 그린 프로젝트 > 1995년 발표
* 초기 가전제품 동작 프로그램
* 현재 **웹 애플리케이션 서버 분야**에서 많이 사용됨
* 2009~10 Oracle로 소유권 이전
* 17년 Oracle은 Java EE를 Eclipse Foundation에 제출
  - Eclipse Foundation은 javax 및 java 상표 사용 합의 실패
    - Jakarta EE로 명칭, 패키지명 변경
  - **Java EE는 버전 8까지 지원**
  - **Jakarta EE는 버전 8부터 지원**
![image](https://github.com/user-attachments/assets/e3e9204e-d764-4659-abbb-18c03eb2e684)

## Spring 특징
   - 일반적 자바 객체 위해 POJO 지원
   - 결합도 줄이는 의존성 주입(DI) 지원
   - 공통 모듈 재사용 위한 AOP 지원
   - 일관성 있는 모듈 트랜잭션 지원
   - 전자정부 표준 프레임워크 기반 기술

## Spring 구성요소, 역할
* Spring Framework(Core)
  - 핵심 컨테이너 • DI 프레임워크
  - Spring Beans, MVC, Web...
* Spring WebFlux
  - Web Framework의 Reactive Stack
  - **완전한 비차단** 지원
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

## Spring MVC 구성요소, 역할
* DispatcherServlet
  - 웹 > 요청 > 컨트롤러 / 컨트롤러 > 반환 값 > 뷰
  - 사용자에게 보여줄 응답 생성하는 등 모든 절차(흐름) 담당
* HandlerMapping
  - 클라이언트 요청 URL 어떤 컨트롤러가 처리할지 결정
* HandlerAdapter
  - HandlerMapping에서 결정된 컨트롤러 호출
* Controller
  - 클라이언트 요청 처리, 결과 반환
  - 응답 결과에서 보여줄 데이터 모델에 담아 뷰로 전달
* ModelAndView
  - 컨트롤러가 처리한 결과 정보, 뷰 선택에 필요한 정보 담는 객체
* ViewResolver
  - 컨트롤러 처리 결과 사용자에게 보여줄 뷰 결정
* View
  - 컨트롤러 처리 결과 화면 생성
  - JSP 비롯한 다양한 뷰 템플릿 엔진 사용
  - 클라이언트에 요청 처리 결과 전송

## 웹 프로젝트 환경설정 파일 순서, 역할
![image](https://github.com/user-attachments/assets/09355445-ec74-42fb-b842-f69e3208001e)

