from django.urls import path
from . import views


urlpatterns = [
    path('saving_products/', views.saving_products), # 전체 적금 상품 목록 출력 & 삽입
    path('saving_product_options/<str:fin_prdt_cd>/', views.saving_product_options), # 특정 적금 옵션 리스트
    path('saving_product_option/<int:option_pk>/', views.option_detail),
]