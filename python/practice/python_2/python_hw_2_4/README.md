### 중복 작가 제거하기 Lv4
목표  
- 파이썬 기본 자료형(문자형, 수치형, 불린형)에 대해 이해한다.
- 파이썬 복합 자료형(리스트,딕셔너리, 튜플)에 대해 이해한다.
- 파이썬의 다양한 연산자에 대해 이해한다.
---
문제  
작가 목록 리스트를 작성할 때, 작가 이름이 중복되지 않도록 작성하는 규칙이 있었다.  
그러나 일부 악성 팬들이 작가 목록 리스트를 자신이 좋아하는 작가 이름으로 가득 채워 넣었다.  
다행히 목록은 파이썬 코드로 관리되고 있으므로 간단하게 중복된 목록을 제거하고 다시 작가 목록 리스트로 돌려놓을 수 있을 것 같다.  
형변환을 사용하여 작가 목록 리스트에서 중복된 값을 제거하고, 잘 제거되었는 지 확인하기 위하여 작가 목록을 출력하시오.  
단, 순서는 신경쓰지 않는다.  

요구사항  
없음
```
['작자 미상', '조성기', '이항복', '허균', '박지원', '임제', '남영로']
```
---
`set`은 중복이 없는 비연속적인 자료구조입니다.  
`set()`을 사용하여 authors에 있는 중복된 요소들을 제거합니다.
<div style="text-align: right">20240116</div>