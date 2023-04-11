import requests
import json
import constants
from datetime import datetime, timezone


class Compteur:
    def __init__(self, id, hc, hp, facturejour, facturemois, facturesemaine, trame, typecontrat):
        self.id = id
        self.hc = hc
        self.hp = hp
        self.facturejour = facturejour
        self.facturemois = facturemois
        self.facturesemaine = facturesemaine
        self.trame = trame
        self.typecontrat = typecontrat
        self.endpoint = constants.API_ENDPOINT + "/compteur"

    def compteur_to_json(self):
        compteurJson = {
            "hc": self.hc,
            "hp": self.hp,
            "facturejour": self.facturejour,
            "facturemois": self.facturemois,
            "facturesemaine": self.facturesemaine,
            "trame": self.trame,
            "typecontrat": self.typecontrat}
        return json.dumps(compteurJson)
    
    def add_compteur_to_database(self):
        headers = {"Content-Type": "application/json"}
        url = self.endpoint + "/add"
        data = self.compteur_to_json()
        try:
            response = requests.post(url, data=data, headers=headers)
            print(response.request.body, response.request.headers)
            return response.json()
        except:
            print("An exception occured: ")
            print(response.content)

def main():
    date_time = datetime.now().replace(tzinfo=timezone.utc)
    compteur = Compteur(0, 0, 0, 0, 0, 0, 389, 0)
    compteur.add_compteur_to_database()


main()