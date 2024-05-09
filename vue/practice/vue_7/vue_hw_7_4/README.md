### Pinia를 활용한 상태 관리하기 2 Lv4
목표  
- Pinia를 활용하여 Vue.js 프로젝트의 상태를 관리할 수 있다.
- Vue Router를 사용하여 다중 페이지 애플리케이션을 구현할 수 있다.
---
문제  
pinia를 활용한 상태 관리 기능을 포함한 프로젝트를 생성하고 한다.  
제시된 스크린샷을 참고하여 동일한 환경의 프로젝트를 생성하고, 요구사항을 만족하는 코드를 작성하시오.  

요구사항
- 사용하지 않을 컴포넌트와 assets들을 모두 삭제한다.
  - components/ 폴더의 모든 컴포넌트
  - assets/ 폴더의 모든 파일
  - views/ 폴더의 모든 View 컴포넌트
  - stores/ 폴더의 모든 파일
- 관련된 모든 코드를 삭제한다.
  - App.vue에 작성된 삭제된 component들
  - main.js에 작성된 main.css import 문
  - router/index.js에 작성된 경로 및 import 문
- App.vue 컴포넌트의 화면을 구성한다.
  - 'pinia + router 연습하기' 문구를 포함한다.
- views/MainView 컴포넌트를 작성한다.
  - '메인 페이지' 문구를 작성한다.
  - router/index.js에 등록한다.
    - 경로: '/'
    - 이름 : 'main'
- stores/balance.js 파일을 생성한다.
  - useBalanceStore를 정의한다.
  - balances 배열에 잔고 정보를 작성한다.
- conponents/MainPage 컴포넌트를 작성한다.
  - MainView 컴포넌트에서 state의 balnaces 정보를 받아와 반복문을 사용하여 MainPage 컴포넌트들을 렌더링한다.
  - MainPage 컴포넌트는 넘겨받은 각 정보를 화면에 렌더링하고,
  - updateBalance 버튼을 사용하여 UpdateView로 이동할 수 있는 기능을 구현한다.
- views/UpdateView 컴포넌트를 작성한다.
  - '데이터 수정 페이지' 문구를 작성한다.
  - name 정보를 params로 넘겨 받을 수 있어야 한다.
  - router/index.js에 등록한다.
    - 경로: '/update/:name'
    - 이름 : 'update'
- stores/balance.js에서 name이 일치하는 객체 정보를 반환하는 기능을 구현한다.
  - getters에 인자를 넘겨주는 방식은 공식문서를 참고한다.
    - 공식 문서 링크 : https://pinia.vuejs.kr/core-concepts/getters.html#passing-arguments-to-getters
  - UpdateView는 name이 일치하는 객체 정보를 화면에 렌더링한다.
- balance 정보의 값을 수정 할 수 있는 기능을 구현한다.
  - UpdateView에서 + 버튼을 클릭시, 해당 객체의 balance 값이 1000 상승하는 기능을 구현한다.
- App.vue 컴포넌트에서 각 View 컴포넌트로 이동할 수 있도록 router-link를 작성한다.
---
`useRoute`를 사용하여 파라미터의 값을 가져올 수 있습니다.  
`action`에 해당하는 로직은 stores에 작성을 합니다.  
prop과 store를 적절히 사용하여 문제를 해결합니다.  
pinia를 이해하고 숙지합니다.
<div style="text-align: right">20240509</div>