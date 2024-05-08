### 컴포넌트간 데이터 전송하기 1 Lv2
목표  
- Vite를 사용하여 프로젝트를 생성할 수 있다.
- 컴포넌트 간 데이터 전송을 구현할 수 있다.
---
문제  
Vite를 활용하여 프로젝트를 생성하고 컴포넌트간의 데이터 전송을 연습하고자 한다.  
제시된 스크린샷을 참고하여 동일한 환경의 프로젝트를 생성하고, 요구사항을 만족하는 코드를 작성하시오.  

요구사항
1. 사용하지 않을 컴포넌트와 assets들을 모두 삭제한다.
  - components/ 폴더의 모든 컴포넌트
  - assets/ 폴더의 모든 파일
2. 관련된 모든 코드를 삭제한다.
  - App.vue에 작성된 삭제된 component들
  - main.js에 작성된 main.css import 문
3. ParentPage 컴포넌트를 생성한다.
  - h1 요소에 부모 페이지입니다. 문구를 작성한다.
  - children 배열을 작성한다. 각 요소는 다음의 속성을 가지는 객체로 작성한다.
    - name: '김하나', age: 30
    - name: '김두리', age: 20
    - name: '김서이', age: 10
4. ChildPage 컴포넌트를 생성한다.
  - h3 요소에 자식 페이지입니다. 문구를 작성한다.
5. ChildPage를 ParentPage에 등록한다.
  - 단, children 배열을 반복하여 등록한다.
6. ChildPage 컴포넌트는 ParentPage 커포넌트로부터 각 자식 데이터를 prop 받는다.	
  - prop받은 객체로부터 이름과 나이를 화면에 렌더링 한다.
7. ParentPage 컴포넌트를 App.vue 컴포넌트에 등록한다.
---
scoped에 작성한 style은 해당 페이지와 하위 컴포넌트 최상위 요소에만 적용됩니다.  
`defineProps()`으로 상위 컴포넌트에서 요소를 전달 받을 수 있습니다.
<div style="text-align: right">20240507</div>