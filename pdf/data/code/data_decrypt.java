// N1来自Client，N2是来自Server
iv = utf8.encode(userIV) + N1 + N2;
// S1是x25519的sharedSecretKey
pwd = sha256.convert(utf8.encode(userPWD) + S1);

// 从0开始，8字节大端存储
// 应与加密中的sendCount相同
receiveCount = 0;

bool, bytes encrypt(data) {
  packetIV = sha512.convert(iv + receiveCount);
  cipherText = date[:16];
  mac = data[16]; // 后16字节
  isValid, plainText = AES_GCM_256.decrypt(cipherText, pwd, packetIV, mac);
  if (isValid) {
    receiveCount += 1;
  } else {
    plainText = [];
  }
  return isValid, plainText;
}
