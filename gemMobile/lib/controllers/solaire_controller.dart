import 'package:flutter/material.dart';

import '../services/solaire_service.dart';

class SolaireController extends ChangeNotifier {
  getListSolaire(DateTime date) async {
    return SolaireService().getSolairesByDate(date);
  }
}
