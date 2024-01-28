### 모듈 연습 Lv1
목표  
- 파이썬의 모듈을 불러오고 사용하는 방법을 이해한다.
- 파이썬의 패키지와 모듈을 생성할 수 있다.
---
문제  
각종 변수와 함수들을 기능에 따라 서로 다른 파일의 다른 모듈에 작성해 두었다. 주어진 코드를 참고하여 요구 사항을 만족하는 코드를 작성하시오.  

요구사항  
conf패키지의 settings모듈에 정의된 변수 NAME과 MAIN_URL을 가져온다.  
utils패키지의 create_url모듈에 정의된 함수 create_url을 가져온다.  
create_url함수에 settings모듈에서 가져온 NAME, MAIN_URL변수를 인자로 할당하여 호출한다.  
함수를 호출한 결과를 result에 할당한다.  
result를 출력한다.
```
http://127.0.0.1:8000/develper?page=1
```
---
`from`을 사용하여 `폴더`에 접근하고, `import`를 사용하여 `파일` 또는 `함수`에 접근합니다.
<div style="text-align: right">20240118</div>