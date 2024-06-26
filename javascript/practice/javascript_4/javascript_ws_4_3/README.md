### submit Event - Card Maker Lv3
목표  
- 학습 주제
  - 자바스크립트 이벤트
- 학습 목표
  - 브라우저에서 이벤트가 어떻게 작동하는지 이해할 수 있다.
  - 주어진 요구사항에 따라 이벤트 핸들러를 작성하고 활용할 수 있다.
- 문제의도
  - 자바스크립트로 이벤트를 다루는 기본적인 이해와 활용 능력을 평가한다.
  - 이벤트 핸들러를 등록하며 DOM 조작하는 방법에 대한 능력을 검증한다.
---
문제  
사용자가 입력한 내용을 토대로 bootstrap 카드를 만들어주는 JS 코드를 작성하고자 한다.  
예시로 참고하기 위해 미리 bootstrap 카드 구성을 받아온 상태이다.  
그러나 예전에 만들어 둔 상태이므로,  
필요하다면 bootstrap CDN과 card component는 최신 버전으로 수정하여 진행하여도 된다.  
`중요한 것은, 사용자가 input tag에 값을 입력하고 submit 이벤트가 발생하면,`  
`그 내용을 토대로 새로운 카드들이 생성되어야 한다는 것이다.`  
모든 기능 구현이 완료되면 예시로 가져온 card 마크업은 직접 삭제한다.  
요구 사항을 참고하여 적절한 기능을 구현하시오.  

요구사항
- submit 이벤트가 발생하면 createCard 함수를 호출한다.
  - createCard 함수
    - input 태그의 value들을 인자로 받는다.
    - 카드 구성에 필요한 각 요소를 createElement 메서드로 생성한다.
    - 각 요소에 적절한 class를 추가한다.
    - textContent는 사용자가 입력한 input 값으로 구성해야 한다.
    - 설정이 완료된 요소들을 부모 - 자식 관계에 맞게 구성한다.
    - 구성이 완료된 article을 반환한다.
  - 반환 받은 article을 #cardsSection에 추가한다.
  - input 태그의 value를 빈 문자열로 초기화 한다.
  - submit의 기본 이벤트를 중지한다.
- 기능을 모두 구현하면 카드 예시 마크업은 주석처리 하거나 삭제한다.
---
이벤트가 발생했을 때, 페이지를 새로 받아오지 않게 `event.preventDefault()`를 사용합니다.  
이벤트가 발생하면 카드를 만들어야 합니다.  
예로 주어진 카드구조를 바탕으로 만들어줍니다.
<div style="text-align: right">20240422</div>