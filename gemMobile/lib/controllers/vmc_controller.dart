import 'package:client_mobile/services/vmc_service.dart';
import 'package:flutter/material.dart';

import '../models/vmc.dart';

class VmcController extends ChangeNotifier {
  getAllVmc() async {
    List<Vmc>? vmcs = await VmcService().getAllVmc();
    return vmcs;
  }

  removeVmc(id) {
    return VmcService().removeVmc(id);
  }

  insertVmc(Vmc vmc) {
    return VmcService().insertVmc(vmc);
  }
}
