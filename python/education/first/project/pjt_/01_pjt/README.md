# problem1
### 데이터 추출 - key 값 출력하기
`requests.get`을 이용하여 `get` 내부에 `URL, params`을 대입하여 요구하는 url 주소를 보기 쉽게 작성할 수 있었습니다.

```python
URL = f'http://finlife.fss.or.kr/finlifeapi/companySearch.json'

params = {
  'auth': API_KEY,
  'topFinGrpNo': '020000',
  'pageNo': 1
}

response = requests.get(URL, params=params).json()
```
---
### `for item in response['result']`와 `response['result'].keys()`의 차이

`for item in response['result']`는 key가 dict에 없는 경우 KeyError 발생
`response['result'].keys()`는 key가 dict에 없는 경우 None을 반환

---

참고  
`json`: 데이터를 표현하는 방법 중 하나(통신 방법이나 프로그래밍 문법이 아님)  
`url'?'`: 기본url + ? + key=value&key=value...

---
# problem2
### 데이터 추출 - 전체 정기예금 상품 리스트
```python
result = response['result']['baseList']
```
response 내의 데이터를 출력하기 위해서 `key`값이 `result`인 데이터에서 다시 `key`값이 `baseList`인 데이터를 찾아 result에 할당합니다.

---
# problem3
### 데이터 가공 - 전체 정기예금 상품들의 옵션 정보 리스트

---
# problem4
### 데이터 가공 - 상품과 옵션 정보들을 담고 있는 새로운 값을 만들어 반환하기
