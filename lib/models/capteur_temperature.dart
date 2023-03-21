import 'dart:convert';

List<CapteurTemperature> userFromJson(String str) =>
    List<CapteurTemperature>.from(
        json.decode(str).map((x) => CapteurTemperature.fromJson(x)));

String userToJson(List<CapteurTemperature> data) =>
    json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class CapteurTemperature {
  CapteurTemperature({
    dynamic id,
    dynamic mesure,
    dynamic date,
    dynamic temperature,
  })  : _mesure = mesure,
        _date = date,
        _id = id,
        _temperature = temperature;

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

  var _temperature;

  get temperature => _temperature;

  set temperature(value) {
    _temperature = value;
  }

  factory CapteurTemperature.fromJson(Map<String, dynamic> json) =>
      CapteurTemperature(
        id: json["id"],
        mesure: List<dynamic>.from(json["mesure"].map((x) => x)),
        date: json["date"],
        temperature: json["temperature"].toDouble(),
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "mesure": List<dynamic>.from(mesure.map((x) => x)),
        "date": date,
        "temperature": temperature,
      };
}
