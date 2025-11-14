from fastapi import FastAPI
from pydantic import BaseModel
import joblib
import numpy as np

app = FastAPI(title="Weather → Passenger Prediction Model API")

model = joblib.load("model.pkl")

class WeatherData(BaseModel):
    avgTemp: float
    rainfall: float
    humidity: float

@app.post("/predict")
def predict(data: WeatherData):

    X = np.array([[data.avgTemp, data.rainfall, data.humidity]])
    pred = model.predict(X)[0]
    return {"prediction": float(pred)}