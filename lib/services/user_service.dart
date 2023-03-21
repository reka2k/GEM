import 'package:client_mobile/models/user.dart';
import 'package:http/http.dart' as http;

class UserService {
  final _client = http.Client();
  static final _baseUri =
      Uri.parse("http://172.16.79.128:8080/gemRestfulAPI-1.0/api/user/");

  Future<List<User>?> getAllUsers() async {
    var url = Uri.parse(
        '${_baseUri}getAll'); // on concatene la fonction voulu a l'URI de base
    var response = await _client.get(url);

    if (response.statusCode == 200) {
      var json = response.body;
      return userFromJson(json);
    }
    return null;
  }

  void removeUser(id) async {
    var url = Uri.parse('${_baseUri}remove/$id');
    try {
      await _client.delete(url);
    } catch (ex) {
      print(ex);
    }
  }

  Future<bool> insertUser(User user) async {
    var url = Uri.parse('${_baseUri}insert');
    try {
      var reponse = await _client.post(url,
          headers: {"Content-Type": "application/json"},
          body: userToJson(user));
      if (reponse.statusCode == 200) return true;
      return false;
    } catch (ex) {
      print(ex);
    }
    return false;
  }
}
