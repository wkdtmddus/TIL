### todo 프로젝트 설정과 데이터 조회(with shell) Lv1
목표  
  - 학습 주제
    - 프레임워크에 대한 이해
    - Django ORM에 대한 이해 
    - Data Schema에 대한 이해
  - 학습 목표
    - 스키마에 명시된 요구 조건에 맞는 django model을 작성할 수 있다. 
    - Django ORM에 대해 이해하고, 적절히 활용할 수 있다.
  - 학습 개념
    - Django Model
      - DB의 테이블을 정의하고 데이터를 조작할 수 있는 기능들을 제공
    - Migrations
      - model 클래스의 변경사항(필드 생성, 수정 삭제 등)을 DB에 최종 반영하는 방법
    - ORM
      - Object-Relational-Mapping​
      - 객체 지향 프로그래밍 언어를 사용하여 ​호환되지 않는 유형의 시스템 간에 데이터를 변환하는 기술​
  - 학습 방향
    - Django 공식 문서를 보며 프로젝트를 생성 한다.
    - Python의 class를 사용하여 DB를 설계 하고 조작할 수 있음을 이해한다.
    - Django 공식 문서 https://docs.djangoproject.com/en/4.2/
---
문제  
할 일 목록을 관리자 페이지에서 생성하고, 정상적으로 생성되었는지 shell 창을 통해 조회하고자 한다.  
주어지는 코드와 모델 예시 표를 참고하여 요구사항을 만족하는 코드를 작성하시오.  

요구사항  
- gitignore를 생성한다.
- 파이썬 가상환경에서 프로젝트를 진행한다.
  - 제공된 requirements.txt를 통해 django를 설치한다.
  - ipython과 django-extensions를 추가로 설치한다.
  - 추가된 외부 라이브러리 정보를 requirements.txt에 추가한다.
- DB 생성하기
  - 작성되어 있는 todos/models.py를 참고하여, Todo 클래스를 확인한다.
  - makemigrations 명령어를 사용하여, 작성한 모델에 대한 설계도를 작성한다.
  - migrate 명령어를 사용하여, 작성한 설계도를 토대로 DB를 생성한다.
- 관리자 계정 생성하기
  - 명령어를 통해 관리자 계정을 생성한다.
  - 아이디는 admin, 비밀번호는 1234로 생성한다.
  - 이메일은 작성하지 않는다.
- 데이터 생성
  - todos/admin.py에 Todo 모델이 admin으로 관리되는 설정이 작성되어 있는지 확인한다.
  - 서버를 실행하고, 관리자 페이지에서 Todo 모델을 생성한다.
  - 데이터 생성 시, work는 '첫 번째 내용', content는 '상세 내용', is_completed는 'False'로 작성한다.
  - 서버를 종료한다.
- 데이터 조회
  - todo_list_project/settings.py에 'django_extensions' 설정을 추가 작성한다.
  - 터미널에서 적절한 명령어를 통해 shell_plus에 진입한다.
  - DB에서 Todo 모델에 작성된 전체 데이터를 조회하는 ORM을 작성한다.
  - 출력 결과를 확인하고 'exit()' 명령어를 통해 shell을 종료한다.
```
In [1]: todo = Todo()

In [2]: todo
Out[2]: <Todo: Todo onject (Nonde)>

In [3]: Todo.objects.all()
Out[3]: <QuerySet [<Todo: Todo object (1)>]>

In [4]: exit()
```
---
요구사항을 천천이 따라갑니다.  
`shell_plus`를 이용하여 `ORM`을 작성합니다.
<div style="text-align: right">20240325</div>