import 'package:flutter/material.dart';

class LoginField extends StatefulWidget {
  final bool fadeLogin;
  final TextEditingController loginController;
  const LoginField(
      {super.key, required this.loginController, required this.fadeLogin});

  @override
  State<LoginField> createState() => _LoginFieldState();
}

class _LoginFieldState extends State<LoginField>
    with SingleTickerProviderStateMixin {
  double bottomAnimationValue = 0;
  double opacityAnimationValue = 0;
  EdgeInsets paddingAnimationValue = const EdgeInsets.only(top: 22);

  late TextEditingController loginController;
  late AnimationController _animationController;
  late Animation<Color?> _animation;

  FocusNode node = FocusNode();
  @override
  void initState() {
    loginController = widget.loginController;
    _animationController = AnimationController(
        vsync: this, duration: const Duration(milliseconds: 300));
    final tween =
        ColorTween(begin: Colors.grey.withOpacity(0), end: Colors.green);

    _animation = tween.animate(_animationController)
      ..addListener(() {
        setState(() {});
      });
    super.initState();

    node.addListener(() {
      if (node.hasFocus) {
        setState(() {
          bottomAnimationValue = 1;
        });
      } else {
        setState(() {
          bottomAnimationValue = 0;
        });
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        TweenAnimationBuilder<double>(
          duration: const Duration(milliseconds: 300),
          tween: Tween(begin: 0, end: widget.fadeLogin ? 0 : 1),
          builder: ((_, value, __) => Opacity(
                opacity: value,
                child: TextFormField(
                  autocorrect: false,
                  textAlign: TextAlign.center,
                  controller: loginController,
                  focusNode: node,
                  validator: (value) {
                    return value!.isNotEmpty ? null : "Entrez un login";
                  },
                  decoration: const InputDecoration(
                      hintText: "Login", border: InputBorder.none),
                  keyboardType: TextInputType.name,
                  onChanged: (value) async {
                    if (value.isNotEmpty) {
                      if (isValidEmail(value)) {
                        setState(() {
                          bottomAnimationValue = 0;
                          opacityAnimationValue = 1;
                          paddingAnimationValue = const EdgeInsets.only(top: 0);
                        });
                        _animationController.forward();
                      } else {
                        _animationController.reverse();
                        setState(() {
                          bottomAnimationValue = 1;
                          opacityAnimationValue = 0;
                          paddingAnimationValue =
                              const EdgeInsets.only(top: 22);
                        });
                      }
                    } else {
                      setState(() {
                        bottomAnimationValue = 0;
                      });
                    }
                  },
                ),
              )),
        ),
        Positioned.fill(
          child: Align(
            alignment: Alignment.bottomCenter,
            child: AnimatedContainer(
              duration: const Duration(milliseconds: 500),
              width: widget.fadeLogin ? 0 : 300,
              child: TweenAnimationBuilder<double>(
                tween: Tween(begin: 0, end: bottomAnimationValue),
                curve: Curves.easeIn,
                duration: const Duration(milliseconds: 500),
                builder: ((context, value, child) => LinearProgressIndicator(
                      value: value,
                      backgroundColor: Colors.grey.withOpacity(0.5),
                      color: Colors.green,
                    )),
              ),
            ),
          ),
        ),
        Positioned.fill(
          child: AnimatedPadding(
            curve: Curves.easeIn,
            duration: const Duration(milliseconds: 500),
            padding: paddingAnimationValue,
            child: TweenAnimationBuilder<double>(
              tween: Tween(begin: 0, end: widget.fadeLogin ? 0 : 1),
              duration: const Duration(milliseconds: 700),
              builder: ((context, value, child) => Opacity(
                    opacity: value,
                    child: Align(
                      alignment: AlignmentDirectional.centerEnd,
                      child: Padding(
                        padding: const EdgeInsets.symmetric(horizontal: 12.0)
                            .copyWith(bottom: 0),
                        child: Icon(Icons.check_rounded,
                            size: 27,
                            color: _animation.value // _animation.value,
                            ),
                      ),
                    ),
                  )),
            ),
          ),
        ),
      ],
    );
  }

  bool isValidEmail(String email) {
    return RegExp(
            r'^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$')
        .hasMatch(email);
  }
}
