from django.http import JsonResponse
from rest_framework.decorators import api_view
import random
import pandas as pd
from django.http import HttpResponse
import csv
import io
from .models import ModelTestData
from django.shortcuts import render
import os
from django.conf import settings


array_length = 1000
random_range = 5000

@api_view(['GET'])
def bubble_sort(request):
    li = []
    for i in range(array_length):
        li.append(random.choice(range(1, random_range)))
    for i in range(len(li) - 1, 0, -1):
        for j in range(i):
            if li[j] < li[j + 1]:
                li[j], li[j + 1] = li[j + 1], li[j]
    context = {
      'top': li[0]
    }
    return JsonResponse(context)

@api_view(['GET'])
def normal_sort(request):
    li = []
    for i in range(array_length):
        li.append(random.choice(range(1, random_range)))
    li.sort(reverse=True)
    context = {
        'top': li[0]
    }
    return JsonResponse(context)

from queue import PriorityQueue

@api_view(['GET'])
def priority_queue(request):
    pq = PriorityQueue()
    for i in range(array_length):
        pq.put(-random.choice(range(1, random_range)))
    context = {
        'top': -pq.get()
    }
    return JsonResponse(context)

@api_view(['GET'])
def dataframe(request):
    file_path = os.path.join(settings.MEDIA_ROOT, 'uploads/test_data.CSV')
    df = pd.read_csv(file_path, encoding='cp949')
    df['이름'].fillna('NULL', inplace=True)
    df['나이'].fillna(0, inplace=True)
    df['성별'].fillna('NULL', inplace=True)
    df['직업'].fillna('NULL', inplace=True)
    df['사는곳'].fillna('NULL', inplace=True)
    # print(df['나이'])
    # df['나이']
    cnt = 0
    people_cnt = 0
    list_age = []
    for age in df['나이']:
        # print(age)
        if age == 0:
            continue
        cnt += age
        list_age.append(age)
        people_cnt += 1
    list_age.sort()
    # print(list_age)
    # print(cnt)
    # print(people_cnt)
    age_average = cnt // people_cnt
    # print(age_average)
    result = []
    while len(result) < 10:
        standard = 1000
        standard_val = 0
        for la in list_age:
            if standard > abs(la-age_average):
                standard = abs(la-age_average)
                standard_val = la
        result.append(standard_val)
        list_age.remove(standard_val)
    # print(result)
    selected_df = pd.DataFrame()  # 빈 데이터프레임 생성
    added_ages = set()  # 추가된 나이를 기록하는 세트
    for age in result:
        if len(selected_df) >= 10:
            break  # 이미 10명을 추가했다면 루프를 종료
        if age not in added_ages:
            age_df = df[df['나이'] == age]
            remaining_spots = 10 - len(selected_df)  # 남은 자리 계산
            selected_df = pd.concat([selected_df, age_df.head(remaining_spots)])
            added_ages.add(age)  # 이 나이를 추가했다는 것을 기록
    # print(selected_df)

    # list_val_plus = []
    # list_val_minus = []
    # for age2 in df['나이']:
    #     if age2 == "NULL":
    #         continue
    #     if age2-age_average < 0:
    #         list_val_minus.append(age2-age_average)
    #     else:
    #         list_val_plus.append(age2-age_average)
    # list_val_minus.sort(reverse=True)
    # print(list_val_minus)
    # list_val_plus.sort()
    # print(list_val_plus)

    data = selected_df.to_dict('records')
    context = {
        'data': data
    }
    return JsonResponse(context)



@api_view(['GET'])
def convert_csv_to_dataframe(request):
    # Read CSV file into a DataFrame
    df = pd.read_csv('data/test_data.CSV', encoding='cp949')

    # Convert DataFrame to a list of dictionaries
    data = df.to_dict('records')

    print(data)

    # Return response in JSON format
    return JsonResponse({'dat': data}, json_dumps_params={'ensure_ascii': False})

@api_view(['GET'])
def get_data_nan(request):
    arr = np.loadtxt('data/test_data.CSV', delimiter=",", encoding='cp949', dtype=str)
    columns = arr[0]
    arr = np.delete(arr, 0, 0)
    df = pd.DataFrame(arr, columns=columns)

    df.replace('', 'NULL', inplace=True)

    print(df)

    # records: 리스트 원소를 각각 하나의 레코드로 만들기 위해 주는 옵션
    data = df.to_dict('records')

    # JSON 형태로 응답합니다.
    return JsonResponse({ 'dat': data })


@api_view(['GET'])
def get_data_avg(request):
    arr = np.loadtxt('data/test_data.CSV', delimiter=",", encoding='cp949', dtype=str)
    columns = arr[0]
    arr = np.delete(arr, 0, 0)
    df = pd.DataFrame(arr, columns=columns)

    # '나이' 필드를 숫자형으로 변환합니다.
    df['나이'] = pd.to_numeric(df['나이'])
    # '나이' 필드의 평균값을 구합니다.
    mean_value = df['나이'].mean()
    # '나이' 필드와 평균값의 차이를 구한 후, 절댓값을 취하여 'diff' 필드를 새로 추가합니다.
    df['diff'] = abs(df['나이'] - mean_value)
    # 'diff' 필드를 기준으로 가장 작은 10개의 행을 선택합니다.
    similar_rows = df.nsmallest(10, 'diff')
    # 선택된 10개의 행을 dictionary 형태로 변환합니다.
    data = similar_rows.to_dict('records')

    # JSON 형태로 응답합니다.
    return JsonResponse({ 'dat': data })