import 'package:client_mobile/models/vmc.dart';
import 'package:http/http.dart' as http;

class VmcService {
  final _client = http.Client();
  static final _baseUri =
      Uri.parse("http://172.16.79.128:8080/gemRestfulAPI-1.0/api/vmc/");

  Future<List<Vmc>?> getAllVmc() async {
    var url = Uri.parse(
        '${_baseUri}getAll'); // on concatene la fonction voulu a l'URI de base
    var response = await _client.get(url);

    if (response.statusCode == 200) {
      var json = response.body;
      return vmcsFromJson(json);
    }
    return null;
  }

  void removeVmc(id) async {
    var url = Uri.parse('${_baseUri}remove/$id');
    try {
      await _client.delete(url);
    } catch (ex) {
      print(ex);
    }
  }

  Future<bool> insertVmc(Vmc vmc) async {
    var url = Uri.parse('${_baseUri}insert');
    try {
      var reponse = await _client.post(url,
          headers: {"Content-Type": "application/json"}, body: vmcToJson(vmc));
      if (reponse.statusCode == 200) return true;
      return false;
    } catch (ex) {
      print(ex);
    }
    return false;
  }
}
