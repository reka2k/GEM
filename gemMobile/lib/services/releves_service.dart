import 'package:http/http.dart' as http;
import '../models/releves.dart';
import 'config.dart';

class RelevesService {
  final _client = http.Client();

  Future<List<Releves>?> getRelevesByDate(DateTime date) async {
    String address = await _address;
    int port = await _port;
    Uri url = Uri.parse(
        "http://$address:$port/gemRestfulAPI-1.0/api/releve/getByDate");

    var response = await _client.post(url,
        headers: {"Content-Type": "application/json"},
        body: "{'date': '${date.year}-${date.month}-${date.day}'}");

    print(response.statusCode);
    print(url);

    if (response.statusCode == 200) {
      return listRelevesFromJson(response.body);
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
