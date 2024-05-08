### 컴포넌트간 데이터 전송하기 2 Lv4
목표  
- Vite를 활용하여 Vue.js 프로젝트를 생성할 수 있다.
- 부모 컴포넌트에서 자식 컴포넌트로 데이터를 전송하고, 자식 컴포넌트에서 이벤트를 발생시킬 수 있다.
---
문제  
Vite를 활용하여 프로젝트를 생성하고 컴포넌트간의 데이터 전송을 연습하고자 한다.  
2단계에서 작성한 코드에서 이어서 진행하거나 주어진 코드를 활용하여 요구사항을 만족하는 코드를 작성하시오.  

요구사항
1. ParentPage에 자식 정보를 담을 children 배열을 작성한다.
  - 자식 정보
    - name: '김하나', age: 30, balance: 100000
    - name: '김두리', age: 20, balance: 10000
    - name: '김서이', age: 10, balance: 1000
2. children 배열을 사용하여 ChildPage 컴포넌트를 등록한다.
3. ChildPage는 ParentPage 컴포넌트로 넘겨받은 데이터를 렌더링한다.
  - 이름, 나이, 용돈을 각각 작성한다.
  - '용돈 더 주세요' 문구를 가진 button을 작성한다.
4. button을 클릭시, giveMeAllowance 이벤트를 발생시킨다.
  - ParentPage 컴포넌트는 해당 이벤트 발생시 updateBalance 함수를 실행한다.
  - updateBalance 함수는 객체를 인자로 받으며, 대상 객체의 balance를 1000 증가시킨다.
---
`defineEmits()`를 이용하여 상위 컴포넌트에 이벤트를 전달할 수 있습니다.
<div style="text-align: right">20240507</div>