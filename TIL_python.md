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

