### Vue3와 DRF 연동하기 - CORS Lv4
목표  
- ArticleList 컴포넌트에서 게시글 정보를 렌더링하는 방법을 익힌다.
- ArticleCreateView 컴포넌트를 생성하고 Vue Router를 통해 라우팅하는 방법을 습득한다.
---
문제  
DRF 서버로부터 게시글 정보를 불러 올 수 있는 Vue 프로젝트를 작성중에 있다.  
주어진 코드를 수정하여 요구사항을 만족하는 코드를 작성하시오.  

요구사항
1. ArticleList 컴포넌트에 게시글 전체 정보를 렌더링 하여야 한다.
  - 하위 컴포넌트 사용 여부는 자유롭게 판단한다.
  - 게시글 생성 페이지로 이동가능한 button 혹은 link가 있어야 한다.
2. ArticleCreateView 컴포넌트를 생성한다.
  - '게시글 생성 페이지' 문구를 작성한다.
    - router/index.js에 등록한다.
      - 경로: '/create'
      - 이름 : 'create'
  - 게시글 생성 form을 포함하고 있어야 한다.
3. stores/articles.js에 게시글 생성 요청 기능을 작성한다.
  - DRF 서버에 POST요청을 통해 게시글이 생성되어야 한다.
  - 생성된 게시글은 ArticleList에서 즉시 확인 가능하여야 한다.
---
django와 vue3의 연결을 이해합니다.  
vue에서 django로 post 요청 시, url에 `/`가 꼭 있어야 합니다.
<div style="text-align: right">20240513</div>