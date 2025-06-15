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

## Spring Security

## Spring Security 설정 태그

## 접근 권한 태그

## form-login 태그

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
