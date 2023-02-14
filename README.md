# njf-openapi-sdk

[![](https://jitpack.io/v/com.njf2016/njf-openapi-sdk.svg)](https://jitpack.io/#com.njf2016/njf-openapi-sdk)

农卷风开放平台 openapi-sdk

# 使用

发布在 Jitpack，支持 gradle、maven 等，请通过 [Jitpack openapi-sdk](https://jitpack.io/#com.njf2016/njf-openapi-sdk) 引入。

- 添加 Jitpack 仓库

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

- 正式版

```groovy
dependencies {
    implementation 'com.njf2016:njf-openapi-sdk:<VERSION>'
}
```

- 开发版

开发版可能支持最新功能、修复一些问题，但测试可能不到位，可能会存在一些 bug。

```groovy
dependencies {
    implementation 'com.njf2016:njf-openapi-sdk:main-SNAPSHOT'
}
```