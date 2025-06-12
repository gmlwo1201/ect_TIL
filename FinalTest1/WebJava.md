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

## Redirect, Forward

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
