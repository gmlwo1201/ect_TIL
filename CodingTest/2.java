package Test;

import java.util.Arrays;
import java.util.Collections;

// 문제 2. 배열 제어하기
// 정수 배열을 하나 받아 배열의 중복값 제거하고 배열 데이터를 
// 내림차순으로 정렬해서 반환하는 solution() 함수 구현
// ! 조건 !
// 1. 정수 배열의 길이는 2 이상 1,000 이하입니다.
// 2. 정수 배열의 각 데이터 값은 -100,000 이상 100,000 이하입니다.
// 출력 예시
// [4, 2, 2, 1, 3, 4] > [4, 3, 2, 1]
// [2, 1, 1, 3, 2, 5, 4] > [5, 4, 3, 2, 1]

public class Test2 {
    public static void main(String[] args) {
        int[] list = {4, 2, 2, 1, 3, 4};
        int[] sorted = solution(list);
        System.out.println(Arrays.toString(list));
        System.out.println(Arrays.toString(sorted));
    }

    private static int[] solution(int[] arr) {
        // 중복값 제거
        Integer[] list = Arrays.stream(arr).boxed().distinct().toArray(Integer[]::new);
        // 내림차순 정렬
        Arrays.sort(list, Collections.reverseOrder());
        // int형 배열로 변경 후 반환
        return Arrays.stream(list).mapToInt(Integer::intValue).toArray();
    } 
}
