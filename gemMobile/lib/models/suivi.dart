// To parse this JSON data, do
//     final suivi = suiviFromJson(jsonString);

import 'dart:convert';

import 'package:client_mobile/models/user.dart';
import 'package:client_mobile/models/vmc.dart';

Suivi suiviFromJson(String str) => Suivi.fromJson(json.decode(str));

String suiviToJson(Suivi data) => json.encode(data.toJson());

class Suivi {
  Suivi({
    required this.id,
    required this.user,
    required this.date,
    required this.action,
    this.vmc,
  });

  int id;
  User user;
  String date;
  String action;
  Vmc? vmc;

  factory Suivi.fromJson(Map<String, dynamic> json) => Suivi(
        id: json["id"],
        user: User.fromJson(json["user"]),
        date: json["date"],
        action: json["action"],
        vmc: Vmc.fromJson(json["vmc"]),
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "user": user.toJson(),
        "date": date,
        "action": action,
        "vmc": vmc?.toJson(),
      };
}
