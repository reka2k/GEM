import requests
import json
import constants
from datetime import datetime, timezone


class Releves:
    def __init__(self, bbrhcjb, bbrhpjb, bbrhcjw, bbrhpjw, bbrhcjr, bbrhpjr, papp, ptec, date):
        self.bbrhcjb = bbrhcjb
        self.bbrhpjb = bbrhpjb
        self.bbrhcjw = bbrhcjw
        self.bbrhpjw = bbrhpjw
        self.bbrhcjr = bbrhcjr
        self.bbrhpjr = bbrhpjr
        self.papp = papp
        self.ptec = ptec
        self.date = date
        self.endpoint = constants.API_ENDPOINT + "releve"

    def releve_to_json(self):
        relevesJson = {
            "bbrhcjb": self.bbrhcjb,
            "bbrhpjb": self.bbrhpjb,
            "bbrhcjw": self.bbrhcjw,
            "bbrhpjw": self.bbrhpjw,
            "bbrhcjr": self.bbrhcjr,
            "bbrhpjr": self.bbrhpjr,
            "papp": self.papp,
            "ptec": self.ptec,
            "date": self.date}
        return json.dumps(relevesJson)

    # Adds measurements to DB, returns response status code
    def add_releves_to_database(self):
        headers = {"Content-Type": "application/json"}
        url = self.endpoint + "/add"
        data = self.releve_to_json()
        try:
            response = requests.post(url, data=data, headers=headers)
            print(response.request.body, response.request.headers)
            return response.json()
        except:
            print("An exception occured: ")
            print(response.content)


def main():
    date_time = datetime.now().replace(tzinfo=timezone.utc)
    releve = Releves(0, 0, 0, 0, 0, 0, 389, 0, date_time.isoformat())
    releve.add_releves_to_database()


main()
