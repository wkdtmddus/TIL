### input Event - Bad Word Filter Lv1
목표  
- 학습 주제
  - 자바스크립트 이벤트
- 학습 목표
  - 브라우저에서 이벤트가 어떻게 작동하는지 이해할 수 있다.
  - 주어진 요구사항에 따라 이벤트 핸들러를 작성하고 활용할 수 있다.
- 문제의도
  - 자바스크립트로 이벤트를 다루는 기본적인 이해와 활용 능력을 평가한다.
  - 이벤트 핸들러를 등록하며 DOM 조작하는 방법에 대한 능력을 검증한다.
---
문제  
현재 코드에서는 input#userInput에 입력한 내용이 그대로 span#output에 출력된다.  
실행 결과를 참고하여 badWords에 포함된 단어가 사용자 입력에 포함되어 있을 경우,  
`span#output에서 해당 단어를 **로 바꿔 출력하도록 filterMessage 함수를 완성하시오.`  

요구사항
- badWords 배열에 있는 단어가 사용자 입력에 포함되어 있으면 교체 한다.
  - forEach Array Helper Method를 활용하여 badWords배열을 순회한다.
- 같은 badWord가 여러번 등장해도 모두 교체되어야 한다.
---
`forEach`를 사용하여 배열을 순회하며, input에 들어온 문자열을 확인합니다.  
`replace`를 사용하여 해당되는 문자를 교체합니다.  
`replaceAll`을 사용하여 해당되는 문자열의 모든 문자를 교체합니다.
<div style="text-align: right">20240422</div>