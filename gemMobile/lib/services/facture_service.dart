// ignore_for_file: non_constant_identifier_names
import 'package:client_mobile/models/releves.dart';
import 'package:client_mobile/services/releves_service.dart';

enum TypeContrats {
  tempo6,
  tempo9,
  tempo12,
  tempo15,
  tempo18,
  tempo30,
  tempo36,
  base3,
  base6,
  base9,
  base12,
  base15,
  base18,
  base24,
  base30,
  base36,
  hcph6,
  hchp9,
  hchp12,
  hchp15,
  hchp18,
  hchp30,
  hchp36,
}

class FactureService {
  FactureService({required this.typeContrat, required this.date});

  TypeContrats typeContrat;
  DateTime date;
  List<Releves>? _releves;

  //****************POUR RÉCUPERER LES VALEUR***********
  int? _HCTB;
  int? _HPTB;
  int? _HCTW;
  int? _HPTW;
  int? _HCTR;
  int? _HPTR;

//****************Pour le contrat TEMPO***************
  final double _HCTB_prixKWh = 0.0970;
  final double _HPTB_prixKWh = 0.1249;
  final double _HCTW_prixKWh = 0.1140;
  final double _HPTW_prixKWh = 0.1508;
  final double _HCTR_prixKWh = 0.1216;
  final double _HPTR_prixKWh = 0.6712;

//****************Pour le contrat HC/HP***************
  final double _HC_prixKWh = 0.1615;
  final double _HP_prixKWh = 0.2228;

//***************Pour le contrat BASE*****************
  final double _BASE_prixKWh = 0.2062;

//****************Pour les taxes**********************
  final double _TCFE_taux = 0.00663;
  final double _CSPE_taux = 0.001;
  final double _CTA = 4.02;

  Future<bool> _getRelevesData() async {
    try {
      _releves = await RelevesService().getRelevesByDate(date);
      Releves lastReleve = _releves![_releves!.length - 1];
      Releves firstReleve = _releves![0];

      _HCTB = (lastReleve.bbrhcjb - firstReleve.bbrhcjb);
      _HPTB = (lastReleve.bbrhpjb - firstReleve.bbrhpjb);
      _HCTW = (lastReleve.bbrhcjw - firstReleve.bbrhcjw);
      _HPTW = (lastReleve.bbrhpjw - firstReleve.bbrhpjw);
      _HCTR = (lastReleve.bbrhcjr - firstReleve.bbrhcjr);
      _HPTR = (lastReleve.bbrhpjr - firstReleve.bbrhpjr);

      return true;
    } catch (exception) {
      print(exception);
    }
    return false;
  }

  Future<String> _calculFactureJourTempo(DateTime date) async {
    await _getRelevesData();
    double abo6 = (12.28 / 30); //abonnement 6kVA
    double abo9 = (15.33 / 30); //abonnement 9kVA
    double abo12 = (18.78 / 30); //abonnement 12kVA
    double abo15 = (21.27 / 30); //abonnement 15kVA
    double abo18 = (23.98 / 30); //abonnement 18kVA
    double abo30 = (36.06 / 30); //abonnement 30kVA
    double abo36 = (41.90 / 30); //abonnement 36kVA
    double facture = 0;

    facture += _HCTB! * _HCTB_prixKWh;
    facture += _HPTB! * _HPTB_prixKWh;

    facture += _HCTW! * _HCTW_prixKWh;
    facture += _HPTW! * _HPTW_prixKWh;

    facture += _HPTR! * _HPTR_prixKWh;
    facture += _HCTR! * _HCTR_prixKWh;

    switch (typeContrat) {
      case TypeContrats.tempo6:
        facture = (facture / 1000) + abo6;
        break;

      case TypeContrats.tempo9:
        facture = (facture / 1000) + abo9;
        break;

      case TypeContrats.tempo12:
        facture = (facture / 1000) + abo12;
        break;

      case TypeContrats.tempo15:
        facture = (facture / 1000) + abo15;
        break;

      case TypeContrats.tempo18:
        facture = (facture / 1000) + abo18;
        break;

      case TypeContrats.tempo30:
        facture = (facture / 1000) + abo30;
        break;

      case TypeContrats.tempo36:
        facture = (facture / 1000) + abo36;
        break;
    }

    String factureArroundi =
        double.parse(facture.toString()).toStringAsFixed(2);

    if (facture == 0.511) factureArroundi = "";
    return factureArroundi;
  }

  calculFactureJour(DateTime date) {
    if (typeContrat.toString().contains("tempo")) {
      return _calculFactureJourTempo(date);
    }

    if (typeContrat.toString().contains("base")) {
      return _calculFactureBase(date);
    }

    if (typeContrat.toString().contains("hchp")) {
      return _calculFactureHCHP(date);
    }
  }

  _calculFactureHCHP(DateTime date) async {
    await _getRelevesData();
  }

  _calculFactureBase(DateTime date) async {
    await _getRelevesData();
    double abo3 = (9.13 / 30); //abonnement 3kVA
    double abo6 = (11.93 / 30); //abonnement 6kVA
    double abo9 = (14.86 / 30); //abonnement 9kVA
    double abo12 = (17.88 / 30); //abonnement 12kVA
    double abo15 = (20.85 / 30); //abonnement 15kVA
    double abo18 = (23.67 / 30); //abonnement 18kVA
    double abo24 = (29.82 / 30); //abonnement 24kVA
    double abo30 = (35.83 / 30); //abonnement 30kVA
    double abo36 = (41.71 / 30); //abonnement 36kVA
    double facture = 0;

    facture += _HCTB! * _BASE_prixKWh;
    facture += _HPTB! * _BASE_prixKWh;

    facture += _HCTW! * _BASE_prixKWh;
    facture += _HPTW! * _BASE_prixKWh;

    facture += _HPTR! * _BASE_prixKWh;
    facture += _HCTR! * _BASE_prixKWh;

    switch (typeContrat) {
      case TypeContrats.base3:
        facture = (facture / 1000) + abo3;
        break;

      case TypeContrats.base6:
        facture = (facture / 1000) + abo6;
        break;

      case TypeContrats.base9:
        facture = (facture / 1000) + abo9;
        break;

      case TypeContrats.base12:
        facture = (facture / 1000) + abo12;
        break;

      case TypeContrats.base15:
        facture = (facture / 1000) + abo15;
        break;

      case TypeContrats.base18:
        facture = (facture / 1000) + abo18;
        break;

      case TypeContrats.base24:
        facture = (facture / 1000) + abo24;
        break;

      case TypeContrats.base30:
        facture = (facture / 1000) + abo30;
        break;

      case TypeContrats.base36:
        facture = (facture / 1000) + abo36;
        break;
    }

    String factureArroundi =
        double.parse(facture.toString()).toStringAsFixed(2);

    if (facture == 0.511) factureArroundi = "";
    return factureArroundi;
  }
}
