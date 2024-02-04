### 문제 11
게임 캐틱터가 움직일 수 있는 범위가 있으며, 이 제한된 구역을 넘어가지 않도록 검사하는 함수를 만들려고 한다.  
캐릭터는 2차원 평면(N*N)에서 이동한다.(0<N<=100)  
2차원 평면(matrix)은 0과 1로 표현되어 있으며 0은 빈공간, 1은 현재 위치를 나타낸다.  
각 숫자별 이동 방향(M)은 0: 위, 1: 아래, 2: 왼쪽, 3: 오른쪽을 의미한다.(0<=M<=3)  
캐릭터의 이동은 이동 방향들의 리스트(move_list)로 주어진다.  
현재 위치를 찾은 후, 인자로 전달된 이동 리스트(move_list)에 따라 움직였을 때, 이동한 최종 위치를 리스트 형태로 반환하는 get_final_position 함수를 완성하시오.  
최종 위치는 [행, 열] 형태로 반환하며, 이동 중 캐릭터가 2차원 평면 밖을 넘어간다면 None을 반환한다.  

---
`이중for문`으로 matrix를 순환하여 값이 1인 현재 위치를 구합니다.  
move_list를 순환하여 이동값을 순환하며 위치를 이동시킵니다. 이 때, 표면을 벗어나는 값이 발견되면 None을 바로 반환합니다.
<div style="text-align: right">20240129</div>