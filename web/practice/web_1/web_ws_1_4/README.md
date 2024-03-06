### CSS 상속 실습 예제 Lv4
목표  
- CSS 상속 심화에 대해 이해한다.
---
문제  
CSS 프로퍼티는 상속이 `되는 것`과 `되지 않는 것`이 있다. https://poiemaweb.com/css3-inheritance-cascading을 참고하여 상속되는 프로퍼티는 상속되지 않게, 상속되지 않는 프로퍼티는 상속 되도록 하시오.

요구사항  
- 오직 style태그의 내용만 수정한다.
- inherit, border, text-align, color를 활용한다.
- .outer-box의 width, height는 자식에게 상속되지 않는다.
- .inner-box의 border는 .outer-box의 속성을 상속받는다.
- 첫 번째 span의 색상과 정렬은 .outer-box의 속성을 상속받아 빨간색, 중앙 정렬이 되어야 한다.
- 두 번째 span은 색상과 정렬 두 속성 모두 .outer-box로부터 상속을 받지 않는다.
- 실행 결과
```
|-------------------|
|      첫 번째       |
|---------|         |
|두 번째   |         |
|         |         |
|---------|         |
|                   |
|-------------------|
```
---
주어진 요구사항에 맞게 만드는 연습을 먼저 해보자.
<div style="text-align: right">20240306</div>