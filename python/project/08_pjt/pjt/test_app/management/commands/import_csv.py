# myapp/management/commands/import_csv.py

from django.core.management.base import BaseCommand
from test_app.models import ModelTestData
import csv
import os
from django.conf import settings

class Command(BaseCommand):
    help = 'Imports data from a specified CSV file into the Person model'

    def handle(self, *args, **options):
        file_path = os.path.join(settings.MEDIA_ROOT, 'uploads/test_data.CSV')
        with open(file_path, newline='', encoding='cp949') as csvfile:
            data_reader = csv.reader(csvfile, delimiter=',', quotechar='"')
            next(data_reader)  # 헤더 스킵

            person_list = []
            for row in data_reader:
                name = row[0].strip() if row[0].strip() else 'Null'  # 이름 결측치 처리
                age = int(row[1]) if row[1] else '0'  # 나이 결측치 처리
                sex = row[2].strip() if row[2].strip() else 'Null'  # 이름 결측치 처리
                job = row[3].strip() if row[3].strip() else 'Null'  # 이름 결측치 처리
                liveplace = row[4].strip() if row[4].strip() else 'Null'  # 이름 결측치 처리

                if name:  # 이름이 있는 레코드만 처리
                    person_list.append(ModelTestData(name=name, age=age, sex=sex, job=job, liveplace=liveplace))

            ModelTestData.objects.bulk_create(person_list)  # 벌크로 모델에 저장
            self.stdout.write(self.style.SUCCESS('Successfully imported data into database'))
