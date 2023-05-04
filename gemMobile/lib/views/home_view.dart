import 'package:client_mobile/controllers/user_controller.dart';
import 'package:client_mobile/views/login_view.dart';
import 'package:client_mobile/views/settings_view.dart';
import 'package:flutter/material.dart';

import '../models/user.dart';
import 'compteur_view.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  int _selectedIndex = 0;
  bool isLoggedIn = false;
  List<Widget>? _widgetOptions;
  //TODO Logged in as *userLogin*

  @override
  void initState() {
    super.initState();
    _widgetOptions = [
      const CompteurPage(),
      // SettingsPage gets added dynamically to allow passing the logged in user
    ];
  }

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  authUser(login) async {
    User? loggedUser = await UserController().getUserByLogin(login);

    setState(() {
      isLoggedIn = true;
    });

    _widgetOptions!
        .add(SettingsPage(logOut: (() => logout()), loggedUser: loggedUser));
  }

  logout() {
    setState(() {
      isLoggedIn = false;
      _selectedIndex = 0;
    });
    _widgetOptions!.removeLast();
  }

  @override
  Widget build(BuildContext context) {
    return Visibility(
      maintainSize: false,
      replacement: LoginScreen(notifyParent: (String login) {
        authUser(login);
      }),
      visible: isLoggedIn,
      child: Scaffold(
        body: Center(
          child: _widgetOptions!.elementAt(_selectedIndex),
        ),
        bottomNavigationBar: BottomNavigationBar(
            items: const <BottomNavigationBarItem>[
              BottomNavigationBarItem(
                icon: Icon(
                  Icons.speed_sharp,
                ),
                label: "Compteur",
              ),
              BottomNavigationBarItem(
                icon: Icon(Icons.settings),
                label: 'Settings',
              ),
            ],
            type: BottomNavigationBarType.shifting,
            currentIndex: _selectedIndex,
            selectedItemColor: Colors.green,
            unselectedItemColor: Colors.grey,
            iconSize: 40,
            onTap: _onItemTapped,
            elevation: 5),
      ),
    );
  }
}
