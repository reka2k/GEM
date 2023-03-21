import 'dart:convert';

List<CapteurHumidite> userFromJson(String str) => List<CapteurHumidite>.from(
    json.decode(str).map((x) => CapteurHumidite.fromJson(x)));

String userToJson(List<CapteurHumidite> data) =>
    json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class CapteurHumidite {
  CapteurHumidite({
    dynamic id,
    dynamic mesure,
    dynamic date,
    dynamic humidite,
  })  : _humidite = humidite,
        _date = date,
        _mesure = mesure,
        _id = id;

  var _id;

  get id => _id;

  set id(value) {
    _id = value;
  }

  var _mesure;

  get mesure => _mesure;

  set mesure(value) {
    _mesure = value;
  }

  var _date;

  get date => _date;

  set date(value) {
    _date = value;
  }

  var _humidite;

  get humidite => _humidite;

  set humidite(value) {
    _humidite = value;
  }

  factory CapteurHumidite.fromJson(Map<String, dynamic> json) =>
      CapteurHumidite(
        id: json["id"],
        mesure: List<dynamic>.from(json["mesure"].map((x) => x)),
        date: json["date"],
        humidite: json["Humidite"],
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "mesure": List<dynamic>.from(mesure.map((x) => x)),
        "date": date,
        "humidite": humidite,
      };
}
