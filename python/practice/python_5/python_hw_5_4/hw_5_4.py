# 아래 함수를 수정하시오.
def find_min_max(lst):
    lst.sort()
    min = lst[0]
    max = lst[-1]
    result = (min, max)
    return result


result = find_min_max([3, 1, 7, 2, 5])
print(result)  # (1, 7)