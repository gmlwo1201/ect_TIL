# 중간고사 시험범위 정리

## 0. 플러터 개발 환경 준비
* **앱 개발 방식**
  * 네이티브 양식
    - 플랫폼 자체 제공 개발 환경으로 개발
    - 각 플랫폼에 맞는 환경으로 개발해야 함
    - 안드로이드 > 안드로이드 스튜디오/자바,코틀린|iOS(맥OS) > 엑스코드/스위프트,오브젝티브-C 
  * 하이브리드 방식
    - 웹 기술로 앱 화면 제작 > 네이티브 기술로 앱 형태로 포장
    - 빠른 앱 개발 가능
    - UI 별도 개발 필요 > 네이티브 성능에 미치지 못함
  * 크로스 플랫폼 방식
    - 한번 구현으로 각 플랫폼용 앱 제작 가능
    - 빌드 시 네이티브 코드로 변환 > 네이티브와 거의 같은 성능

* **플러터 특징**
  - 크로스 플랫폼 지원
  - 고성능
  - 사용자 정의 UI 구성 용이
  - 빠른 개발
  - 강력한 커뮤니티 및 생태계

## 1. 첫 모바일 앱 생성
* **환경 구성 검사**
  * Flutter doctor 명령어 이용해 검사
    - 1. cmd에서 실행 - flutter doctor
    - 2. 안드로이드 스튜디오 Terminal에서 실행 - flutter doctor
    - 3. 안드로이드 스튜디오 프로젝트 창에서 사용
      > 프로젝트 열기 > pubspec.yaml > Flutter doctor

## 2. Dart 문법
* **변수 타입**
  - int : 정수
  - double : 실수/소수점
  - String : 문자열
  - bool : 참/거짓
* **final, const**
  - 상수(값 설정되면 바꿀 수 없음)
  - 차이점 : final은 동작 중 값 고정, const는 컴파일 시점에서 값 고정
* **타입 검사, 형 변환**(강의 설명 내용 - hint. 상속)
  * 타입 검사 - is, is!
    - is : 같은 타입이면 true, 아니면 false/is!는 반대
    - 연산 결과 : bool형
    - 상속 : 부모 검사도 true/ 부모가 아니라면 false
  * 형 변환 - as
    - 명시적 형 변환
    - 연산 결과 : 형 변환 된 타입
    - 형 변환 필요성 - 객체지향 언어 객체의 형 변환 필요
    - 상속 : 자식 타입으로 캐스팅할 때 사용(실제 타입 맞아야 함)                                                                                                                                                             
* **익명 함수, 람다식**
  * 익명 함수
    - 함수 코드 변수 값처럼 취급 > 변수에 담기 가능
  ```dart
  var list = [1, 2, 3, 4];
  list.forEach((item) {			// (item) { … } 익명 함수
    print(item);
  });
  ```
  * 람다식
    - 익명 함수보다 단순한 함수코드 간단하게 표현 가능
    - (인수 명) => (코드/반환 값)
  ```dart
  var list = [1, 2, 3, 4];
  list.forEach((item) => print(item));
  ```
* **선택 매개변수**
  - 선택적으로 함수에 전달하고자 하는 매개변수 {}로 표시
```dart
void somthing({String name, int age}) {}
void main() {
   something(name: ‘홍길동’, age: 10);
   something(name: ‘홍길동’);
   something(age: 10);
}
```
* **클래스 정의**
  - 구성 요소 : 필드, 생성자, 메소드
```dart
class Person {
  // 필드 - 선언된 변수, 객체 상태 저장
  String name;
  int age;

  // 생성자 - 객체 생성 시 호출되는 함수
  Person(this.name, this.age);

  // 메소드 - 클래스가 가진 동작(함수와 동일)
  void introduce() {
    print("$name");
  }
}
```
  - 기타 문법
    > 접근 지정자(이름 앞 _붙이면 외부 접근 불가), 게터/세터, 상속, 추상 클래스, 믹스인, 열거 타입, 컬렉션, 스프레드 연산자, Map, Set
* **상속 vs 인터페이스**(강의 설명 내용 - hint. 서로 장단점 반대)
  * 상속
    - 한 클래스의 속성과 클래스 그대로 상속
    - 단 하나의 클래스만 상속 가능
    - 메소드 재정의(override) 가능
    - 부모의 기능 그대로/일부만 바꿔서 재사용에 용이
  * 인터페이스
    - 다중 구현 가능
    - 구현 시 모든 멤머(메소드, 게터/세터 등) 반드시 재정의 해야함
    - 기존 클래스도 인터페이스처럼 사용 가능
    - 규칙 강제하고 싶을 때, 여러 개 클래스 기반 기능 구현에 용이
* **List, Map, Set 표기법**
  * List
    ```dart
    List<String> items = ['A, B, C'];  // List<> var로 사용 가능
    ```
  * Map
    ```dart
    var cityMap = {
      '한국':'부산'
      '일본':'도쿄'
      '중국':'베이징'
    };

    cityMap['한국'] = '서울'; // value 값 변경
    cityMap['미국'] = '워싱턴' // 새로운 값 추가
    ```
  * Set
    ```dart
    var citySet = {'서울', '수원', '오산'};

    citySet.add('부산'); // 추가
    cirySet.remove('오산'); // 제거

    print(citySet.contains('서울')); // Set에 서울이 있는지 = true
    ```
    
* **함수형 프로그래밍 특징 : 일급 객체, forEach, where, map**
  * 일급 객체 - 함수를 변수로 전달, 수정, 대입 가능한 객체
    - 함수를 함수 반환 값으로 사용 가능
    - 프로그래밍 유연성, 표현력 높일 수 있음
  * forEach - 내부 반복 수행(for: 외부 반복 수행)
    - (E element) {} 향테 함수를 인수로 받음 (E는 모든 타입 가능)
  * where - 조건 필터링
    - (상수 값).where(조건 식).forEach(print);
  * map - 반복되는 값 다른 형태로 변환
    - E.where.map(변환 식).forEach();
* **컬랙션 if/for**
  * 조건에 의해 컬렉션 값 조정/다르게 사용하고 싶을 때
  * if - 임시 변수 작성 필요 없음, 더 유동적 코드 작성 가능
    ```dart
    bool proActive = true;

    print([1,2,3,4,5, if(promoActive) 6]); // [1,2,3,4,5,6]
    ```
  * for
    ```dart
    var listOfInts = [1,2,3]
    var listOfStrings = [
      '#0',
      for (var i in listOfInts) '#$i'
    ];
    listIfStrings.forEach(print);  // #0,#1,#2,#3
    ```
## 3. 프로젝트 구조 / 앱 구조
* **pubspec.yaml 관련 설명**(LLM 이용해 상세 파악)
  - 프로젝트에 필요한 의존성 패키지, 버전 정보, 에셋, 환경 설정 등을 정의하는 파일
  - name, description, version - 프로젝트 이름, 설명, 버전 정보
  - enviroment - 사용하는 Dart SDK 버전 범위 지정
  - dependencies - 앱에서 실제 사용되는 패키지들
  - dev_dependencies - 테스트/개발에만 쓰이는 패키지(ex.flutter_test,flutter_lints,mockito 등)
  - flutter - Flutter 앱 전용 설정(uses-material-design: true = 머티리얼 디자인 사용)
  - assets - 이미지, JSON 등(프로젝트 내 디렉토리, 실제로 파일이 존재해야 함)
  - fonts - 커스텀 폰트 추가
  > flutter pub get: yaml 수정 후 실행해야 의존성 적용 / outdated: 사용중인 패키지들 업데이트 상태 확인 / upgrade: 가능한 최신 버전으로 업데이트
* **샘플 앱 기본 구조**(Ex. Stateless/StatefulWidget 기본 구조, 각각 동작성 차이)
  * @ Stateless, Stateful 동작성 차이
    - Stateless
> 1. build 메소드 return 앞 어떤 화면인지 알 수 있게 print 로그 작성 | 2. 앱 실행 | 3. push로 두 번째 페이지 표시 | 4. pop으로 두 번째 페이지 결과 첫 번째 페이지로 전달
    - Stateful
> 1. push 메소드로 SecondStatefulPage 클래스 표시 | 2. FirstStatefulPage 클래스 build 메소드와 | 3. SecondStatefulPage 클래스 build 메소드 호출
  * main() - 앱의 시작점, 함수에 인스턴스를 전달함
  * *StatelessWidget* - 상태가 없는 위젯 정의(한번 그려진 후 다시 그리지 않음)
    - 프로퍼티를 변수로 가지지 않음
    - 불변 UI 구성, 재사용 가능, 렌더링 효율 좋음
    - build() 메소드 가짐 (build 메소드는 위젯 작성할 때 호출 > 실제 화면에 그릴 위젯 작성해 변환)
  ```dart
  class MyApp extends StatelessWidget {  // Stateless 상속받은 Myapp가
   @override
   Widget build(BuildContext context) {  // Material의 인스턴스 작성해 반환
    return MaterialApp(...);
   }
  }
  ```
  * MaterialApp - title, theme, home 세가지 이름이 있는 인수 설정
    > title : 제목, theme : 테마, home : 실제 앱에 표시할 위젯
    - build() 메소드에서 반환하는 인스턴스
    - Flutter 애플리케이션 기본 설정, 구조 제공에 사용
    - UI 기본구조 제공, 테마 설정, 내비게이션 관리, 지역화 지원, 디버깅 도구 제공
  ```dart
  return MaterialApp(
   title: 'Flutter',
   theme: ThemeData(
    primarySwatch: Color.blue,
   ),
   home: MyHomePage(title: 'Flutter Demo Home Page'),
  );
  ```
  * *StatefulWidget* - 상태가 있는 위젯 정의
    - MyHomepage(< Stageful), _MyHomePageState(< State<MyHomePage>)로 구성
    - 상태 관리, UI 재구성, 동적 데이터 처리
  ```dart
  class MyHomePage extends StatefulWidget {
   MyHomePage({Key key, this.title}) : super(key:key);  // 1
   final String title;
   @override
   _MyHomePageState createState() => _MyHomePageState();  // 2
  }
  class _MyHomePageState extends State<MyHomePage> {
   int _count = 0;  // 변경 가능한 상태  3
   ...
   @override
   Widget build(BuildContext context) {  // 4
    return Scaffold(...)
   }
  }
  ```
   - 1. MyHomePage 생성자는 key, title 프로퍼티 옵션으로 받아 super 키워드로 부모 클래스 생성자에 key 전달
   - 2. 상속받은 createState() 메소드 재정의, _MyHomePageState 인스턴스 반환, 생성될 때 한 번만 실행
   - 3. 상태 클래스(Stare 상속받은 클래스) 변경 가능한 상태 프로퍼티 변수로 표현, 화면 다시 그릴 때 값 변경
   - 4. _MyHomePageState 클래스 상태에 따라 화면에 그려질 코드 작성, build() 메소드에 그려질 부분 정의
  * 위젯 > 위젯으로 값 전달 - 생성자 이용해 전달 (MaterialAPP - home)
  * 상태 변경 - State 클래스 이용해 상태 변경 > 변경 내용 따라 UI화면 변경
    - _incrementCounter() 의해 setState() 호출
    - setState() 호출 > Scaffold 영역 갱신
  * Scaffold, AppBar - 머티리얼 디자인 앱 뼈대
    - MaterialApp > Scaffold
  
## 4. 기본 위젯 I
* **화면 배치용**
  * Container - 너비, 높이가 있는 영역
```dart
body: Container(
  width: 100,
  height: 100,
  color: Colors.red,
  child: Text('디모이'),
  padding: EdgeInsets.all(8.0),
  margin: EdgeInsets.all(8.0),
),
```
![image](https://github.com/user-attachments/assets/ca94605d-7667-46b9-a420-14efc38166aa)


* width, height, padding, margin, child 속성
  - child 속성으로 자식 위젯 담을 수 있음
* Column - 수직 방향으로 위젯 배치
  - children 속성
* Row - 수평 방향으로 위젯 배치
  - children 속성
```dart
body: Row( // Column
       children: [
         Container(
           width: 100,
           height: 100,
           color: Colors.red,
           margin: EdgeInsets.all(10.0),
           padding: EdgeInsets.all(10.0),
           child: Text('111'),
         ),
         Container(
           width: 100,
          height: 100,
           color: Colors.blue,
           margin: EdgeInsets.all(10.0),
           padding: EdgeInsets.all(10.0),
           child: Text('222'),
         ),
         Container(
           width: 100,
           height: 100,
           color: Colors.yellow,
           margin: EdgeInsets.all(10.0),
           padding: EdgeInsets.all(10.0),
           child: Text('333'),
         ),
       ],
      ),
```
![image](https://github.com/user-attachments/assets/51c51eed-5916-4c4f-8a53-39119269aeb1)

* Stack - 위젯 순서대로
```dart
body: Stack(
       children: [
         Container(width: 100, height: 100, color: Colors.red),
         Container(width: 100, height: 100, color: Colors.blue),
         Container(width: 100, height: 100, color: Colors.yellow),
       ], // ctrl + alt + l = 정리
),
```
* SingleChildScrollView - 화면 크기 넘어가면 스크롤 생성
```dart
body: SingleChildScrollView(
        child: ListBody(
          children: items.map((i) => Text('$i')).toList(),
        ),
),
```
* ListView, ListTile - 리스트 표시
  - ListTitle 위젯 이용해 리스트 아이템 쉽게 작성 가능
```dart
body: ListView(
        scrollDirection: Axis.vertical,
        children: [
          ListTile(
            leading: Icon(Icons.home, size: 40,),
            title: Text('Home'),
            trailing: Icon(Icons.navigate_next),
            onTap: () {},
          ),
          ListTile(
            leading: Icon(Icons.event, size: 30,),
            title: Text('Event'),
            trailing: Icon(Icons.navigate_next),
            onTap: () {},
          ),ListTile(
            leading: Icon(Icons.camera, size: 20,),
            title: Text('Camera'),
            trailing: Icon(Icons.navigate_next),
            onTap: () {},
          )
        ],
),
```
* GridView - 열 수를 지정하여 그리드 형태로 표시
```dart
body: GridView.count(
          crossAxisCount: 2,
          children: [
            Container(color: Colors.red, margin: EdgeInsets.all(80.0),),
            Container(color: Colors.blue, margin: EdgeInsets.all(80.0),),
            Container(color: Colors.yellow, margin: EdgeInsets.all(80.0),)
        ],
),
```
* PageView - 여러 페이지 좌우로 슬라이드하여 넘길 수 있게 하는 위젯
```dart
body: PageView(
        children: [
          Container(color: Colors.red),
          Container(color: Colors.blue),
          Container(color: Colors.yellow)
        ]
),
```
* AppBar, TabBar, Tab, TabBarView - AppBar에 TabBar를 배치하고 Tab/body에 TabBarView 배치해 탭으로 이동하는 화면 구성 가능
* BottomNavigationBar - 하단에 2~5개 탭 메뉴 구성할 수 있게 해주는 위젯
```dart
@override
  Widget build(BuildContext context) {
    return DefaultTabController(
        length: 3,
        child: Scaffold(
          appBar: AppBar(
            title: Text('Tab'),
            bottom: TabBar(
                tabs: [
                  Tab(icon: Icon(Icons.tag_faces)),
                  Tab(text: '메뉴2'),
                  Tab(icon: Icon(Icons.info), text: '메뉴3',),
                ],
            ),
          ),
          body: TabBarView(
              children: [
                Container(color: Colors.red),
                Container(color: Colors.blue),
                Container(color: Colors.yellow),
              ]
          ),
          floatingActionButton: FloatingActionButton(
              child: Icon(Icons.add),
              onPressed: () {},
          ),
          bottomNavigationBar: BottomNavigationBar(items: [
            BottomNavigationBarItem(
              icon: Icon(Icons.home),
              label: 'Profile'
            ),
            BottomNavigationBarItem(
                icon: Icon(Icons.notifications),
                label: 'Notifications'
            ),
            BottomNavigationBarItem(
                icon: Icon(Icons.person),
                label: 'Person'
            ),
    ]),
  ),
);
```
  
* **위치, 정렬, 크기**
 * Center - 중앙 정렬
```dart
body: Center(
        child: Container(
          width: 100,
          height: 100,
          color: Colors.red,
        ),
)
```
* Padding - 안쪽 여백 표현
```dart
 body: Padding(
        padding: EdgeInsets.all(40.0),
        child: Container(
          color: Colors.red,
        ),
      ),
```
* Align - 자식 위젯 정렬 방향 지정
```dart
body: Align(
        alignment: Alignment.topCenter,
        child: Container(
          color: Colors.red,
          width: 100,
          height: 100,
        ),
      ),
```
* Expanded - 자식 위젯 크기 최대로 확장
```dart
 body: Row(
        children: [
          Expanded(flex: 2, child: Container(color: Colors.red)),
          Expanded(flex: 3, child: Container(color: Colors.blue)),
          Expanded(flex: 4, child: Container(color: Colors.yellow)),
        ],
      ),
```
* SizedBox - 자식 위젯 특정 사이즈로 조정
```dart
body: SizedBox(
        width: 100,
        height: 100,
        child: Container(
          color: Colors.red,
        ),
      ),
```
* Card - 카드 형태 모양으로 제공
```dart
body: Center(
        child: Card(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(16.0),
          ),
          elevation: 40.0,
          child: SizedBox(
            width: 200,
            height: 200,
            child: Center(child: Text('내용')),
          ),
        ),
      ),
```

* **버튼 등**
* ElevatedButton - 입체감 가지는 일반적인 버튼
* TextButton - 평평한 텍스트 버튼
* IconButton - 아이콘 표시하는 버튼
* FloatingActionButton - 입체감 있는 둥근 버튼
```dart
body: Column(
        children: [
          ElevatedButton(
              onPressed: () {},
              child: Text('RaisedButton')
          ),
          TextButton(
              onPressed: () {},
              child: Text('TextButton')
          ),
          IconButton(
              onPressed: () {},
              icon: Icon(Icons.add),
              iconSize: 100.0,
          ),
          FloatingActionButton(
              onPressed: () {},
              child: Icon(Icons.add)
          ),
        ],
      ),
```
* Text - 글씨
```dart
body: Center(
        child: Text('Hello World',
          style: TextStyle(
            fontSize: 40.0,
            color: Colors.red,
            fontStyle: FontStyle.italic,
            fontWeight: FontWeight.bold,
          ),
        ),
      ),
```
* Image - 이미지
```dart
body: Image.network('http://bit.ly/2Pv4t8'),
```
* Icon - 아이콘
```dart
body: Icon(
        Icons.home,
        color: Colors.red,
        size: 600.0,
      ),
```
* Progress - 로딩 중/오래걸리는 작업 표시
```dart
body: Column(
        children: [
          CircularProgressIndicator(),
          LinearProgressIndicator()
        ],
      ),
```
* CircleAvatar - 프로필 화면 등에 사용되는 원형 위젯
```dart
body: Center(
          child: CircleAvatar(
            child: Icon(Icons.person),
        )
      ),
```

## 5. 기본 위젯 II
* **입력 관련**
  * TextField - InputDecoration으로 다양한 입력 형태 선택
```dart
import 'package:flutter/material.dart';

class TestTextfield extends StatelessWidget {
  const TestTextfield({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('TextField 테스트'),
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          TextField(),

          SizedBox(height: 40),

          TextField(
            decoration: InputDecoration(
              labelText: '여기에 입력하세요',
            ),
          ),

          SizedBox(height: 40),


          TextField(
            decoration: InputDecoration(
              border: OutlineInputBorder(),
              labelText: '여기에 입력하세요',
            ),
          ),
        ],
      ),
    );
  }
}
```
![image](https://github.com/user-attachments/assets/d5bd8cc5-4230-42bf-af66-90610f374383)

* CheckBox/Switch - 선택 체크, 체크 해제 지원
```dart
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class TestCheckbox extends StatefulWidget {
  const TestCheckbox({super.key});

  @override
  State<TestCheckbox> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<TestCheckbox> {
  bool? isChecked1 = false;
  bool isChecked2 = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text('CheckBox/Switch test'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Checkbox(
                value: isChecked1,
                onChanged: (value) {
                  setState(() {
                    isChecked1 = value;
                  });
                }
            ),
            Text('$isChecked1'),

            SizedBox(height: 80),

            Switch(
                value: isChecked2,
                onChanged: (value) {
                  setState(() {
                    isChecked2 = value;
                  });
                }
            ),
            Text('$isChecked2')
          ],
        ),
      ),
    );
  }
}
```
![image](https://github.com/user-attachments/assets/c712337c-e373-46dc-91d7-67dde8ae41f5)

* Radio/RadioListTile - 선택 그룹 중 하나 선택
```dart
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

enum FoodKind {
  /// 한식
  food1,
  /// 중식
  food2,
  /// 양식
  food3
}

class TestRadio extends StatefulWidget {
  const TestRadio({super.key});

  @override
  State<TestRadio> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<TestRadio> {
  FoodKind? foodKind = FoodKind.food1; // 한식

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text('Radio test'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('$foodKind'),

            // ListTile(
            //   title: Text('한식'),
            //   leading: Radio(
            //       value: FoodKind.food1,
            //       groupValue: foodKind,
            //       onChanged: (value) {
            //         setState(() {
            //           foodKind = value;
            //         });
            //       }
            //   ),
            // ),
            // ListTile(
            //   title: Text('중식'),
            //   leading: Radio(
            //       value: FoodKind.food2,
            //       groupValue: foodKind,
            //       onChanged: (value) {
            //         setState(() {
            //           foodKind = value;
            //         });
            //       }
            //   ),
            // ),
            // ListTile(
            //   title: Text('양식'),
            //   leading: Radio(
            //       value: FoodKind.food3,
            //       groupValue: foodKind,
            //       onChanged: (value) {
            //         setState(() {
            //           foodKind = value;
            //         });
            //       }
            //   ),
            // )

            RadioListTile(
                title: Text('한식'),
                value: FoodKind.food1,
                groupValue: foodKind,
                onChanged: (value) {
                setState(() {
                  foodKind = value;
                });
              }
              ),
            RadioListTile(
                title: Text('중식'),
                value: FoodKind.food2,
                groupValue: foodKind,
                onChanged: (value) {
                  setState(() {
                    foodKind = value;
                  });
                }
            ),
            RadioListTile(
                title: Text('양식'),
                value: FoodKind.food3,
                groupValue: foodKind,
                onChanged: (value) {
                  setState(() {
                    foodKind = value;
                  });
                }
            ),
          ],
        ),
      ),
    );
  }
}

```
![image](https://github.com/user-attachments/assets/440cc2f1-c2d4-47e9-ba6c-ff90c002e2bf)

* DropDownButton - 여러 아이템 중 하나 선택
```dart
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class TestDropdownbutton extends StatefulWidget {
  const TestDropdownbutton({super.key});

  @override
  State<TestDropdownbutton> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<TestDropdownbutton> {
  final _valueList = ['첫 번째', '두 번째', '세 번째'];
  var _selectedValue = '첫 번째';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text('DropDownButton test'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            DropdownButton(
                value: _selectedValue,
                items: _valueList.map((value) {
                  return DropdownMenuItem(
                  value: value,
                  child: Text(value),
                  );
                }).toList(),
                onChanged: (value) {
                  setState(() {
                    _selectedValue = value!;
                  });
                }
            ),
            Text('선택된 항목은 `$_selectedValue`입니다!'),
          ],
        ),
      ),
    );
  }
}
```
![image](https://github.com/user-attachments/assets/63327929-8ef6-41c3-a11d-5d4fd1356058)

* **다이얼로그 등**
* AlertDialog - 사용자 확인 요구 or 메시지 표시
```dart
import 'package:flutter/material.dart';

class TestDialog extends StatelessWidget {
  const TestDialog({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Dialog 테스트'),
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          ElevatedButton(
              onPressed: () {
                showDialog(
                    context: context,
                    barrierDismissible: false,
                    builder: (BuildContext context) {
                      return AlertDialog(
                        title: Text('제목'),
                        content: SingleChildScrollView(
                          child: ListBody(
                            children: [
                              Text('Alert Dialog 입니다.'),
                              Text('OS를 눌러 닫습니다.')
                            ],
                          ),
                        ),
                        actions: [
                          TextButton(
                              onPressed: () {
                                Navigator.of(context).pop();
                                },
                              child: Text('OK')
                          ),
                          TextButton(
                              onPressed: () {
                                Navigator.of(context).pop();
                                },
                              child: Text('Cancel')
                          )
                        ],
                      );
                    }
                );
              },
              child: Text('Alert Dialog 띄우기')
          )
        ],
      ),
    );
  }
}

```
![image](https://github.com/user-attachments/assets/27aa64eb-bcea-4059-9eb1-a5ec33880168)

* DatePicker - 날짜 선택
- 플러터에서 Future<> 이란? : <br>
![image](https://github.com/user-attachments/assets/8d6cf2df-1cb1-4149-b5ef-fa5d1efbd7d4)

* TimePicker - 시간 선택 <br>
![image](https://github.com/user-attachments/assets/c4a074cf-0d7e-4913-bb84-67774e3da080)

* **이벤트**
* GestureDetector, InkWell - 글자나 그림 같은 이벤트 속성이 없는 위젯에서 이벤트 사용
```dart
import 'package:flutter/material.dart';

class TestGesture extends StatelessWidget {
  const TestGesture({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('GestureDetector, InkWell 테스트'),
      ),
      body: Center(
        child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              GestureDetector(
                onTap: () {
                  print('GestureDetector Click!');
                },
                child: Text('클릭 Me!'),
              ),

              SizedBox(height: 80),

              InkWell(
                onTap: () {
                  print('InkWell Click!');
                },
                child: Text('클릭 Me too!'),
              )
            ],
      )
      ),
    );
  }
}

```
![image](https://github.com/user-attachments/assets/3a1e2b7e-7d9b-415a-bfe2-6942499a799e)

 
* **애니메이션**
* Hero - 페이지 전환 시 연결되는 애니메이션 지원
  - tag 값 동일하게 맞춤
```dart
// hero_page
import 'package:b_6_2/hero_detail_page.dart';
import 'package:flutter/material.dart';

class HeroPage extends StatelessWidget {
  const HeroPage({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Hero test'),
      ),
      body: Center(
        child: GestureDetector(
          onTap: () {
            Navigator.push(context,
            MaterialPageRoute(builder: (context) => HeroDetailPage()));
          },
          child: Hero(
              tag: 'image',
              child: Image.asset('assets/스크린샷(3).png',
              width: 100,
              height: 1000,
              )
          ),
        ),

      ),
    );
  }
}
```
```dart
// hero_detail_page
import 'package:flutter/material.dart';

class HeroDetailPage extends StatelessWidget {
  const HeroDetailPage({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Hero Detail')),
      body: Hero(
        tag: 'image',
        child: Image.asset('assets/png'),
      ),
    );
  }
}
```
![image](https://github.com/user-attachments/assets/109474a9-d1b9-495a-8e5d-ff7eb92dface)

* AnimatedContainer - 한 화면 내에서 변경된 프로퍼티에 의한 애니메이션
  - setState()에 의해 화면을 새로 그릴 때의 애니메이션<br>
![image](https://github.com/user-attachments/assets/378a2de7-262f-4b85-ac2c-eb1c987c7a66)

* SilverAppBar/SilverFillRemaining - 화면 헤더 동적 표현<br>
![image](https://github.com/user-attachments/assets/2132084f-3414-4a31-88f6-1df45fba97c3)

* SilverAppBar, SilverList - ListView 사용해 Silver 효과 줌<br>
![image](https://github.com/user-attachments/assets/9ebbb19b-4632-4b39-940c-086e4a1710f4)
 
* **쿠퍼티노 디자인**
* 쿠퍼티노 기본 UI - 머티리얼 디자인 대신 쿠퍼티노 디자인 적용
  - AppBar > CupertinoNavigationBar
  - Switch > CupertinoSwitch
  - ElevatedButton > CupertinoButton<br>
  ![image](https://github.com/user-attachments/assets/cafa7bc4-2122-4f90-8031-dbd85b4ccec3)
* CupertinoAlertDialog<br>
![image](https://github.com/user-attachments/assets/f0a1df8f-530d-49f5-be84-109f17b2732c)

* CupertinoPicker<br>
![image](https://github.com/user-attachments/assets/294aef87-a14a-439a-b373-46c02b0bac08)


## 6. 내비게이션
* **내비게이션 동작성**
> 실행되는 화면이 스택 구조로 메모리에 쌓임 / 다음 화면으로 전환:push, 이전 화면:pop / 스택에서 모두 제거되면 앱 종료
* **push/pop 사용법**
* push
  - 첫 번째 인수=context, 두 번째=MaterialRoute 필요
```dart
class FirstPage extends StatelessWidget {
 @override
 Widget build(BuildContext context) {
 return Scaffold(
  appBar: AppBar(
   title: Text('다음 페이지로'),
   onPressed: () {
    Navigator.push(   // 1
     context,
     MaterialPageRoute(builder: (context) => SecondPage()),  // 2
    );
   },
  ),
 );
```
- 1. Navigator.push 메소드 두 번째 인수로 사용된
- 2. MaterialPageRoute 클래스가 각 플랫폼에 맞는 화면 전환 지원

* pop
  - 현재 화면 종료, 이전 화면으로 돌아감
 ```dart
 class SecondPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
  return Scaffold(
   appBar: AppBar(
    title: Text('Second'),
    ),
    body: ElevatedButton(
     child: Text('이전 페이지로')
     onPressed: () {
      Navigator.pop(context);  // 현재 화면 종료, 이전 화면으로 돌아가기
     },
    ),
   );
  }
 }
 ```
* **routes 이용한 방법**(강의 설명 내용)
  * routes - 간결하고 체계적인 방법으로 내비게이션 구성 가능
```dart
class MyApp extends StatelessWidget {
 @override
 Widget build(BuildContext context) {
  return MaterialApp(
   title: 'Flutter Demo',
   theme: ThemeData(
    primarySwatch: Colors.blue,
   ),
   home:FistPage(),
   route: {
    '/first':(context) => FirstPage(),
    '/second':(context) => SecondPage(),
   },
  );
 }
}
```
 - route 프로퍼티에 Map 형태(키-값)로 문자열과 목적지 인스턴스 작성
 - /first > FistPage | /second > SecondPage
 * routes 화면 이동
  - push() 대신pushNamed() 사용해 내비게이션 실행 가능
```dart
onPressed: () async {
 final result = await Navigator.pushNamed(context, '/second');
 print(result);
}
```

## 7. 플러터 2 변경점
* **Null 안전한 코드**(flutter 3 기준 Null에 안전한 코드 LLM으로 숙지)
  * ? - null 가능 변수
  ```dart
  String? title = null
  ```
  * 변수 사용 전 null 검사
  ```dart
  if (title != null) {
   print(title.length);
  }
  ```
  * ! - null 아님 단언
  ```dart
  print(title!.length);  // 실제로 null이면 런타임 에러
  ```
  * ?? - null이면 대체값 사용
  ```dart
  String? title = null;
  String result = title ?? '기본값';

  // null일 때만 값 할당
  String? title;
  title ?? = '초기값';
  ```

