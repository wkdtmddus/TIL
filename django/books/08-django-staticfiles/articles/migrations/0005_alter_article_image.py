# Generated by Django 4.2.11 on 2024-03-28 01:39

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ("articles", "0004_alter_article_image"),
    ]

    operations = [
        migrations.AlterField(
            model_name="article",
            name="image",
            field=models.ImageField(blank=True, upload_to="%Y/%m/%d"),
        ),
    ]
