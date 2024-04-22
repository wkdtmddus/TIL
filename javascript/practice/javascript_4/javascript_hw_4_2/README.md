### Click Event - ThemeSwitcher Lv2
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
상용중인 웹 서비스에 새롭게 반영할 테마 변경 시스템을 구상중에 있다.  
`각 버튼을 클릭하면, 페이지 전체의 배경 색상, 글자 색상등이 테마에 맞게 변경되어야 한다.`  
JS를 사용하여 요구 사항을 만족하는 코드를 작성하시오.  

요구사항
- 각 li 태그가 클릭 되면 body의 background color 와 color 속성이 변경되어야 한다.
  - css와 HTML 마크업을 직접 수정하지 않는다.
- li#grayButton 클릭시 background-color는 gray, color는 white로 변경
- li#whiteButton 클릭시 background-color는 white, color는 black으로 변경
- li#navyButton 클릭시 background-color는 navy, color는 white로 변경
---
요구사항을 천천히 따라갑니다.  
필요한 태그들을 `querySelector`를 이용하여 찾습니다.  
`addEventListener`를 이용하여 해당 이벤트가 발생할 때, 적절한 css를 적용시킵니다.
<div style="text-align: right">20240422</div>