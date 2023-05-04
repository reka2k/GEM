import 'dart:convert';
import 'dart:io';
import 'package:path_provider/path_provider.dart';

class ConfigService {
  Future<String> get _localPath async {
    final directory = await getApplicationDocumentsDirectory();
    return directory.path;
  }

  Future<File> get _localConfigFile async {
    final path = await _localPath;
    return File('$path/config.json');
  }

  Future<Map<String, dynamic>?> readConfigFile() async {
    try {
      final configFile = await _localConfigFile;

      final config = await configFile.readAsString();

      return jsonDecode(config);
    } catch (e) {
      return null;
    }
  }

  Future<File> writeConfigFile(String address, int port) async {
    final config = json.encode({"address": address, "port": port});

    final File file = await _localConfigFile;

    return file.writeAsString(config);
  }

  Future<dynamic> _defaultConfigFile() async {
    var configFile = await readConfigFile();

    if (configFile == null) {
      await ConfigService().writeConfigFile("172.16.79.128", 8080);
    }

    return ConfigService().readConfigFile();
  }

  Future<String> get address async {
    await _defaultConfigFile();

    var data = await readConfigFile() as Map<String, dynamic>;

    return data["address"];
  }

  Future<int> get port async {
    await _defaultConfigFile();

    var data = await readConfigFile() as Map<String, dynamic>;

    return data["port"];
  }
}
