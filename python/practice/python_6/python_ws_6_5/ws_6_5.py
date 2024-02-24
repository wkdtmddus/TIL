# 아래 함수를 수정하시오.
def difference_sets(set1, set2):
    # return set1 - set2

    for n in set2:
        if n in set1:
            set1.remove(n)
    return set1


result = difference_sets({1, 2, 3}, {3, 4, 5})
print(result)