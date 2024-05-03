from django import forms
from .models import Keyword, Trend

class KeywordForm(forms.ModelForm):
    class Meta:
        model = Keyword
        fields = '__all__'

class TrendForm(forms.ModelForm):
    class Meta:
        model = Trend
        fileds = '__all__'