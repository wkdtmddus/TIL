# problem1

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
### `for item in response['result']`과 `response['result'].keys()`의 차이

`for item in response['result']`는 key가 dict에 없는 경우 KeyError 발생
`response['result'].keys()`는 key가 dict에 없는 경우 None을 반환

---
# problem2

