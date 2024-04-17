### Card 만들기 Lv5
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
JavaScript를 활용하여 기존에 제시 되어 있는 article.card와 동일한 구조의 요소를 생성하여 추가하시오.  
본문 내용은 자유롭게 구성한다.  
새로 생성한 article을 추가한 뒤, 기존 article은 삭제한다.  

요구사항
- section 요소와 article 요소를 선택한다.
  - article의 경우, 변수에 할당 시 추가로 생성할 article 요소와 구분 지을 수 있는 변수명으로 작성한다.
- 새 article 요소와 그 하위 요소를 생성한다.
  - 하위 요소 구성은 기존의 article과 동일하게 작성한다.
- 새 article 요소와 하위 요소에 클래스 등 속성과 값을 추가한다.
  - div 요소에 h5와 p 요소를 추가한다.
  - div를 새로 작성한 article에 추가한다.
- section에 새로 작성한 article을 추가한다.
- 기존의 article 요소는 삭제한다.
---
요구사항을 천천히 따라갑니다.  
`document.createElement`를 이용하여 요소를 생성합니다.  
`appendChild`를 이용하여 부모 자식 관계를 형성합니다.  
`removeChild`를 이용하여 자식 요소를 삭제합니다.
<div style="text-align: right">20240416</div>