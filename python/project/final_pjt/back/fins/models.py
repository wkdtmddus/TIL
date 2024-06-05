from django.db import models

# Create your models here.
class DepositProduct(models.Model):
    # 금융 상품 코드
    fin_prdt_cd = models.TextField(unique=True)
    # 금융 회사명
    kor_co_nm = models.TextField(null=True)
    # 금융 상품명
    fin_prdt_nm = models.TextField(null=True)
    # 금융 상품 설명
    etc_note = models.TextField(null=True)
    # 가입 제한(1: 제한없음, 2: 서민전용, 3: 일부제한)
    join_deny = models.IntegerField(null=True)
    # 가입 대상
    join_member = models.TextField(null=True)
    # 가입 방법
    join_way = models.TextField(null=True)
    # 우대 조건
    spcl_cnd = models.TextField(null=True)

class DepositOption(models.Model):
    # # 외래 키
    product = models.ForeignKey(DepositProduct, on_delete=models.CASCADE)
    # 금융 상품 코드
    fin_prdt_cd = models.TextField(null=True)
    # 저축금리 유형명
    intr_rate_type_nm = models.CharField(max_length=100)
    # 저축금리
    intr_rate = models.FloatField(null=True)
    # 최고우대금리
    intr_rate2 = models.FloatField(null=True)
    # 저축기단(단위: 월)
    save_trm = models.IntegerField(null=True)