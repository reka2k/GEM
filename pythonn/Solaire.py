import requests
import json
import constants
from datetime import datetime, timezone


class Solaire:
    def __init__(self, id, conso, batterycapacity, batteryvoltage, pvpower, outputsourcepriority, chargingsourcepriority, datetime):
        self.id = id
        self.conso = conso
        self.batterycapacity = batterycapacity
        self.batteryvoltage = batteryvoltage
        self.pvpower = pvpower
        self.outputsourcepriority = outputsourcepriority
        self.chargingsourcepriority = chargingsourcepriority
        self.datetime = datetime
        self.endpoint = constants.API_ENDPOINT + "/solaire"

    def solaire_to_json(self):
        solaireJson = {
            "conso": self.conso,
            "batterycapacity": self.batterycapacity,
            "batteryvoltage": self.batteryvoltage,
            "pvpower": self.pvpower,
            "outputsourcepriority": self.outputsourcepriority,
            "chargingsourcepriority": self.chargingsourcepriority,
            "datetime": self.datetime}
        return json.dumps(solaireJson)
    
    def add_solaire_to_database(self):
        headers = {"Content-Type": "application/json"}
        url = self.endpoint + "/add"
        data = self.solaire_to_json()
        try:
            response = requests.post(url, data=data, headers=headers)
            print(response.request.body, response.request.headers)
            return response.json()
        except:
            print("An exception occured: ")
            print(response.content)

def main():
    date_time = datetime.now().replace(tzinfo=timezone.utc)
    solaire = Solaire(0, 0, 0, 0, 0, 389, 0, date_time.isoformat())
    solaire.add_solaire_to_database()


main()

