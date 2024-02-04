### 자동차 클래스 정의하기 Lv3
목표  
- 파이썬 클래스의 기본적인 개념(객체, 인스턴스)에 대해 이해한다.
- 파이썬 클래스의 변수(클래스, 인스턴스)에 대해 이해한다.
- 파이썬 클래스의 (인스턴스, 클래스, 생성자) 메서드에 대해 이해한다.
---
문제  
파이썬의 class를 연습하고자 한다. '자동차'의 특성을 가진 class를 요구 사항에 맞춰 정의하시오.  

요구사항  
car 클래스를 정의한다.  
모든 자동차들이 공통으로 가질 클래스 변수 wheels를 정의하고 4를 할당한다.  
- 생성자 메서드를 정의한다.
- 생성자 메서드는 엔진의 종류를 인자로 받는다.
- 각 인스턴스는 고유한 엔진의 종류를 담을 수 있는 engine변수를 가지고, 인자로 넘겨받은 값을 할당받는다.
(ex: gasoline, diesel, hybrid, electric)
- 각 인스턴스는 구동 방식을 담을 수 있는 driving_system변수를 가지고, 인자로 넘겨받은 값을 할당받는다.  
(ex: 전륜구동, 후륜구동, 4wd)
- 각 인스턴스는 엔진 소리를 담을 수 있는 sound변수를 가지고, 인자로 넘겨받은 값을 할당받는다.  

인스턴스 메서드를 정의한다.  
a. drive 인스턴스 메서드는 호출 시, 인스턴스가 가진 고유 sound를 출력한다.  
1. 인스턴스가 가진 engine을 반환한다.  

b. introduce메서드는 호출 시, 인스턴스의 엔진 종류, 구동 방식을 소개하는 문자열을 출력한다.  
모든 자동차의 바퀴 스를 1증가시키는 increase_wheels 클래스 메서드를 정의한다.  
a. 호출될 때마다 클래스 변수 wheels가 1증가한다.  
b. '법이 개정되어 모든 자동차의 필요 바퀴 수가 1증가하였습니다.'문자열을 출력한다.  
'자동차'에 대한 설명을 출력하는 description 스태틱 메서드를 정의한다.  
- 출력 문구: 자동차(自動車, 영어: car, automobile)는 엔진에서 만든 동력을 바퀴에 전달하여 지상에서 승객이나 화물을 운반하는 교통 수단이다.  
- 출처: 위키피디아  

코드를 실행하고, 출력 결과를 확인한다.
```
부릉부릉
달달달달
diesel
===
제 차의 엔진은 gasoline 방식이고, 후륜구동 (으)로 동작합니다.
제 차의 엔진은 hybrid 방식이고, 4wd (으)로 동작합니다.
===
이 세상의 자동차는 4개의 바퀴를 가집니다.
법이 개정되어 모든 자동차의 필요 바퀴수가 1증가하였습니다.
이 세상의 자동차는 5개의 바퀴를 가집니다.
자동차(自動車, 영어: car, automobile)는 엔진에서 만든 동력을 바퀴에 전달하여 지상에서 승객이나
 화물을 운반하는 교통 수단이다.
```
---
`__init__`을 사용하여 인스턴스를 생성할 때, 인자를 받습니다.  
함수를 작성하여 요구하는 반환값을 입력합니다.  
`@classmethod`와 `@staticmethod`를 사용하여 요구하는 메서드를 작성합니다.  
출력 순서에 주의합니다.
<div style="text-align: right">20240124</div>