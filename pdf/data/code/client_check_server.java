bool clientCheck(serverHello){
  serverFakeRandom = serverHello['random'];
  serverHello['random'] = 0;
  // 此时 clientHello 中的random 不置0
  serverPublicKey, S1 = handleDH(serverHello);
  iv = utf8.encode(userIV) + clientHello + serverHello;
  pwd = utf8.encode(userPwd) + S1;
  isValid, N2 = parseFakeRandom(serverFakeRandom, iv, pwd);
  return isValid;
}
