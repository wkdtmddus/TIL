### 문제 4
날씨정보를 제공해주는 서비스의 개발팀에서 데이터 분석을 담당하고 있다.  
인자로 날씨정보가 2차원 list로 전달되며, 내부 list는 첫 번째 값은 해당 일자의 최대 온도, 두 번째 값은 해당 일자의 최소 온도를 의미한다.  
개별 리스트로 저장된 최대, 최소 온도 정보를 maximum과 minimum을 key로 하고 각각의 온도 정보를 list로 모아 dictionary로 반환하는 함수 turn을 완성하시오.

---
최댓값과 최솟값을 저장하기위해 빈 리스트 두 개를 만듭니다.  
`for문`을 사용하여 주어진 리스트를 순환하며 인덱스 0과 1의 값을 리스트에 각각 저장합니다.  
빈 딕셔너리에 그 값을 알맞은 키의 이름을 입력하여 넣어줍니다.
<div style="text-align: right">20240126</div>