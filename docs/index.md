# OpenApi SDK

为了业务端便捷接入农卷风开放服务，我们发布并开源了 [openapi-sdk](https://github.com/njf-dev/njf-openapi-sdk){:target="_blank"}，支持 Java、Kotlin 等 JVM 语言开发的后端服务。理论上 JVM 前端也可以使用，但是因为 accessToken 需要在后端维护，所以建议仅在服务端集成。

OpenApi-SDK 是对 [OpenApi 接口](https://docs.njf2016.com/njf-open-service-docs/latest/open-api/open-api-docs/) 的封装，所以在本 sdk 不支持的开发语言环境，您依然可以使用 http 实现同样的功能。

## 开源协议

![license](https://image2.njf2016.com/docs/license-light.jpeg#only-light)
![license](https://image2.njf2016.com/docs/license-dark.jpeg#only-dark)

```
MIT License

Copyright (c) 2023 农卷风技术

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```