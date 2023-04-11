import 'package:client_mobile/services/releves_service.dart';
import 'package:flutter/material.dart';

class RelevesController extends ChangeNotifier {
  getListReleves(DateTime date) async {
    return RelevesService().getRelevesByDate(date);
  }
}
