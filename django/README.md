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