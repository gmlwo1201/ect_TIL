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

## 파일 업로드 JEE Servlet

## 파일 업로드 코드

## 파일 업로드 경로

## 예외 처리 방법

## @ResponseStatus 이용한 예외 처리

## @ExceptionHandler 예외 처리

## 전역 예외 처리를 위한 @ControllerAdvice

## 각 어노테이션의 기능 범위, 사용 방법

## log4j2

## log4j2 설정, 특징

## Interceptor

## HandlerInterceptor

## Asynchronous Processing

## ThreadLocal

## 메시지 리소드 파일 작성

## 뷰 페이지에 메시지 출력

## 메시지 출력에 인자 사용

## LocaleResolver 환경 설정

## 실습 내용
