# 아래 함수를 수정하시오.
def union_sets(set1, set2):
    # return set1 | set2
    
    set_lst = []
    for i in set1:
        if i in set_lst:
            continue
        set_lst.append(i)
    for i in set2:
        if i in set_lst:
            continue
        set_lst.append(i)
    new_set = set(set_lst)
    return new_set


result = union_sets({1, 2, 3}, {3, 4, 5})
print(result)