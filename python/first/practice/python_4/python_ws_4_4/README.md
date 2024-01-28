### 도서관 사용자 관리 서비스 - 블랙리스트 관리 Lv4
목표  
- 제어문을 작성하는 방법을 이해하고 명세에 맞춰 코드를 작성한다.
- 작성한 코드에서 직접 디버깅을 수행하여 발생하는 문제를 해결한다.
---
문제  
ws_4_3에서 작성한 코드로 사용자들을 등록하는 함수를 작성하고자 한다.  
단, 특정 회사에 재직중인 사용자는 제외하고 등록하여야 한다.  
제시된 black_list에 포함된 company 소속이 아닌 사용자만 들록할 수 있도록 코드를 작성하시오.  

요구사항  
create_user 함수에 사용자 목록을 인자로 넘겨 순회하는 코드르 작성하시오.  
create_user 함수는 넘겨받은 리스트를 순회하여, company 이름을 key로, 사용자 이름을 value로 갖는 딕셔너리 censored_user_list를 생성한다.  
- 이 때, value는 리스트로 구성한다.  

create_user함수를 통해 사용자 목록을 순회하면서, 각 사용자 정보를 censorship함수에 인자로 넘겨, black_list에 포함 되어 있는지 확인한다.  
censorship함수는 넘겨받은 회사 명이 black_list에 포함되어 있으면 '{회사명} 소속의 {사용자명} 은/는 등록할 수 없습니다.' 문구를 출력하고, False를 반환한다. 포함되어 있지 않다면 '이상 없습니다.' 문구를 출력하고 True를 반환한다.  
censorship함수의 반환 값을 기준으로 사용자 정보를 딕셔너리 censored_user_list에 담을 것인지 판단한다.  
create_user는 작성 완료된 딕셔너리 censored_user_list를 반환한다.  
반환 받은 censored_user_list를 출력한다.
```
이상 없습니다.
이상 없습니다.
Keebler LLC 소속의 Chelsey Dietrich 은/는 등록할 수 없습니다.
이상 없습니다.
Johns Group 소속의 Kurtis Weissnat 은/는 등록할 수 없습니다.
Hoeger LLC 소속의 Clementina DuBuque 은/는 등록할 수 없습니다.
{'Considine-Lockman': ['Mrs. Dennis Schulist'],
 'Deckow-Crist': ['Ervin Howell'],
 'Romaguera-Jacobson': ['Clementine Bauch']}
```
---
받는 데이터와 원하는 데이터의 `타입`과 `위치`를 정확하게 파악해야 합니다.  
`for문`을 사용하여 블랙리스트에 속하는지 확인합니다.
<div style="text-align: right">20240118</div>