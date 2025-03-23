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
## 일반적 자바 객체를 위한 POJO 지원
* POJO란?
  - 별도의 API 사용, 제한 없는 자바 클래스
  - 특화된 인터페이스 반드시 구현 or 의존성 높은 클래스의 확장 따라야할 필요X
  - 일반적 자바 언어, 필수 API 외에는 특정 구현 기술 종속X
  - 데이터베이스나 서버 의존 없는 자바 클래스만으로 구성해도 프로그래밍 가능

@ EJB
```java
import javax.ejb.EntityBean;

pubilc class BookBean implements EntityBean {
...
}
```
@ Servlet
```java
import javax.servlet.http.HttpServlet;

pubilc class BookController extends HttpServlet {
...
}
```
@ Spring POJO
```java
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
pubilc class Book {
...
}
```
* POJO 지원 장점
  - 코드 단순화
  - 개발 후 특정 데이터베이스, 서버 없어도 테스트 가능해 개발 속도 증가
  - 프레임워크 규약, 규제 없어 자유롭게 객체지향 설계 가능

## 객체 간 결합도 줄이는 의존성 주입(DI)
* 의존성 주입(Dependency Injection)이란?
  - 객체 간 관계 정리에 사용하는 기법
  - 객체 or 구성 요소 사이의 의존 관계 직접 생성/제어 X
  - 외부 빈(Bean) 설정 파일 or 어노테이션(Annotation) 활용해 스프링 컨테이너가 자동으로 연결해주는 방식

* 기존 개발 방식
```java
public class Phone {
  private MobilePhone mobilePhone;
  public Phone() {
    // 개발자가 직접 MobilePhoneImpl 객체 조립
    MobilePhone = new MobilePhoneImpl();
  }
}
```
* 의존성 자동 주입
```java
// setter() 메소드 사용하는 경우
public class Phone {
  private MobilePhone mobilePhone;
  setter() // 메소드의 매개변수로 객체 조립
  public void setMobilePhone(MobilePhone mobilePhone) {
    this.mobilePhone; = mobilePhone;
  }
}
@Autowired 어노테이션 사용하는 경우
public class Phone {
@Autowired // 어노테이션 설정으로 객체 조립
private MobilePhone mobilePhone;
}
<beans> 
<bean id="mobilePhone" class="com.springmvc.MobilePhoneImpl">
<bean id="phone" class="com.springmvc.Phone">
<property name="mobilePhone" ref="mobilePhone"/>
</bean>
</beans> 
```
* 의존성 주입 장점
  - 객체 간 의존 관계 직접 생성, 제어할 필요 X
  - 설정 파일 통해서 스프링 컨테이너가 자동으로 객체 연결
  - 코드 간단해지고 이해하기 쉬우며 테스트 용이
  - 프로그래밍 설계 쉬워짐
  - 이미 개발된 프로그램 변경 사항 적용하기 쉬워 확장성 매우 좋음
  - 각 객체 간 의존 관계와 객체들 생명 주기 간편하게 개발하거나 유지보수 가능

## 공통 모듈 재사용을 위한 AOP 지원
* 관점 지향 프로그래밍(AOP)이란?
  - 핵심적 기능에서 부가적인 공통 관심사 분리 > 공통 모듈로 만들어 설계, 개발하는 방법
  - 반복적으로 나타나는 공통 관심사 분리하여 설계, 관리 가능
* AOP 지원 장점
  - 애플리케이션 전체에 사용되는 핵심 기능 소스코드와 분리하여 재사용 가능
  - 개발자는 비즈니스 기능만 구현 > 개발 과정 간소화
  - 공통 모듈을 각 독립된 모듈로 중복 없이 작성 가능
  - 공통 모듈 XML 설정 파일에 설정 가능

## 일관성 있는 모듈 트랜잭션 지원
* 트랜잭션이란?
  - 쪼갤 수 없는 최소 작업 단위
  - 작업을 하나로 묶어 실행 시 하나라도 실패하면 모두 실패, 모두 성공하면 성공 처리
* 스프링에서 지원하는 트랜잭션
  - 데이터베이스 연동 기술, 트랜잭션 서비스 사이 종속성 제거
  - 트랜잭션 처리 위한 일관된 방법 제공
* 장점
  - 데이터베이스 연동 기술과 상관없이 같은 방식으로 트랜잭션 기능 활용 가능
  - 트랜잭션 서비스 종류, 환경 변해도 트랜잭션 사용 코드 그대로 유지하는 유연성 제공

## 스프링 웹 MVC
* 스프링 웹 MVC
  - 스프링이 제공하는 웹 애플리케이션 개발 전용 프레임워크
  - MVC(Model-View-Controller) 패턴 사용
    - M V C 사이 의존 관계 스프링 컨테이너가 관리
    - 스프링이 제공하는 많은 기능 확장하여 웹 애플리케이션 구축 가능
* Front Controller
  - 애플리케이션 흐름 관리
  - Spring Framwork의 DispatcherServlet
* Model
  - 애플리케이션 데이터 들어있는 객체
* Controller
  - 애플리케이션 비즈니스 로직 포함하는 자바 클래스
     - @Controller
* View
  - 모델 정보(데이터) 특정 형식에 맞게 표현
  - JSP, Thymeleaf, Freemarker...
 
## MVC 기본 구성 요소
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
 
* View Template Engine이란?
  - 개발자가 템플릿 정의할 수 있는 소프트웨어 구성요소
  - 템플릿은 동적 콘텐츠 위치 표시자가 있는 HTML or XML로 개발
  - 형식과 구조가 정해진 동적 웹 페이지 생성
 
* MVC 동작 흐름
