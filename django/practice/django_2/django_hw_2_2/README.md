### form tag 실습 예제 Lv2
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
articles 앱에 있는 catch함수에게 form tag의 GET method를 이용하여 데이터를 전달하고자 한다.  
그러나 현재 작성된 form으로는 올바른 경로로 요청을 보내지 못하고 있다.  
올바르게 요청을 보낼 수 있도록 form을 수정한다.  
또한, 이렇게 보내진 데이터는 DTL을 사용하여 응답 페이지에서 렌더링 되어야 한다.  
주어진 코드와 요구사항을 참고하여 코드를 작성하시오.  

요구사항  
- 새로운 가상 환경을 생성하고, 가상환경을 활성화한다.
- 활성화된 가상환경에서 제공된 requiremenets.txt를 활용하여 패키지들을 설치한다.
- 기존에 생성되어 있는 프로젝트의 서버를 실행하여 정상 작동 여부를 확인한다.
- 'send.html' 수정하기
  - 'templates/articles/send.html' 파일의 form태그를 수정한다.
    - submit 버튼이 클릭 되었을 때, 'htttp://127.0.0.1:8000/receive/' 경로로 요청이 보내져야한다.
      - 선택: 경로를 상대경로로 작성한다.
---
요구사항을 천천이 따라갑니다.  
태그들은 알맞은 경로를 갖고 있어야 합니다.  
<div style="text-align: right">20240313</div>