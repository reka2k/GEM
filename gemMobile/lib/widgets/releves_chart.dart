import 'package:flutter/material.dart';
import 'package:syncfusion_flutter_charts/charts.dart';

import '../models/releves.dart';

// ignore: must_be_immutable
class RelevesChart extends StatefulWidget {
  List<Releves> data;
  RelevesChart({super.key, required this.data});

  @override
  State<RelevesChart> createState() => _RelevesChartState();
}

class _RelevesChartState extends State<RelevesChart> {
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
          series: <CartesianSeries<Releves, DateTime>>[
            SplineAreaSeries<Releves, DateTime>(
                borderColor: Colors.green[700],
                borderWidth: 2,
                name: "PAPP",
                color: Colors.green,
                dataSource: widget.data,
                xValueMapper: (Releves releves, _) => releves.date,
                yValueMapper: (Releves releve, _) => releve.papp)
          ]),
    );
  }
}
