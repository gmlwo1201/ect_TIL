package Test;

import java.util.Arrays;

// 문제 1. 배열 정렬하기
// 정수 배열을 정렬해서 반환하는 solution() 함수 완성하기
// ! 조건 !
// 1. 정수 배열의 길이는 2 이상 10^5 이하입니다.
// 2. 정수 배열의 각 데이터 값은 -100,000 이상 100,000 이하입니다.
// 출력 예시
// [1, -5, 2, 4, 3] > [-5, 1, 2, 3, 4]
// [2, 1, 1, 3, 2, 5, 4] > [1, 1, 2, 2, 3, 4, 5]
// [6, 1, 7] > [1, 6, 7]

public class Test1 {
    public static void main(String[] args) {
        int[] list = {1, -5, 2, 4, 3};
        int[] sorted = solution(list);
        System.out.println(Arrays.toString(list));
        System.out.println(Arrays.toString(sorted));
    }

    private static int[] solution(int[] arr) {
        int[] clone = arr.clone();
        Arrays.sort(clone);
        return clone;
    }
}
