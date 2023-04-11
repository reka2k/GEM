import 'package:client_mobile/controllers/user_controller.dart';
import 'package:client_mobile/views/home_view.dart';
import 'package:flutter/material.dart';

import '../widgets/get_started_button.dart';
import '../widgets/login_field.dart';
import '../widgets/password_field.dart';

class LoginScreen extends StatefulWidget {
  final Function(String login) notifyParent;
  const LoginScreen({Key? key, required this.notifyParent}) : super(key: key);

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  late TextEditingController loginController;
  late TextEditingController passwordController;
  double _elementsOpacity = 1;
  bool loadingBallAppear = false;
  double loadingBallSize = 1;
  bool isLoggedIn = false;

  @override
  void initState() {
    loginController = TextEditingController();
    passwordController = TextEditingController();

    super.initState();
  }

  authenticateUser() async {
    String login = loginController.text.trim();
    String password = passwordController.text.trim();
    var tempBool = await UserController().authenticateUser(login, password);
    if (tempBool) {
      setState(() {
        isLoggedIn = true;
      });
      return true;
    }
    return false;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: true,
      body: SafeArea(
        bottom: false,
        child: loadingBallAppear
            ? const Padding(
                padding: EdgeInsets.symmetric(vertical: 20, horizontal: 30.0),
                child: HomePage())
            : Padding(
                padding: const EdgeInsets.symmetric(horizontal: 50.0),
                child: SingleChildScrollView(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      const SizedBox(height: 70),
                      TweenAnimationBuilder<double>(
                        duration: const Duration(milliseconds: 300),
                        tween: Tween(begin: 1, end: _elementsOpacity),
                        builder: (_, value, __) => Opacity(
                          opacity: value,
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              const Icon(Icons.waving_hand_outlined,
                                  size: 60, color: Color(0xff21579C)),
                              const SizedBox(height: 25),
                              const Text(
                                "Welcome,",
                                style: TextStyle(
                                    color: Colors.black, fontSize: 35),
                              ),
                              Text(
                                "Sign in to continue",
                                style: TextStyle(
                                    color: Colors.black.withOpacity(0.7),
                                    fontSize: 35),
                              ),
                            ],
                          ),
                        ),
                      ),
                      const SizedBox(height: 50),
                      Padding(
                        padding: const EdgeInsets.symmetric(horizontal: 8.0),
                        child: Column(
                          children: [
                            LoginField(
                                fadeLogin: _elementsOpacity == 0,
                                loginController: loginController),
                            const SizedBox(height: 40),
                            PasswordField(
                                fadePassword: _elementsOpacity == 0,
                                passwordController: passwordController),
                            const SizedBox(height: 60),
                            GetStartedButton(
                              elementsOpacity: _elementsOpacity,
                              onTap: () async {
                                var res = await authenticateUser();
                                if (res) {
                                  widget.notifyParent(
                                      loginController.text.trim());
                                  setState(() {
                                    isLoggedIn = true;
                                    _elementsOpacity = 0;
                                  });
                                } else {
                                  // ignore: use_build_context_synchronously
                                  ScaffoldMessenger.of(context).showSnackBar(
                                      const SnackBar(
                                          content: Text(
                                              'Verifiez vos coordonnees')));
                                }
                              },
                              onAnimatinoEnd: () async {
                                await Future.delayed(
                                    const Duration(milliseconds: 500));
                                setState(() {
                                  loadingBallAppear = true;
                                });
                              },
                            )
                          ],
                        ),
                      ),
                    ],
                  ),
                ),
              ),
      ),
    );
  }
}
