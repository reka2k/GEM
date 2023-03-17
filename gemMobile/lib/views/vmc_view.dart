import 'package:client_mobile/controllers/vmc_controller.dart';
import 'package:client_mobile/models/capteur_humidite.dart';
import 'package:client_mobile/models/capteur_temperature.dart';
import 'package:client_mobile/models/vmc.dart';
import 'package:flutter/material.dart';

class VmcPage extends StatefulWidget {
  const VmcPage({super.key});

  @override
  State<VmcPage> createState() => _VmcPageState();
}

class _VmcPageState extends State<VmcPage> {
  List<Vmc>? vmcs; // Liste de VMCs

  // sert a savoir si les donnes ont ete fetch ou non -> si non on affiche une progressbar
  bool isLoaded = false;

  //Differents controller pour le form d'ajout(modif aussi possiblement??) d'utilisateur
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final formCapteurHumiditeController = TextEditingController();
  final formCapteurTemperatureController = TextEditingController();

  //Fetch les donnees a l'initialisation de la vue
  @override
  void initState() {
    super.initState();
    getData();
  }

  //func qui recupere les donnees
  getData() async {
    dynamic tempVmcs = await VmcController().getAllVmc();

    if (tempVmcs != null) {
      setState(() {
        isLoaded = true;
      });
      vmcs = tempVmcs as List<Vmc>;
    }
  }

  //Supprime user
  removeData(id) async {
    if (vmcs?.length == 1) {
      return "Action impossible";
    } else {
      await VmcController().removeVmc(id);
    }
  }

  insertData() async {
    Vmc vmc = Vmc(
        statut: 1,
        capteurTemperature: CapteurTemperature(id: 5),
        capteurHumidite: CapteurHumidite(id: 4));

    var response = await VmcController().insertVmc(vmc);
    if (response) {
      getData();
    } else {
      print('err');
    }

    formCapteurHumiditeController.clear;
    formCapteurTemperatureController.clear();
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
              itemCount: vmcs?.length,
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
                    removeData(vmcs![index].id);
                    setState(() {
                      if (vmcs!.length != 1) {
                        ScaffoldMessenger.of(context).showSnackBar(
                            const SnackBar(content: Text('VMC supprimé(e)')));
                        vmcs!.removeAt(index);
                      }
                    });
                  },
                  key: Key(vmcs![index].id.toString()),
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
                            Icons.wind_power,
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
                                'VMC ${vmcs![index].id}',
                                style: const TextStyle(
                                    fontSize: 18, fontWeight: FontWeight.bold),
                                maxLines: 2,
                                overflow: TextOverflow.ellipsis,
                              ),
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
                                controller: formCapteurHumiditeController,
                                validator: (value) {
                                  return value!.isNotEmpty
                                      ? null
                                      : "Entrez un prenom";
                                },
                                decoration:
                                    const InputDecoration(hintText: "Prenom"),
                              ),
                              TextFormField(
                                controller: formCapteurTemperatureController,
                                validator: (value) {
                                  return value!.isNotEmpty
                                      ? null
                                      : "Entrez un nom";
                                },
                                decoration:
                                    const InputDecoration(hintText: "Nom"),
                              ),
                            ],
                          )),
                      title: const Text('Ajouter VMC'),
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
