### A. 데이터 전처리 - 데이터 읽어오기
- Pandas를 사용하여 csv파일(NLFX.csv)을 DataFrame으로 읽어옵니다.
- 이 때, [`'Date'`, `'Open'`, `High`, `'Low'`, `'Close'`] 필드만 읽어오도록 구성합니다.
---
반드시 `numpy`, `pandas`, `matplotlib.pyplot`을 `import` 해야합니다.

---
### B. 데이터 전처리 - 2021년 이후의 종가 데이터 출력하기
날짜 데이터를 먼저 변환하여 원하는 데이터를 위해 필터링합니다.  
`plt.plot`에 차례로 가로축과 세로축 데이터를 입력합니다.

---
### C. 데이터 분석 - 2021년 이후 최고, 최저가 출력하기
`max()`와 `min()`을 사용하여 값을 구합니다.

---
### D. 데이터 분석 - 2021년 이후 월 별 평균 종가 출력하기
> df_after_2021['YearMonth'] = df_after_2021['Date'].dt.to_period("M"):  
데이터프레임의 'Date' 열을 기반으로 각 날짜를 월 단위의 Period 객체로 변환합니다. dt.to_period("M")은 각 날짜를 월 단위의 Period 객체로 변환하는 메서드입니다.

> df_after_2021['YearMonth'] = df_after_2021['YearMonth'].dt.to_timestamp():  
앞서 생성한 'YearMonth' 열의 데이터를 다시 Timestamp 객체로 변환합니다. 이를 통해 'YearMonth' 열에는 각 월의 시작일자가 남게 됩니다.

> monthly_avg_close = df_after_2021.groupby('YearMonth')['Close'].mean():  
'YearMonth' 열을 기준으로 데이터프레임을 그룹화하고, 그룹화된 각 그룹에 대해 'Close' 열의 평균을 계산합니다. 따라서 monthly_avg_close는 각 월별 평균 종가를 담은 Series가 됩니다.

> df_monthly_avg_close = pd.DataFrame({'YearMonth': monthly_avg_close.index, 'MonthlyAvgClose': monthly_avg_close.values}):  
monthly_avg_close Series의 인덱스는 'YearMonth' 열이 됩니다. 이를 기반으로 새로운 데이터프레임 df_monthly_avg_close을 생성합니다. 이 데이터프레임에는 'YearMonth'와 'MonthlyAvgClose' 열이 있으며, 각 월별 평균 종가가 기록되어 있습니다.

---
### E. 데이터 시각화 - 2022년 1월 이후 월 별 최고, 최저, 종가 시각화
`x축`의 눈금이 겹쳐서 보이지 않을 때, `plt.xticks()`를 사용하여 회전 시켜줍니다.