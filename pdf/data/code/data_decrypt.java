iv = utf8.encode(userIV);
// N1来自Client，N2是来自Server，S1是x25519的sharedSecretKey
pwd = sha256.convert(utf8.encode(userPWD) + S1 + N1 + N2);

// 从0开始，8字节大端存储
// 应与加密中的sendCount相同
receiveCount = 0;

bytes encrypt(data) {
  packetIV = sha512.convert(iv + receiveCount);
  mac = data[0:16]; // 前16字节
  cipherText = date[16:];
  mac, plainText = AES_GCM_256.decrypt(cipherText, pwd, packetIV, mac);
  receiveCount += 1;
  return plainText;
}
