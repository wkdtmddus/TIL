### 포트폴리오 만들기 - DOM 조작하여 스타일 적용하기 Lv4
목표  
- 학습 주제
  - 자바스크립트를 활용한 DOM 조작
- 학습 목표
  - 요구사항을 만족하는 자바스크립트 코드를 작성할 수 있다.
  - 자바스크립트를 활용해 DOM을 조작하는 방법을 이해한다.
- 문제의도
  - DOM 조작을 통해 웹 페이지를 동적으로 변경하는 기술을 연습하고 이해한다.
  - 주어진 요구사항을 만족시키기 위해 자바스크립트 코드를 작성하고 디버깅하는 능력을 향상시킨다.
---
문제  
JS를 활용하여 DOM을 조작하는 방법을 연습하려고 한다.  
style.css의 내용을 포트폴리오용 html 파일에 외부 참조 할 수 있도록 link를 작성 한 후, 적절한 요소에 class를 부여하여야 한다.  
`단, HTML 마크업을 직접 수정하지 않고 JS 만을 사용하여 class를 부여한다.`  
요구사항을 만족하는 코드를 작성하시오.  

요구사항
- 스타일 시트 적용
  - 제공된 style.css 파일을 외부 참조 할 수 있도록 link 태그를 작성한다.
    - JS가 아닌 HTML link 태그를 직접 작성한다.
- JS DOM 조작
  - body태그를 선택하여 container class를 부여한다.
  - h1 태그를 선택하여 title class를 부여한다.
  - img 태그에 img class를 부여한다.
  - id가 name, job, experience, email, phone인 요소에 highlight class를 부여한다.
  - 연락처 정보 하단에 SNS 정보를 추가하시오.
    - 구성은 다른 연락처 정보(전화번호, 이메일)와 동일하게 작성한다.
---
요구사항을 천천히 따라갑니다.  
`classList.add`를 사용하여 해당 요소에 class를 부여합니다.  
`document.getElementById`를 이용하여 해당 id값을 가진 요소를 선택할 수 있습니다.  
`appendChild`를 사용하여 SNS 정보를 body태그의 자식 요소로 추가합니다.
<div style="text-align: right">20240416</div>