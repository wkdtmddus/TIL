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
풀이.1
```python
for item in response['result']:
    pprint.pprint(item)
```
`for문`을 사용하여 result의 요소를 출력합니다.  

풀이.2
```python
result = response['result'].keys()
```
dictionary의 `keys()`함수를 사용하여 result 요소의 `key`값을 할당합니다.

---
### `for item in response['result']`와 `response['result'].keys()`의 차이

`for item in response['result']`는 key가 dict에 없는 경우 KeyError 발생
`response['result'].keys()`는 key가 dict에 없는 경우 None을 반환

---

참고  
`json`: 데이터를 표현하는 방법 중 하나(통신 방법이나 프로그래밍 문법이 아님)  
`url'?'`: 기본url + ? + key=value&key=value...  
`pprint`: pretty print. 인간이 보기 좋게 데이터를 출력합니다.

---
dictionary에서 key만 얻고 싶을 때, `.keys()`함수를 사용하여 편리하게 얻을 수 있습니다.
<div style="text-align: right">20240119</div>

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
```python
for option in optionList:
    new_option = {
        '금융상품코드': option['fin_prdt_cd'],
        '저축 금리': option['intr_rate'],
        '저축 기간': option['save_trm'],
        '저축금리유형': option['intr_rate_type'],
        '저축금리유형명': option['intr_rate_type_nm'],
        '최고 우대금리': option['intr_rate2']          
    }
    resultList.append(new_option)
```
원하는 요소가 있는 dictionary를 직접 작성하여 얻을 수 있습니다. `append`를 사용하여 수집한 dict를 넣어줍니다.

---
위 처럼 데이터의 양이 많을 때는 목록을 찾기가 어렵습니다. `pprint`를 사용하여 보기 좋게 출력하여 원하는 데이터를 잘 찾도록 합니다.  
<div style="text-align: right">20240119</div>

---
# problem4
### 데이터 가공 - 상품과 옵션 정보들을 담고 있는 새로운 값을 만들어 반환하기
`이중for문`을 사용하여 두 리스트를 비교하였습니다. for문 내에서 `dict`도 잘 찾아서 얻도록 해야합니다. 원하는 요소들을 `새로운 리스트`에 `append`하여 반환하도록 코드를 작성하였습니다.