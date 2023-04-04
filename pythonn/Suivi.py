import requests
import json
import constants
from datetime import datetime, timezone

class Suivi:
    def __init__(self, id, action, date, user_id, vmc_id):
        self.id = id
        self.action = action
        self.date = date
        self.user_id = user_id
        self.vmc_id = vmc_id
        self.endpoint = constants.API_ENDPOINT + "/suivi"

    def suivi_to_json(self):
        suiviJson = {
            "action": self.action,
            "date": self.date,
            "user_id": self.user_id,
            "vmc_id": self.vmc_id}
        return json.dumps(suiviJson)
    
    def add_suivi_to_database(self):
        headers = {"Content-Type": "application/json"}
        url = self.endpoint + "/add"
        data = self.suivi_to_json()
        try:
            response = requests.post(url, data=data, headers=headers)
            print(response.request.body, response.request.headers)
            return response.json()
        except:
            print("An exception occured: ")
            print(response.content)

def main():
    date_time = datetime.now().replace(tzinfo=timezone.utc)
    suivi = Suivi(0, 0, 2, 8, date_time.isoformat())
    suivi.add_suivi_to_database()


main()
