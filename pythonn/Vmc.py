import requests
import json
import constants
from datetime import datetime, timezone


class Vmc:
    def __init__(self, id, humidite, statut, temperature, capteurhumidite_id, capteurtemperature_id):
        self.id = id
        self.humidite = humidite
        self.statut = statut
        self.temperature = temperature
        self.capteurhumidite_id = capteurhumidite_id
        self.capteurtemperature_id = capteurtemperature_id
        self.endpoint = constants.API_ENDPOINT + "/vmc"

    def vmc_to_json(self):
        vmcJson = {
            "humidite": self.humidite,
            "statut": self.statut,
            "temperature": self.temperature,
            "capteurhumidite_id": self.capteurhumidite_id,
            "capteurtemperature_id": self.capteurtemperature_id}
        return json.dumps(vmcJson)
    
    def add_vmc_to_database(self):
        headers = {"Content-Type": "application/json"}
        url = self.endpoint + "/add"
        data = self.vmc_to_json()
        try:
            response = requests.post(url, data=data, headers=headers)
            print(response.request.body, response.request.headers)
            return response.json()
        except:
            print("An exception occured: ")
            print(response.content)

def main():
    date_time = datetime.now().replace(tzinfo=timezone.utc)
    vmc = Vmc(0, 0, 0, 0, 12, 45)
    vmc.add_vmc_to_database()


main()