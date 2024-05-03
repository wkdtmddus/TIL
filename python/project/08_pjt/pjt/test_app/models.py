from django.db import models

# Create your models here.
class ModelTestData(models.Model):
    name = models.CharField(max_length=50, null=True)
    age = models.IntegerField(null=True)
    sex = models.CharField(max_length=50, null=True)
    job = models.CharField(max_length=50, null=True)
    liveplace = models.CharField(max_length=50, null=True)