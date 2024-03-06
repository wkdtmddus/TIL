### CSS 명시도 실습 예제 Lv3
목표  
- CSS 우선순위에 대해 이해한다.
- CSS 선택자 사용법에 대해 이해한다.
---
문제  
다음과 같은 HTML 마크업이 있을 때, 주어진 스켈레톤 코드를 활용하여 예시 화면처럼 되도록 CSS 코드를 작성하시오.

요구사항  
- 참고. 다음은 CSS 명시도가 높은 순으로 나열한 예시이다.
  1) !important
  2) Inline style
  3) id 선택자
  4) class 선택자
  5) 요소 선택자
  6) 소스 순서
- 사용된 색상은 darkviolet, orange, blue, green, red, yellow이다.
- 실행 결과
```
1 orange
2 blue
3 green
4 green
5 red
6 darkviolet
7 yellow
8 darkviolet
```
---
참고에 주어진 `CSS 명시도 순서`를 잘 기억하자.
<div style="text-align: right">20240306</div>