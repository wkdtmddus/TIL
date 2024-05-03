##### 참고
- numpy
  - 수학 배열 연산을 위한 라이브러리
  - 파이썬보다 빠름
- pandas
  - numpy가 조금 불편하다면
  - 조작 + 분석
- matplotlib
  - 결과를 시각화(그래프)
### 목표
##### Django에서 데이터 사이언스 패키지 사용하기
##### 필수 라이브러리
- Django 4.2+
- Pandas
- Numpy
- Matplotlib
### Django Template 그래프 출력
##### View에서 Template으로 이미지 전달하기
- view에서 template으로 이미지 형식의 데이터를 직접 전달할 수 없습니다.
- `저장된 이미지의 경로를 전달`하여 template에서 출력해야 합니다.
- moatplotlib의 그래프를 버퍼에 이미지 형식으로 저장 후 저장된 경로를 전달합니다.
  - 버퍼(buffer): 임시로 데이터를 저장하는 공간
- Python `ByteIO` 클래스
  - 파이썬의 내장 모듈인 `io` 모듈에 포함된 클래스
  - 메모리 내에 데이터를 저장 및 조작할 수 있는 기능 제공