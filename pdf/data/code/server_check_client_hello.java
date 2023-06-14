bool, bytes parseFakeRandom(clientFakeRandom, iv, pwd){
  iv = sha512.convert(iv);
  pwd = sha256.convert(pwd);

  mac = clientFakeRandom[0:16];
  cipherText = clientFakeRandom[16:32];
  isValid, n = AES_GCM_256.decrypt(cipherText, mac, pwd, iv);
  return isValid, n;
}

bool serverCheck(clientHello){
  clientFakeRandom = clientHello['random'];
  // 32字节全置为0
  clientHello['random'] = 0;
  // 防止Client Hello被修改
  iv = utf8.encode(userIV) + clientHello;
  pwd = utf8.encode(userPwd);

  isValid, N1 = parseFakeRandom(clientFakeRandom, iv, pwd);
  return isValid;
}
