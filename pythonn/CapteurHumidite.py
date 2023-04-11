import requests
import json
import constants
from datetime import datetime, timezone

class CapteurHumidite:
    def __init__(self, id, date, humidite, mesure):
        self.id = id
        self.date = date
        self.humidite = humidite
        self.mesure = mesure
        self.endpoint = constants.API_ENDPOINT + "/capteurhumidite"

    def capteurhumidite_to_json(self):
        capteurhumiditeJson = {
            "date": self.date,
            "humidite": self.humidite,
            "mesure": self.mesure,}
        return json.dumps(capteurhumiditeJson)
    

    def add_capteurhumidite_to_database(self):
        headers = {"Content-Type": "application/json"}
        url = self.endpoint + "/add"
        data = self.capteurhumidite_to_json()
        try:
            response = requests.post(url, data=data, headers=headers)
            print(response.request.body, response.request.headers)
            return response.json()
        except:
            print("An exception occured: ")
            print(response.content)

def main():
    date_time = datetime.now().replace(tzinfo=timezone.utc)
    capteurhumidite = CapteurHumidite(0, 0, 10, 20, date_time.isoformat())
    capteurhumidite.add_capteurhumidite_to_database()


main()




        

