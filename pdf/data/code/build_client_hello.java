bytes, bytes buildFakeRandom(iv, pwd) {
  iv = sha512.convert(iv);
  pwd = sha256.convert(pwd);

  // n 长度为16字节
  n = buildRandomBytes(16);

  // mac，cipherText均为默认的16字节
  mac, cipherText = AES_GCM_256.encrypt(n, pwd, iv);

  fakeRandom = mac + cipherText;
  return fakeRandom, n;
}

bytes buildClientHello() {
  // clientHello是将要发送给server的clientHello
  // 由于未生成fakeRandom，
  // 所以此时clientHello中的random 32 字节应该全部填充为0
  clientHello['random'] = 0;
  clientHello['shared_key'] = buildDH();
  // 此时整个clientHello都要参与运算，以防篡改
  // 特别要注意包含 shared_key
  iv = utf8.encode(userIV) + clientHello;

  clientFakeRandom, N1 = buildFakeRandom(iv, utf8.encode(userPwd));

  clientHello['random'] = clientFakeRandom;
  return clientHello;
}
