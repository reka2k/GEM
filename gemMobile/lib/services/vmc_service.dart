import 'package:client_mobile/models/vmc.dart';
import 'package:client_mobile/services/config.dart';
import 'package:http/http.dart' as http;

class VmcService {
  final _client = http.Client();

  Future<List<Vmc>?> getAllVmc() async {
    String address = await _address;
    int port = await _port;
    Uri url = Uri.parse(
        "http://$address:$port/gemRestfulAPI-1.0/api/vmc/getAll"); // on concatene la fonction voulu a l'URI de base
    var response = await _client.get(url);

    if (response.statusCode == 200) {
      var json = response.body;
      return vmcsFromJson(json);
    }
    return null;
  }

  void removeVmc(id) async {
    String address = await _address;
    int port = await _port;
    Uri url =
        Uri.parse("http://$address:$port/gemRestfulAPI-1.0/api/vmc/remove/$id");
    try {
      await _client.delete(url);
    } catch (ex) {
      print(ex);
    }
  }

  Future<bool> insertVmc(Vmc vmc) async {
    String address = await _address;
    int port = await _port;
    Uri url =
        Uri.parse("http://$address:$port/gemRestfulAPI-1.0/api/vmc/insert");
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

  Future<String> get _address async {
    return await ConfigService().address;
  }

  Future<int> get _port async {
    return await ConfigService().port;
  }
}
