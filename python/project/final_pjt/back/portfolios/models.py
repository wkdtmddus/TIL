from django.db import models
from django.conf import settings

# Create your models here.
class Portfolio(models.Model):
    user = models.OneToOneField(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)
    bank = models.TextField()
    tendency = models.TextField()