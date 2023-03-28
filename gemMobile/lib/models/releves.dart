import 'dart:convert';

Releves relevesFromJson(String str) => Releves.fromJson(json.decode(str));

List<Releves> listRelevesFromJson(String str) =>
    List<Releves>.from(json.decode(str).map((x) => Releves.fromJson(x)));

String relevesToJson(Releves data) => json.encode(data.toJson());

class Releves {
  Releves({
    required this.id,
    required this.bbrhcjb,
    required this.bbrhpjb,
    required this.bbrhcjw,
    required this.bbrhpjw,
    required this.bbrhcjr,
    required this.bbrhpjr,
    required this.papp,
    required this.ptec,
    required this.date,
  });

  final int id;
  final int bbrhcjb;
  final int bbrhpjb;
  final int bbrhcjw;
  final int bbrhpjw;
  final int bbrhcjr;
  final int bbrhpjr;
  final int papp;
  final String ptec;
  final DateTime date;

  factory Releves.fromJson(Map<String, dynamic> json) => Releves(
        id: json["id"],
        bbrhcjb: json["bbrhcjb"],
        bbrhpjb: json["bbrhpjb"],
        bbrhcjw: json["bbrhcjw"],
        bbrhpjw: json["bbrhpjw"],
        bbrhcjr: json["bbrhcjr"],
        bbrhpjr: json["bbrhpjr"],
        papp: json["papp"],
        ptec: json["ptec"],
        date: DateTime.parse(json["date"]),
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "bbrhcjb": bbrhcjb,
        "bbrhpjb": bbrhpjb,
        "bbrhcjw": bbrhcjw,
        "bbrhpjw": bbrhpjw,
        "bbrhcjr": bbrhcjr,
        "bbrhpjr": bbrhpjr,
        "papp": papp,
        "ptec": ptec,
        "date": date,
      };
}
