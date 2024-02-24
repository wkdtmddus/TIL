############## 주의 ##############
# 입력을 받기위한 input 함수는 절대 사용하지 않습니다.
def calc_total(cart_list):
    # 총 금액 변수를 반듭니다.
    costs = 0
    # 아이템들의 키들을 추출합니다.
    item_keys = list(item_dict.keys())
    # 카트 리스트를 순환합니다.
    for item in cart_list:
        # 키를 순환합니다.
        for key in item_keys:
            # 만약 아이템과 키가 같으면, 아이템의 금액을 더합니다.
            if item == key:
                cost = item_dict.get(key)
                costs = costs + cost
    return costs
    # 여기에 코드를 작성하여 함수를 완성합니다.


# 추가 테스트를 위한 코드 작성 가능
# 예) print(함수명(인자))

#####################################################
# 아래 코드를 삭제하는 경우 
# 모든 책임은 삭제한 본인에게 있습니다. 
############## 테스트 코드 삭제 금지 #################
item_dict= {
    'apple_pie': 2500,    'banana_milk': 1800,     'coconut_milk': 2000,
    'egg_tart': 2300,    'fruits_cocktail': 3000,    'gum': 1200,
    'hotdog': 2500,    'ice_cream': 3200,    'juice': 2800,
    'keyboard': 35000,  'lotion': 8700
}

cart1 = ['apple_pie', 'ice_cream', 'juice', 'gum', 'banana_milk']
print(calc_total(cart1))    # 11500
cart2 = ['coconut_milk', 'egg_tart', 'fruits_cocktail', 'lotion', 'keyboard']
print(calc_total(cart2))    # 51000
#####################################################