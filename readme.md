# JLS

[协议PDF](https://github.com/JimmyHuang454/JLS/blob/master/pdf/thuthesis-example.pdf)

# 总结

- 无需证书，伪装成任意网站
- 超低延迟 0-RTT/1-RTT
- 行为表现跟 TLS 完全一样
- 具有 TLS 的安全性
- 不能被识别出来，无视主动探测
- 支持 QUIC；传输层 + 安全层 延迟仅需 0-RTT/1-RTT
- 无痛替换安全层

## 推荐工具

[Sing-Box](https://github.com/JimmyHuang454/sing-box)

[Leaf](https://github.com/eycorsican/leaf)

## 开发者库

### TCP

[rustls](https://github.com/vincentliu77/rustls-jls) (Rust)

[crypto/tls](https://github.com/JimmyHuang454/JLS-go) (Golang)

### QUIC

[quic-go](https://github.com/JimmyHuang454/quic-go) (Golang)

[quinn-jls](https://github.com/vincentliu77/quinn-jls) (Rust)
