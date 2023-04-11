import requests
import json
import constants
from datetime import datetime, timezone

class CapteurTemperature:
    def __init__(self, id, date, mesure, temperature):
        self.id = id
        self.date = date
        self.mesure = mesure
        self.temperature = temperature
        self.endpoint = constants.API_ENDPOINT + "/capteurtemperature"

    def capteurtemperature_to_json(self):
        capteurtemperatureJson = {
            "date": self.date,
            "mesure": self.mesure,
            "temperature": self.temperature}
        return json.dumps(capteurtemperatureJson)
    
    def add_capteurtemperature_to_database(self):
        headers = {"Content-Type": "application/json"}
        url = self.endpoint + "/add"
        data = self.capteurtemperature_to_json()
        try:
            response = requests.post(url, data=data, headers=headers)
            print(response.request.body, response.request.headers)
            return response.json()
        except:
            print("An exception occured: ")
            print(response.content)

def main():
    date_time = datetime.now().replace(tzinfo=timezone.utc)
    capteurtemperature = CapteurTemperature(0, 0,389, 0, date_time.isoformat())
    capteurtemperature.add_capteurtemperature_to_database()


main()

