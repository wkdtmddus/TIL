# Generated by Django 4.2.9 on 2024-03-27 06:12

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('todos', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='todo',
            name='created_at',
            field=models.DateField(auto_now=True),
        ),
    ]
