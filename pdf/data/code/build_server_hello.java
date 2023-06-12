bytes handleDH(clientHello) {
  algorithm = clientHello['supported_groups'];
  serverKeyPair = newServerKeyPair;
  sharedSecretKey = algorithm.sharedSecretKey(
        keyPair: serverKeyPair,
        remotePublicKey: clientHello['pre_shared_key'],
      );
  return serverKeyPair.publicKey, sharedSecretKey;
}

bytes buildServerHello(clientHello) {
  if (!serverCheck(clientHello)) {
    forwardFallbackWebsite();
    return;
  }
  serverPublicKey, S1 = handleDH(clientHello);
  serverHello['pre_shared_key'] = serverPublicKey;

  // 此时 serverHello 中的 random
  // 也同样全置为0
  serverHello['random'] = 0;
  // 此时 clientHello 中的random 不置0
  iv = utf8.encode(userIV) + clientHello + serverHello;
  pwd = utf8.encode(userPwd) + S1;

  serverFakeRandom, N2 = buildFakeRandom(iv, pwd);
  serverHello['random'] = serverFakeRandom
  return serverFakeRandom;
}
