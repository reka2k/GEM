import 'dart:convert';

import 'package:client_mobile/models/user.dart';
import 'package:http/http.dart' as http;

import 'config.dart';

class UserService {
  final _client = http.Client();

  Future<List<User>?> getAllUsers() async {
    String address = await _address;
    int port = await _port;
    Uri url =
        Uri.parse("http://$address:$port/gemRestfulAPI-1.0/api/user/getAll");

    var response = await _client.get(url);

    if (response.statusCode == 200) {
      var json = response.body;
      return usersFromJson(json);
    }
    return null;
  }

  void removeUser(id) async {
    String address = await _address;
    int port = await _port;
    Uri url = Uri.parse(
        "http://$address:$port/gemRestfulAPI-1.0/api/user/remove/$id");
    try {
      await _client.delete(url);
    } catch (ex) {
      print(ex);
    }
  }

  Future<bool> editUser(User user) async {
    String address = await _address;
    int port = await _port;
    Uri url = Uri.parse(
        "http://$address:$port/gemRestfulAPI-1.0/api/user/edit/${user.id}");
    try {
      var response = await _client.put(url,
          headers: {"Content-Type": "application/json"},
          body: userToJson(user));

      if (response.statusCode == 200) return true;
      return false;
    } catch (ex) {
      print(ex);
    }
    return false;
  }

  Future<bool> insertUser(User user) async {
    String address = await _address;
    int port = await _port;
    var url =
        Uri.parse('http://$address:$port/gemRestfulAPI-1.0/api/user/insert');
    try {
      var response = await _client.post(url,
          headers: {"Content-Type": "application/json"},
          body: userToJson(user));
      if (response.statusCode == 200) return true;
      return false;
    } catch (ex) {
      print(ex);
    }
    return false;
  }

  Future<bool> authenticateUser(String login, String password) async {
    String address = await _address;
    int port = await _port;
    Uri url = Uri.parse(
        'http://$address:$port/gemRestfulAPI-1.0/api/user/auth/$login-$password');
    try {
      var response = await _client.get(url);
      if (response.statusCode == 200) {
        var data = jsonDecode(response.body);
        if (data["auth"]["valueType"] == "TRUE") {
          return true;
        }
        return false;
      }
      return false;
    } catch (ex) {
      print(ex);
    }
    return false;
  }

  Future<bool> editUserPassword(User user) async {
    String address = await _address;
    int port = await _port;
    var url = Uri.parse(
        'http://$address:$port/gemRestfulAPI-1.0/api/user/editUserPassword/${user.id}');
    try {
      var response = await _client.put(url,
          headers: {"Content-Type": "application/json"},
          body: userToJson(user));
      if (response.statusCode == 200) return true;
      return false;
    } catch (ex) {
      print(ex);
    }
    return false;
  }

  Future<User?> getUserByLogin(String login) async {
    String address = await _address;
    int port = await _port;
    var url = Uri.parse(
        'http://$address:$port/gemRestfulAPI-1.0/api/user/getByLogin/$login');
    try {
      var response = await _client.get(url);
      if (response.statusCode == 200 && response.body.isNotEmpty) {
        return userFromJson(response.body);
      }
    } catch (ex) {
      print(ex);
    }
    return null;
  }

  Future<String> get _address async {
    return await ConfigService().address;
  }

  Future<int> get _port async {
    return await ConfigService().port;
  }
}
