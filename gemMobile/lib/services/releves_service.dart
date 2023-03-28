import 'dart:convert';

import 'package:http/http.dart' as http;

import '../models/releves.dart';

class RelevesService {
  final _client = http.Client();
  static final _baseUri =
      Uri.parse("http://172.16.79.128:8080/gemRestfulAPI-1.0/api/releve/");

  Future<List<Releves>?> getRelevesByDate(DateTime date) async {
    var url = Uri.parse('${_baseUri}getByDate');
    print("$url : {'date': '${date.year}-${date.month}-${date.day}'}");
    var response = await _client.post(url,
        headers: {"Content-Type": "application/json"},
        body: "{'date': '${date.year}-${date.month}-${date.day}'}");

    if (response.statusCode == 200) {
      return listRelevesFromJson(response.body);
    }
    return null;
  }
}
