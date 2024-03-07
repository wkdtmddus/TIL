### Flex Box 기본 구성 Lv2
목표  
  - 학습 주제
    - CSS Display & Position & Combinator
    - Felx Layout
  - 학습 목표
    - CSS Box model(content, padding, border, margin)을 이해한다.
    - CSS Display를 활용해 Layout Positioning을 이해하고 연습한다.
  - 학습 개념
    - 클래스 선택자: '.classname'
      - 주어진 class 특성을 가진 모든 요소를 선택합니다.
      - 구문: .classname 예제: .index는 'index'클래스를 가진 모든 요소와 일치합니다.
    - flex
      - flex CSS 속성은 하나의 플렉스 아이템이 자신의 컨테이너가 차지하는 공간에 맞추기 위해 크기를 키우거나 줄이는 방법을 설정하는 속성입니다.
      - flex는 flex-grow, glex-shrink, flex-basis의 단축 속성입니다.
    - justify-content
      - CSS의 justify-content 속성은 브라우저가 콘텐츠 항목 사이와 주변의 공간을 플렉스 컨테이너에서는 main-axis를 기준으로 정렬합니다.
      - 그리고 그리드 컨테이너에서는 인라인 축을 기준으로 어떻게 정렬할 것인지를 정의합니다.
  - 학습 방향
    - HTML에서 사용할 수 있는 요소들을 활용하여 문서를 구성한다.
    - position과 display:flex를 사용하였을 때 각각 얻을 수 있는 장단점을 구별하며 학습한다.
    - mdn 문서를 활용하여 CSS에서 활용할 수 있는 다양한 속성을 학습한다.
---
문제  
주어진 스켈레톤 코드에 Flexbox를 적용하여 레이아웃을 구성하시오.  

요구사항  
- div의 크기를 직접 변경하지 않는다.
- 수직 방향, 수평 방향 모두 가운데 정렬이 되어야 한다.
---
`display: flex`를 사용하여, `justify-content`와 `align-items`를 이용하여 수직, 수평 방향으로 정렬을 합니다.
<div style="text-align: right">20240307</div>