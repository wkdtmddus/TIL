# 아래 함수를 수정하시오.
def intersection_sets(set1, set2):
    # return set1 & set2

    answer = set()

    for i in set2:
        if i in set1:
            answer.add(i)
    return answer
    

result = intersection_sets({1, 2, 3}, {3, 4, 5})
print(result)