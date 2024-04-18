### Card 속성 조작하기 Lv4
목표  
- 학습 주제
  - 자바스크립트를 활용한 DOM 조작
  - Bootstrap에 대한 이해
- 학습 목표
  - 요구사항을 만족하는 자바스크립트 코드를 작성할 수 있다.
  - 자바스크립트를 활용해 DOM을 조작하는 방법을 이해한다.
  - CDN을 사용하여 Bootstrap을 문서에 적용할 수 있다.
- 문제의도
  - DOM 조작을 통해 웹 페이지를 동적으로 변경하는 기술을 연습하고 이해한다.
  - 주어진 요구사항을 만족시키기 위해 자바스크립트 코드를 작성하고 디버깅하는 능력을 향상시킨다.
  - Bootstrap의 동작 원리를 이해한다.
---
문제  
bootstrap을 활용해서 card를 구성하였으나, 정상적으로 보여지지 않는다.  
JavaScrip로 DOM을 조작하여 요구사항을 만족하는 코드를 완성하시오.  

요구사항
- header 요소의 class에 부여되어야 할 값이 id에 부여되었다.
  - id에 부여된 값을 class에 부여하시오.
- header 요소의 id 속성을 삭제하시오.
- img 태그의 src 값을 주어진 book.png 파일 경로가 될 수 있도록 수정하시오.
---
`removeAttribute`를 사용하여 속성을 제거합니다.  
`setAttribute`를 사용하여 속성 값을 변경합니다.
<div style="text-align: right">20240416</div>