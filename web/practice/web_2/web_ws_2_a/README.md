### 위로가기 버튼 만들기 Lv1
목표  
  - 학습 주제
    - HTML 기초
    - CSS Display & Position & Combinator
  - 학습 목표
    - CSS Box model(content, padding, border, margin)을 이해한다.
    - CSS Display를 활용해 Layout Positioning을 이해하고 연습한다.
  - 학습 개념
    - 클래스 선택자: '.classname'
      - 주어진 class 특성을 가진 모든 요소를 선택합니다.
      - 구문: .classname 예제: .index는 'index'클래스를 가진 모든 요소와 일치합니다.
    - position
      - CSS text-align 속성은 블록 요소나 표의 칸 상자의 가로 정렬을 설정합니다.
    - margin
      - CSS position 속성은 문서상에 요소를 배치하는 방법을 지정합니다.
      - top, right, bottom, left 속성이 요소를 배치할 최종 위치를 결정합니다.
    - background-color
      - CSS background-color 속성은 요소의 배경색을 지정합니다.
  - 학습 방향
    - HTML에서 사용할 수 있는 요소들을 활용하여 문서를 구성한다.
    - 적절한 마크업을 사용하였을 때 얻을 수 있는 이점을 학습한다.
    - mdn 문서를 활용하여 CSS에서 활용할 수 있는 다양한 속성을 학습한다.
---
문제  
CSS의 position을 연습하고자 한다.  
주어진 스켈레톤 코드를 활용하여 요구사항에 맞는 스타일 속성을 작성하시오.  

요구사항  
- 주어진 스켈레톤 코드의 container div는 브라우저에 스크롤이 생성되도록 해주는 역할이므로 수정하지 않는다.
- 클래스 top-btn 버튼
  - 버튼은 사용자가 스크롤을 내릴 때도 고정된 위치를 갖는다.
  - 버튼은 브라우저의 우측 하단에 위치한다.
  - 버튼의 높이는 40px를 부여한다.
  - 버튼은 둥근 테두리 속성을 갖는다.
- 완성된 버튼은 실제로 화면의 상단으로 이동하는 기능을 지원하지는 않는다.
---
`position`으로 화면에 고정시킬 수 있습니다. `radius`로 테두리를 둥글게 만들 수 있습니다.
<div style="text-align: right">20240307</div>