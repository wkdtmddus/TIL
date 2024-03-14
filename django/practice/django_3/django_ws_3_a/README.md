### 두 번째 앱과 URL 관리 Lv1
목표  
  - 학습 주제
    - 프레임워크에 대한 이해
    - Django Template Laguage에 대한 이해
    - 디자인 패턴에 대한 이해
  - 학습 목표
    - 프레임워크에 대해 이해하고 활용할 수 있다.
    - Django Template Language의 사용법을 이해하고 활용할 수 있다.
    - 디자인 패턴에 대해 이해하고 적용할 수 있다.
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
    - Variable Routing
      - URL 일부에 변수를 포함시키는 것
      - 변수는 view 함수의 인자로 전달할 수 있음
    ```
     # urls.py

    urlpatterns = [
        path('hello/<str:name>/', views.greeting),
    ]
    ```
  - 학습 방향
    - Django 공식 문서를 보며 프로젝트를 생성한다.
    - DTL 사용법을 익혀 Template의 기본적인 구성을 작성한다.
    - app과 url을 분리하여 관리할 때, 어떤 문제점이 발생할 수 있는지 고려한다.
    - Django 공식 문서 https://docs.djangoproject.com/en/4.2/
---
문제  
할 일 목록 프로젝트를 진행하던 중, 언젠가는 다수의 회원들이 각자의 할 일을 관리할 수 있도록 하고 싶어졌다.  
변경된 기획에 맞춰 2번째 app을 생성해 나중에 추가될 회원 관리를 위한 app을 추가하도록 한다.  
2번째 앱을 생성한 후, 각 페이지로 연결되는 URL을 각자의 app이 관리할 수 있도록 수정한다.  
요구사항을 만족하는 코드를 작성하시오.  

요구사항  
- gitignore를 생성한다.
- 파이썬 가상환경에서 프로젝트를 진행한다.
- URL 관리하기
  - 다수의 app들은 각자가 수행하는 요청 경로를 각자의 urls.py에서 관리할 수 있도록 수정한다.
  - todo_list_project/urls.py에 작성한 코드를 수정한다.
    - todos 관련 경로들을 모두 todos/urls.py에서 관리할 수 있도록 include 함수를 사용한 경로로 수정한다.
  - todos/urls.py 파일을 생성하고, 3개의 페이지로 이동할 수 있는 각 경로를 작성한다.
  - 각 경로는 경로마다 고유의 name을 가지고 있어야 한다.
  - app_name을 작성한다.
  - 추후에 app이 추가될 때, 모든 app에 대해 동일한 규칙을 적용한다.
- 두 번째 app 생성하기
  - app 이름은 'accounts'로 생성한다.
  - '/accounts/login/' 경로로 요청을 보내면 login view 함수가 실행되어야 한다.
  - 경로 설정 방법은 위에서 수정한 todos의 방법을 참고한다.
  - login view 함수는 'accounts/login.html'을 렌더링 한다.
  - login.html은 유저네임과 비밀번호를 입력할 수 있는 form 태그를 가진다.
- base.html 수정
  - login 페이지로 이동할 수 있는 링크를 작성한다.
- 서버를 실행하고, 기존의 todos app의 기능과 새로 추가된 accounts app의 모든 기능이 정상 작동하는 지 확인한다.
---
요구사항을 천천이 따라갑니다.  
url 관리를 각 앱이 담당하는 방식입니다.
<div style="text-align: right">20240314</div>