# 아래 함수를 수정하시오.
def remove_duplicates_to_set(lst):
    # set1 = set(lst)
    # return set1

    answer = set()
    for n in lst:
        if n not in answer:
            answer.add(n)
    return answer

result = remove_duplicates_to_set([1, 2, 2, 3, 4, 4, 5])
print(result)