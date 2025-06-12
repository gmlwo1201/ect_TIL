# AWS 실습시험
## 계정 로그인
* Account ID: shingu-cs
* IAM username: sgu-202549
* Password: @gmlwo1203
* Authenticator의 OTP 입력

## DynamoDB - 1문제/Lambda 생성 - 4문제

# DynamoDB
## 직접 테이블, 값 생성
* DynamoDB > 항목 탐색 > 항목 생성 > JSON 뷰 > 코드 작성
* 예시 코드
```json
{
  "user_id": { "S": "haeri05" },
  "product_name": { "S": "무선 이어폰" },
  "category": { "S": "디지털" },
  "reason": { "S": "최근 블루투스 기기 검색" },
  "timestamp": { "S": "2025-05-11T21:00:00" },
  "price": { "N": "89000" },
  "brand": { "S": "소니" },
  "color": { "S": "블랙" },
  "rating": { "N": "4.7" }
}
```
## 항목 편집
* 항목 탐색 > 테이블 작업 > 항목 편집 > 테이블 추가 or 삭제
* 또는 JSON뷰에서 코드 작성
```
INSERT INTO "항목 이름" VALUE {
  // 삽입할 테이블 값
  ...
};
```

## Lambda에서 삽입
* Lambda 코드
```python
import boto3
def lambda_handler(event, context):
  dynamodb = boto3.resource('dynamodb')
  table = dynamodb.Table('sgu-202500-user-likes')

  # 덮어쓰기할 항목
  response = table.put_item(
    Item={
      'user_id': 'jihoon07', 
      'product_name': '제로콜라' 
    }
  )

  return {
    'statusCode': 200,
    'body': 'PutItem successful!'
  }
```


## 정렬키 추가한 테이블 생성
* 정렬키: timestamp
* 코드
```python
{
  {
    "user_id": {
    "S": "haeri05"
  },
  // 아래의 시간을 기준으로 정렬
  "timestamp": {
    "S": "2025-05-11T21:00:00"
  },
  "product_name": {
    "S": "무선 이어폰"
  },
  "category": {
    "S": "디지털"
  },
  "reason": {
    "S": "최근 블루투스 기기 검색"
  },
  "price": {
    "N": "89000"
  },
  "brand": {
    "S": "소니"
  },
  "color": {
    "S": "블랙"
  },
  "rating": {
    "N": "4.7"
  }
}
```
* 항목 추가할땐 위의 테이블 값에 맞춰 쿼리 생성

## 항목 정렬
* 시간 순 정렬
* 항목 탐색 > 쿼리 > 쿼리 값 입력
```
SELECT * FROM "항목 이름"
WHERE user_id = '정렬할 테이블 값'
ORDER BY "timestamp" ASC;
```

## REST API
* Lambda 코드
```python
import boto3
import json
from datetime import datetime

def lambda_handler(event, context):
  try:
    # 요청 본문 파싱
    body = json.loads(event['body'])

    user_id = body['user_id']
    product = body['product']
    timestamp = datetime.now().isoformat()

    # 선택적 필드들 (있으면 저장, 없으면 None)
    reason = body.get('reason')
    brand = body.get('brand')
    price = body.get('price')
    color = body.get('color')

    # 저장할 항목 구성
    item = {
      'user_id': user_id,
      'timestamp': timestamp,
      'product': product
    }

    # 선택 필드가 있는 경우만 추가
    if reason: item['reason'] = reason
    if brand: item['brand'] = brand
    if price: item['price'] = price
    if color: item['color'] = color

    # DynamoDB 저장
    table = 
      boto3.resource('dynamodb').Table('sgu-202500-user-likes-time')
      table.put_item(Item=item)
      return {
        'statusCode': 200,
        'body': json.dumps({
          'message': '저장 완료',
          'user_id': user_id,
          'timestamp': timestamp
        }, ensure_ascii=False)
      }
      except Exception as e:
        return {
          'statusCode': 400,
          'body': json.dumps({'error':
            str(e)}, ensure_ascii=False)
        }
```

## API Gateway 연결
* Lambda에서 API Gateway 연결
* Lambda 함수 > 트리거 추가 > 트리거 구성: API 게이트웨이/API 유형: HTTP API/보안: 열기
* 포스트맨에서 POST에 API 엔드포인트 넣고 Body에 쿼리 추가

# Lambda
## DynammoDB와 연동하는 부분 나올수도

## 기본 생성
```python
def lambda_handler(event, context):
  return {
    "statusCode": 200,
    "statusDescription": "200 OK",
    "isBase64Encoded": False,
    "headers": {
      "Content-Type": "application/json"
    },
    "body": '{"message": "Hello Lambda "}'
}
```
## Lambda Target 생성
* 로드밸런싱 > 대상 그룹 > 대상 그룹 생성
* 그룹 세부 정보 지정
* Lambda 함수 체크
* 대상 그룹 이름

## Lambda 규칙 추가
* 해당 ALB > 리스너 규칙 추가
* 대상 등록 > 생성한 람다 함수 선택 > 이름 지정
* 조천 추가 > 경로 /lambda
* 우선 순위 1로 지정
* 작업: 대상 그룹으로 전달 > 대상 그룹 추가
* 생성 > ALB DNS 이름 도메인 검색

## ALB + S3 + Lambda
* 코드
```python
import json # JSON 형식으로 응답을 구성하기 위한 모듈
import boto3 # AWS 서비스(S3 등)를 사용하기 위한 SDK
from datetime import datetime # 현재 시간 정보를 얻기 위한 모듈

def lambda_handler(event, context):
  # S3 클라이언트 생성 (S3에 접근하기 위한 boto3 객체)
  s3 = boto3.client('s3')

  # 업로드할 대상 버킷 이름
  bucket_name = 'sgu-202500-3b'

  # 파일을 저장할 경로
  #(S3에서는 디렉터리 개념이 아니라 key prefix)
  prefix = 'uploaded/'

  # 현재 시간을 문자열로 포맷 (파일 이름에 포함시킬 용도)
  now = datetime.now().strftime('%Y-%m-%d_%H-%M-%S')

  # 파일 이름 생성: 예) uploaded/hello_2025-05-07_14-00-00.txt
  filename = f'{prefix}hello_{now}.txt'

  # 파일 내용 구성
  content = f'Hello Haeri! This file was created at {now}'

  # S3에 파일 업로드
  # Bucket: 업로드할 버킷 이름
  # Key: 파일 경로 및 이름
  # Body: 파일의 실제 내용
  #(문자열을 UTF-8로 인코딩)
  s3.put_object(
    Bucket=bucket_name,
    Key=filename,
    Body=content.encode('utf-8')
  )

  # ALB가 Lambda로부터 기대하는 응답 형식
  return {
    "statusCode": 200, 
    "statusDescription": "200 OK", 
    "isBase64Encoded": False, # 바디 인코딩 여부 (파일이 아닌 경우 False)
    "headers": {
      "Content-Type": "application/json" 
    },
    "body": '{"message": "업로드 완료"}' 
  }
```

## 람다 레이어
* 람다 > 추가 리소스 > 계층 > 계층 생성
* 이름, 설명, 파이썬에서 다운로드 받은 .zip 파일 업로드
* 호환 런타임: python3.13 > 생성
* 람다 함수 > Layers > Add a layer
* 사용자 지정 계층 > 생성한 레이어 > 버전 1 > 추가
* 라이브러리 추가하고 싶은 경우
  1. cmd 관리자
  2. cd aws_3b
  3. py -3.13 -m pip install requests -t python/
  4. 버전 생성 > 사용자 지정 계층 > 위에서 다운받은 파일로 레이어 생성 후 적용

## request-get, post
```python
# get
import requests

def lambda_handler(event, context):
  url = "https://httpbin.org/get"
  response = requests.get(url)
  data = response.json()

  print("API 응답:", data)

  return {
    'statusCode': 200,
    'body': str(data)
  }
```
```python
# post
import requests
import json

def lambda_handler(event, context):
  url = "https://httpbin.org/post"
  payload = {"name": "test"}
  response = requests.post(url, json=payload)
  data = response.json()

  print("API 응답:", data)

  return {
    'statusCode': 200,
    'body': str(data)
  }
```
