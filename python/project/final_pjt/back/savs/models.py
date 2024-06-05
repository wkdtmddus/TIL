from django.db import models

# Create your models here.
class SavingProduct(models.Model):
    # 금융 상품 코드
    fin_prdt_cd = models.TextField(unique=True)
    # 금융 회사명
    kor_co_nm = models.TextField(null=True)
    # 금융 상품명
    fin_prdt_nm = models.TextField(null=True)
    # 기타 유의사항
    etc_note = models.TextField(null=True)
    # 가입 제한(1: 제한없음, 2: 서민전용, 3: 일부제한)
    join_deny = models.IntegerField(null=True)
    # 가입 대상
    join_member = models.TextField(null=True)
    # 가입 방법
    join_way = models.TextField(null=True)
    # 우대 조건
    spcl_cnd = models.TextField(null=True)
    # 공시 제출월
    dcls_month = models.TextField(null=True)
    # 최고한도
    max_limit = models.IntegerField(null=True)
    # 공시 시작일
    dcls_strt_day = models.TextField(null=True)
    # 공시 종료일
    dcls_end_day = models.TextField(null=True)
    # 만기 후 이자율
    mtrt_int = models.TextField(null=True)


class SavingOption(models.Model):
    # # 외래 키
    product = models.ForeignKey(SavingProduct, on_delete=models.CASCADE)
    # 금융 상품 코드
    fin_prdt_cd = models.TextField(null=True)
    # 저축금리 유형명
    intr_rate_type_nm = models.CharField(max_length=100)
    # 적립 유형명
    rsrv_type_nm = models.CharField(max_length=100)
    # 저축금리
    intr_rate = models.FloatField(null=True)
    # 최고우대금리
    intr_rate2 = models.FloatField(null=True)
    # 저축기단(단위: 월)
    save_trm = models.IntegerField(null=True)