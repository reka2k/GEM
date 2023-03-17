// To parse this JSON data, do
//     final vmc = vmcFromJson(jsonString);

import 'dart:convert';

import 'package:client_mobile/models/capteur_humidite.dart';
import 'package:client_mobile/models/capteur_temperature.dart';

Vmc vmcFromJson(String str) => Vmc.fromJson(json.decode(str));

List<Vmc> vmcsFromJson(String str) =>
    List<Vmc>.from(json.decode(str).map((x) => Vmc.fromJson(x)));

String vmcToJson(Vmc data) => json.encode(data.toJson());

class Vmc {
  Vmc({
    dynamic id,
    required this.statut,
    required this.capteurTemperature,
    required this.capteurHumidite,
  }) : _id = id;

  var _id;

  get id => _id;

  set id(value) {
    _id = value;
  }

  int statut;
  CapteurTemperature capteurTemperature;
  CapteurHumidite capteurHumidite;

  factory Vmc.fromJson(Map<String, dynamic> json) => Vmc(
        id: json["id"],
        statut: json["statut"],
        capteurTemperature:
            CapteurTemperature.fromJson(json["capteurTemperature"]),
        capteurHumidite: CapteurHumidite.fromJson(json["capteurHumidite"]),
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "statut": statut,
        "capteurTemperature": capteurTemperature.toJson(),
        "capteurHumidite": capteurHumidite.toJson(),
      };
}
