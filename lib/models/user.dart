// To parse this JSON data, do
//     final user = userFromJson(jsonString);
import 'dart:convert';

List<User> userFromJson(String str) =>
    List<User>.from(json.decode(str).map((x) => User.fromJson(x)));

String usersToJson(List<User> data) =>
    json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

String userToJson(User data) => json.encode(data);

class User {
  User({
    dynamic id,
    required this.nom,
    required this.prenom,
    required this.login,
    required this.mdp,
  }) : _id = id;

  var _id;

  get id => _id;

  set id(value) {
    _id = value;
  }

  String nom;
  String prenom;
  String login;
  String mdp;

  factory User.fromJson(Map<String, dynamic> json) => User(
        id: json["id"],
        nom: json["nom"],
        prenom: json["prenom"],
        login: json["login"],
        mdp: json["mdp"],
      );

  Map<String, dynamic> toJson() => {
        "nom": nom,
        "prenom": prenom,
        "login": login,
        "mdp": mdp,
      };
}
