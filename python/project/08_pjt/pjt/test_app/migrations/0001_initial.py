# Generated by Django 4.2.6 on 2024-05-03 03:50

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='ModelTestData',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=50, null=True)),
                ('age', models.IntegerField(null=True)),
                ('sex', models.CharField(max_length=50, null=True)),
                ('job', models.CharField(max_length=50, null=True)),
                ('liveplace', models.CharField(max_length=50, null=True)),
            ],
        ),
    ]
