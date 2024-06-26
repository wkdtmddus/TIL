# Generated by Django 4.2.8 on 2024-05-22 04:29

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='DepositProduct',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('fin_prdt_cd', models.TextField(unique=True)),
                ('kor_co_nm', models.TextField(null=True)),
                ('fin_prdt_nm', models.TextField(null=True)),
                ('etc_note', models.TextField(null=True)),
                ('join_deny', models.IntegerField(null=True)),
                ('join_member', models.TextField(null=True)),
                ('join_way', models.TextField(null=True)),
                ('spcl_cnd', models.TextField(null=True)),
            ],
        ),
        migrations.CreateModel(
            name='DepositOption',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('fin_prdt_cd', models.TextField(null=True)),
                ('intr_rate_type_nm', models.CharField(max_length=100)),
                ('intr_rate', models.FloatField(null=True)),
                ('intr_rate2', models.FloatField(null=True)),
                ('save_trm', models.IntegerField(null=True)),
                ('product', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='fins.depositproduct')),
            ],
        ),
    ]
