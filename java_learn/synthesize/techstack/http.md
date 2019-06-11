（1）Access-Control-Allow-Origin

该字段是必须的。它的值要么是请求时Origin字段的值，要么是一个*，表示接受任意域名的请求。

（2）Access-Control-Allow-Credentials

该字段可选。它的值是一个布尔值，表示是否允许发送Cookie。默认情况下，Cookie不包括在CORS请求之中。设为true，即表示服务器明确许可，Cookie可以包含在请求中，一起发给服务器。这个值也只能设为true，如果服务器不要浏览器发送Cookie，删除该字段即可。

（3）Access-Control-Expose-Headers

该字段可选。CORS请求时，XMLHttpRequest对象的getResponseHeader()方法只能拿到6个基本字段：Cache-Control、Content-Language、Content-Type、Expires、Last-Modified、Pragma。如果想拿到其他字段，就必须在Access-Control-Expose-Headers里面指定。上面的例子指定，getResponseHeader('FooBar')可以返回FooBar字段的值。

3.2 withCredentials 属性
上面说到，CORS请求默认不发送Cookie和HTTP认证信息。如果要把Cookie发到服务器，一方面要服务器同意，指定Access-Control-Allow-Credentials字段。

Access-Control-Allow-Credentials: true
另一方面，开发者必须在AJAX请求中打开withCredentials属性。

var xhr = new XMLHttpRequest();
xhr.withCredentials = true;
否则，即使服务器同意发送Cookie，浏览器也不会发送。或者，服务器要求设置Cookie，浏览器也不会处理。

但是，如果省略withCredentials设置，有的浏览器还是会一起发送Cookie。这时，可以显式关闭withCredentials。

xhr.withCredentials = false;
需要注意的是，如果要发送Cookie，Access-Control-Allow-Origin就不能设为星号，必须指定明确的、与请求网页一致的域名。同时，Cookie依然遵循同源政策，只有用服务器域名设置的Cookie才会上传，其他域名的Cookie并不会上传，且（跨源）原网页代码中的document.cookie也无法读取服务器域名下的Cookie。



2014 年，W3C 的 Web 应用安全工作组（Web Application Security Working Group）发布了 Referrer Policy 草案，对浏览器该如何发送 Referrer 做了详细的规定。新版 Chrome 已经支持了这份草案，我们终于可以灵活地控制自己网站的 Referrer 策略了。

通过新的 Referrer Policy，我们可以针对第三方网站隐藏 Referrer，也可以只发送来源 URL 的 host 部分。但有一点要记住，新策略允许沉默，但不允许说谎。换句话说，你有权不告诉对方请求从哪儿来，但是不允许用假来源去骗人。不过即便是这样，这也对现有一些 Web 应用程序的安全性造成威胁。不少 Web 应用在限制 Referrer 时允许为空，之前想要发送无 Referrer 请求还要一点点技巧，现在就轻而易举了。

Referrer Policy States
新的 Referrer Policy 规定了五种 Referrer 策略：No Referrer、No Referrer When Downgrade、Origin Only、Origin When Cross-origin、和 Unsafe URL。之前就存在的三种策略：never、default 和 always，在新标准里换了个名称。他们的对应关系如下：

策略名称	属性值（新）	属性值（旧）
No Referrer	no-referrer	never
No Referrer When Downgrade	no-referrer-when-downgrade	default
Origin Only	origin	-
Origin When Cross-origin	origin-when-crossorigin	-
Unsafe URL	unsafe-url	always
可以看到，新标准给之前的三种策略赋予了更具意义的新名称，同时还增加了两种新策略。另外现阶段支持 Referrer Policy 的浏览器保留了对旧标准的支持，但还是推荐大家尽快更新。简单介绍下这五种类型的具体含义：

No Referrer：任何情况下都不发送 Referrer 信息；
No Referrer When Downgrade：仅当发生协议降级（如 HTTPS 页面引入 HTTP 资源，从 HTTPS 页面跳到 HTTP 等）时不发送 Referrer 信息。这个规则是现在大部分浏览器默认所采用的；
Origin Only：发送只包含 host 部分的 Referrer。启用这个规则，无论是否发生协议降级，无论是本站链接还是站外链接，都会发送 Referrer 信息，但是只包含协议 + host 部分（不包含具体的路径及参数等信息）；
Origin When Cross-origin：仅在发生跨域访问时发送只包含 host 的 Referrer，同域下还是完整的。它与 Origin Only 的区别是多判断了是否 Cross-origin。需要注意的是协议、域名和端口都一致，才会被浏览器认为是同域；
Unsafe URL：无论是否发生协议降级，无论是本站链接还是站外链接，统统都发送 Referrer 信息。正如其名，这是最宽松而最不安全的策略；
Referrer Policy Delivery
知道了有哪些策略可以用，还需要了解怎么用。这里介绍指定 Referrer Policy 的三种方式：

CSP 响应头
CSP（Content Security Policy），是一个跟页面内容安全有关的规范。在 HTTP 中通过响应头中的 Content-Security-Policy 字段来告诉浏览器当前页面要使用何种 CSP 策略。我之前写过一篇 Content Security Policy 介绍，可以先看看。现在 CSP 还可以通过 referrer 指令和五种可选的指令值，来指定 Referrer 策略，格式非常简单：

Content-Security-Policy: referrer no-referrer|no-referrer-when-downgrade|origin|origin-when-cross-origin|unsafe-url;
注：根据文档，通过 CSP 头部设置 Origin When Cross-origin 策略时，指令值应该用 origin-when-cross-origin，这跟前面的表格里的 origin-when-crossorigin 有差异。实际上经过我的测试，Chrome 42 只支持 origin-when-crossorigin，后续会不会变还不知道，建议大家使用时，自己先测一下。

CSP 的指令和指令值之间以空格分割，多个指令之间用英文分号分割。

<meta> 标签
通过 <meta> 标签也可以指定 Referrer 策略，同样很简单：

<meta name="referrer" content="no-referrer|no-referrer-when-downgrade|origin|origin-when-crossorigin|unsafe-url">
需要注意的是，<meta> 只能放在 <head>...</head> 之间，如果出现的位置不对会被忽略。同样，如果没有给它定义 content 属性，或者 content 属性为空，也会被忽略。如果 content 属性不是合法的取值，浏览器会自动选择 no-referrer 这种最严格的策略。

<a> 标签的 referrer 属性
通过给 <a> 标签增加 referrer 属性也可以指定 Referrer 策略，格式如下：

<a href="http://example.com" referrer="no-referrer|origin|unsafe-url">xxx</a>

# XMLHttpRequest.withCredentials 有什么用?
跨域请求是否提供凭据信息(cookie、HTTP认证及客户端SSL证明等)
也可以简单的理解为，当前请求为跨域类型时是否在请求中协带cookie。
XMLHttpRequest.withCredentials 怎么用?
withCredentials属于XMLHttpRequest对象下的属性，可以对其进行查看或配置
withCredentials: true,// 指定某个请求应该发送凭据。允许客户端携带跨域cookie，也需要此配置
