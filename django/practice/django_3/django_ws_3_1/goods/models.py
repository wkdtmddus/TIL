from django.db import models

# Create your models here.
class good(models.Model):
    name = models.CharField(max_length=100)
    description = models.TextField()
    price = models.IntegerField()
    in_published = models.BooleanField()