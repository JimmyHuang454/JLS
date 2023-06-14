// random 均不填充0
iv = utf8.encode(userIV) + clientHello + serverHello;
pwd = sha256.convert(utf8.encode(userPWD) + S1);

// 从0开始，4字节大端存储
sendCount = 0;

bytes,bytes encrypt(data){
  packetIV = sha512.convert(iv + sendCount);
  mac, cipherText = AES_GCM_256.encrypt(data, pwd, packetIV);
  sendCount += 1;
  return cipherText, mac;
}
