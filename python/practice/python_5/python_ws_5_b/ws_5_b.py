data_1 = 'qweqwYadnOyjnsaU4trwg asjnaAn245krRmkfE 42grTasdnHasdnvEasdn asdevadnBasdanEsdkqefqefvaSasdqaeeqqvedwt5hfbsdT24tewfd'
'''
예시코드
arr = [1, 2, 3, 4, 5]
for num in arr:
    print(num, end='')
출력결과 : 12345
'''
# 아래에 코드를 작성하시오.
for data in data_1:
    if data.isupper() or data == ' ':
        print(data, end='')        


print()
data_2 = '걉파반샤팝다푸거맥파바자들퍼바배들밥샵파누타히매니배사바파힘다브사부힙헤베내테치대내'
arr = []
# 아래에 코드를 작성하시오.

index1 = data_2.find('내')
index2 = data_2.find('힘')
index3 = data_2.find('들')
index4 = data_2.find('다')

arr.append(index1)
arr.append(index2)
arr.append(index3)
arr.append(index4)
print(arr)

arr.sort()
print(arr)
for ar in arr:
    print(data_2[ar], end='')