import 'package:http/http.dart' as http;

import '../models/solaire.dart';
import 'config.dart';

class SolaireService {
  final _client = http.Client();

  Future<List<Solaire>?> getSolairesByDate(DateTime date) async {
    String address = await _address;
    int port = await _port;
    Uri url = Uri.parse(
        "http://$address:$port/gemRestfulAPI-1.0/api/solaire/getByDate");
    var response = await _client.post(url,
        headers: {"Content-Type": "application/json"},
        body: "{'date': '${date.year}-${date.month}-${date.day}'}");

    if (response.statusCode == 200) {
      return listSolaireFromJson(response.body);
    }
    return null;
  }

  Future<String> get _address async {
    return await ConfigService().address;
  }

  Future<int> get _port async {
    return await ConfigService().port;
  }
}
