### 조건문과 상세 페이지 Lv3
목표  
  - 학습 주제
    - 프레임워크에 대한 이해
    - Django 프로젝트 구조에 대한 이해
    - 디자인 패턴에 대한 이해
  - 학습 목표
    - 프레임워크에 대해 이해하고 활용할 수 있다.
    - Django Template Language의 사용법을 이해하고 활용할 수 있다.
    - 디자인패턴에 대해 이해하고 적용할 수 있다.
  - 학습 개념
    - 가상환경
      - Python 애플리케이션과 그에 따른 패키지들을 격리하여 관리할 수 있는 독립적인 실행 환경
    ```
    # 1. 가상 환경 venv 생성
    # bash창에서 아래 명령어 입력
    $ python -v venv {가상환경 폴더명}

    # 2. 가상환경 활성화
    $ source venv/Scripts/activate

    # 3. 의존성 패키지 목록 생성
    $ pip freeze > requirements.txt
    ```
    - Django Template system​
      - 데이터 표현을 제어하면서, 표현과 관련된 부분을 담당​
    - Variable Routing​
      - URL 일부에 변수를 포함시키는 것​
      - 변수는 view 함수의 인자로 전달 할 수 있음
    ```
     # urls.py​

    urlpatterns = [​
        path('hello/<str:name>/', views.greeting),​
    ]​
    ```
  - 학습 방향
    - Django 공식 문서를 보며 프로젝트를 생성한다.
    - DTL 사용법을 익혀 Template의 기본적인 구성을 작성한다.
    - Django 공식 문서 https://docs.djangoproject.com/en/4.2/
---
문제  
현재 프로젝트는 index view에서 넘겨주려는 데이터가 존재하지 않으면 None이 화면에 렌더링 되고 있다.  
Django Template Language를 활용하여 데이터가 없을 때, 이에 맞는 올바른 문구가 출력되도록 수정하도록 한다.  
또한, 올바른 데이터가 있다면, 상세 정보를 확인할 수 있는 페이지를 만들고자 한다.  
요구사항을 만족하는 코드를 작성하시오.  

요구사항  
- gitignore를 생성한다.
  - python과 django 관련 설정은 반드시 포함되어야 한다.
- 파이썬 가상 환경을 생성하고, 활성화한다.
- 활성화된 가상 환경에서 django를 설치한다.
- DTL 활용하기
  - create_todo를 통해 데이터를 요청받은 경우가 아니라면, request.GET.get('work') 함수의 호출 결과가 None이다.
  - index view 함수에서 조건문을 작성하여도 되지만, 이번에는 DTL을 연습해 보고자 한다.
  - index.html에서 DTL 조건문을 사용하여, work에 데이터가 있으면 데이터를, 없다면 "아직 할 일이 없습니다." 문구가 렌더링 되어야 한다.
- 상세 페이지 요청 경로 만들기
  - variable routing을 활용하여 할 일 내용마다의 상세 페이지를 요청할 수 있는 경로를 작성한다.
  - 'http://127.0.0.1:8000/todos/{{ work }}/' 경로로 요청을 보내면 detail view 함수가 실행되어야 한다.
- detail view 함수 만들기
  - detail view 함수는 2번째 인자로 'work' 매개변수를 가진다.
  - 'work' 매개변수에는 사용자가 URL에 입력한 값이 전달된다.
  - 전달받은 값을 detail.html 에서 렌더링 할 수 있도록 context dict에 담아 render함수의 3번째 인자로 넘겨준다.
- template 수정하기
  - detail.html 에서는 아래 문구가 출력되어야 한다.
  - {{ work }} 상세 페이지
  - 이때 work 부분은 넘겨받은 데이터이다.
  - index.html 에서는 리스트에 작성된 할 일을 클릭하면, 상세 페이지로 이동하여야 한다.
  - 링크의 이동 위치는 '/todos/{{ work }}/' 로 작성한다.
- 서버를 실행하고, 수정한 내용이 모두 정상 작동하는지 확인한다.
---
`DTL`을 활용하여 html에서 조건문을 작성할 수 있습니다. `context dict`에 변수를 담아 `render`함수를 사용하여 html에 넘겨줍니다.
<div style="text-align: right">20240313</div>