# 使用

## 引入

[![](https://jitpack.io/v/com.njf2016/njf-openapi-sdk.svg)](https://jitpack.io/#com.njf2016/njf-openapi-sdk){:target="_blank"}

发布在 Jitpack，支持 gradle、maven 等，请通过 [Jitpack openapi-sdk](https://jitpack.io/#com.njf2016/njf-openapi-sdk){:target="_blank"} 引入。

- 添加 Jitpack 仓库

=== "gradle"

    ```groovy
    allprojects {
        repositories {
            maven {url "https://jitpack.io"}
        }
    }
    ```

=== "maven"

    ```maven
    <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
    ```

- 正式版

请填入上方版本号，或跳转到 [Jitpack openapi-sdk](https://jitpack.io/#com.njf2016/njf-openapi-sdk){:target="_blank"} 查看历史版本。

=== "gradle"

    ```groovy
    dependencies {
        implementation "com.njf2016:njf-openapi-sdk:<VERSION>"
    }
    ```

=== "maven"

    ```maven
	<dependency>
	    <groupId>com.njf2016</groupId>
	    <artifactId>njf-openapi-sdk</artifactId>
	    <version>Tag</version>
	</dependency>
    ```

- 开发版

开发版可能支持最新功能、修复一些问题，但测试可能不到位，可能会存在一些 bug。

=== "gradle"

    ```groovy
    dependencies {
        implementation "com.njf2016:njf-openapi-sdk:main-SNAPSHOT"
    }
    ```

=== "maven"

    ```maven
	<dependency>
	    <groupId>com.njf2016</groupId>
	    <artifactId>njf-openapi-sdk</artifactId>
	    <version>main-SNAPSHOT</version>
	</dependency>
    ```

---

## 范例

可参考 [仓库 Demo](https://github.com/njf-dev/njf-openapi-sdk/tree/main/demo){:target="_blank"}

1. 初始化 OpenApi

    ```java
    // 找个合适的地方初始化 SDK
    OpenApi.init(appId, secret);
    ```
   
2. 获取 accessToken（可选）

    ```java
    // 获取 accessToken
    String accessToken = OpenApi.getAccessToken();
    ```

    !!! note

        SDK 会在内部维护好 accessToken，正常情况下您无需关心 accessToken 过期。

        SDK 只在每次调用接口，但农卷风开放服务返回 401 的情况下进行重签 accessToken，所以无需担心调用频率超限。

3. 调用对应业务接口（可选）

    可按照业务需求，调用接口。
    ```java
    // 读取网关列表
    List<Concentrator> concentrators = OpenApi.getIntelliConcentrators();
    ```

!!! tip
   
      其他可参考 [接口文档](https://docs.njf2016.com/njf-openapi-sdk/api/)

