food_list = [
    {
        '종류': '한식',
        '이름': '잡채'
    },
    {
        '종류': '채소',
        '이름': '토마토'
    },
    {
        '종류': '중식',
        '이름': '자장면'
    },
]

# 아래에 코드를 작성하시오.

# for food in food_list:
#     if food['이름'] == '토마토':
#         food['종류'] = '과일'
#     elif food['이름'] == '자장면':
#         print('자장면엔 고춧가루지')

#     print(f'{food["이름"]} 은/는 {food["종류"]} (이)다.')

# print(food_list)


num = 0
while num < len(food_list):
    food = food_list[num]

    if food['이름'] == '토마토':
        food['종류'] = '과일'
    elif food['이름'] == '자장면':
        print('자장면엔 고춧가루지')

    print(f'{food["이름"]} 은/는 {food["종류"]} (이)다.')

    num += 1

print(food_list)