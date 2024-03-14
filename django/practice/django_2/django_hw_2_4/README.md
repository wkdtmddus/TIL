### DTL에서의 url Lv4
목표  
  - 학습 주제
    - 프레임워크에 대한 이해
    - Django Template Laguage에 대한 이해
    - 디자인 패턴에 대한 이해
  - 학습 목표
    - 프레임워크에 대해 이해하고 활용할 수 있다.
    - Django Template Language의 사용법을 이해하고 활용할 수 있다.
    - 디자인 패턴에 대해 이해하고 적용할 수 있다.
  - 문제 의도
    - Django 프레임워크에 대해 이해하고 명세에 맞춰 웹 서버를 구현할 수 있다.
---
문제  
사용자가 URL에 다양한 내용을 입력했을 때, 적절히 대응할 수 있는 경로를 작성하고자 한다.  
예를들어, 자신의 아이디를 경로로 만들어 자신만의 프로필 페이지처럼 사용할 수 있도록 하고자 한다.  
주어진 코드를 활용하여 요구사항을 만족하는 코드를 작성하시오.  

요구사항  
- 새로운 가상 환경을 생성하고, 가상환경을 활성화한다.
- 활성화된 가상환경에서 제공된 requiremenets.txt를 활용하여 패키지들을 설치한다.
- 기존에 생성되어 있는 프로젝트의 서버를 실행하여 정상 작동 여부를 확인한다.
- 'urls.py' 수정하기
  - 'my_pjt/urls.py'를 수정한다.
  - '/introduce/{username}/'경로로 요청을 보내면, introduce view 함수가 실행되어야 한다.
- view 함수 작성하기
  - 'my_app/views.py' 파일에 introduce view 함수를 정의한다.
  - 매개변수로 request와 함께, username을 받을 수 있어야 한다.
  - 전달받은 값 username은 'templates/my_app/introduce.html' 파일에서 렌더링 되어야 한다.
- html 파일 작성하기
  - my_app 앱에서 사용할 template들을 모을 수 있는 폴더를 생성한다.
  - introduce.html 파일에서는 username의 데이터가 렌더링 되어야 한다.
---
요구사항을 천천이 따라갑니다.  
`render`를 사용하여 데이터를 넘겨주어야 합니다.
<div style="text-align: right">20240313</div>