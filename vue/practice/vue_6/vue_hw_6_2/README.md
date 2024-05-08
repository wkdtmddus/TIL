### 라우터를 활용한 컴포넌트 관리하기 1 Lv2
목표  
- Vue Router를 활용하여 다중 페이지 애플리케이션을 개발할 수 있다.
- 라우터를 설정하고 컴포넌트 간의 화면 전환을 구현할 수 있다.
---
문제  
router 기능을 포함한 프로젝트를 생성하고 한다.  
제시된 스크린샷을 참고하여 동일한 환경의 프로젝트를 생성하고, 요구사항을 만족하는 코드를 작성하시오.  

요구사항
1. 사용하지 않을 컴포넌트와 assets들을 모두 삭제한다.
  - components/ 폴더의 모든 컴포넌트
  - assets/ 폴더의 모든 파일
  - views/ 폴더의 모든 View 컴포넌트
2. 관련된 모든 코드를 삭제한다.
  - App.vue에 작성된 삭제된 component들
  - main.js에 작성된 main.css import 문
  - router/index.js에 작성된 경로 및 import 문
3. components/MainPage 컴포넌트를 작성한다.
  - 'router 연습하기' 문구를 포함한다.
  - App.vue 컴포넌트에 등록한다.
4. views/SomeView 컴포넌트를 작성한다.
  - '첫 페이지' 문구를 작성한다.
  - router/index.js에 등록한다.
  - 경로: '/'
  - 이름 : 'some'
5. views/OtherView 컴포넌트를 작성한다.
  - '이동한 페이지' 문구를 작성한다.
  - router/index.js에 등록한다.
  - 경로: '/other'
  - 이름 : 'other'
6. App.vue 컴포넌트에서 각 View 컴포넌트로 이동할 수 있도록 router-link를 작성한다.
---
프로젝트를 생성할 때, router 설정을 Yes로 합니다.  
router 폴더 설정을 숙지합니다.
<div style="text-align: right">20240508</div>