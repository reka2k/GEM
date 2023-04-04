import requests
import json
import constants
from datetime import datetime, timezone

class Chauffage:
    def __init__(self, id, heurecreuse, heurepleine, statut, compteur_id):
        self.id = id
        self.heurecreuse = heurecreuse
        self.heurepleine = heurepleine
        self.statut = statut
        self.compteur_id = compteur_id
        self.endpoint = constants.API_ENDPOINT + "/chauffage"

    def chauffage_to_json(self):
        chauffageJson = {
            "heurecreuse": self.heurecreuse,
            "heurepleine": self.heurepleine,
            "statut": self.statut,
            "compteur_id": self.compteur_id}
        return json.dumps(chauffageJson)
    
    def add_chauffage_to_database(self):
        headers = {"Content-Type": "application/json"}
        url = self.endpoint + "/add"
        data = self.chauffage_to_json()
        try:
            response = requests.post(url, data=data, headers=headers)
            print(response.request.body, response.request.headers)
            return response.json()
        except:
            print("An exception occured: ")
            print(response.content)

def main():
    date_time = datetime.now().replace(tzinfo=timezone.utc)
    chauffage = Chauffage(0, 0, 0, 38, 0,)
    chauffage.add_chauffage_to_database()


main()