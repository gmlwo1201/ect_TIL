# 알고리즘 이해 TIL
## @ Stack
  ### Stack 이란?
  - 데이터를 차례로 쌓아 선입후출, 후입선출의 특징을 가진 자료구조
  - 데이터 삽입 : PUSH / 데이터 추출 : POP / 데이터 맨 윗층 : TOP
  - Last In First Out(LIFO)

## @ Code
  ### Stack code
```py
stk = [10, 20, 30]  # stack 기존 값 생성
print(f'기존 값 : {stk}')
stk.append(40)  # stack에 40 추가
print(f'40 추가 후 결과 : {stk}')
#topNum = stk.pop()
topNum = stk.pop(-1)  # stack top부터 bottom까지 -1 ~ -x
print("topNum = %d"  % topNum)  # %d는 값을 10진수로 표기, %는 place holder
print("topNum = "  , topNum)  
```

  ### Stack 추가, 삭제
```py
def push(item):
    global top
    stack.append(item)
    top += 1 # top = top + 1

def pop():
    global top
    if len(stack) != 0:
        # stack에서 top을 지정하는 다양한 방법
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
        num = int(input("1 : 삽입, 2 : 삭제, 3 : 종료 중 선택 = "))  # input은 값을 문자열로 받음, int로 숫자로 변환
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
  
  ![Image](https://github.com/user-attachments/assets/9ce6239c-1941-484c-82d6-16e96bac1f54)
