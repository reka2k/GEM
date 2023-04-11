import 'package:client_mobile/services/user_service.dart';
import 'package:flutter/widgets.dart';

import '../models/user.dart';

class UserController extends ChangeNotifier {
  getAllUsers() async {
    var users = await UserService().getAllUsers();
    return users;
  }

  removeUser(id) async {
    return UserService().removeUser(id);
  }

  insertUser(User user) async {
    return await UserService().insertUser(user);
  }

  authenticateUser(String login, String password) async {
    return await UserService().authenticateUser(login, password);
  }

  editUser(User user) async {
    return await UserService().editUser(user);
  }

  editUserPassword(User user) async {
    return await UserService().editUserPassword(user);
  }

  Future<User?> getUserByLogin(String login) async {
    return await UserService().getUserByLogin(login);
  }
}
