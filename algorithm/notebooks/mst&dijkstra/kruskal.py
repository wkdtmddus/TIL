# 1. 전체 그래프를 보고, 가중치가 제일 작은 간선부터 뽑기
#   -> 코드로 구현: 전체 간선 정보를 저장 + 가중치로 정렬

# 2. 방문처리
#   -> 이때, 싸이클이 발생하면 안 됨
#   -> 싸이클 여부: unifon-find 알고리즘이 활용

def find_set(x):
    if parents[x] == x:
        return x
    
    return find_set(parents[x])

def union(x, y):
    x = find_set(x)
    y = find_set(y)

    # 같은 집합이면 pass
    if x == y:
        return
    
    if x < y:
        parents[y] = x
    else:
        parents[x] = y

V, E = map(int, input().split())
# 간선 정보들을 모두 저장
edges = []
for _ in range(E):
    s, e, w = map(int, input().split())
    edges.append([s, e, w])

# 가중치를 기준으로 정렬
edges.sort(key=lambda x: x[2])

# 대표자 배열
parents = list(range(V))

# mst 완성 = 간선의 개수 V-1
cnt = 0

sum_weight = 0

# 간선들을 모두 확인
for s, e, w in edges:
    # 싸이클이 발생하면
    #   -> 이미 같은 집합에 속해 있다면 == 대표자가 같다면
    if find_set(s) == find_set(e):
        continue

    cnt += 1
    # 싸이클이 없다면, 방문처리
    union(s, e)
    sum_weight += w

    # mst 완성 = 간선의 개수 V-1
    if cnt == V-1:
        break
    
print(f'최소 비용 = {sum_weight}')