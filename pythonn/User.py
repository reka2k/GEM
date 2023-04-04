import requests
import json
import constants
from datetime import datetime, timezone


class User:
    def __init__(self, id, login, mdp, nom, prenom):
        self.id = id
        self.login = login
        self.mdp = mdp
        self.nom = nom
        self.prenom = prenom
        self.endpoint = constants.API_ENDPOINT + "/user"

    def user_to_json(self):
        userJson = {
            "login": self.login,
            "mdp": self.mdp,
            "nom": self.nom,
            "prenom": self.prenom}
        return json.dumps(userJson)
    
    def add_user_to_database(self):
        headers = {"Content-Type": "application/json"}
        url = self.endpoint + "/add"
        data = self.user_to_json()
        try:
            response = requests.post(url, data=data, headers=headers)
            print(response.request.body, response.request.headers)
            return response.json()
        except:
            print("An exception occured: ")
            print(response.content)

def main():
    date_time = datetime.now().replace(tzinfo=timezone.utc)
    user = User(0, "login", "mdp", "iguedlane", "ethan")
    user.add_user_to_database()


main()