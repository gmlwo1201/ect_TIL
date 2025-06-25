# 코딩 테스트 필수 문법 - 주의할 점
## 엡실론 포함 연산 주의
* 자바는 부동소수형 데이터를 이진법으로 표현해서 표현과정에 오차 발생
```java
public class Solution {
    public static void main(String[] args) {
        double epsilon = 1E-5;

        // 앱실론 출력
        System.out.println(epsilon); // 1.0E-5

        // 부동소수점 수 오차 검사
        double a = 0.1 + 0.2;
        double b = 0.3;
        System.out.println(a - b); // 0 안나옴

        if (a == b) {
            System.out.println("a와 b는 같은 값");
        } else {
            System.out.println("a와 b는 다른 값"); // 이 코드 출력됨
        }

        if (Math.abs(a - b) < epsilon) {
            System.out.println("a와 b는 같은 값"); // 이 코드 출력됨
        } else {
            System.out.println("a와 b는 다른 값");
        }
    }
}
```
