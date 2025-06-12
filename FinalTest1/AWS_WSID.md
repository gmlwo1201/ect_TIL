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
```json
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
