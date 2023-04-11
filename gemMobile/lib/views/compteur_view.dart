import 'package:client_mobile/models/solaire.dart';
import 'package:client_mobile/services/facture_service.dart';
import 'package:client_mobile/widgets/solaire_chart.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:loading_animation_widget/loading_animation_widget.dart';

import '../controllers/solaire_controller.dart';

import 'package:intl/intl.dart';

class CompteurPage extends StatefulWidget {
  const CompteurPage({super.key});

  @override
  State<CompteurPage> createState() => _CompteurPageState();
}

class _CompteurPageState extends State<CompteurPage> {
  List<Solaire> data = List.empty();
  bool isLoaded = false;
  DateTime selectedDate = DateTime(2022, 11, 16);
  String facture = "";
  TypeContrats typeContrat = TypeContrats.tempo9;

  @override
  void initState() {
    super.initState();
    getSolaireData(selectedDate);
    getFacture(selectedDate);
  }

  getFacture(DateTime date) async {
    var tmp = await FactureService(typeContrat: typeContrat, date: selectedDate)
        .calculFactureJour(date);
    setState(() {
      facture = tmp;
    });
  }

  getSolaireData(DateTime date) async {
    setState(() {
      isLoaded = false;
    });
    var res = await SolaireController().getListSolaire(date);

    if (res != null && res.isNotEmpty) {
      setState(() {
        data = res;
        isLoaded = true;
      });
    } else {
      setState(() {
        data = [];
        isLoaded = true;
        facture = "";
      });
      // ignore: use_build_context_synchronously
      showDialog(
          context: context,
          builder: (context) {
            return StatefulBuilder(builder: (context, setState) {
              return AlertDialog(
                  content: SizedBox(
                height: 55,
                width: 200,
                child: Column(
                  children: [
                    const Text("No data available at this date"),
                    Container(
                      padding: const EdgeInsets.only(top: 5),
                      alignment: AlignmentDirectional.bottomEnd,
                      child: TextButton(
                          onPressed: () => {Navigator.of(context).pop()},
                          child: const Text("OK")),
                    )
                  ],
                ),
              ));
            });
          });
    }
  }

  Future<void> _refresh() async {
    getSolaireData(selectedDate);
    getFacture(selectedDate);
  }

  getContractsList() {
    return TypeContrats.values.map((TypeContrats contrat) {
      return DropdownMenuEntry<TypeContrats>(
          label: describeEnum(contrat).toString(), value: contrat);
    }).toList();
  }

  Future<void> _selectDate(BuildContext context) async {
    final DateTime? picked = await showDatePicker(
        context: context,
        initialDate: selectedDate,
        firstDate: DateTime(2022, 8),
        lastDate: DateTime(2101));
    if (picked != null && picked != selectedDate) {
      setState(() {
        selectedDate = picked;
      });
      getSolaireData(selectedDate);
      getFacture(selectedDate);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Visibility(
          visible: isLoaded,
          replacement: LoadingAnimationWidget.threeRotatingDots(
              color: Colors.green, size: 100),
          child: Scaffold(
            body: RefreshIndicator(
                onRefresh: () => _refresh(),
                child: Column(children: [
                  Container(
                    decoration: const BoxDecoration(
                        color: Colors.green,
                        borderRadius: BorderRadius.only(
                            bottomLeft: Radius.circular(15),
                            bottomRight: Radius.circular(15))),
                    margin: const EdgeInsets.only(bottom: 10),
                    padding: const EdgeInsets.only(top: 40),
                    height: 80,
                    child: Center(
                      child: InkWell(
                        onTap: () => _selectDate(context),
                        child: Text(
                            style: const TextStyle(
                                color: Colors.white,
                                fontWeight: FontWeight.bold,
                                fontSize: 24),
                            "Current date: ${DateFormat.yMMMMd().format(selectedDate)}"),
                      ),
                    ),
                  ),
                  Container(
                      decoration:
                          BoxDecoration(borderRadius: BorderRadius.circular(5)),
                      margin: const EdgeInsets.only(top: 10, bottom: 30),
                      height: 500,
                      child: SolaireChart(data: data)),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      DropdownMenu<TypeContrats>(
                        enableSearch: true,
                        leadingIcon: const Icon(Icons.note_add),
                        hintText: "Contract",
                        label: null,
                        menuHeight: 300,
                        width: 200,
                        dropdownMenuEntries: getContractsList(),
                        onSelected: (newContract) {
                          setState(() {
                            typeContrat = newContract!;
                          });
                          getFacture(selectedDate);
                        },
                      ),
                      Container(
                        margin: const EdgeInsets.only(left: 35),
                        decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(5),
                            color: Colors.green),
                        width: 130,
                        height: 60,
                        child: Center(
                          child: Column(
                            children: [
                              Container(
                                padding: const EdgeInsets.only(top: 3),
                                child: const Text(
                                    style: TextStyle(
                                        color: Colors.white,
                                        fontWeight: FontWeight.bold,
                                        fontSize: 16),
                                    "Daily bill:"),
                              ),
                              Text(
                                  style: const TextStyle(
                                      color: Colors.white,
                                      fontWeight: FontWeight.bold,
                                      fontSize: 30),
                                  "$facture €"),
                            ],
                          ),
                        ),
                      ),
                    ],
                  ),
                ])),
          ),
        ),
      ),
    );
  }
}
