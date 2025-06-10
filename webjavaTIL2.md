# 스프링 시큐리티
* 스프링 기반 애플리케이션의 사용자 정의 가능한 인증/접근제어 프레임워크
* 스프링 기반 애플리케이션 보호 위해 스프링 프레임워크 내에서의 사실상 표준
* 사용자 인증(authentication), 권한부여(authorization), 접근제어(Access Control) 등을 효율적으로 구현 가능

## 스프링 시큐리티 Maven 설정(pom.xml)
* spring-security-web
  - 필터 및 웹 보안 인프라 관련 라이브러리
  - 서블릿 API 종속성 있는 스프링 시큐리티 웹 인증 및 URL 기반 접근 제어할 때 필요
* spring-security-config
  - 보안 네임 스페이스 구문 분석
  - 구성을 위해 스프링 시큐리티 네임스페이스 사용하는 경우 필요
    - *-context.xml
```java
<dependency>
  <groupId>org.springframework.security</groupId>
  <artifactId>spring-security-web</artifactId>
  <version>6.4.2</version>
</dependency>
<dependency>
  <groupId>org.springframework.security</groupId>
  <artifactId>spring-security-config</artifactId>
  <version>6.4.2</version>
</dependency>
```
* 스프링 시큐리티 웹 설정(web.xml)
  - 스프링 시큐리티 관련 설정파일 로드
  - 스프링 시큐리티 필터 설정
```java
<context-param>
  <param-name>contextConfigLocation</param-name>
  <param-value>/WEB-INF/spring/root-context.xml
               /WEB-INF/spring/security-context.xml</param-value>
</context-param>
...
<filter>
  <filter-name>springSecurityFilterChain</filter-name>
  <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>
<filter-mapping>
  <filter-name>springSecurityFilterChain</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>
```
## 스프링 시큐리티 설정 태그(security-context.xml)
* 접근 권한 및 사용자 권한 제어를 위한 태그로 분류
* 접근 권한 태그
  - 허가된 사용자만 특정 페이지에 접근 가능
  - 인증 처리하는 로그인 페이지 호출하거나 로그아웃 처리하도록 설정
* 사용자 권한 태그
  - 인증 처리하기 위해 사용자 정보 가져오는 데 사용
![image](https://github.com/user-attachments/assets/1801f7b7-8e18-45c6-861a-d385a2ef43f8)

# 접근 권한과 사용자 권한 설정
## 접근 권한 설정하는 시큐리티 태그
* <http> 태그
  - 스프링 시큐리티 설정의 핵심
  - 시작과 끝 태그(<http>...</http>) 안에 스프링 시큐리티와 관련된 내용 포함하는 최상위 태그
```java
<http auto-config="true" use-expressions="true">
...
</http>
```
> auto-config : 일반적인 웹 애플리케이션에 필요한 기본 보안 서비스 자동 설정 <br>
> use-expressions : <intercept-url> 태그의 access 속성에서 스프링 표현 언어(SpEL)를 사용할 수 있는지 여부
* <intercept-url> 태그
  - 접근 권한에 대한 URL 패턴 설정
  - <http> 태그 안에 여러 개 설정할 수 있고 선언된 순서대로 접근 권한 적용
```java
<http auto-config="true" use-expressions="true">
  <intercept-url pattern="/admin/**" access="hasAuthority('ROLE_ADMIN')" />
  <intercept-url pattern="/manager/**" access="hasRole('ROLE_MANAGER')" />
  <intercept-url pattern="/member/**" access="isAuthenticated()" />
  <intercept-url pattern="/**" access="permitAll" />
</http>
```
> pattern: ant 경로 패턴(?: 문자 한 개와 매칭, *: 0개 이상의 문자와 매칭, **: 0개 이상의 디랙터리와 매칭)을 사용해 접근 경로 설정 <br>
> access: pattern에 설정된 경로 패턴에 접근할 수 있는 사용자 권한 설정 <br>
> requires-channel: 정의된 패턴 URL로 접근하면 설정된 옵션 URL로 리다이렉션 하도록 설정 ※ 옵션: http, https, any

* 스프링 표현 언어(SpEL) 표현
![image](https://github.com/user-attachments/assets/de9159f4-3adb-4a70-98c8-e09d24f86db1)

* 사용자 권한(authentication) 태그
  - 허가된 사용자의 아이디와 비밀번호 등 사용자 정보 직접 설정하는 데 사용
```java
<authentication-manager>
  <authentication-provider>
    <user-service>
      <user name="admin" password="{noop}1234" authorities="ROLE_ADMIN, ROLE_USER"/>
      <user name="manager" password="{noop}1235" authorities="ROLE_MANAGER"/>
      <user name="guest" password="{noop}1236" authorities="ROLE_USER"/>
    </user-service>
  </authentication-provider>
</authentication-manager>
```
> <authentication-manager>: 사용자 권한 인증 설정을 위한 최상위 태그 <br>
> <authentication-provider>: 사용자 정보를 인증 요청하는데 사용 <br>
> <user-service>: 사용자 정보(사용자ID, 암호, 권한 등)를 가져오는 데 사용 <br>
> <user>: name, password, authorities 속성으로 사용자 정보를 표현

## csrf
* 교차 사이트 요청 위조(Cross-Site Request Forgery, CSRF)
  - 신뢰할 수 있는 사용자 사칭해 웹 사이트에 원하지 않는 명령 보내는 공격
  - ex) 만약 www.example.com에 인증된 사용자가 아래 태그가 포함된 사이트에 접속한다면...
  > <img src="https://www.example.com/index.php?action=delete&id=123" />
  ![image](https://github.com/user-attachments/assets/f5d17be7-ed26-4672-a754-5e907d4be3cd)

## 뷰 페이지
* 태그 라이브러리 선언
  - 보안 정보에 접근하고 보안 제약 조건 적용하는 태그 라이브러리 선언
  > <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  - pom.xml 파일에 spring-security-tablibs.jar를 의존 라이브러리로 등록
  ```java
  <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-taglibs</artifactId>
      <version>6.4.2</version>
  </dependency>
  ```

## 권한 태그: <sec:authorize>
* 권한 태그
  - 태그 안 내용 실행할지 여부 결정
  - <sec:authorize> 태그로 표현
  ![image](https://github.com/user-attachments/assets/af85d189-4236-402f-b8de-098bff18561e)
```java<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
  <title>Security</title>
</head>
<body>
  <h2>스프링 시큐리티 태그 예</h2>
  <sec:authorize access="hasRole('ROLE_MANAGER')" var="isAdmin">
    <p><h3>매니저 권한 화면입니다.</h3></p>
  </sec:authorize>
  <c:choose>
    <c:when test="${isAdmin}">
      <p>ROLE_MANAGER 권한 로그인 중입니다.</p>
      <p><a href="<c:url value='/exam02' />">[웹 요청 URL /exam02로 이동하기]</a></p>
    </c:when>
    <c:otherwise>
      <p>로그인 중이 아닙니다.</p>
      <p><a href="<c:url value='/manager/tag' />">[웹 요청 URL /manager/tag로 이동하기]</a></p>
    </c:otherwise>
  </c:choose>
</body>
</html>
```

## 인증 태그: <sec:authentication>
* 인증 태그
  - 시큐리티 설정 파일에 저장된 현재 authentication 객체에 대한 접근 허용
  - <security:authentication>으로 표현
  - JSP에서 property 속성 사용하여 현재 authentication 객체에 직접 접근 가능
  > property - 사용하려고 하는 authentication 객체의 속성 이름 <br>
  > scope - var 설정 사용 가능한 범위 <br>
  > var - 접근 권한이 설정된 사용자를 변수로 재정의 설정 <br>

# 로그인과 로그아웃 처리
## <form-login> 태그
* 인증되지 않은 사용자가 특정 경로에 접근하거나 사용자 인증이 필요할 때 로그인 페이지 보여주는데 사용
![image](https://github.com/user-attachments/assets/74b35b41-c11e-47a9-ab3f-9eb6c9a23a0e)

## <logout> 태그
* 로그아웃 처리하는 방법 설정
![image](https://github.com/user-attachments/assets/98ba249a-6377-4211-8645-343fe536a9a7)

## authentication-provider 확장
* 사용자 인증 정보를 security-context.xml 외부에서 공급받아 처리해야 할 경우
  - UserDetailsService 구현하여 공급 가능
  - 사용자 인증 정보를 DB 등에서 관리할 때 사용됨
* security-context.xml에 authentication-provider 확장
```java
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/security" ...>
...
<authentication-manager>
<authentication-provider user-service-ref="customUserService">
<password-encoder ref="customPasswordEncoder"/>
</authentication-provider>
<!--
<authentication-provider>
<user-service>
<user name="Admin" password="{noop}Admin1234" authorities="ROLE_ADMIN" />
</user-service>
</authentication-provider>
-->
</authentication-manager>
<beans:bean id="customUserService" class="com.springmvc.service.CustomUserDetailsService" />
<beans:bean id="customPasswordEncoder" class="com.springmvc.service.CustomPasswordEncoder" />
<beans:bean id="mvcHandlerMappingIntrospector"
class="org.springframework.web.servlet.handler.HandlerMappingIntrospector" />
</beans:beans>
```
* UserDetailService 구현
```java
package com.springmvc.service;
...
public class CustomUserDetailsService implements UserDetailsService {
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
if("Admin".equals(username)) {
return new UserDetails() {
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
}
@Override
public String getPassword() {
return "Admin1234";
}
@Override
public String getUsername() {
return "Admin";
}
};
}
return null;
}
}
```
> 사용자 인증 정보가 Database에 있다면 Database에서 조회하는 기능 구현
* PasswordEncoder 구현
```java
package com.springmvc.service;
import org.springframework.security.crypto.password.PasswordEncoder;
public class CustomPasswordEncoder implements PasswordEncoder {
@Override
public String encode(CharSequence rawPassword) {
return rawPassword.toString();
}
@Override
public boolean matches(CharSequence rawPassword, String encodedPassword) {
return rawPassword.equals(encodedPassword);
}
}
```
>  비밀번호를 암호화하여 저장했다면 암호화해서 비교하는 기능 구현

# 파일 업로드
* 파일을 웹 클라이언트(웹 브라우저)에서 서버로 전송, 서버에서 파일 처리
* HTTP에서는 파일 업로드 위해 multipart/form-data 형식의 프로토콜 사용

## JEE Servlet에서 파일업로드 처리
  * Servlet v3.0 이전
    - Servlet API에서 파일업로드를 지원하지 않음
    - Apache Commons Fileupload 등과 같은 외부 라이브러리을 이용하여 파일 업로드를 처리함
  * Servlet v3.0 부터
    - Servlet API에서 파일업로드를 지원
    - Servlet API에서 지원하는 Fileupload를 이용하여 기능 구현
## Springframework 6.0은 Servlet 6.0을 지원
  * Springframework 6.0은 더 이상 Third-party Fileupload 기능을 지원하지 않음
    - Servlet API에서 지원하는 Fileupload 기능을 사용해야 함
  * 책에서는 Springframework 5.x 버전을 사용하여 Apache Commons Fileupload를 사용하고 있음

## 파일 업로드 설정
* 파일 업로드 처리할 Servlet에 Multipart 설정(web.xml)
  - Servlet API 이용하여 파일 업로드 처리하는 설정
```java
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee" ... version="6.0">
<context-param>
...
<servlet>
<servlet-name>appServlet</servlet-name>
<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
<init-param>
<param-name>contextConfigLocation</param-name>
<param-value>/WEB-INF/spring/servlet-context.xml</param-value>
</init-param>
<load-on-startup>1</load-on-startup>
<multipart-config>
<location>C:\\webjavaapp\\{학번}\\upload</location><!-- 업로드 된 파일을 저장할 경로 -->
<max-file-size>20971520</max-file-size><!-- 업로드 되는 파일들의 최대 크기 20MB -->
<max-request-size>41943040</max-request-size><!-- multipart/form-data 요청의 최대 크기 40M -->
<file-size-threshold>20971520</file-size-threshold><!-- 업로드된 파일이 디스크에 기록되는 크기 임계값 20MB -->
</multipart-config>
</servlet>
...
```
* 스프링 애플리케이션에서 파일 업로드 처리
  - 스프링 MVC에서 파일 업로드를 처리하는 Bean 설정
    - servlet-context.xml
  - 스프링6.0 부터는 StandardServletMultipartResolver만 지원함
    - 이전 버전에서는 다양한 MultipartResolver를 지원했음
```java
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/mvc"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xmlns:beans="http://www.springframework.org/schema/beans"
xmlns:context="http://www.springframework.org/schema/context"
xsi:schemaLocation="http://www.springframework.org/schema/mvc
http://www.springframework.org/schema/mvc/spring-mvc.xsd
http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/springbeans.xsd
http://www.springframework.org/schema/context http://www.springframework.org/schema/context/springcontext.xsd">
...
<beans:bean id="multipartResolver"
class="org.springframework.web.multipart.support.StandardServletMultipartResolver">
</beans:bean>
</beans:beans>
```
* 파일 업로드 위한 HTML Form 태그
  - <form> 태그의 method 속성은 반드시 POST 로 설정
  - <form> 태그의 enctype 속성은 반드시 multipart/form-data 로 설정
  - 파일을 선택할 수 있는 <input> 태그의 type 속성은 file로 설정
```java
<form action ="form" method="post" enctype="multipart/form-data">
  <p>이름 : <input type="text" name="name"/></p>
  <p>파일 : <input type="file" name="fileImage"/></p>
  <p><input type="submit" value="전송하기"/></p>
  <input type="reset" value="다시쓰기"/>
</form>
```

# MultipartFile 사용한 파일 업로드
## MultipartFile 인터페이스
* org.springframework.web.multipart.MultipartFile
  - 수신된 멀티파트 요청에서 업로드된 파일 표현하는 인터페이스
  - 파일 내용은 메모리에 저장되거나 디스크에 임시로 저장
    - 개발자는 파일 내용 처리하거나 영구 저장소에 복사해야 함
    - 임시 저장소는 요청 처리 끝나면 삭제됨
![image](https://github.com/user-attachments/assets/a8c1b916-01c3-40ab-a915-810308482e9a)

## RequestParam 이용한 파일 처리
* 컨트롤러에서 멀티파트 요청 들어올 때 요청 처리 메소드의 매개변수에 @RequestParam이 적용된 MultipartFile 타입의 매개변수 사용
```java
package com.springmvc.chap09;
...
@Controller
@RequestMapping("/exam01")
public class Example01Controller {
@GetMapping("/form")
public String requestForm() {
return "webpage09_01";
}
@PostMapping("/form")
public String submitForm(@RequestParam("name") String name
, @RequestParam("fileImage") MultipartFile file) {
String filename = file.getOriginalFilename();
File f = new File("c:\\upload\\" + name + "_" + filename); 
try {
file.transferTo(f);
} catch (IOException e) {
e.printStackTrace();
}
return "webpage09_submit";
}
}
```
## MultipartHttpServletRequest 이용한 파일 처리
* 스프링에서 제공하는 HttpServletRequest/MultipartRequest 인터페이스 구현한 인터페이스
* 일반 웹 요청과 멀티파트 파일 처리하기 위한 메소드 제공
```java
package com.springmvc.chap09;
...
@Controller
@RequestMapping("/exam02")
public class Example02Controller {
@GetMapping("/form")
public String requestForm() {
return "webpage09_01";
}
@PostMapping("/form")
public String submitForm(MultipartHttpServletRequest request){
String name = request.getParameter("name");
MultipartFile file = request.getFile("fileImage");
String filename = file.getOriginalFilename();
File f = new File("c:\\upload\\" + name + "_" + filename);
try {
file.transferTo(f);
} catch (IOException e) {
e.printStackTrace();
}
return "webpage09_submit";
}
}
```
![image](https://github.com/user-attachments/assets/31e45ce4-282b-4e3d-877f-b2ec8b61f6dc)

## @ModelAttribute 이용한 파일 처리
* 멀티파트 요청 매개변수와 동일한 이름으로 Java Bean 객체에 MultipartFile 타입의 속성 추가
```java
package com.springmvc.chap09;
import org.springframework.web.multipart.MultipartFile;
public class Member {
private String name;
private MultipartFile imageFile;
public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
public MultipartFile getImageFile() {
return imageFile;
}
public void setImageFile(MultipartFile imageFile) {
this.imageFile = imageFile;
}
}
```

# 실습 - 도서 이미지 저장 및 출력
![image](https://github.com/user-attachments/assets/71486d98-f3af-4d94-8032-e64c1b9e3bea)

* web.xml에 Fileupload Servlet 설정
  - DispatcherServlet이 파일 업로드를 처리할 수 있도록 설정
```java
<multipart-config>
  <location>C:\\webjavaapp\\{학번}\\upload</location><!-- 업로드 된 파일을 저장할 경로 -->
  <max-file-size>20971520</max-file-size><!-- 업로드 되는 파일들의 최대 크기 20MB -->
  <!-- multipart/form-data 요청의 최대 크기 40M -->
  <max-request-size>41943040</max-request-size>
  <file-size-threshold>20971520</file-size-threshold><!-- 업로드된 파일이 디스크에 기록되는 크기 임계값 20MB -->
</multipart-config>
```

* application.properties
  - 스프링 애플리케이션의 처리에 관련된 설정파일
  - 설정 파일의 이름은 변경이 가능
  - application.~ 이름은 스프링이 지원하는 표준 이름으로 Spring Boot에서는 기본 사용하는 이름
* /src/main/resources/application.properties 파일에 업로드 경로 설정
  > uploadPath=C:/webjavaapp/{학번}/upload/

# 업로드 파일 다운로드
## 파일 다운로드
![image](https://github.com/user-attachments/assets/afdd9ca6-c872-4d8a-826a-54839ed586e8)

# 예외 처리
## 예외(exception)
- 프로그램 실행 중에 발생한 오류나 예상치 못한 사건
- 하드웨어와 소프트웨어 조건에 의해 발생 가능
* 종류
  - 코딩 오류
  - 시스템 전원 고장
  - 존재하지 않는 데이터 접근 시도
  - 수치 오버 플로와 언더플로 등
* 예외 처리
  - 프로그램을 실행할 때 발생할 수 있는 예외 상황에 대비한 코드 작성하여 프로그램 비정상적 종료 방지
  - 웹 애플리케이션에서는 모든 유형의 오류 발생 가능 > 예외 처리는 안전 측면에서 필수 구현 요건

## 예외 처리 방법 종류
* Spring Web MVC에서 예외 처리에 사용되는 어노테이션
![image](https://github.com/user-attachments/assets/eab86474-047a-402f-88f8-ca153a1ba0d8)

* ***우선순위***
  1. Controller에 적용된 @ExceptionHandler
  2. 공통적으로 적용된 @ControllerAdvice
  3. Exception에 적용된 @ResponseStatus
 
# @ResponseStatus 이용한 HTTP 상태 코드 기반 예외 처리
## HTTP 상태코드
* 웹 브라우저에서 보낸 HTTP 요청을 서버에서 처리한 결과가 정상적으로 처리되었거나 오류가 발생했는지를 코드화한 HTTP 응답 정보
* 예) 요청 URL에 해당하는 서버 자원 없을 때 발생하는 오류 페이지
![image](https://github.com/user-attachments/assets/48d2bf43-fe85-4b1e-8286-dcb49ca25121)

* 주요 HTTP 상태 코드
![image](https://github.com/user-attachments/assets/8503c2e4-cbaa-497b-8138-5277ebd5d772)

## @ResponseStatus 이용한 예외 처리
* @ResponseStatus
  - 메소드 실행 중 예외가 발생하면 지정된 HTTP 상태 코드를 웹 브라우저에 전달
  - ResponseStatusExceptionResolver가 처리
    - DispatcherServlet에 기본적으로 등록되어 동작함
* HTTP 400 코드 출력 예
```java
@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="요청이 실패했습니다.")
```
![image](https://github.com/user-attachments/assets/5fca276a-a130-428e-b701-7045da40dd43)

* HTTP 404 코드 출력 예
  - ***Exception 클래스에 어노테이션을 등록할 수 있을 때 사용 가능***
```java
...
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="찾을 수 없습니다")
public class Example02Exception extends Exception {
  public Example02Exception(String message) { 
    super(message);
    System.out.print(message);
  }
}
...
@Controller
public class Example02Controller {
  @GetMapping("/exam02")
  public void handleRequest () throws Exception {
    throw new Example02Exception("Example02Exception 메시지 입니다");
  }
}
```

## ExceptionHandler 이용한 컨트롤러 기반 예외처리
* @ExceptionHandler
  - 웹 요청에 따라 컨트롤러의 요청 처리 메소드를 실행하는 동안 예외 발생하면 이를 처리하기 위해 예외 처리 메소드에 사용
> value - Class<? extends Throwable>[] - @@ExceptionHandler가 선언된 메소드가 처리할 예외 클래스 목록
* @ExceptionHandler 이용한 예외 처리 메소드 설정 예
```java
package com.springmvc.chap10;
...
@Controller
public class Example03Controller {
  @GetMapping("/exam03")
  public void handleRequest () {
    throw new Example03Exception();
  }
  @ExceptionHandler(Example03Exception.class)
  public ModelAndView handleException(Example03Exception ex) {
    ModelAndView model = new ModelAndView(); 
    model.addObject("errorMassage", ex.getErrMsg()); 
    model.addObject("exception", ex);
    model.setViewName("webpage10_03");
    return model;
  }
} 
```
```java
package com.springmvc.chap10;
public class Example03Exception extends RuntimeException { 
  public Example03Exception() {
    super(); 
  }
}
```

## ControllerAdvice 이용한 전역 예외 처리
* ControllerAdvice
  - 하나의 컨트롤러가 아닌 여러 컨트롤러에서 발생하는 예외를 공통으로 처리
![image](https://github.com/user-attachments/assets/7ea11e98-ebd1-4d78-8ab8-103e47b93db1)

* @ControllerAdvice 선언한 클래스 예
```java
package com.springmvc.chap10;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(basePackages={"com.springmvc"})
public class Example04ExceptionHandler { 

  @ExceptionHandler(value={RuntimeException.class})
  private ModelAndView handleErrorMethod(Exception ex) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("errorMassage", "Example04Exception 메시지입니다.");
    modelAndView.addObject("exception", ex );
    modelAndView.setViewName("webpage10_03");

    return modelAndView;
  }
}
```

# 기말 시험범위
- Redirect, Foward 윗부분 - 자세히 못들었지만 의미와 역할
- Redirect, Forward 차이점
- @InitBinder 사용하는 방법, 요소들(ppt 아래부분)
- 스프링 시큐리티 정의, 역할, 사용법
- 스프링 설정 태그
- 접근권한 태그 해석(인터셉트 태그), 의미 이해
- form-login 태그 요소 별 무엇을 설정할 수 있는지 (logout도)
- 파일 업로드 어떻게 처리하는지 개념과 요소
- 파일 업로드 JEE Servlet 버전 별 특징, 버전에 대한 문제와 변화
- 파일 업로드 설정- 파일 업로드 코드에서 각각 요소가 가지고 있는 역할, 특징
- 파일은 애플리케이션 내부에 저장하면 X, 파일 업로드 경로(별 3개짜리)
- 예외 처리 방법 종류 - 어노테이션들이 어떤 요소에 설정할 수 있는가, 어떤 역할을 하는가, 우선순위
- @ResponseStatus 이용한 예외처리
- @ExceptionHandler 예외처리
- 전역 예외 처리 위한 @ControllerAdvice
- 각 어노테이션의 기능 범위, 사용 방법
- log4j2 정의, 구조, 요소 별 역할
- log4j2 설정, 어떤 로거를 기록할수 있는지, 없는지
- 인터셉터 - 어떤 방식으로 어떤 요소를 처리하는지
- HandlerInterceptor 인터페이스 구조, 호출 순서와 역할
- Asynchronous Processing - 무엇인지, 의미, 동작방식 차이
- ThreadLocal - 객체에 귀속 X, 스레드에 귀속, 스레드 별로 고유한 로컬 변수 제공 - 용도와 의미
- 메시지 리소드 파일 작성 - 리소스 파일 명, 파일 위치와 특징
- 뷰 페이지에 메시지 출력 - 속성 별 의미와 역할
- 메시지 출력에 인자 사용
- LocaleResolver 환경 설정 - 이게 뭘 하는건지, 구현체 종류와 용도
- + 실습 내용
