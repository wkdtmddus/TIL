# 아래 함수를 수정하시오.
def add_item_to_dict(dct, key, val):
    # new_dict = dct.copy()
    # dict1 = {key : val}
    # new_dict.update(dict1)
    # return new_dict


    dct[key] = val
    return dct


my_dict = {'name': 'Alice', 'age': 25}
result = add_item_to_dict(my_dict, 'country', 'USA')
print(result)