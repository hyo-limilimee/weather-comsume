import pandas as pd
from sklearn.ensemble import RandomForestRegressor
from sklearn.preprocessing import StandardScaler
from sklearn.pipeline import Pipeline
from sklearn.model_selection import train_test_split
import joblib

df = pd.read_csv("data/merged.csv")

X = df[['avg_temp', 'rainfall', 'humidity']]
y = df['subway_user']

X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)

pipeline = Pipeline([
    ('scaler', StandardScaler()),
    ('model', RandomForestRegressor(
        n_estimators=300,
        random_state=42
    ))
])

pipeline.fit(X_train, y_train)

joblib.dump(pipeline, "model.pkl")

print("✔ Scaling 적용된 model.pkl 저장 완료!")
