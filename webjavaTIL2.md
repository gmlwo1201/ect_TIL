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

