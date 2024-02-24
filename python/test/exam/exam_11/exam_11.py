############## 주의 ##############
# 입력을 받기위한 input 함수는 절대 사용하지 않습니다.
def get_final_position(N, matrix, move_list):
    for i in range(N):
        for j in range(N):
            if matrix[i][j] == 1:
                a = i
                b = j
    
    position = []
    position.append(a)
    position.append(b)

    for q in move_list:
        if q == 0:
            move = [-1, 0]
        elif q == 1:
            move = [1, 0]
        elif q == 2:
            move = [0, -1]
        elif q == 3:
            move = [0, 1]    
    
        for e in range(len(position)):
            for w in position:
                if w < 0 or w >= N:
                    return None
            position[e] += move[e]
    
    return position
    # 여기에 코드를 작성하여 함수를 완성합니다.
    

# 추가 테스트를 위한 코드 작성 가능
# 예) print(함수명(인자))

#####################################################
# 아래 코드를 삭제하는 경우 
# 모든 책임은 삭제한 본인에게 있습니다. 
############## 테스트 코드 삭제 금지 #################
N = 3
matrix = [
    [1, 0, 0],
    [0, 0, 0],
    [0, 0, 0],
] 
moves1 = [1, 1, 3]
print(get_final_position(N, matrix, moves1)) # [2, 1]

moves2 = [1, 2, 3, 3]
print(get_final_position(N, matrix, moves2)) # None
#####################################################