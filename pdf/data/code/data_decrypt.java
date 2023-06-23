iv = utf8.encode(userIV);
// N1来自Client，N2是来自Server，S1是x25519的sharedSecretKey
pwd = sha256.convert(utf8.encode(userPWD) + S1 + N1 + N2);

// 从0开始，8字节大端存储
sendCount = 0;

bytes encrypt(data) {
  packetIV = sha512.convert(iv + receiveID);
  mac = data[0:16]; // 前16字节
  cipherText = date[16:];
  mac, cipherText = AES_GCM_256.decrypt(cipherText, pwd, packetIV, mac);
  receiveID += 1;
  return plainText;
}
