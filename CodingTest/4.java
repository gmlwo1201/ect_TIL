package Test;

import java.util.ArrayList;
import java.util.Arrays;

// 문제 4. 모의고사*
// 3명의 수포자가 문제를 다음과 같은 방식으로 찍었다
// 1번 : 1, 2, 3, 4, 5, 1, 2, 3, 4, 5...
// 2번 : 2, 1, 2, 3, 2, 4, 2, 5, 2, 1...
// 3번 : 3, 3, 1, 1, 2, 2, 4, 4, 5, 5...
// 1번 문제부터 마지막 문제까지의 정답이 순서대로 저장된 배열 answers가 주어졌을 때
// 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 반환하도록 solution() 함수 작성
// ! 조건 !
// 1. 시험은 최대 10,000 문제로 구성
// 2. 문제의 정답은 1, 2, 3, 4, 5 중 하나
// 3. 가장 높은 점수를 받은 사람이 여럿이라면 반환 값 오름차순으로 정렬
// 출력 예시
// [1, 2, 3, 4, 5] > [1]
// [1, 3, 2, 4, 2] > [1, 2, 3]

public class Test4 {
    public static void main(String[] args) {
        int[] list = {1, 3, 2, 4, 2};
        int[] sorted = solution(list);
        System.out.println(Arrays.toString(list));
        System.out.println(Arrays.toString(sorted));
    }
    
    public static int[] solution(int[] answers) {
        // 찍기 패턴
        int[][] pat = {
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5},
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };

        // 점수 저장 배열
        int[] scores = new int[3];

        // 패턴과 정답이 얼마나 일치하는지 확인
        for (int i = 0; i < answers.length; i++) {
            for (int j = 0; j < pat.length; j++) {
                if (answers[i] == pat[j][i % pat[j].length]) {
                    scores[j]++;
                }
            }
        }

        // 가장 높은 점수 저장
        int maxScore = Arrays.stream(scores).max().getAsInt();

        // 가장 높은 점수 가진 수포자들 번호 찾아 리스트에 저장
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == maxScore) {
                answer.add(i + 1);
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
