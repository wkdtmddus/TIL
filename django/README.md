### 기초 설정
1. 가상 환경 venv 생성
  ```
  $ python -m venv venv
  ```
2. 가상 환경 활성화
  ```
  $ source venv/Scripts/activate
  ```
3. 환경에 설치된 패키지 목록 확인
  ```
  $ pip list
  ```
4. Django 설치
  ```
  $ pip install django
  ```
5. 의존성 패키지 목록 생성
  ```
  $ pip freeze > requirements.txt
  ```
---
### 기타 명령어
- 패키지 목록 기반으로 설치
  ```
  $ pip install -r requirements.txt
  ```
- Django 프로젝트 생성
  ```
  $ django-admin startproject firstpjt .
  ```
- Django 서버 실행
  ```
  $ python manage.py runserver
  ```
- 앱 생성
  - 앱의 이름은 `복수형`
  ```
  $ python manage.py startapp articles
  ```
- 관리자 계정
  ```
  $ python manage.py createsuperuser
  ```
---
### 모델 명령어(데이터베이스 관련)
- model class를 기반으로 최종 설계도(migration) 작성
  ```
  $ python manage.py makemigrations
  ```
  - 이미 기존 테이블이 존재 할 때
    ```
    # 현재 대화를 유지하면서 직접 기본 값을 입력하는 방법
    1) Provide a one-off default now which will be set on all existing rows
    # 현재 대화에서 나간 후 models.py에 기본값 관련 설정을 하는 방법
    2) Quit and manually define a default value in models.py.
    ```
- 최종 설계도를 DB에 전달하여 반영
  ```
  $ python manage.py migrate
  ```
- migrations 파일들이 migrate 됐는지 확인하는 명령어(`X` 표시가 있으면 완료)
  ```
  $ python manage.py showmigrations
  ```
- 해당 migrations 파일이 SQL 언어로 어떻게 번역되어 DB에 전달되는지 확인
  ```
  $ python manage.py sqlmigrate articles 0001
  ```
---
### ORM & QuerySet API
##### Aricle.objects.all()
##### Model class/Manager/Querset API
- QuerySet API 사전 준비
  - 외부 라이브러리 설치 및 설정
```
$ pip install ipython
$ pip install django-extensions
```
```
# settings.py
INSTALLED_APPS = [
  'articles',
  'django_extensions',
  ...,
]
```
```
$ pip freeze > requirements.txt
```
- Django shell 실행
```
$ python manage.py shell_plus
```