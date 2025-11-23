import joblib
from fastapi import FastAPI
from pydantic import BaseModel
import numpy as np

app = FastAPI()

model = joblib.load("model.pkl")

class WeatherData(BaseModel):
    avgTemp: float
    rainfall: float
    humidity: float

@app.post("/predict")
def predict(data: WeatherData):
    input_data = np.array([[data.avgTemp, data.rainfall, data.humidity]])
    prediction = model.predict(input_data)[0]

    return {"prediction": float(prediction)}
