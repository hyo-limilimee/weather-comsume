from fastapi import FastAPI
from pydantic import BaseModel
import random

app = FastAPI(title="Weather Consume Model API")

class WeatherData(BaseModel):
    avgTemp: float
    rainfall: float
    humidity: float

@app.post("/predict")
def predict(data: WeatherData):
    result = data.avgTemp * 1000 - data.humidity * 25 + random.uniform(-300, 300)
    return {"prediction": round(result, 2)}
