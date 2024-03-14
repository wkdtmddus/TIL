### 페이지 간 데이터 주고 받기 Lv2
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
    - form
      - HTML `<form>` 요소는 정보를 제출하기 위한 대화형 컨트롤을 포함하는 문서 구획을 나타냅니다.
  - 학습 방향
    - Django 공식 문서를 보며 프로젝트를 생성한다.
    - DTL 사용법을 익혀 Template의 기본적인 구성을 작성한다.
    - Django 공식 문서 https://docs.djangoproject.com/en/4.2/
---
문제  
html form과 Django Template Language를 활용하여, 하나의 페이지에서 다른 페이지로 데이터를 전송하고, 전송받은 데이터를 화면에 렌더링하는 기능을 구현하고자 한다.  
요구사항을 만족하는 코드를 작성하시오.  

요구사항  
- gitignore를 생성한다.
  - python과 django 관련 설정은 반드시 포함되어야 한다.
- 파이썬 가상 환경을 생성하고, 활성화한다.
- 활성화된 가상 환경에서 django를 설치한다.
- 사용자 입력 데이터 전송하기
  - create_todo.html을 수정하여, 사용자가 문자열을 입력할 수 있는 form을 작성한다.
  - form은 문자열을 입력할 수 있는 input 태그와 제출을 위한 submit input 태그를 가진다.
  - input 태그에 작성한 값은 submit 버튼을 누르면, '/todos/' 경로로 전송되어야 한다.
  - 이때, name은 'work'로 작성한다.
- 전송 받은 데이터 활용하기
  - index view 함수를 수정하여 create_todo로 부터 전송받은 데이터를 work 변수에 할당한다.
  - work 변수는 index.html에서 활용할 수 있도록 context dict에 담아 render함수의 3번째 인자로 넘겨준다.
  - index.html에서 넘겨받은 work 데이터를 unordered list 태그로 렌더링한다.
- 서버를 실행하고, create_todo 페이지에서 전송한 데이터가 index 페이지에서 렌더링 되는지 확인한다.
---
form에 적절한 경로를 작성합니다. wokr 변수 이름을 알맞은 곳에 정확하게 작성해야 합니다.
<div style="text-align: right">20240313</div>