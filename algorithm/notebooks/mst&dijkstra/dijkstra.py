from heapq import heappush, heappop

INF = int(21e8)

V, E = map(int, input().split())

# 시작 노드 번호
start = 0

# 인접 리스트
graph = [[] for _ in range(V)]

# 누적 거리를 저장할 변수
distance = [INF]*V

# 간선 정보 저장
for _ in range(E):
    s, e, w = map(int, input().split())
    graph[s].append([w, e])

def djikstra(start):
    pq = []

    # weight, node 한 번에 저장
    heappush(pq, (0, start))
    # 시작노드 초기화
    distance[start] = 0

    while pq:
        # 최단거리 노드에 대한 정보
        dist, now = heappop(pq)

        # pq의 특성 때문에 더 긴거리가 미리 저장되어 있음
        # now가 이미 더 짧은 거리로 온 적이 있다면
        if distance[now] < dist:
            continue

        # now에서 인접한 다른 노드 확인
        for to in graph[now]:
            next_dist = to[0]
            next_node = to[1]

            # 누적 거리 계산
            new_dist = dist + next_dist

            # 이미 더 짧은 거리로 간 경우
            if new_dist >= distance[next_dist]:
                continue
            
            # 누적거리를 최단거리로 갱신
            distance[next_node] = new_dist
            # next_node의 인접 노드들을 추가
            heappush(pq, (new_dist, next_node))