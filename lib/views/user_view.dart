import 'package:client_mobile/controllers/user_controller.dart';
import 'package:client_mobile/models/user.dart';
import 'package:flutter/material.dart';

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

    if (tempUsers != null) {
      setState(() {
        isLoaded = true;
      });
      users = tempUsers as List<User>;
    }
  }

  //Supprime user
  removeData(id) async {
    if (users?.length == 1) {
      return "Action impossible";
    } else {
      await UserController().removeUser(id);
    }
  }

  insertData() async {
    User user = User(
        nom: formNomController.text,
        prenom: formPrenomController.text,
        login: formLoginController.text,
        mdp: formPasswordController.text);

    var response = await UserController().insertUser(user);
    if (response) {
      getData();
    } else {
      print('err');
    }

    formNomController.clear();
    formPrenomController.clear();
    formLoginController.clear();
    formPasswordController.clear();
  }

  Future<void> _refresh() async {
    getData();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Visibility(
        visible: isLoaded,
        replacement: const Center(child: CircularProgressIndicator()),
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
                              child: const Text("Annuler"),
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
                          child: const Icon(
                            Icons.person,
                            size: 40,
                            color: Colors.green,
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
                                    fontSize: 18, fontWeight: FontWeight.bold),
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
                );
              }),
        ),
      ),
      floatingActionButton: Visibility(
        visible: isLoaded,
        child: FloatingActionButton(
          child: const Icon(Icons.add),
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
