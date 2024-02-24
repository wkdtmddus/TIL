### 딕셔너리 메서드2 Lv3
목표  
- 파이썬의 데이터 구조(세트, 딕셔너리)에 대해 이해하고 활용할 수 있다.
- 파이썬의 반복문과 조건문 작성 문법에 대해 이해한다.
---
문제  
파이썬의 딕셔너리 데이터 타입이 가진 메서드를 연습하고자 한다. 주어진 코드를 활용하여 요구 사항을 만족하는 코드를 작성하시오.  

요구사항  
다수의 dict를 가진 리스트 data와 목록이 담긴 key_list가 주어진다.  
data를 순회하여 얻은 dict를 key_list를 순회하여 얻은 값에 따라 아래 조건을 만족하는 코드를 작성하시오.  
a. 만약 순회중인 dict에 key_list로 얻은 key가 없다면,
1. 해당 key에 'unknown'문자열을 할당한다.
2. get메서드와 setdefault메서드를 활용한다.  

b. 모든 상황에 대해 '{key} 은/는 {value}입니다.'를 출력한다.
```
name 은/는 galxy flip입니다.
company 은/는 samsung입니다.
is_collapsible 은/는 True입니다.

name 은/는 ipad입니다.
company 은/는 unknown입니다.
is_collapsible 은/는 False입니다.

name 은/는 galxy fold입니다.
company 은/는 samsung입니다.
is_collapsible 은/는 True입니다.

name 은/는 galxy note입니다.
company 은/는 samsung입니다.
is_collapsible 은/는 False입니다.

name 은/는 optimus입니다.
company 은/는 unknown입니다.
is_collapsible 은/는 False입니다.
```
---
`이중for문`을 사용하여 `data`와 `key_list`를 순회하여 data를 수정합니다. `setdefault`를 사용하여 key가 없을 때, 해당 키와 뒤에 입력한 string을 추가합니다.
<div style="text-align: right">20240123</div>