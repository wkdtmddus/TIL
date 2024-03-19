# 백트래킹
# 완전탐색 + 가지치기
# 가능성이 없는(볼 필요 없는) 경우의 수를 제거하는 기법

# 중복된 순열
# 1~3까지 숫자 배열
# 111, 112, 113, 121, 122, 123, ..., 332, 333

# 재귀함수는 특정 시점으로 돌아오는게 핵심
# 재귀함수 팁
# 파라미터: 바로 작성x
# 구조를 먼저 잡으면 자연스럽게 필요한 변수들이 보인다.

arr = [i for i in range(1, 4)]
path = [0]*3

# 순열
# 123, 132, 213, 231, 312, 321
# 조건: 숫자는 한 번만 사용

def dfs(level):
    # 기저조건
    # 이 문제에서는 3개를 뽑았을 때까지 반복
    if level == 3:
        print(path)
        return
    
    # 들어가기전
    # 다음 재귀호출
    # - 다음에 갈 수 있는 곳들은 어디인가?
    # - 이 문제에서는 1, 2, 3 세 가지(arr의 길이만큼) 경우의 수가 존재
    
    #기본 코드
    # path[level] = arr[1]
    # dfs(level+1)
    # path[level] = arr[2]
    # dfs(level+1)
    # path[level] = arr[3]
    # dfs(level+1)

    # 갈 수 있는 후보군
    for i in range(len(arr)):
        # 여기는 못 감(가지치기)
        # 백트래킹 코드 팁
        # 갈 수 없는 경우를 활용
        if arr[i] in path:
            continue

        path[level] = arr[i]
        dfs(level+1)

        # 갔다와서 할 로직
        # 기존 방문 초기화
        path[level] = 0

dfs(0)