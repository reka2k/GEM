import 'dart:convert';

Solaire relevesFromJson(String str) => Solaire.fromJson(json.decode(str));

String relevesToJson(Solaire data) => json.encode(data.toJson());

List<Solaire> listSolaireFromJson(String str) =>
    List<Solaire>.from(json.decode(str).map((x) => Solaire.fromJson(x)));

class Solaire {
  Solaire({
    required this.id,
    required this.chargingSourcePriority,
    required this.outputSourcePriority,
    required this.batteryCapacity,
    required this.batteryVoltage,
    required this.conso,
    required this.pvPower,
    required this.date,
  });

  int id;
  final String chargingSourcePriority;
  final String outputSourcePriority;
  final int batteryCapacity;
  final double batteryVoltage;
  final int conso;
  final int pvPower;
  final String date;

  factory Solaire.fromJson(Map<String, dynamic> json) => Solaire(
        id: json["id"],
        chargingSourcePriority: json["chargingsourcepriority"],
        outputSourcePriority: json["outputsourcepriority"],
        batteryCapacity: json["batterycapacity"],
        batteryVoltage: json["batteryvoltage"]?.toDouble(),
        conso: json["conso"],
        pvPower: json["pvpower"],
        date: json["date"],
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "chargingsourcepriority": chargingSourcePriority,
        "outputsourcepriority": outputSourcePriority,
        "batterycapacity": batteryCapacity,
        "batteryvoltage": batteryVoltage,
        "conso": conso,
        "pvpower": pvPower,
        "date": date,
      };
}
