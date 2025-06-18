# 알고리즘 이해 기말고사
- 코딩 70%, 이론 30%, 10문제
- 알고리즘 1강 2차시 Big O 표현 시험범위
- 너비, 깊이 우선 탐색 이론
- 5강 2차시 버블, 쉘 등 4가지 정렬까지

## 너비 우선 탐색
* 선입선출 방식 - **큐**

## 깊이 우선 탐색
* 스택 자료구조 이용
* 재귀 함수로 구현

## 버블 정렬
* 이웃하는 숫자 비교해 작은 수 앞쪽으로 이동시키는 과정 반복해 정렬하는 알고리즘
```python
for pass = 1 to n-1
  for i = 1 to n-pass
    if (A[i-1] > A[i]) // 위의 원소가 아래의 원소보다 크면
      A[i-1] ↔ A[i] // 서로 자리를 바꾼다.
return 배열 A
```
* 총 비교 횟수: n(n-1)/2
* 시간 복잡도: O(n^2)

## 선택 정렬
* 입력 배열 전체에서 최소값 선택해 배열의 0번 원소와 자리 바꿈
* 0번 원소 제외 최소값 선택에 1번 원소와 자리 바꿈 > 반복해서 정렬
```python
for i = 0 to n-2 {
  min = i
  for j = i+1 to n-1 { // A[i]~A[n-1]에서 최솟값을 찾는다.
    if (A[j] < A[min])
      min = j
  }
  A[i] ↔ A[min] // min이 최솟값이 있는 원소의 인덱스
}
return 배열 A
```
* 총 비교 횟수: n(n-1)/2
* 시간 복잡도: O(n^2) - 항상 일정한 시간 복잡도 나타냄

## 삽입 정렬
* 배열을 정렬된 부분 (앞부분)과 정렬 안 된 부분 (뒷부분)으로 나눔
* 정렬 안 된 부분 가장 앞 원소 정렬한 부분의 적절한 위치에 삽입
```python
for i = 1 to n-1 {
  CurrentElement = A[i] // 정렬 안된 부분의 가장 왼쪽 원소

  j ← i – 1 // 정렬된 부분의 가장 오른쪽 원소로부터 왼쪽 방향으로 삽입할 곳을 탐색하기 위하여

  while (j >= 0) and (A[j] > CurrentElement) {
    A[j+1] = A[j] // 자리 이동
    j ← j -1
  }
  A [j+1] ← CurrentElement
}
return A
```
* 총 비교 횟수: n(n-1)/2
* 시간 복잡도: O(n^2) - 입력이 이미 정렬되어 있다면 시간 복잡도 O(n), 역으로 정렬될 경우 O(n^2)

## 쉘 정렬
* 버블 정렬 이용해 이웃하는 원소끼리의 자리이동으로 원소들 정렬
* 삽입 정렬 이용해 배열 뒷부분 작은 숫자 빠르게 앞으로 이동, 앞부분 큰 숫자는 뒷부분으로 이동
```python
for each gap h = [ h0 > h1 > ... > hk=1] // 큰 gap부터 차례로
  for i = h to n-1 {
    CurrentElement=A[i];
    j = i;
    while (j>=h) and (A[j-h]>CurrentElement) {
      A[j]=A[j-h];
      j=j-h;
    }
    A[j]=CurrentElement;
  }
return 배열 A
```
* 시간 복잡도: 최악의 경우 O(n^2)
* 2^k-1을 사용하면 O(n^1.5)-아직 확실 X

## O(Big-Oh)-표기
* 최악의 경우의 알고리즘 수행시간을 표현 - 점근적 상한 수행시간
* 가장 많이 사용하는 알고리즘 성능 표기법
* 표기 방법
  - n개의 숫자가 있는 경우 임의의 숫자 찾기 위한 순차탐색 최악 경우 시간 복잡도 = O(n)
  - 함수가 있으면 최고차항 사용

## Ω(Big-Omega)–표기
* 수행해야 하는 최소한의 수행시간 - 점근적 하한
* 표기 방법
  - O-표기와 마찬가지로 최고차항 사용

## Θ(Theta)–표기
* O 표기법(점근적 상한)과 Ω 표기법(점근적 하한)을 모두 만족시키는 증가 함수
* O-표기와 Ω-표기가 같은 경우에 사용
* 동일한 증가율 의미

# 코드 모음
## 깊이 우선 탐색 코드
```python
n, m = map(int, input().split())
graph = [[] for _ in range(n+1)]

for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

def dfs(node):
    # 노드 방문체크
    visited[node] = True
    # 시작점 기준으로 깊이 우선 탐색
    for next in graph[node]:
        if not visited[next]:
            dfs(next)

count = 0
visited = [False] * (n+1)
for i in range(1, n+1):
    if not visited[i]:
        dfs(i)
        count += 1

print(count)
```

## 깊이 우선 탐색 연결 요소 개수
```python
import sys
sys.setrecursionlimit(10000)
inpuy = sys.stdin.readline
n,m = map(int, input().split())

A = [ [] for _ in range(n+1) ]
visited = [False] * (n+1)

def DFS(v):
    visited[v] = True
    for k in A[v]:
        if not visited[k]:
            DFS(k)

for _ in range(m):
    s , e = map(int, input().split())
    A[s].append(e)
    A[e].append(s)

print("A = ", A)

## 연결 요소의 개수 저장할 변수
count = 0
for i in range(1, n + 1):
    if not visited[i]:
        count += 1
        DFS(i)

print("연결 요소의 갯수 = %d" % count)
```

## 깊이, 너비 우선 탐색 연결 요소 개수
```python
N,M,V= map(int,input().split())

graph = [[] for _ in range(N+1)]
for _ in range(M):
    a,b = map(int,input().split())
    graph[a].append(b)
    graph[b].append(a)

for i in graph:
    i.sort()

def dfs(start):
    print(start,end=' ')
    visited_dfs[start]=True
    for next_node in graph[start]:
        if not visited_dfs[next_node]:
            dfs(next_node)

from collections import deque            
def bfs(start):    
    q=deque()
    q.append(start)
    visited_bfs[start]=True
    
    while q:
        cur_node = q.popleft()
        print(cur_node,end=' ')
        for next_node in graph[cur_node]:
            if not visited_bfs[next_node]:
                q.append(next_node)
                visited_bfs[next_node]=True


visited_dfs = [False]*(N+1)                
dfs(V)    
print()
visited_bfs = [False]*(N+1)                
bfs(V)
```

## 이진 트리 탐색
```python
class TreeNode:
    def __init__(self):
        self.left = None
        self.data = None
        self.right = None

# 전역변수 선언 부분 #
memory = [ ]
root = None
nameList = ['A', 'B', 'C', 'D', 'E', 'F']

def inorderTraversal(node):
    if node is not None:
        inorderTraversal(node.left)
        print(node.data, end=" ")
        inorderTraversal(node.right)

# Main Code #
# 이진탐색 트리 생성 #
if __name__ == "__main__":
    node = TreeNode() # __init__(self) 생성자 호출
    node.data = nameList[0]
    root = node
    memory.append(node)

    for name in nameList[1 : ]:
        node = TreeNode()
        node.data = name
        current = root

        while True:
            if name < current.data:
                if current.left == None:
                    current.left = node
                    break
                current = current.left
            else:
                if current.right == None:
                    current.right = node
                    break
                current = current.right

        memory.append(node)

    print("이진탐색 트리 구성 완료")
    print("memory 리스트의 값 출력")
    for node in memory:
        print(node.data, end=" ")

    print("이진탐색트리를 중위순회한 결과")
    inorderTraversal(root)
```

## 이진 탐색으로 정수 찾기
```python
import sys

input = sys.stdin.readline

# 1. N 입력
N = int(input())

# 2. N개의 정수 입력받아 NumList에 저장
NumList = list(map(int, input().split()))

# 3. NumList 오름차순 정렬
NumList.sort()

# 4. M 입력
M = int(input())

# 5. M개의 정수 입력받아 target에 저장
target_list = list(map(int, input().split()))

# 6. 이진 탐색
for i in range(M):
    target = target_list[i]

    start = 0
    end = N - 1

    is_exist = 0

    while start <= end:
        mid_idx = (start + end) // 2
        mid_val = NumList[mid_idx]

        #  mid 값보다 큰 곳에서 target 찾아야 함
        if mid_val < target:
            start = mid_idx + 1
        # mid 값보다 작은 곳에서 target 찾아야 함
        elif mid_val > target:
            end = mid_idx - 1
        # target 찾음
        else:
            is_exist = 1
            break

    # 7. 원하는 답 출력
    print(is_exist)
```
