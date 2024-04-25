### 발표 순서 정하기 Lv5
목표  
- Vue3 CDN 및 lodash CDN을 로드하고 사용할 수 있다. 
- Vue3의 ref 함수를 사용하여 반응성을 가진 데이터를 정의하고 변경할 수 있다. 
- lodash를 사용하여 배열 요소를 섞는 shuffle 함수를 구현할 수 있다. 
- Vue 템플릿을 사용하여 데이터를 렌더링하고 인덱스를 활용하여 특정 요소를 추출하여 출력할 수 있다.
---
문제  
Vue3과 lodash를 사용하여 발표 순서를 정하는 페이지를 만들고자 한다.  
주어진 코드를 활용하여 요구 사항을 만족하는 코드를 작성하시오.  

요구사항
- Vue3 CDN을 사용한다.
- lodash CDN을 사용한다.
- lodash 공식 문서 : https://lodash.com/
1. 아이디가 app인 container 요소에 새로운 앱 인스턴스를 mount 하시오.
2. 주석으로 주어지는 students 배열을 앱 인스턴스 내부에 정의한다.
  - 단, ref 함수를 사용하여 반응성을 가지는 참조 변수로 만들어야 한다.
3. lodash를 활용하여, students 배열의 요소를 무작위로 섞은 값을 다시 students 배열에 할당하는 shuffle 함수를 정의하시오.
  - students의 value가 변경 되어야 함에 주의한다.
4. button을 클릭하면 shuffle 함수가 실행되도록 코드를 작성하시오.
5. template에 적절한 데이터가 렌더링 될 수 있도록 코드를 수정한다.
  - 전체 학생 목록을 출력하시오.
  - 무작위로 섞인 students 요소를 1~3번째와 마지막 요소만 각각 출력한다.
  - 이때, 인덱스 접근을 활용하여 렌더링한다.
---
`lodash`를 이용하여 적절한 값을 얻을 수 있습니다.  
`_.sampleSize(arr, n)`는 배열 arr의 값들 중, 랜덤하게 n개의 값을 반환합니다.
<div style="text-align: right">20240425</div>