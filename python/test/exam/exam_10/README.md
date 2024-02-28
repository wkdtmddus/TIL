### 문제 10
게임 캐릭터가 움직을 수 있는 범위가 있으며, 이 제한된 구역을 넘어가지 않도록 검사하는 함수를 만들려고 한다.  
캐릭터는 2차원 평면(N*N)에서 이동한다.(0<N<=100)  
사용자의 키 입력에 따라 움직일 수 있으며, 입력은 0부터 3까지의 정수 M으로 주어진다.(0<=M<=100)  
각 숫자별 이동 방향은 0: 위, 1: 아래, 2: 왼쪽, 3: 오른쪽을 의미한다.  
캐릭터의 현재 위치(current)는 튜플(row,col) 형태로 주어지며, row와 col은 각각 2차원 평면의 행과 열을 의미한다.(0<=row<100, 0<=col<100)  
현재 위치(current)에서 키 입력(M)으로 이동한 결과가 2차원 평면의 범위를 벗어난다면 False, 그렇지 않으면 True를 반환하는 하수 is_position_safe를 완성하시오.  

---
움직임에 따른 좌표변화를 리스트에 저장합니다.  
현재 위치를 리스트로 변환합니다.  
`for문`으로 움직인 후의 위치를 순환하여 위치의 값이 0보다 작거나 평면보다 클 경우 False를 반환합니다.
<div style="text-align: right">20240129</div>