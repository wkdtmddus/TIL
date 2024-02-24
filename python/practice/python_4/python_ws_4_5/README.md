### 도서관 사용자 관리 서비스 - 데이터 유효성 검사 Lv5
목표  
- 제어문을 작성하는 방법을 이해하고 명세에 맞춰 코드를 작성한다.
- 작성한 코드에서 직접 디버깅을 수행하여 발생하는 문제를 해결한다.
---
문제  
주어진 데이터에는 잘못된 값이 포함되거나 값이 누락된 경우가 있다.  
파이썬 코드를 활용하여 올바른 데이터로만 이루어진 사용자 등록 서비스를 구성하시오.  

요구사항  
create_user함수는 하나의 리스트를 인자로 넘겨받는다.  
넘겨받은 사용자 목록을 순회하며 각각 올바른 데이터로 이루어져있는지 확인하기 위해 is_validation함수를 구성하고 확인한다.  
is_validation 함수에서 확인하여야 하는 목록은 다음과 같다.  
a. blood_group의 값이 blood_types에 포함되어 있는가.  
b. company의 값이 black_list에 포함되어 있지 않은가.  
c. mail의 값에 @ 문자열이 포함되어 있는가.  
d. name의 값의 길이가 최소 2글자 이상 최대 30글자 이하인가.  
e. website가 최소 1개 이상 있는가.  
만약, 하나라도 잘못된 값이 있다면 False를 반환하고, 어떤 데이터가 잘못 기록되었는지도 함께 반환한다. 2개 이상의 데이터가 잘못 되었다면 리스트 형태로 목록을 반환한다. 모두 정상이라면 True를 반환한다.  
- 반환 예시: (False, ['blood_group', 'name'])
- 단, black_list에 company가 포함된 경우에는 'blocked' 를 반환하고, 검사를 종료한다.  

create_user는 is_validation함수의 반환 결과를 토대로 새로운 사용자 목록 user_list를 생성한다.  
이 때, 반환 받은 값이 False인 경우, 잘못된 데이터에는 None을 할당하여 데이터를 생성한다.  
또한, 반환 받은 값이 False이거나 'blocked'인 경우를 모두 세어, '잘못된 데이터로 구성된 유저의 수는 {개수} 입니다.' 를 출력한다.  
단,'blocked'가 반환된 경우, 해당 유저 정보는 user_list에 추가하지 않는다.  
완성된 user_list를 출력한다.
```
잘못된 데이터로 구성된 유저의 수는 5 입니다.
[{'blood_group': None,
  'company': 'Stone Inc',
  'mail': 'ian17@yahoo.com',
  'name': 'Kathryn Jenkins',
  'website': ['https://www.boyd-herrera.com/',
              'https://watson.com/',
              'http://www.mitchell.com/',
              'http://irwin-cline.biz/']},
 {'blood_group': 'AB+',
  'company': 'Fleming Ltd',
  'mail': 'patricianelson@yahoo.com',
  'name': 'Angel Williamson',
  'website': ['https://wilson-johnson.com/',
              'https://santiago-hammond.com/',
              'https://morales.com/',
              'https://fry-fleming.com/']},
...]
```
---
받는 데이터와 원하는 데이터의 `타입`과 `위치`를 정확하게 파악해야 합니다.  
`if문`을 여러번 사용하여 조건에 맞는 값을 얻습니다.  
함수의 `return`값에 `True`와 `False`를 사용하여 `if문`에 활용할 수 있습니다.
<div style="text-align: right">20240118</div>