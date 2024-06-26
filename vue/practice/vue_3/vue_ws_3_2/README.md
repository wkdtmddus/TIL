### 전시 정보 페이지 만들기 - 조건부 데이터 입력 Lv2
목표  
- Vue.js를 활용하여 데이터를 객체로 구조화하고, 필터링하여 원하는 정보를 동적으로 화면에 표시하는 방법을 습득한다.
- Vue.js의 computed 속성을 사용하여 데이터를 조건에 따라 필터링하는 기능을 구현한다.
- HTML 템플릿 내에서 Vue.js의 양방향 데이터 바인딩을 활용하여 체크박스와 상태 변수를 연동한다.
---
문제  
전시 정보별 조건에 따라 다른 내용을 보여주고자 한다.  
1에서 작성한 코드에서 진행하거나 주어진 코드를 활용하여 요구사항을 만족하는 코드를 작성하시오.  

요구사항
1. 주어진 전시 정보를 객체에 담고, 모든 객체를 하나의 배열에 할당하시오.
2. 전시 정보가 전시 중인 경우만 filtering한 배열을 반환하는 isActiveExhibition computed 함수를 작성한다.
3. 전시 중인 정보만 볼 것인지 체크하는 변수 isActive를 작성한다.
  - checkBox의 값과 양방향 바인딩 되어야 한다.
  - isActive가 True인 경우, isActiveExhibition 배열을 화면에 렌더링한다.
  - 아닌경우, 모든 객체 정보를 담고 있는 배열을 화면에 렌더링한다.
---
`v-model`을 사용하여 양방향으로 설정한다.  
`computed`를 사용하여 변화를 감지하고 조건에 맞게 배열을 구성한다.  
<div style="text-align: right">20240430</div>