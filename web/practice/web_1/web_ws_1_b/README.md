### 명함 스타일 적용하기1 Lv2
목표  
- 학습 주제
  - HTML 기초
  - HTML Tag의 이해와 활용
  - CSS 기초
- 학습 목표
  - HTML의 기본 마크업을 이해하고 사용할 수 있다.
  - HTML&CSS를 이해하고 명세에 맞춰 코드를 작성한다.
- 학습 개념
  - 클래스 선택자: `.classname`
    - 주어진 class 특성을 가진 모든 요소를 선택합니다.
    - 구문: .classname 예제: .index는 'index'클래스를 가진 모든 요소와 일치합니다.
  - border
    - CSS border 단축 속성은 요소의 테두리를 설정합니다.
    - border-width, border-style, border-color의 값을 설정합니다.
  - margin
    - margin CSS 속성은 요소의 네 방향 바깥 여백 영역을 설정합니다.
    - margin-top, margin-right, margin-bottom, margin-left의 단축 속성입니다.
- 학습 방향
  - HTML에서 사용할 수 있는 요소들을 활용하여 문서를 구성한다.
  - 적절한 마크업을 사용하였을 때 얻을 수 있는 이점을 학습한다.
  - mdn 문서를 활용하여 CSS에서 활용 가능한 다양한 속성을 학습한다.
---
문제  
주어진 마크업에 스타일을 추가 적용하고자 한다.  
명함의 틀이 될 수 있도록 테두리를 생성하고, 좌우에 여백을 둘 수 있도록 수정한다.  
아래 요구사항에 맞춰 필요한 CSS 속성을 부여한 뒤 결과를 확인하시오.  

요구사항  
- style tag를 활용한다.
- 1단계에서 작성한 요소들을 하나의 div 하위에 포함되는 관계를 갖도록 수정한다.
  - 최상위 요소인 div
    - card-box 클래스를 가진다.
    - 가로 400px, 세로 200px
    - 1px의 검은색 solid 테두리를 가진다
  - y축 margin은 0, x축 자동 margin을 갖는다.
- 실행 결과
```
|----------------------|
|   |---------------|  |
|   |---------|     |  |
|   |         |     |  |
|   |         |     |  |
|   |---------|     |  |
|   |홍길동          |  |
|   |example        |  |
|   |---------------|  |
|    주소               |
|    02-111-1111       |
|----------------------|
```
---
주어진 요구사항에 맞게 연습하기.
<div style="text-align: right">20240306</div>