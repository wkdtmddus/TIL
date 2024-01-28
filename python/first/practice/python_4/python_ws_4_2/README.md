### 도서관 사용자 관리 서비스 - 데이터 수집 Lv2
목표  
- 제어문을 작성하는 방법을 이해하고 명세에 맞춰 코드를 작성한다.
- 작성한 코드에서 직접 디버깅을 수행하여 발생하는 문제를 해결한다.
---
문제  
도서관 사용자 관리 서비스를 구축하기 위한 임시 데이터를 수집하는 코드를 작성하고자 한다.  
ws_4_1에서 작성한 코드를 수정하여 다수의 사용자 정보를 모은 리스트를 구성하시오.  

요구사항  
반복문을 사용하여 1부터 10까지 총 10명의 데이터를 요청한다.  
- 응답 받은 결과에서 사용자의 name을 dummy_data리스트에 추가한다.
- 이 때, 리스트에 추가는 dummy_data.append(name) 형식으로 진행한다.
```
['Leanne Graham',
'Ervin Howell',
'Clementine Bauch',
Patricia Lebsack',
Chelsey Dietrich',
'Mrs. Dennis Schulist',
'Kurtis Weissnat',
'Nicholas Runolfsdottir V',
Glenna Reichert',
'Clementina DuBuque']
```
---
`for문`을 사용하여 `range`만큼 반복합니다.
<div style="text-align: right">20240118</div>