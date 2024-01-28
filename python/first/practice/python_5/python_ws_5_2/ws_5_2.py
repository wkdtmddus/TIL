# 아래 함수를 수정하시오.
def remove_duplicates(lst):
    # new_lst = []
    # lst2 = set(lst)
    # for item in lst2:
    #     new_lst.append(item)
    # return new_lst

    new_lst = []
    for i in lst:
        if new_lst.count(i) == 0:
            new_lst.append(i)
    return new_lst

result = remove_duplicates([1, 2, 2, 3, 4, 4, 5])
print(result)