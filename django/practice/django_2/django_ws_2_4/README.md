### 도서 조회 서비스 만들기-베스트 셀러 Lv4
목표  
  - 학습 주제
    - API에 대한 이해 
    - 요청과 응답에 대한 이해 
    - Django Template Laguage에 대한 이해 
    - 디자인 패턴에 대한 이해
  - 학습 목표
    - API를 활용하여 원하는 데이터를 수집할 수 있다. 
    - requests 라이브러리를 사용할 수 있다. 
    - 프로젝트 구성에 필요한 데이터를 수집할 수 있다. 
    - Django Template Language의 사용법을 이해하고 활용할 수 있다. 
    - 디자인 패턴에 대해 이해하고 적용할 수 있다.
  - 문제 의도
    - Django에서 화면 구성을 위해 필요한 데이터를 수집 할 수 있다.
    - Django 프레임워크에 대해 이해하고 명세에 맞춰 웹 서버를 구현할 수 있다.
---
문제  
새로운 페이지를 사용하여 ‘베스트 셀러’를 보여주는 페이지를 작성하고자 한다.  
이때 사용할 데이터는 aladin API를 사용하여 받아오고, 필요한 정보를 적절한 template에 넘겨주어 DTL을 사용해 작성한다.  
요구사항을 충족하는 페이지를 구성하시오.  

요구사항  
- 127.0.0.1:8000/recommend/ 경로로 요청시 주목 할 만한 신간 리스트 항목을 보여준다.
- 이를 위해, 알라딘 open API를 활용한다.
- 알라딘 open API 메뉴얼을 참고하여 아래의 값들만 정리하여 수집한다.
  - ※ 주의: 특정 값이 없는 도서의 경우, KeyError 가 발생 할 수 있음에 유의한다.	
  - 책 제목
  - 저자
  - 출간일
  - 국제 표준 도서 번호 (ISBN)
  - 판매 지수
  - 베스트 셀러 순위 관련 추가 정보 (베스트 셀러 순위와 다름)
- 판매 지수를 기준으로 내림차순 정렬한다.
- 수집한 데이터 중 책 제목, 저자, 그리고 베스트 셀러 순위 관련 정보만 사용자에게 추가로 제공한다.
---
요구사항을 천천히 따라갑니다.  
`views`안에서 적절한 로직을 구현하여 문제에 알맞게 정보를 찾습니다.  
`DTL`을 사용하여 적절한 html 출력을 구현합니다.
<div style="text-align: right">20240313</div>