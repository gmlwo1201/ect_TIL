# 웹자바프로그래밍 기말고사 시험 범위
## @ModelAttribute
* 메소드 매개변수 or 메소드 반환 값 웹 뷰에 노출되는 명명된 모델 속성에 바인딩하는 어노테이션
* @RequestMapping 메소드가 있는 컨트롤러 클래스에서 지원됨
* JSP 폼에서 입력된 데이터가 컨트롤러에 전달되면 Java Bean 객체에 입력된 데이터 채움
* 컨트롤러 안에 @RequestMapping 적용된 요청 처리 메소드의 매개변수에 설정해 사용
```java
public String method_name(@ModelAttribute 매개변수, 
Model model) {
  // model.addAttribute(...);
  return "뷰이름";
}
```

* 메소드에 @ModelAttribute 적용
  - 컨트롤러에서 공통적으로 Model에 설정할 속성 지정할 때 사용
  - 컨트롤러 내부에 @RequestMapping이 적용되지 않은 별도 메소드 만들고 @ModelAttribute 적용
```java
@ModelAttribute("모델_속성_이름")
public String 메소드_이름() {
...
}

@ModelAttribute
public void 메소드_이름(Model model) {
// model.addAttribute(...);
}
```
## @ModelAttribute 실습 적용
* BookRepository
```java
package com.springmvc.repository;
...
public interface BookRepository {
  List<Book> getAllBookList();
  List<Book> getBookListByCategory(String category);
  Set<Book> getBookListByFilter(Map<String, List<String>> filter);
  Book getBookById(String bookId);
  void setNewBook(Book book); // 추가
}
```
* BootRepositoryImpl
```java
package com.springmvc.repository;
...
@Repository
public class BookRepositoryImpl implements BookRepository {
  private JdbcTemplate template;
  ...
  @Override
  public void setNewBook(Book book) {
    String sql = "INSERT INTO book (b_bookId, b_name, b_unitPrice, b_author, "
                + " b_description, b_publisher, b_category, b_unitsInStock, "
                + " b_releaseDate, b_condition) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    this.template.update(sql, book.getBookId(), book.getName(), book.getUnitPrice(),
      book.getAuthor(), book.getDescription(), book.getPublisher(),
      book.getCategory(), book.getUnitsInStock(), book.getReleaseDate(),
      book.getCondition());
  }
}
```
* BookService
```java
package com.springmvc.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.springmvc.domain.Book;

public interface BookService {
  List<Book> getAllBookList();
  List<Book> getBookListByCategory(String category);
  Set<Book> getBookListByFilter(Map<String, List<String>> filter);
  Book getBookById(String bookId);
  void setNewBook(Book book);
}
```
* BookServiceImpl
```java
package com.springmvc.service;
...
@Service
public class BookServiceImpl implements BookService {
  @Autowired
  private BookRepository bookRepository;
  ...
  @Override
  public void setNewBook(Book book) {
    this.bookRepository.setNewBook(book);
  }
}
```
* BookController
```java
package com.springmvc.controller;
...
@Controller
@RequestMapping("/books")
public class BookController {
  @Autowired
  private BookService bookService;
  ...
  @GetMapping("/add")
  public String requestAddBookForm(@ModelAttribute("NewBook") Book book) {
    return "addBook";
  }

  @PostMapping("/add")
  public String submitAddNewBook(@ModelAttribute("NewBook") Book book) {
    this.bookService.setNewBook(book);
    return "redirect:/books";
  }

  @ModelAttribute
  public void addAttributes(Model model) {
    model.addAttribute("addTitle", "신규 도서 등록");
  }
}
```
* addBook.jsp
```
...
<body>
  <nav class="navbar navbar-expand navbar-dark bg-dark">
    <div class="container">
      <div class="navbar-header">
...
  <div class="container">
<%--
<form:form modelAttribute="book" class="form-horizontal" >
--%>
    <form:form modelAttribute="NewBook" class="form-horizontal" >
      <fieldset>
        <legend>${addTitle}</legend>
        <div class="form-group row">
          <label class="col-sm-2 control-label">도서 ID</label>
          <div class="col-sm-3">
            <form:input path="bookId" class="form-control"/>
...
```

## Redirect, Forward 비교
![image](https://github.com/user-attachments/assets/e1aeab41-e497-4b1a-9555-2041acfc0c22)

## @InitBinder
* 사용자가 입력한 데이터가 Java Bean 객체의 프로퍼티에 매핑되기 전에 데이터 바인딩하는 방법 변겅
* WebDataBinder 객체 초기화하는 메소드와 함께 사용
```java
@InitBinder
public void 메소드_이름(WebDataBinder binder, ...) {
  ...
}

@InitBinder("Java Bean 객체 이름")
public void 메소드_이름(WebDataBinder binder, ...) {
  ...
}
```
* 실습 적용 - BookController
```java
package com.springmvc.controller;
...
@Controller
@RequestMapping("/books")
public class BookController {
  @Autowired
  private BookService bookService;
  ...

  @ModelAttribute
  public void addAttributes(Model model) {
    model.addAttribute("addTitle", "신규 도서 등록");
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.setAllowedFields("bookId", "name", "unitPrice", "author", "description"
                          , "publisher", "category", "unitsInStock", "releaseDate"
                          , "condition");
  }
}
```

## Spring Security
* 스프링 기반 애플리케이션의 사용자 정의 가능한 인증 및 접근제어 프레임워크
* 스프링 기반 애플리케이션 보호 위한 스프링 프레임워크 내에서의 사실상 표준
* 사용자 인증과 권한 부여 등을 효율적으로 구현 가능
* 스프링 시큐리티 Maven 설정(pom.xml)
  - spring-security-web
    - 필터 및 웹 보안 인프라 관련 라이브러리
    - 서블릿 API 종속성 있는 스프링 시큐리티 웹 인증 및 URL 기반 접근 제어할 때 필요
  - spring-security-config
    - 보안 네임 스페이스 구문 분석
    - 구성을 위해 스프링 시큐리티 네임 스페이스 사용하는 경우 필요 > *-context.xml
```
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

## Spring Security 설정 태그
![image](https://github.com/user-attachments/assets/6b27a425-1fad-4bf1-a6ab-c535c206b17b)

## 접근 권한 태그
* <http> 태그
![image](https://github.com/user-attachments/assets/f6a0df77-12ae-468e-8ca1-c797323e0999)

* <intercept-url> 태그
![image](https://github.com/user-attachments/assets/3c13699d-6cd6-4bc8-90f4-d7a46c099eaf)

* 스프링 표현 언어(SpEL) 표현
![image](https://github.com/user-attachments/assets/29d4ac64-55ee-4397-8950-18f62fbc007a)

* 사용자 권한 태그
![image](https://github.com/user-attachments/assets/3770bdd2-8087-41a9-a60e-08bd947b1222)

## form-login, logout 태그
* login
![image](https://github.com/user-attachments/assets/a616a6ba-ee18-4a1e-82ef-2c9a8e1d6f6e)

* logout
![image](https://github.com/user-attachments/assets/3c4b52ad-42f2-487c-8768-6e33cb44d755)

## 파일 업로드
* 파일을 웹 클라이언트(웹 브라우저)에서 서버로 전송하고 서버에서 파일 처리
* HTTP에서는 파일 업로드 위해 multipart/form-data 형식의 프로토콜 사용

## 파일 업로드 JEE Servlet
* JEE Servlet에서 파일업로드 처리
  - Servlet v3.0 이전
    • Servlet API에서 파일업로드를 지원하지 않음
    • Apache Commons Fileupload 등과 같은 외부 라이브러리을 이용하여 파일 업로드를 처리함
  - Servlet v3.0 부터
    • Servlet API에서 파일업로드를 지원
    • Servlet API에서 지원하는 Fileupload를 이용하여 기능 구현
* Springframework 6.0은 Servlet 6.0을 지원
  - Springframework 6.0은 더 이상 Third-party Fileupload 기능을 지원하지 않음
    • Servlet API에서 지원하는 Fileupload 기능을 사용해야 함

## 파일 업로드 코드
* web.xml
```java
<multipart-config>
  <location>C:\\webjavaapp\\{학번}\\upload</location><!-- 업로드 된 파일을 저장할 경로 -->
  <max-file-size>20971520</max-file-size><!-- 업로드 되는 파일들의 최대 크기 20MB -->
  <max-request-size>41943040</max-request-size><!-- multipart/form-data 요청의 최대 크기 40M -->
  <file-size-threshold>20971520</file-size-threshold><!-- 업로드된 파일이 디스크에 기록되는 크기 임계값 20MB -->
</multipart-config>
```
* servlet-context.xml
```java
<beans:bean id="multipartResolver"
  class="org.springframework.web.multipart.support.StandardServletMultipartResolver">
</beans:bean>
```
* org.springframework.web.multipart.MultipartFile
![image](https://github.com/user-attachments/assets/d2c814e7-7430-49c9-8a60-6b67338d1869)

* MultipartHttpServletRequest를 이용한 파일 처리
![image](https://github.com/user-attachments/assets/e3cb878d-e109-4d22-b8de-fec5843ce2e0)

## 파일 업로드 경로
![image](https://github.com/user-attachments/assets/808ff887-0c68-44af-a3c6-5dd70d35662d)

## 예외 처리 방법
* 각 어노테이션의 기능 범위, 사용 방법 알아둘 것
![image](https://github.com/user-attachments/assets/643ed5c1-c91d-43b4-ba22-60cc50e9b5c9)

## @ResponseStatus 이용한 예외 처리
* 메소드 실행 중 예외 발생하면 지정된 HTTP 상태 코드 웹 브라우저에 전달
* ResponseStatusExceptionResolver가 처리
  - DispatcherServlet에 기본적으로 등록되어 동작
![image](https://github.com/user-attachments/assets/56a117fc-98f0-48b0-a07e-9ba2c744e253)
```java
package com.springmvc.chap10;

...
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class Example01Controller {
  @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="요청 실패했습니다.")
  @GetMapping("/exam01")
  public String requestMethod(Model model) {
    System.out.println("chap10_01 예제입니다");
    model.addAttribute("data", "@ResponseStatus 처리 예제입니다"); 
    return "webpage10_01";
  }
}
```
![image](https://github.com/user-attachments/assets/6deb8cd0-e1d8-4d48-8262-cff0df11112b)
※ Exception 클래스에 어노테이션 등록할 수 있을 때 사용 가능

## @ExceptionHandler 예외 처리
![image](https://github.com/user-attachments/assets/bb6ba1d4-7cd5-485e-8bf8-c543cb10e676)
* com.springmvc.exception 패키지에 BookIdException 클래스를 생성
```java
package com.springmvc.exception;

public class BookIdException extends RuntimeException {
  private String bookId;

  public BookIdException(String bookId) {
    this.bookId = bookId;
  }

  public String getBookId() {
    return this.bookId;
  }
}
```
* BookRepositoryImpl 클래스의 getBookById() 메소드 수정
```java
package com.springmvc.repository;
...
@Repository
public class BookRepositoryImpl implements BookRepository {
...
  @Override
  public Book getBookById(String bookId) {
    Book bookInfo = null;
    String sql = "SELECT count(*) FROM book where b_bookId=?";
    int rowCount = this.template.queryForObject(sql, Integer.class, bookId);
    if(rowCount != 0) {
      sql = "SELECT b_bookId, b_name, b_unitPrice, b_author, b_description, b_publisher, b_category, b_unitsInStock, b_releaseDate, b_condition, b_fileName FROM book where b_bookId=?";
      bookInfo = this.template.queryForObject(sql, new BookRowMapper(), bookId);
    }
    if(bookInfo == null) {
      throw new BookIdException(bookId);
    }
    return bookInfo;
  }
...
}
```
* BookController 클래스에 @ExceptionHandler를 설정한 handleException 메소드 추가
```java
package com.springmvc.controller;
...
@Controller
@RequestMapping("/books")
public class BookController {
...
  @ExceptionHandler(value= {BookIdException.class})
  public ModelAndView handlerException(HttpServletRequest req, BookIdException exception) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("invalidBookId", exception.getBookId());
    mav.addObject("exception", exception);
    mav.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
    mav.setViewName("errorBook");
    return mav;
  }
}
```

## 전역 예외 처리를 위한 @ControllerAdvice
![image](https://github.com/user-attachments/assets/938f46e4-fa29-4408-8d6c-5991b1ef9a14)
* ControllerAdvice를 선언한 클래스 예
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
* com.springmvc.exception 패키지에 CommonExceptionAdvice 클래스를 생성
```java
package com.springmvc.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
public class CommonExceptionAdvice {
  @ExceptionHandler(RuntimeException.class)
  private ModelAndView handleExceptionCommon(Exception e) {
    e.printStackTrace();

    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", e);
    mav.setViewName("errorCommon");
    return mav;
  }
}
```

## Log4j2
* 산업용 Java 로깅 프레임워크
* 다양한 사용 사례에 대한 배포 지원하는 API, 구현체로 구성됨
* Apache Software Foundation에서 관리하는 프로젝트
* Log4j 1.x
  - 로깅 서비스 프로젝트 관리 위원회는 2015년 8월 5일 Log4j 1.x 버전의 지원을 중단함
  - 여러 가지 보안 위협이 발견된 것이 원인
  - Log4j 2 로 변환이 권고됨
* 구조
![image](https://github.com/user-attachments/assets/f36497ce-839d-4b77-bbb5-c1c27da2541a)

## Log4j2 설정, 특징
* 위치 - src/main/resources/log4j2.xml
```java
<?xml version="1.0" encoding="UTF-8"?>
<Configuration xmlns="https://logging.apache.org/xml/ns" xmlns:xsi="http://www.w3.org/2001/XMLSchemainstance"
xsi:schemaLocation="https://logging.apache.org/xml/ns 
https://logging.apache.org/xml/ns/log4j-config-2.xsd">
  <Appenders>
    <Console name="console">
      <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} %-5p %c{-10}:%L - %m%n"/>
    </Console>
  </Appenders>
  <Loggers>
    <Root level="INFO" additivity="false">
      <AppenderRef ref="console"/>
    </Root>
    <Logger name="com.springmvc" level="DEBUG"/>
    <Logger name="org.springframework.core" level="INFO"/>
    <Logger name="org.springframework.beans" level="INFO"/>
    <Logger name="org.springframework.context" level="INFO"/>
    <Logger name="org.springframework.web" level="DEBUG"/>
    <Logger name="org.springframework.security" level="DEBUG"/>
    <Logger name="org.springframework.jdbc" level="DEBUG"/>
  </Loggers>
</Configuration>
```
* 과거 버전의 Log4j는 Log4j를 활성화 하기 위해 추가 설정이 필요, Log4j2에서는 log4j2.xml을 Classpath 경로에 생성해주기만 하면 자동으로 적용됨.
* 실습 코드
```java
<?xml version="1.0" encoding="UTF-8"?>
<Configuration xmlns="https://logging.apache.org/xml/ns" xmlns:xsi="http://www.w3.org/2001/XMLSchemainstance"
xsi:schemaLocation="https://logging.apache.org/xml/ns 
https://logging.apache.org/xml/ns/log4j-config-2.xsd">
  <Appenders>
    <Console name="console">
      <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} %-5p %c{-10}:%L - %m%n" />
    </Console>
    <RollingFile name="monitor" fileName="C:/webjavaapp/{학번}/logs/monitor.log"
                  filePattern="C:/webjavaapp/{학번}/logs/monitor.%d{yyyy-MM-dd-hh-mm}.log">
      <LevelRangeFilter minLevel="DEBUG" maxLevel="DEBUG"/>
      <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} %-5p %c{-10}:%L - %m%n" />
      <Policies>
        <SizeBasedTriggeringPolicy size="10KB"/>
        <TimeBasedTriggeringPolicy interval="1"/>
      </Policies>
    </RollingFile>
  </Appenders>
  <Loggers>
    <Root level="INFO" additivity="false">
      <AppenderRef ref="console" />
    </Root>
    <Logger name="com.springmvc" level="DEBUG" >
      <AppenderRef ref="monitor" />
    </Logger>
...
  </Loggers>
</Configuration>
```
* Policies
  - 현재 로그 파일을 롤오버할 시점을 결정
* SizeBasedTriggeringPolicy
  - 파일이 지정된 크기에 도달하면 롤오버를 실행
* TimeBasedTriggeringPolicy
  - filePattern의 최소 시간 단위 값이 변경될 때 롤오버를 발생

## Interceptor

## HandlerInterceptor

## Asynchronous Processing

## ThreadLocal

## 메시지 리소드 파일 작성

## 뷰 페이지에 메시지 출력

## 메시지 출력에 인자 사용

## LocaleResolver 환경 설정

