# 아래 함수를 수정하시오.
def even_elements(my_list):
    new_list = []
    for item in range(len(my_list)):
        pp = my_list.pop()
        if pp % 2 == 0:
            new_list.extend([pp])
    new_list.sort()
    return new_list


my_list = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
result = even_elements(my_list)
print(result)