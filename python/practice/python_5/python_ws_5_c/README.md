### 문자열과 리스트 메서드3 Lv3
목표  
- 파이썬의 데이터 구조(문자열, 리스트, 튜플)에 대해 이해하고 활용할 수 있다.
- 파이썬의 반복문과 조건문 작성 문법에 대해 이해한다.
---
문제  
문자열과 리스트가 가진 메서드를 연습하고자 한다. 주어진 코드를 활용하여 요구 사항을 만족하는 코드를 작성하시오.

요구사항  
잘못된 문장이 작성된 문자열 original_word, 제거할 대상이 작성된 word문자열과 빈 리스트 arr이 주어진다.  
original_word변수에 담긴 각 문자열을 모두 나누어 arr리스트에 담는다.  
a. extend메서드를 활용한다.  
arr 리스트를 출력한다.  
문장에서 잘못된 내용을 제거하는 함수 restructure_word함수를 작성한다.  
a. 인자로 넘겨받은 word문자열을 순회하며 아래 조건에 맞춰 arr에서 불필요한 문자열을 제거한다.  
1. 만약 순회중인 문자열이 숫자라면, 해당 숫자 만큼 반복하여 arr의 마지막 요소를 제거한다.  
    1. isdecimal 메서드와 pop 메서드를 활용한다.  
2. 그 외의 경우, arr에서 해당 문자열을 제거한다.  
    1. remove 메서드를 활용한다.  

b. 불필요한 문자를 제거한 arr를 반환한다.   
함수 호출 결과를 result 변수에 담고 result를 출력한다.  
result에 할당된 리스트를 하나의 문자열로 변환하여 출력한다.  
a. join 메서드를 활용한다.
```
['코', '딩', ' ', '공', '부', '는', 'ㄴ', ' ', '1', '일', 'ㄹ', ' ', '1', '커', 'ㅓ', '밋', '
ㅅ', ' ', '@', '@', '@', '#', '^', '(', ')', '#', '_', '+', '!', '&', '~', ':', '"']
['코', '딩', ' ', '공', '부', '는', ' ', '1', '일', ' ', '1', '커', '밋', ' ']
코딩 공부는 1일 1커밋
```
---
`extend()`는 해당 iterable의 모든 항목을 넣습니다.  
`isdecimal()`은 문자열 내의 모든 문자가 `십진수` 문자이고, 적어도 하나의 문자가 존재하는 경우를 확인합니다.  
`isdigit()`은 문자열 내의 모든 문자가 `digit`이고, 적어도 하나의 문자가 존재하는 경우를 확인합니다.  
`isnumeric()`은 문자열 내의 모든 문자가 숫자이고, 적어도 하나의 문자가 존재하는 경우를 확인합니다.  
`isdecimal() < isdigit() < isnumeric()` 순으로 `True`를 반환하는 범위가 큽니다.
<div style="text-align: right">20240122</div>