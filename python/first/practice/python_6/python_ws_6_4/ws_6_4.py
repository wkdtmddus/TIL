# 아래 함수를 수정하시오.
def get_keys_from_dict(dic):
    # return list(dic.keys())

    key_list = []

    for key in dic:
        key_list.append(key)
    return key_list

my_dict = {'name': 'Alice', 'age': 25}
result = get_keys_from_dict(my_dict)
print(result)  # ['name', 'age']