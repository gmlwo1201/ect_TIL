package Test;

import java.util.Arrays;
import java.util.HashSet;

// 문제 3. 두 개 뽑아서 더하기
// 정수 배열 numbers가 주어집니다. number에서 서로 다른 인덱스에 있는 2개의 수를 뽑아
// 더해 만들 수 있는 모든 수를 배열에 오름차순으로 담아 반환하는 solution() 함수를 완성하세요.
// ! 조건 !
// 1. 정수 배열의 길이는 2 이상 100 이하입니다.
// 2. 정수 배열의 각 데이터 값은 0 이상 100 이하입니다.
// 출력 예시
// [2, 1, 3, 4, 1] > [2, 3, 4, 5, 6, 7]
// [5, 0, 2, 7] > [2, 5, 7, 9, 12]

public class Test3 {
    public static void main(String[] args) {
        int[] list = {2, 1, 3, 4, 1};
        int[] sorted = solution(list);
        System.out.println(Arrays.toString(list));
        System.out.println(Arrays.toString(sorted));
    }

    public static int[] solution(int[] numbers) {
        HashSet<Integer> set = new HashSet<>(); // 중복 값 제거 위한 해시셋 생성

        // 두 수를 선택하는 모든 경우의 수 반복문으로 구함
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                // 두 수 더한 결과 해시셋에 추가
                set.add(numbers[i] + numbers[j]);
            }
        }
        // 해시셋 값 오름차순 정렬, int[] 형태 배열로 변환 후 반환
        return set.stream().sorted().mapToInt(Integer::intValue).toArray();
    }
}

