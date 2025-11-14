import pandas as pd

def load_and_merge():
    subway = pd.read_csv("data/subway_raw.csv", encoding="cp949")
    weather = pd.read_csv("data/weather_raw.csv", encoding="cp949")

    subway = subway.rename(columns={'사용일자': 'date'})
    weather = weather.rename(columns={'일시': 'date'})

    weather = weather[['date', '평균기온(°C)', '일강수량(mm)', '평균 상대습도(%)']]

    weather = weather.rename(columns={
        '평균기온(°C)': 'avgTemp',
        '일강수량(mm)': 'rainfall',
        '평균 상대습도(%)': 'humidity'
    })

    subway['date'] = pd.to_datetime(subway['date'], format='%Y%m%d')
    weather['date'] = pd.to_datetime(weather['date'])

    subway['total_passenger'] = subway['승차총승객수'] + subway['하차총승객수']

    merged = pd.merge(weather, subway.groupby('date')['total_passenger'].sum().reset_index(),
                      on='date', how='inner')

    merged.to_csv("data/merged.csv", index=False, encoding="utf-8-sig")
    print("✔ merged.csv 생성 완료!")

if __name__ == "__main__":
    load_and_merge()
