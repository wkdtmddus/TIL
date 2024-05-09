### Pinia를 활용한 상태 관리하기 1 Lv2
목표  
- Pinia를 사용하여 Vue.js 프로젝트를 생성할 수 있다.
- Vue.js 컴포넌트를 작성하고 상태를 Pinia를 통해 관리할 수 있다.
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
- components/MainPage 컴포넌트를 작성한다.
  - 'pinia 연습하기' 문구를 포함한다.
  - App.vue 컴포넌트에 등록한다.
- components/ParentPage 컴포넌트를 작성한다.
  - '부모 컴포넌트' 문구를 작성한다.
  - MainPage 컴포넌트에 하위 컴포넌트로 등록한다.
- components/ChildPage 컴포넌트를 작성한다.
  - '자식 페이지' 문구를 작성한다.
  - ParentPage 컴포넌트에 하위 컴포넌트로 등록한다.
- stores/family.js 파일을 생성한다.
  - useFamilyStore 변수에 defineStore를 활용하여 store를 객체를 할당한다.
  - 가족 정보를 가질 배열 familyInfo 정보를 작성한다.
- MainPage 컴포넌트에서 store에 저장된 familyInfo 정보를 불러온다.
  - v-for를 활용하여 ParentPage를 배열의 수 만큼 생성되도록 한다.
  - 각 가족 정보를 ParentPage로 prop한다.
- ParentPage 컴포넌트는 넘겨받은 가족 정보를 화면에 렌더링한다.
  - children 배열을 활용하여 ChildPage 컴포넌트를 렌더링한다.
---
`pinia`에 대해 이해하고 숙지합니다.  
`stores`에서 전역으로 관리할 수 있습니다.
<div style="text-align: right">20240509</div>