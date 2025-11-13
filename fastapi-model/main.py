from fastapi import FastAPI
from pydantic import BaseModel
import random

app = FastAPI(title="Weather Consume Model API")

class WeatherData(BaseModel):
    avg_temp: float
    rainfall: float
    humidity: float

@app.post("/predict")
def predict(data: WeatherData):
    result = data.avg_temp * 1000 - data.humidity *25 + random.uniform(-300,300)
    return {"predict":round(result,2)}