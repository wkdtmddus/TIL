### 다양한 directive 연습하기 - 3 Lv2
목표  
- Vue.js를 사용하여 웹 애플리케이션을 개발하는 방법을 이해한다.
- 조건문을 활용하여 로그인 및 로그아웃 기능을 구현하는 방법을 배운다.
- Vue.js에서 상태 관리를 위한 변수를 활용하는 방법을 익힌다.
---
문제  
vue3이 제공하는 다양한 directive를 연습해 보고자 한다.  
주어진 코드를 활용하여 요구 사항을 만족하는 코드를 작성하시오.  

요구사항
- Vue3 CDN을 사용한다.
1. 새로운 app instance를 생성하여 아이디가 app인 container에 mount 하시오.
2. 로그인 여부를 판별할 변수 isLogin을 작성한다. 기본값은 false이다.
3. Login/Logout 버튼을 클릭하면 isLogin에 할당된 값이 이전 값의 부정형이 되도록 하는 메서드가 실행되도록 한다.
  - isLogin의 값이 true일때, 버튼이 클릭되면 false가 할당되어야 한다. false인 경우에는 true가 할당되어야 한다.
4. isLogin 정보가 true일 때만, user 정보가 화면에 렌더링 될 수 있도록 한다.
  - user 정보 객체는 다음의 속성들을 가진다.
  - userName : admin
  - isAdmin : true
  - passWord : 1q2w3e4r
---
`v-if`를 사용하여 조건을 정할 수 있습니다.
<div style="text-align: right">20240430</div>