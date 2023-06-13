// random 均不填充0
iv = utf8.encode(userIV) + clientHello + serverHello;
iv = sha512.convert(iv);
pwd = utf8.encode(userPWD) + S1;

sendCount = 0;

bytes,bytes encrypt(data){
  // 从0开始
  packetPWD = sha256.convert(pwd + sendCount);
  mac, cipherText = AES_GCM_256.encrypt(data, packetPWD, iv);
  sendCount += 1;
  return cipherText, mac;
}
