### Vue3와 DRF 연동하기 - CORS Lv2
목표  
- back 프로젝트에서 모든 교차 사이트의 HTTP 요청을 승인할 수 있는 방법을 이해한다.
- front 프로젝트에서 Axios를 사용하여 HTTP 요청을 보내는 방법을 익힌다.
---
문제  
DRF 서버로부터 게시글 정보를 불러 올 수 있는 Vue 프로젝트를 작성중에 있다.  
그런데 CORS 문제로 정보를 받아 올 수가 없는 상황이다.  
주어진 코드를 수정하여 정상적으로 데이터를 받아 올 수 있도록 수정하시오.  

요구사항
- back 프로젝트에서 모든 교차 사이트의 HTTP 요청에 대하여 승인할 수 있도록 수정하시오.
- front 프로젝트에서 ArticleList 컴포넌트가 Mount되는 시점에 axios 요청을 정상적으로 수행하는지 확인하시오.
---
django의 settings.py에서 아래 코드를 작성한다.
```
ISTALLED_APP = [
  ...,
  'corshaeders',
  ...
]

MIDDLEWARE = [
  ...,
  'corsheaders.middleware.CorsMiddleware',
  ...
]

CORS_ALLOWED_ORIGINS = [
  'http://127.0.0.1:5173',
  'http://localhost:5173',
]
```
<div style="text-align: right">20240513</div>