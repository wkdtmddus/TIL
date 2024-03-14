### base template 만들기 Lv1
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
    - Django Template Language
      - Template에서 조건, 반복, 변수 등의​ 프로그래밍적 기능을 제공하는 시스템​
    ```
    # 1. Variable​
    {{ variable.attribute }}​

    # 2. Tags​
    {% if %} {% endif %}​
    ```
  - 학습 방향
    - Django 공식 문서를 보며 프로젝트를 생성한다.
    - 기본적인 명령어들을 차례대로 작성해 본다.
    - Django 공식 문서 https://docs.djangoproject.com/en/4.3/
---
문제  
할 일 목록을 관리하는 프로젝트의 모든 페이지들을 공통으로 관 할 수 있는 base template을 생성하고, 모든 페이지에서 각기 다른 페이지로 이동할 수 있는 nav bar도 함께 생성해보고자 한다.  
요구 사항을 만족하는 코드를 작성하시오.  

요구사항  
- gitignore를 생성한다.
  - python과 django 관련 설정은 반드시 포함되어야 한다.
- 파이썬 가상 환경을 생성하고, 활성화한다.
- 활성화된 가상 환경에서 django를 설치한다.
- 모든 페이지에서 활용가능한 base.html을 생성한다.
  - 프로젝트의 최상단 폴더에 templates 폴더를 생성한다.
  - templates/base.html 파일을 생성한다.
  - html의 body 태그 내부에, 각 페이지마다 본문을 작성할 수 있는 'content' block을 생성한다.
- todo_list_project/settings.py 파일을 수정한다.
  - 'TEMPLATES' 리스트를 수정하여, base.html이 작성된 templates 폴더를 탐색할 수 있도록 적절한 설정을 작성한다.
- index.html과 create_todo.html을 수정한다.
  - base.html을 상속받아 화면을 구성한다.
  - 페이지별 본문은 'content block' 내부에 작성한다.
- nav bar 만들기
  - index.html과 create_todo.html에 작성된 링크를 base.html로 옮겨 작성한다.
  - 단, 'content block' 이외의 영역에 작성되어야 한다.
- 서버를 실행하고, 각 페이지가 정상적으로 렌더링 되는지 확인한다.
---
`DTL`을 숙지하여 문제에 접근합니다.
<div style="text-align: right">20240313</div>