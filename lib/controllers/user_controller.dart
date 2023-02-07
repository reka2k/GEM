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
    return UserService().insertUser(user);
  }
}
