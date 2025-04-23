# 알고리즘 이해 중간대비
# 시험 범위는 깊이 우선 탐색X, 트리까지/10문제 100% 주관식

# 코드 정리

## 스택
```
stk = [10, 20, 30]
print(f'기존 값 : {stk}')
stk.append(40)  # stack에 40 추가
print(f'40 추가 후 결과 : {stk}')
#topNum = stk.pop()
topNum = stk.pop(-1)
print("topNum = %d"  % topNum)
print("topNum = "  , topNum)
print(f'40 삭제 후 결과 = {stk}')
```
## push, pop
```
def push(item):
    global top
    stack.append(item)
    top += 1 # top = top + 1

def pop():
    global top
    if len(stack) != 0:   
        #num = stack.pop()
        #num = stack.pop(-1)
        #num = stack.pop(top)
        num = stack.pop(len(stack) - 1)
        top -= 1 #top = top - 1
        return num
    else :
        print("stack이 비어있습니다.")

stack = [] # 리스트 방식의 빈 스텍 선언
top = -1

if __name__ == "__main__":
    while True:
        num = int(input("1 : 삽입, 2 : 삭제, 3 : 종료 중 선택 = "))
        # input은 값을 문자열로 받음, int로 숫자로 변환
        if num == 1:
            val = int(input("삽입할 데이터 = "))
            push(val)
            print("stack = ", stack)
        elif num == 2:
            pop_val = pop()
            if pop_val != None:
                print("stack의 top값 = %d" %  pop_val)
                print("stack = ", stack)
        else:
            print("3 : 종료를 입력했습니다.")
            break
```

## 스택 수열
```
## 1~n까지 수 스택에 저장, 출력하는 방식으로 수열을 만든다.
## 스택에 push 하는 순서는 오름차순
## 수열이 주어지면 위의 방식으로 스택을 이용해 수열을 만들 수 있는지 확인
## 만들 수 있다면 어떤 순서로 push와 pop을 수행해야 하는지 확인하는 프로그램 작성
# 입력 : 1번째 줄은 수열의 개수 n(1<=n<=1000000), 2~n번째 줄은 1 이상 n 이하의 정수 1개씩 주어짐(단, 같은 정수 2번 이상 나오지 않음)
# 출력 : 수열을 만들기 위한 연산 출력, push는 +, pop은 -로 출력, 불가능할 때는 NO 출력

n = int(input("수열의 개수를 정해주세요 : "))
stack = [] # 스택 생성
a = [] # +, -
find = True # 수열을 만들 수 있는가?

x = 1 # 입력 순서
for i in range(n):
    num = int(input("숫자를 입력해주세요(중복 안되게) : "))
    while x <= num: # 입력한 숫자가 그 숫자의 순서와 같을 때 까지 push
        stack.append(x)
        a.append('+')
        x += 1
    if stack[-1] == num: # 스택에 push된 값이 입력값과 같을 때
        stack.pop()
        a.append('-')
    else:
        find = False # 수열을 만들 수 없음

if find == False:
    print("NO")
else:
    for y in a:
        print(y)
```

## 리스트
```
class SList:
    class Node:
        def __init__(self, item, link):
            self.item = item
            self.next = link

    def __init__(self):
        print("나는 SList의 Constructor 메서드")
        self.head = None
        self.size = 0

    def isEmpty(self) :
        return self.size == 0
    
    # 리스트의 맨 앞에 삽입
    def insert_front(self, item): 
        if self.isEmpty():
            self.head = self.Node(item, None)
        else:
            self.head = self.Node(item, self.head)
        self.size += 1

    # 리스트의 맨 뒤에 삽입
    def insert_after(self, item, p):
        p.next = self.Node(item, p.next)
        self.size += 1

    def find(self, target):
        p = self.head
        # k = 0, 1, 2
        for k in range(self.size):
            if target == p.item:
                return k
            p = p.next

    def remove_front(self):
        if self.isEmpty():
            print("삭제작업 불가")
            return None
        else:
            temp = self.head
            self.head = self.head.next
            del temp
            self.size -= 1

    def remove_after(self, p):
        if self.isEmpty():
            print("리스트 텅 비어서 삭제불가")
            return None
        else:
            temp = p.next
            p.next = temp.next
            del temp
            self.size -= 1

    # 원하는 인덱스에 새로운 노드 삽입
    def insert_index(self, index, item): 
        new_node = self.Node(item, index)

        if index == 0:
            new_node.next = self.head
            self.head = new_node
            return
        
        p = self.head
        count = 0

        while p is not None and count < index - 1:
            p = p.next
            count += 1

        if p is None:
            print("인덱스 범위 벗어났습니다.")
            return
        
        new_node.next = p.next
        p.next = new_node
    
    # .. 삭제
    def delete_index(self, index):
        if index == 0:
            if self.head is not None:
                self.head = self.head.next
            else:
                print("텅 비어있어 삭제 불가")
            return
        
        temp = self.head
        count = 0

        while temp is not None and count < index - 1:
            temp = temp.next
            count += 1

        if temp is None or temp.next is None:
            print("인덱스 범위 벗어남")
            return
        
        temp.next = temp.next.next

    # 리스트 맨 마지막 노드 삭제
    def delete_final(self):
        if self.head is None:
            print("텅 비어있어 삭제 불가")
            return
        if self.head.next is None:
            self.head = None
            return
        
        temp = self.head
        while temp.next.next is not None:
            temp = temp.next

        temp.next = None

    def showList(self):
        p = self.head
        while p:
            if p.next is not None:
                print(p.item, "=> ", end="")
            else:
                print(p.item)
            
            p = p.next

if __name__ == "__main__":
    s = SList()
    s.insert_front("mango")
    s.insert_front("apple")
    s.insert_after("cherry", s.head.next)
    s.showList()
    # index = s.find("cherry")
    # print("cherry는 %d번째 노드에 있음" % index)
    print("cherry는 %d번째 노드에 있음" %
                (s.find("cherry")+1))
    s.remove_front()
    print("첫번째 노드인 apple을 삭제 후 = ")
    s.showList()
    s.insert_front("melon")
    s.remove_after(s.head)
    print("mango 노드 삭제 후 = ")
    s.showList()
    print("")

    # 실습 과제 추가 코드
    s.insert_front("kiwi")
    s.insert_front("strawberry")
    s.insert_front("grape")
    s.insert_front("peach")
    s.insert_front("banana")

    print("pear을 2번째 노드에 삽입")
    s.insert_index(2, "pear")
    s.showList()
    print("")
    print("3번 노드 값 삭제")
    s.delete_index(3)
    s.showList()
    print("")
    print("맨 마지막 노드 삭제")
    s.delete_final()
    s.showList()
```

## 큐
```
class LinkedQueue:
    class Node:
        def __init__(self, item, next):
            self.item = item
            self.next = next
    
    def __init__(self):
        self.front = None
        self.rear = None
        self.size = 0

    def isEmpty(self):
        return self.size == 0
    
    def add(self, item):
        newnode = self.Node(item, None)
        if self.isEmpty():
            self.front = newnode
        else:
            self.rear.next = newnode

        self.rear = newnode
        self.size += 1
    
    def showq(self):
        p = self.front
        print("front => ", end='')
        while p:
            if p.next != None:
                print("{!s:<7}".format(p.item), "->", end="")
            else:
                print(p.item, end="")
            p = p.next
        print(" <= rear ")

    def remove(self):
        if self.isEmpty():
            raise EmptyError("queue안에 아무 것도 없음")
        else:
            fitem = self.front.item
            prefront = self.front
            self.front = self.front.next
            self.size -= 1
            del prefront
            if self.isEmpty():
                self.rear = None
            return fitem


class EmptyError(Exception):
    pass

if __name__ == "__main__":
    q = LinkedQueue()
    q.add("apple")
    q.add("orange")
    q.add("cherry")
    q.showq()
    removeitem = q.remove()
    print("front인 사과 삭제 후 removeitem =", removeitem)
    q.showq()
    removeitem = q.remove()
    print("front인 오렌지 삭제 후 removeitem =", removeitem)
    q.showq()
    q.add("grape")
    print("grape 추가 = ")
    q.showq()
```

## 큐 카드 게임
```
from collections import deque

N = int(input("카드 장 수 = "))
deque = deque([i for i in range(1, N+1)])

while(len(deque) > 1):
    deque.popleft()
    m_num = deque.popleft()
    deque.append(m_num)

print("마지막 남은 카드 = ")
print(deque[0])
```

# 중요 설명 정리
## Stack
- 데이터를 차례로 쌓아 선입후출, 후입선출의 특징을 가진 자료구조
- 데이터 삽입 : PUSH / 데이터 추출 : POP / 데이터 맨 윗층 : TOP
- Last In First Out(LIFO)

## 기본 자료구조
* 선형 자료구조
  - 순서 정해져 있는 자료구조
  - 데이터 정해진 자료형으로 순서대로 저장
  - 저장된 여러개의 데이터 중에 특정 데이터 순차적으로 접근

* 비선형 자료구조
  - 순서 정해져 있지 않은 자료구조
  - 데이터 정해진 자료형으로 순서 상관없이 저장
  - 기본 자료구를 다양하게 적용해 표현
  - 저장된 여러 개의 데이터 중에 특정 데이터 접근하려면 저장된 규칙 알아야함

* 기본 자료구조
  - 배열/연결 리스트
  - C 언어에서 제공하는 자료구조
  - C++, JAVA, Python 등에서 제공하는 다양한 자료구조 사용해도 결국 기본 자료구조로 주기억장치에 저장

* 배열
  - 번호와 번호에 대응하는 데이터들로 이뤄진 자료구조
  - 순차적 데이터 저장
  - 개념 쉽고 사용 편하지만 메모리 사용 효율성 낮음
  - 데이터 접근 빠름

  ![Image](https://github.com/user-attachments/assets/039eb3fd-81fd-455d-9422-07648f3a28db)

* 연결 리스트
  - 링크드 리스트
  - 노드(데이터, 포인터)가 한줄로 연결되어 있는 방식의 자료구조
  - 포인터는 다음 or 이전 노드와의 연결(주소 값) 담당
  - 개념 복잡, 구현 어려움
  - 메모리 사용 효율성 높음
  - 데이터 접근 느림
  - 단일 연결 리스트/이중 연결 리스트/원형 연결 리스트

  @ 단일 연결 리스트
  ![Image](https://github.com/user-attachments/assets/9ce6239c-1941-484c-82d6-16e96bac1f54)

## 스택 구조
* 스택
  - 입출구가 하나로 선입후출, 후입선출
  - 규칙을 가지고 데이터 저장
* 기본 구조
  - push:데이터 삽입
  - pop:데이터 추출
  - top:가장 위의 데이터
* 구현
  - 배열 자료구조 이용
    - 스택 크기를 미리 정해놓고 사용
  - 연결 리스트 자료구조 이용
    - 스택 크기가 한정되지 않고 삽입 삭제 용이
  - 각 프로그래밍 언어에서 제공하는 자료구조(자료형) 이용하여 구현

![image](https://github.com/user-attachments/assets/f976887e-5be0-4728-b8f2-5155d080592e)

## 큐(QUEUE)
* 큐
  - 기본 자료구조에 규칙 포함한 자료구조
  - 삽입과 삭제가 양 끝에서 각각 수행
  - 규칙 가지고 데이터 저장
  - 너비 우선 탐색(BFS) 에서 자주 사용
  - 규칙
    - 가장 마지막에 데이터 저장
    - 가장 처음 데이터 읽은
    - 선입선출(FIFO)
* 구현
  - 배열 자료구조 이용
    - 큐 크기 미리 정해놓고 사용
    - 선형 큐 > 삭제 후 데이터 위치 이동/재사용 불가 > 순환 큐 구현
    ![image](https://github.com/user-attachments/assets/0ae7e6ac-0e34-420b-9e0b-f33047357d09)

  - 연결 리스트 자료구조 이용
    - 큐 크기 한정되지 않음
  - 각 프로그래밍 언어에서 제공하는 자료구조(자료형) 이용하여 구현
* 사용
  - 우선순위가 같은 작업 예약
  - 은행업무
  - 콜센터 고객 대기시간
  - 프로세스 관리
 
## 데크
* 데크(Double-ended Queue)
  - 양쪽 끝에서 삽입, 삭제 허용하는 자료구조
  - 스택, 큐 자료구조 혼합 > 두 자료구조 성질 모두 가짐
  - 스텍, 큐 동시 구현하는 데 사용
* 응용
  - 스크롤
  - 문서 편집기 등의 undo 연산
  - 웹 브라우저 방문기록 등
    @ 최근 방문한 웹 페이지 주소는 앞에 삽입, 일정 수의 새 주소 삽입되면 뒤에서 삭제 진행
* 특징
  - 데크는 이중 연결 리스트로 구현하는 것이 편리
  - 단순 연결 리스트는 노드의 이전 노드 레퍼런스 알아야 삭제 가능
  - 파이썬에는 Collections 패키지에 데크 정의되어 있음
  - 삽입, 삭제 등의 연산은 파이썬 리스트 연산과 매우 유사
* 수행 시간
  - 배열이나 이중 연결 리스트로 구현한 경우, 스택/큐 수행 시간과 동일
  - 양 끝에서 삽입/삭제 가능 > 프로그램 복잡
  - 이중 연결 리스트로 구현한 경우 > 더 복잡
 
## 그래프
* 그래프
  - 선형/트리 자료구조로 표현 어려운 n:n 관계 가지는 원소들 표현하기 위한 자료구조
  - 정점과 간선 모음들의 결합
  - 정점의 집합 : V, 간선의 집합 : E, 그래프 : G -> G = (V,E)
* 용어
  - 인접(Adjacent) : 간선으로 연결되어 있는 두 정점
  - 경로(Path) : 그래프 안에서 형성된 길
  - 경로 길이(path length) : 경로 구성하는 간선 수
  - 단순 경로(simple path) : 모두 다른 정점으로 구성된 경로
  - 차수(degree) : 정점에 속한 간선들의 수
  - 연결(Connected) : 무방향 그래프 - 두 정점 사이 경로 존재 > 두 정점이 연결 / 그래프 내 모든 정점이 연결 > 그래프 연결
  - 사이클(Cycle) : 단순 경로에서 처음과 마지막 정점이 같은 단순 경로
  - DAG(directed acyclic graph) : 방향 그래프면서 사이클 없는 그래프
* 종류
  - 무방향 그래프(Undirected Graph) : 간선에 방향성 없는 그래프, 정점의 쌍에 순서 없음
  ![image](https://github.com/user-attachments/assets/727ca033-d889-4926-9536-8c1e2e9d40e5)

  - 방향 그래프(Directed Graph) : 간선에 방향성 있는 그래프, 정점의 쌍에 순서 있음
  ![image](https://github.com/user-attachments/assets/5ae3e79e-6aa4-4ae6-aea0-07d700083219)

  - 완전 그래프(complete graph) : 각 정점에서 다른 모든 정점 연결해 가능한 최대의 간선 수 가진 그래프
    - 정점 n개 무방향 그래프의 최대 간선 수 : n(n-1)/2 || ... 방향 그래프 최대 간선 수 : n(n-1)
  ![image](https://github.com/user-attachments/assets/c2e8a4bc-3358-46db-a8f4-d1a6b38d55de)

  - 부분 그래프(subgraph) : 기존 그래프의 일부 정점/간선 제외해 만든 그래프
  ![image](https://github.com/user-attachments/assets/75570478-5a9b-4d44-990b-9bffe6fc1433)

  - 가중 그래프(weight graph), 네트워크(network) : 간선에 가중치(weight) 할당한 그래프
  ![image](https://github.com/user-attachments/assets/4f9781be-a0a5-412c-8900-d87f25f31df5)


* 자료구조
  - 그래프 특징과 규칙 갖도록 데이터 저장 > 자료구조
  - 자료형과 기본 자료구조 이용하여 데이터 저장
  - 프로그래밍 언어에서 제공하는 자료형 이용하여 표현
* 표현 - 인접 리스트
  - 배열과 연결 리스트 자료구조 사용
  - 적은 메모리 공간 요구, 인접 행렬보다 접근 느림
  - 연결 리스트 자료구조 통하여 그래프 표현
  - 그래프의 각 정점마다 헤드 노드 두어 각 정점 할당
  - 사이에 정점이 존재하는 정점들을 헤드 노드 뒤에 연결
  - 무방향 그래프일 경우 헤드 노드 뒤에 연결된 노드 개수가 그 노드의 차수
  ![image](https://github.com/user-attachments/assets/a1530555-0bed-4ae9-a914-e18f8934d14a)

* 표현 - 배열(인접 행렬)
  - 2차원 배열 자료구조 사용
  - 많은 메모리 공간 요구, 인접 리스트보다 접근 빠름
  - 정점 수만큼 행과 열을 가진 행렬 이용하여 표현
  - 정점 i와 j 사이 간선 존재하면 i번째 행과 j번째 열 원소 1로 표현
  - 간선 없으면 i번째 행과 j번째 열 원소가 0으로 표현
  - n개의 정점 가진 무방향 그래프의 인접 행렬에서 진입 차수는 행의 합
  ![image](https://github.com/user-attachments/assets/60ec009a-64fa-46af-8340-53a5154644ef)
  ![image](https://github.com/user-attachments/assets/e91ecb94-168b-416c-a1dc-594e51167ace)

* 그래프 자료구조 이용한 문제 해결 알고리즘
  - 최단 거리 문제 : 프림, 크러스컬, 다익스트라
  - 탐색 문제 : DSF 탐색, BFS 탐색
 
## 트리(Tree)
* 트리
  - 노드와 간선으로 구성
  - 그래프의 일종으로 여러 노드가 한 노드를 가리킬 수 없는 구조
  ![스크린샷 2025-04-03 140043](https://github.com/user-attachments/assets/1bd959d1-0891-438e-8368-f3c17c7c11f8)
 
* 용어<br>
![image](https://github.com/user-attachments/assets/01d9ff63-6fa6-43f7-86db-f8b641dfc47b)


* 종류
  - B트리
    - 자식 노드가 2개 이상
  - 이진트리
    - 자식 노드가 2개 이하(0,1,2)
  ![image](https://github.com/user-attachments/assets/f72fcdd0-b93e-4779-9485-2332270b153e)

  * 완전(Average Valanced Level) 이진트리
    - 자식 노드 추가할 때 왼쪽부터 오른쪽으로 추가
  ![image](https://github.com/user-attachments/assets/9749b75f-4932-4c02-a315-69fe6ef72221)

  * 균형 이진트리
    - 자식 노드의 추가 위치 상관없음, 왼쪽 오른쪽 레벨 차이 1 이하
  ![image](https://github.com/user-attachments/assets/d7c05fb8-e72a-49c3-9fee-a74f53d5f088)

  * 포화 이진트리
    - 자식 노드가 가득찬 상태(모두 2개씩 연결)
  ![image](https://github.com/user-attachments/assets/14cd1534-9fe8-4f55-b006-2e7b04e4a3a3)

  * 일반 이진트리
    - 균형도 포화도 아닌 이진트리
  ![image](https://github.com/user-attachments/assets/26f2ec26-990f-4ffc-a08e-34ea75c169a7)

  * 편향(사향) 이진트리
    - 자식 노드 한쪽에만 배치됨
  ![image](https://github.com/user-attachments/assets/a0c7c0da-ac7c-4c3f-bab3-ac19a28f1c3c)

* 이진트리 노드 구조
![image](https://github.com/user-attachments/assets/c66890a2-9f6c-4fbd-be40-ba797d44c76b)

* 생성 과정
  - 높이 : 2 / 데이터 6개 완전 이진트리 생성
  1. 루트 노드 생성
  2. 두 번째 노드 생성, 루트 노드 *왼쪽 노드*로 지정
  3. 세 번째 노드 생성, *오른쪽 노드*로 지정
  4. 네 번째부터 여섯 번째까지 노드 생성, 연결

* 자료구조
  - 트리 특징과 규칙 갖도록 데이터 저장 -> 자료구조
  - 자료형과 기본 자료구조 이용해 데이터 저장
  - 프로그래밍 언어에서 제공하는 자료형 이용해 표현
  
* 표현
  - 인접 리스트
    - 왼쪽, 오른쪽 자식 가리키는 포인터
  - 배열
    - 1차원 배열
      - 자신의 부모 노드만 저장
      ![image](https://github.com/user-attachments/assets/63d08997-59ba-433c-9d40-2bdba1a019f0)

    - 2차원 배열
      - A[i][0] : 왼쪽 자식 노드 , A[i][1] : 오른쪽 자식 노드
      ![image](https://github.com/user-attachments/assets/daf2e56b-44ed-41cb-97d0-b04c55db74dd)

* 트리 자료구조 이용한 문제 해결 알고리즘
  - 효율적 데이터 삽입, 삭제
  - 탐색 문제 - 이진 탐색
  - 정렬 문제 - 힙 정렬
  - 압축 문제 - 허프만 압축
 
* 이진 탐색(Binary Search)
  - 데이터를 오름차순으로 정렬해야 함
  - 데이터의 중앙 값(middle)을 선정하여 키 값과 비교
  - 키 값 > middle > middle 왼쪽, 반대 > 오른쪽 데이터 제거
  - 남은 데이터에서 middle 선정해 반복
![image](https://github.com/user-attachments/assets/012e6a19-3995-4ac1-9a31-a55c6eff290e)
 
* 힙 정렬(트리)
  - 이진 트리 구성 방법
  - 루트 값 선정 > 루트보다 작은 데이터 왼쪽 노드, 크면 오른쪽 노드
  - 자식 노드에서 반복
  - 탐색 > 이진 탐색과 동일
![image](https://github.com/user-attachments/assets/228a14a5-8172-4d46-8849-bfc81aa1c979)

* 그래프/트리 차이
![image](https://github.com/user-attachments/assets/9a91a77d-fdf5-45a3-b068-4c3ba63a06af)

## 그래프 순회(탐색)
  - 그래프 또는 트리 같은 연결 구조에서 **모든 정점(노드)** 을 순차적으로 탐색
  - 목적 : 특정 노드 탐색
  - 깊이 우선 탐색(DSF)
  - 너비 우선 탐색(BSF)
  - 경험적 탐색
 
* 깊이 우선 탐색(DSF:Depth First Search)
  - 시작 노드 정하고 방문하지 않은 노드가 존재하지 않을 때까지 방문
  - 방문한 노드의 인접 노드를 전부 방문하면 방문했던 경로로 되돌아감
  - 시작 노드에서 한 방향으로 마지막 노드까지 방문
  - 방문 경로 되돌아가면서 방문하지 않은 노드 확인 후 방문
  - 후입선출 구조 스택 사용
  
  ![image](https://github.com/user-attachments/assets/f106279d-8e3f-483b-b5d9-417f5c4cfc35)

  ![image](https://github.com/user-attachments/assets/b5a61fd5-cb36-4487-bd74-1b9332f042f9)

  * 트리구조 DSF<br>
  ![image](https://github.com/user-attachments/assets/0adca823-0b8c-4563-b45b-b42e800ba2be)

* 너비 우선 탐색(BSF:Breadth First Search)
  - 시작 노드를 정하고 인접한 노드 차례대로 모두 방문
  - 방문했던 정점 시작으로 다시 인접한 정점 차례로 방문
  - 시작 노드와 가까운 노드부터 방문하고 멀리 떨어진 노드는 나중에 방문
  - 선입선출 구조 큐 사용

  ![image](https://github.com/user-attachments/assets/6c1ca4d1-b6a0-40d8-b901-ab4e1f8ec45a)

  ![image](https://github.com/user-attachments/assets/2ea2be7b-ae6c-4e03-85ed-3673bda7e44a)

  * 트리 구조 BSF<br>
  ![image](https://github.com/user-attachments/assets/41f59e26-aff3-4830-b5cc-d449ad35d8b2)
