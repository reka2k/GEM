import 'package:flutter/material.dart';
import 'package:syncfusion_flutter_charts/charts.dart';

import '../models/solaire.dart';

// ignore: must_be_immutable
class SolaireChart extends StatefulWidget {
  List<Solaire> data;
  SolaireChart({super.key, required this.data});

  @override
  State<SolaireChart> createState() => _SolaireChartState();
}

class _SolaireChartState extends State<SolaireChart> {
  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 500,
      width: 750,
      child: SfCartesianChart(
          zoomPanBehavior: ZoomPanBehavior(
              enableDoubleTapZooming: true,
              enablePinching: true,
              enableMouseWheelZooming: true,
              zoomMode: ZoomMode.x,
              enableSelectionZooming: true),
          tooltipBehavior: TooltipBehavior(
            enable: true,
          ),
          primaryXAxis: DateTimeAxis(),
          series: <CartesianSeries<Solaire, DateTime>>[
            SplineAreaSeries<Solaire, DateTime>(
                borderColor: Colors.green[700],
                borderWidth: 2,
                name: "Consommation",
                color: Colors.green,
                dataSource: widget.data,
                xValueMapper: (Solaire solaire, _) =>
                    DateTime.parse(solaire.date),
                yValueMapper: (Solaire solaire, _) => solaire.conso)
          ]),
    );
  }
}
