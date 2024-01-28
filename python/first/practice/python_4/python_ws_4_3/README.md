### 도서관 사용자 관리 서비스 - 데이터 처리 Lv3
목표  
- 제어문을 작성하는 방법을 이해하고 명세에 맞춰 코드를 작성한다.
- 작성한 코드에서 직접 디버깅을 수행하여 발생하는 문제를 해결한다.
---
문제  
도서관 사용자 관리 서비스를 구축하기 위한 임시 데이터를 수집하는 코드를 작성하고자 한다.  
ws_4_2에서 작성한 코드를 수정하여 다수의 사용자 정보를 정리한 리스트를 구성하시오.  

요구사항  
반복문을 사용하여 1부터 10까지 총 10명의 데이터를 요청한다.  
- 응답 받은 결과에서 사용자의 name을 dummy_data리스트에 추가한다.
- 이 때, 리스트에 추가는 dummy_data.append(name) 형식으로 진행한다.
- 단, lat(위도)과 lng(경도)는 각각 -80초과, 80미만인 경우만 삽입한다.  

dummy_data를 출력한다.
```
[{'company': 'Deckow-Crist',
  'lat': -43.9509,
  'lng': -34.4618,
  'name': 'Ervin Howell'},
 {'company': 'Romaguera-Jacobson',
  'lat': -68.6102,
  'lng': -47.0653,
  'name': 'Clementine Bauch'},
 {'company': 'Keebler LLC',
  'lat': -31.8129,
  'lng': 62.5342,
  'name': 'Chelsey Dietrich'},
 {'company': 'Considine-Lockman',
  'lat': -71.4197,
  'lng': 71.7478,
  'name': 'Mrs. Dennis Schulist'},
 {'company': 'Johns Group',
  'lat': 24.8918,
  'lng': 21.8984,
  'name': 'Kurtis Weissnat'},
 {'company': 'Hoeger LLC',
  'lat': -38.2386,
  'lng': 57.2232,
  'name': 'Clementina DuBuque'}]
```
---
받아온 데이터에 `key`값을 입력하여 원하는 데이터를 얻습니다.  
새로운 딕셔너리에 원하는 형식으로 담고 `append()`를 사용하여 `list`에 데이터를 넣어줍니다.  
`비교 연산자`는 해당 변수에 겹치게 사용할 수 있습니다.  
(-80 < lat < 80)  
`and`를 사용하여 조건을 추가합니다.
<div style="text-align: right">20240118</div>