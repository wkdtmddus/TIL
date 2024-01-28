### 도서 대여 서비스 Lv3
목표  
- 함수의 정의와 호출에 대해 방법을 이해한다.
- 함수의 매개변수와 반환값에 대해 이해한다.
- 내장 함수를 사용하는 방법을 이해하고 실습한다.
---
문제  
대여점이 보유중인 총 도서 수를 관리하는 코드를 작성하고자 한다.  
단, 고객과 도서는 서로 다른 영역에서 관리하고자 함수를 분리해 두었다.  
요구 사항을 참고하여 적절한 코드를 작성하여 기능을 완성하시오.    

요구사항  
decrease_book함수는 한 번에 대여하는 책의 수를 정수로 넘겨 받는다.  
- 넘겨받은 값만큼 number_of_book의 수를 감소시킨다.
- 현재 남은 책의 수를 출력한다.

retal_book함수는 대여자의 이름과, 대여하는 책의 수를 인자로 넘겨 받는다.  
- rental_book함수가 실행될 때, decrease_book함수를 호출한다.
- 이후, '{name}님이 {number}권의 책을 대여하였습니다.' 문구를 출력한다.
```
남은 책의 수 : 97
홍길동님이 3권의 책을 대여하였습니다.
```
---
함수에서 함수를 호출할 수 있습니다. 알맞게 지정하여 함수 실행 순서를 조절해야 합니다.
<div style="text-align: right">20240117</div>