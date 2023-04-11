import 'package:client_mobile/controllers/user_controller.dart';
import 'package:client_mobile/models/user.dart';
import 'package:flutter/material.dart';
import 'package:loading_animation_widget/loading_animation_widget.dart';
import 'dart:math' as math;

class UsersPage extends StatefulWidget {
  const UsersPage({super.key});

  @override
  State<UsersPage> createState() => _UsersPageState();
}

class _UsersPageState extends State<UsersPage> {
  List<User>? users; // Liste d'user

  // sert a savoir si les donnes ont ete fetch ou non -> si non on affiche une progressbar
  bool isLoaded = false;

  //Differents controller pour le form d'ajout(modif aussi possiblement??) d'utilisateur
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final formLoginController = TextEditingController();
  final formPasswordController = TextEditingController();
  final formNomController = TextEditingController();
  final formPrenomController = TextEditingController();

  //Fetch les donnees a l'initialisation de la vue
  @override
  void initState() {
    super.initState();
    getData();
  }

  //func qui recupere les donnees
  getData() async {
    dynamic tempUsers = await UserController().getAllUsers();
    setState(() {
      isLoaded = false;
    });

    if (tempUsers != null) {
      setState(() {
        isLoaded = true;
      });
      users = tempUsers as List<User>;
      users!.sort((a, b) {
        return a.login.toLowerCase().compareTo(b.login.toLowerCase());
      });
    }
  }

  //Supprime user
  removeData(id) async {
    if (users?.length == 1) {
      //TODO SnackBar impossible de delete
    } else {
      await UserController().removeUser(id);
    }
  }

  insertData() async {
    User user = User(
        nom: formNomController.text.trim(),
        prenom: formPrenomController.text.trim(),
        login: formLoginController.text.trim(),
        mdp: formPasswordController.text.trim());

    var response = await UserController().insertUser(user);
    if (response) {
      getData();
    } else {
      print('err');
    }

    SnackBar snackBar = SnackBar(content: Text('${user.login} ajouté'));
    ScaffoldMessenger.of(context).showSnackBar(snackBar);

    formNomController.clear();
    formPrenomController.clear();
    formLoginController.clear();
    formPasswordController.clear();
  }

  modifyData(User user) async {
    if (formLoginController.text.isNotEmpty) {
      user.login = formLoginController.text.trim();
    }

    if (formNomController.text.isNotEmpty) {
      user.nom = formNomController.text.trim();
    }

    if (formPrenomController.text.isNotEmpty) {
      user.prenom = formPrenomController.text.trim();
    }

    var response = await UserController().editUser(user);
    if (response) {
      getData();
      // ignore: use_build_context_synchronously
      Navigator.pop(context);
    } else {
      // ignore: use_build_context_synchronously
      Navigator.pop(context);
      return const AlertDialog(
        content: Text("Couldn't modify user; please try again."),
      );
    }

    SnackBar snackBar =
        SnackBar(content: Text('${user.prenom} ${user.nom} modifié'));
    ScaffoldMessenger.of(context).showSnackBar(snackBar);

    formNomController.clear();
    formPrenomController.clear();
    formLoginController.clear();
  }

  editPassword(User user) async {
    //TODO FONCTIONNE PAS -> Fix API Java

    if (formPasswordController.text.isEmpty) {
      Navigator.pop(context);
      return const AlertDialog(
        content: Text("Couldn't modify user; please try again."),
      );
    }

    user.mdp = formPasswordController.text.trim();
    var response = await UserController().editUserPassword(user);

    if (response) {
      getData();
      Navigator.pop(context);
      SnackBar snackBar = SnackBar(
          content: Text('Mot de passe de ${user.prenom} ${user.nom} modifié'));
      ScaffoldMessenger.of(context).showSnackBar(snackBar);
      return;
    }

    Navigator.pop(context);
    SnackBar snackBar = const SnackBar(
        content: Text('Couldn\'t edit password; Please try again'));
    ScaffoldMessenger.of(context).showSnackBar(snackBar);
    return;
  }

  Future<void> _refresh() async {
    getData();
  }

  usersModalBottomSheet(User user) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: <Widget>[
        ListTile(
          leading: const Icon(Icons.edit),
          title: const Text('Edit name'),
          onTap: () {
            showDialog(
                context: context,
                builder: (context) {
                  return StatefulBuilder(builder: (context, setState) {
                    return AlertDialog(
                      content: Form(
                          key: _formKey,
                          child: Column(
                            mainAxisSize: MainAxisSize.min,
                            children: [
                              TextFormField(
                                autocorrect: false,
                                controller: formPrenomController,
                                validator: (value) {
                                  return value!.isNotEmpty
                                      ? null
                                      : "Enter first name";
                                },
                                decoration: const InputDecoration(
                                    hintText: "First name"),
                              ),
                              TextFormField(
                                autocorrect: false,
                                controller: formNomController,
                                validator: (value) {
                                  return value!.isNotEmpty
                                      ? null
                                      : "Enter name";
                                },
                                decoration:
                                    const InputDecoration(hintText: "Name"),
                              ),
                            ],
                          )),
                      title: const Text('Edit user'),
                      actions: <Widget>[
                        TextButton(
                            onPressed: () {
                              if (_formKey.currentState!.validate()) {
                                modifyData(user);
                                Navigator.of(context).pop();
                              }
                            },
                            child: const Text('Edit')),
                      ],
                    );
                  });
                });
          },
        ),
        ListTile(
          leading: const Icon(Icons.edit_note),
          title: const Text('Edit login'),
          onTap: () {
            showDialog(
                context: context,
                builder: (context) {
                  return StatefulBuilder(builder: (context, setState) {
                    return AlertDialog(
                      content: Form(
                          key: _formKey,
                          child: Column(
                            mainAxisSize: MainAxisSize.min,
                            children: [
                              TextFormField(
                                autocorrect: false,
                                controller: formLoginController,
                                validator: (value) {
                                  return value!.isNotEmpty
                                      ? null
                                      : "Enter a login";
                                },
                                decoration:
                                    const InputDecoration(hintText: "Login"),
                              ),
                            ],
                          )),
                      title: const Text('Edit login'),
                      actions: <Widget>[
                        TextButton(
                            onPressed: () {
                              if (_formKey.currentState!.validate()) {
                                modifyData(user);
                                Navigator.of(context).pop();
                                formPasswordController.clear();
                              }
                            },
                            child: const Text('Edit')),
                      ],
                    );
                  });
                });
          },
        ),
        ListTile(
          leading: const Icon(Icons.password),
          title: const Text('Edit Password'),
          onTap: () {
            showDialog(
                context: context,
                builder: (context) {
                  return StatefulBuilder(builder: (context, setState) {
                    return AlertDialog(
                      content: Form(
                          key: _formKey,
                          child: Column(
                            mainAxisSize: MainAxisSize.min,
                            children: [
                              TextFormField(
                                obscureText: true,
                                controller: formPasswordController,
                                validator: (value) {
                                  return value!.isNotEmpty
                                      ? null
                                      : "Enter password";
                                },
                                decoration:
                                    const InputDecoration(hintText: "Password"),
                              ),
                            ],
                          )),
                      title: const Text('Edit password'),
                      actions: <Widget>[
                        TextButton(
                            onPressed: () {
                              if (_formKey.currentState!.validate()) {
                                editPassword(user);
                                Navigator.of(context).pop();
                                formPasswordController.clear();
                              }
                            },
                            child: const Text('Edit')),
                      ],
                    );
                  });
                });
          },
        )
      ],
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Manage Users"),
      ),
      body: Visibility(
        visible: isLoaded,
        replacement: Center(
          child: LoadingAnimationWidget.fourRotatingDots(
              color: Colors.green, size: 100),
        ),
        child: RefreshIndicator(
          onRefresh: _refresh,
          child: ListView.builder(
              itemCount: users?.length,
              itemBuilder: (context, index) {
                return Dismissible(
                  direction: DismissDirection.endToStart,
                  confirmDismiss: (direction) async {
                    return await showDialog(
                      context: context,
                      builder: (BuildContext context) {
                        return AlertDialog(
                          title: const Text("Confirmation"),
                          content:
                              const Text("Voulez-vous vraiment supprimer?"),
                          actions: <Widget>[
                            TextButton(
                              onPressed: () => Navigator.of(context).pop(false),
                              child: const Text("Cancel"),
                            ),
                            TextButton(
                                onPressed: () =>
                                    Navigator.of(context).pop(true),
                                child: const Text("Supprimer")),
                          ],
                        );
                      },
                    );
                  },
                  onDismissed: (direction) {
                    // on supprime l'objet de la base
                    removeData(users![index].id);
                    setState(() {
                      if (users!.length != 1) {
                        ScaffoldMessenger.of(context).showSnackBar(SnackBar(
                            content: Text(
                                '${users![index].prenom} ${users![index].nom} supprimé(e)')));
                        users!.removeAt(index);
                      }
                    });
                  },
                  key: Key(users![index].id.toString()),
                  background: Container(
                    color: Colors.red[400],
                    child: const Align(
                        alignment: Alignment.centerRight,
                        child: Icon(
                          Icons.delete,
                          size: 40,
                          color: Colors.white,
                        )),
                  ),
                  child: InkWell(
                    onTap: () {
                      showModalBottomSheet(
                          context: context,
                          builder: ((context) {
                            return usersModalBottomSheet(users![index]);
                          }));
                    },
                    child: Container(
                      padding: const EdgeInsets.all(16),
                      child: Row(
                        children: [
                          Container(
                            width: 50,
                            height: 50,
                            decoration: BoxDecoration(
                                borderRadius: BorderRadius.circular(
                                  12,
                                ),
                                color: Colors.grey[300]),
                            child: Icon(
                              Icons.face_retouching_natural,
                              size: 30,
                              color: Color(
                                      (math.Random().nextDouble() * 0xFFFFFF)
                                          .toInt())
                                  .withOpacity(1.0),
                            ),
                          ),
                          const SizedBox(width: 16),
                          Expanded(
                            child: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text(
                                  users![index].login,
                                  style: const TextStyle(
                                      fontSize: 18,
                                      fontWeight: FontWeight.bold),
                                  maxLines: 2,
                                  overflow: TextOverflow.ellipsis,
                                ),
                                Text(
                                    '${users![index].prenom} ${users![index].nom}')
                              ],
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                );
              }),
        ),
      ),
      floatingActionButton: Visibility(
        visible: isLoaded,
        child: FloatingActionButton.extended(
          icon: const Icon(Icons.add),
          label: const Text("Add user"),
          onPressed: () {
            showDialog(
                context: context,
                builder: (context) {
                  return StatefulBuilder(builder: (context, setState) {
                    return AlertDialog(
                      content: Form(
                          key: _formKey,
                          child: Column(
                            mainAxisSize: MainAxisSize.min,
                            children: [
                              TextFormField(
                                autocorrect: false,
                                controller: formPrenomController,
                                validator: (value) {
                                  return value!.isNotEmpty
                                      ? null
                                      : "Entrez un prenom";
                                },
                                decoration:
                                    const InputDecoration(hintText: "Prenom"),
                              ),
                              TextFormField(
                                controller: formNomController,
                                validator: (value) {
                                  return value!.isNotEmpty
                                      ? null
                                      : "Entrez un nom";
                                },
                                decoration:
                                    const InputDecoration(hintText: "Nom"),
                              ),
                              TextFormField(
                                autocorrect: false,
                                controller: formLoginController,
                                validator: (value) {
                                  return value!.isNotEmpty
                                      ? null
                                      : "Entrez un login";
                                },
                                decoration:
                                    const InputDecoration(hintText: "Login"),
                              ),
                              TextFormField(
                                autocorrect: false,
                                obscureText: true,
                                controller: formPasswordController,
                                validator: (value) {
                                  return value!.isNotEmpty
                                      ? null
                                      : "Entrez un mot de passe";
                                },
                                decoration: const InputDecoration(
                                    hintText: "Mot de Passe"),
                              ),
                            ],
                          )),
                      title: const Text('Ajouter utilisateur'),
                      actions: <Widget>[
                        TextButton(
                            onPressed: () {
                              if (_formKey.currentState!.validate()) {
                                insertData();
                                Navigator.of(context).pop();
                              }
                            },
                            child: const Text('Ajouter')),
                      ],
                    );
                  });
                });
          },
        ),
      ),
    );
  }
}
