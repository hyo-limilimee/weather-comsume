import pandas as pd
from sklearn.ensemble import RandomForestRegressor
from sklearn.model_selection import train_test_split
import joblib

def train():
    df = pd.read_csv("data/merged.csv", encoding="utf-8-sig")

    X = df[['avgTemp', 'rainfall', 'humidity']]
    y = df['total_passenger']

    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=0.2, random_state=42
    )

    model = RandomForestRegressor(
        n_estimators=200,
        random_state=42
    )
    model.fit(X_train, y_train)

    joblib.dump(model, "model.pkl")
    print("✔ model.pkl 저장 완료!")

if __name__ == "__main__":
    train()
