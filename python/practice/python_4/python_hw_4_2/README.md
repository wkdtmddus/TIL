### 대여 불가 도서 구분하기 Lv2
목표  
- 제어문을 작성하는 방법을 이해하고 명세에 맞춰 코드를 작성한다.
- 작성한 코드에서 직접 디버깅을 수행하여 발생하는 문제를 해결한다.
---
문제  
도서 대여 서비스 자동화 시스템을 구축하려고 한다.  
대여 예정 도서 목록 중, 현재 보유하고 있지 않은 도서가 있다면 특정 문자열을 출력하도록 코드를 작성하시오.  

요구사항  
보유 중인 도서 리스트 list_of_book과 대여 예정 도서 리스트 rental_list가 주어진다.  
반복문을 사용하여 rental_list 요소 중, 보유 중인 도서에 포함되지 않은 요소를 발견하면 '{도서명} 은/는 보유하고 있지 않습니다.' 문구를 출력한다.  
보유하고 있지 않은 문서가 있다면, 위 문구를 출력한 후, 반복문을 종료한다.  
만약 모든 도서를 보유하고 있다면 '모든 도서가 대여 가능한 상태입니다.'를 출력한다.
```
모든 도서가 대여 가능한 상태입니다.
난중일기 은/는 보유하고 있지 않습니다.
```
---
`in`을 사용하여 리스트안에 해당 요소가 포함되어 있는지 확인할 수 있습니다. `True`또는 `False`를 반환합니다.
<div style="text-align: right">20240118</div>