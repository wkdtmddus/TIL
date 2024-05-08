### 라우터를 활용한 컴포넌트 관리하기 2 Lv4
목표  
- Vue Router를 활용하여 다중 페이지 애플리케이션을 구현할 수 있다.
- 동적 라우팅을 사용하여 학생 정보를 표시하는 페이지를 만들 수 있다.
---
문제  
router 기능을 포함한 프로젝트를 생성하고 한다.  
2단계에서 작성한 코드에서 이어서 진행하거나 제시된 코드를 활용하여 요구사항을 만족하는 코드를 작성하시오.  

요구사항
1. views/StudentViews 컴포넌트를 생성한다.
  - router/index.js에 등록한다.
    - path: '/students'
  - 학생 정보 객체를 가진 배열을 작성한다.
  - 학생 정보
    - name : '김하나'
    - name : '김두리'
    - name : '김서이'
  - 각 학생 정보의 name을 기준으로 router-link 혹은 router.push를 통해 StudentDetailView 경로로 이동 할 수 있도록 코드를 작성한다.
2. views/StudentDetailView 컴포넌트를 작성한다.
  - 학생 상세 정보 페이지의 화면 구성을 작성한다.
  - router/index.js에 등록한다.
    - path: '/students/:name'
  - params로 넘겨받은 학생의 이름을 화면에 표시한다.
---
컴포넌트 내에 라우터가 존재합니다.  
함수 형식으로 `router.push()`를 할 수 있고, App.vue처럼 routerlink를 사용 할 수도 있습니다.
<div style="text-align: right">20240508</div>