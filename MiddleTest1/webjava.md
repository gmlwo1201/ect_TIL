# WebJavaProgramming 중간대비
## Java 역사
* 썬 마이크로시스템즈 제임스 고슬링 팀에서 개발한 객체지향 프로그래밍 언어
* 1991년 그린 프로젝트 > 1995년 발표
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

```java
<?xml version="1.0" encoding="UTF-8"?>
<web-app metadata-complete="false" version="6.0"

// 웹 애플리케이션 설정 파일 문법검증 위한 네임 스페이스와 스키마 선언
xmlns="https://jakarta.ee/xml/ns/jakartaee" 
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd">

<!-- 서블릿 컨텍스트가 시작될 때 전달할 파라메터를 설정 -->
<context-param> ... </context-param>
<!-- 서블릿 컨테이너의 각종 이벤트를 수신/처리할 이벤트 리스너를 등록 -->
<listener> ... </listener>
<!-- 사용자의 요청을 처리할 서블릿을 등록 -->
<servlet> ... </servlet>
<!-- 서블릿을 사용자 요청 URL과 매핑 -->
<servlet-mapping> ... </servlet-mapping>
<!-- 서블릿 호출 전처리 필터를 등록 -->
<filter> ... </filter>
<!-- 필터를 요청 URL과 매핑 -->
<filter-mapping> ... </filter-mapping>
</web-app>
```


## 자바 클래스 Bean, 어노테이션 별 역할
* Spring MVC에서 사용할 Bean 객체 XML에 등록하지 않고 설정된 패키지 하위 경로의 모든 클래스 검색해 자동 등록
  - <context:component-scan base-package="com.springmvc.*" /> or <beans:bean class="com.springmvc.controller.HomeController"/>
  - @ 자동 감지 어노테이션
    - @(Component, Repository, Service, Controller, RestController, ControllerAdvice, Configuration)
  - @ 구성요소 클래스에서 자동 활성화하는 어노테이션
    - @(Required, Autowired, PostConstruct, PreDestroy, Resource, PersistenceContext, PersistenceUnit)

## 정적 리소스 설정(resources 위치)
* 설정
  - 정적 리소스(이미지, js, css)를 웹 브라우저에 효율적으로 전달하도록 최적화된 캐시 헤더와 함께 제공하기 위한 핸들러 구성
  - Spring의 리소스 처리 통해 도달 가능한 모든 경로에서 리소스 제공
  - <resources mapping="/resources/**" location="/resources/" />
  - **위치='project'/src/main/webapp/WEB-INF/spring/servlet.xml**
* resources 요소 속성
  - mapping
    - 웹 요청 URL 경로 패턴 설정
    - 컨텍스트 경로 제외한 나머지 부분의 경로와 매핑
  - location
    - 웹 애플리케이션 내에서 실제 요청 경로 패턴에 해당하는 자원 위치 설정
    - 위치가 여러 곳이면 쉼표로 구분
  - cache-period
    - 웹 브라우저에 캐시 시간 관련 응답 헤더 전송
    - 초 단위로 캐시 시간 지정
    - 값 = 0이면 웹 브라우저가 캐시하지 않고 값 설정하지 않으면 캐시 관련 응답 헤더 전송 X

## 뷰 매핑 설정(경로 추적)
* 브라우저에 요청 결과를 전달할 View 관련 설정
* 속성
  - prefix
    - View 위치 결정할 때 컨트롤러에서 반환한 View 이름 앞에 붙일 접두사
  - suffix
    - ... 접미사
  
  ![Image](https://github.com/user-attachments/assets/177b22eb-8f31-4602-a8c4-cd52e7377c6b)

## Maven Scope
* 종속성 범위(Dependency scope)
  - 종속성의 전이성 제한, 종속성이 클래스 경로에 포함되는 시점 결정
* 종속성 범위 종류
  - compile
    - 기본 범위로 설정하지 않으면 적용
    - 프로젝트의 모든 클래스 경로에서 사용 가능
    - 종속 프로젝트로 전파됨
  - provided
    - 컴파일과 유사
    - JDK or 컨테이너가 런타임에 종속성 제공
    - 컴파일/테스트 단계에 클래스 경로에 추가되지만 런타임 단계에 추가 X
  - runtime
    - 컴파일에 필요없지만 실행에는 필요한 종속성 의미
    - Maven은 런타임 및 테스트 클래스 경로에 이 범위가 있는 종속성 포함하지만 컴파일에는 포함 X
  - test
    - 테스트 컴파일 및 테스트 실행 단계에서만 사용 가능
  - system
    - provided와 유사하지만 명시적으로 포함된 JAR 제공해야 함
    - 항상 사용 가능, Maven Repository에서 조회 X
  - import
    - <dependencyManagement> 섹션의 pom 유형의 종속성에서만 지원
    - 종속성이 저장된 POM의 <dependencyManagement> 섹션에 있는 종속성 목록으로 대체

## 계층적 구조 장/단점, 특징, 구현 과정
* 전체 애플리케이션 관심사를 계층별 분리 > 느슨하게 결합 > 유연한 동작 위해 애플리케이션 구조화
* 웹 애플리케이션 효율적으로 개발, 유지보수 쉽게 하기 위해 시스템 구조를 계층화하여 개발
* 계층적 구조 없이 한곳에서 모든 작업 처리하면 문제 발생
  - 코드 복잡성 증가
  - 유지 보수 어려움
  - 유연성 부족
  - 중복 코드 증가
  - 낮은 확장성 등
  ![Image](https://github.com/user-attachments/assets/f6913a09-6184-443b-89e4-5949c34bec28)

* 도메인 객체
  - 정보 저장하는 데이터 모델
* 퍼시스턴트(영속) 계층[데이터 액세스 계층]
  - 데이터베이스나 파일에 접근해 데이터 처리
* 서비스 계층[비즈니스 계층]
  - 애플리케이션이 제공하는 포괄적 서비스들 표현
  - 브라우저에서 요청한 데이터 처리하기 위해 퍼시스턴트 계층 호출
  - 프레젠테이션 계층 / 퍼시스턴트 계층 사이 연결
* 프레젠테이션 계층[표현 계층]
  - 애플리케이션과 사용자(브라우저)와의 최종 접점
  - 사용자로부터 데이터 입력받거나 데이터 출력하는 계층
    - 사용자 요청을 애플리케이션으로 전달 or 처리된 결과 사용자에게 보여줌
* 구현 과정
  * 계층간 데이터 전달하기 위한 데이터 정의
  * 구현 순서
  ![Image](https://github.com/user-attachments/assets/21945be9-90a1-4694-909c-63d4e4314a9b)

## 컨트롤러 기본 구조
  - 컨트롤러 정의 > 요청 경로 매핑 > 요청처리 메소드 구현 > 뷰[& 모델] 반환

## books.jsp - forEach
* 각 요소 반복 처리할 때 사용
* <c:forEach items="${bookList}" var="book">
  - items : 반복할 컬렉션
  - var : 반복할 때 사용할 변수 이름

## Spring Jdbc 개발자/앱 역할
![Image](https://github.com/user-attachments/assets/8e2550d4-194c-4076-ba45-92bf8c2934e2)

## root-context의 datasource 데이터 의미, 역할
* db.properties 에 설정된 정보로 DataSource 생성
  * JDBC Driver를 이용한 DataSource의 구현체인 아래 클래스를 적용
    - org.springframework.jdbc.datasource.DriverManagerDataSource
  * DriverManagerDataSource는 javax.sql.DataSource의 구현체
```java
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
  <property name="driverClassName" value="${datasource.driverClassName}" />
  <property name="url" value="${datasource.url}" />
  <property name="username" value="${datasource.username}" />
  <property name="password" value="${datasource.password}" />
</bean>

  <jdbc:initialize-database data-source="dataSource">
  <jdbc:script location="classpath:db/create-db.sql" />
  <jdbc:script location="classpath:db/insert-data.sql" />
  </jdbc:initialize-database>
</beans>
```
* DataSource 이용해 데이터베이스 초기화
  - jdbc:initialize-database 요소 통해 설정
  - SQL 스크립트 이용해 데이터베이스 초기화
  - SQL 스크립트는 jdbc:script 요소 통해 위치 설정
  - 테이블 생성 설정: create-db.sql
  - 데이터 생성 설정: insert-data.sql

## 스프링 컨텍스트에 빈 파일 자동 등록
* 스프링 MVC 설정 파일(servlet-context.xml)에 빈 클래스로 등록
  * @Controller 가 선언된 컨트롤러를 빈으로 자동 등록
  * 자동 의존성 관리
  * <context:component-scan> 요소 이용
    - <context:component-scan base-package="기본 패키지 명" />
    - 자동으로 감지하여 빈으로 관리하는 타입
      - @Component, @Repository, @Service, @Controller, @RestController, @ControllerAdvice, @Configuration
    - 감지된 클래스에서 자동으로 활성화하는 어노테이션
      - @Required, @Autowired, @PostConstruct, @PreDestroy, @Resource, @PersistenceContext, @PersistenceUnit

## 메소드에 Request Mapping 적용
* 메소드 수준의 @RequestMapping
  - 웹에서 사용자가 요청한 URL을 처리할 컨트롤러 메소드 지정
  - method 속성 기본값 : GET
  - 매핑되는 URL
    - 클래스에 적용된 @RequestMapping의 value + 메소드에 매핑된 @RequestMapping의 value 의 값이 적용
```java
@Controller
@RequestMapping("/books")
public class BookController {
  ...
  @RequestMapping("/all")
  public String requestAllBooks(Model model) {
    ...

// requestAllBooks 메소드가 실행되기 위한 URL = http(s)://[host]:[port]/[context-path]/books/all
```

## ModelView 요소 역할
  - Model : 데이터 저장하고 반환받는 단순한 메소드 제공
    - 필요한 데이터는 Model 객체의 addAttribute() 메소드 이용해 Model에 추가
    * 역할 : 제공된 이름으로 값 등록
    * 매개변수
      - attributeName : 데이터의 이름 (null 될 수 없음)
      - attributeValue : 데이터 (null 가능)
  - ModelMap : Map 또는 Model 인터페이스 통해 데이터 저장하고 반환 받을 수 있음
  - ModelAndView : 모델, 뷰 정보 모두 관리 가능
    - public ModelAndView addObject(String attributeName, @Nullable Object attributeValue)
      * 역할 : 지정된 이름으로 데이터 저장
      * 매개변수
        - attributeName : 데이터의 이름 (null 될 수 없음)
        - attributeValue : 데이터 (null 가능)
    - public void setViewName(@Nullable String viewName)
      * 역할 : ModelAndView 위한 뷰 이름 설정
      * 매개변수
        - viewName : 뷰 이름

## 경로변수 각각의 역할
  - 경로 변수 적용 사례 - http://.../home/exam01/ISBN1234
  ``` java
  @Controller
  @RequestMapping("/home") // /home/exam01/ISBN1234
  public class Example01Controller {
    @GetMapping("/exam01/{bookId}") // /exam01/ISBN1234
    public String requestMethod
    (@PathVariable String bookId, Model model) // ISBN1234
  {
    System.out.println("도서 ID: " + bookId);
    model.addAttribute("data", "도서 ID: "
    + bookId);
    return "webpage06";
  }
  }
  ```
  ```jsp
  <%@ page language="java"
  contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Example</title>
  </head>
  <body>
  <p>${data}</p> // ISBN1234
  </body>
  </html>
  ```
  - 다중 경로 변수 적용 사례 - http://.../home/exam02/IT전문서/publisher/신구문학사
```java
@Controller
@RequestMapping("/home") // /home/exam02/IT전문서/publisher/신구문학사
public class Example02Controller {
@GetMapping("/exam02/{category}/publisher/{publisher}") // /exam02/IT전문서/publisher/신구문학사
public String requestMethod(
@PathVariable String category,
@PathVariable String publisher, Model model) {
System.out.println("도서 분야: " + category);
System.out.println("출판사: " + publisher);
model.addAttribute("data", "도서 분야: "
+ category + "<br/>"
+ "출판사: " + publisher);
return "webpage06";
}
}
```
```jsp
<%@ page language="java"
contentType="text/html;charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Example</title>
</head>
<body>
<p>${data}</p> // 도서 분야: IT전문서 <br/> 출판사: 신구문학사
</body>
</html>
```

## Matrix
- 웹 요청 URL에 포함된 파라미터 값 전달받는 데 사용
- ; 으로 데이터 구분해 다중 데이터 받을 수 있음
- @RequestMapping의 경로 변수에 '매트릭스_변수=값' 형태로 사용
- 매트릭스 변수 여러 개일 경우
  - , 로 구분 or 변수 이름 반복하여 사용
```java
// 경로 변수 내 매트릭스 변수를 그대로 사용할 때
@RequestMapping("경로_변수")
public String method1(@MatrixVariable 매트릭스_변수, ...) {
...
}
@RequestMapping("/cars/{car}")
public String method1(@MatrixVariable Map<String, String> color, ...) {
...
}
// 경로 변수 내 매트릭스 변수를 재정의하여 사용할 때
@RequestMapping("경로_변수")
public String method1(@MatrixVariable(매트릭스_변수) 매개변수, ...) {
...
}
// 경로 변수 내 매트릭스 변수를 재정의하여 사용할 때
@RequestMapping("/cars/{car}")
public String method1(@MatrixVariable("color") String color, ...) {
...
}
```
- 속성
![image](https://github.com/user-attachments/assets/a0037c78-3d34-4841-ad81-ffa7cae71552)


## Matrix 안되는 이유, 해결법
* 안되는 이유 : publisher, category를 한글로 입력하면 한글 인식을 못해 오류 발생
* 해결 방법 - Book.java에 hashCode(), equals() 메소드 구현
  - 우클릭 > Source > Generate hashCode() and equals()...
```java
  @Override
	public int hashCode() {
		return Objects.hash(author, bookId, category, condition, description, name, publisher, releaseDate,    unitsInStock, unitPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(bookId, other.bookId)
				&& Objects.equals(category, other.category) && Objects.equals(condition, other.condition)
				&& Objects.equals(description, other.description) && Objects.equals(name, other.name)
				&& Objects.equals(publisher, other.publisher) && Objects.equals(releaseDate, other.releaseDate)
				&& unitsInStock == other.unitsInStock && unitPrice == other.unitPrice;
	}
```

## request param
* 요청 URL에 포함된 파라미터
  - http_URL = "http:" "//" host [ ":" port ] [ abs_path [ "?" query ]]
* 파라미터 이름, 값은 = 로 구분
* 파라미터 여러 개일 때 파라미터는 & 로 구분
```java
@Controller
public class CarController {
  ...
  @GetMapping("/cars")
  public String requestMethod(@RequestParam String color,...) {
    ...
    return "cars";
  }
}
```
* @RequestMapping 요청 매핑 경로에 포함된 요청 파라미터 값 요청 처리 메소드의 매개변수로 전달 받음
![image](https://github.com/user-attachments/assets/597ae07c-6aa2-4312-9982-c92dec656a33)


## 폼 태그 종류, 특징, 제한 상황
- 스프링 웹 MVC와 연동되는 JSP 태그 라이브러리
* 사용하는 경우
  - 폼에서 전달되는 파라미터 이름으로 Geter() 메소드 작성한 Java Bean 클래스 프로퍼티(필드 이름)에 접근 가능
  - 컨트롤러가 다루는 데이터 참조할 수 있어 동적 웹 애플리케이션 더 쉽고 편리하게 개발, 유지 관리 가능
  - 스프링 폼 태그 사용하려면 JSP 상단 스프링 폼 태그 라이브러리 선언 필요
    - <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
* 종류<br>
![image](https://github.com/user-attachments/assets/993f0497-ffb2-4c21-a2df-0b991168701b)

* <form> 태그
  - 단독으로 쓰임 X, 사용자에게 데이터 입력받는 다양한 입력 양식 포함하는 최상위 태그로 사용
- 형식
```java
<form:form 속성1="값1" [속성2="값2" ...]>
// 다양한 입력 양식 태그(<input>, <select>, < textarea>...)
</form:form>
```
![image](https://github.com/user-attachments/assets/1d3fe798-58b3-4263-925c-38038eb7b263)

* 사용 예제
  - action 속성 생락된 경우 이 폼 출력한 요청 URL 자동 설정
  - method 속성 생략된 경우 POST 자동 설정
```java
<form:form modelAttribute="member">
...
</form:form>
```
* 출력된 HTML <form>
```java
<form id="member" action="..." method="post">
...
</form>
```
* <form> 안에 사용하는 태그
  - <form::form> 태그 내 중첩되어 사용되며 반드시 종료(/>) 태그로 닫아야함
```java
<form:form modelAttribute="커맨드 객체">
  <form:태그_이름 path="커맨드 객체의 프로퍼티(필드)"/>
</form:form>
```
  - <form:form> 태그 내 modelAttribute 속성 설정하면 중첩되는 <form:form> 태그에 반드시 path 속성 설정해야 함

## 실습 코드 동작 방식, 구조
