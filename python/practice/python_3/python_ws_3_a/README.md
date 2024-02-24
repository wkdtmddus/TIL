### 함수 연습하기 - 기초 Lv1
목표  
- 함수의 정의와 호출에 대해 방법을 이해한다.
- 함수의 매개변수와 반환값에 대해 이해한다.
---
문제  
파이썬의 함수를 정의하고 호출하는 방법을 연습하고자 한다. 주어진 코드를 활용하여 요구 사항을 만족하는 코드를 작성하시오.  

요구사항  
my_multi함수를 수정한다.  
a. my_multi함수의 매개변수 number_1, number_2를 곱한 값을 return한다.  
my_multi함수를 호출한 결과를 result_1변수에 할당한다.  
a. 함수를 호출하는 방법은 '함수_이름(인자1, 인자2)'와 같이 작성한다.  
result_1을 출력한다.  
is_negative함수를 수정한다.  
a. is_negative함수의 매개변수 number가 0이하면 True를, 아니면 False를 return한다.  
b. 비교 연산자를 활용하여 작성한다.(a > b 등...)  
is_negative함수를 호출한 결과를 result_2변수에 할당한다.  
result_2를 출력한다.  
default_arg_func함수를 수정한다.  
a. default_arg_func함수의 매개변수 default에 '기본 값'문자열을 할당하여, 기본 인자 값을 가지도록 수정한다.  
b. default변수를 return한다.  
result_3과 result_4변수를 각각 출력하여 차이점을 확인한다.  
```
6
False
기본 값
다른 값
```
---
함수는 `def`를 사용하여 만듭니다. `return`을 사용하여 함수가 끝날 때, 원하는 값을 반환할 수 있습니다.  
매개변수에 `(default = 'value')`를 사용하여 입력을 안 했을 때, `value`값을 기본으로 정할 수 있습니다.  
함수는 `funtion_name()`을 사용하여 호출합니다.
<div style="text-align: right">20240117</div>