### 할일 목록 만들기 - 스타일 부여 Lv3
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
JS를 사용하여 HTML 마크업을 직접 수정하지 않고 DOM을 조작해 보고자 한다.  
단, 이번에는 Bootstrap을 사용해 간단하게 UI를 개선하고자 한다.  
bootstrap의 CDN을 작성하고, 각 태그에 적합한 class를 JS를 사용하여 부여한다.  
또한, 특수한 속성을 수정하여 bootstrap의 동작 방식을 이해한다.  
요구사항을 만족하는 코드를 작성하시오.  

요구사항
- bootstrap 공식 문서에서 CDN을 가져와 등록한다.
- bootstrap 공식 문서를 참고하여 list-group을 할일 목록 ul 태그에 적용할 수 있도록 class를 추가한다.
- ul태그에 study class를 추가하시오.
- li:nth-child(n) 선택자를 사용하여 각각의 li 태그를 하나씩 선택 해 서로 다른 변수에 할당한다.
  - 모든 li태그에 list-group-item class를 추가한다.
- 2번째 li 태그는 아래 추가 요구사항을 따른다.
  - aria-current 속성에 true 값을 부여한다.
  - active class를 추가한다.
---
bootstrap 공식 문서를 참고합니다.  
`for...of문`을 사용하여 li를 순회하며 class를 추가합니다.  
요구사항에 알맞게 코드를 작성합니다.
<div style="text-align: right">20240416</div>