import 'package:client_mobile/controllers/releves_controller.dart';
import 'package:client_mobile/widgets/releves_chart.dart';
import 'package:flutter/material.dart';
import 'package:loading_animation_widget/loading_animation_widget.dart';

import '../models/releves.dart';

class CompteurPage extends StatefulWidget {
  const CompteurPage({super.key});

  @override
  State<CompteurPage> createState() => _CompteurPageState();
}

class _CompteurPageState extends State<CompteurPage> {
  List<Releves> data = List.empty();
  bool isLoaded = false;
  DateTime selectedDate = DateTime(2022, 11, 16);

  @override
  void initState() {
    super.initState();
    getRelevesData(selectedDate);
  }

  getRelevesData(DateTime date) async {
    setState(() {
      isLoaded = false;
    });
    var res = await RelevesController().getListReleves(date);

    if (res != null && res.isNotEmpty) {
      setState(() {
        data = res;
        isLoaded = true;
      });
    } else {
      setState(() {
        data = [];
        isLoaded = true;
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
      getRelevesData(selectedDate);
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
                onRefresh: () => getRelevesData(selectedDate),
                child: Column(children: [
                  Container(
                      padding: const EdgeInsets.only(top: 40),
                      height: 400,
                      child: RelevesChart(data: data)),
                  ElevatedButton(
                    onPressed: () => _selectDate(context),
                    child: const Text('Select date'),
                  ),
                ])),
          ),
        ),
      ),
    );
  }
}
