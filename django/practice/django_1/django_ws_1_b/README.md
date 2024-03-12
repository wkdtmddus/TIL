### 메인페이지 생성 Lv2
목표  
  - 학습 주제
    - 프레임워크에 대한 이해
    - Django 프로젝트 구조에 대한 이해
    - 디자인 패턴에 대한 이해
  - 학습 목표
    - 프레임워크에 대해 이해하고 활용할 수 있다.
    - Django 프로젝트의 구조를 이해하고 활용할 수 있다.
    - 디자인패턴에 대해 이해하고 적용할 수 있다.
  - 학습 개념
    - Web Framework
      - 웹 애플리케이션을 빠르게 개발할 수 있도록 도와주는 도구
      - 개발에 필요한 기본 구조, 규칙, 라이브러리 등을 제공
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
    - Django application
      - 독립적으로 작동하는 기능 단위 모듈
      - 각자 특정한 기능을 담당하며 다른 앱들과 함께 하나의 프로젝트를 구성
    - URL dispatcher
      - URL 패턴을 정의하고 해당 패턴이 일치하는 요청을 처리할 view 함수를 연결(매핑)
    ```
    urlpatterns = [
      path('articles/1/', ...),
      ...,
    ]
    ```
  - 학습 방향
    - Django 공식 문서를 보며 프로젝트를 생성한다.
    - 기본적인 명령어들을 차례대로 작성해 본다.
    - Django 공식 문서 https://docs.djangoproject.com/en/4.3/
---
문제  
할 일 목록을 관리하는 프로젝트를 생성하고자 한다.  
요구사항을 만족하는 코드를 작성하시오.  

요구사항  
- `gitignore.io` 사이트를 활용하여 .gitignore를 생성한다.
  - python과 django 관련 설정은 반드시 포함되어야 한다.
- 파이썬 가상환경을 생성하고, 활성화한다.
- 활성화된 가상환경에서 django를 설치한다.
  - 선택
    - django 직접 설치하기
    - 제공된 requirements.txt를 활용하여 설치하기
- app을 생성한다.
  - app명은 todos로 작성한다.
- 메인페이지를 요청할 수 있는 경로를 작성한다.
  - 'todo_list_project/urls.py'에서 요청을 위한 경로를 관리한다.
  - `http://127.0.0.1:8000/todos/`경로로 이동하면 todos앱의 view 함수 index가 실행되어야 한다.
- index view 함수를 작성한다.
  - index view 함수는 index.html을 사용자에게 제공하여야 한다.
  - index.html은 'todos/templates/todos/'경로에 생성한다.
- 서버를 실행하고 지정된 경로로 요청을 보내 정상적으로 응답하는지 확인한다.
---
html은 `templates`폴더에 만들어야 합니다. 경로설정도 잘 해줘야 합니다.
<div style="text-align: right">20240312</div>