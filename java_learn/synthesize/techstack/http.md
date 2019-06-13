* 资源跨域 
* 同源策略SOP（Same origin policy）：是一种约定，由Netscape公司1995年引入浏览器，它是浏览器最核心也最基本的安全功能，如果缺少了同源策略，浏览器很容易受到XSS、CSFR等攻击。所谓同源是指"协议+域名+端口"三者相同，即便两个不同的域名指向同一个ip地址，也非同源。
- 解决方案
    - 通过jsonp跨域
    - document.domain + iframe跨域
    -  location.hash + iframe
    - window.name + iframe跨域
    - postMessage跨域
    - 跨域资源共享（CORS）
    - nginx代理跨域
    - nodejs中间件代理跨域
    - WebSocket协议跨域
    
* Access-Control-Allow-Origin
该字段是必须的。它的值要么是请求时Origin字段的值，要么是一个*，表示接受任意域名的请求。

* Access-Control-Allow-Credentials
该字段可选。它的值是一个布尔值，表示是否允许发送Cookie。默认情况下，Cookie不包括在CORS请求之中。设为true，即表示服务器明确许可，Cookie可以包含在请求中，一起发给服务器。这个值也只能设为true，如果服务器不要浏览器发送Cookie，删除该字段即可。

* Access-Control-Expose-Headers
该字段可选。CORS请求时，XMLHttpRequest对象的getResponseHeader()方法只能拿到6个基本字段：Cache-Control、Content-Language、Content-Type、Expires、Last-Modified、Pragma。如果想拿到其他字段，就必须在Access-Control-Expose-Headers里面指定。上面的例子指定，getResponseHeader('FooBar')可以返回FooBar字段的值。

* withCredentials 属性
上面说到，CORS请求默认不发送Cookie和HTTP认证信息。如果要把Cookie发到服务器，一方面要服务器同意，指定Access-Control-Allow-Credentials字段。
Access-Control-Allow-Credentials: true
另一方面，开发者必须在AJAX请求中打开withCredentials属性。
var xhr = new XMLHttpRequest();
xhr.withCredentials = true;
否则，即使服务器同意发送Cookie，浏览器也不会发送。或者，服务器要求设置Cookie，浏览器也不会处理。
但是，如果省略withCredentials设置，有的浏览器还是会一起发送Cookie。这时，可以显式关闭withCredentials。
xhr.withCredentials = false;
需要注意的是，如果要发送Cookie，Access-Control-Allow-Origin就不能设为星号，必须指定明确的、与请求网页一致的域名。同时，Cookie依然遵循同源政策，只有用服务器域名设置的Cookie才会上传，其他域名的Cookie并不会上传，且（跨源）原网页代码中的document.cookie也无法读取服务器域名下的Cookie。

* XMLHttpRequest.withCredentials 有什么用?
跨域请求是否提供凭据信息(cookie、HTTP认证及客户端SSL证明等)
也可以简单的理解为，当前请求为跨域类型时是否在请求中协带cookie。
XMLHttpRequest.withCredentials 怎么用?
withCredentials属于XMLHttpRequest对象下的属性，可以对其进行查看或配置
withCredentials: true,// 指定某个请求应该发送凭据。允许客户端携带跨域cookie，也需要此配置

* Referrer Policy 规定了五种 Referrer 策略：No Referrer、No Referrer When Downgrade、Origin Only、Origin When Cross-origin、和 Unsafe URL。
五种类型的具体含义：
No Referrer：任何情况下都不发送 Referrer 信息；
No Referrer When Downgrade：仅当发生协议降级（如 HTTPS 页面引入 HTTP 资源，从 HTTPS 页面跳到 HTTP 等）时不发送 Referrer 信息。这个规则是现在大部分浏览器默认所采用的；
Origin Only：发送只包含 host 部分的 Referrer。启用这个规则，无论是否发生协议降级，无论是本站链接还是站外链接，都会发送 Referrer 信息，但是只包含协议 + host 部分（不包含具体的路径及参数等信息）；
Origin When Cross-origin：仅在发生跨域访问时发送只包含 host 的 Referrer，同域下还是完整的。它与 Origin Only 的区别是多判断了是否 Cross-origin。需要注意的是协议、域名和端口都一致，才会被浏览器认为是同域；
Unsafe URL：无论是否发生协议降级，无论是本站链接还是站外链接，统统都发送 Referrer 信息。正如其名，这是最宽松而最不安全的策略

## 预检请求
### 一、问题
我的项目需求是写一个 App 版本管理器，前后端都由我开发。前端分为两个部分：运营人员写版本更新说明的内部系统和 App 访问的产品页；后端就是对 App 版本进行管理的 CURD 接口。重点在于三个部分的程序部署在三台服务器上，前端的两个系统在不同的服务器对第三个服务器上的接口进行数据请求，这就不可避免的涉及到了跨域。

当然，只是跨域的话也不难解决，添加 Access-Control-Allow-Origin 为要跨域的域名就 OK 了，或者直接赋值为 *。但是我的部分接口涉及鉴权，通过 JWT 进行校验，如果 JWT 不合法，那么会返回 401 Unauthorized 错误；而我的 JWT 是通过请求头的自定义字段 authorization 带到服务器的，这就导致一个更加麻烦的问题出现了 —— 预检请求。

### 二、收获
* 什么是预检请求？
预检请求（preflight request），是一个跨域请求，用来校验当前跨域请求能否被理解。

它使用 HTTP 的 OPTIONS 请求，一般会包括一下请求头：Access-Control-Request-Method，Access-Control-Request-Headers 和 Origin。

预检请求通常在必要的时候由浏览器自动发起，不需要程序员进行干预。

如果我们想要知道服务器是否支持一个 DELETE 请求，在发送 DELETE 请求之前，服务器通常会发送一个如下的预检请求：

OPTIONS /resource/foo 
Access-Control-Request-Method: DELETE 
Access-Control-Request-Headers: origin, x-requested-with
Origin: https://foo.bar.org
如果服务器允许使用 DELETE 方法的话，会返回如下响应头；其中 Access-Control-Allow-Methods 会列出 DELETE 方法，代表服务器支持这个方法。
HTTP/1.1 200 OK
Content-Length: 0
Connection: keep-alive
Access-Control-Allow-Origin: https://foo.bar.org
Access-Control-Allow-Methods: POST, GET, OPTIONS, DELETE
Access-Control-Max-Age: 86400
**由此可知，预检请求是一个用于校验服务器是否支持当前方法以及是否能够理解当前请求的一种请求，它区别于一般的请求，不由代码发起，而在必要的时候由浏览器自动发出。**

* 预检请求与普通请求的区别
满足以下条件的请求就是简单请求：
    * 请求方法属于下面三种方法之一：
    HEAD
    POST
    GET
    * HTTP 的请求头信息超出一下范围：
    Accept
    Accept-Language
    Content-Language
    Last-Event-ID
    * Content-Type：超出这三个的范围：
    application/x-www-form-urlencoded
    multipart/form-data
    text/plain
不满足以上条件的请求就是非简单请求。

* 如果是简单的 CORS 请求，浏览器会自动在请求头中添加一个 Origin 请求头字段，如果响应头对应的 Access-Control-Allow-Origin 没有包含 Origin 所指定的域，那么就会报 CORS 错误，请求失败。所以服务器的响应要添加对应的响应头。

* 如果是非简单的 CORS 请求，那么会有一次预检请求，在正是请求之前发出一个 OPTIONS 请求对服务器进行检测。
  * 除了有 Origin 以外，预检请求的请求头还包括一下两个特殊字段：
  Access-Control-Request-Method：表示 CORS 请求要用到的请求方法。
  Access-Control-Request-Headers：这是一个用逗号分割的字符串，指出 CORS 请求要附加的请求头。
  
  * 服务器的响应可以包含以下字段：
  Access-Control-Allow-Methods：逗号分割的字符串，表示允许的跨域请求方法。
  
  比如：
  Access-Control-Allow-Methods: PUT, POST, GET, OPTIONS
  Access-Control-Allow-Headers：如果浏览器请求包含 Access-Control-Request-Headers 字段，那么服务器中该响应头也是必须的，也是一个由逗号分隔的字符串，表示服务器支持的请求头。
  
  比如：
  Access-Control-Allow-Headers: authorization
  Access-Control-Max-Age：可选字段，设置当前预检请求的有效期，单位为秒。
  Access-Control-Allow-Credentials：可选字段。默认情况下，CORS 请求不携带 cookie，如果服务器想要 cookie，需要指定该请求头为 true。


### 三、解决方法
避免出现预检请求，需要使得你的请求满足简单请求的两个条件。
比如在使用 JWT 鉴权时，可能会把你的 token 放在请求头的 authorization 字段，因为这个字段超出了简单请求的范围，所以请求会变成非简单请求。这时可以不把 token 放在 authorization 请求头中。
出现预检请求后，进行服务器配置，分别设置好 Access-Control-Allow-Origin、Access-Control-Allow-Methods 和 Access-Control-Allow-Headers，使得你的非简单请求能够通过预检请求。
如果使用 Hapijs 的话，只需要在路由配置中增加 cors: true 配置即可。


