import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.pipeline import Pipeline
from sklearn.metrics import mean_absolute_error, r2_score

from sklearn.ensemble import RandomForestRegressor, GradientBoostingRegressor
from sklearn.linear_model import LinearRegression

import joblib

df = pd.read_csv("data/merged.csv", encoding="utf-8-sig")

df = df.dropna()

X = df[['avgTemp', 'rainfall', 'humidity']]
y = df['total_passenger']

X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)

models = {
    "RandomForest": RandomForestRegressor(n_estimators=300, random_state=42),
    "LinearRegression": LinearRegression(),
    "GradientBoosting": GradientBoostingRegressor(random_state=42)
}

best_model = None
best_score = float("-inf")
best_name = ""

print("\n==============================")
print("📊 모델 성능 비교 시작")
print("==============================")

for name, model in models.items():

    pipeline = Pipeline([
        ("scaler", StandardScaler()),
        ("model", model)
    ])

    pipeline.fit(X_train, y_train)

    pred = pipeline.predict(X_test)

    mae = mean_absolute_error(y_test, pred)
    r2 = r2_score(y_test, pred)

    print(f"\n▶ {name}")
    print(f"   MAE : {mae:,.2f}")
    print(f"   R²  : {r2:.4f}")

    if r2 > best_score:
        best_score = r2
        best_model = pipeline
        best_name = name

joblib.dump(best_model, "model.pkl")

print("\n==============================")
print(f"🏆 최종 선택된 모델: {best_name}")
print("✔ model.pkl 저장 완료!")
print("==============================")
